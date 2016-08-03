package com.bc.controller.solr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
 
/**
 * 此类是测试SolrJ组件使用的类，包括：单条插入索引，多条插入索引，简单查询，复杂查询数据等
 * @author Administrator
 *
 */
@SuppressWarnings("deprecation")
public class SlorJTest {
	 
    private static final String DEFAULT_URL = "http://localhost:8393/solr/blog";
    private HttpSolrServer server;
 
    public void init() throws Exception{
        server = new HttpSolrServer(DEFAULT_URL);
 
        server.setMaxRetries(1); // defaults to 0. > 1 not recommended.
        server.setConnectionTimeout(5000); // 5 seconds to establish TCP
 
        // Setting the XML response parser is only required for cross
        // version compatibility and only when one side is 1.4.1 or
        // earlier and the other side is 3.1 or later.
        // server.setParser(new XMLResponseParser()); // binary parser is used
        // by default
 
        // The following settings are provided here for completeness.
        // They will not normally be required, and should only be used
        // after consulting javadocs to know whether they are truly required.
        server.setSoTimeout(1000); // socket read timeout
        server.setDefaultMaxConnectionsPerHost(100);
        server.setMaxTotalConnections(100);
        server.setFollowRedirects(false); // defaults to false
 
        // allowCompression defaults to false.
        // Server side must support gzip or deflate for this to have any effect.
        server.setAllowCompression(true);
 
        // SolrJ lets you upload content in XML and Binary format.
        // The default is set to be XML.
        // Use the following to upload using Binary format.
        // This is the same format which SolrJ uses to fetch results, and can
        // greatly improve performance as it reduces XML marshalling overhead.
        // Note -- be sure you have also enabled the
        // "BinaryUpdateRequestHandler" in your solrconfig.xml for example like:
        // <requestHandler name="/update/javabin"
        // class="solr.BinaryUpdateRequestHandler" />
        // server.setRequestWriter(new BinaryRequestWriter());
    }
 
    public void destory() {
        server = null;
        System.runFinalization();
        System.gc();
    }
 
    /**
     * 获取查询结果，直接打印输出
     * @param query
     */
    public void query(String query) throws Exception{
        SolrQuery params = new SolrQuery(query);
        params.set("rows", 5);
        try {
            QueryResponse response = server.query(params);
            SolrDocumentList list = response.getResults();
            System.out.println("总计：" + list.getNumFound() + "条，本批次:" + list.size() + "条");
            for (int i = 0; i < list.size(); i++) {
                SolrDocument doc = list.get(i);
                System.out.println(doc.get("title"));
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
    }
 
    
//    域字段必须有定义 会报异常
    public void addDoc() throws Exception{
        // 创建doc文档
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id",1234 );
//        doc.addField("objectId", 0);
        doc.addField("webTitle", "测试标题");
        doc.addField("webTime", new java.util.Date());
        doc.addField("webContent", "尚学堂是北京口碑最好的java培训机构,就业率始终位居北京java培训机构首位,并为学员提供就业后分期还等服务!提供java视频教程下载,师资:马士兵,高淇等等,是国内最好");
        try {
            // 添加一个doc文档
            UpdateResponse response = (UpdateResponse)server.add(doc);
            // commit后才保存到索引库
            server.commit();
 
            // 输出统计信息
            System.out.println("Query Time：" + response.getQTime());
            System.out.println("Elapsed Time：" + response.getElapsedTime());
            System.out.println("Status：" + response.getStatus());
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("--------------------------");
 
        query("测试");
 
        System.out.println("--------------------------");
    }
    public void testAdddoc() throws SolrServerException, IOException{
    	String url = "http://localhost:8080/solr"; 
    	HttpSolrServer server = new HttpSolrServer(url);
    	SolrInputDocument doc1 = new SolrInputDocument(); 
    	doc1.addField("id", "1");
    	doc1.addField("title", "云南xxx科技");
    	doc1.addField("cat", "企业信息门户，元数据，数字沙盘，知识管理");
    	SolrInputDocument doc2 = new SolrInputDocument(); 
    	doc2.addField("id", "2");
    	doc2.addField("title", "胡启稳");
    	doc2.addField("cat", "知识管理，企业信息门户，云南，昆明");
    	SolrInputDocument doc3 = new SolrInputDocument(); 
    	doc3.addField("id", "3");
    	doc3.addField("title", "liferay");
    	doc3.addField("test_s", "这个内容能添加进去么？这是动态字段呀");
    	List<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
    	docs.add(doc1);
    	docs.add(doc2);
    	docs.add(doc3);
    	server.add(docs);
    	server.commit();
    }
    public void addDocs() throws Exception{
//        String[] title = new String[] { "IK Analyzer介绍", "观前街介绍", "服务业情况",
//                "人大动态", "高技能" };
//        String[] content = new String[] {
//                "IK Analyzer是一个结合词典分词和文法分词的中文分词开源工具包。它使用了全新的正向迭代最细粒度切分算法。",
//                "观前街实际上就是玄妙观前面的那条街，卫道观前当然也有一个观，那就是卫道",
//                "服务业集聚区加快建设。全市完成全社会固定资产投资5265亿元，比上年增长17%",
//                "为了提高加快立法质量和实效，市人大常委会还首次开展了立法后评估工作，对《苏州市公路条例》",
//                "继续位居动态全国地级市首位。2012年新增高技能人才7.6万人，其中新培养技师、高级技师4600人" };
// 
//        Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
//        for (int i = 0; i < title.length; i++) {
//            SolrInputDocument doc = new SolrInputDocument();
//            doc.addField("id", (i + 1));
//            doc.addField("title", title[i]);
//            doc.addField("content", content[i]);
//            docs.add(doc);
//        }
// 
//        try {
//            UpdateResponse response = server.add(docs);
//            server.commit();
//        } catch (SolrServerException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
 
        query("中文分词");
 
        System.out.println("--------------------------");
    }
 
    public void querySimple() throws Exception{
        // 这是一个简单查询
 
        ModifiableSolrParams params = new ModifiableSolrParams();
        params.set("q", "企业 昆明");
        params.set("q.op", "AND");
        params.set("start", 0);
        params.set("rows", 3);
        params.set("fl", "*,score");
        try {
            QueryResponse response = server.query(params);
            SolrDocumentList list = response.getResults();
            System.out.println("总计：" + list.getNumFound() + "条，本批次:" + list.size() + "条");
            for (int i = 0; i < list.size(); i++) {
                SolrDocument doc = list.get(i);
                System.out.println(doc.get("title"));
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
 
        System.out.println("--------------------------");
    }
/**
 * http://ronxin999.blog.163.com/blog/static/42217920201292951457295/
 * http://www.cnblogs.com/llz5023/archive/2012/05/24/2516908.html
 * 
 * http://www.cnblogs.com/Laupaul/archive/2012/04/24/2467388.html
 * 
 * http://www.devnote.cn/article/42.html
 * http://blog.csdn.net/wxwzy738/article/details/17149433
 * http://blog.csdn.net/houyj1986/article/details/9275743
 * http://blog.csdn.net/thundersssss/article/details/5369654
 * http://blog.csdn.net/wxwzy738/article/details/17149433
 * http://peigang.iteye.com/blog/1460301
 * 
 * http://hbin8668.blog.163.com/blog/static/178844311201375112957139/
 */
    public void queryCase() throws Exception{
        // 这是一个稍复杂点的查询
 
        SolrQuery params = new SolrQuery("content:hadoop");
//        params.set("q.op", "OR");
        params.set("start", 0);
        params.set("rows", 10);
        params.set("fl", "*,score");
//        params.setIncludeScore(true);
//        params.set("sort", "webTime desc");
 
        params.setHighlight(true); // 开启高亮组件
        params.addHighlightField("title");// 高亮字段
        params.addHighlightField("content");// 高亮字段
//        params.
        params.set("hl.useFastVectorHighlighter", "true"); 
        params.set("hl.fragsize", "200");
        params.setHighlightSimplePre("<SPAN class=\"serachred\">");// 高亮关键字前缀；
        params.setHighlightSimplePost("</SPAN>");// 高亮关键字后缀
        params.setHighlightSnippets(3); //结果分片数，默认为1
//        params.
//        params.setf
        try {
            QueryResponse response = server.query(params);
 
//            // 输出查询结果集
//            SolrDocumentList list = response.getResults();
//            System.out.println("总计：" + list.getNumFound() + "条，本批次:" + list.size() + "条");
//            for (int i = 0; i < list.size(); i++) {
//                SolrDocument doc = list.get(i);
//                System.out.println(doc.get("title"));
//                System.out.println(doc.get("content"));
//            }
 
            // 第一种：常用遍历Map方法；
            Map<String, Map<String, List<String>>> map = response.getHighlighting();
            Iterator<String> iterator = map.keySet().iterator();
            while(iterator.hasNext()) {
                String keyname = (String) iterator.next();
                Map<String, List<String>> keyvalue = map.get(keyname);
                System.out.println("objectId：" + keyname);
 
                // 第二种：JDK1.5之后的新遍历Map方法。
                for (Map.Entry<String, List<String>> entry : keyvalue.entrySet()) {
                    String subkeyname = entry.getKey().toString();
                    List<String> subkeyvalue = entry.getValue();
 
                    System.out.print(subkeyname + "\n");
                    for(String str: subkeyvalue) {
                        System.out.print(str);
                    }
                    System.out.println();
                }                
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } 
 
        System.out.println("--------------------------");
    }
}