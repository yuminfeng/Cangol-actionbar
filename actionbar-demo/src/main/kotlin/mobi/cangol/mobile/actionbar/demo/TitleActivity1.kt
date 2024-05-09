package mobi.cangol.mobile.actionbar.demo

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import mobi.cangol.mobile.actionbar.ActionBarActivity

class TitleActivity1 : ActionBarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title_view)
        customActionBar.displayUpIndicator()
        title = localClassName.replace("Activity", "")
        initViews()
    }

    private fun initViews() {
        findViewById(R.id.button_title_1).setOnClickListener {
            customActionBar.titleGravity = Gravity.CENTER
        }
        findViewById(R.id.button_title_2).setOnClickListener {
            customActionBar.titleGravity = Gravity.LEFT or Gravity.CENTER_VERTICAL
        }
        findViewById(R.id.button_title_3).setOnClickListener {
            if (customActionBar.titleVisibility == View.VISIBLE) {
                customActionBar.titleVisibility = View.INVISIBLE
            } else {
                customActionBar.titleVisibility = View.VISIBLE
            }
        }
        findViewById(R.id.button_shadwow_1).setOnClickListener {
            setActionbarShadow(true)
            setShadow(findViewById(R.id.image), true)
        }
        findViewById(R.id.button_shadwow_2).setOnClickListener {
            setActionbarShadow(false)
            setShadow(findViewById(R.id.image), false)
        }
        //设置标题点击事件
        this.customActionBar.setOnTitleClickListener {
            Toast.makeText(
                applicationContext,
                "Click Title",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setShadow(view: View, shadow: Boolean) {
        if (shadow) {
            view.elevation = 4 * resources.displayMetrics.density
        } else {
            view.elevation = 0f
        }
    }

}