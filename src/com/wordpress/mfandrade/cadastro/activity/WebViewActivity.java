package com.wordpress.mfandrade.cadastro.activity;

import android.app.*;
import android.os.*;
import com.wordpress.mfandrade.cadastro.*;
import android.webkit.*;

public class WebViewActivity extends Activity
{

    @Override
    protected void onCreate(Bundle state)
    {
        // TODO: Implement this method
        super.onCreate(state);
        setContentView(R.layout.activity_webview);
        
        WebView browser = (WebView) findViewById(R.id.browser);
        String website = (String) getIntent().getSerializableExtra("website");
        if(website != null)
        {
            browser.loadUrl(website);
        }
    }
}
