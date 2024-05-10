package mobi.cangol.mobile.actionbar.demo

import android.os.Bundle
import android.widget.Toast
import mobi.cangol.mobile.actionbar.ActionBarActivity

class NavViewActivity1 : ActionBarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_view)
        customActionBar.displayUpIndicator()
        title = this@NavViewActivity1.javaClass.simpleName.replace("Activity", "")
        initViews()
    }

    private fun initViews() {
        findViewById(R.id.button_nav_1).setOnClickListener {
            actionNav()
        }
        findViewById(R.id.button_nav_2).setOnClickListener {
            customActionBar.clearListNavigation()
        }
    }

    private fun actionNav() {
        val navs = arrayOf("首页", "游戏", "壁纸", "资讯")
        customActionBar.listNavigation = navs
        customActionBar.setOnNavigationListener { position, _ ->
            Toast.makeText(
                this@NavViewActivity1,
                "Navigation " + navs[position],
                Toast.LENGTH_SHORT
            ).show()
            true
        }
    }
}