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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bc.controller.base.BaseController;
import com.bc.entity.Page;
import com.bc.entity.system.User;
import com.bc.service.back.brand.BrandService;
import com.bc.service.back.goods.GoodsService;
import com.bc.util.Const;
import com.bc.util.DateUtil;
import com.bc.util.FileUpload;
import com.bc.util.JSONUtil;
import com.bc.util.ObjectExcelView;
import com.bc.util.PageData;
import com.bc.util.PathUtil;

/** 
 * 类名称：GoodsController
 * 创建人：liuqiang
 * 创建时间：2016-08-08
 */
@Controller
@RequestMapping(value="/goods")
public class GoodsController extends BaseController {
	
	@Resource(name="goodsService")
	private GoodsService goodsService;
	@Resource(name="brandService")
	private BrandService brandService;
	/**
	 * 新增
	 */
	
	@RequestMapping(value = "/save")
	public ModelAndView save(
			@RequestParam(value = "PICTUREURL", required = false) MultipartFile PICTUREURL,
			String SID,
			String NAME,
			String TYPE,
			String CODE,
			String KEYSWORK,
			String TITLE,
			String PRICE,
			String STARTTIME,
			String ISPUB,
			String TOTALNUM,
			String TOTALWARN,
			String BRAND_ID,
			String PROMOTIONTIME,
			String PROMOTIONPRICE,
			String LIMITNUM,
			String ISSHOW,
			String WEIGHT,
			String ISSALE,
			String ISEXPRESS,
			String DESCRIPTION
			)
			throws Exception {
		logBefore(logger, "新增ArtworkSort");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		if (PICTUREURL != null && !PICTUREURL.isEmpty()) {
			String ffile = DateUtil.getDays(), fileName = "";

			String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG
					+ ffile; // 文件上传路径
			fileName = FileUpload.fileUp(PICTUREURL, filePath, this.get32UUID());
			pd.put("PICTUREURL", Const.FILEPATHIMG + ffile + "/"
					+ fileName);
		}
		pd.put("SID", SID); // 主键
		pd.put("NAME", SID);
		pd.put("TYPE", TYPE);
		pd.put("CODE", CODE);
		pd.put("KEYSWORK", KEYSWORK);
		pd.put("TITLE", TITLE);
		pd.put("PRICE", PRICE);
		pd.put("STARTTIME", STARTTIME);
		pd.put("ISPUB", ISPUB);
		pd.put("TOTALNUM", TOTALNUM);
		pd.put("TOTALWARN", TOTALWARN);
		pd.put("BRAND", BRAND_ID);
		pd.put("PROMOTIONTIME", PROMOTIONTIME);
		pd.put("PROMOTIONPRICE", PROMOTIONPRICE);
		pd.put("LIMITNUM", LIMITNUM);
		pd.put("ISSHOW", ISSHOW);
		pd.put("WEIGHT", WEIGHT);
		pd.put("ISSALE", ISSALE);
		pd.put("ISEXPRESS", ISEXPRESS);
		pd.put("DESCRIPTION", DESCRIPTION);
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		User user = (User)session.getAttribute(Const.SESSION_USER);
		pd.put("CREATEDATE", DateUtil.getTime());
		pd.put("CREATEBY", user.getNAME() + "," + user.getIP());
		pd.put("LASTUPDATE", DateUtil.getTime());
		pd.put("LASTUPDATEBY", user.getNAME() + "," + user.getIP());
		
		goodsService.save(pd);
		mv.addObject("msg", "success");
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
	public ModelAndView edit(
			@RequestParam(value = "PICTUREURL", required = false) MultipartFile PICTUREURL,
			String ID,
			String SID,
			String NAME,
			String TYPE,
			String CODE,
			String KEYSWORK,
			String TITLE,
			String PRICE,
			String STARTTIME,
			String ISPUB,
			String TOTALNUM,
			String TOTALWARN,
			String BRAND,
			String PROMOTIONTIME,
			String PROMOTIONPRICE,
			String LIMITNUM,
			String ISSHOW,
			String WEIGHT,
			String ISSALE,
			String ISEXPRESS,
			String DESCRIPTION
			)
			throws Exception {
		logBefore(logger, "修改Goods");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("ID", ID.trim());
		pd = goodsService.findById(pd);
		if (PICTUREURL != null && !PICTUREURL.isEmpty()) {
			String ffile = DateUtil.getDays(), fileName = "";

			String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG
					+ ffile; // 文件上传路径
			fileName = FileUpload.fileUp(PICTUREURL, filePath, this.get32UUID());
			pd.put("PICTUREURL", Const.FILEPATHIMG + ffile + "/"
					+ fileName);
		}
		pd.put("SID", SID); // 主键
		pd.put("NAME", SID);
		pd.put("TYPE", TYPE);
		pd.put("CODE", CODE);
		pd.put("KEYSWORK", KEYSWORK);
		pd.put("TITLE", TITLE);
		pd.put("PRICE", PRICE);
		pd.put("STARTTIME", STARTTIME);
		pd.put("ISPUB", ISPUB);
		pd.put("TOTALNUM", TOTALNUM);
		pd.put("TOTALWARN", TOTALWARN);
		pd.put("BRAND", BRAND);
		pd.put("PROMOTIONTIME", PROMOTIONTIME);
		pd.put("PROMOTIONPRICE", PROMOTIONPRICE);
		pd.put("LIMITNUM", LIMITNUM);
		pd.put("ISSHOW", ISSHOW);
		pd.put("WEIGHT", WEIGHT);
		pd.put("ISSALE", ISSALE);
		pd.put("ISEXPRESS", ISEXPRESS);
		pd.put("DESCRIPTION", DESCRIPTION);
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		User user = (User)session.getAttribute(Const.SESSION_USER);
		pd.put("CREATEDATE", DateUtil.getTime());
		pd.put("CREATEBY", user.getNAME() + "," + user.getIP());
		pd.put("LASTUPDATE", DateUtil.getTime());
		pd.put("LASTUPDATEBY", user.getNAME() + "," + user.getIP());
		goodsService.edit(pd);
		mv.addObject("msg", "success");
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
			mv.setViewName("back/goods/goods_edit");
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
	 * 上传图片
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/upImage")
	@ResponseBody
	public Object upImage(
			@RequestParam(required = false) MultipartFile uploadImg)
			throws Exception {
		PageData pd = new PageData();
		String name = this.get32UUID();
		Map<String, String> map = new HashMap<String, String>();
		String ffile = DateUtil.getDays(), fileName = "";
		if (null != uploadImg && !uploadImg.isEmpty()) {
			long size = uploadImg.getSize();
			if (size > 1024 * 300) {
				map.put("result", "error");
				return JSONUtil.returnObject(pd, map);
			}
			String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG
					+ Const.UPLOAD_ARTWORK_IMAGE_PATH + ffile; // 鏂囦欢涓婁紶璺緞
			fileName = FileUpload.fileUp(uploadImg, filePath, name);
		}
		map.put("result", "ok");
		map.put("path", Const.FILEPATHIMG + Const.UPLOAD_ARTWORK_IMAGE_PATH
				+ ffile + "/" + fileName);
		map.put("id", name);
		return JSONUtil.returnObject(pd, map);
	}
	
	/**
	 * 选择品牌列表
	 */
	@RequestMapping(value = "/addBrand")
	public ModelAndView addBrand(Page page) {
		logBefore(logger, "列表addBrand");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData> varList = brandService.list(page); // 列出TbArtiset列表
			mv.setViewName("back/goods/brand_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX, this.getHC()); // 按钮权限
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
			titles.add("商店");	//1
			titles.add("商品名称");	//2
			titles.add("商品主类型");	//3
			titles.add("商品货号");	//4
			titles.add("商品关键字");	//5
			titles.add("商品简单描述");	//6
			titles.add("主图片地址");	//7
			titles.add("商品原始价格");	//8
			titles.add("开始销售时间");	//9
			titles.add("是否发布");	//10
			titles.add("库存总数");	//11
			titles.add("库存警告");	//12
			titles.add("品牌");	//13
			titles.add("促销价格");	//14
			titles.add("促销时间");	//15
			titles.add("限购数量");	//16
			titles.add("市场售价");	//17
			titles.add("分成金额");	//18
			titles.add("是否在店铺显示");	//19
			titles.add("推荐标签");	//20
			titles.add("商品重量");	//21
			titles.add("是否允许做为普通商品销售，否则只能赠送及配件");	//22
			titles.add("快递费");	//23
			titles.add("是否免费邮寄");	//24
			titles.add("创建时间");	//25
			titles.add("创建人");	//26
			titles.add("更新时间");	//27
			titles.add("最后更新");	//28
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
				vpd.put("var12", varOList.get(i).getString("TOTALWARN"));	//12
				vpd.put("var13", varOList.get(i).getString("BRAND"));	//13
				vpd.put("var14", varOList.get(i).getString("PROMOTIONPRICE"));	//14
				vpd.put("var15", varOList.get(i).getString("PROMOTIONTIME"));	//15
				vpd.put("var16", varOList.get(i).get("LIMITNUM").toString());	//16
				vpd.put("var17", varOList.get(i).getString("MARKETPRICE"));	//17
				vpd.put("var18", varOList.get(i).getString("FEEDBACKPRICE"));	//18
				vpd.put("var19", varOList.get(i).get("ISSHOW").toString());	//19
				vpd.put("var20", varOList.get(i).getString("LABELID"));	//20
				vpd.put("var21", varOList.get(i).getString("WEIGHT"));	//21
				vpd.put("var22", varOList.get(i).getString("ISSALE"));	//22
				vpd.put("var23", varOList.get(i).getString("EXPRESSPRICE"));	//23
				vpd.put("var24", varOList.get(i).getString("ISEXPRESS"));	//24
				vpd.put("var25", varOList.get(i).getString("CREATEDATE"));	//25
				vpd.put("var26", varOList.get(i).get("CREATEBY").toString());	//26
				vpd.put("var27", varOList.get(i).getString("LASTUPDATE"));	//27
				vpd.put("var28", varOList.get(i).getString("LASTUPDATEBY"));	//28
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










