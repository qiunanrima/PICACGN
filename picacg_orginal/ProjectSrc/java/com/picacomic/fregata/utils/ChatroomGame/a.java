package com.picacomic.fregata.utils.ChatroomGame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
/* loaded from: picacg_1.jar:com/picacomic/fregata/utils/ChatroomGame/a.class */
public class a {
    int targetHeight;
    int targetWidth;

    public a(int i, int i2) {
        this.targetWidth = i;
        this.targetHeight = i2;
    }

    public int a(Canvas canvas, int i) {
        int height;
        return (canvas == null || (height = canvas.getHeight()) <= 0) ? i : (i * height) / 400;
    }

    public void a(Canvas canvas, Bitmap bitmap, Rect rect, Rect rect2, Paint paint) {
        canvas.drawBitmap(bitmap, rect, rect2, paint);
    }
}
