package my.voting;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.os.AsyncTask;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class MainActivity3 extends Activity {
	private EditText phone;
    private Button otpch;
    char[] otp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity3);
        phone = (EditText)findViewById(R.id.phoneNumber);
        otpch = (Button)findViewById(R.id.sendOtpBtn);
        otpch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
		        Random random = new Random();


		        //Generate random integer in range 0 to 9
		        
		       otp = new char[4];
		        for (int i=0; i<4; i++)
		        {
		            otp[i]= (char)(random.nextInt(10)+48);
		        }

//		        Toast.makeText(getApplicationContext(), String.valueOf(otp), Toast.LENGTH_SHORT).show();

		        String number  = phone.getText().toString();

		       if(!(phone.getText().toString().equals(""))) {
		           //new MyAsyncTask().execute("https://api.msg91.com/api/sendhttp.php?route=4&sender=TESTIN&message=OTP for your OTP Verification App is : "+String.valueOf(otp)+"&country=91&flash=&unicode=&mobiles="+number+"&authkey=297116AFCGQdLuvdm25d96f3f7");
		       }
		       else{
		           phone.setError("Please Enter your mobile number");
		       }
				Intent i= new Intent(MainActivity3.this, MainActivity2.class);
				startActivity(i);
			}
		});
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity3, menu);
		return true;
	}

}
/*public class MyAsyncTask extends AsyncTask{
    URL Url;
    HttpURLConnection httpURLConnection = null;
    InputStream inputStream;


       @Override
       protected Void doInBackground(String... urls) {

           try {
               Url = new URL(urls[0]);
               httpURLConnection = (HttpURLConnection)Url.openConnection();
               inputStream = httpURLConnection.getInputStream();


           } catch (MalformedURLException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           }
           return null;
       }
}*/
