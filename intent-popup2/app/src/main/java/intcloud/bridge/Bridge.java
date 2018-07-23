package intcloud.bridge;

import java.io.IOException;

import java.util.List;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Activity;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.Toast;

   
public class Bridge extends Activity {
	WebView webview;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        webview = (WebView) findViewById(R.id.webView1);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        webview.getSettings().setSupportMultipleWindows(false);
        webview.getSettings().setSupportZoom(false);
        webview.getSettings().setAllowFileAccessFromFileURLs(true);
        webview.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webview.setVerticalScrollBarEnabled(false);
        webview.setHorizontalScrollBarEnabled(false);
        //Inject WebAppInterface methods into Web page by having Interface 'bridge' 
        webview.addJavascriptInterface(new bridge_protocol(this), "bridge");
        webview.setWebChromeClient(new WebChromeClient());
        String myUrl2 = "https://store11833595.ecwid.com/";
        String myUrl = "file:///android_asset/www/index.html";
        webview.loadUrl(myUrl);
                
    }
    // to get return from popup screen
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("onActivityResult: " + resultCode);
        if (requestCode == 100) { // it is my code predefined as 100
            if (resultCode == RESULT_OK) {
                Log.d("result ok", "onActivityResult: " +  resultCode);
            } else {
                Log.d("result cancel", "onActivityResult: canceled");
            }
        }
    }

    public void js_call(WebView webview, String jsString) {
		final String webUrl = "javascript:" +  jsString;
		 if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
	        webview.evaluateJavascript(jsString, null);
	        System.out.println(jsString);
	    } else {
	        webview.loadUrl(webUrl);
	        System.out.println("old way");
	    }
	}
	
// class to comm with JS	
public class bridge_protocol {
        Context aContext;

        bridge_protocol(Context c) {
            aContext = c;
        }

        private void sendjs(final String s) {
            webview.post(new Runnable() {
                public void run() {
                    webview.loadUrl("javascript:" + s + ";");
                }
            });
        }
    	
        //  Show Toast Message
        // @param toast as String
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(aContext, toast, Toast.LENGTH_SHORT).show();
        }
        
        //  Show Dialog 
        @JavascriptInterface
        public void showDialog(String dialogMsg){
        	AlertDialog.Builder alertDialog = new AlertDialog.Builder(aContext);
        	 
            // Setting Dialog Title
            alertDialog.setTitle("Title of Android dialog");
            // Setting Dialog Message
            alertDialog.setMessage(dialogMsg);
            alertDialog.setCancelable(true);
            // Setting Positive "Yes" Button
            alertDialog.setPositiveButton("YES",
                     new DialogInterface.OnClickListener() {
                         public void onClick(DialogInterface dialog, int which) {  // do something
                             dialog.cancel();
                        	 sendjs("Javascript_function('dialog', 'OK')");
                         }
                     });
            // Showing Alert Message
            alertDialog.show();
        }
    //  Show popup
    @JavascriptInterface
    public void showPopup(String Msg) {
        Intent myIntent = new Intent(getApplicationContext(), PopupScreen.class);
        // startActivity(myIntent); //if no need return code
        startActivityForResult(myIntent,100, null);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    //  Show popupg
    @JavascriptInterface
    public void showPopup2(String Msg) {
        Intent myIntent = new Intent(getApplicationContext(), Popup.class);
        // startActivity(myIntent); //if no need return code
        startActivityForResult(myIntent,100, null);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    }
	}




