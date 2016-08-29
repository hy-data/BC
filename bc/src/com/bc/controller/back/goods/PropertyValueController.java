package com.bc.controller.back.goods;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import com.bc.service.back.goods.PropertyValueService;
import com.bc.util.Const;
import com.bc.util.DateUtil;
import com.bc.util.JSONUtil;
import com.bc.util.ObjectExcelView;
import com.bc.util.PageData;

/** 
 * 类名称：PropertyValueController
 * 创建人：liuqiang
 * 创建时间：2016-08-29
 */
@Controller
@RequestMapping(value="/propertyvalue")
public class PropertyValueController extends BaseController {
	
	@Resource(name="propertyvalueService")
	private PropertyValueService propertyvalueService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增PropertyValue");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String ID = (String)pd.get("ID");
		String TYPE_ID = (String)pd.get("TYPE_ID");
		String GOODS_ID = (String)pd.get("GOODS_ID");
		pd.remove("ID");
		pd.remove("TYPE_ID");
		pd.remove("GOODS_ID");
		Iterator it = pd.keySet().iterator();  
        while(it.hasNext()){  
             String key;     
             String value;     
             key=it.next().toString();     
             value=(String) pd.get(key);
             PageData p = new PageData();
             System.out.println(key + "    " + value);
             if(key.contains(",")){
            	 String [] arr = key.split(",");
            	 p.put("GOODS_PROPERTY_ID", Integer.parseInt(arr[2]));
                 p.put("GOODS_PROPERTY_TYPE", Integer.parseInt(arr[1]));
                 p.put("GOODS_PROPERTY_VALUE", arr[0]);
             }
             p.put("GOODS_ID", Integer.parseInt(GOODS_ID));
             p.put("GOODS_TYPE_ID", Integer.parseInt(TYPE_ID));
             p.put("PROPERTY_STATUS", 0);//预留字段
             p.put("UPDATE_TIME", DateUtil.getTime());
             propertyvalueService.save(p);
        }  
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除PropertyValue");
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			propertyvalueService.delete(pd);
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
		logBefore(logger, "修改PropertyValue");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("UPDATE_TIME", DateUtil.getTimes());
		propertyvalueService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表PropertyValue");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = propertyvalueService.list(page);	//列出PropertyValue列表
			mv.setViewName("back/goods/propertyvalue/propertyvalue_list");
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
		logBefore(logger, "去新增PropertyValue页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("back/goods/propertyvalue/propertyvalue_edit");
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
		logBefore(logger, "去修改PropertyValue页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = propertyvalueService.findById(pd);	//根据ID读取
			mv.setViewName("back/goods/propertyvalue/propertyvalue_edit");
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
		logBefore(logger, "批量删除PropertyValue");
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				propertyvalueService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出PropertyValue到excel");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("商品类型id");	//1
			titles.add("商品属性id");	//2
			titles.add("属性类型");	//3
			titles.add("商品属性值");	//4
			titles.add("预留字段");	//5
			titles.add("更新时间");	//6
			titles.add("商品id");	//7
			dataMap.put("titles", titles);
			List<PageData> varOList = propertyvalueService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).get("GOODS_TYPE_ID").toString());	//1
				vpd.put("var2", varOList.get(i).get("GOODS_PROPERTY_ID").toString());	//2
				vpd.put("var3", varOList.get(i).get("GOODS_PROPERTY_TYPE").toString());	//3
				vpd.put("var4", varOList.get(i).getString("GOODS_PROPERTY_VALUE"));	//4
				vpd.put("var5", varOList.get(i).get("PORPERTY_STATUS").toString());	//5
				vpd.put("var6", varOList.get(i).getString("UPDATE_TIME"));	//6
				vpd.put("var7", varOList.get(i).get("GOODS_ID").toString());	//7
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
