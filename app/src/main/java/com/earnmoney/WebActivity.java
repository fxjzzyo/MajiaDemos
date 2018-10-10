package com.earnmoney;

import android.graphics.Bitmap;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.earnmoney.utils.ADFilterTool;

public class WebActivity extends AppCompatActivity {

    private WebView mWebView;

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        setTitle("详情");
        url = getIntent().getStringExtra("url");
        if (url != null) {
            mWebView = findViewById(R.id.web_view);
//
//            mWebView.setWebViewClient(new WebViewClient() {
//                @Override
//                public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                    view.loadUrl(url);
//                    return true;
//                }
//            });

            mWebView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);

                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);

                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    if (url.contains("tnb")) {
//                        new WebProtocols(WebActivity.this, url, "");
                    } else {
                        view.loadUrl(url);
                    }
                    return true;
                }


                //防广告注入的代码
                @Override
                public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                    url= url.toLowerCase();
                    if(!ADFilterTool.hasAd(WebActivity.this,url)){
                        return super.shouldInterceptRequest(view,url);//正常加载
                    }else{
                        return new WebResourceResponse(null,null,null);//含有广告资源屏蔽请求
                    }
                }

            });


            mWebView.getSettings().setDomStorageEnabled(true);
            mWebView.loadUrl(this.url);
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
            default:
                break;
        }

        return true;
    }
    //使用Webview的时候，返回键没有重写的时候会直接关闭程序，这时候其实我们要其执行的知识回退到上一步的操作
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //这是一个监听用的按键的方法，keyCode 监听用户的动作，如果是按了返回键，同时Webview要返回的话，WebView执行回退操作，因为mWebView.canGoBack()返回的是一个Boolean类型，所以我们把它返回为true
        if(keyCode==KeyEvent.KEYCODE_BACK&&mWebView.canGoBack()){
            mWebView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


}
