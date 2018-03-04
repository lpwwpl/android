package cn.com.farsight.mysensorlist;

import java.util.ArrayList;
import java.util.List;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListView listView = (ListView) findViewById(R.id.listView1);

		SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		List<Sensor> list = sensorManager.getSensorList(Sensor.TYPE_ALL);

		List<String> arrayList = new ArrayList<String>();

		//ÐÂÐÍforÑ­»·
		for (Sensor sensor : list) {
			arrayList.add(sensor.getName());
		}

		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				MainActivity.this, android.R.layout.simple_list_item_1,
				arrayList);
		
		listView.setAdapter(arrayAdapter);
	}

}
