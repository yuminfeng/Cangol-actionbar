package mobi.cangol.mobile.actionbar.demo

import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import mobi.cangol.mobile.actionbar.ActionBarActivity
import mobi.cangol.mobile.actionbar.ActionMenu
import mobi.cangol.mobile.actionbar.ActionMenuItem

class RefreshActivity1 : ActionBarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refresh_view)
        customActionBar.displayUpIndicator()
        title = this@RefreshActivity1.javaClass.simpleName.replace("Activity", "")
        initViews()
    }

    private fun initViews() {

        findViewById(R.id.button_refresh_00).setOnClickListener {
            customActionBar.enableRefresh(true, Gravity.LEFT)
        }
        findViewById(R.id.button_refresh_01).setOnClickListener {
            customActionBar.enableRefresh(false, Gravity.LEFT)
        }
        findViewById(R.id.button_refresh_10).setOnClickListener {
            customActionBar.enableRefresh(true, Gravity.RIGHT)
        }
        findViewById(R.id.button_refresh_11).setOnClickListener {
            customActionBar.enableRefresh(false, Gravity.RIGHT)
        }
        findViewById(R.id.button_refresh_2).setOnClickListener {
            customActionBar.refreshing(true)
        }
        findViewById(R.id.button_refresh_3).setOnClickListener {
            customActionBar.refreshing(false)
        }
        customActionBar.setOnRefreshClickListener { showToast() }
    }

    override fun onMenuActionCreated(actionMenu: ActionMenu) {
        super.onMenuActionCreated(actionMenu)
        actionMenu.addMenu(1, R.string.action_delete, -1, 1)
    }

    override fun onMenuActionSelected(actionMenu: ActionMenuItem): Boolean {
        when (actionMenu.id) {
            1 -> showToast(R.string.action_delete)
            2 -> showToast(R.string.action_selectAll)
            3 -> showToast(R.string.action_invert)
        }
        return super.onMenuActionSelected(actionMenu)
    }

    private fun showToast() {
        Toast.makeText(this, "refreshing", Toast.LENGTH_SHORT).show()
    }

    private fun showToast(id: Int) {
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show()
    }
}