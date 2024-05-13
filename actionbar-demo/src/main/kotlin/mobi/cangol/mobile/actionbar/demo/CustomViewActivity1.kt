package mobi.cangol.mobile.actionbar.demo

import android.os.Bundle
import android.widget.EditText
import mobi.cangol.mobile.actionbar.ActionBarActivity

class CustomViewActivity1 : ActionBarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view)
        customActionBar.displayUpIndicator()
        title = this@CustomViewActivity1.javaClass.simpleName.replace("Activity", "")
        initViews()
    }

    private fun initViews() {
        findViewById(R.id.button_custom_1).setOnClickListener {
            customActionBar.setDisplayShowHomeEnabled(false)
            customActionBar.setCustomView(EditText(this@CustomViewActivity1))
        }
        findViewById(R.id.button_custom_2).setOnClickListener {
            customActionBar.removeCustomView()
        }
    }

}