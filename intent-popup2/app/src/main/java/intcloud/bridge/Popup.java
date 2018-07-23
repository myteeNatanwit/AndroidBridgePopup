/*
This is coded screen module not used layout.
*/

package intcloud.bridge;

import globalStuff.gb;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import globalStuff.gb;



public class Popup extends Activity {
	 PopupWindow msgbox;
	 LinearLayout layout;
	 TextView tv;
	 LayoutParams params;
	 LinearLayout mainLayout;
	 Button but;
	 Button but2;
	boolean click = true;

 	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  msgbox = new PopupWindow(this);
	  layout = new LinearLayout(this);
	  mainLayout = new LinearLayout(this);
	  tv = new TextView(this);
	  but = new Button(this);

	  but2 = new Button(this);
	  but2.setText("Close me");
	  but.setText("Click Me");
	  but.setOnClickListener(new OnClickListener() {

	   public void onClick(View v) {
	    if (click) {
            msgbox.showAtLocation(layout, Gravity.BOTTOM, 10, 150);
            msgbox.update(50, 150, 800, 80);
			but.setText("Click again");
	     click = false;
	    } else {
			but.setText("Click Me");
            msgbox.dismiss();
	     	click = true;

	    }
	   }

	  });
		 but2.setOnClickListener(new OnClickListener() {

			 public void onClick(View v) {
					 setResult(RESULT_CANCELED);
					 onBackPressed();
			 }

		 });

	 params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
	  layout.setOrientation(LinearLayout.VERTICAL);
	  tv.setTextColor(0xffbdbdbd);
	  tv.setText("sample text for popup window");
	  layout.addView(tv, params);
         msgbox.setContentView(layout);
	  mainLayout.addView(but, params);
	  mainLayout.addView(but2, params);
	  setContentView(mainLayout);

	 }
	}

