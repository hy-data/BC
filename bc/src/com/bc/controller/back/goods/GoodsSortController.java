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
import com.bc.service.back.goods.GoodsSortService;
import com.bc.util.Const;
import com.bc.util.DateUtil;
import com.bc.util.JSONUtil;
import com.bc.util.ObjectExcelView;
import com.bc.util.PageData;

/** 
 * 类名称：GoodsSortController
 * 创建人：liuqiang
 * 创建时间：2016-08-19
 */
@Controller
@RequestMapping(value="/goodssort")
public class GoodsSortController extends BaseController {
	
	@Resource(name="goodssortService")
	private GoodsSortService goodssortService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增GoodsSort");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("UPDATE_TIME", DateUtil.getTimes());
		goodssortService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除GoodsSort");
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			goodssortService.delete(pd);
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
		logBefore(logger, "修改GoodsSort");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("UPDATE_TIME", DateUtil.getTimes());
		goodssortService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表GoodsSort");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = goodssortService.list(page);	//列出GoodsSort列表
			mv.setViewName("back/goods/sort/goodssort_list");
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
		logBefore(logger, "去新增GoodsSort页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("PARENT_ID", "0");
		List<PageData> sortList = new ArrayList<PageData>();
		try {
			sortList = goodssortService.getTree(pd);// 查询全部艺术品分类列表
			mv.setViewName("back/goods/sort/goodssort_edit");
			mv.addObject("sortList", sortList);
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
		logBefore(logger, "去修改GoodsSort页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = goodssortService.findById(pd);	//根据ID读取
			mv.setViewName("back/goods/sort/goodssort_edit");
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
		logBefore(logger, "批量删除GoodsSort");
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				goodssortService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出GoodsSort到excel");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("分类名称");	//1
			titles.add("父级分类id");	//2
			titles.add("父级分类名称");	//3
			titles.add("是否显示");	//4
			titles.add("关键字");	//5
			titles.add("分类描述");	//6
			titles.add("商品类型ids");	//7
			titles.add("商品属性ids");	//8
			titles.add("导航栏");	//9
			titles.add("排序");	//10
			titles.add("数量单位");	//11
			titles.add("首页推荐");	//12
			titles.add("价格区间个数");	//13
			titles.add("状态，预留字段");	//14
			dataMap.put("titles", titles);
			List<PageData> varOList = goodssortService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("NAME"));	//1
				vpd.put("var2", varOList.get(i).get("PARENT_ID").toString());	//2
				vpd.put("var3", varOList.get(i).getString("PARENT_NAME"));	//3
				vpd.put("var4", varOList.get(i).get("IS_SHOW").toString());	//4
				vpd.put("var5", varOList.get(i).getString("KEY_WORDS"));	//5
				vpd.put("var6", varOList.get(i).getString("SORT_DESCRIPTION"));	//6
				vpd.put("var7", varOList.get(i).getString("TYPE_IDS"));	//7
				vpd.put("var8", varOList.get(i).getString("PROPERTY_IDS"));	//8
				vpd.put("var9", varOList.get(i).get("NAVIGATION").toString());	//9
				vpd.put("var10", varOList.get(i).get("SORT").toString());	//10
				vpd.put("var11", varOList.get(i).getString("NUMBER_UNIT"));	//11
				vpd.put("var12", varOList.get(i).getString("HOME_RECOMMEND"));	//12
				vpd.put("var13", varOList.get(i).getString("NUM_PRICE"));	//13
				vpd.put("var14", varOList.get(i).get("STATUS").toString());	//14
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
