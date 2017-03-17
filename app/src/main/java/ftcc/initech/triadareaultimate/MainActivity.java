package ftcc.initech.triadareaultimate;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import ftcc.initech.triadareaultimate.controller.HashMapMenuAdapter;

public class MainActivity extends AppCompatActivity {
    private LinkedHashMap<String, Integer> mMenuMap;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: 3/16/17 change from using string array to menuItem model
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMenuMap = new LinkedHashMap<>();
        mMenuMap.put("Home", R.drawable.ic_home_black_24dp);
        mMenuMap.put("News", R.drawable.ic_rss_feed_24px);
        mMenuMap.put("Calendar", R.drawable.ic_event_black_24dp);
        mMenuMap.put("Settings", R.drawable.ic_settings_applications_black_24dp);

        mDrawerList = (ListView) findViewById(R.id.menu_drawer);
        mDrawerList.setAdapter(new HashMapMenuAdapter(mMenuMap));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }
        };
        // TODO: 3/16/17 setDrawerListener is deprecated, find its replacement 
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    /**
     * Swaps selected item corresponding fragment into content_frame
     */
    private void selectItem(int position) {
        // TODO: 3/16/17 Implement Compat Fragments
        Fragment fragment = new Fragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        mDrawerList.setItemChecked(position, true);
        setTitle(new ArrayList<String>(mMenuMap.keySet()).get(position));
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }
}
