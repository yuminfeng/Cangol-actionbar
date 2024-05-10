
package mobi.cangol.mobile.actionbar.demo;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;

import mobi.cangol.mobile.actionbar.ActionBarActivity;

public class LeftMenuActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_menu);
        this.getCustomActionBar().displayUpIndicator();
        this.setTitle(this.getClass().getSimpleName().replace("Activity", ""));
        findViews();
    }

    public void findViews() {
        this.getCustomActionBar().setTitleGravity(Gravity.CENTER);
        this.findViewById(R.id.button_menu_0).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getCustomActionBar().setDisplayShowHomeEnabled(true);
            }
        });
        this.findViewById(R.id.button_menu_1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getCustomActionBar().setDisplayShowHomeEnabled(false);
            }
        });
        this.findViewById(R.id.button_menu_2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getCustomActionBar().setLeftMenu(0x11, R.string.action_setting, R.drawable.actionbar_clear_dark, new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        });
        this.findViewById(R.id.button_menu_3).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getCustomActionBar().clearLeftMenu();
            }
        });
    }

}
