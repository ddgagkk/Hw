package noname.hw5vs6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.INotificationSideChannel;

import java.util.ArrayList;

/**
 * Created by hp on 10/19/2017.
 */

public class DataHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "Hw5.db";
    private final static String TABLE_NAME="InforUser";
    private final static String NAME_COMLUMN="Name";
    private final static String PHONE_COLUMN= "Phone";
    private final static String CREATE_DATABASE=" Create table "+TABLE_NAME+" ( "+ NAME_COMLUMN+" text, "+ PHONE_COLUMN +" text )";
    public DataHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DropTable If Exists contracts");
        onCreate(sqLiteDatabase);
    }

    public void InsertUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contenValues = new ContentValues();
        contenValues.put(NAME_COMLUMN,user.getName());
        contenValues.put(PHONE_COLUMN,user.getNumber());
        db.insert(TABLE_NAME,null,contenValues);
    }
    public ArrayList<User> getAll(){
        ArrayList<User> listUser= new ArrayList<User>();
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor= db.rawQuery("Select * from "+TABLE_NAME,null);

        while (cursor.moveToNext()){
            String name=cursor.getString(cursor.getColumnIndex(NAME_COMLUMN));
            String phone= cursor.getString(cursor.getColumnIndex(PHONE_COLUMN));
            User user= new User(name,phone);
            listUser.add(user);
        };
        return listUser;
    }

    public int checkDataEx(){

        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor= db.rawQuery("Select * from "+TABLE_NAME,null);
        int temp= cursor.getCount();
        return temp;
    }
}
