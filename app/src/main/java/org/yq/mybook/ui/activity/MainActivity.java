package org.yq.mybook.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import org.yq.mybook.R;
import org.yq.mybook.ui.fragment.BookshelfFragment;
import org.yq.mybook.ui.fragment.CommunityFragment;
import org.yq.mybook.ui.fragment.DiscoveryFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    private MenuItem menuItem;

    private BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_bookshelf:
                    mViewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_community:
                    mViewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_discovery:
                    mViewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };


    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            if(null!=menuItem)
            {
                menuItem.setChecked(false);
            }
            else
            {
                menuItem = navigation.getMenu().getItem(0).setChecked(false);
            }

            menuItem = navigation.getMenu().getItem(position);
            menuItem.setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager)findViewById(R.id.viewpager_main);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            private List<Fragment> fragments = new ArrayList<>();
            {
                fragments.add(new BookshelfFragment());
                fragments.add(new CommunityFragment());
                fragments.add(new DiscoveryFragment());

            }


            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
    }

}
