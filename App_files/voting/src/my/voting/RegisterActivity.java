package my.voting;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	SQLiteDatabase voteDB = null; 
	Button r;
	EditText e1,e2,e3,e4,e5,e6;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		r=(Button)findViewById(R.id.reg4);
		e1=(EditText)findViewById(R.id.name);
		e2=(EditText)findViewById(R.id.pnum);
		e3=(EditText)findViewById(R.id.mail);
		e4=(EditText)findViewById(R.id.vid);
		e5=(EditText)findViewById(R.id.ps1);
		e6=(EditText)findViewById(R.id.ps2);
        
        r.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(check_fields()){
					if(isvalidate_phone(e2.getText().toString())){
						if(validate_email()){
							if(isvalidate_voterid(e4.getText().toString())){
								if(validate_pass()){
									createDB(arg0);
									addContact(arg0);
									Intent i = new Intent(RegisterActivity.this,MainActivity.class);
									String u=e4.getText().toString();
									String p=e6.getText().toString();
									i.putExtra("voterid", u);
									i.putExtra("password", p);
									startActivity(i);
								}
							}
						}
					}
				}
				
			}

			private boolean validate_pass() {
				// TODO Auto-generated method stub
				if(e6.getText().toString().length()!=0){
					if(!(e6.getText().toString()).equals(e5.getText().toString())){
						Toast.makeText(getApplicationContext(),"password and confirm password not matching", Toast.LENGTH_LONG).show();
						return false;
					}
					Toast.makeText(getApplicationContext(),"password matches", Toast.LENGTH_LONG).show();
					return true;
				}
				//Toast.makeText(getApplicationContext(),"password must be minimum of 8 characters", Toast.LENGTH_LONG).show();
				return false;
			}

			private boolean check_fields() {
				// TODO Auto-generated method stub
				if(e1.getText().toString().length()==0 || e2.getText().toString().length()==0 || e3.getText().toString().length()==0|| e4.getText().toString().length()==0|| e5.getText().toString().length()==0|| e6.getText().toString().length()==0){
					Toast.makeText(getApplicationContext(),"please enter all the fields", Toast.LENGTH_LONG).show();
					return false;
				}
				else{
					return true;
				}
			}
			private boolean validate_phone(String s) {
				// TODO Auto-generated method stub
					Pattern p = Pattern.compile("^\\d{10}$");
					Matcher m = p.matcher(s);
					return (m.matches());
			}
			private boolean isvalidate_phone(String s1) {
				if(validate_phone(s1)==true){
					Toast.makeText(getApplicationContext(),"Phone number is correct", Toast.LENGTH_LONG).show();
					return true;
				}
				else{
					Toast.makeText(getApplicationContext(),"please enter valid phone number", Toast.LENGTH_LONG).show();
					return false;
				}
			}
			private boolean validate_voterid(String str){
				String regex = "^(?=.[a-zA-Z])(?=.[0-9])[A-Za-z0-9]+$";
				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(str);
				return m.matches();
			}
			private boolean isvalidate_voterid(String str) {
				// TODO Auto-generated method stub
				if(e4.getText().toString().length()==10 ){
					//&& validate_voterid(str)==true){
					Toast.makeText(getApplicationContext(),"voterid is correct", Toast.LENGTH_LONG).show();
					return true;
				}
				else{
					Toast.makeText(getApplicationContext(),"please enter valid voterid 10 characters", Toast.LENGTH_LONG).show();
					return false;
				}
			}

			private boolean validate_email() {
				// TODO Auto-generated method stub
				if(Patterns.EMAIL_ADDRESS.matcher(e3.getText().toString()).matches()){
					Toast.makeText(getApplicationContext(),"email verified", Toast.LENGTH_LONG).show();
					return true;
				}
				else{
					Toast.makeText(getApplicationContext(),"please enter valid email", Toast.LENGTH_LONG).show();
					return false;
				}
			}
		});
	}
	public void createDB(View view){
		try{
			voteDB=this.openOrCreateDatabase("Vote",MODE_PRIVATE,null);
			voteDB.execSQL("CREATE TABLE IF NOT EXISTS voters(name varchar, phone varchar, mail varchar, voterid varchar primary key, pass varchar, confirm varchar);");
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
		String n=e1.getText().toString();
		String ph=e1.getText().toString();
		String m=e3.getText().toString();
		String v=e4.getText().toString();
		String pa=e6.getText().toString();
		String c=e5.getText().toString();
		//contactsDB.execSQL("delete from contacts");
		voteDB.execSQL("Insert into voters(name,phone,mail,voterid,pass,confirm) values('"+n+"','"+ph+"','"+m+"','"+v+"','"+pa+"','"+c+"');");
	} 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

}