package my.voting;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	SQLiteDatabase voteDB = null; 
	EditText e1,e2;
	Button l;
	Button r2;
	Button admin1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText)findViewById(R.id.name);
        e2=(EditText)findViewById(R.id.pnum);
        l=(Button)findViewById(R.id.otp);
        admin1=(Button)findViewById(R.id.admin);
        
        admin1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(MainActivity.this, AdminPage.class);
				startActivity(i);
			}
		});
        
        l.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(check_fields()){
					if(isvalidate_voterid(e1.getText().toString())){
						if(validate()){
							createDB(arg0);
							addContact(arg0);
							//Intent i = new Intent(MainActivity.this,MainActivity2.class);
							Intent i = new Intent(MainActivity.this, VoteActivity.class);
						
							/*Bundle o=getIntent().getExtras();
							String u=o.getString("voterid");
							String p=o.getString("password");*/
							startActivity(i);
						}
					}
				}
			}

			private boolean validate() {
				// TODO Auto-generated method stub
				Bundle o=getIntent().getExtras();
				Bundle o1=getIntent().getExtras();
				String u=o.getString("voterid");
				String p=o1.getString("password");
				String a=e1.getText().toString();
				String b=e2.getText().toString();
				if(a.equals(u) && b.equals(p)){
					Toast.makeText(getApplicationContext(),"Login successful", Toast.LENGTH_LONG).show();
					return true;
				}
				else{
					Toast.makeText(getApplicationContext(),"Invalid credentials, Login Unsuccessful", Toast.LENGTH_LONG).show();
					return false;
				}
			}

			private boolean check_fields() {
				// TODO Auto-generated method stub
				if(e1.getText().toString().length()==0 || e2.getText().toString().length()==0){
					Toast.makeText(getApplicationContext(),"please enter all the fields", Toast.LENGTH_LONG).show();
					return false;
				}
				else{
					return true;
				}
			}
			private boolean validate_voterid(String str){
				String regex = "^(?=.[a-zA-Z])(?=.[0-9])[A-Za-z0-9]+$";
				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(str);
				return m.matches();
			}
			private boolean isvalidate_voterid(String s) {
				// TODO Auto-generated method stub
				if(e1.getText().toString().length()==10 ){
					//&& validate_voterid(s)==true){
					Toast.makeText(getApplicationContext(),"voterid is correct", Toast.LENGTH_LONG).show();
					return true;
				}
				else{
					Toast.makeText(getApplicationContext(),"please enter valid voterid 10 characters", Toast.LENGTH_LONG).show();
					return false;
				}
			}
		});
        
        r2=(Button)findViewById(R.id.reg2);
        
        r2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this,RegisterActivity.class);
				startActivity(i);
			}
		});
    }
	public void createDB(View view){
		try{
			voteDB=this.openOrCreateDatabase("Vote",MODE_PRIVATE,null);
			voteDB.execSQL("CREATE TABLE IF NOT EXISTS login(voterid varchar primary key, password varchar);");
			File database = getApplicationContext().getDatabasePath("Vote");
			if (database.exists()){
			Toast.makeText(this,"Database created Successfully"+database.toString(),Toast.LENGTH_LONG).show();
			}
		}
		catch(Exception e){
			Log.e("CONTACTS ERROR ","DATABASE CREATION ERROR");
			 }
		 //Addbtn.setClickable(true);
	}
	public void addContact(View view) {
		String un=e1.getText().toString();
		String pwd=e1.getText().toString();
		//contactsDB.execSQL("delete from contacts");
		voteDB.execSQL("Insert into login(voterid,password) values('"+un+"','"+pwd+"');");
	} 

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
        
    }
    
}
