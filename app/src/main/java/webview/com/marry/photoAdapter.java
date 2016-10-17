package webview.com.marry;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/7/18.
 */
public class photoAdapter extends BaseAdapter {
    private String[] http=new String[]{};
    private Context context;
    private LayoutInflater layoutInflater;
    public photoAdapter(Context context){
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
    }
    public void setList(String [] http){
        this.http=http;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return http.length;
    }

    @Override
    public Object getItem(int position) {
        return http[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final HolderView holderView;
          if(convertView==null){
              convertView=layoutInflater.inflate(R.layout.gridadapter,null);
              ImageView imageView= (ImageView) convertView.findViewById(R.id.grid_photo);
              holderView=new HolderView();
              holderView.imageView=imageView;

              convertView.setTag(holderView);
          }else{
              holderView= (HolderView) convertView.getTag();
          }
           String http1= (String) getItem(position);
           Glide.with(context).load(http1).into(holderView.imageView);

        return convertView;
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
    class HolderView{
        ImageView imageView;
    }
}
