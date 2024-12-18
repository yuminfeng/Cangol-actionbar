
package mobi.cangol.mobile.actionbar.demo;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import mobi.cangol.mobile.actionbar.ActionBarActivity;

@Deprecated
public class TitleActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_view);
        this.getCustomActionBar().displayUpIndicator();
        this.setTitle(this.getClass().getSimpleName().replace("Activity", ""));
        findViews();
    }

    public void findViews() {
        this.findViewById(R.id.button_title_1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getCustomActionBar().setTitleGravity(Gravity.CENTER);
            }
        });
        this.findViewById(R.id.button_title_2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getCustomActionBar().setTitleGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
            }
        });
        this.findViewById(R.id.button_title_2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getCustomActionBar().getTitleVisibility() == View.VISIBLE) {
                    getCustomActionBar().setTitleVisibility(View.VISIBLE);
                } else {
                    getCustomActionBar().setTitleVisibility(View.INVISIBLE);
                }
            }
        });
        this.findViewById(R.id.button_shadwow_1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setShadow(findViewById(R.id.image), true);
            }
        });
        this.findViewById(R.id.button_shadwow_2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setShadow(findViewById(R.id.image), false);
            }
        });
        //设置标题点击事件
        this.getCustomActionBar().setOnTitleClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Click Title", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setShadow(View view, boolean shadow) {
        if (shadow) {
            view.setElevation(4 * getResources().getDisplayMetrics().density);
        } else {
            view.setElevation(0);
        }
    }
}
