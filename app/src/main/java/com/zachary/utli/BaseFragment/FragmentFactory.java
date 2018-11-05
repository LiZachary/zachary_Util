package com.zachary.utli.BaseFragment;

import android.support.v4.app.Fragment;

import com.zachary.utli.Fragment.FragmentFrist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zachary on 2018-04-11.
 */
public class FragmentFactory {

    private List<Fragment> fragments=new ArrayList<>();

    /**
     * 添加或者显示fragment
     *
     * @param position
     */
    public static Fragment ShowOrderFragment(int position) {

         Fragment  currentFragment=null;
        switch (position){
         case 0:
             currentFragment=new FragmentFrist();
             break;
         case 1:
             currentFragment=new FragmentFrist();
             break;
         case 2:
             currentFragment=new FragmentFrist();
             break;
         default:
             break;
     }
        return currentFragment;
    }

    public static Fragment ShowSendFragment(int position) {

        Fragment  currentFragment=null;
        switch (position){
            case 0:
                currentFragment=new FragmentFrist();
                break;
            case 1:
                currentFragment=new FragmentFrist();
                break;
            case 2:
                currentFragment=new FragmentFrist();
                break;
            default:
                break;
        }
        return currentFragment;
    }
}
