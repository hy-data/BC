package com.bc.controller.solr;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SolrPingResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.MapSolrParams;
import org.apache.solr.common.params.SolrParams;
 
/**
 * 此类是测试SolrJ组件使用的类，包括：单条插入索引，多条插入索引，简单查询，复杂查询数据等
 * @author Administrator
 *
 */
public class SlorJTest {
	 
	/** 
     * 创建solrClient （4.x的版本使用类是SolrServer,在新版本中已经被弃用了） 
     */  
	public static void main(String[] args) throws Exception {
		   SolrClient solr = new HttpSolrClient("http://200.0.0.210:8983/solr/goods_search");  
	       Map<String, String> map = new HashMap<String, String>();  
	       map.put("q", "title:路口");  
	       SolrParams params = new MapSolrParams(map);  
	       QueryResponse resp = solr.query(params);  
	       //以下是第二种方法  
	       //String queryString="content:test";  
	       //MultiMapSolrParams mParams = SolrRequestParsers.parseQueryString("queryString");  
	       //QueryResponse resp = solr.query(mParams);  
	       SolrDocumentList docsList = resp.getResults();  
	       for (SolrDocument doc : docsList) {  
	            System.out.println(doc.get("id"));  
	       }  
	       solr.close();  
	}
	
    public void createSolrClient(){  
        try {  
            SolrClient solr = new HttpSolrClient("http://200.0.0.210:8080/solr/hy_bc");  
            SolrPingResponse  response = solr.ping();  
            //打印执行时间  
            System.out.println(response.getElapsedTime());  
            solr.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
     
    /** 
     * 增加bean索引,该bean中的字段需要匹配schema中的fields,(可以用@Field注解来关联相关字段) 
     * 否者抛出org.apache.solr.client.solrj.beans.BindingException: class: class com.ccy.solr.Blog does not define any fields. 
     * @throws Exception  
     */  
  /* public void addBeanIndex() throws Exception{  
       SolrClient solr = new HttpSolrClient("http://localhost:8080/solr/db");  
       Blog blog = new Blog();  
       blog.setId(123);  
       blog.setTitle("test");  
       blog.setContent("test...");  
       blog.setKeyWord("test");  
       UpdateResponse response = solr.addBean(blog);  
       System.out.println(response.getElapsedTime());  
       solr.commit();  
       solr.close();  
   }  */
     
     
   /** 
    * 增加索引 
    *  
    * @throws Exception 
    */  
     
   public void addIndex() throws Exception{  
       SolrClient solr = new HttpSolrClient("http://localhost:8080/solr/solr");  
       SolrInputDocument document = new SolrInputDocument();  
       document.addField("id",123, new Float(1.0));  
       document.addField("content", "test");  
       UpdateResponse response = solr.add(document);  
       System.out.println(response.getElapsedTime());  
       solr.commit();  
       solr.close();  
   }  
      
     
     
   /** 
    * 删除索引 
    */  
     
   public void delIndex() throws Exception{  
       SolrClient solr = new HttpSolrClient("http://localhost:8080/solr/solr");  
       UpdateResponse response = solr.deleteById("123");  
       System.out.println(response.getElapsedTime());  
       solr.commit();  
       solr.close();  
   }  
     
   /** 
    * 简单查询 
    * @throws IOException  
    * @throws SolrServerException  
    */  
     
   public void query() throws Exception{  
       SolrClient solr = new HttpSolrClient("http://localhost:8080/solr/solr");  
       Map<String, String> map = new HashMap<String, String>();  
       map.put("q", "content:test");  
       SolrParams params = new MapSolrParams(map);  
       QueryResponse resp = solr.query(params);  
       //以下是第二种方法  
       //String queryString="content:test";  
       //MultiMapSolrParams mParams = SolrRequestParsers.parseQueryString("queryString");  
       //QueryResponse resp = solr.query(mParams);  
       SolrDocumentList docsList = resp.getResults();  
       System.out.println(docsList.size());  
       for (SolrDocument doc : docsList) {  
            System.out.println(doc.get("id"));  
       }  
       solr.close();  
   }
}