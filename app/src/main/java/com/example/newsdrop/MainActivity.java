package com.example.newsdrop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends FragmentActivity {
    ViewPager viewPager=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager=(ViewPager) findViewById(R.id.viewP);
        FragmentManager fm=getSupportFragmentManager();
        viewPager.setAdapter(new MyAdapter(fm,9));

    }
    public static class MyAdapter extends FragmentPagerAdapter {
        int count=0;
        public MyAdapter(@NonNull FragmentManager fm,int count) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            this.count=count;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:

                case 1:

                case 2:

                case 3:

                case 4:

                case 5:

                case 6:

                case 7:

                case 8:

                default:
                    return null;
            }

        }

        @Override
        public int getCount() {
            return count;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:

                case 1:

                case 2:

                case 3:

                case 4:

                case 5:

                case 6:

                case 7:

                case 8:

                default:
                    return super.getPageTitle(position);
        }
    }
    }
}
