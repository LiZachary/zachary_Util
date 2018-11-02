package com.zachary.utli.BaseAdapter.BaseSlideRecycleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhan on 2017/2/6.
 */

public class ISlideHelper {

  private List<com.zachary.utli.BaseAdapter.BaseSlideRecycleView.ISlide> mISlides = new ArrayList<>();

  public void add(com.zachary.utli.BaseAdapter.BaseSlideRecycleView.ISlide iSlide) {
    mISlides.add(iSlide);
  }

  public void remove(com.zachary.utli.BaseAdapter.BaseSlideRecycleView.ISlide iSlide) {
    mISlides.remove(iSlide);
  }

  public void clear() {
    mISlides.clear();
  }

  public List<com.zachary.utli.BaseAdapter.BaseSlideRecycleView.ISlide> getISlideList() {
    return mISlides;
  }

  public void slideOpen() {
    for (com.zachary.utli.BaseAdapter.BaseSlideRecycleView.ISlide slide : mISlides) {
      slide.slideOpen();
    }
  }

  public void slideClose() {
    for (com.zachary.utli.BaseAdapter.BaseSlideRecycleView.ISlide slide : mISlides) {
      slide.slideClose();
    }
  }
}
