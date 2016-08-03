package com.bc.controller.base;


import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bc.entity.Page;
import com.bc.util.Const;
import com.bc.util.DateUtil;
import com.bc.util.FileUpload;
import com.bc.util.Logger;
import com.bc.util.PageData;
import com.bc.util.PathUtil;
import com.bc.util.UuidUtil;

public class BaseController {
	
	protected Logger logger = Logger.getLogger(this.getClass());

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 6357869213649815390L;
	
	/**
	 * 得到PageData
	 */
	public PageData getPageData(){
		return new PageData(this.getRequest());
	}
	
	/**
	 * 得到ModelAndView
	 */
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}
	
	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		return request;
	}

	/**
	 * 得到32位的uuid
	 * @return
	 */
	public String get32UUID(){
		
		return UuidUtil.get32UUID();
	}
	
	/**
	 * 得到分页列表的信息 
	 */
	public Page getPage(){
		
		return new Page();
	}
	
	public static void logBefore(Logger logger, String interfaceName){
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}
	
	public static void logAfter(Logger logger){
		logger.info("end");
		logger.info("");
	}
	/**
	 * 上传图片
	 * @param file
	 * @param path
	 * @return 图片保存的路径
	 * @throws Exception
	 * @author changjingyu
	 *  2015-12-29
	 */
	public String uploadImage(@RequestParam(required=false) MultipartFile file,String path) throws Exception{
		logBefore(logger, "上传图片");
		String  ffile = DateUtil.getDays(), fileName = "";
		if (null != file && !file.isEmpty()) {
			String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + path + ffile;		//文件上传路径
			fileName = FileUpload.fileUp(file, filePath, this.get32UUID());//执行上传
		}
		String picPath = Const.FILEPATHIMG + path + ffile + "/" + fileName;
		return picPath;
	}
}
