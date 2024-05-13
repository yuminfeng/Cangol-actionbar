
package mobi.cangol.mobile.actionbar.demo;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import mobi.cangol.mobile.actionbar.ActionBarActivity;

@Deprecated
public class CustomViewActivity extends ActionBarActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        this.getCustomActionBar().displayUpIndicator();
        this.setTitle(this.getClass().getSimpleName().replace("Activity",""));
        findViews();
    }

    public void findViews() {
        this.findViewById(R.id.button_custom_1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getCustomActionBar().setDisplayShowHomeEnabled(false);
                getCustomActionBar().setCustomView(new EditText(CustomViewActivity.this));
            }
        });
        this.findViewById(R.id.button_custom_2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getCustomActionBar().removeCustomView();
            }
        });
    }
}
