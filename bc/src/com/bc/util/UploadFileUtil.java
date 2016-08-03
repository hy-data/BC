package com.bc.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

/** 
 * 类名称：UploadFileUtil
 * 创建人：ruhe
 * 文件上传工具类
 */
public class UploadFileUtil {

	private static final  String IMAGE = "IMAGE";
	
	private static final String DOC = "DOCUMENT";
	
	/**
	 * 图片类型最大上传大小为5M
	 */
	private static long imageSize = 5*1024*1024;
	
	/**
	 * 文件类型最大上传大小为30M
	 */
	private static long docSize = 30*1024*1024;
	
	private static List<String> getListByType(String type){
		List<String> list = new ArrayList<String>();
		if(IMAGE.equals(type)){
			list.add("JPEG");
			list.add("JPG");
			list.add("PNG");
			list.add("GIF");
		}else if(DOC.equals(type)){
			list.add("XLS");
			list.add("XLSX");
		}
		return list;
	}
	public static UploadFile upload(MultipartFile filedata, String filePath)throws FileUploadException{
		return upload(filedata, filePath,IMAGE);
	}
	/**
	 * 上传文件，并返回上传后的文件信息
	 * @param filedata MultipartFile
	 * @param filePath 上传文件的目录
	 * @param uploadType 上传文件的类型
	 * @return
	 * @throws FileUploadException
	 */
	public static UploadFile upload(MultipartFile filedata, String filePath, String uploadType) throws FileUploadException{
		if (filedata != null && !filedata.isEmpty()) {
			// 获取图片的文件名
			String fileName = filedata.getOriginalFilename();
			// 获取图片的扩展名
			String extensionName = fileName
					.substring(fileName.lastIndexOf(".") + 1);
			// 新的图片文件名 = 获取时间戳+"."图片扩展名
			String newFileName = String.valueOf(System.currentTimeMillis())
					+ "." + extensionName;
			//根据后缀名，验证文件类型
			List<String> typeList = getListByType(uploadType);
			if (!typeList.contains(extensionName.toUpperCase())) {
				throw new FileUploadException("上传文件类型非法");
			}
			//验证文件大小
			if(IMAGE.equals(uploadType)){
				if(filedata.getSize()>imageSize){
					throw new FileUploadException("上传文件不能超过"+(imageSize/1024/1024)+"M");
				}
			}else if(DOC.equals(uploadType)){
				if(filedata.getSize()>docSize){
					throw new FileUploadException("上传文件不能超过"+(docSize/1024/1024)+"M");
				}
			}
			try {
				saveFile(newFileName, filedata, "/" + filePath);
				UploadFileUtil.UploadFile uploadFile= new UploadFileUtil.UploadFile();
				uploadFile.setFileFullName(filePath+"/"+newFileName);
				uploadFile.setFileName(fileName);
				return uploadFile;
			} catch (Exception e) {
				throw new FileUploadException("文件上传失败");
			}
		}
		throw new FileUploadException("未选择上传文件");
	}

	/**
	 * 保存
	 * 
	 * @param newFileName
	 * @param filedata
	 * @throws Exception 
	 */
	private static String saveFile(String newFileName, MultipartFile filedata,
			String picDir) throws Exception {
		// 根据配置文件获取服务器图片存放路径
		String saveFilePath = PathUtil.getClasspath()+picDir;

		/* 构建文件目录 */
		File fileDir = new File(saveFilePath);
		if (!fileDir.exists()) {
			System.out.println("111111"+fileDir.mkdirs());
		}

		String fileName = saveFilePath + "/" + newFileName;
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(fileName);
			// 写入文件
			out.write(filedata.getBytes());
			out.flush();
		} catch (Exception e) {
			throw e;
		} finally {
			if(out!=null){
				out.close();
				out = null;
			}
		}

		return fileName;
	}
	public static class UploadFile implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 8386683358899981328L;

		/**
		 * 原文件名称
		 */
		private String fileName;
		/**
		 * 保存后的文件名称
		 */
		private String fileFullName;
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getFileFullName() {
			return fileFullName;
		}
		public void setFileFullName(String fileFullName) {
			this.fileFullName = fileFullName;
		}
		
		
	}
}
