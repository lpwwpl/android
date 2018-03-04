package cn.com.farsight.mysimpleadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	int[] images = { R.drawable.p0015, R.drawable.p0018, R.drawable.p0019,
			R.drawable.p0022 };
	String[] names = { "马化腾", "李白", "奥巴马", "习近平" };
	String[] phones = { "110", "112", "114", "119" };
	String[] address = {"北京","唐朝","美国","中南海"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ListView listView = (ListView) findViewById(R.id.listView1);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		
		
		for (int i = 0; i < images.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("image", images[i]);
			map.put("name", names[i]);
			map.put("phone", phones[i]);
			map.put("address", address[i]);

			list.add(map);
		}

		SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.this,
				list, R.layout.contact,
				new String[] { "image", "name", "phone","address" }, new int[] {
						R.id.imageview, R.id.nameview, R.id.phoneview ,R.id.addressview});
		
		listView.setAdapter(simpleAdapter);
		
	}

}
