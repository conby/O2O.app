/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package io.conby.feiyang;

import android.os.Bundle;
import android.os.Build;
import org.apache.cordova.*;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.view.*;
import android.widget.*;

import org.apache.cordova.engine.*;
import android.webkit.WebChromeClient;
//import org.apache.cordova.engine.SystemWebChromeClient;
//import org.apache.cordova.engine.SystemWebViewClient;
//import org.apache.cordova.engine.SystemWebViewEngine;

import java.util.Timer;

import io.conby.feiyang.R;

public class MainActivity extends CordovaActivity
{
    private Dialog loadDialog;
    private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.init();
        initp();

        //super.setIntegerProperty("splashscreen", R.drawable.screen);
        
        // Dialog
        loadDialog = new Dialog(MainActivity.this, R.style.dialog);
        loadDialog.setCancelable(false);
        loadDialog.setContentView(R.layout.activity_main);
        
        Window win = loadDialog.getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.FILL_PARENT;
        lp.height = WindowManager.LayoutParams.FILL_PARENT;
        win.setAttributes(lp);
        
        progressBar = (ProgressBar) loadDialog.findViewById(R.id.progressBar5);

        // Set by <content src="index.html" /> in config.xml
        loadUrl(launchUrl);
    }

    //@Override
    public void initp() {
        //CordovaWebView webView = null;
        //CordovaWebView webView = new CordovaWebView(MainActivity.this);
		//SystemWebView webView = (SystemWebView)appView.getView();
		SystemWebView webView = (SystemWebView)appView.getEngine().getView();
		SystemWebViewEngine engine = (SystemWebViewEngine)appView.getEngine();

        WebSettings settings = webView.getSettings();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            settings.setAllowFileAccessFromFileURLs(true);
            settings.setAllowUniversalAccessFromFileURLs(true);
        }

        //this.init(webView, new CordovaWebViewClient(this, webView) {
        webView.setWebViewClient(new SystemWebViewClient(engine){
		//webView.setWebViewClient(new SystemWebViewClient(){
            @Override
            public void onPageFinished(WebView arg0, String arg1) {
                super.onPageFinished(arg0, arg1);
                progressBar.setProgress(100);
                endLoad();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (url.equals("about:blank")) {
                } else {
                    startLoad();
                }
            }

//    		@Override
//    		public void doUpdateVisitedHistory(WebView view, String url, boolean isReload){
//        		super.doUpdateVisitedHistory(view, url, isReload);
//    		}
//
//    		@Override
//    		public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//        		super.onReceivedError(view, errorCode, description, failingUrl);
//    		}
        });

		/*
		, new CordovaChromeClient(this, webView) {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);                
                progressBar.setProgress(newProgress);
            }
        });
        */

		//webView.setWebChromeClient(new SystemWebChromeClient((SystemWebChromeEngine)appView.getEngine())) {
   	    webView.setWebChromeClient(new SystemWebChromeClient(engine){
//   	    webView.setWebChromeClient(new WebChromeClient() {
		    @Override
            public void onProgressChanged(WebView view, int newProgress) {
			    super.onProgressChanged(view, newProgress);  
                progressBar.setProgress(newProgress);
            }
        });

    }
    

    /**
     *
     * @param view
     * @param url
     */
    private void startLoad() {
        if (loadDialog.isShowing()) {
        } else {
            loadDialog.show();
        }
    }
  
    /**
     *
     * @param view
     * @param url
     */
    private void endLoad() {
        if (loadDialog.isShowing()) {
            loadDialog.cancel();
            loadDialog.dismiss();
        }
    }
}
