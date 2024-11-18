package com.example.async_sortowanie;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class SortingView extends View {

    private int[] array = new int[0];
    private Paint paint = new Paint();

    public SortingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.BLUE);
    }

    public void setArray(int[] array) {
        this.array = array;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (array == null || array.length == 0) return;

        int szerokosc = getWidth();
        int wysokosc = getHeight();
        float szerokosc_okna = (float) szerokosc / array.length;

        for (int i = 0; i < array.length; i++) {
            float wysokosc_okna = (array[i] / 500.0f) * wysokosc;
            float left = i * szerokosc_okna;
            float top = wysokosc - wysokosc_okna;
            float right = left + szerokosc_okna;
            float bottom = wysokosc;

            canvas.drawRect(left, top, right, bottom, paint);
        }
    }


}

