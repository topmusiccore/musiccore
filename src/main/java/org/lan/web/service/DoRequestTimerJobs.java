package org.lan.web.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import java.util.Timer;

public class DoRequestTimerJobs {
	static String pathfile="";

	public static void main(String[] args) {
		start();
	}
	public static void start() {
		Properties prop = readProperties("config.properties");
		pathfile=prop.getProperty("pathfile");
		long timeinterval = Long.parseLong(prop.getProperty("timeinterval"));
		Timer timer = new Timer();
		timer.schedule(new MyTask(), 1000, timeinterval);// 在1秒后执行此任务,每次间隔2秒,如果传递一个Data参数,就可以在某个固定的时间执行这个任务.
	}

	static class MyTask extends java.util.TimerTask {
		@Override
		public void run() {
			doTask();
		}
	}

	static void doTask(){
		Properties prop = readProperties(pathfile);
		String temppaths =prop.getProperty("path");
		String[] paths = temppaths.split(" ");
		for(String temppath:paths){
			System.out.println("*****************************************");
			System.out.println(temppath);
			doRequest(temppath);
		}
	}
	static void doRequest(String urlpath){
		URL url;			 
		try {
			url = new URL(urlpath);
			URLConnection conn = url.openConnection();	 
			BufferedReader br = new BufferedReader(
                               new InputStreamReader(conn.getInputStream()));
			String il;
			while((il=br.readLine())!=null){
				System.out.println(il);
			}
			br.close(); 
			System.out.println("Done");	 
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	static Properties readProperties(String urlpath){
		Properties prop = new Properties();
		InputStream is = DoRequestTimerJobs.class.getClassLoader().getResourceAsStream(urlpath);
		try {
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}