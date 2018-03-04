package cn.com.farsight.myarrayadapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	String[] songs = {"月亮之上","小苹果","月亮之上","小苹果","月亮之上","小苹果","月亮之上","小苹果","月亮之上","小苹果"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListView listView = (ListView) findViewById(R.id.listView1);
		final List<String> list = new ArrayList<String>();
		list.add("喜剧之王");
		list.add("K歌之王");
		list.add("活着");
		list.add("铁窗泪");
		list.add("窗外");
		list.add("喜剧之王");
		list.add("K歌之王");
		list.add("活着");
		list.add("铁窗泪");
		list.add("窗外");


	/*	ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				MainActivity.this, android.R.layout.simple_list_item_1, list);*/
		
		

		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				MainActivity.this, R.layout.item, list);
		listView.setAdapter(arrayAdapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				
				//TextView  textView = (TextView) view.findViewById(view.getId());
				
				Toast.makeText(MainActivity.this, list.get(position), Toast.LENGTH_SHORT).show();
				
			//	Toast.makeText(MainActivity.this, songs[position], Toast.LENGTH_SHORT).show();

			}
		});
	}

}
