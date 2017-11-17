package cn.xiwu.util;

import android.util.Log;

/**
 * Log统一管理类
 */
public class L
{

	private L()
	{
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	public static boolean isDebug = true;// 是否需要打印bug，可以在application的onCreate函数里面初始化
	private static final String TAG = "zhy";

	// 下面四个是默认tag的函数
	public static void i(String msg)
	{
		if (isDebug)
			Log.i(TAG, msg);
	}

	public static void d(String msg)
	{
		if (isDebug)
			Log.d(TAG, msg);
	}

	public static void e(String msg)
	{
		if (isDebug)
			Log.e(TAG, msg);
	}

	public static void v(String msg)
	{
		if (isDebug)
			Log.v(TAG, msg);
	}

	// 下面是传入自定义tag的函数
	public static void i(String tag, String msg)
	{
		if (isDebug)
			Log.i(tag, msg);
	}

	public static void d(String tag, String msg)
	{
		if (isDebug)
			Log.i(tag, msg);
	}

	public static void e(String tag, String msg)
	{
		if (isDebug)
			Log.i(tag, msg);
	}

	public static void v(String tag, String msg)
	{
		if (isDebug)
			Log.i(tag, msg);
	}
	/**
	 * 分段打印出较长log文本
	 * @param log        原log文本
	 * @param showCount  规定每段显示的长度（最好不要超过eclipse限制长度）
	 */
	public static void showLogCompletion(String log, int showCount,String flag){
		if(log.length() >showCount){
			String show = log.substring(0, showCount);
//          System.out.println(show);
			Log.i(flag, show+"");
			if((log.length() - showCount)>showCount){//剩下的文本还是大于规定长度
				String partLog = log.substring(showCount,log.length());
				showLogCompletion(partLog, showCount,flag);
			}else{
				String surplusLog = log.substring(showCount, log.length());
//              System.out.println(surplusLog);
				Log.i(flag, surplusLog+"");
			}

		}else{
//          System.out.println(log);
			Log.i(flag, log+"");
		}
	}
}