package mobi.cangol.mobile.actionbar.demo

import android.os.Bundle
import android.widget.Toast
import mobi.cangol.mobile.actionbar.ActionBarActivity

class TabViewActivity1 : ActionBarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_view)
        setActionbarShadow(true, 3.0f)
        customActionBar.displayUpIndicator()
        title = localClassName.replace("Activity", "")
        initViews()
    }

    private fun initViews() {
        findViewById(R.id.button_tab_1).setOnClickListener {
            title = ""
            initActionTab()
            customActionBar.actionTab.getTabView(2).text = "tab_1"
        }

        findViewById(R.id.button_tab_2).setOnClickListener {
            customActionBar.actionTab.removeAllTabs()
            title = this@TabViewActivity1.javaClass.simpleName.replace("Activity", "")
        }

        customActionBar.actionTab.setOnTabSelectedListener { tab ->
            when (tab.id) {
                1 -> Toast.makeText(this@TabViewActivity1, tab.text, Toast.LENGTH_SHORT)
                    .show()
                2 -> Toast.makeText(this@TabViewActivity1, tab.text, Toast.LENGTH_SHORT)
                    .show()
            }
            false
        }
    }

    private fun initActionTab() {
        val actionTab = this.customActionBar.actionTab
        actionTab.newTab(1, "推荐", 1)
        actionTab.newTab(2, "关注", 0)
    }
}