package com.guagua.qiqi.gifteffect;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.guagua.qiqi.gifteffect.animation.AnimationWrapper;
import com.guagua.qiqi.gifteffect.animation.algorithm.CaculationModel;
import com.guagua.qiqi.gifteffect.animation.algorithm.RangeCommon;
import com.guagua.qiqi.gifteffect.elements.IScene;
import com.guagua.qiqi.gifteffect.elements.ISignt;
import com.guagua.qiqi.gifteffect.elements.SceneProxy;
import com.guagua.qiqi.gifteffect.elements.level.Level10Scene;
import com.guagua.qiqi.gifteffect.elements.level.Level1Scene;
import com.guagua.qiqi.gifteffect.elements.level.Level2Scene;
import com.guagua.qiqi.gifteffect.elements.level.Level3Scene;
import com.guagua.qiqi.gifteffect.elements.level.Level4Scene;
import com.guagua.qiqi.gifteffect.elements.level.Level5Scene;
import com.guagua.qiqi.gifteffect.elements.level.Level6Scene;
import com.guagua.qiqi.gifteffect.elements.level.Level7Scene;
import com.guagua.qiqi.gifteffect.elements.level.Level8Scene;
import com.guagua.qiqi.gifteffect.elements.level.Level9Scene;
import com.guagua.qiqi.gifteffect.util.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by yujintao on 15/7/1.
 */
public class BaseSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

	private SurfaceHolder surfaceHolder;
	private Thread mSurfaceThread;
	private BlockingQueue<SceneInfo> list;

	private ISignt mCurSignt;

	private Bitmap mBoard;
	private Canvas mCanvas;

	private Paint paint;
	private boolean mIsRunning;

	private PorterDuffXfermode clear;
	private PorterDuffXfermode src;
	private Matrix matrix;
	private CaculationModel caculationModel;//出场动画
	private CaculationModel alpha;

	//能够确定的surfaceview的大小
	private int mWidth;
	private int mheight;

	//播放回调函数
	private OnPlaySceneEndListener playSceneEndListener;

	public BaseSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		surfaceHolder = getHolder();
		setZOrderOnTop(true);
		surfaceHolder.setFormat(PixelFormat.TRANSPARENT);
		getHolder().addCallback(this);

		list = new LinkedBlockingQueue<SceneInfo>();
		mIsRunning = true;
		paint = new Paint();
		clear = new PorterDuffXfermode(Mode.CLEAR);
		src = new PorterDuffXfermode(Mode.SRC);
		matrix = new Matrix();
		alpha = RangeCommon.build(255, 0, 400);
	}

	public void onStop() {

	}

	public void onStart() {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {

		mWidth = getWidth();
		mheight = getHeight();
		
		Logger.d("surfaceCreated width:" + mWidth + " height:" + getHeight());
		mSurfaceThread = new Thread(this, "giftThread");
		mSurfaceThread.start();
		mIsRunning = true;
		Logger.d("礼物动画线程创建..." + mSurfaceThread.getName() + "-" + mSurfaceThread.getId());

	}

	public void addScene(SceneInfo info) {
		list.add(info);
		Logger.d("添加场景，进行特效播放" + info);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		if ((mWidth != width || mheight != height) && height > 0 && width > 0) {
			mWidth = width;
			mheight = height;
			Logger.d("surfaceChanged width:" + mWidth + " height:" + mheight);
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Logger.d("surfaceDestroyed..." + mSurfaceThread.getName() + "-" + mSurfaceThread.getId());
		mIsRunning = false;//推出或者放入后台都会执行。
		mSurfaceThread.interrupt();
	}

	/**
	 * while 是否还有场景 没有则进行休眠
	 * scene 展现
	 * while 场景是否播放完毕
	 * 重新绘制场景达到动画效果
	 * Thread sleep休眠-播放帧数
	 * end 场景播放完毕
	 * if 是否需要释放资源
	 * 释放资源
	 * scene end
	 * end
	 * <p/>
	 * 线程结束
	 */

	@Override
	public void run() {
		Canvas canvas = null;
		Logger.d("动画线程开始执行。。。" + Thread.currentThread().getName() + "-" + Thread.currentThread().getId());
		while (mIsRunning) {
			try {
				mCurSignt = take();
				Logger.d("开始播放场景" + mCurSignt);
				if (!mCurSignt.readyForPlay()) {
					Logger.d("ready for play false " + mCurSignt);
					mCurSignt = null;
					continue;
				}
				while (!mCurSignt.isPlayEnd()) {
					try {
						canvas = surfaceHolder.lockCanvas();
						paint.setXfermode(clear);
						canvas.drawPaint(paint);
						paint.setXfermode(src);
						mCurSignt.play(canvas);
//						SystemClock.sleep(10);//帧数50
					}
					catch (Exception e) {
						Logger.d("绘制场景出现错误" + mCurSignt, e);
						mCurSignt.playEnd();

					}
					finally {
						if (canvas != null)
							surfaceHolder.unlockCanvasAndPost(canvas);

					}
				}
				mCurSignt.destroy();
				Logger.d("场景播放完成  " + mCurSignt);
				try {
					//清除surface
					canvas = surfaceHolder.lockCanvas();
					if (canvas != null) {
						paint.setXfermode(clear);
						canvas.drawPaint(paint);
					}
				}
				catch (Exception e) {
					Logger.d("清楚surface试图，保留绘制线程" + mCurSignt, e);
				}
				finally {
					if (canvas != null)
						surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
			catch (InterruptedException e) {
				Logger.d(" 发生线程打断异常，终止绘制线程  " + mCurSignt, e);
				mIsRunning = false;
			}
			catch (Exception e) {
				Logger.d("当前场景发生错误，退出当前场景，保留绘制线程" + mCurSignt, e);
			}
			finally {
				Logger.d("场景动画彻底结束..." + mCurSignt + " thread status " + mIsRunning);
				mCurSignt = null;//结束赋值null。
			}
			if (playSceneEndListener != null) {
				playSceneEndListener.onPlayEnd();
			}
		}
		Logger.d("动画线程退出..." + Thread.currentThread().getName() + "-" + Thread.currentThread().getId());

	}

	private ISignt take() throws InterruptedException {
		return build(list.take());
	}

	public ISignt build(SceneInfo sceneInfo) {
		IScene iScene;
		SceneProxy sceneProxy = null;
		AnimationWrapper begin=new AnimationWrapper();
		switch (sceneInfo.effectLevel) {
			case 1:
				iScene = new Level1Scene(getContext(), mWidth, mheight / 6);
				sceneProxy = new SceneProxy(iScene);
				begin.setXYAnimation(null, RangeCommon.build(mheight, mheight / 2, 500));
//				begin.setXYAnimation(null, StaticValue.build( mheight / 2));
//				begin.setAlphaAnimation(RangeCommon.build(0, 255, 500));
				sceneProxy.setmBeginAnimation(begin);
				break;
			case 2:
				iScene = new Level2Scene(getContext(), mWidth, mheight / 6);
				sceneProxy = new SceneProxy(iScene);
				begin.setXYAnimation(null, RangeCommon.build(mheight, mheight / 2, 300));
				sceneProxy.setmBeginAnimation(begin);
				break;
			case 3:
				iScene = new Level3Scene(getContext(), mWidth, mheight / 6);
				sceneProxy = new SceneProxy(iScene);
				begin.setXYAnimation(null, RangeCommon.build(mheight, mheight / 2, 300));
				sceneProxy.setmBeginAnimation(begin);
				break;
			case 4:
				iScene = new Level4Scene(getContext(), mWidth, mheight / 6);
				sceneProxy = new SceneProxy(iScene);
				begin.setXYAnimation(null, RangeCommon.build(mheight, mheight / 2, 300));
				sceneProxy.setmBeginAnimation(begin);
				break;
			case 5:
				iScene = new Level5Scene(getContext(), mWidth, mheight / 6);
				sceneProxy = new SceneProxy(iScene);
				begin.setXYAnimation(null, RangeCommon.build(mheight, mheight / 2, 300));
				sceneProxy.setmBeginAnimation(begin);
				break;
			case 6:
				iScene = new Level6Scene(getContext(), mWidth, mheight / 6);
				sceneProxy = new SceneProxy(iScene);
				begin.setXYAnimation(null, RangeCommon.build(mheight, mheight / 2, 300));
				sceneProxy.setmBeginAnimation(begin);
				break;
			case 7:
				iScene = new Level7Scene(getContext(), mWidth, mheight / 6);
				sceneProxy = new SceneProxy(iScene);
				begin.setXYAnimation(null, RangeCommon.build(mheight, mheight / 2, 300));
				sceneProxy.setmBeginAnimation(begin);
				break;
			case 8:
				iScene = new Level8Scene(getContext(), mWidth, mheight);
				sceneProxy = new SceneProxy(iScene);
				sceneProxy.setmBeginAnimation(begin);
				sceneProxy.setBGColor(0x99000000);
				begin.setAlphaAnimation(RangeCommon.build(0, 255, 500));
				break;
			case 9:
				iScene = new Level9Scene(getContext(), mWidth, mheight);
				sceneProxy = new SceneProxy(iScene);
				sceneProxy.setmBeginAnimation(begin);
				sceneProxy.setBGColor(0x99000000);
				begin.setAlphaAnimation(RangeCommon.build(0, 255, 500));
				break;
			case 10:
			default:
				iScene = new Level10Scene(getContext(), mWidth, mheight);
				sceneProxy = new SceneProxy(iScene);
				sceneProxy.setBGColor(0x99000000);
				sceneProxy.setmBeginAnimation(begin);
				begin.setAlphaAnimation(RangeCommon.build(0, 255, 500));
				break;
		}
		iScene.setSceneInfo(sceneInfo);
		return sceneProxy;
	}

	public OnPlaySceneEndListener getPlaySceneEndListener() {
		return playSceneEndListener;
	}

	public void setPlaySceneEndListener(OnPlaySceneEndListener playSceneEndListener) {
		this.playSceneEndListener = playSceneEndListener;
	}

	public static interface OnPlaySceneEndListener {
		void onPlayEnd();
	}
}
