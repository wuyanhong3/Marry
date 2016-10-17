package webview.com.marry;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Wish extends AppCompatActivity implements View.OnClickListener {
      private EditText wishContent,wishName;
      private ListView showWish;
      private Button sendWish;
      private List<String> wishList=new ArrayList<>();
      private  ArrayAdapter arrayAdapter;
      private WishData wishData;
      private SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wish_layout);
        initView();
        setListener();
        wishData=new WishData(this);
        sqLiteDatabase=wishData.getReadableDatabase();
        getWishData();
        arrayAdapter=new ArrayAdapter(this,R.layout.wish_list_item,wishList);
        arrayAdapter.notifyDataSetChanged();
        showWish.setAdapter(arrayAdapter);
        wishContent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    Log.v("pppp",view.getId()+""+">>>>>"+b);
                    wishName.setVisibility(View.VISIBLE);
                    sendWish.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    private void initView(){
        wishContent= (EditText) findViewById(R.id.wish_content_edit);
        wishName= (EditText) findViewById(R.id.wish_name_edit);
        showWish= (ListView) findViewById(R.id.show_wish_listView);
        sendWish= (Button) findViewById(R.id.send_wish_button);
    }
   private void setListener(){
       sendWish.setOnClickListener(this);
   }
    private void getWishData(){
        String[] colume=new String[]{WishLiteUtil.TABLE_WISH_NAME,WishLiteUtil.TABLE_WISH_CONTENT};
        Cursor cursor=sqLiteDatabase.query(WishLiteUtil.DATABASE_TABLE_NAME,colume,null,null,null,null,null);
        while(cursor.moveToNext()){
            int contentIndex=cursor.getColumnIndex(WishLiteUtil.TABLE_WISH_CONTENT);
            int nameIndex=cursor.getColumnIndex(WishLiteUtil.TABLE_WISH_NAME);
            String content=cursor.getString(contentIndex);
            String name=cursor.getString(nameIndex);
            wishList.add(content+"----"+name);
        }
    }
    @Override
    public void onClick(View view) {
        ContentValues contentValues=new ContentValues();
        switch (view.getId()){
            case R.id.send_wish_button:
                String wishC=wishContent.getText().toString();
                String wishN=wishName.getText().toString();
                contentValues.put(WishLiteUtil.TABLE_WISH_CONTENT,wishC);
                contentValues.put(WishLiteUtil.TABLE_WISH_NAME,wishN);
                sqLiteDatabase.insert(WishLiteUtil.DATABASE_TABLE_NAME,null,contentValues);
                wishList.add(wishC+"------"+wishN);
                arrayAdapter.notifyDataSetChanged();
                wishName.setVisibility(View.GONE);
                sendWish.setVisibility(View.GONE);
                wishContent.setText("");
                wishName.setText("");
        }
    }
}
