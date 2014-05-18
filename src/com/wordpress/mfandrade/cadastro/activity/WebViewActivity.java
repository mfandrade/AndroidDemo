package com.wordpress.mfandrade.cadastro.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import com.wordpress.mfandrade.cadastro.R;

public class WebViewActivity extends Activity {
  @Override
  protected void onCreate(Bundle state) {
	super.onCreate(state);
	setContentView(R.layout.activity_webview);
	WebView browser = (WebView) findViewById(R.id.browser);
	String website = (String) getIntent().getSerializableExtra("website");
	if (website != null) {
	  browser.loadUrl(website);
	}
  }
}
