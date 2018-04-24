package com.mooc.house.biz.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.collect.Lists;
import com.mooc.house.biz.mapper.UserMapper;
import com.mooc.house.biz.service.IUserService;
import com.mooc.house.common.model.User;
import com.mooc.house.common.utils.BeanHelper;
import com.mooc.house.common.utils.HashUtils;


@Service
public class UserService implements IUserService{
	
	//设置email的缓存及过期时间
	private final Cache<String, String> registerCache = CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(15, TimeUnit.MINUTES)
																.removalListener(new RemovalListener<String, String>() {
														
															public void onRemoval(RemovalNotification<String, String> notification) {
																userMapper.delete(notification.getValue());
															}
														}).build();
														
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private FileService fileService;
	
	@Value("${domain.name}")
	private String domainName;
	
	@Autowired
	private MailService mailService;
	
	@Override
	public List<User> getAllUsers() {
		return userMapper.getAllUsers();
	}
	
	/**
	 * 步骤:第四章1.4
	 * 1.插入数据库，非激活
	 * 2.密码加盐
	 * 3.保存头像到本地
	 * 4.生成key，绑定email
	 * 5.发送邮件给用户
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean addAccount(User account) {
		//加盐
		account.setPasswd(HashUtils.encryPassword(account.getPasswd()));
		//创建FileService
		List<String> imgList = fileService.getImgPath(Lists.newArrayList(account.getAvatarFile()));
		if(!imgList.isEmpty()) {
			account.setAvatar(imgList.get(0));
		}
		//在数据库insert的时候需要将所有的数据都设置进去,所以不能为null,为null就会报错
		//所以这里借助BeanHelper
		BeanHelper.setDefaultProp(account, User.class);
		BeanHelper.onInsert(account);
		account.setEnable(0);
		userMapper.insert(account);
		regisyterNotify(account.getEmail());
		return false;
	}

	/**
	 * 步骤:第四章1.3
	 * 1.缓存email与用户的关系
	 * 2.使用springmail发送邮件
	 * 3.异步发送邮件
	 * @param email
	 */
	@Async
	public void regisyterNotify(String email) {
		String randomKey = RandomStringUtils.randomAlphabetic(10);
		registerCache.put(randomKey, email);
		//构造url
		String url = "http://" + domainName + "/accounts/verify?key="+randomKey;
		mailService.sendMail("房产平台激活链接",url,email);
	}

}
