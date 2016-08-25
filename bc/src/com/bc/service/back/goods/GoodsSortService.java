package com.bc.service.back.goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bc.dao.DaoSupport;
import com.bc.entity.Page;
import com.bc.util.Const;
import com.bc.util.PageData;


@Service("goodssortService")
public class GoodsSortService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("GoodsSortMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("GoodsSortMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("GoodsSortMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("GoodsSortMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("GoodsSortMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("GoodsSortMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("GoodsSortMapper.deleteAll", ArrayDATA_IDS);
	}
	/**
	 * 艺术品分类树
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> getTree(PageData pd) throws Exception{
		List<PageData> reList = new ArrayList<PageData>();
		List<PageData> list = (List<PageData>) dao.findForList("GoodsSortMapper.listAll", pd);
		if (list!=null && list.size()>0) {
			for (PageData pageData : list) {
				PageData pageData2 = new PageData();
				pageData2.put("ID", (int)pageData.get("ID"));
				pageData2.put("NAME", pageData.getString("NAME"));
				pageData2.put("PARENT_ID", (int)pageData.get("PARENT_ID"));
				if ((int)pageData.get("PARENT_ID") == (int)pageData.get("PARENT_ID")) {
					PageData pageData3 = new PageData();
					pageData3.put("PARENT_ID", (int)pageData.get("ID"));
					pageData2.put("child", getTree(pageData3));
				}
				reList.add(pageData2);
			}
		}
		
		return reList;
	}
	/**
	 * 获取商品分类
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getSort() throws Exception{
		PageData pd = new PageData();
		//pd.put("ARTWORK_PARENT_ID", Const.GOODS_PARENT_ID);
		Map<String, Object> map = new HashMap<String, Object>();
		List<PageData> list = (List<PageData>) dao.findForList("ArtworkSortMapper.listAll", pd);//一级分类
		if (list!=null && list.size()>0) {
			for (PageData pageData : list) {
				pd.put("ARTWORK_PARENT_ID", pageData.getString("ARTWORK_SORT_ID"));//一级分类作为父ID
				//获取二级分类
				Map<String, Object> secondMap = new HashMap<String, Object>();
				List<PageData> secondList = (List<PageData>) dao.findForList("ArtworkSortMapper.listAll", pd);
				for (PageData pd2 : secondList) {
					pd.put("ARTWORK_PARENT_ID", pd2.getString("ARTWORK_SORT_ID"));//二级分类作为父ID
					//获取三级分类
					secondMap.put(pd2.getString("ARTWORK_SORT_NAME"), dao.findForList("ArtworkSortMapper.listAll", pd));
				}
				
				map.put(pageData.getString("ARTWORK_SORT_NAME"), secondMap);
			}
		}
		return map;
	}
	
}

