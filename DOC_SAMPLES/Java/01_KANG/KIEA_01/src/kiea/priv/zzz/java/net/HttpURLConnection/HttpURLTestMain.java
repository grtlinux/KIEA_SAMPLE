package kiea.priv.zzz.java.net.HttpURLConnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class HttpURLTestMain
{
	private static boolean flag = true;
	
	///////////////////////////////////////////////////////////////////////////
	
	private static Map<String, String> getQueryParser(String query) throws Exception
	{
		Map<String, String> returnData = new HashMap<String, String>();
		
		if (flag) {
			StringTokenizer st = new StringTokenizer(query, "&", false);
			
			while (st.hasMoreElements()) {
				// first pass to retrive the "parametername=value" combo
				String paramValueToken = st.nextElement().toString();
				// StringTokenizer stParamVal = new StringTokenizer(paramValueToken, "=", false);
				
				// 방식변경
				String[] strParamVal = paramValueToken.split("=", 2);
				String paramName = strParamVal[0];
				String paramValue = strParamVal[1];
				
				returnData.put(paramName, paramValue);
			}
		}
		
		return returnData;
	}
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			
			HttpURLConnection connection = null;
			
			try {
				URL url = new URL("http://javaking75.blog.me/rss?q=test&id=");
				if (flag) System.out.printf("URL : %s\n", url.toExternalForm());
				
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setReadTimeout(3000);
				
				if (flag) {
					System.out.printf("getContentEncoding() : %s\n", connection.getContentEncoding());
					System.out.printf("getContentType()     : %s\n", connection.getContentType());
					System.out.printf("getContentEncoding() : %s\n", connection.getResponseCode());
					System.out.printf("getResponseCode()    : %s\n", connection.getResponseMessage());
					System.out.printf("getRequestMethod()   : %s\n", connection.getRequestMethod());
					System.out.printf("getURL()             : %s\n", connection.getURL());
					String queryString = connection.getURL().getQuery();
					System.out.printf("getURL().getQuery()  : %s\n", queryString);
					
					Map<String, String> queryMap = getQueryParser(queryString);
					System.out.println(queryMap);
					System.out.printf("q 파라미터의 값 : %s\n", queryMap.get("q"));
				}
				
				if (flag) System.out.println("==============================================================================");
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
				StringBuffer buffer = new StringBuffer();
				String line = null;
				while ((line = reader.readLine()) != null) {
					buffer.append(line).append("\r\n");
				}
				reader.close();
				
				System.out.println(buffer.toString());
				
			} catch (Exception e) {
				// e.printStackTrace();
				throw e;
			} finally {
				if (connection != null) {
					connection.disconnect();
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		if (flag) test01(args);
	}
}
