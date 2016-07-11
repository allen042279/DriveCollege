package com.softcreate.pojo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.softcreate.model.AccessToken;
import com.softcreate.model.JsApiTicket;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * 公众平台通用接口工具类
 * 
 */
@Component
public class WeixinUtil {
	
	
	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);

	// 获取access_token的接口地址（GET） 限200（次/天）
	public final static String sub_access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public final static String corp_access_token_url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=CORPID&corpsecret=CORPSECRET";
	
	public final static String access_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	public final static String fetch_news_by_id = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
	public final static String fetch_news_list = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
	
	/**
	 * 获取access_token
	 * 
	 * @param appid 凭证
	 * @param appsecret 密钥
	 * @return
	 */
	public static AccessToken getSubAccessToken(String appid, String appsecret) {
		
		AccessToken accessToken = null;

		String requestUrl = sub_access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setAppId(appid);
				accessToken.setAccessToken(jsonObject.getString("access_token"));
				accessToken.setAppSecret(appsecret);
				accessToken.setExpire(jsonObject.getString("expires_in"));
				accessToken.setAddTimestamp(new Date());
			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return accessToken;
	}
	
	/**
	 * 获取access_token
	 * 
	 * @param appid 凭证
	 * @param appsecret 密钥
	 * @return
	 */
	public static AccessToken getCorpAccessToken(String corpid, String corpsecret) {
		
		AccessToken accessToken = null;

		String requestUrl = corp_access_token_url.replace("CORPID", corpid).replace("CORPSECRET", corpsecret);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setAppId(corpid);
				accessToken.setAccessToken(jsonObject.getString("access_token"));
				accessToken.setAppSecret(corpsecret);
				accessToken.setExpire(jsonObject.getString("expires_in"));
				accessToken.setAddTimestamp(new Date());
			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return accessToken;
	}
	
	// 菜单创建（POST） 限100（次/天）
	public static String create_sub_menu_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	/**
	 * 创建订阅号菜单
	 * 
	 * @param menu 菜单实例
	 * @param accessToken 有效的access token
	 * @return 0表示成功，其他值表示失败
	 */
	public static int createSubMenu(Menu menu, AccessToken accessToken) {
		
		int result = 0;

		// 拼装创建菜单的url
		String url = create_sub_menu_url.replace("ACCESS_TOKEN", accessToken.getAccessToken());
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);

		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}

		return result;
	}
	
	// 菜单创建（POST） 限100（次/天）
	public static String create_corp_menu_url = "https://qyapi.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN&agentid=CORPID";	
	/**
	 * 创建企业号菜单
	 * 
	 * @param menu 菜单实例
	 * @param accessToken 有效的access token
	 * @return 0表示成功，其他值表示失败
	 */
	public static int createCorpMenu(Menu menu, AccessToken accessToken) {
		int result = 0;

		// 拼装创建菜单的url
		String url = create_corp_menu_url.replace("ACCESS_TOKEN", accessToken.getAccessToken()).replace("CORPID", accessToken.getAppId());
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);

		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}

		return result;
	}
	
	public static String delete_corp_menu_url = "https://qyapi.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN&agentid=CORPID";	
	/**
	 * 删除企业号菜单
	 * 
	 * @param accessToken 有效的access token
	 * @return 0表示成功，其他值表示失败
	 */
	public static int deleteCorpMenu(AccessToken accessToken) {
		int result = 0;

		// 拼装创建菜单的url
		String url = delete_corp_menu_url.replace("ACCESS_TOKEN", accessToken.getAccessToken()).replace("CORPID", accessToken.getAppId());
		// 将菜单对象转换成json字符串
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "GET", null);

		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}

		return result;
	}
	
	public static String get_corp_menu_url = "https://qyapi.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN&agentid=CORPID";	
	/**
	 * 获取企业号菜单
	 * 
	 * @param accessToken 有效的access token
	 * @return 返回结果与菜单创建的参数一致
	 */
	public static int getCorpMenu(AccessToken accessToken) {
		
		int result = 0;

		// 拼装创建菜单的url
		String url = get_corp_menu_url.replace("ACCESS_TOKEN", accessToken.getAccessToken()).replace("CORPID", accessToken.getAppId());
		// 将菜单对象转换成json字符串
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "GET", null);

		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}

		return result;
	}	
	
//--------------------- just test  begin ---------------------------------------------------------------------------------------------------------------------------------	
	/**
	 * 获取指定的图文消息
	 * 
	 * @param number 图文消息编号
	 * @param access_token 凭证
	 * @return
	 */
	public static String getNewsById(int number, String access_token) {
		
		JsApiTicket jsApiTicket = null;

//		String requestUrl = fetch_news_list.replace("ACCESS_TOKEN", access_token);
//		JSONObject jsonObject = httpRequest(requestUrl, "POST", "{\"type\":\"news\", \"offset\":0, \"count\":20	}");
			
		String requestUrl = fetch_news_by_id.replace("ACCESS_TOKEN", access_token);
		JSONObject jsonObject = httpRequest(requestUrl, "POST", "{\"media_id\":\"QYmzYCZsVepRHMCRLcR5J40fYHYz7DYQqtdElHyntXU\"}");
		// 如果请求成功
		if (null != jsonObject) {
			try {
				requestUrl = jsonObject.getString("news_item/content");
				jsApiTicket = new JsApiTicket();
				jsApiTicket.setJsapiTicket(jsonObject.getString("content"));
				jsApiTicket.setExpire(jsonObject.getString("expires_in"));
				jsApiTicket.setAddTimestamp(new Date());
			} catch (JSONException e) {
				jsApiTicket = null;
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return requestUrl;
	}
	
	/**
	 * 获取access_token
	 * 
	 * @param appid 凭证
	 * @param appsecret 密钥
	 * @return
	 */
	public static JsApiTicket getJSApiTicket(String access_token) {
		
		JsApiTicket jsApiTicket = null;

		String requestUrl = access_ticket_url.replace("ACCESS_TOKEN", access_token);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				jsApiTicket = new JsApiTicket();
				jsApiTicket.setJsapiTicket(jsonObject.getString("ticket"));
				jsApiTicket.setExpire(jsonObject.getString("expires_in"));
				jsApiTicket.setAddTimestamp(new Date());
			} catch (JSONException e) {
				jsApiTicket = null;
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return jsApiTicket;
	}
	
	/**
	 * JS-SDK使用权限签名算法
	 * 
	 * @param jsapi_ticket 公众号用于调用微信JS接口的临时票据
	 * @param url 当前网页的URL，不包含#及其后面部分
	 * @return
	 */
	public static Map<String, String> sign(String jsapi_ticket, String url) {
        
    	Map<String, String> ret = new HashMap<String, String>();
    	
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        System.out.println(string1);

        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

//--------------------- just test  end ---------------------------------------------------------------------------------------------------------------------------------
	
    private static String byteToHex(final byte[] hash) {
    	
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
	
	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return jsonObject;
	}
}