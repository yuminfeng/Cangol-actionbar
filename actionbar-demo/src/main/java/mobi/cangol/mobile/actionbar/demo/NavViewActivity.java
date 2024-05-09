
package mobi.cangol.mobile.actionbar.demo;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import mobi.cangol.mobile.actionbar.ActionBarActivity;
import mobi.cangol.mobile.actionbar.OnNavigationListener;

public class NavViewActivity extends ActionBarActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_view);
        this.getCustomActionBar().displayUpIndicator();
        this.setTitle(this.getClass().getSimpleName().replace("Activity",""));
        findViews();
    }

    public void findViews() {
        this.findViewById(R.id.button_nav_1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                actionNav();
            }
        });
        this.findViewById(R.id.button_nav_2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getCustomActionBar().clearListNavigation();
            }
        });
    }
    public void actionNav() {
        final String[] navs = {"首页", "游戏", "壁纸", "资讯"};
        this.getCustomActionBar().setListNavigation(navs);
        this.getCustomActionBar().setOnNavigationListener(new OnNavigationListener() {

            @Override
            public boolean onNavigationItemSelected(int itemPosition,
                                                    long itemId) {
                Toast.makeText(NavViewActivity.this, "Navigation " + navs[itemPosition], Toast.LENGTH_SHORT).show();
                getCustomActionBar().setTitle(navs[itemPosition]);
                return true;
            }
        });
    }
}
