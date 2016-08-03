package com.bc.service.solr;
import java.io.IOException;
 
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.BinaryRequestWriter;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.util.SimpleOrderedMap;
 

//参数　　　　说明    　　　　　　　　　　　　　　　　　　　　　　　　　　值域 
//mlt　　　　在查询时，打开/关闭 MoreLikeThisComponent 的布尔值。 　　真|假 
//mlt.count  可选。每一个结果要检索的相似文档数。 　　　　　　　　　　　　　> 0 
//mlt.fl　　　用于创建 MLT 查询的字段。 　　　　　　　　　　　　　　　　　模式中任何被储存的或含有检索词向量的字段。 
//mlt.maxqt 可选。查询词语的最大数量。由于长文档可能会有很多关键词语，这样 MLT 查询可能会很大，从而导致反应缓慢或可怕的 TooManyClausesException，该参数只保留最关键的词语。 > 0 
public class QueryDocsDemoMoreLikeThis {
//  public static final String SOLR_URL = "http://192.168.230.128:8983/solr";
    public static final String SOLR_URL = "http://localhost:8393/solr/blog";
 
    public static void main(String[] args) throws SolrServerException, IOException {
        HttpSolrServer server = new HttpSolrServer(SOLR_URL);
//        server.setMaxRetries(1);
//        server.setMaxRetries(1); // defaults to 0. > 1 not recommended.
        server.setConnectionTimeout(5000); // 5 seconds to establish TCP
        //正常情况下，以下参数无须设置
        //使用老版本solrj操作新版本的solr时，因为两个版本的javabin incompatible,所以需要设置Parser
        server.setParser(new XMLResponseParser());
        server.setSoTimeout(1000); // socket read timeout
        server.setDefaultMaxConnectionsPerHost(100);
        server.setMaxTotalConnections(100);
        server.setFollowRedirects(false); // defaults to false
        // allowCompression defaults to false.
        // Server side must support gzip or deflate for this to have any effect.
        server.setAllowCompression(true);
 

        server.setRequestWriter(new BinaryRequestWriter());
        SolrQuery query = new SolrQuery();
      query.setQuery("id:" + "487")
//        query.setQuery("title:"+"hbase")
      .setParam("fl", "id","content","title","createDate","userid","score")
      .setParam("mlt", "true")
      .setParam("mlt.fl", "title,content")
//      .setParam("mlt.fl", "title")
      .setParam("mlt.mintf", "20")
      .setParam("mlt.mindf", "1")
      .setParam("mlt.maxqt", "20")
//      .setParam("mlt.count", String.valueOf(20));
      .setRows(20);
 
        QueryResponse response = server.query( query );
         
         
//        // 搜索得到的结果数
//        System.out.println("Find:" + response.getResults().getNumFound());
//        // 输出结果
//        int iRow = 1;
//        for (SolrDocument doc : response.getResults()) {
//            System.out.println("----------" + iRow + "------------");
//            System.out.println("id: " + doc.getFieldValue("id").toString());
//            Object bc2 = doc.getFieldValue("apicontent");
//            System.out.println("name: " +bc2 );
//            System.out.println("price: "
//                    + doc.getFieldValue("price").toString());
//            System.out.println("score: " + doc.getFieldValue("score"));
//            iRow++;
//        }
//      
      
      
      
      SimpleOrderedMap<SolrDocumentList> mltResults = (SimpleOrderedMap<SolrDocumentList>) response.getResponse().get("moreLikeThis");
      for (int i = 0; i < mltResults.size(); i++) {
          SolrDocumentList items = mltResults.getVal(i);
          for (SolrDocument doc : items) {
        	  System.out.println("id: " + doc.getFieldValue("id").toString());
        	  
              System.out.println("dataTitle: "
                      + doc.getFieldValue("title").toString());
//              System.out.println("dataContent: " + doc.getFieldValue("dataContent"));
              System.out.println("createUid: " + doc.getFieldValue("userid"));
              System.out.println("createDate: " + doc.getFieldValue("createDate"));
              System.out.println("score: " + doc.getFieldValue("score"));
          }
      }
    }
}