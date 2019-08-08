package com.vision.erp.common;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;

public class ImageFileUpload {

	
	public ImageFileUpload() {
		
	}
	
	//DB에 저장되어야할 파일 이름을 리턴
	public static String fileUpload(Map<String, Object> map) throws Exception{
		
		System.out.println("File Uploading...");
		
		String str = (String)map.get("base64");
		int idx = str.indexOf(",");
		byte[] decodeByte = Base64.decodeBase64(str.substring(idx+1));
		BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(decodeByte));
		
		String fileName = (String)map.get("name");
		String fileForm = fileName.substring(fileName.indexOf(".")+1,fileName.length());
		
		UUID name = UUID.randomUUID();
		ImageIO.write(bufImg, fileForm, new File("//Users//munmyeonghwan//git//VISION//VISION//WebContent//Images//"+name+"."+fileForm));
//		ImageIO.write(bufImg, fileForm, new File("C:\\Users\\qhdqh\\git\\VISION\\VISION\\WebContent\\Images\\"+name+"."+fileForm));
//		ImageIO.write(bufImg, fileForm, new File("C:\\Users\\AAA\\git\\VISION\\VISION\\WebContent\\Images\\"+name+"."+fileForm));
		System.out.println("File Upload Success!!!!!!!!!!!");
		
		return name+"."+fileForm;
		
	}
	
}
