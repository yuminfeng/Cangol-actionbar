package mobi.cangol.mobile.actionbar.demo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import mobi.cangol.mobile.actionbar.ActionBarActivity

private var light = true

class MainActivity1 : ActionBarActivity() {


    private var activities: MutableList<Class<out Activity>> = ArrayList()

    private lateinit var mListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        if (light) setTheme(R.style.AppTheme1)
        else setTheme(R.style.AppTheme2)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "首页"
        setStatusBarTintColor(resources.getColor(R.color.red))
        setWindowBackground(R.drawable.ic_bg)
        customActionBar.setDisplayShowHomeEnabled(true)
        customActionBar.setBackgroundResource(R.color.red)
        setActionbarShadow(true, 8f)

        activities.add(ActionModeActivity1::class.java)
        activities.add(RefreshActivity1::class.java)
        activities.add(TabViewActivity1::class.java)
        activities.add(NavViewActivity1::class.java)
        activities.add(MenuActivity1::class.java)
        activities.add(CustomViewActivity1::class.java)
        activities.add(LeftMenuActivity1::class.java)
        activities.add(TitleActivity1::class.java)
        activities.add(ThemeActivity1::class.java)
        activities.add(TransparentActivity1::class.java)

        mListView = findViewById(R.id.listView) as ListView
        mListView.adapter = object : BaseAdapter() {
            override fun getCount(): Int {
                return activities.size
            }

            override fun getItem(position: Int): Class<*> {
                return activities[position]
            }

            override fun getItemId(position: Int): Long {
                return position.toLong()
            }

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
                var convertView = convertView
                var holder: ViewHolder?
                val item = getItem(position)
                if (null != convertView) {
                    holder = convertView.tag as ViewHolder
                } else {
                    convertView = LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_item_view, parent, false)
                    holder = ViewHolder()
                    holder.textView = convertView.findViewById<View>(R.id.textView) as TextView
                    convertView.tag = holder
                }
                holder.textView!!.text = item.simpleName.replace("Activity", "")
                return convertView
            }

            inner class ViewHolder {
                var textView: TextView? = null
            }
        }
        mListView.setOnItemClickListener { _, _, position, _ ->
            val clazz = activities[position]
            startActivity(Intent(this@MainActivity1, clazz))
        }
    }
}