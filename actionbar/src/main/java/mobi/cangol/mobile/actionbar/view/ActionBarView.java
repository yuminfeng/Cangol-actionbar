package mobi.cangol.mobile.actionbar.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;

import java.util.List;

import mobi.cangol.mobile.actionbar.ActionBarActivity;
import mobi.cangol.mobile.actionbar.ActionMenu;
import mobi.cangol.mobile.actionbar.ActionMenuItem;
import mobi.cangol.mobile.actionbar.ActionTab;
import mobi.cangol.mobile.actionbar.ActionTabItem;
import mobi.cangol.mobile.actionbar.OnNavigationListener;
import mobi.cangol.mobile.actionbar.R;
import mobi.cangol.mobile.actionbar.internal.ActionMenuImpl;
import mobi.cangol.mobile.actionbar.internal.ActionTabImpl;

/**
 * @author Cangol
 */
public class ActionBarView extends RelativeLayout {
    public static final String TAG = "ActionBar";
    private LayoutInflater mInflater;
    private LinearLayout mLeftMenuLayout;
    private ImageView mIndicator;
    private LinearLayout mTitleLayout;
    private TextView mTitleView;
    private PopupWindow mPopupMenu;
    private ImageView mRefreshView;
    private ActionMenu mActionMenu;
    private ActionTab mActionTab;
    private FrameLayout mCustomLayout;
    private ActionBarActivity mActionBarActivity;
    private DrawerArrowDrawable mDrawerArrowDrawable;
    private boolean mIsCustomHomeAsUpIndicator;
    private boolean mDisplayShowHomeEnabled;
    private int mHomeId;
    private int mUpId;
    private String[] mListNavigation;
    private OnNavigationListener mOnNavigationListener;
    private OnClickListener mOnRefreshClickListener;

    public ActionBarView(Context context) {
        super(context);
        initView(context);
    }

    public ActionBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public ActionBarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    public ActionBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public void initView(Context context) {
        mActionBarActivity = (ActionBarActivity) context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mDrawerArrowDrawable = new DrawerArrowDrawable(context);

        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.actionbar_indicator, typedValue, true);
        mDrawerArrowDrawable.setColor(typedValue.data);


        mInflater.inflate(R.layout.actionbar_layout, this, true);
        mLeftMenuLayout = this.findViewById(R.id.actionbar_left_menus);
        mIndicator = this.findViewById(R.id.actionbar_main_indicator);
        mTitleLayout = this.findViewById(R.id.actionbar_main_title_layout);
        mTitleView = this.findViewById(R.id.actionbar_main_title);
        mCustomLayout = this.findViewById(R.id.actionbar_main_custom_layout);
        mActionTab = new ActionTabImpl(this.findViewById(R.id.actionbar_main_tabview));
        mActionMenu = new ActionMenuImpl(this.findViewById(R.id.actionbar_main_menu));
//        setTitle(context.getApplicationInfo().name);
        initListeners();
    }

    private void initListeners() {
        if (!mIsCustomHomeAsUpIndicator)
            mDrawerArrowDrawable.setProgress(0);
        mIndicator.setImageDrawable(mDrawerArrowDrawable);
        mIndicator.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mActionBarActivity != null)
                    mActionBarActivity.onSupportNavigateUp();
            }

        });
        mActionMenu.setOnActionClickListener(new ActionMenuView.OnActionClickListener() {

            @Override
            public boolean onActionClick(ActionMenuItem action) {
                if (mActionBarActivity != null) {
                    return mActionBarActivity.onMenuActionSelected(action);
                }
                return false;
            }

        });
    }

    public void setArrowDrawableThickness(float width) {
        mDrawerArrowDrawable.setBarThickness(width);
    }

    public String[] getListNavigation() {
        return mListNavigation;
    }

    public void setListNavigation(final String[] listNavigation) {
        this.mListNavigation = listNavigation;
    }

    public void clearListNavigation() {
        mTitleView.setCompoundDrawables(null, null, null, null);
        mTitleView.setOnClickListener(null);
        this.mOnNavigationListener = null;
    }

    public void setOnNavigationListener(final OnNavigationListener onNavigationListener) {
        this.mOnNavigationListener = onNavigationListener;
        BaseAdapter adapter = new BaseAdapter() {

            @Override
            public int getCount() {
                return mListNavigation.length;
            }

            @Override
            public String getItem(int position) {
                return mListNavigation[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder;
                if (convertView == null) {
                    convertView = mInflater.inflate(R.layout.actionbar_navigation_list_item, parent, false);
                    holder = new ViewHolder();
                    holder.labelView = convertView.findViewById(R.id.actionbar_navigation_item_text);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                holder.labelView.setText(mListNavigation[position]);
                return convertView;
            }

            class ViewHolder {
                private TextView labelView;
            }

        };
        initNavigationPopupMenu(mActionBarActivity, adapter, mOnNavigationListener);
    }

    private void initNavigationPopupMenu(final Context context, BaseAdapter adapter, final OnNavigationListener onNavigationListener) {
        final View popuLayout = mInflater.inflate(R.layout.actionbar_navigation_list, null);
        ListView listView = popuLayout.findViewById(R.id.actionbar_popup_navigation_list);
        listView.setAdapter(adapter);
        final int width = (int) (200 * context.getResources().getDisplayMetrics().density);
        mPopupMenu = new PopupWindow(popuLayout, width, LayoutParams.WRAP_CONTENT, true);
        mPopupMenu.setBackgroundDrawable(new BitmapDrawable());
        mPopupMenu.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss() {
                TypedValue typedValue = new TypedValue();
                context.getTheme().resolveAttribute(R.attr.actionbar_dropdown, typedValue, true);
                Drawable imgV = getResources().getDrawable(typedValue.resourceId);
                imgV.setBounds(0, 0, imgV.getIntrinsicWidth(), imgV.getIntrinsicHeight());
                mTitleView.setCompoundDrawables(null, null, imgV, null);
            }

        });
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (onNavigationListener != null)
                    onNavigationListener.onNavigationItemSelected(position, id);
                mPopupMenu.dismiss();
            }

        });
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.actionbar_dropdown, typedValue, true);
        Drawable imgV = getResources().getDrawable(typedValue.resourceId);
        imgV.setBounds(0, 0, imgV.getIntrinsicWidth(), imgV.getIntrinsicHeight());
        mTitleView.setCompoundDrawables(null, null, imgV, null);
        mTitleView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!mPopupMenu.isShowing()) {
                    TypedValue typedValue = new TypedValue();
                    context.getTheme().resolveAttribute(R.attr.actionbar_dropup, typedValue, true);
                    Drawable imgV = getResources().getDrawable(typedValue.resourceId);
                    imgV.setBounds(0, 0, imgV.getIntrinsicWidth(), imgV.getIntrinsicHeight());
                    mTitleView.setCompoundDrawables(null, null, imgV, null);

                    int xoff = -(width - mTitleView.getWidth()) / 2;
                    if (mTitleView.getGravity() == Gravity.CENTER) {
                        mPopupMenu.showAsDropDown(mTitleView, xoff, 0);
                    } else {
                        mPopupMenu.showAsDropDown(mTitleView, 0, 0);
                    }
                }
            }

        });
    }

    public void setCustomHomeAsUpIndicator(int homeId, int upId) {
        mIsCustomHomeAsUpIndicator = true;
        mHomeId = homeId;
        mUpId = upId;
        mIndicator.setImageResource(homeId);
    }

    public void resetCustomHomeAsUpIndicator() {
        mIsCustomHomeAsUpIndicator = false;
        mHomeId = 0;
        mUpId = 0;
        mDrawerArrowDrawable.setProgress(0);
        mIndicator.setImageDrawable(mDrawerArrowDrawable);
    }

    public void setDisplayShowHomeEnabled(boolean show) {
        mDisplayShowHomeEnabled = show;
        mIndicator.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void displayHomeIndicator() {
        if (!mIsCustomHomeAsUpIndicator) {
            mDrawerArrowDrawable.setProgress(0);
            mIndicator.setImageDrawable(mDrawerArrowDrawable);
        } else {
            mIndicator.setImageResource(mHomeId);
        }
        mIndicator.setVisibility(mDisplayShowHomeEnabled ? View.VISIBLE : View.GONE);
    }

    public void hideHomeAsUpIndicator() {
        mIndicator.setVisibility(View.GONE);
    }

    public void displayUpIndicator() {
        if (!mIsCustomHomeAsUpIndicator) {
            mDrawerArrowDrawable.setProgress(1);
            mIndicator.setImageDrawable(mDrawerArrowDrawable);
        } else {
            mIndicator.setImageResource(mUpId);
        }
        mIndicator.setVisibility(View.VISIBLE);
    }

    public void displayIndicator(float slideOffset) {
        if (!mIsCustomHomeAsUpIndicator) {
            if (slideOffset >= .995) {
                mDrawerArrowDrawable.setFilterBitmap(true);
            } else if (slideOffset <= .005) {
                mDrawerArrowDrawable.setFilterBitmap(false);
            }
            mDrawerArrowDrawable.setProgress(slideOffset);
            mIndicator.setImageDrawable(mDrawerArrowDrawable);
        } else {
            if (slideOffset >= .995) {
                mIndicator.setImageResource(mUpId);
            } else if (slideOffset <= .005) {
                mIndicator.setImageResource(mHomeId);
            }
        }
        mIndicator.setVisibility(View.VISIBLE);
    }

    public void setIndicatorColor(int color) {
        if (!mIsCustomHomeAsUpIndicator)
            mDrawerArrowDrawable.setColor(color);
    }

    public void setLeftMenu(final int id, final int text, int drawable, OnClickListener listener) {
        mLeftMenuLayout.setVisibility(View.VISIBLE);
        mLeftMenuLayout.removeAllViews();
        RelativeLayout.LayoutParams layoutParams = (LayoutParams) mLeftMenuLayout.getLayoutParams();
        if (mIndicator.getVisibility() == VISIBLE) {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
            layoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.actionbar_main_indicator);
        } else if (mRefreshView != null && mRefreshView.getId() == R.id.actionbar_main_refresh_left && mRefreshView.getVisibility() == VISIBLE) {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
            layoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.actionbar_main_refresh_left);
        } else {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 1);
        }

        if (drawable != -1) {
            final View view = mInflater.inflate(R.layout.actionbar_item_icon, null, false);

            ImageView labelView = view.findViewById(R.id.actionbar_item);
            labelView.setImageResource(drawable);
            view.setId(id);
            view.setTag(getContext().getString(text));
            labelView.setOnClickListener(listener);
            labelView.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast toast = null;
                    if (text != -1) {
                        toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.LEFT | Gravity.TOP, view.getLeft(), view.getBottom());
                        toast.show();
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            mLeftMenuLayout.addView(view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        } else {
            final View view = mInflater.inflate(R.layout.actionbar_item_text, null, false);

            TextView labelView = view.findViewById(R.id.actionbar_item);
            labelView.setText(text);
            view.setId(id);
            view.setTag(getContext().getString(text));
            labelView.setOnClickListener(listener);
            labelView.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast toast = null;
                    if (text != -1) {
                        toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.LEFT | Gravity.TOP, view.getLeft(), view.getBottom());
                        toast.show();
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            mLeftMenuLayout.addView(view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }


    }

    public void clearLeftMenu() {
        if (mLeftMenuLayout.getChildAt(0) != null)
            mLeftMenuLayout.getChildAt(0).setOnClickListener(null);
        mLeftMenuLayout.removeAllViews();
        mLeftMenuLayout.setVisibility(View.GONE);
    }

    public CharSequence getTitle() {
        return mTitleView.getText();
    }

    public void setTitle(int resId) {
        mTitleView.setText(resId);
    }

    public void setTitle(CharSequence title) {
        mTitleView.setText(title);
    }

    public void setTitleGravity(int gravity) {
        mTitleLayout.setGravity(gravity);
        mTitleView.setGravity(gravity);
    }

    public int getTitleGravity() {
        return mTitleView.getVisibility();
    }


    public void setTitleColor(int color) {
        mTitleView.setTextColor(color);
    }

    public void setTitleSize(float size) {
        mTitleView.setTextSize(size);
    }

    public void setOnTitleClickListener(OnClickListener listener) {
        mTitleView.setOnClickListener(listener);
    }

    public ActionMenu getActionMenu() {
        return mActionMenu;
    }

    public void clearActions() {
        mActionMenu.clear();
    }

    public List<ActionMenuItem> getActions() {
        return mActionMenu.getActions();
    }

    public void setActions(List<ActionMenuItem> actions) {
        mActionMenu.setActions(actions);
    }

    public ActionTab getActionTab() {
        return mActionTab;
    }

    public void clearActionTabs() {
        mActionTab.removeAllTabs();
        setTitleVisibility(View.VISIBLE);
    }

    public List<ActionTabItem> getTabs() {
        return mActionTab.getTabs();
    }

    public void setTabs(List<ActionTabItem> tabs) {
        setTitleVisibility(View.GONE);
        mActionTab.setTabs(tabs);
    }

    public boolean onBackPressed() {
        return false;
    }

    public void setTitleVisibility(int visibility) {
        mTitleLayout.setVisibility(visibility);
    }

    public int getTitleVisibility() {
        return mTitleLayout.getVisibility();
    }

    public void setCustomView(View view) {
        mCustomLayout.removeAllViews();
        mCustomLayout.addView(view);
        mTitleLayout.setVisibility(View.GONE);
        mActionTab.removeAllTabs();
    }

    public void removeCustomView() {
        mCustomLayout.removeAllViews();
        mTitleLayout.setVisibility(View.VISIBLE);
    }

    public void enableRefresh(boolean enable) {
        enableRefresh(enable, Gravity.RIGHT);
    }

    public void enableRefresh(boolean enable, int gravity) {
        if (mRefreshView != null) {
            mRefreshView.clearAnimation();
            mRefreshView.setVisibility(View.GONE);
            mRefreshView.setOnClickListener(null);
            if (mRefreshView.getId() == R.id.actionbar_main_refresh_left) {
                View view = findViewById(R.id.actionbar_left_menus);
                RelativeLayout.LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                if (mIndicator.getVisibility() == VISIBLE) {
                    layoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.actionbar_main_indicator);
                } else {
                    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 1);
                }
            } else {
                View view = findViewById(R.id.actionbar_main_menu);
                RelativeLayout.LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 1);
            }
            mRefreshView = null;
        }
        mRefreshView = this.findViewById(gravity == Gravity.LEFT ? R.id.actionbar_main_refresh_left : R.id.actionbar_main_refresh_right);
        View view = findViewById(gravity == Gravity.LEFT ? R.id.actionbar_left_menus : R.id.actionbar_main_menu);
        RelativeLayout.LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (enable) {
            if (gravity == Gravity.LEFT) {
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
                layoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.actionbar_main_refresh_left);
            } else {
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
            }
            mRefreshView.setVisibility(View.VISIBLE);
            mRefreshView.setOnClickListener(mOnRefreshClickListener);
        } else {
            if (gravity == Gravity.LEFT) {
                if (mIndicator.getVisibility() == VISIBLE) {
                    layoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.actionbar_main_indicator);
                } else {
                    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 1);
                }
            } else {
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 1);
            }

            mRefreshView.clearAnimation();
            mRefreshView.setVisibility(View.GONE);
            mRefreshView.setOnClickListener(null);
            mOnRefreshClickListener = null;
            mRefreshView = null;
        }

    }

    public void setOnRefreshClickListener(OnClickListener listener) {
        mOnRefreshClickListener = listener;
        if (mRefreshView != null)
            mRefreshView.setOnClickListener(mOnRefreshClickListener);
    }

    public void refreshing(boolean refresh) {
        if (mRefreshView != null && mRefreshView.getVisibility() == VISIBLE) {
            RotateAnimation anim = new RotateAnimation(0.0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setInterpolator(new LinearInterpolator());
            anim.setRepeatCount(Animation.INFINITE);
            anim.setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
            if (refresh) {
                mRefreshView.startAnimation(anim);
            } else {
                mRefreshView.clearAnimation();
            }
        }
    }

}