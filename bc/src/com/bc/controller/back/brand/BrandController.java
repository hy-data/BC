package com.bc.controller.back.brand;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bc.controller.base.BaseController;
import com.bc.entity.Page;
import com.bc.service.back.brand.BrandService;
import com.bc.util.Const;
import com.bc.util.DateUtil;
import com.bc.util.FileUpload;
import com.bc.util.JSONUtil;
import com.bc.util.ObjectExcelView;
import com.bc.util.PageData;
import com.bc.util.PathUtil;

/** 
 * 类名称：BrandController
 * 创建人：liuqiang
 * 创建时间：2016-08-08
 */
@Controller
@RequestMapping(value="/brand")
public class BrandController extends BaseController {
	
	@Resource(name="brandService")
	private BrandService brandService;
	
	/**
	 * 新增
	 */
	
	@RequestMapping(value = "/save")
	public ModelAndView save(
			@RequestParam(value = "LOGO_IMAGE", required = false) MultipartFile LOGO_IMAGE,
			String NAME, String URL,
			String DESCRIPTION, String IS_SHOW, String SORT)
			throws Exception {
		logBefore(logger, "新增ArtworkSort");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		if (LOGO_IMAGE != null && !LOGO_IMAGE.isEmpty()) {
			String ffile = DateUtil.getDays(), fileName = "";

			String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG
					+ ffile; // 文件上传路径
			fileName = FileUpload.fileUp(LOGO_IMAGE, filePath, this.get32UUID());
			pd.put("LOGO_IMAGE", Const.FILEPATHIMG + ffile + "/"
					+ fileName);
		}
		pd.put("NAME", NAME); // 主键
		pd.put("URL", URL);
		pd.put("DESCRIPTION", DESCRIPTION);
		pd.put("IS_SHOW", Integer.parseInt(IS_SHOW));
		pd.put("SORT", Integer.parseInt(SORT));
		pd.put("UPDATE_TIME", DateUtil.getTime());
		brandService.save(pd);
		mv.addObject("msg", "success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Brand");
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			brandService.delete(pd);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value = "/edit")
	public ModelAndView edit(
			@RequestParam(value = "LOGO_IMAGE", required = false) MultipartFile LOGO_IMAGE,String ID, 
			String NAME, String URL,
			String DESCRIPTION, String IS_SHOW, String SORT)
			throws Exception {
		logBefore(logger, "新增ArtworkSort");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd.put("ID", ID);
		pd = brandService.findById(pd);
		if (LOGO_IMAGE != null && !LOGO_IMAGE.isEmpty()) {
			String ffile = DateUtil.getDays(), fileName = "";

			String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG
					+ ffile; // 文件上传路径
			fileName = FileUpload.fileUp(LOGO_IMAGE, filePath, this.get32UUID());
			pd.put("LOGO_IMAGE", Const.FILEPATHIMG + ffile + "/"
					+ fileName);
		}
		pd.put("NAME", NAME); // 主键
		pd.put("URL", URL);
		pd.put("DESCRIPTION", DESCRIPTION);
		pd.put("IS_SHOW", Integer.parseInt(IS_SHOW));
		pd.put("SORT", Integer.parseInt(SORT));
		pd.put("UPDATE_TIME", DateUtil.getTime());
		brandService.edit(pd);
		mv.addObject("msg", "success");
		mv.setViewName("save_result");
		return mv;
	}
	
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Brand");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = brandService.list(page);	//列出Brand列表
			mv.setViewName("back/brand/brand_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(){
		logBefore(logger, "去新增Brand页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("back/brand/brand_edit");
			mv.addObject("msg", "save");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit(){
		logBefore(logger, "去修改Brand页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = brandService.findById(pd);	//根据ID读取
			mv.setViewName("back/brand/brand_edit");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() {
		logBefore(logger, "批量删除Brand");
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				brandService.deleteAll(ArrayDATA_IDS);
				pd.put("msg", "ok");
			}else{
				pd.put("msg", "no");
			}
			pdList.add(pd);
			map.put("list", pdList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return JSONUtil.returnObject(pd, map);
	}
	
	/*
	 * 导出到excel
	 * @return
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel(){
		logBefore(logger, "导出Brand到excel");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("品牌名称");	//1
			titles.add("品牌网址");	//2
			titles.add("品牌LOGO");	//3
			titles.add("品牌描述");	//4
			titles.add("排序");	//5
			titles.add("是否显示");	//6
			dataMap.put("titles", titles);
			List<PageData> varOList = brandService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("NAME"));	//1
				vpd.put("var2", varOList.get(i).getString("URL"));	//2
				vpd.put("var3", varOList.get(i).getString("LOGO_IMAGE"));	//3
				vpd.put("var4", varOList.get(i).getString("DESCRIPTION"));	//4
				vpd.put("var5", varOList.get(i).get("SORT").toString());	//5
				vpd.put("var6", varOList.get(i).get("IS_SHOW").toString());	//6
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/* ===============================权限================================== */
	public Map<String, String> getHC(){
		Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
		Session session = currentUser.getSession();
		return (Map<String, String>)session.getAttribute(Const.SESSION_QX);
	}
	/* ===============================权限================================== */
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
