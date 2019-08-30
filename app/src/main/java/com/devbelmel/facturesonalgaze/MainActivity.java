package com.devbelmel.facturesonalgaze;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {
    private WebView webView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    webView.loadUrl("https://eccp.poste.dz/");
                    return true;
                case R.id.navigation_back:
                    if(webView.canGoBack())
                    webView.goBack();
                    return true;
                case R.id.navigation_forward:
                    if(webView.canGoForward())
                    webView.goForward();
                    return true;
            }
            return false;
        }
    };
    @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setIcon(R.mipmap.ic_icon_app);
        setSupportActionBar(toolbar);
            webView = (WebView) findViewById(R.id.mWebView);
            WebSettings webSetting=webView.getSettings();
            webSetting.setJavaScriptEnabled(true);
            webView.loadUrl("https://eccp.poste.dz/");
            webView.setWebViewClient(new MyWebViewClient());
        }


    public boolean onKeyDown(int keyCode, KeyEvent event){
            // Check if the key event was the Back button and if there's history
            if (((keyCode == KeyEvent.KEYCODE_BACK)) && webView.canGoBack()) {
                Toast.makeText(this,"button go back",Toast.LENGTH_SHORT).show();
                webView.goBack();
                return true;
            }
            else if (((keyCode == KeyEvent.KEYCODE_FORWARD)) && webView.canGoBack()) {
            Toast.makeText(this,"button go back",Toast.LENGTH_SHORT).show();
            webView.goBack();
            return true;
        }
            // If it wasn't the Back key or there's no web page history, bubble up to the default
            // system behavior (probably exit the activity)
            return super.onKeyDown(keyCode, event);
        }

      //  @Deprecated
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            if ("https://eccp.poste.dz/".equals(request.toString())) {
                // This is my website, so do not override; let my WebView load the page
                return false;
            }
            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                webView.loadUrl(request.getUrl().toString());
            }
            else {
                webView.loadUrl(request.toString());
            }
            return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_About) {
            Toast.makeText(MainActivity.this, "im developer",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public void onStop(){
        super.onStop();
    }}







