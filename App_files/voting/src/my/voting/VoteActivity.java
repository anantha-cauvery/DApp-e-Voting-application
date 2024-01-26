package my.voting;

import java.io.File;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class VoteActivity extends Activity {
	Button v;
	RadioGroup r;
	RadioButton r1,r2,r3,r4;
	SQLiteDatabase voteDB = null; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vote);
		v=(Button)findViewById(R.id.v1);
		r=(RadioGroup)findViewById(R.id.radioGroup1);
		r1=(RadioButton)findViewById(R.id.radio0);
		r2=(RadioButton)findViewById(R.id.radio1);
		r3=(RadioButton)findViewById(R.id.radio2);
		r4=(RadioButton)findViewById(R.id.radio3);
		v.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Toast.makeText(context, text, duration)
				int count=0;
				int count1=0;
				int count2=0;
				int count3=0;
				if(r1.isChecked()){
					createDB(arg0);
					count++;
					voteDB.execSQL("Insert into dmk(cnt) values('"+count+"');");
				}
				else if(r2.isChecked()){
					createDB1(arg0);
					count1++;
					voteDB.execSQL("Insert into admk(cnt1) values('"+count1+"');");					
				}
				else if(r3.isChecked()){
					createDB2(arg0);
					count2++;
					voteDB.execSQL("Insert into congress(cnt2) values('"+count2+"');");
				}
				else if(r4.isChecked()){
					createDB3(arg0);
					count3++;
					voteDB.execSQL("Insert into bjp(cnt3) values('"+count3+"');");
				}
				else{
					Toast.makeText(getApplicationContext(),"please select only one candidate", Toast.LENGTH_LONG).show();
				}
				
				Intent i= new Intent(VoteActivity.this, MainActivity.class);
				startActivity(i);
			}
		});
	}
	public void createDB(View view){
		try{
			voteDB=this.openOrCreateDatabase("Vote",MODE_PRIVATE,null);
			voteDB.execSQL("CREATE TABLE IF NOT EXISTS dmk(cnt integer);");
			File database = getApplicationContext().getDatabasePath("Vote");
			if (database.exists()){
				Toast.makeText(this,"Voting Successfull",Toast.LENGTH_LONG).show();
			Toast.makeText(this,"Database created Successfully",Toast.LENGTH_LONG).show();
			}
		}
		catch(Exception e){
			Log.e("CONTACTS ERROR ","DATABASE CREATION ERROR");
			 }
		 //Addbtn.setClickable(true);
	}
	public void createDB1(View view){
		try{
			voteDB=this.openOrCreateDatabase("Vote",MODE_PRIVATE,null);
			voteDB.execSQL("CREATE TABLE IF NOT EXISTS admk(cnt1 integer);");
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
			voteDB=this.openOrCreateDatabase("Vote",MODE_PRIVATE,null);
			voteDB.execSQL("CREATE TABLE IF NOT EXISTS congress(cnt2 integer);");
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
			voteDB=this.openOrCreateDatabase("Vote",MODE_PRIVATE,null);
			voteDB.execSQL("CREATE TABLE IF NOT EXISTS bjp(cnt3 integer);");
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
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vote, menu);
		return true;
	}

}
