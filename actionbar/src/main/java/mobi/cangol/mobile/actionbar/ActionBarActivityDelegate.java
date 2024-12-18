package mobi.cangol.mobile.actionbar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

import java.util.ArrayList;

import mobi.cangol.mobile.actionbar.internal.ActionBarImpl;

/**
 * @author Cangol
 */
public class ActionBarActivityDelegate {

    private final ActionBarActivity mActivity;
    private ViewGroup mContainerView;
    private View mRootView;
    private ActionBar mActionBar;
    private FrameLayout mContentView;
    private boolean mActionbarOverlay = false;

    public ActionBarActivityDelegate(ActionBarActivity activity) {
        mActivity = activity;
    }

    protected void onCreate(Bundle savedInstanceState) {
        mContainerView = (ViewGroup) LayoutInflater.from(mActivity).inflate(R.layout.actionbar_activity_main, null);
        mRootView = mContainerView.findViewById(R.id.container_view);
        mContentView = mContainerView.findViewById(R.id.actionbar_content_view);
        mActionBar = new ActionBarImpl(mContainerView.findViewById(R.id.actionbar_view));
    }

    public View getRootView() {
        return mRootView;
    }

    public boolean isActionbarOverlay() {
        return mActionbarOverlay;
    }

    public void setActionbarOverlay(boolean mActionbarOverlay) {
        this.mActionbarOverlay = mActionbarOverlay;
        if (mActionbarOverlay) {
            mContentView.setPadding(0, 0, 0, 0);
        } else {
            mContentView.setPadding(0, (mActivity.getResources().getDimensionPixelSize(R.dimen.actionbar_height)), 0, 0);
        }
    }

    public ActionBar getCustomActionBar() {
        return mActionBar;
    }

    public void setBackgroundColor(int color) {
        mContainerView.setBackgroundColor(color);
    }

    public void setBackgroundResource(int resId) {
        mContainerView.setBackgroundResource(resId);
    }

    protected void onPostCreate(Bundle savedInstanceState) {
        this.attachToActivity(mActivity, mContainerView);
        if (savedInstanceState != null) {
            CharSequence title = savedInstanceState.getCharSequence("ActionBar.title");
            mActionBar.setTitle(title);

            String[] navs = savedInstanceState.getStringArray("ActionBar.navs");
            mActionBar.clearListNavigation();
            mActionBar.setListNavigation(navs);

            ArrayList<ActionTabItem> tabs = savedInstanceState.getParcelableArrayList("ActionBar.tabs");
            mActionBar.clearActionTabs();
            mActionBar.setTabs(tabs);
            mActionBar.getActionTab().setTabSelected(savedInstanceState.getInt("ActionBar.tabs.selected"));
        }
        mActionBar.clearActionMenus();
        mActivity.onMenuActionCreated(mActionBar.getActionMenu());


        if (!mActionBar.getTabs().isEmpty()) {
            mActionBar.setTitleVisibility(View.GONE);
        } else {
            mActionBar.setTitleVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("ResourceType")
    private void attachToActivity(Activity activity, View layout) {
        // get the window background
        int background = 0;
        int type = 0;
        {
            TypedArray a = activity.getTheme().obtainStyledAttributes(new int[]{android.R.attr.windowBackground});
            type = a.getType(0);
            if (type == TypedValue.TYPE_INT_COLOR_RGB8) {
                background = a.getColor(0, 0);
            } else {
                background = a.getResourceId(0, 0);
            }
            a.recycle();
        }

        ViewGroup decor = (ViewGroup) activity.getWindow().getDecorView();
        ViewGroup decorChild = (ViewGroup) decor.getChildAt(0);
        if (decor.getBackground() == null) {
            if (type == TypedValue.TYPE_INT_COLOR_RGB8) {
                decor.setBackgroundColor(background);
            } else {
                decor.setBackgroundResource(background);
            }
        }
        if (decorChild.getBackground() != null) {
            mContainerView.setBackground(decorChild.getBackground());
            decorChild.setBackground(null);
        }
        decor.removeView(decorChild);
        decor.addView(layout, 0, decorChild.getLayoutParams());

        decorChild.setFitsSystemWindows(false);
        View view = decorChild.findViewById(android.R.id.content);
        ViewGroup parent = (ViewGroup) view.getParent();
        parent.removeView(view);
        setContentView(view);
    }

    public void setContent(View view) {
        setContentView(view);
    }

    public void setContentView(View v) {
        mContentView.removeAllViews();
        mContentView.addView(v);
    }

    public void setContentView(View v, LayoutParams params) {
        mContentView.removeAllViews();
        mContentView.addView(v, params);
    }

    public View findViewById(int id) {
        View v;
        if (mContainerView != null) {
            v = mContainerView.findViewById(id);
            return v;
        }
        return null;
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.isTracking() && !event.isCanceled()) {
            return mActionBar.onBackPressed();
        }
        return false;
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putCharSequence("ActionBar.title", mActionBar.getTitle());
        outState.putStringArray("ActionBar.navs", mActionBar.getListNavigation());
        outState.putParcelableArrayList("ActionBar.menus", (ArrayList<? extends Parcelable>) mActionBar.getMenus());
        outState.putParcelableArrayList("ActionBar.tabs", (ArrayList<? extends Parcelable>) mActionBar.getTabs());
        outState.putInt("ActionBar.tabs.selected", mActionBar.getActionTab().getTabSelected());
    }

    public void setTitle(int titleId) {
        mActionBar.setTitle(titleId);
    }

    public void setTitle(CharSequence title) {
        mActionBar.setTitle(title);
    }

    public boolean isActionbarShow() {
        return mActionBar.isShow();
    }

    public void setActionbarShow(boolean show) {
        mActionBar.setShow(show);
    }

}
