package jp.ac.gifu_u.info.niino.jissen2_2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class MyView extends View {
    //イベント発生時の座標を保存する動的配列
    private final ArrayList<Integer> array_x = new ArrayList<>();
    private final ArrayList<Integer> array_y = new ArrayList<>();
    private final ArrayList<Boolean> array_status = new ArrayList<>();
    private final Paint paint = new Paint();

    //コンストラクタ
    public MyView(Context context) {
        super(context);
        init();
    }
    public MyView(Context context, android.util.AttributeSet attrs){
        super(context,attrs);
        init();
    }
    private void init(){
        //Paintの設定
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //座標を取得
        int x = (int) event.getX();
        int y = (int) event.getY();
        //イベントに応じて動作を変更
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://タッチパネルが押されたとき
            case MotionEvent.ACTION_POINTER_DOWN:
                array_x.add(x); //座標を配列に保存
                array_y.add(y); //線の描画はしない
                array_status.add(false);
                invalidate(); //画面を強制的に再描画
                break;
            case MotionEvent.ACTION_UP://タッチパネルから離れた時
            case MotionEvent.ACTION_POINTER_UP:
                array_x.add(x); //座標を配列に保存
                array_y.add(y); //線の描画をする
                array_status.add(true);
                invalidate(); //画面を強制的に再描画
                break;
        }
        return true;
    }
    @Override
    public boolean performClick() {
        return super.performClick();
    }

    //描画メソッド
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        //背景を白色で塗りつぶす
        canvas.drawColor(Color.WHITE);
        //配列内の座標を読みだして描画
        for (int i = 1; i < array_status.size(); i++) {
            //描画するように状態値が与えられているとき
            //一度離してから次に押されるまでの移動分は描画しない
            if (array_status.get(i)) {
                //開始点の終了点の座標の値を取得
                int x1 = array_x.get(i - 1);
                int x2 = array_x.get(i);
                int y1 = array_y.get(i - 1);
                int y2 = array_y.get(i);
                //線を描画
                canvas.drawLine(x1, y1, x2, y2, paint);
            }
        }
    }
}