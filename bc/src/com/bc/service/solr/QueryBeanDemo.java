package com.bc.service.solr;
import java.io.IOException;
import java.util.List;
 

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
//http://localhost:8080/solr/core1/select?shards=localhost:8080/solr/core1,localhost:8080/solr/core2&q=some query

import com.bc.entity.solr.NewsBean;



public class QueryBeanDemo {
    public static final String SOLR_URL = "http://192.168.17.7:8080/solr";
//    提交好数据后，就可以查一下。
//    http://localhost:8080/solr-cores/core0/select/?q=*%3A*&version=2.2&start=0&rows=10&indent=on
//    http://localhost:8080/solr-cores/core1/select/?q=*%3A*&version=2.2&start=0&rows=10&indent=on 
    public static void main(String[] args) throws SolrServerException,
            IOException {
        // http://172.168.63.233:8983/solr/collection1/select?q=description%3A%E6%80%BB%E7%9B%AE%E6%A0%87&facet=true&facet.field=author_s
        HttpSolrServer server = new HttpSolrServer(SOLR_URL);
//        server.
//        CloudSolrServer cloudSolrServer = new CloudSolrServer 
        server.setMaxRetries(1); // defaults to 0. > 1 not recommended.
        server.setConnectionTimeout(5000); // 5 seconds to establish TCP
        // server.setRequestWriter(new BinaryRequestWriter());
        
        
        SolrQuery query = new SolrQuery();
// solrQuery.set("shards", "localhost:8080/solr-cores/core0,localhost:8080/solr-cores/core1")
//        query.setQuery("description:中央");
        query.setQuery("author:张三 description:中央");
        query.setStart(0);
        query.setRows(2);
        
 
        
        
        
//        query.set
//        query.addFacetField("author_s");
 
        QueryResponse response = server.query(query);
        // 搜索得到的结果数
        System.out.println("Find:" + response.getResults().getNumFound());
        // 输出结果
        int iRow = 1;
//         Distribute
        //response.getBeans存在BUG,将DocumentObjectBinder引用的Field应该为 org.apache.solr.client.solrj.beans.Field
        SolrDocumentList list = response.getResults();
        DocumentObjectBinder binder = new DocumentObjectBinder();
        List<NewsBean> beanList=binder.getBeans(NewsBean.class, list);
        for(NewsBean news:beanList){
            System.out.println(news.getId());
            System.out.println(news.getDescription());
            System.out.println(news.getAuthor());
            System.out.println("-------------------");
        }
 
//        for (SolrDocument doc : response.getResults()) {
//            System.out.println("----------" + iRow + "------------");
//            System.out.println("id: " + doc.getFieldValue("id").toString());
//            System.out.println("name: " + doc.getFieldValue("name").toString());
//            iRow++;
//        }
       
    }
}