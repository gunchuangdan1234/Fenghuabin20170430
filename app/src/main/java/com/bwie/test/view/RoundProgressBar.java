package com.bwie.test.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.bwie.test.R;



/**
 * 自定义圆形进度控件
 * Created by jinguo on 2017/3/13.
 */

public class RoundProgressBar  extends View {


    //--------------------自定义属性值-------------------
    private  int roundColor;
    private  int roundProgressColor;
    private  int textColor;
    private  float textSize;
    private float roundWidth=0;
    private float max;
    private   boolean  textIsDisplayable;
    private int style;
    private int progress=78;
    //--------------------自定义属性值-------------------

    //画笔
    private Paint mPaint;
    private RectF mRect;


    public RoundProgressBar(Context context) {
        super(context);
    }

    public RoundProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

//获取自定义属性值列表
        TypedArray  mTypedArray=  context.obtainStyledAttributes(attrs,R.styleable.RoundProgressBar);

        roundColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundColor, Color.RED);
        roundProgressColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundProgressColor, Color.GREEN);
        textColor = mTypedArray.getColor(R.styleable.RoundProgressBar_textColor, Color.GREEN);
        textSize = mTypedArray.getDimension(R.styleable.RoundProgressBar_textSize, getResources().getDimension(R.dimen.text_size));
        roundWidth = mTypedArray.getDimension(R.styleable.RoundProgressBar_roundWidth, getResources().getDimension(R.dimen.round_width));
        max = mTypedArray.getInteger(R.styleable.RoundProgressBar_max, 100);
        textIsDisplayable = mTypedArray.getBoolean(R.styleable.RoundProgressBar_textIsDisplayable, true);
        style = mTypedArray.getInt(R.styleable.RoundProgressBar_style, 0);

        //回收属性集合
        mTypedArray.recycle();

        mPaint=new Paint();

        mRect=new RectF();
    }


    @Override
    protected void onDraw(Canvas canvas) {

        //-----------------绘制圆环----------------
        float cx=getWidth()>>1;//计算圆的圆心x坐标
        float cy=getHeight()>>1;//计算圆的圆心y坐标
        float radius=cx-(roundWidth/2);//计算圆的半径
        mPaint.setColor(roundColor);
        mPaint.setStrokeWidth(roundWidth);//设置画笔的宽度
        mPaint.setAntiAlias(true);
        if(style==0) {
            mPaint.setStyle(Paint.Style.STROKE);
        }else if(style==1){
            mPaint.setStyle(Paint.Style.FILL);
        }
        canvas.drawCircle(cx,cy,radius,mPaint);
        //-----------------绘制圆环----------------


        //-----------------绘制圆环中心的文字----------------
        mPaint.setStrokeWidth(0);//恢复一下画笔宽度
        mPaint.setColor(textColor);
        mPaint.setTextSize(textSize);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);//设置字体为粗体
        int   temp=(int)((float)progress/max*100);//计算出当前进度与最大值（此时的值是小数）的比例然后再乘以100
        String text=temp+"%";
        float textWidth=mPaint.measureText(text);
        canvas.drawText(text,cx-(textWidth/2),cy,mPaint);


        //-----------------绘制圆环中心的文字----------------


        //-----------------绘制圆弧（圆环进度）----------------
        mRect.set(roundWidth/2,roundWidth/2,cx+radius,cy+radius);//确定圆弧的绘制区域
        mPaint.setColor(roundProgressColor);
        mPaint.setStrokeWidth(roundWidth);
        canvas.drawArc(mRect,0,progress/max*360,false,mPaint);
        //-----------------绘制圆弧（圆环进度）----------------
    }


    /**
     * 改变外圆环颜色值
     * @param color
     */
    public void setColor(int color){
        roundProgressColor=color;
        //刷新view
        postInvalidate();

    }
}
