package com.picacomic.fregata.utils.ChatroomGame;

import android.graphics.Canvas;
/* loaded from: picacg_1.jar:com/picacomic/fregata/utils/ChatroomGame/b.class */
public class b extends Thread {
    private ChatroomGameView tX;
    private boolean tY = false;

    public b(ChatroomGameView chatroomGameView) {
        this.tX = chatroomGameView;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Canvas canvas;
        while (this.tY) {
            long currentTimeMillis = System.currentTimeMillis();
            try {
                canvas = this.tX.getHolder().lockCanvas();
                try {
                    synchronized (this.tX.getHolder()) {
                        this.tX.j(canvas);
                    }
                    if (canvas != null) {
                        this.tX.getHolder().unlockCanvasAndPost(canvas);
                    }
                    long currentTimeMillis2 = 33 - (System.currentTimeMillis() - currentTimeMillis);
                    if (currentTimeMillis2 > 0) {
                        try {
                            sleep(currentTimeMillis2);
                        } catch (Exception e) {
                        }
                    } else {
                        sleep(10L);
                    }
                } catch (Throwable th) {
                    th = th;
                    if (canvas != null) {
                        this.tX.getHolder().unlockCanvasAndPost(canvas);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                canvas = null;
            }
        }
    }

    public void setRunning(boolean z) {
        this.tY = z;
    }
}
