package mobi.cangol.mobile.actionbar.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mobi.cangol.mobile.actionbar.ActionBarActivity;

@Deprecated
public class MainActivity extends ActionBarActivity {
    private ListView mListView;
    private List<Class<? extends Activity>> activities = new ArrayList<>();
    private static boolean light = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity", "light==" + light);
        if (light) {
            setTheme(R.style.AppTheme1);
        } else {
            setTheme(R.style.AppTheme2);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("首页");
        this.setStatusBarTintColor(getResources().getColor(R.color.red));
        this.setWindowBackground(R.drawable.ic_bg);
        this.getCustomActionBar().setDisplayShowHomeEnabled(true);
        this.getCustomActionBar().setBackgroundResource(R.color.red);

        activities.add(RefreshActivity1.class);
        activities.add(TabViewActivity1.class);
        activities.add(NavViewActivity1.class);
        activities.add(MenuActivity1.class);
        activities.add(CustomViewActivity1.class);
        activities.add(LeftMenuActivity1.class);
        activities.add(TitleActivity1.class);
        activities.add(ThemeActivity1.class);
        activities.add(TransparentActivity1.class);
        mListView = (ListView) this.findViewById(R.id.listView);
        mListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return activities.size();
            }

            @Override
            public Class getItem(int position) {
                return activities.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder = null;
                final Class item = getItem(position);
                if (null != convertView) {
                    holder = (ViewHolder) convertView.getTag();
                } else {
                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_view, parent, false);
                    holder = new ViewHolder();
                    holder.text = (TextView) convertView.findViewById(R.id.textView);
                    convertView.setTag(holder);
                }
                holder.text.setText(item.getSimpleName().replace("Activity", ""));
                return convertView;
            }

            class ViewHolder {
                TextView text;
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Class clazz = (Class) parent.getItemAtPosition(position);
                startActivity(new Intent(MainActivity.this, clazz));
            }
        });

    }
}
