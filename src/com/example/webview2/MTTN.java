package com.example.webview2;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MTTN extends Activity {
	public class WebAppInterface {
	    Context mContext;

	    /** Instantiate the interface and set the context */
	    WebAppInterface(Context c) {
	        mContext = c;
	    }

	    /** Show a toast from the web page */
	    @JavascriptInterface
	    public void showToast(String toast) {
	        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
	    }
	}



	
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mttn);
		
		WebView myWebView = (WebView) findViewById(R.id.webview);
		
	
		 myWebView.loadUrl("http://www.manipalthetalk.net");
		 
		 myWebView.getSettings().setLoadWithOverviewMode(true);
		 myWebView.getSettings().setUseWideViewPort(true);
		
		
		WebSettings webSettings = myWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");
		myWebView.setWebViewClient(new WebViewClient());
		myWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
		
		myWebView.getSettings().setAppCacheEnabled(true);

	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		WebView myWebView = (WebView) findViewById(R.id.webview);
	    // Check if the key event was the Back button and if there's history
	    if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
	        myWebView.goBack();
	        return true;
	    }
	    // If it wasn't the Back key or there's no web page history, bubble up to the default
	    // system behavior (probably exit the activity)
	    return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mttn, menu);
		return true;
	}

}
