package com.guagua.qiqi.gifteffect.animation.algorithm;

import java.util.Random;

/**
 * Created by yujintao on 15/7/4.
 */
public class MathCommonAlg {
    public static Random random=new Random(System.currentTimeMillis());
    public static final int gaussian=-3;
    public static int rangeRandom(int b,int e){
    	if(b>0){
    		return random.nextInt(e)%(e-b+1)+b;
    	}else if(b<0&&e>=0){
    		return random.nextInt(e-b)+b;
    	}else{
    		return random.nextInt(e-b)+b;
    	}
    	
        
    }
    public static boolean randomBool(){
        return random.nextBoolean();
    }
    //返回0-1
    public static float randomFloat(){
        return random.nextFloat();
    }
    public static float randomFloat(float min,float max){
    	return randomFloat()*(max-min)+min;
    }
    
    public static double nextGaussian(){
        return (random.nextGaussian()%1+1)/2;
    }
    public static void createGaussian(float[] arr){


    }
}
