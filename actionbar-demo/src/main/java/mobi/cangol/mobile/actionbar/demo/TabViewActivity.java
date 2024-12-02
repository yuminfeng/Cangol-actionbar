
package mobi.cangol.mobile.actionbar.demo;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import mobi.cangol.mobile.actionbar.ActionBarActivity;
import mobi.cangol.mobile.actionbar.ActionTab;
import mobi.cangol.mobile.actionbar.ActionTabItem;
import mobi.cangol.mobile.actionbar.view.ActionTabView;

@Deprecated
public class TabViewActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_view);
        this.getCustomActionBar().displayUpIndicator();
        this.setTitle(this.getClass().getSimpleName().replace("Activity", ""));
        findViews();
    }

    public void findViews() {
        this.findViewById(R.id.button_tab_1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setTitle("");
                initActionTab();
                getCustomActionBar().getActionTab().getTabView(2).setText("tab_1");
            }
        });
        this.findViewById(R.id.button_tab_2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getCustomActionBar().getActionTab().removeAllTabs();
                setTitle(TabViewActivity.this.getClass().getSimpleName().replace("Activity", ""));
            }
        });
        getCustomActionBar().getActionTab().setOnTabSelectedListener(tab -> {
            switch (tab.getId()) {
                case 1:
                    Toast.makeText(TabViewActivity.this, tab.getText(), Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(TabViewActivity.this, tab.getText(), Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        });
    }

    public void initActionTab() {
        ActionTab actionTab = this.getCustomActionBar().getActionTab();
        actionTab.newTab(1, "推荐", 1);
        actionTab.newTab(2, "关注", 0);
    }
}
