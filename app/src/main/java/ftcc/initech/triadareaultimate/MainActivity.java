package ftcc.initech.triadareaultimate;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.HashMap;

import ftcc.initech.triadareaultimate.model.menuItem;

public class MainActivity extends AppCompatActivity {
    private String[] mMenuTitles;
    private HashMap<String, Integer> mMenuMap;
    private menuItem[] mMenuItems;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: 3/16/17 add material icons
        // TODO: 3/16/17 change from using string array to menuItem model
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMenuTitles = getResources().getStringArray(R.array.menu_array);
        mDrawerList = (ListView) findViewById(R.id.menu_drawer);
        mDrawerList.setAdapter(new ArrayAdapter<String>(MainActivity.this, R.layout.menu_list_item,
                mMenuTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(View view);
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
        mDrawerLayout.setDrawerListener(mDrawerToggle);
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
        setTitle(mMenuTitles[position]);
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
