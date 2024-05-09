
package mobi.cangol.mobile.actionbar.demo;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import mobi.cangol.mobile.actionbar.ActionBarActivity;
import mobi.cangol.mobile.actionbar.ActionMenu;
import mobi.cangol.mobile.actionbar.ActionMenuItem;

public class RefreshActivity extends ActionBarActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_view);
        this.getCustomActionBar().displayUpIndicator();
        this.setTitle(this.getClass().getSimpleName().replace("Activity",""));
        findViews();
        getCustomActionBar().setLeftMenu(0x11, R.string.action_setting, R.drawable.actionbar_clear_dark, new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    @Override
    public void onMenuActionCreated(ActionMenu actionMenu) {
        super.onMenuActionCreated(actionMenu);
        actionMenu.addMenu(1, R.string.action_delete, -1, 1);
//        actionMenu.addMenu(2, R.string.action_selectAll, R.drawable.ic_action_select, 1);
//        actionMenu.addMenu(3, R.string.action_invert, R.drawable.ic_action_unselect, 0);
    }

    @Override
    public boolean onMenuActionSelected(ActionMenuItem action) {
        switch (action.getId()) {
            case 1:
                showToast(R.string.action_delete);
                break;
            case 2:
                showToast(R.string.action_selectAll);
                break;
            case 3:
                showToast(R.string.action_invert);
                break;
        }
        return super.onMenuActionSelected(action);
    }
    public void findViews() {
        this.findViewById(R.id.button_refresh_00).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getCustomActionBar().enableRefresh(true, Gravity.LEFT);
            }
        });
        this.findViewById(R.id.button_refresh_01).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getCustomActionBar().enableRefresh(false, Gravity.LEFT);
            }
        });
        this.findViewById(R.id.button_refresh_10).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getCustomActionBar().enableRefresh(true, Gravity.RIGHT);
            }
        });
        this.findViewById(R.id.button_refresh_11).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getCustomActionBar().enableRefresh(false, Gravity.RIGHT);
            }
        });
        this.findViewById(R.id.button_refresh_2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getCustomActionBar().refreshing(true);
            }
        });
        this.findViewById(R.id.button_refresh_3).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getCustomActionBar().refreshing(false);
            }
        });
        getCustomActionBar().setOnRefreshClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("refreshing");
            }
        });
    }
    private void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    private void showToast(int id) {
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
    }
}
