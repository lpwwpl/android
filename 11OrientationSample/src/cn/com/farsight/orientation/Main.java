package cn.com.farsight.orientation;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Main extends Activity implements SensorEventListener {
	private static final String TAG = "SensorDemo";
	private SensorManager sensorManager;
	private TextView outView;
	private Sensor sensor;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		outView = (TextView) findViewById(R.id.output);

		// Real sensor manager
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
	}

	/** Register for the updates when Activity is in foreground */
	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");
		sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
	}

	/** Stop the updates when Activity is paused */
	@Override
	protected void onPause() {
		super.onPause();
		Log.d(TAG, "onPause");
		sensorManager.unregisterListener(this);
	}



	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float azimuth = Math.round(event.values[0]);
		float pitch = Math.round(event.values[1]);
		float roll = Math.round(event.values[2]);

		String out = String.format("Azimuth: %.2f\n\nPitch:%.2f\nRoll",
				azimuth, pitch, roll);
		Log.d(TAG, out);
		outView.setText(out);

	}
}
