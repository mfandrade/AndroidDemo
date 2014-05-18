package com.wordpress.mfandrade.cadastro.activity;

import android.app.*;
import android.os.*;
import android.webkit.*;
import com.wordpress.mfandrade.cadastro.*;

public class WebViewActivity extends Activity
{
	@Override
	protected void onCreate(Bundle state)
	{
		super.onCreate(state);
		setContentView(R.layout.activity_webview);
		//
		WebView browser = (WebView) findViewById(R.id.browser);
		String website = (String) getIntent().getSerializableExtra("website");
		if (website != null)
		{
			browser.loadUrl(website);
		}
	}
}
