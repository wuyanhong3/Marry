package webview.com.marry;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView marryHtml;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getID();
        setJavascriptInterface();
        marryHtml.setWebViewClient(new MyWebView());
        marryHtml.loadUrl("file:///android_asset/htmlview.html");

    }
    private void getID(){
        marryHtml= (WebView) findViewById(R.id.marry_html);
    }
    class MyWebView extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            marryHtml.loadUrl(url);
            return true;
        }
    }

    interface MyJavaScricInterface{
         void MarryCard();
         void Wish();
         void Shark();
         void ShowPhoto();
    }
    /**
     * 设置javaScript与android原生应用交互接口
     */
    @SuppressLint("JavascriptInterface")
    public void setJavascriptInterface() {
        //第一步 设置javascript 可用
       marryHtml.getSettings().setJavaScriptEnabled(true);
        // marryHtml.getSettings().setUseWideViewPort(true);
        //marryHtml.getSettings().setLoadWithOverviewMode(true);
        //第三步 添加javaScript交互接口到webview
    MyJavaScricInterface myJavaScriptInterface = new MyJavaScricInterface() {
        @JavascriptInterface
        @Override
        public void MarryCard() {
            Log.v("ppp","MarryCard");
                 Intent intent=new Intent(MainActivity.this,MarryCard.class);
                 startActivity(intent);
        }
        @JavascriptInterface
        @Override
        public void Wish() {
           Intent intent=new Intent(MainActivity.this,Wish.class);
            startActivity(intent);
        }
        @JavascriptInterface
        @Override
        public void Shark() {
            Intent intent=new Intent(MainActivity.this,MarryCard.class);
            startActivity(intent);
        }
       @JavascriptInterface
        @Override
        public void ShowPhoto() {
            Log.v("ppp","ShowPhoto");
            Intent intent=new Intent(MainActivity.this,MarryPhoto.class);
            startActivity(intent);
        }
    };
      marryHtml.addJavascriptInterface(myJavaScriptInterface,"myJavaScriptInterface");//添加与html交互的接口实例
    }
}

