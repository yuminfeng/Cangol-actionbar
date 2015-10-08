package mobi.cangol.mobile.actionbar;

import android.view.View.OnClickListener;

import java.util.ArrayList;
/**
 * @author Cangol
 */
import mobi.cangol.mobile.actionbar.view.SearchView;

public abstract class ActionBar {
    /**
     * 设置自定义的home和up
     *
     * @param homeId
     * @param upId
     */
    abstract public void setCustomHomeAsUpIndicator(int homeId, int upId);

    /**
     * 设置home的显示
     *
     * @param show
     */
    abstract public void setDisplayShowHomeEnabled(boolean show);

    /**
     * 显示home指示器
     */
    abstract public void displayHomeIndicator();

    /**
     * 显示Up指示器
     */
    abstract public void displayUpIndicator();

    /**
     * 设置指示器显示的offset，只对原生指示器起作用
     *
     * @param slideOffset
     */
    abstract public void displayIndicator(float slideOffset);

    /**
     * 设置指示器显示的颜色，只对原生指示器起作用
     *
     * @param color
     */
    abstract public void setIndicatorColor(int color);

    /**
     * back时执行
     *
     * @return
     */
    abstract protected boolean onBackPressed();

    /**
     * 设置显示
     *
     * @param show
     */
    abstract protected void setShow(boolean show);

    /**
     * 返回是否显示
     *
     * @return
     */
    abstract public boolean isShow();

    /**
     * 设置背景颜色
     *
     * @param color
     */
    abstract public void setBackgroundColor(int color);

    /**
     * 设置背景
     *
     * @param resId
     */
    abstract public void setBackgroundResource(int resId);

    /**
     * 获取标题
     *
     * @return
     */
    abstract public String getTitle();

    /**
     * 设置标题
     *
     * @param title
     */
    abstract public void setTitle(CharSequence title);

    /**
     * 设置标题
     *
     * @param resid
     */
    abstract public void setTitle(int resid);

    /**
     * 设置标题对其方式
     *
     * @param gravity
     */
    abstract public void setTitleGravity(int gravity);

    /**
     * 设置标题显示
     *
     * @param visibly
     */
    abstract public void setTitleVisibility(int visibly);

    /**
     * 设置标题点击事件监听
     * 此事件与setOnNavigationListener相冲突，只有后设置的有效
     * @param listener
     */
    abstract public void setOnTitleClickListener(OnClickListener listener);

    /**
     * 启动ActionMode
     *
     * @param callback
     * @return
     */
    abstract public ActionMode startActionMode(ActionMode.Callback callback);

    /**
     * 停止ActionMode
     */
    abstract public void stopActionMode();

    /**
     * 开启进度模式
     */
    abstract public void startProgress();

    /**
     * 关闭进度模式
     */
    abstract public void stopProgress();

    /**
     * 开启搜索模式，与stopSearchMode成对使用
     *
     * @return
     */
    abstract public SearchView startSearchMode();

    /**
     * 停止搜索模式
     */
    abstract public void stopSearchMode();

    /**
     * 设置导航菜单
     * @param navs
     */
    abstract public void setListNavigation(String[] navs);
    /**
     * 设置导航菜单
     * 此事件与setOnTitleClickListener相冲突，只有后设置的有效
     * @param onNavigationListener 导航监听
     */
    abstract public void setOnNavigationListener(OnNavigationListener onNavigationListener);

    /**
     * 获取导航菜单
     */
    abstract public String[] getListNavigation();

    /**
     * 清除导航菜单
     */
    abstract public void clearListNavigation();


    /**
     * 获取actionMenu
     *
     * @return
     */
    abstract public ActionMenu getActionMenu();

    /**
     * 添加 菜单按钮
     *
     * @param actions
     */
    abstract public void addMenus(ArrayList<ActionMenuItem> actions);

    /**
     * 获取所有菜单按钮
     *
     * @return
     */
    abstract public ArrayList<ActionMenuItem> getMenus();

    /**
     * 清除所有菜单按钮
     */
    abstract public void clearActionMenus();

    /**
     * 创建ActionTab
     *
     * @return
     */
    abstract public ActionTab getActionTab();

    /**
     * 添加在标题栏添加tabItem
     *
     * @param tabs
     */
    abstract public void addTabs(ArrayList<ActionTabItem> tabs);

    /**
     * 获取所有tabItem
     *
     * @return
     */
    abstract public ArrayList<ActionTabItem> getTabs();

    /**
     * 清除所有tabItem
     */
    abstract public void clearActionTabs();


}
