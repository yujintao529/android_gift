package com.guagua.qiqi.gifteffect;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

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

import com.guagua.qiqi.gifteffect.animation.algorithm.CaculationModel;
import com.guagua.qiqi.gifteffect.animation.algorithm.RangeCommon;
import com.guagua.qiqi.gifteffect.elements.IScene;
import com.guagua.qiqi.gifteffect.elements.level.Level1Scene;
import com.guagua.qiqi.gifteffect.elements.level.Level2Scene;
import com.guagua.qiqi.gifteffect.elements.level.Level3Scene;
import com.guagua.qiqi.gifteffect.elements.level.Level4Scene;
import com.guagua.qiqi.gifteffect.elements.level.Level5Scene;
import com.guagua.qiqi.gifteffect.elements.level.Level6Scene;
import com.guagua.qiqi.gifteffect.elements.level.Level7Scene;
import com.guagua.qiqi.gifteffect.util.Logger;

/**
 * Created by yujintao on 15/7/1.
 */
public class BaseSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

	private SurfaceHolder surfaceHolder;
	private Thread mSurfaceThread;
	private BlockingQueue<SceneInfo> list;

	private IScene mCurScene;

	private Bitmap mBoard;
	private Canvas mCanvas;

	private Paint paint;
	private boolean mIsRunning;

	private PorterDuffXfermode clear;
	private PorterDuffXfermode src;
	private Matrix matrix;
	private CaculationModel caculationModel;//出场动画
	private CaculationModel alpha;

	//所使用的画板的大小
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
		alpha = RangeCommon.build(255, 0, 1000);

		//paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));  
	}

	public void onStop() {

	}

	public void onStart() {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {

		mWidth = getWidth();
		mheight = getHeight() / 3;
		Logger.d("surfaceCreated width:" + mWidth + " height:" + getHeight());
		caculationModel = RangeCommon.build(getHeight(), 20, 500);
		mBoard = Bitmap.createBitmap(mWidth, mheight, Bitmap.Config.ARGB_8888);
		Logger.d("borad view width " + getWidth() + " height " + mheight);
		mCanvas = new Canvas(mBoard);
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
		if (mWidth != getWidth() || mheight != getHeight() / 3) {
			mWidth = getWidth();
			mheight = getHeight() / 3;
			Logger.d("surfaceChanged width:" + mWidth + " height:" + getHeight());
			caculationModel = RangeCommon.build(getHeight(), 20, 500);
			if(mBoard!=null&&!mBoard.isRecycled()){
				mBoard.isRecycled();
			}
			mBoard = Bitmap.createBitmap(mWidth, mheight, Bitmap.Config.ARGB_8888);
			Logger.d("borad view width " + getWidth() + " height " + mheight);
			mCanvas = new Canvas(mBoard);
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Logger.d("surfaceDestroyed..." + mSurfaceThread.getName() + "-" + mSurfaceThread.getId());
		mIsRunning = false;//推出或者放入后台都会执行。
		mSurfaceThread.interrupt();
	}

	/**
	 *  while 是否还有场景 没有则进行休眠
	 *      scene 展现
	 *      while 场景是否播放完毕
	 *          重新绘制场景达到动画效果
	 *          Thread sleep休眠-播放帧数
	 *      end 场景播放完毕
	 *      if 是否需要释放资源
	 *         释放资源
	 *      scene end
	 * end
	 *
	 * 线程结束
	 */

	@Override
	public void run() {
		Canvas canvas = null;
		Logger.d("动画线程开始执行。。。" + Thread.currentThread().getName() + "-" + Thread.currentThread().getId());
		while (mIsRunning) {
			try {
				mCurScene = take();
				Logger.d("开始播放场景" + mCurScene);
				mCurScene.readyForShow(mCanvas);
				final long time = System.currentTimeMillis();
				while (!mCurScene.isEnd()) {
					try {
						paint.setXfermode(clear);
						mCanvas.drawPaint(paint);
						paint.setXfermode(src);
						mCurScene.draw(mCanvas);
						long diff = System.currentTimeMillis() - time;
						matrix.setTranslate(0, caculationModel.caculate((int) diff));
						canvas = surfaceHolder.lockCanvas();
						paint.setXfermode(clear);
						canvas.drawPaint(paint);
						paint.setXfermode(src);
						canvas.drawBitmap(mBoard, matrix, paint);
//						SystemClock.sleep(10);//帧数50
					}
					catch (Exception e) {
						Logger.d("绘制场景出现错误" + mCurScene, e);
						//手动结束iScene
						mCurScene.end();

					}
					finally {
						if (canvas != null)
							surfaceHolder.unlockCanvasAndPost(canvas);

					}
				}
				mCurScene.destroy();
				Logger.d("场景播放完成  " + mCurScene);
				Logger.d("开始执行场景退出动画..." + mCurScene);
				//死亡动画
				final long die = System.currentTimeMillis();
				boolean dead = false;
				while (!dead) {
					try {
						long diff = System.currentTimeMillis() - die;
						int value = (int) alpha.caculate((int) diff);
						paint.setAlpha(value);
						canvas = surfaceHolder.lockCanvas();
						paint.setXfermode(clear);
						canvas.drawPaint(paint);
						paint.setXfermode(src);
						canvas.drawBitmap(mBoard, matrix, paint);
						if (value == 0) {
							dead = true;
						}
					}
					catch (Exception e) {
						Logger.d("场景退出动画发生异常, 结束退出过场动画" + mCurScene, e);
						dead = true;
					}
					finally {
						if (canvas != null)
							surfaceHolder.unlockCanvasAndPost(canvas);
					}

				}
				//恢复画笔的alpha值
				paint.setAlpha(255);
				//
				try {
					//清除surface
					canvas = surfaceHolder.lockCanvas();
					if (canvas != null) {
						paint.setXfermode(clear);
						canvas.drawPaint(paint);
					}
				}
				finally {
					if (canvas != null)
						surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
			catch (InterruptedException e) {
				Logger.d(" 发生线程打断异常，终止绘制线程  " + mCurScene, e);
				mIsRunning = false;
			}
			catch (Exception e) {
				Logger.d("当前场景发生错误，退出当前场景，保留绘制线程" + mCurScene, e);
			}
			finally {
				Logger.d("场景动画彻底结束..." + mCurScene + " thread status " + mIsRunning);
				mCurScene = null;//结束赋值null。
			}
			if (playSceneEndListener != null) {
				playSceneEndListener.onPlayEnd();
			}
		}
		Logger.d("动画线程退出..." + Thread.currentThread().getName() + "-" + Thread.currentThread().getId());

	}

	private IScene take() throws InterruptedException {
		return build(list.take());
	}

	public IScene build(SceneInfo sceneInfo) {
		IScene iScene = null;
		switch (sceneInfo.effectLevel) {
			case 1:
				iScene = new Level1Scene(getContext(), mWidth, mheight);
				break;
			case 2:
				iScene = new Level2Scene(getContext(), mWidth, mheight);
				break;
			case 3:
				iScene = new Level3Scene(getContext(), mWidth, mheight);
				break;
			case 4:
				iScene = new Level4Scene(getContext(), mWidth, mheight);
				break;
			case 5:
				iScene = new Level5Scene(getContext(), mWidth, mheight);
				break;
			case 6:
				iScene = new Level6Scene(getContext(), mWidth, mheight);
				break;
			case 7:
			default:
				iScene = new Level7Scene(getContext(), mWidth, mheight);
				break;

		}

		iScene.setSceneInfo(sceneInfo);
		return iScene;
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
