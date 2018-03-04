package cn.com.farsight.sqlite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class Main extends Activity {
	private final String DATABASE_NAME = "school";
	private SQLiteDatabase db;
	private ListView lv;
	private ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
	private HashMap<String, Object> mymap;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.main);
		lv = new ListView(this);

		MyDatabaseHelper myDBHelper = new MyDatabaseHelper(this, DATABASE_NAME,
				null, 3);
		db = myDBHelper.getWritableDatabase();
		// 初始化数据
		initData("tom", "09393", "1106");
		// initData("jerry", "843293", "1107");
		// initData("harry", "232342", "1108");		

		// 获取STUDENTINFO数据表中的数据
		String sql = "SELECT * FROM STUDENTINFO;";

		Cursor result = db.rawQuery(sql, null);
		
		
		 data.clear();
		//将数据放到Adapter中
		while (!result.isLast()) {
			result.moveToNext();
			mymap = new HashMap<String, Object>();
			for (int i = 0; i < result.getColumnCount(); i++) {
				mymap.put(result.getColumnName(i), result.getString(i));
			}
			data.add(mymap);
		}
		SimpleAdapter adapter = new SimpleAdapter(this, data,
				R.layout.listcontent, new String[] { "ID", "NAME", "PHONE",
						"CLASS" }, new int[] { R.id.textView1, R.id.textView2,
						R.id.textView3, R.id.textView4 });
	
		/*另一种采用SimpleCursorAdapter的方法
		//但需要注意，使用SimpleCursorAdapter必须有字段_id，且SimpleCursorAdpater会把
		 //所有字段都解释成小写
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.listcontent,result,new String[] { "ID", "NAME", "PHONE",
		"CLASS" }, new int[] { R.id.textView1, R.id.textView2,
				R.id.textView3, R.id.textView4 });
		*/
		lv.setAdapter(adapter);
		setContentView(lv);

		result.close();
		db.close();
		//自己在ListView上加上上下文菜单，并且做删除/修改的处理	
		
	}

	// 初始化表格数据
	private void initData(String name, String phone, String sclass) {
		ContentValues values = new ContentValues();
		values.put("NAME", name);
		values.put("PHONE", phone);
		values.put("CLASS", sclass);
		db.insert("STUDENTINFO", "ID", values);
	}

	class MyDatabaseHelper extends SQLiteOpenHelper {
		public String DATABASE_TABLE = "STUDENTINFO";
		public final String DB_CREATE_TABLE = "CREATE TABLE " + DATABASE_TABLE
				+ "( " + "ID     INTEGER    NOT NULL,"
				+ "NAME   CHAR(20)   NOT NULL," + "PHONE  CHAR(20),"
				+ "CLASS  CHAR(50)," + "PRIMARY KEY(ID) );";

		public MyDatabaseHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DB_CREATE_TABLE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub

		}
	}

}