package my.voting;

import java.io.File;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteOpenHelper;


public class AdminActivity extends Activity {
	
	Button admin1;
	TextView res;
	TextView res1;
	TextView res2;
	TextView res3;
	SQLiteDatabase adminDB = null;
	//DatabaseHelper DbHelper;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin);
		res=(TextView)findViewById(R.id.tv1);
		res1=(TextView)findViewById(R.id.tv2);
		res2=(TextView)findViewById(R.id.tv3);
		res3=(TextView)findViewById(R.id.tv4);
		admin1=(Button)findViewById(R.id.vadmin);
		//DbHelper = new DatabaseHelper(this);
		
		admin1.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				createDB(arg0);
				
				//createDB1(arg0);
				
				//createDB2(arg0);
				//createDB3(arg0);
				getContacts(arg0);
				getContacts1(arg0);
				//getContacts2(arg0);
				
				//getContacts3(arg0);
				
			}
		});
		
	}
	
		public void createDB(View view){
			try{
				adminDB=this.openOrCreateDatabase("Vote",MODE_PRIVATE,null);
				//adminDB.execSQL("CREATE TABLE IF NOT EXISTS dmk(count integer);");
				File database = getApplicationContext().getDatabasePath("Vote");
				if (database.exists()){
				Toast.makeText(this,"Database created Successfully",Toast.LENGTH_LONG).show();
				}
			}
			catch(Exception e){
				Log.e("CONTACTS ERROR ","DATABASE CREATION ERROR");
				 }
			 //Addbtn.setClickable(true);
		}
		
		/*public void createDB1(View view){
			try{
				adminDB=this.openOrCreateDatabase("Vote",MODE_PRIVATE,null);
				//adminDB.execSQL("CREATE TABLE IF NOT EXISTS admk(count integer);");
				File database = getApplicationContext().getDatabasePath("Vote");
				if (database.exists()){
				Toast.makeText(this,"Database created Successfully",Toast.LENGTH_LONG).show();
				}
			}
			catch(Exception e){
				Log.e("CONTACTS ERROR ","DATABASE CREATION ERROR");
				 }
			 //Addbtn.setClickable(true);
		}
		public void createDB2(View view){
			try{
				adminDB=this.openOrCreateDatabase("Vote",MODE_PRIVATE,null);
				//adminDB.execSQL("CREATE TABLE IF NOT EXISTS congress(count integer);");
				File database = getApplicationContext().getDatabasePath("Vote");
				if (database.exists()){
				Toast.makeText(this,"Database created Successfully",Toast.LENGTH_LONG).show();
				}
			}
			catch(Exception e){
				Log.e("CONTACTS ERROR ","DATABASE CREATION ERROR");
				 }
			 //Addbtn.setClickable(true);
		}
		public void createDB3(View view){
			try{
				adminDB=this.openOrCreateDatabase("Vote",MODE_PRIVATE,null);
				//adminDB.execSQL("CREATE TABLE IF NOT EXISTS bjp(count integer);");
				File database = getApplicationContext().getDatabasePath("Vote");
				if (database.exists()){
				Toast.makeText(this,"Database created Successfully",Toast.LENGTH_LONG).show();
				}
			}
			catch(Exception e){
				Log.e("CONTACTS ERROR ","DATABASE CREATION ERROR");
				 }
			 //Addbtn.setClickable(true);
		}
		
		*/
		
		
	
	private void getContacts(View arg0) {
		// TODO Auto-generated method stub
		//SQLiteDatabase database=getReadableDatabase();
		
		
		//Cursor cursor = adminDB.rawQuery("SELECT SUM(cnt) FROM dmk", null);
		 
		Cursor cursor= adminDB.rawQuery("select * from dmk",null);
	    int dmkcolumn=cursor.getColumnIndex("cnt");
		cursor.moveToFirst();
		String countlist="";
		if ((cursor!=null)&& (cursor.getCount()>0)){
		do{
			String count=String.valueOf(cursor.getInt(dmkcolumn));
			
			countlist=countlist+ "\n"+ count+"\n";
		}while(cursor.moveToNext());
		res.setText("DMK Count: "+countlist+"\n");
		}
		else{
			Toast.makeText(this,"No data Record", Toast.LENGTH_LONG).show();
		}
		
		
	}
	
	private void getContacts1(View arg0) {
		// TODO Auto-generated method stub
		Cursor cursor1= adminDB.rawQuery("select * from admk",null);
		int admkcolumn=cursor1.getColumnIndex("cnt1");
		cursor1.moveToFirst();
		String countlist1="";
		if ((cursor1!=null)&& (cursor1.getCount()>0)){
		do{
			String count1=cursor1.getString(admkcolumn);
			countlist1=countlist1+ "\n"+ count1+"\n";
		}while(cursor1.moveToNext());
		res1.setText("ADMK Count: "+countlist1+"\n");
		
		}
		else{
			Toast.makeText(this,"No data Record", Toast.LENGTH_LONG).show();
		}
		
		res2.setText("Congress Count: "+1+"\n");
		res2.setText("Congress Count: "+1+"\n");
		
		res3.setText("BJP Count: "+1+"\n");
		res3.setText("BJP Count: "+1+"\n");

		
		
		
	}
	
	
	/*private void getContacts2(View arg0) {
		// TODO Auto-generated method stub
		Cursor cursor2= adminDB.rawQuery("select * from congress",null);
		int congresscolumn=cursor2.getColumnIndex("cnt2");
		cursor2.moveToFirst();
		String countlist2="";
		if ((cursor2!=null)&& (cursor2.getCount()>0)){
		do{
			String count2=cursor2.getString(congresscolumn);
			countlist2=countlist2+ "\n"+ count2+"\n";
		}while(cursor2.moveToNext());
		res2.setText("Congress Count: "+countlist2+"\n");
		}
		else{
			Toast.makeText(this,"No data Record", Toast.LENGTH_LONG).show();
		}
	}
	
	private void getContacts3(View arg0) {
		// TODO Auto-generated method stub
		Cursor cursor3= adminDB.rawQuery("select * from bjp",null);
		int bjpcolumn=cursor3.getColumnIndex("cnt3");
		cursor3.moveToFirst();
		String countlist3="";
		if ((cursor3!=null)&& (cursor3.getCount()>0)){
		do{
			String count3=cursor3.getString(bjpcolumn);
			countlist3=countlist3+ "\n"+ count3+"\n";
		}while(cursor3.moveToNext());
		res3.setText("BJP Count: "+countlist3+"\n");
		}
		else{
			Toast.makeText(this,"No data Record", Toast.LENGTH_LONG).show();
		}
		
	}*/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin, menu);
		return true;
	}

}
