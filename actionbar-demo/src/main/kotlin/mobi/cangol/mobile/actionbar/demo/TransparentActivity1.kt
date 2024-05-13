package mobi.cangol.mobile.actionbar.demo

import android.os.Build
import android.os.Bundle
import mobi.cangol.mobile.actionbar.ActionBarActivity

class TransparentActivity1 : ActionBarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transparent_view)
        customActionBar.displayUpIndicator()
        setActionbarShadow(true, 9f)
        title = this@TransparentActivity1.javaClass.simpleName.replace("Activity", "")
        initView()
    }

    private fun initView() {
        findViewById(R.id.button_transparent_0).setOnClickListener {
            setSystemUiFloatFullScreen(false)
            isActionbarShow = true
            setStatusBarTintColor(resources.getColor(R.color.actionbar_background))
            customActionBar.setBackgroundResource(R.color.actionbar_background)
        }

        findViewById(R.id.button_transparent_01).setOnClickListener { isActionbarShow = true }
        findViewById(R.id.button_transparent_02).setOnClickListener { isActionbarShow = false }

        findViewById(R.id.button_transparent_11).setOnClickListener {
            customActionBar.setBackgroundResource(R.color.translucent)
        }
        findViewById(R.id.button_transparent_12).setOnClickListener {
            customActionBar.setBackgroundResource(R.color.transparent)
        }

        findViewById(R.id.button_transparent_21).setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                setTranslucent(true)
            }
        }
        findViewById(R.id.button_transparent_22).setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                setTranslucent(false)
            }
        }

        findViewById(R.id.button_transparent_31).setOnClickListener {
            setSystemUiFloatFullScreen(true)
        }
        findViewById(R.id.button_transparent_32).setOnClickListener {
            setSystemUiFloatFullScreen(false)
            setStatusBarTintColor(resources.getColor(R.color.actionbar_background))
        }

        findViewById(R.id.button_transparent_41).setOnClickListener { isFullScreen = true }
        findViewById(R.id.button_transparent_42).setOnClickListener { isFullScreen = false }

        findViewById(R.id.button_transparent_51).setOnClickListener {
            setFullScreenNoActionBar(true)
        }
        findViewById(R.id.button_transparent_52).setOnClickListener {
            setFullScreenNoActionBar(false)
        }
    }
}