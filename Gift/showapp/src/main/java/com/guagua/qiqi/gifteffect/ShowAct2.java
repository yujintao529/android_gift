package com.guagua.qiqi.gifteffect;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;


/**
 * Created by jintao on 2015/7/2.
 */
public class ShowAct2 extends Activity {
    private BaseSurfaceView baseSurfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_show);
        baseSurfaceView= (BaseSurfaceView) findViewById(R.id.surface);
        
    }
    public void onClick(View view){
    	SceneInfo info=new SceneInfo();
    	info.sender="郁金涛郁金涛郁金涛郁金涛郁金涛";
    	info.receiver="god";
    	info.num=9999;
    	info.effectLevel=2;
    	Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.gg_anchor_level11);
    	info.setGiftBitmap(bitmap);
    	baseSurfaceView.addScene(info);
//    	switch (view.getId()) {
//			case R.id.add4:
//				
//				break;
//    	}			
////
//			default:
//				break;
//		}
////    	
    	
//        switch (view.getId()){
////        	case R.id.
////        	case R.id.
//           
//                baseSurfaceView.addScene();
//                break;
//        }
    }
}
