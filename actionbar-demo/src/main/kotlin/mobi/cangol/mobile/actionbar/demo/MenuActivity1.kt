package mobi.cangol.mobile.actionbar.demo

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import mobi.cangol.mobile.actionbar.ActionBarActivity
import mobi.cangol.mobile.actionbar.ActionMenu
import mobi.cangol.mobile.actionbar.ActionMenuItem

class MenuActivity1 : ActionBarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_view)
        customActionBar.displayUpIndicator()
        customActionBar.setArrowDrawableThickness(5f)
        title = this@MenuActivity1.javaClass.simpleName.replace("Activity", "")
        initViews()
    }

    private fun initViews() {

        findViewById(R.id.button_menu_view_1).setOnClickListener(object : OnClickListener {
            var fullScreen = false
            override fun onClick(v: View) {
                if (!fullScreen) {
                    isFullScreen = true
                    fullScreen = true
                } else {
                    isFullScreen = false
                    fullScreen = false
                }
            }
        })

        findViewById(R.id.button_menu_view_2).setOnClickListener {
            isActionbarShow = !isActionbarShow
        }

        findViewById(R.id.button_menu_view_4).setOnClickListener(object : OnClickListener {
            var displayHome = true
            override fun onClick(v: View?) {
                if (!displayHome)
                    customActionBar.setDisplayShowHomeEnabled(true)
                else
                    customActionBar.setDisplayShowHomeEnabled(false)
                displayHome = !displayHome
            }
        })

        findViewById(R.id.button_menu_view_6).setOnClickListener { setWindowBackground(R.color.red) }

        findViewById(R.id.button_menu_view_7).setOnClickListener { setBackgroundResource(R.color.blue) }

        findViewById(R.id.button_menu_view_8).setOnClickListener {
            setStatusBarTintColor(resources.getColor(R.color.blue))
        }
        findViewById(R.id.button_menu_view_9).setOnClickListener {
            setNavigationBarTintColor(resources.getColor(R.color.red))
        }

        findViewById(R.id.button_menu_view_10).setOnClickListener {
            customActionBar.setBackgroundResource(R.color.blue)
        }
        findViewById(R.id.button_menu_view_11).setOnClickListener {
            customActionBar.setLeftMenu(0x11, R.string.action_setting, -1) {
                showToast("click me")
            }
        }
    }

    override fun onMenuActionCreated(actionMenu: ActionMenu) {
        super.onMenuActionCreated(actionMenu)
        actionMenu.addMenu(1, R.string.action_delete, -1, 1)
        actionMenu.addMenu(2, R.string.action_selectAll, R.drawable.ic_action_select, 1)
        actionMenu.addMenu(3, R.string.action_invert, R.drawable.ic_action_unselect, 1)
    }

    override fun onMenuActionSelected(actionMenu: ActionMenuItem): Boolean {
        when (actionMenu.id) {
            1 -> showToast(R.string.action_delete)
            2 -> showToast(R.string.action_selectAll)
            3 -> showToast(R.string.action_invert)
        }
        return super.onMenuActionSelected(actionMenu)
    }

    private fun showToast(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }

    private fun showToast(id: Int) {
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show()
    }
}