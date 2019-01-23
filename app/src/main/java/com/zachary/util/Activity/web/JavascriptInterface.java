package com.zachary.util.Activity.web;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.text.ClipboardManager;
import android.webkit.WebView;

import java.io.IOException;
import java.util.HashMap;

/**
 * WebView使用的JavaScript接口.
 * (从UrlGetActivity提取)
 * @author gaopeng
 */
public class JavascriptInterface {
	private Activity mActivity;
	private Context context;
	private WebView webView;
	/** 用于使用setValue和getValue在JS和App之间传值. */
	private HashMap<String, String> values = new HashMap<String, String>();
	
	/** 
	 * 处理JS登录事件.
	 * JS调用window.house365js.login()时，会触发该事件. 
	 */
	private JSListener jsListener = null;

	private OnRefreshOrderStatus refreshListener = null;

	public JavascriptInterface(Context context, Activity app, WebView webView) {
	    this.mActivity = app;
	    this.context = context;
		this.webView = webView;
    }
	
	////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	////
	////	sys系统级别(供网页端调用)
	////	sys_playSound();//播放系统当前铃声
	////	sys_vibrate(long milsconds);//震动
	////	sys_getDeviceId();//获取设备唯一标识
	////	sys_getNetwork();//获取当前网络（wifi,mobile）
	////	sys_getOperator();//获取运营商
	////	sys_getDeviceMode();//获取设备型号
	////
	////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	@android.webkit.JavascriptInterface
	public void sys_playSound(){
		try {
			Uri defaultRingtone = RingtoneManager.getActualDefaultRingtoneUri(context,
			        RingtoneManager.TYPE_RINGTONE);
			MediaPlayer mMediaPlayer = MediaPlayer.create(context, defaultRingtone);
			mMediaPlayer.setLooping(false);//设置循环
			mMediaPlayer.prepare();
			mMediaPlayer.start();
		} catch (IllegalStateException e) {
			//先进行注销
			//CorePreferences.ERROR(e);
		} catch (IOException e) {
			//CorePreferences.ERROR(e);
		}
	}
	@android.webkit.JavascriptInterface
	public void sys_vibrate(long milsconds){
		Vibrator vibrator = (Vibrator) context.getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
		vibrator.vibrate(milsconds); 
//		vibrator.vibrate(long[] pattern, int repeat);
	}

	/**
	 * 用户是否已经登入
	 * @return
	 */
	@android.webkit.JavascriptInterface
	public void app_isLogin(){
//		this.callBack("app_getToken", String.valueOf(!app.isUserExpire()));
//		return !app.isUserExpire();
	}
	
	/**
	 * 登入，并回调JS方法loginCallback(boolean)
	 */
	@android.webkit.JavascriptInterface
	public void app_Login(){
		if (jsListener != null) {
			jsListener.onLogin();
		} else {

		}
	}

	/**
	 * 复制文本到剪贴板.
	 * @param str 需要复制的文本
	 */
	@android.webkit.JavascriptInterface
	public void app_copyToClipboard(String str){
		ClipboardManager cmb = (ClipboardManager)context.getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
		cmb.setText(str.trim());
	}
	
	/**
	 * 设置Listener处理JS处罚的登录事件(house365js.login())
	 * @param listener
	 */
	public void setJSListener(JSListener listener) {
		this.jsListener = listener;
	}
	
	public interface JSListener {
		public void onLogin();
		public void onCreateRightBt(String text, String jsFun);
		public void onShare(String shareTitle, String shareImg, String shareUrl, String shareDesc);
	}

	//创建右侧按钮
	@android.webkit.JavascriptInterface
	public void app_createRightBt(String text, String jsFunction){
		if (jsListener != null) {
			jsListener.onCreateRightBt(text,jsFunction);
		} else {

		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	////
	////	网页端JS(供移动APP端调用)
	////	page_getTitle();页面中标题，如果不存在，调用title标签
	////	page_isShare();是否需要分享
	////	page_getShareTitle();//分享时使用标题
	////	page_getShareUrl();//分享时URL
	////	page_getShareImg();//分享图片
	////	page_getShareDesc();//分享内容
	////
	////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	/** 设置值. */
	@android.webkit.JavascriptInterface
	public void setValue(String key, String value) {
		values.put(key, value);
	}
	
	/** 获取值. */
	@android.webkit.JavascriptInterface
	public String getValue(String key) {
		return values.get(key);
	}
	@android.webkit.JavascriptInterface
	public void clearValues(){
		values.clear();
	}

	@android.webkit.JavascriptInterface
	public void returnAction(){
		mActivity.finish();

	}



	public interface OnRefreshOrderStatus{
		void pay(String orderGuid, int payAmount, String remainTime, String image_url, String centerName);
	}

	public OnRefreshOrderStatus getRefreshListener() {
		return refreshListener;
	}

	public void setRefreshListener(OnRefreshOrderStatus refreshListener) {
		this.refreshListener = refreshListener;
	}
}
