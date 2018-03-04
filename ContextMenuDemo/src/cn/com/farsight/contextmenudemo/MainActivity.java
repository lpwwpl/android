package cn.com.farsight.contextmenudemo;

import com.example.contextmenudemo.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	private EditText etname;
	private EditText etpass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etname = (EditText) this.findViewById(R.id.etname);
		etpass = (EditText) this.findViewById(R.id.etpass);
		
		registerForContextMenu(etname);
		registerForContextMenu(etpass);	
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.clear();
		if(v==etname)
		{
			menu.setHeaderTitle("请输入你的性别");
			menu.setHeaderIcon(android.R.drawable.ic_media_play);
			menu.add(0, 0, 0, "男");
			menu.add(0,1,1,"女");
		}else if(v ==etpass)
		{
			menu.setHeaderTitle("请输入你的爱好");
			menu.setHeaderIcon(android.R.drawable.ic_delete);
			menu.add(1, 2, 2, "dota");
			menu.add(1, 3, 3,"追女生");
			menu.add(1, 4, 4,"追男生");
			menu.add(1, 5, 5,"被女生追");
		}
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		EditText et = null;
		int groupid = item.getGroupId();
		
		if(groupid == 0){
			et = etname;
		}else if(groupid ==1){
			et = etpass;
		}	
		et.setText(item.getTitle());
		return super.onContextItemSelected(item);
	}
}
