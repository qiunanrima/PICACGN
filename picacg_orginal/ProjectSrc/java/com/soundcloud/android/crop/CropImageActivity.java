package com.soundcloud.android.crop;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.opengl.GLES10;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import com.soundcloud.android.crop.ImageViewTouchBase;
import com.soundcloud.android.crop.c;
import com.soundcloud.android.crop.d;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.CountDownLatch;
/* loaded from: picacg_1.jar:com/soundcloud/android/crop/CropImageActivity.class */
public class CropImageActivity extends c {
    private Uri by;
    private Uri bz;
    private int exifRotation;
    private final Handler handler = new Handler();
    private int vf;
    private int vg;
    private int vh;
    private int vi;
    private boolean vj;
    private int vk;
    private e vl;
    private CropImageView vm;
    private HighlightView vn;

    /* loaded from: picacg_1.jar:com/soundcloud/android/crop/CropImageActivity$a.class */
    private class a {
        private a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void em() {
            int i;
            int i2;
            int i3;
            if (CropImageActivity.this.vl == null) {
                return;
            }
            HighlightView highlightView = new HighlightView(CropImageActivity.this.vm);
            int width = CropImageActivity.this.vl.getWidth();
            int height = CropImageActivity.this.vl.getHeight();
            Rect rect = new Rect(0, 0, width, height);
            int min = (Math.min(width, height) * 4) / 5;
            if (CropImageActivity.this.vf == 0 || CropImageActivity.this.vg == 0) {
                i = min;
            } else if (CropImageActivity.this.vf > CropImageActivity.this.vg) {
                i = (CropImageActivity.this.vg * min) / CropImageActivity.this.vf;
            } else {
                i = min;
                min = (CropImageActivity.this.vf * min) / CropImageActivity.this.vg;
            }
            RectF rectF = new RectF((width - min) / 2, (height - i) / 2, i2 + min, i3 + i);
            Matrix unrotatedMatrix = CropImageActivity.this.vm.getUnrotatedMatrix();
            boolean z = false;
            if (CropImageActivity.this.vf != 0) {
                z = false;
                if (CropImageActivity.this.vg != 0) {
                    z = true;
                }
            }
            highlightView.a(unrotatedMatrix, rect, rectF, z);
            CropImageActivity.this.vm.c(highlightView);
        }

        public void en() {
            CropImageActivity.this.handler.post(new Runnable() { // from class: com.soundcloud.android.crop.CropImageActivity.a.1
                @Override // java.lang.Runnable
                public void run() {
                    a.this.em();
                    CropImageActivity.this.vm.invalidate();
                    if (CropImageActivity.this.vm.vt.size() == 1) {
                        CropImageActivity.this.vn = CropImageActivity.this.vm.vt.get(0);
                        CropImageActivity.this.vn.G(true);
                    }
                }
            });
        }
    }

    private Bitmap a(Rect rect, int i, int i2) {
        Bitmap bitmap;
        InputStream inputStream;
        InputStream openInputStream;
        Bitmap bitmap2;
        Bitmap bitmap3;
        Bitmap bitmap4;
        int width;
        int height;
        Rect rect2;
        Bitmap bitmap5;
        ek();
        InputStream inputStream2 = null;
        try {
            try {
                openInputStream = getContentResolver().openInputStream(this.by);
                bitmap2 = null;
                bitmap3 = null;
                try {
                    try {
                        BitmapRegionDecoder newInstance = BitmapRegionDecoder.newInstance(openInputStream, false);
                        width = newInstance.getWidth();
                        height = newInstance.getHeight();
                        if (this.exifRotation != 0) {
                            Matrix matrix = new Matrix();
                            matrix.setRotate(-this.exifRotation);
                            RectF rectF = new RectF();
                            matrix.mapRect(rectF, new RectF(rect));
                            float f = 0.0f;
                            float f2 = rectF.left < 0.0f ? width : 0.0f;
                            if (rectF.top < 0.0f) {
                                f = height;
                            }
                            rectF.offset(f2, f);
                            rect2 = new Rect((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
                        } else {
                            rect2 = rect;
                        }
                        try {
                            bitmap5 = newInstance.decodeRegion(rect2, new BitmapFactory.Options());
                        } catch (IllegalArgumentException e) {
                            e = e;
                            bitmap5 = null;
                        }
                    } catch (Throwable th) {
                        th = th;
                        inputStream2 = openInputStream;
                        com.soundcloud.android.crop.a.b(inputStream2);
                        throw th;
                    }
                } catch (IOException e2) {
                    e = e2;
                } catch (OutOfMemoryError e3) {
                    bitmap4 = bitmap2;
                    e = e3;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e4) {
            e = e4;
            bitmap = null;
            inputStream = null;
        } catch (OutOfMemoryError e5) {
            e = e5;
            bitmap = null;
            inputStream = null;
        }
        try {
        } catch (IOException e6) {
            e = e6;
            bitmap3 = bitmap5;
            inputStream = openInputStream;
            bitmap = bitmap3;
            InputStream inputStream3 = inputStream;
            StringBuilder sb = new StringBuilder();
            InputStream inputStream4 = inputStream;
            sb.append("Error cropping image: ");
            InputStream inputStream5 = inputStream;
            sb.append(e.getMessage());
            InputStream inputStream6 = inputStream;
            b.a(sb.toString(), e);
            InputStream inputStream7 = inputStream;
            b(e);
            com.soundcloud.android.crop.a.b(inputStream);
            return bitmap;
        } catch (IllegalArgumentException e7) {
            e = e7;
            Bitmap bitmap6 = bitmap5;
            Bitmap bitmap7 = bitmap5;
            StringBuilder sb2 = new StringBuilder();
            Bitmap bitmap8 = bitmap5;
            sb2.append("Rectangle ");
            Bitmap bitmap9 = bitmap5;
            sb2.append(rect2);
            Bitmap bitmap10 = bitmap5;
            sb2.append(" is outside of the image (");
            Bitmap bitmap11 = bitmap5;
            sb2.append(width);
            Bitmap bitmap12 = bitmap5;
            sb2.append(",");
            Bitmap bitmap13 = bitmap5;
            sb2.append(height);
            Bitmap bitmap14 = bitmap5;
            sb2.append(",");
            Bitmap bitmap15 = bitmap5;
            sb2.append(this.exifRotation);
            Bitmap bitmap16 = bitmap5;
            sb2.append(")");
            Bitmap bitmap17 = bitmap5;
            bitmap2 = bitmap5;
            bitmap3 = bitmap5;
            throw new IllegalArgumentException(sb2.toString(), e);
        } catch (OutOfMemoryError e8) {
            e = e8;
            bitmap4 = bitmap5;
            inputStream = openInputStream;
            bitmap = bitmap4;
            InputStream inputStream8 = inputStream;
            StringBuilder sb3 = new StringBuilder();
            InputStream inputStream9 = inputStream;
            sb3.append("OOM cropping image: ");
            InputStream inputStream10 = inputStream;
            sb3.append(e.getMessage());
            InputStream inputStream11 = inputStream;
            b.a(sb3.toString(), e);
            InputStream inputStream12 = inputStream;
            b(e);
            com.soundcloud.android.crop.a.b(inputStream);
            return bitmap;
        }
        if (rect2.width() <= i) {
            if (rect2.height() > i2) {
            }
            com.soundcloud.android.crop.a.b(openInputStream);
            bitmap = bitmap5;
            return bitmap;
        }
        Matrix matrix2 = new Matrix();
        matrix2.postScale(i / rect2.width(), i2 / rect2.height());
        bitmap5 = Bitmap.createBitmap(bitmap5, 0, 0, bitmap5.getWidth(), bitmap5.getHeight(), matrix2, true);
        com.soundcloud.android.crop.a.b(openInputStream);
        bitmap = bitmap5;
        return bitmap;
    }

    private void b(Throwable th) {
        setResult(404, new Intent().putExtra("error", th));
    }

    private int d(Uri uri) throws IOException {
        InputStream inputStream;
        BitmapFactory.Options options = new BitmapFactory.Options();
        int i = 1;
        options.inJustDecodeBounds = true;
        try {
            inputStream = getContentResolver().openInputStream(uri);
        } catch (Throwable th) {
            th = th;
            inputStream = null;
        }
        try {
            BitmapFactory.decodeStream(inputStream, null, options);
            com.soundcloud.android.crop.a.b(inputStream);
            int eg = eg();
            while (true) {
                if (options.outHeight / i <= eg && options.outWidth / i <= eg) {
                    return i;
                }
                i <<= 1;
            }
        } catch (Throwable th2) {
            th = th2;
            com.soundcloud.android.crop.a.b(inputStream);
            throw th;
        }
    }

    private void e(Uri uri) {
        setResult(-1, new Intent().putExtra("output", uri));
    }

    @TargetApi(19)
    private void ed() {
        requestWindowFeature(1);
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().clearFlags(67108864);
        }
    }

    private void ee() {
        setContentView(d.c.crop__activity_crop);
        this.vm = (CropImageView) findViewById(d.b.crop_image);
        this.vm.context = this;
        this.vm.setRecycler(new ImageViewTouchBase.a() { // from class: com.soundcloud.android.crop.CropImageActivity.1
            @Override // com.soundcloud.android.crop.ImageViewTouchBase.a
            public void i(Bitmap bitmap) {
                bitmap.recycle();
                System.gc();
            }
        });
        findViewById(d.b.btn_cancel).setOnClickListener(new View.OnClickListener() { // from class: com.soundcloud.android.crop.CropImageActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CropImageActivity.this.setResult(0);
                CropImageActivity.this.finish();
            }
        });
        findViewById(d.b.btn_done).setOnClickListener(new View.OnClickListener() { // from class: com.soundcloud.android.crop.CropImageActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CropImageActivity.this.ej();
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [java.io.Closeable] */
    private void ef() {
        Intent intent;
        InputStream inputStream;
        Intent intent2 = getIntent();
        Bundle extras = intent2.getExtras();
        if (extras != null) {
            this.vf = extras.getInt("aspect_x");
            this.vg = extras.getInt("aspect_y");
            this.vh = extras.getInt("max_x");
            this.vi = extras.getInt("max_y");
            this.bz = (Uri) extras.getParcelable("output");
        }
        this.by = intent2.getData();
        if (this.by != null) {
            this.exifRotation = com.soundcloud.android.crop.a.h(com.soundcloud.android.crop.a.a(this, getContentResolver(), this.by));
            try {
                try {
                    this.vk = d(this.by);
                    inputStream = getContentResolver().openInputStream(this.by);
                    try {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = this.vk;
                        intent2 = inputStream;
                        this.vl = new e(BitmapFactory.decodeStream(inputStream, null, options), this.exifRotation);
                    } catch (IOException e) {
                        e = e;
                        InputStream inputStream2 = inputStream;
                        StringBuilder sb = new StringBuilder();
                        InputStream inputStream3 = inputStream;
                        sb.append("Error reading image: ");
                        InputStream inputStream4 = inputStream;
                        sb.append(e.getMessage());
                        InputStream inputStream5 = inputStream;
                        b.a(sb.toString(), e);
                        intent2 = inputStream;
                        b(e);
                        com.soundcloud.android.crop.a.b(inputStream);
                    } catch (OutOfMemoryError e2) {
                        e = e2;
                        InputStream inputStream6 = inputStream;
                        StringBuilder sb2 = new StringBuilder();
                        InputStream inputStream7 = inputStream;
                        sb2.append("OOM reading image: ");
                        InputStream inputStream8 = inputStream;
                        sb2.append(e.getMessage());
                        InputStream inputStream9 = inputStream;
                        b.a(sb2.toString(), e);
                        intent2 = inputStream;
                        b(e);
                        com.soundcloud.android.crop.a.b(inputStream);
                    }
                } catch (Throwable th) {
                    intent = intent2;
                    th = th;
                    com.soundcloud.android.crop.a.b(intent);
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
                inputStream = null;
            } catch (OutOfMemoryError e4) {
                e = e4;
                inputStream = null;
            } catch (Throwable th2) {
                th = th2;
                intent = null;
                com.soundcloud.android.crop.a.b(intent);
                throw th;
            }
            com.soundcloud.android.crop.a.b(inputStream);
        }
    }

    private int eg() {
        int eh = eh();
        if (eh == 0) {
            return 2048;
        }
        return Math.min(eh, 4096);
    }

    private int eh() {
        int[] iArr = new int[1];
        GLES10.glGetIntegerv(3379, iArr, 0);
        return iArr[0];
    }

    private void ei() {
        if (isFinishing()) {
            return;
        }
        this.vm.a(this.vl, true);
        com.soundcloud.android.crop.a.a(this, null, getResources().getString(d.C0015d.crop__wait), new Runnable() { // from class: com.soundcloud.android.crop.CropImageActivity.4
            @Override // java.lang.Runnable
            public void run() {
                final CountDownLatch countDownLatch = new CountDownLatch(1);
                CropImageActivity.this.handler.post(new Runnable() { // from class: com.soundcloud.android.crop.CropImageActivity.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (CropImageActivity.this.vm.getScale() == 1.0f) {
                            CropImageActivity.this.vm.ep();
                        }
                        countDownLatch.countDown();
                    }
                });
                try {
                    countDownLatch.await();
                    new a().en();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, this.handler);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x005a, code lost:
        if (r0 > r6.vi) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void ej() {
        /*
            Method dump skipped, instructions count: 231
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.soundcloud.android.crop.CropImageActivity.ej():void");
    }

    private void ek() {
        this.vm.clear();
        if (this.vl != null) {
            this.vl.recycle();
        }
        System.gc();
    }

    private void g(final Bitmap bitmap) {
        if (bitmap != null) {
            com.soundcloud.android.crop.a.a(this, null, getResources().getString(d.C0015d.crop__saving), new Runnable() { // from class: com.soundcloud.android.crop.CropImageActivity.5
                @Override // java.lang.Runnable
                public void run() {
                    CropImageActivity.this.h(bitmap);
                }
            }, this.handler);
        } else {
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(final Bitmap bitmap) {
        OutputStream outputStream;
        if (this.bz != null) {
            OutputStream outputStream2 = null;
            try {
                try {
                    outputStream = getContentResolver().openOutputStream(this.bz);
                    if (outputStream != null) {
                        try {
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
                        } catch (IOException e) {
                            e = e;
                            b(e);
                            OutputStream outputStream3 = outputStream;
                            OutputStream outputStream4 = outputStream;
                            StringBuilder sb = new StringBuilder();
                            OutputStream outputStream5 = outputStream;
                            sb.append("Cannot open file: ");
                            OutputStream outputStream6 = outputStream;
                            sb.append(this.bz);
                            outputStream2 = outputStream;
                            b.a(sb.toString(), e);
                            com.soundcloud.android.crop.a.b(outputStream);
                            com.soundcloud.android.crop.a.a(com.soundcloud.android.crop.a.a(this, getContentResolver(), this.by), com.soundcloud.android.crop.a.a(this, getContentResolver(), this.bz));
                            e(this.bz);
                            this.handler.post(new Runnable() { // from class: com.soundcloud.android.crop.CropImageActivity.6
                                @Override // java.lang.Runnable
                                public void run() {
                                    CropImageActivity.this.vm.clear();
                                    bitmap.recycle();
                                }
                            });
                            finish();
                        } catch (Throwable th) {
                            th = th;
                            outputStream2 = outputStream;
                            com.soundcloud.android.crop.a.b(outputStream2);
                            throw th;
                        }
                    }
                    com.soundcloud.android.crop.a.b(outputStream);
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e2) {
                e = e2;
                outputStream = null;
            }
            com.soundcloud.android.crop.a.a(com.soundcloud.android.crop.a.a(this, getContentResolver(), this.by), com.soundcloud.android.crop.a.a(this, getContentResolver(), this.bz));
            e(this.bz);
        }
        this.handler.post(new Runnable() { // from class: com.soundcloud.android.crop.CropImageActivity.6
            @Override // java.lang.Runnable
            public void run() {
                CropImageActivity.this.vm.clear();
                bitmap.recycle();
            }
        });
        finish();
    }

    @Override // com.soundcloud.android.crop.c
    public /* bridge */ /* synthetic */ void a(c.b bVar) {
        super.a(bVar);
    }

    @Override // com.soundcloud.android.crop.c
    public /* bridge */ /* synthetic */ void b(c.b bVar) {
        super.b(bVar);
    }

    public boolean el() {
        return this.vj;
    }

    @Override // com.soundcloud.android.crop.c, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ed();
        ee();
        ef();
        if (this.vl == null) {
            finish();
        } else {
            ei();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.soundcloud.android.crop.c, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.vl != null) {
            this.vl.recycle();
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onSearchRequested() {
        return false;
    }
}
