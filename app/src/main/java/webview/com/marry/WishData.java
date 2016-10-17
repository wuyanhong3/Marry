package webview.com.marry;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/10/17.
 */
public class WishData extends SQLiteOpenHelper {
    private final static String DATABASE_NAME =WishLiteUtil.DATABASE_NAME;                  //数据库名
    private final static int DATABASE_VERSION = 1;                                          //数据库版本
    private final static String DATABASE_TABLE_NAME=WishLiteUtil.DATABASE_TABLE_NAME;       //表名

  public WishData(Context context){
      super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
              String addTable="CREATE TABLE IF NOT EXISTS "+
                                DATABASE_TABLE_NAME+"("+
                                WishLiteUtil.TABLE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                 WishLiteUtil.TABLE_WISH_CONTENT+" varchar, "+
                                 WishLiteUtil.TABLE_WISH_NAME+" varchar"+
                                                          ")";
        sqLiteDatabase.execSQL(addTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {                //升级数据库

    }
}
