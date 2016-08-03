package com.bc.controller.back.shop;

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
import com.bc.service.back.shop.ShopService;
import com.bc.util.Const;
import com.bc.util.JSONUtil;
import com.bc.util.ObjectExcelView;
import com.bc.util.PageData;

/** 
 * 类名称：ShopController
 * 创建人：FH 
 * 创建时间：2016-08-03
 */
@Controller
@RequestMapping(value="/shop")
public class ShopController extends BaseController {
	
	@Resource(name="shopService")
	private ShopService shopService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增Shop");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("SMID", "");	//所属生活层id
		pd.put("CREATEBY", "");	//创建人
		pd.put("LASTUPDATE", "");	//更新时间
		pd.put("LASTUPDATEBY", "");	//更新人
		shopService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Shop");
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			shopService.delete(pd);
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
		logBefore(logger, "修改Shop");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		shopService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Shop");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = shopService.list(page);	//列出Shop列表
			mv.setViewName("back/shop/shop_list");
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
		logBefore(logger, "去新增Shop页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("shop/shop/shop_edit");
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
		logBefore(logger, "去修改Shop页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = shopService.findById(pd);	//根据ID读取
			mv.setViewName("shop/shop/shop_edit");
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
		logBefore(logger, "批量删除Shop");
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				shopService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出Shop到excel");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("所属生活层id");	//1
			titles.add("名称");	//2
			titles.add("简介");	//3
			titles.add("介绍");	//4
			titles.add("关键字");	//5
			titles.add("公告");	//6
			titles.add("等级");	//7
			titles.add("地址");	//8
			titles.add("国家");	//9
			titles.add("省份");	//10
			titles.add("城市");	//11
			titles.add("电话");	//12
			titles.add("客服邮箱");	//13
			titles.add("是否闭店");	//14
			titles.add("闭店理由");	//15
			titles.add("闭店时间");	//16
			titles.add("是否开启积分策略");	//17
			titles.add("商品标签显示数量");	//18
			titles.add("未支付订单失效时间");	//19
			titles.add("维护时间");	//20
			titles.add("创建时间");	//21
			titles.add("创建人");	//22
			titles.add("更新时间");	//23
			titles.add("更新人");	//24
			dataMap.put("titles", titles);
			List<PageData> varOList = shopService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).get("SMID").toString());	//1
				vpd.put("var2", varOList.get(i).getString("NAME"));	//2
				vpd.put("var3", varOList.get(i).getString("INTRO"));	//3
				vpd.put("var4", varOList.get(i).getString("DESCRIPTION"));	//4
				vpd.put("var5", varOList.get(i).getString("KEYSWORD"));	//5
				vpd.put("var6", varOList.get(i).getString("INFO"));	//6
				vpd.put("var7", varOList.get(i).getString("LEVEL"));	//7
				vpd.put("var8", varOList.get(i).getString("ADDR"));	//8
				vpd.put("var9", varOList.get(i).getString("COUNTRY"));	//9
				vpd.put("var10", varOList.get(i).getString("PROVINCE"));	//10
				vpd.put("var11", varOList.get(i).getString("CITY"));	//11
				vpd.put("var12", varOList.get(i).getString("PHONE"));	//12
				vpd.put("var13", varOList.get(i).getString("EMAIL"));	//13
				vpd.put("var14", varOList.get(i).getString("ISCLOSED"));	//14
				vpd.put("var15", varOList.get(i).getString("CLOSEREASON"));	//15
				vpd.put("var16", varOList.get(i).getString("CLOSEDATE"));	//16
				vpd.put("var17", varOList.get(i).getString("ISINTEGRAL"));	//17
				vpd.put("var18", varOList.get(i).get("TAGNUMBER").toString());	//18
				vpd.put("var19", varOList.get(i).get("ORDERFAILURETIME").toString());	//19
				vpd.put("var20", varOList.get(i).get("REPAIRTIME").toString());	//20
				vpd.put("var21", varOList.get(i).getString("CREATEDATE"));	//21
				vpd.put("var22", varOList.get(i).get("CREATEBY").toString());	//22
				vpd.put("var23", varOList.get(i).getString("LASTUPDATE"));	//23
				vpd.put("var24", varOList.get(i).get("LASTUPDATEBY").toString());	//24
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
