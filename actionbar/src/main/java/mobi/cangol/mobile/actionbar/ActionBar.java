package mobi.cangol.mobile.actionbar;

import android.view.View;
import android.view.View.OnClickListener;

import androidx.annotation.IdRes;

import java.util.List;

/**
 * @author Cangol
 */

public abstract class ActionBar {
    /**
     * 设置自定义的home和up
     *
     * @param homeId home资源id
     * @param upId   up资源id
     */
    public abstract void setCustomHomeAsUpIndicator(int homeId, int upId);

    /**
     *
     */
    public abstract void resetCustomHomeAsUpIndicator();

    /**
     * 设置home的显示
     *
     * @param show 是否显示
     */
    public abstract void setDisplayShowHomeEnabled(boolean show);


    public abstract void hideHomeAsUpIndicator();

    /**
     * 显示home指示器
     */
    public abstract void displayHomeIndicator();

    /**
     * 显示Up指示器
     */
    public abstract void displayUpIndicator();

    /**
     * 设置指示器显示的offset，只对原生指示器起作用
     *
     * @param slideOffset 指示器的offset
     */
    public abstract void displayIndicator(float slideOffset);

    /**
     * 设置指示器显示的颜色，只对原生指示器起作用
     *
     * @param color 颜色
     */
    public abstract void setIndicatorColor(int color);

    /**
     * 设置左侧按钮
     *
     * @param id       菜单唯一Id
     * @param text     文本资源string的id，必须有效
     * @param icon     图片资源的id，如果无，请填-1
     * @param listener 点击事件listener
     */
    public abstract void setLeftMenu(final int id, final int text, int icon, OnClickListener listener);

    /**
     * 清除左侧按钮
     */
    public abstract void clearLeftMenu();

    /**
     * back时执行
     *
     * @return 是否执行
     */
    protected abstract boolean onBackPressed();

    /**
     * 返回是否显示
     *
     * @return 是否显示
     */
    protected abstract boolean isShow();

    /**
     * 设置显示
     *
     * @param show 是否显示
     */
    protected abstract void setShow(boolean show);

    /**
     * 设置背景颜色
     *
     * @param color 颜色
     */
    public abstract void setBackgroundColor(int color);

    /**
     * 设置背景
     *
     * @param resId 背景资源id
     */
    public abstract void setBackgroundResource(int resId);

    /**
     * 修改箭头线的宽度 默认7
     */
    public abstract void setArrowDrawableThickness(float width);

    /**
     * 获取标题
     *
     * @return 标题
     */
    public abstract CharSequence getTitle();

    /**
     * 设置标题
     *
     * @param resId 标题资源id
     */
    public abstract void setTitle(int resId);

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public abstract void setTitle(CharSequence title);

    /**
     * 设置标题对其方式
     *
     * @param gravity 对其方式
     */
    public abstract void setTitleGravity(int gravity);

    /**
     * 设置标题Color
     *
     * @param color 颜色
     */
    public abstract void setTitleColor(int color);

    /**
     * 设置标题size
     *
     * @param size 字体大小
     */
    public abstract void setTitleSize(float size);

    /**
     * 获取标题对其方式
     *
     * @return 对其方式
     */
    public abstract int getTitleGravity();

    /**
     * 设置标题显示
     *
     * @param visibly 显示状态
     */
    public abstract void setTitleVisibility(int visibly);

    /**
     * 获取标题显示
     *
     * @return 显示状态
     */
    public abstract int getTitleVisibility();

    /**
     * 设置标题点击事件监听
     * 此事件与setOnNavigationListener相冲突，只有后设置的有效
     *
     * @param listener 监听
     */
    public abstract void setOnTitleClickListener(OnClickListener listener);

    /**
     * 开启/关闭 刷新
     *
     * @param enable 是否刷新
     */
    public abstract void enableRefresh(boolean enable);


    /**
     * 开启/关闭 刷新left|right
     *
     * @param enable  是否刷新
     * @param gravity 位置
     */
    public abstract void enableRefresh(boolean enable, int gravity);

    /**
     * 开始/停止 刷新
     *
     * @param refresh 是否刷新
     */
    public abstract void refreshing(boolean refresh);

    /**
     * 刷新按钮点击事件
     *
     * @param listener 监听
     */
    public abstract void setOnRefreshClickListener(OnClickListener listener);

    /**
     * 设置导航菜单
     * 此事件与setOnTitleClickListener相冲r突，只有后设置的有效
     *
     * @param onNavigationListener 导航监听
     */
    public abstract void setOnNavigationListener(OnNavigationListener onNavigationListener);

    /**
     * 获取导航菜单
     *
     * @return 导航菜单list
     */
    public abstract String[] getListNavigation();

    /**
     * 设置导航菜单
     *
     * @param navs 导航菜单
     */
    public abstract void setListNavigation(String[] navs);

    /**
     * 清除导航菜单
     */
    public abstract void clearListNavigation();

    /**
     * 获取actionMenu
     *
     * @return ActionMenu
     */
    public abstract ActionMenu getActionMenu();

    /**
     * 获取所有菜单按钮
     *
     * @return 菜单列表
     */
    public abstract List<ActionMenuItem> getMenus();

    /**
     * 设置菜单按钮
     *
     * @param menus 菜单
     */
    protected abstract void setMenus(List<ActionMenuItem> menus);

    /**
     * 清除所有菜单按钮
     */
    public abstract void clearActionMenus();

    /**
     * 创建ActionTab
     *
     * @return ActionTab
     */
    public abstract ActionTab getActionTab();

    /**
     * 获取所有tabItem
     *
     * @return tabItem列表
     */
    public abstract List<ActionTabItem> getTabs();

    /**
     * 设置标题栏tabItems
     *
     * @param tabs tabItem列表
     */
    protected abstract void setTabs(List<ActionTabItem> tabs);

    /**
     * 清除所有tabItem
     */
    public abstract void clearActionTabs();

    /**
     * 添加自定义view
     * 背景透明，所占空间为[指示器右侧到menu左侧]
     * 此时title会被隐藏，tab会被移除
     *
     * @param view 自定义view
     */
    public abstract void setCustomView(View view);

    /**
     * 移除自定义view
     */
    public abstract void removeCustomView();


    /**
     * 获取view
     *
     * @param id  资源id
     * @param <T> view
     * @return view
     */
    public abstract <T extends View> T findViewById(@IdRes int id);
}
