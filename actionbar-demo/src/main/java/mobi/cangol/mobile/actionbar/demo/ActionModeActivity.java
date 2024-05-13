
package mobi.cangol.mobile.actionbar.demo;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import mobi.cangol.mobile.actionbar.ActionBarActivity;
import mobi.cangol.mobile.actionbar.ActionMenu;
import mobi.cangol.mobile.actionbar.ActionMenuItem;
import mobi.cangol.mobile.actionbar.ActionMode;

@Deprecated
public class ActionModeActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_mode);
        this.getCustomActionBar().displayUpIndicator();
        this.setTitle(this.getClass().getSimpleName().replace("Activity", ""));
        findViews();
    }

    public void findViews() {
        this.findViewById(R.id.button_mode_1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startCustomActionMode(new ActionMode.Callback() {

                    @Override
                    public void onCreateActionMode(ActionMode mode,
                                                   ActionMenu actionMenu) {

                    }

                    @Override
                    public boolean onActionItemClicked(ActionMode mode,
                                                       ActionMenuItem menuItem) {
                        return false;
                    }

                    @Override
                    public void onDestroyActionMode(ActionMode mode) {

                    }

                });
            }
        });
        this.findViewById(R.id.button_mode_2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                stopCustomActionMode();
            }
        });
    }
}
