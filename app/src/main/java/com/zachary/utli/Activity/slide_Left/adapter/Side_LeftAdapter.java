package com.zachary.utli.Activity.slide_Left.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zachary.utli.BaseAdapter.BaseListView.BaseListAdapter;
import com.zachary.utli.BaseAdapter.BaseListView.BaseListHolder;
import com.zachary.utli.BaseAdapter.BaseListView.ViewCreator;
import com.zachary.utli.BaseBean.SlideLeftBean;
import com.zachary.utli.R;

import java.util.List;

/**
 * Created by Zachary on 2018-04-25.
 */
public class Side_LeftAdapter extends BaseListAdapter<SlideLeftBean,Side_LeftAdapter.SlideLeftHolder> {

    public Side_LeftAdapter(List data,ViewCreator mViewCreator) {
        super(data,mViewCreator);
    }


    public  static class SlideLeftHolder extends BaseListHolder {
        public  ImageView iv;
        public  TextView  text;

        public SlideLeftHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.item_img);
            text = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }
}
