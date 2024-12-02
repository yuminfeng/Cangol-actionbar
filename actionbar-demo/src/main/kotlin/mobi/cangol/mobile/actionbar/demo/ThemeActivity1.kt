package mobi.cangol.mobile.actionbar.demo

import android.os.Bundle
import android.util.Log
import mobi.cangol.mobile.actionbar.ActionBarActivity
import mobi.cangol.mobile.actionbar.ActionMenu

private var light: Boolean = true //类似java中 static 中的作用

class ThemeActivity1 : ActionBarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("ThemeActivity", "light==$light")
        if (light) setTheme(R.style.AppTheme1)
        else setTheme(R.style.AppTheme2)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme)
        customActionBar.displayUpIndicator()
        title = localClassName.replace("Activity", "")
        initViews()
    }

    private fun initViews() {
        findViewById(R.id.button_1).setOnClickListener {
            light = true
            recreate()
        }
        findViewById(R.id.button_2).setOnClickListener {
            light = false
            recreate()
        }
        val actionTab = customActionBar.actionTab
        actionTab.newTab(1, "推荐", 1)
        actionTab.newTab(2, "关注", 0)
    }

    override fun onMenuActionCreated(actionMenu: ActionMenu) {
        super.onMenuActionCreated(actionMenu)
        actionMenu.addMenu(
            1, R.string.action_delete, getAttrTypedValue(R.attr.actionbar_clear).resourceId, 1
        )
        actionMenu.addMenu(2, R.string.action_selectAll, R.drawable.ic_action_select, 1)
        actionMenu.addMenu(3, R.string.action_invert, R.drawable.ic_action_unselect, 0)
    }
}