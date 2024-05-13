package mobi.cangol.mobile.actionbar.demo

import android.os.Bundle
import mobi.cangol.mobile.actionbar.ActionBarActivity
import mobi.cangol.mobile.actionbar.ActionMenu
import mobi.cangol.mobile.actionbar.ActionMenuItem
import mobi.cangol.mobile.actionbar.ActionMode

class ActionModeActivity1 : ActionBarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action_mode)
        customActionBar.displayUpIndicator()
        title = this@ActionModeActivity1.javaClass.simpleName.replace("Activity", "")
        initViews()
    }

    private fun initViews() {
        findViewById(R.id.button_mode_2).setOnClickListener {
            stopCustomActionMode()
        }
        findViewById(R.id.button_mode_1).setOnClickListener {
            startCustomActionMode(object : ActionMode.Callback {
                override fun onCreateActionMode(mode: ActionMode?, actionMenu: ActionMenu?) {
                }

                override fun onActionItemClicked(
                    mode: ActionMode?,
                    menuItem: ActionMenuItem?
                ): Boolean {
                    return false
                }

                override fun onDestroyActionMode(mode: ActionMode?) {
                }

            })
        }
    }

}