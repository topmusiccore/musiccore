package org.lan.web.model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.lan.web.util.WebEncodingUtil;

public class Page {
	private String urlStr;
	private URL url;
	private String html;
	
	public Page(String urlString) throws MalformedURLException{
		this.urlStr=urlString;
		this.url = new URL(urlString); 
	}
	public Page(URL url){
		this.url=url;
	}
	public Page(){		
	}
    

	public String getUrlStr() {
		return urlStr;
	}
	public void setUrlStr(String urlStr) throws MalformedURLException {
		this.urlStr = urlStr;
		this.url=new URL(this.urlStr);
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public void setUrl(URL url) {
		this.url = url;
		this.urlStr=url.toString();
	}
	
	public URL getUrl(){
		return this.url;
	}
	

    
    public static void main(String[] args) throws IOException {

        URL url = new URL("http://www.baidu.cn/"); 
    	Page page = new Page(url);
        System.out.println("content = \n"+page.getContenString()); 
        

	}
    public String getContenString() throws IOException{
		HttpURLConnection conn  =  (HttpURLConnection) this.url.openConnection();


		conn.setRequestMethod("GET"); 
		conn.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)");

		String str=conn.getContentType();
		System.out.println(str);
		
		String code = new Integer(conn.getResponseCode()).toString();
        String message = conn.getResponseMessage();
        System.out.println("getResponseCode code ="+ code);
        System.out.println("getResponseMessage message ="+ message);
		// 读取源码
		//读取中文时，使用Reader类是每次读出两个字节的，不会出现中文乱码
		String chartset = WebEncodingUtil.getCharset(this.url);
		InputStreamReader in = new InputStreamReader(this.url.openStream(), chartset);
		char[] buf = new char[2048];//缓存
		StringBuffer sb = new StringBuffer();
		int len = 0;
		while ((len = in.read(buf)) != -1) {//当没到文档尽头继续读取
			sb.append(buf, 0, len);
		}
    	return sb.toString();
    }
    
}
