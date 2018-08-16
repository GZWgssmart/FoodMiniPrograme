package com.gzzy.foodManProgram.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.gzzy.foodManProgram.common.Namevaluepairforhttp;
import com.gzzy.foodManProgram.logs.LogManager;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;


public class HttpClientUtil {

	//post 参数 json ， Map 
	public static String post(String url,String json,Map<String,String> paramters)  {
		RequestConfig config = null;
		CloseableHttpClient client = null;
		HttpPost hp = null;
		try {
			config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
			client = HttpClients.custom().setDefaultRequestConfig(config).build();

			hp = new HttpPost(url);
			if(paramters != null && !paramters.isEmpty()){
				List<Namevaluepairforhttp> formparams = new ArrayList<Namevaluepairforhttp>();
				for(Entry<String,String> entry : paramters.entrySet()){
					Namevaluepairforhttp formparam = new Namevaluepairforhttp();
					formparam.setName(entry.getKey());
					formparam.setValue(entry.getValue());
					formparams.add(formparam);
				}
				UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
				hp.setEntity(uefEntity);
			}
			if(json != null && json.trim().length() > 0){
				List<Namevaluepairforhttp> formparams = new ArrayList<Namevaluepairforhttp>();
				Namevaluepairforhttp formparam = new Namevaluepairforhttp();
				formparam.setName("data");
				formparam.setValue(json);
				formparams.add(formparam);
				UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
				hp.setEntity(uefEntity);
			}
			HttpResponse response = client.execute(hp);
			String resultjson = HttpClientUtil.paseResponse(response);

			return resultjson;
		}catch(Exception ex) {

			StringWriter stringWriter= new StringWriter();
			PrintWriter writer= new PrintWriter(stringWriter);
			ex.printStackTrace(writer);
			StringBuffer buffer= stringWriter.getBuffer();
			LogManager.err(buffer.toString());
		} finally {
			if(hp != null){
				try {
					hp.releaseConnection();
				} catch (Exception e) {
					StringWriter stringWriter= new StringWriter();
					PrintWriter writer= new PrintWriter(stringWriter);
					e.printStackTrace(writer);
					StringBuffer buffer= stringWriter.getBuffer();
					LogManager.err(buffer.toString());
				}
			}
			if(client != null){
				try {
					client.close();
				} catch (IOException e) {
					StringWriter stringWriter= new StringWriter();
					PrintWriter writer= new PrintWriter(stringWriter);
					e.printStackTrace(writer);
					StringBuffer buffer= stringWriter.getBuffer();
					LogManager.err(buffer.toString());
				}
			}
		}
		return "";
	}

	public static String postfile(String url,byte[] filedata)  {
		RequestConfig config = null;
		CloseableHttpClient client = null;
		try {
			config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
			client = HttpClients.custom().setDefaultRequestConfig(config).build();

			HttpPost hp = new HttpPost(url);
			hp.setEntity(new ByteArrayEntity(filedata));
			HttpResponse response = client.execute(hp);
			String resultjson = HttpClientUtil.paseResponse(response);
			return resultjson;
		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if(client != null){
				try {

					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}


	public static String get(String url) {
		CloseableHttpClient client = null;
		RequestConfig config = null;
		try {
			config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
			client = HttpClients.custom().setDefaultRequestConfig(config).build();
			HttpGet hp = new HttpGet(url);
			HttpResponse response = client.execute(hp);
			String resultjson = HttpClientUtil.paseResponse(response);
			return resultjson;
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	public static String paseResponse(HttpResponse response) {
		//log.info("get response from http server..");
		HttpEntity entity = response.getEntity();

//        log.info("response status: " + response.getStatusLine());
		String charset = EntityUtils.getContentCharSet(entity);
//        log.info(charset);

		String body = null;
		try {
			body = EntityUtils.toString(entity);
//            log.info(body);
		}catch (IOException e) {
			e.printStackTrace();
		}

		return body;
	}

	public static <T> Object getRequestdate(byte[] responseBody,Class<T> clazz,Map<String,Class<T>> classMap){

		if(responseBody == null || responseBody.length == 0){
			return null;
		}

		JSONObject jo = JSONObject.fromObject(responseBody);

		Object ccb = JSONObject.toBean(jo, clazz, classMap);
		return ccb;
	}

	public static <T> Object getRequestdate(byte[] responseBody,TypeReference<T> typeref) throws Exception{

		String s3 = new String(responseBody,"utf-8");

		return getRequestdate(s3, typeref);
	}

	public static <T> List<T> getRequestdateList(String s3,
												 TypeReference<List<T>> typeref) throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		// 去掉默认的时间戳格式
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		// 设置为中国北京时区
		objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		// 空值不序列化
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		// 反序列化时，属性不存在的兼容处理
		objectMapper.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 单引号处理
		objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		return objectMapper.readValue(s3,typeref);
	}
	public static <T> T getRequestdate(String s3,
											TypeReference<T> typeref) throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		// 去掉默认的时间戳格式
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		// 设置为中国北京时区
		objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		// 空值不序列化
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		// 反序列化时，属性不存在的兼容处理
		objectMapper.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 单引号处理
		objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		return objectMapper.readValue(s3,typeref);
	}

	public static <T> List<T> getRequestdateList(byte[] responseBody,
												 TypeReference<List<T>> typeref) throws Exception{
		String s3 = "";
		if(responseBody != null){
			s3 = new String(responseBody,"utf-8");
			return getRequestdateList(s3, typeref);
		} else {
			return new ArrayList<>();
		}
	}

	public static String doPostClientforfile(InputStream in) throws Exception{

		RequestConfig config = null;
		CloseableHttpClient client = null;
		HttpPost hp = null;
		try {
			config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
			client = HttpClients.custom().setDefaultRequestConfig(config).build();

			HttpPost httpPost = new HttpPost("http://139.196.200.103:8099/a/if001");
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.addBinaryBody("file", in, ContentType.DEFAULT_BINARY, "pdffile");// 文件流
			builder.addTextBody("filename", "pdffile");// 类似浏览器表单提交，对应input的name和value
			HttpEntity entity = builder.build();
			httpPost.setEntity(entity);
			HttpResponse response = client.execute(httpPost);// 执行提交
			HttpEntity responseEntity = response.getEntity();
			String resultjson = "";
			if (responseEntity != null) {
				// 将响应内容转换为字符串
				resultjson = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
				LogManager.err("resultjson%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%:" + resultjson);
			}
			return resultjson;
		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if(hp != null){
				try {
					hp.releaseConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(client != null){
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}

	public static boolean doUploadClientforfile(byte[] file, File existfile) throws Exception{

		FileOutputStream fos = null;
		BufferedInputStream bis = null;
//		existfile.renameTo(new File(fileNewName));
		try {
			bis = new BufferedInputStream(new ByteArrayInputStream(file));
			fos = new FileOutputStream(existfile);
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage()+","+e.getStackTrace());
			return false;
		}finally{
			fos.close();
			bis.close();
		}


		System.out.println("上传图片成功======================");
		return true;
	}
}
