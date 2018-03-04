package cn.com.farsight.network.form;

import android.app.Activity;
import android.os.Bundle;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
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
	// ���͵Ĳ���
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
		//��Post��ʽ�ύ����
		tv.setText(doPostSubmit(url,params));
	}

	public String doPostSubmit(String url, List<NameValuePair> params) {
		/* ����HTTPPost���� */
		HttpPost httpRequest = new HttpPost(url);

		String strResult = "doPostError";

		try {
			/* �������������������� */
			httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			/* �������󲢵ȴ���Ӧ */
			HttpResponse httpResponse = httpClient.execute(httpRequest);
			/* ��״̬��Ϊ200 ok */
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				/* ����Ӧ���� */
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

		try {
			strResult = new String(strResult.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.v("strResult", strResult);

		return strResult;
	}

	public HttpClient getHttpClient() {

		// ���� HttpParams ���������� HTTP ��������һ���ֲ��Ǳ���ģ�

		this.httpParams = new BasicHttpParams();

		// �������ӳ�ʱ�� Socket ��ʱ���Լ� Socket �����С

		HttpConnectionParams.setConnectionTimeout(httpParams, 20 * 1000);

		HttpConnectionParams.setSoTimeout(httpParams, 20 * 1000);

		HttpConnectionParams.setSocketBufferSize(httpParams, 8192);

		// �����ض���ȱʡΪ true
		HttpClientParams.setRedirecting(httpParams, true);

		// ���� user agent
		String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2) Gecko/20100115 Firefox/3.6";
		HttpProtocolParams.setUserAgent(httpParams, userAgent);

		// ����һ�� HttpClient ʵ��
		httpClient = new DefaultHttpClient(httpParams);

		return httpClient;
	}
}