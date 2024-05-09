
package mobi.cangol.mobile.actionbar.demo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import mobi.cangol.mobile.actionbar.ActionBarActivity;
import mobi.cangol.mobile.actionbar.ActionMenu;
import mobi.cangol.mobile.actionbar.ActionTab;

public class ThemeActivity extends ActionBarActivity {
    private static boolean light = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity", "light==" + light);
        if (light) {
            setTheme(R.style.AppTheme1);
        } else {
            setTheme(R.style.AppTheme2);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        this.setActionbarShadow(true);
        this.getCustomActionBar().displayUpIndicator();
        this.setTitle(this.getClass().getSimpleName().replace("Activity", ""));
        findViews();
    }

    public void findViews() {
        this.findViewById(R.id.button_1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                light = true;
                recreate();
            }
        });
        this.findViewById(R.id.button_2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                light = false;
                recreate();
            }
        });
        ActionTab actionTab = this.getCustomActionBar().getActionTab();
        actionTab.newTab(1, "推荐", 1);
        actionTab.newTab(2, "关注", 0);
    }

    @Override
    public void onMenuActionCreated(ActionMenu actionMenu) {
        super.onMenuActionCreated(actionMenu);
        Log.d(">>", "addMenu " + getAttrTypedValue(R.attr.actionbar_clear).resourceId);
        actionMenu.addMenu(1, R.string.action_delete, getAttrTypedValue(R.attr.actionbar_clear).resourceId, 1);
        actionMenu.addMenu(2, R.string.action_selectAll, R.drawable.ic_action_select, 1);
        actionMenu.addMenu(3, R.string.action_invert, R.drawable.ic_action_unselect, 0);
    }
}
