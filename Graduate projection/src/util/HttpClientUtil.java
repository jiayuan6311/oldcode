package util;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParamBean;

import config.ConnectionConfig;
import config.CrawlerConfig;



public class HttpClientUtil {
	public static DefaultHttpClient createHttpClient(){
		HttpParams params=new BasicHttpParams();
		HttpProtocolParamBean paramsBean=new HttpProtocolParamBean(params);
		paramsBean.setVersion(HttpVersion.HTTP_1_1);
		paramsBean.setContentCharset("GBK");
		params.setParameter("http.socket.timeout", 50000);
		params.setParameter("http.connection.timeout", ConnectionConfig.TIMEOUT);
		
		ConnManagerParams.setMaxTotalConnections(params, CrawlerConfig.MAX_THREAD_QUANTITY);
		
		HttpRequestRetryHandler retry=new DefaultHttpRequestRetryHandler(3, true);
		
		SchemeRegistry schemeRegistry=new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		ClientConnectionManager cm=new ThreadSafeClientConnManager(params, schemeRegistry);
		DefaultHttpClient httpClient=new DefaultHttpClient(cm, params);
		httpClient.setHttpRequestRetryHandler(retry);
		
		return httpClient;
	}
}
