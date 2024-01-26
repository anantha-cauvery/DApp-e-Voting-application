package my.voting;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminPage extends Activity {
	Button register;
	EditText u1;
	EditText p1;
	Button login1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_page);
		//register=(Button)findViewById(R.id.reg3);
		u1=(EditText)findViewById(R.id.un1);
		p1=(EditText)findViewById(R.id.pw1);
		login1=(Button)findViewById(R.id.login1);
		
		login1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				check_fields();
				validate();
				
			}

			private boolean validate() {
				// TODO Auto-generated method stub
				String n1=getResources().getString(R.string.adminsri);
				String pass1=getResources().getString(R.string.adminpass);
				
				String orgu1=u1.getText().toString();
				String orgp1=p1.getText().toString();
				if(n1.equals(orgu1) && pass1.equals(orgp1)){
					Toast.makeText(getApplicationContext(),"Login successful", Toast.LENGTH_LONG).show();
					Intent i=new Intent(AdminPage.this,AdminActivity.class);
					startActivity(i);
					return true;
				}
				else{
					Toast.makeText(getApplicationContext(),"Invalid credentials, Login Unsuccessful", Toast.LENGTH_LONG).show();
					return false;
				}
			}

			private boolean check_fields() {
				// TODO Auto-generated method stub
				if(u1.getText().toString().length()==0 || p1.getText().toString().length()==0){
					Toast.makeText(getApplicationContext(),"Please Enter all the fields", Toast.LENGTH_LONG).show();
					return false;
				}
				else{
					return true;
				}
			}
		});

		
		
		
		
		/*register.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(AdminPage.this,AdminRegister.class);
				startActivity(i);
			}
		});*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin_page, menu);
		return true;
	}

}
