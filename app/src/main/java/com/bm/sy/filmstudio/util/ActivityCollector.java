package com.bm.sy.filmstudio.util;

import android.app.Activity;

import java.util.Stack;

/**
 * 创建者：梁建军 创建日期：2015/9/6 创建时间：13:51
 */
public class ActivityCollector {
	public static Stack<Activity> activityStack = new Stack<>();

	/**
	 * 添加Activity
	 *
	 * @param activity
	 */
	public static void addActivity(Activity activity) {
		activityStack.add(activity);
	}

	/**
	 * 移除Activity
	 *
	 * @param activity
	 */
	public static void removeActivity(Activity activity) {
		activityStack.remove(activity);
	}

	/**
	 * 结束全部Activity
	 */
	public static void finishAll() {
		while (!activityStack.isEmpty()) {
			Activity activity = activityStack.pop();
			if (!activity.isFinishing()) {
				activity.finish();
			}
		}
	}

	/**
	 * 结束除了第一个
	 */
	public static void finishFirst() {
		while (activityStack.size() > 1) {
			Activity activity = activityStack.pop();
			if (!activity.isFinishing()) {
				activity.finish();
			}
		}
	}

	/**
	 * 除了当前
	 */
	public static void finishOther() {
		new Thread(){
			@Override
			public void run() {
				super.run();
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (activityStack.size() > 1) {
					Activity activity = activityStack.pop();

					while (activityStack.size() > 0) {
						Activity activity1 = activityStack.pop();
						if (!activity1.isFinishing()) {
							activity1.finish();
						}
					}
					addActivity(activity);
				}
			}
		}.start();

	}

	/**
	 * 结束NumberActivity
	 *
	 * @param number 结束的个数
	 */
	public static void finishNumber(int number) {
		for (int i = 0; i < number; i++) {
			if (activityStack.size() > 1) {
				Activity activity = activityStack.pop();
				if (!activity.isFinishing()) {
					activity.finish();
				}
			}
		}
	}

	public static Activity peek() {
		return activityStack.peek();
	}
}
