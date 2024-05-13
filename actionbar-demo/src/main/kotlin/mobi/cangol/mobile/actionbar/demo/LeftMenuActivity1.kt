package mobi.cangol.mobile.actionbar.demo

import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import mobi.cangol.mobile.actionbar.ActionBarActivity

class LeftMenuActivity1 : ActionBarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_left_menu)
        customActionBar.displayUpIndicator()
        title = this@LeftMenuActivity1.javaClass.simpleName.replace("Activity", "")
        initViews()
    }

    private fun initViews() {
        customActionBar.titleGravity = Gravity.CENTER
        findViewById(R.id.button_menu_0).setOnClickListener {
            customActionBar.setDisplayShowHomeEnabled(true)
        }
        findViewById(R.id.button_menu_1).setOnClickListener {
            customActionBar.setDisplayShowHomeEnabled(false)
        }
        findViewById(R.id.button_menu_2).setOnClickListener {
            customActionBar.setLeftMenu(
                0x11,
                R.string.action_setting,
                R.drawable.actionbar_clear_light
            ) {
                Toast.makeText(this@LeftMenuActivity1, "111", Toast.LENGTH_SHORT).show()
            }
        }
        findViewById(R.id.button_menu_3).setOnClickListener {
            customActionBar.clearLeftMenu()
        }
    }
}