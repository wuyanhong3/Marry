package webview.com.marry;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MarryPhoto extends AppCompatActivity {
     private ImageView image;
     private Button downImage;
     private GridView photoGrid;
    photoAdapter photoAdapter;
      private String [] http={
             "http://www.zhennihua.cn/uploadfile/2016/0616/20160616074540143.jpg",
              "http://pic6.wed114.cn/20130726/20130726191906998.jpg",
              "http://img2.imgtn.bdimg.com/it/u=2125072010,1379765094&fm=11&gp=0.jpg",
             "http://aiqiandu.com/Files/Image/20150806142244446.jpg",
              "http://love.qingdaonews.com/user/Uploads/201312/1386640903485_6007.jpg",
              "http://img4.imgtn.bdimg.com/it/u=1988366692,274890054&fm=11&gp=0.jpg",
              "http://img0.imgtn.bdimg.com/it/u=2374095751,1798360001&fm=11&gp=0.jpg",
              "http://img0.imgtn.bdimg.com/it/u=15891043,3411277919&fm=21&gp=0.jpg",
              "http://img0.imgtn.bdimg.com/it/u=3243951718,2280426318&fm=21&gp=0.jpg"
      };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marryphoto_layout);
        getID();
        photoAdapter=new photoAdapter(this);
        photoGrid.setAdapter(photoAdapter);
        photoAdapter.setList(http);
    }
public void getID(){

    photoGrid= (GridView) findViewById(R.id.grid_photo);
}
    public void getImage(){
        AsyncTask<String,Void,Bitmap> asyncTask=new AsyncTask<String,Void,Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... params) {
                Log.v("pppp","doInBackground");

                return getBitmap(params[0]);
            }
            @Override
            protected void onPostExecute(Bitmap bitmap) {
              image.setImageBitmap(bitmap);
            }

        };
        asyncTask.execute(http[1]);
    }
    public Bitmap getBitmap(String http){
        URL url=null;
        Bitmap bitmap=null;
        Log.v("pppp","doInBackground");
        try {
            url=new URL(http);
            Log.v("pppp",http.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap ;
    }
}
