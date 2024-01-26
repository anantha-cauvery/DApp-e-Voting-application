package my.voting;
import android.content.Context;
import android.database.*;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	public static final String COUNT = null;
	String TableName="table_name";
	public String DMK_TABLE;
	String DMK="table_name";
	public DatabaseHelper(Context context) {
		super(context, "database_name", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db){
		String sQuery="create table " + DMK+"(cnt)";
		db.execSQL(sQuery);
		getSum();
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){
		String sQuery="drop table if exists " + TableName;
		
		db.execSQL(sQuery);
		onCreate(db);
	}
	
	public String getSum(){
		SQLiteDatabase database=getReadableDatabase();
		
		String sQuery="select sum(cnt) from " + DMK;
		
		return null;
		
	}

}
