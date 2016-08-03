package com.bc.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;

public class JSONUtil  {
	
	protected static Logger logger = Logger.getLogger(JSONUtil.class);
	
	public static void main(String[] args){
		
		
		PageData pd = new PageData();
		pd.put("username", "zhangsan");
		
		try {
			checkParam("APP_ARTWORK_DELETE", pd);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//检查参数是否完整
	@SuppressWarnings("rawtypes")
	public static boolean checkParam(String method, Map pd) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
		boolean result = false;
		
		int falseCount = 0;
		String[] paramArray = new String[20];
		String[] valueArray = new String[20];
		String[] tempArray  = new String[20];  //临时数组
		
		paramArray = (String[]) Const.class.getDeclaredField(method+"_PARAM").get(Const.class);
		valueArray = (String[]) Const.class.getDeclaredField(method+"_VALUE").get(Const.class);
		
		int size = paramArray.length;
		for(int i=0;i<size;i++){
			String param = paramArray[i];
			if(!pd.containsKey(param)){
				tempArray[falseCount] = valueArray[i]+"--"+param;
				falseCount += 1;
			}
		}
		
		if(falseCount>0){
			logger.error(method+"接口，请求协议中缺少 "+falseCount+"个 参数");
			for(int j=1;j<=falseCount;j++){
				logger.error("   第"+j+"个："+ tempArray[j-1]);
			}
		} else {
			result = true;
		}
		
		return result;
	}
	
	/**
	 * 设置分页的参数
	 * @param pd
	 * @return
	 */
	public static PageData setPageParam(PageData pd){
		String page_now_str = pd.get("page_now").toString();
		int pageNowInt = Integer.parseInt(page_now_str)-1;
		String page_size_str = pd.get("page_size").toString(); //每页显示记录数
		int pageSizeInt = Integer.parseInt(page_size_str);
		
		String page_now = pageNowInt+"";
		String page_start = (pageNowInt*pageSizeInt)+"";
		
		pd.put("page_now", page_now);
		pd.put("page_start", page_start);
		
		return pd;
	}
	
	/**
	 * 设置list中的distance
	 */
	public static List<PageData>  setListDistance(List<PageData> list, PageData pd){
		List<PageData> listReturn = new ArrayList<PageData>();
		String user_longitude = "";
		String user_latitude = "";
		
		try{
			user_longitude = pd.get("user_longitude").toString(); //"117.11811";
			user_latitude  = pd.get("user_latitude").toString();  //"36.68484";
		} catch(Exception e){
			logger.error("缺失参数--user_longitude和user_longitude");
			logger.error("lost param：user_longitude and user_longitude");
		}
		
		PageData pdTemp = new PageData();
		int size = list.size();
		for(int i=0;i<size;i++){
			pdTemp = list.get(i);
			String longitude = pdTemp.get("longitude").toString();
			String latitude = pdTemp.get("latitude").toString();
			String distance = MapDistance.getDistance(
						user_longitude,	user_latitude,
						longitude,		latitude
					);
			pdTemp.put("distance", distance);
			pdTemp.put("size", distance.length());
			listReturn.add(pdTemp);
		}
		
		return listReturn;
	}
	
	@SuppressWarnings("rawtypes")
	public static Object returnObject(PageData pd, Map map){
		if(pd.containsKey("callback")){
			String callback = pd.get("callback").toString();
			return new JSONPObject(callback, map);
		}else{
			return map;
		}
	}
	
	public static <T> T readValue(String content, Class<T> valueType) throws Exception{
		ObjectMapper om = new ObjectMapper();
		return om.readValue(content, valueType);
	}
	
	public static PageData getPagingParameter(PageData pd) {
		int OFFSET = (Integer.parseInt(pd.getString("CURRENT_PAGE")) - 1)
				* Integer.parseInt(pd.getString("SHOW_COUNT"));//每页的开始位置
		
		pd.put("OFFSET", OFFSET);
		pd.put("SHOW_COUNT", Integer.parseInt(pd.getString("SHOW_COUNT")));
		return pd;
	}
}
