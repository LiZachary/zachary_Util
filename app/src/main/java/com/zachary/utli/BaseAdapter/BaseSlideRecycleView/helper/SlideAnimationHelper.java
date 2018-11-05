package com.zachary.utli.BaseAdapter.BaseSlideRecycleView.helper;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;

/**
 * Created by zhan on 2018/2/3.
 */

public class SlideAnimationHelper {

  public static final int STATE_CLOSE = 20000;
  public static final int STATE_OPEN = 30000;

  private static int mCurrentState = STATE_CLOSE;

  private ValueAnimator mValueAnimator;

  public SlideAnimationHelper() {

  }

  public int getState() {
    return mCurrentState;
  }

  public ValueAnimator getAnimation() {
    if (mValueAnimator == null) {
      mValueAnimator = new ValueAnimator();
      mValueAnimator.setFloatValues(0.0f, 1.0f);
    }
    return mValueAnimator;
  }

  public void openAnimation(long duration, AnimatorUpdateListener animatorUpdateListener,
      Animator.AnimatorListener listener, float... values) {
    mCurrentState = STATE_OPEN;
    setValueAnimator(duration, animatorUpdateListener, listener, values);
  }

  public void closeAnimation(long duration, AnimatorUpdateListener animatorUpdateListener,
      Animator.AnimatorListener listener, float... values) {
    mCurrentState = STATE_CLOSE;
    setValueAnimator(duration, animatorUpdateListener, listener, values);
  }

  public void openAnimation(long duration, AnimatorUpdateListener animatorUpdateListener,
      float... values) {
    mCurrentState = STATE_OPEN;
    setValueAnimator(duration, animatorUpdateListener, null, values);
  }

  public void closeAnimation(long duration, AnimatorUpdateListener animatorUpdateListener,
      float... values) {
    mCurrentState = STATE_CLOSE;
    setValueAnimator(duration, animatorUpdateListener, null, values);
  }

  public void openAnimation(long duration, AnimatorUpdateListener animatorUpdateListener,
      Animator.AnimatorListener listener) {
    mCurrentState = STATE_OPEN;
    setValueAnimator(duration, animatorUpdateListener, listener);
  }

  public void closeAnimation(long duration, AnimatorUpdateListener animatorUpdateListener,
      Animator.AnimatorListener listener) {
    mCurrentState = STATE_CLOSE;
    setValueAnimator(duration, animatorUpdateListener, listener);
  }

  public void openAnimation(long duration, AnimatorUpdateListener animatorUpdateListener) {
    mCurrentState = STATE_OPEN;
    setValueAnimator(duration, animatorUpdateListener, null);
  }

  public void closeAnimation(long duration, AnimatorUpdateListener animatorUpdateListener) {
    mCurrentState = STATE_CLOSE;
    setValueAnimator(duration, animatorUpdateListener, null);
  }

  private void setValueAnimator(long duration, AnimatorUpdateListener animatorUpdateListener,
      Animator.AnimatorListener listener) {
    mValueAnimator = getAnimation();
    mValueAnimator.setDuration(duration);

    if (animatorUpdateListener != null) {
      mValueAnimator.addUpdateListener(animatorUpdateListener);
    }
    if (listener != null) {
      mValueAnimator.addListener(listener);
    }
    start();
  }

  private void setValueAnimator(long duration, AnimatorUpdateListener animatorUpdateListener,
      Animator.AnimatorListener listener, float... values) {
    mValueAnimator = getAnimation();
    mValueAnimator.setDuration(duration);
    mValueAnimator.setFloatValues(values);

    if (animatorUpdateListener != null) {
      mValueAnimator.addUpdateListener(animatorUpdateListener);
    }
    if (listener != null) {
      mValueAnimator.addListener(listener);
    }
    start();
  }

  private void start() {
    if (mValueAnimator != null && !mValueAnimator.isRunning()) {
      mValueAnimator.start();
    }
  }

  public static int getOffset(Context context, int offset) {
    return (int) (context.getResources().getDisplayMetrics().density * offset + 0.5f);
  }
}
