/**
 * simple popup with layout
 */
package intcloud.bridge;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import globalStuff.gb; // for global classes


public class PopupScreen extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forecast); // using layout
    }
// if prefer no return
//  public void gohome(View v) {         this.finish();     }
// for returning code
    public void gohome(View v) {
        String ss =  gb.getHelloWorld();
        gb.ss = " of Bridge ";
        Integer ss2 =  gb.getAppleCount();
        System.out.println(ss + gb.ss + ", id " + ss2);
        setResult(RESULT_CANCELED);
        this.finish(); // or onBackPressed();
    }

}
