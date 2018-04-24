package com.mooc.house.biz.service.impl;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.google.common.io.Files;

@Service
public class FileService {

	@Value("${file.path}")
	private String filePath;
	
	/**
	 * 将上传文件返回成一个列表路径
	 */
	public List<String> getImgPath(List<MultipartFile> files){
		ArrayList<String> paths = Lists.newArrayList();
		files.forEach(file -> {
			File localFile = null;
			if(!file.isEmpty()) {
				try {
					localFile = saveToLocal(file,filePath);
					String path = StringUtils.substringAfterLast(localFile.getAbsolutePath(), filePath);
					paths.add(path);
				} catch (Exception e) {
					throw new IllegalArgumentException();
				}
			}
		});
		return paths;
	}

	private File saveToLocal(MultipartFile file, String filePath2) throws IOException {
		File newFile = new File(filePath + "/" + Instant.now().getEpochSecond() + "/" + file.getOriginalFilename());
		if(!newFile.exists()) {
			//创建上机目录
			newFile.getParentFile().mkdirs();
			newFile.createNewFile();
		}
		Files.write(file.getBytes(), newFile);
		return null;
	}
}
