package cn.com.farsight.network.form;

import android.app.Activity;
import android.os.Bundle;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main extends Activity implements View.OnClickListener {
	private EditText et1, et2;
	private Button btn;
	private HttpParams httpParams;
	// 发送的参数
	List<NameValuePair> params;
	private HttpClient httpClient;
	private TextView tv;
	private final String url = "http://10.0.2.2:8080/MyWeb/UserCheck";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		et1 = (EditText) findViewById(R.id.editText1);
		et2 = (EditText) findViewById(R.id.editText2);
		btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(this);
		tv = (TextView) findViewById(R.id.textView1);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", et1.getText().toString()));
		params.add(new BasicNameValuePair("passwd",et2.getText().toString()));
		httpClient = getHttpClient();
		// 以Get方式提交请求
		tv.setText(doGetSubmit(url, params));
	}

	public String doGetSubmit(String url, List<NameValuePair> params) {
		

		String strResult = "doPostError";
		String queryString = "";
		NameValuePair nvp = null;

		try {
			/* 添加请求参数到请求对象 */
			for (int i = 0; i < params.size(); i++) {
				//拼接请求字符串，即name1=value1&name2=value2格式
				nvp = params.get(i);
				queryString+=nvp.getName()+"="+nvp.getValue()+"&";
			}
			//和原来的url拼接，成http://host/login.jsp?name1=value1&name2=value2的格式
			if(queryString!=""){
				url += "?"+queryString.substring(0, queryString.length()-1);
			}
			Log.e("",url);
			/* 建立HTTPGet对象 */
			HttpGet httpRequest = new HttpGet(url);
			
			/* 发送请求并等待响应 */
			HttpResponse httpResponse = httpClient.execute(httpRequest);
			/* 若状态码为200 ok */
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				/* 读响应数据 */
				strResult = EntityUtils.toString(httpResponse.getEntity());

			} else {
				strResult = "Error Response: "
						+ httpResponse.getStatusLine().toString();
			}
		} catch (ClientProtocolException e) {
			strResult = e.getMessage().toString();
			e.printStackTrace();
		} catch (IOException e) {
			strResult = e.getMessage().toString();
			e.printStackTrace();
		} catch (Exception e) {
			strResult = e.getMessage().toString();
			e.printStackTrace();
		}

		Log.v("strResult", strResult);
		try {
			strResult = new String(strResult.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strResult;
	}

	public HttpClient getHttpClient() {

		// 创建 HttpParams 以用来设置 HTTP 参数（这一部分不是必需的）

		this.httpParams = new BasicHttpParams();

		// 设置连接超时和 Socket 超时，以及 Socket 缓存大小

		HttpConnectionParams.setConnectionTimeout(httpParams, 20 * 1000);

		HttpConnectionParams.setSoTimeout(httpParams, 20 * 1000);

		HttpConnectionParams.setSocketBufferSize(httpParams, 8192);

		// 设置重定向，缺省为 true

		HttpClientParams.setRedirecting(httpParams, true);

		// 设置 user agent
		String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2) Gecko/20100115 Firefox/3.6";
		HttpProtocolParams.setUserAgent(httpParams, userAgent);

		// 创建一个 HttpClient 实例
		httpClient = new DefaultHttpClient(httpParams);

		return httpClient;
	}
}