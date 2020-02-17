package com.duchenyu.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bobo.common.utils.FileUtil;

@Controller
@RequestMapping("/file/")
public class FileController {

		@Value("${upload.path}")
		private String filePath;
		
		@Value("${pic.path}")
		private String picPath;
		
		
		@RequestMapping("upload")
		public @ResponseBody Object upload(@RequestParam("file") MultipartFile file) {
			return uploadImg(file);
		}
		
		@RequestMapping("uploadImg")
		public @ResponseBody Object uploadImg(@RequestParam("imgFile") MultipartFile file) {
			
			Map<String, Object> result=new HashMap<>();
			
			result.put("error",1);
			
			if(file.getSize()>0) {
				String extName = FileUtil.getExtendName(file.getOriginalFilename());
				
				String fileName=UUID.randomUUID()+extName;
				
				String fileFullName=filePath+fileName;
				
				try {
					file.transferTo(new File(fileFullName));
					
					result.put("error",0);
					
					result.put("url", picPath+fileName);
					
					return result;
					
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			return uploadImg(file);
			
		}

}
