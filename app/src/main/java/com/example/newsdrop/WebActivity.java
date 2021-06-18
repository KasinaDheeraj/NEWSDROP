package com.example.newsdrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.view.MenuItemCompat;

public class WebActivity extends AppCompatActivity {
    public static String URL="";
    String url;
    private ShareActionProvider shareActionProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        url= String.valueOf(getIntent().getExtras().get(URL));

        WebView webView =findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_webview,menu);
        MenuItem item=menu.findItem(R.id.ShareOption);
        shareActionProvider= (ShareActionProvider) MenuItemCompat.getActionProvider(item);

        Intent intent =new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,url);
        intent.setType("text/plain");
        shareActionProvider.setShareIntent(intent);


        return super.onCreateOptionsMenu(menu);
    }


}
