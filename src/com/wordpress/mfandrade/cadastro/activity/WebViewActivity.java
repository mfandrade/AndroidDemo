/*
 *  Copyright (C) 2014  Marcelo F Andrade <mfandrade@gmail.com>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
