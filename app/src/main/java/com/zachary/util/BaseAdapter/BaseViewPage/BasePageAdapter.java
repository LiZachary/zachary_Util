package com.zachary.util.BaseAdapter.BaseViewPage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zachary.util.BaseFragment.FragmentFactory;
import com.zachary.util.Util.ResourceUtil;

/**
 * Created by Zachary on 2018-04-11.
 */
   public class BasePageAdapter extends FragmentPagerAdapter {
        private String[] titles;
        private String title;

        public BasePageAdapter(FragmentManager fm,int array,String title) {
            super(fm);
            this.titles = ResourceUtil.getStringArray(array);
            this.title  =title;
        }

        @Override
        public Fragment getItem(int position) {
            if(title.equals("FragmentSecond")){
                return FragmentFactory.ShowOrderFragment(position);
            }
            return FragmentFactory.ShowSendFragment(position);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

}

