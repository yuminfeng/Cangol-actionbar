
package mobi.cangol.mobile.actionbar.demo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import mobi.cangol.mobile.actionbar.ActionBarActivity;
import mobi.cangol.mobile.actionbar.ActionMenu;
import mobi.cangol.mobile.actionbar.ActionMenuItem;

@Deprecated
public class MenuActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_view);
        this.getCustomActionBar().displayUpIndicator();
        this.getCustomActionBar().setArrowDrawableThickness(5f);
        this.setTitle(this.getClass().getSimpleName().replace("Activity", ""));
        findViews();
    }

    public void findViews() {
        this.findViewById(R.id.button_menu_view_1).setOnClickListener(new OnClickListener() {
            boolean fullScreen;

            @Override
            public void onClick(View v) {
                if (!fullScreen) {
                    setFullScreen(true);
                    fullScreen = true;
                } else {
                    setFullScreen(false);
                    fullScreen = false;
                }

            }
        });
        this.findViewById(R.id.button_menu_view_2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setActionbarShow(!isActionbarShow());
            }
        });

        this.findViewById(R.id.button_menu_view_4).setOnClickListener(new OnClickListener() {
            boolean displayHome = true;

            @Override
            public void onClick(View v) {
                if (!displayHome) {
                    getCustomActionBar().setDisplayShowHomeEnabled(true);
                    displayHome = true;
                } else {
                    getCustomActionBar().setDisplayShowHomeEnabled(false);
                    displayHome = false;
                }
            }
        });
        this.findViewById(R.id.button_menu_view_6).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setWindowBackground(R.color.red);
            }
        });
        this.findViewById(R.id.button_menu_view_7).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackgroundResource(R.color.blue);
            }
        });
        this.findViewById(R.id.button_menu_view_8).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setStatusBarTintColor(getResources().getColor(R.color.blue));
            }
        });
        this.findViewById(R.id.button_menu_view_9).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setNavigationBarTintColor(getResources().getColor(R.color.red));
            }
        });
        this.findViewById(R.id.button_menu_view_10).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getCustomActionBar().setBackgroundResource(R.color.blue);
            }
        });

        this.findViewById(R.id.button_menu_view_11).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getCustomActionBar().setLeftMenu(0x11, R.string.action_setting, -1, new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e(">>", "click me");
                        showToast("click me");
                    }
                });
            }
        });
    }

    @Override
    public void onMenuActionCreated(ActionMenu actionMenu) {
        super.onMenuActionCreated(actionMenu);
        actionMenu.addMenu(1, R.string.action_delete, -1, 1);
        actionMenu.addMenu(2, R.string.action_selectAll, R.drawable.ic_action_select, 1);
        actionMenu.addMenu(3, R.string.action_invert, R.drawable.ic_action_unselect, 1);
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

    private void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    private void showToast(int id) {
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
    }
}
