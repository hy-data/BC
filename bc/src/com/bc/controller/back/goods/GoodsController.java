package com.bc.controller.back.goods;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.controller.base.BaseController;
import com.bc.entity.Page;
import com.bc.service.back.goods.GoodsService;
import com.bc.util.JSONUtil;
import com.bc.util.Const;
import com.bc.util.ObjectExcelView;
import com.bc.util.PageData;

/** 
 * 类名称：GoodsController
 * 创建人：FH 
 * 创建时间：2016-07-29
 */
@Controller
@RequestMapping(value="/goods")
public class GoodsController extends BaseController {
	
	@Resource(name="goodsService")
	private GoodsService goodsService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增Goods");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("GOODS_ID", this.get32UUID());	//主键
		goodsService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Goods");
		PageData pd = new PageData();
		try{
			pd = this.getPageData(); 
			goodsService.delete(pd);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, "修改Goods");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		goodsService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Goods");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = goodsService.list(page);	//列出Goods列表
			mv.setViewName("back/goods/goods_list");
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
		logBefore(logger, "去新增Goods页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("back/goods/goods_add");
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
		logBefore(logger, "去修改Goods页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = goodsService.findById(pd);	//根据ID读取
			mv.setViewName("back/goods/goods_edit");
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
		logBefore(logger, "批量删除Goods");
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				goodsService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出Goods到excel");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("sid");	//1
			titles.add("name");	//2
			titles.add("type");	//3
			titles.add("code");	//4
			titles.add("keyswork");	//5
			titles.add("title");	//6
			titles.add("pictureurl");	//7
			titles.add("price");	//8
			titles.add("starttime");	//9
			titles.add("ispub");	//10
			titles.add("totalnum");	//11
			titles.add("totalwarn");	//12
			titles.add("brand");	//13
			titles.add("promotionprice");	//14
			titles.add("promotiontime");	//15
			titles.add("limitnum");	//16
			titles.add("marketprice");	//17
			titles.add("feedbackprice");	//18
			titles.add("isshow");	//19
			titles.add("labelid");	//20
			titles.add("weight");	//21
			titles.add("issale");	//22
			titles.add("expressprice");	//23
			titles.add("isexpress");	//24
			titles.add("createdate");	//25
			titles.add("createby");	//26
			titles.add("lastupdate");	//27
			titles.add("lastupdateby");	//28
			dataMap.put("titles", titles);
			List<PageData> varOList = goodsService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).get("SID").toString());	//1
				vpd.put("var2", varOList.get(i).getString("NAME"));	//2
				vpd.put("var3", varOList.get(i).getString("TYPE"));	//3
				vpd.put("var4", varOList.get(i).getString("CODE"));	//4
				vpd.put("var5", varOList.get(i).getString("KEYSWORK"));	//5
				vpd.put("var6", varOList.get(i).getString("TITLE"));	//6
				vpd.put("var7", varOList.get(i).getString("PICTUREURL"));	//7
				vpd.put("var8", varOList.get(i).getString("PRICE"));	//8
				vpd.put("var9", varOList.get(i).getString("STARTTIME"));	//9
				vpd.put("var10", varOList.get(i).getString("ISPUB"));	//10
				vpd.put("var11", varOList.get(i).get("TOTALNUM").toString());	//11
				vpd.put("var12", varOList.get(i).get("TOTALWARN").toString());	//12
				vpd.put("var13", varOList.get(i).getString("BRAND"));	//13
				vpd.put("var14", varOList.get(i).getString("PROMOTIONPRICE"));	//14
				vpd.put("var15", varOList.get(i).getString("PROMOTIONTIME"));	//15
				vpd.put("var16", varOList.get(i).get("LIMITNUM").toString());	//16
				vpd.put("var17", varOList.get(i).getString("MARKETPRICE"));	//17
				vpd.put("var18", varOList.get(i).getString("FEEDBACKPRICE"));	//18
				vpd.put("var19", varOList.get(i).getString("ISSHOW"));	//19
				vpd.put("var20", varOList.get(i).getString("LABELID"));	//20
				vpd.put("var21", varOList.get(i).getString("WEIGHT"));	//21
				vpd.put("var22", varOList.get(i).getString("ISSALE"));	//22
				vpd.put("var23", varOList.get(i).getString("EXPRESSPRICE"));	//23
				vpd.put("var24", varOList.get(i).getString("ISEXPRESS"));	//24
				vpd.put("var25", varOList.get(i).getString("CREATEDATE"));	//25
				vpd.put("var26", varOList.get(i).get("CREATEBY").toString());	//26
				vpd.put("var27", varOList.get(i).getString("LASTUPDATE"));	//27
				vpd.put("var28", varOList.get(i).get("LASTUPDATEBY").toString());	//28
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
