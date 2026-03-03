package com.isseiaoki.simplecropview.a;

import android.os.SystemClock;
import android.view.animation.Interpolator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/* loaded from: picacg_1.jar:com/isseiaoki/simplecropview/a/d.class */
public class d implements a {
    private static final int ca = Math.round(33.333332f);
    ScheduledExecutorService cb;
    long cc;
    long ce;
    private Interpolator mInterpolator;
    boolean cd = false;
    private b bY = new b() { // from class: com.isseiaoki.simplecropview.a.d.1
        @Override // com.isseiaoki.simplecropview.a.b
        public void h(float f) {
        }

        @Override // com.isseiaoki.simplecropview.a.b
        public void j() {
        }

        @Override // com.isseiaoki.simplecropview.a.b
        public void k() {
        }
    };
    private final Runnable cf = new Runnable() { // from class: com.isseiaoki.simplecropview.a.d.2
        @Override // java.lang.Runnable
        public void run() {
            long uptimeMillis = SystemClock.uptimeMillis() - d.this.cc;
            if (uptimeMillis <= d.this.ce) {
                d.this.bY.h(Math.min(d.this.mInterpolator.getInterpolation(((float) uptimeMillis) / ((float) d.this.ce)), 1.0f));
                return;
            }
            d.this.cd = false;
            d.this.bY.k();
            d.this.cb.shutdown();
        }
    };

    public d(Interpolator interpolator) {
        this.mInterpolator = interpolator;
    }

    @Override // com.isseiaoki.simplecropview.a.a
    public void a(long j) {
        if (j >= 0) {
            this.ce = j;
        } else {
            this.ce = 150L;
        }
        this.cd = true;
        this.bY.j();
        this.cc = SystemClock.uptimeMillis();
        this.cb = Executors.newSingleThreadScheduledExecutor();
        this.cb.scheduleAtFixedRate(this.cf, 0L, ca, TimeUnit.MILLISECONDS);
    }

    @Override // com.isseiaoki.simplecropview.a.a
    public void a(b bVar) {
        if (bVar != null) {
            this.bY = bVar;
        }
    }

    @Override // com.isseiaoki.simplecropview.a.a
    public void l() {
        this.cd = false;
        this.cb.shutdown();
        this.bY.k();
    }
}
