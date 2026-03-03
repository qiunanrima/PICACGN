package com.just.agentweb.download;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v4.view.InputDeviceCompat;
import android.text.TextUtils;
import android.util.SparseArray;
import com.just.agentweb.ag;
import com.just.agentweb.download.DefaultDownloadImpl;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.UnknownFormatConversionException;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: picacg_1.jar:com/just/agentweb/download/f.class */
public class f extends AsyncTask<Void, Integer, Integer> implements AgentWebDownloader<DownloadTask>, b {
    private static final String TAG = "f";
    private static final SparseArray<String> gh = new SparseArray<>();
    private volatile DownloadTask fT;
    private volatile Throwable gb;
    private e ge;
    private volatile long fU = 0;
    private volatile long fV = -1;
    private long fW = 0;
    private long fX = 0;
    private long fY = 0;
    private volatile long fZ = 0;
    private volatile long ga = 0;
    private long gc = Long.MAX_VALUE;
    private int gd = 10000;
    private AtomicBoolean gf = new AtomicBoolean(false);
    private AtomicBoolean gg = new AtomicBoolean(false);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: picacg_1.jar:com/just/agentweb/download/f$a.class */
    public final class a extends RandomAccessFile {
        public a(File file) throws FileNotFoundException {
            super(file, "rw");
        }

        @Override // java.io.RandomAccessFile, java.io.DataOutput
        public void write(byte[] bArr, int i, int i2) throws IOException {
            super.write(bArr, i, i2);
            f.this.fU += i2;
            f.this.publishProgress(0);
        }
    }

    static {
        gh.append(1024, "Network connection error . ");
        gh.append(InputDeviceCompat.SOURCE_GAMEPAD, "Response code non-200 or non-206 . ");
        gh.append(1026, "Insufficient memory space . ");
        gh.append(1029, "Shutdown . ");
        gh.append(1027, "Download time is overtime . ");
        gh.append(1028, "The user canceled the download . ");
        gh.append(1031, "IO Error . ");
        gh.append(1283, "Service Unavailable . ");
        gh.append(1030, "Too many redirects . ");
        gh.append(512, "Download successful . ");
    }

    private int a(InputStream inputStream, RandomAccessFile randomAccessFile, boolean z) throws IOException {
        byte[] bArr = new byte[8192];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 8192);
        try {
            if (z) {
                randomAccessFile.seek(randomAccessFile.length());
            } else {
                randomAccessFile.seek(0L);
                this.fW = 0L;
            }
            do {
                if (!this.gf.get() && !this.gg.get()) {
                    int read = bufferedInputStream.read(bArr, 0, 8192);
                    if (read != -1) {
                        randomAccessFile.write(bArr, 0, read);
                    }
                }
                if (this.gf.get()) {
                    com.just.agentweb.h.a(randomAccessFile);
                    com.just.agentweb.h.a(bufferedInputStream);
                    com.just.agentweb.h.a(inputStream);
                    return 1028;
                } else if (this.gg.get()) {
                    com.just.agentweb.h.a(randomAccessFile);
                    com.just.agentweb.h.a(bufferedInputStream);
                    com.just.agentweb.h.a(inputStream);
                    return 1029;
                } else {
                    com.just.agentweb.h.a(randomAccessFile);
                    com.just.agentweb.h.a(bufferedInputStream);
                    com.just.agentweb.h.a(inputStream);
                    return 512;
                }
            } while (SystemClock.elapsedRealtime() - this.fZ <= this.gc);
            com.just.agentweb.h.a(randomAccessFile);
            com.just.agentweb.h.a(bufferedInputStream);
            com.just.agentweb.h.a(inputStream);
            return 1027;
        } catch (Throwable th) {
            com.just.agentweb.h.a(randomAccessFile);
            com.just.agentweb.h.a(bufferedInputStream);
            com.just.agentweb.h.a(inputStream);
            throw th;
        }
    }

    private long a(HttpURLConnection httpURLConnection, String str) {
        long parseLong;
        String headerField = httpURLConnection.getHeaderField(str);
        if (headerField == null) {
            parseLong = -1;
        } else {
            try {
                parseLong = Long.parseLong(headerField);
            } catch (NumberFormatException e) {
                if (ag.ap()) {
                    e.printStackTrace();
                    return -1L;
                }
                return -1L;
            }
        }
        return parseLong;
    }

    private HttpURLConnection a(URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(this.gd);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setReadTimeout(this.fT.ay());
        httpURLConnection.setRequestProperty("Accept", "application/*");
        httpURLConnection.setRequestProperty("Accept-Encoding", "identity");
        httpURLConnection.setRequestProperty("Connection", "close");
        httpURLConnection.setRequestProperty("Cookie", com.just.agentweb.c.b(url.toString()));
        Map<String, String> headers = this.fT.aT().getHeaders();
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                if (!TextUtils.isEmpty(entry.getKey()) && !TextUtils.isEmpty(entry.getValue())) {
                    httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
        }
        if (this.fT.aV().length() > 0) {
            String etag = getEtag();
            if (!TextUtils.isEmpty(etag)) {
                String str = TAG;
                ag.c(str, "Etag:" + etag);
                httpURLConnection.setRequestProperty("If-Match", getEtag());
            }
            StringBuilder sb = new StringBuilder();
            sb.append("bytes=");
            long length = this.fT.aV().length();
            this.fW = length;
            sb.append(length);
            sb.append("-");
            httpURLConnection.setRequestProperty("Range", sb.toString());
        }
        return httpURLConnection;
    }

    private void a(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("ETag");
        if (TextUtils.isEmpty(headerField)) {
            return;
        }
        String str = TAG;
        ag.c(str, "save etag:" + headerField);
        SharedPreferences.Editor edit = this.fT.getContext().getSharedPreferences("AgentWeb", 0).edit();
        edit.putString(this.fT.aV().getName(), headerField);
        edit.apply();
    }

    private boolean aY() {
        if (this.fT.getLength() - this.fT.aV().length() > com.just.agentweb.h.N()) {
            ag.d(TAG, " 空间不足");
            return false;
        }
        return true;
    }

    private boolean aZ() {
        return !this.fT.aU() ? com.just.agentweb.h.g(this.fT.getContext()) : com.just.agentweb.h.h(this.fT.getContext());
    }

    private void b(DownloadTask downloadTask) {
    }

    private boolean b(Integer num) {
        RuntimeException runtimeException;
        d aX = this.fT.aX();
        if (aX == null) {
            ag.d(TAG, "DownloadListener has been death");
            DefaultDownloadImpl.ExecuteTasksMap.aN().u(this.fT.aV().getPath());
            return false;
        }
        String absolutePath = this.fT.aV().getAbsolutePath();
        String url = this.fT.getUrl();
        if (num.intValue() <= 200) {
            runtimeException = null;
        } else if (this.gb == null) {
            runtimeException = new RuntimeException("Download failed ， cause:" + gh.get(num.intValue()));
        } else {
            runtimeException = this.gb;
        }
        return aX.c(absolutePath, url, runtimeException);
    }

    /* JADX WARN: Removed duplicated region for block: B:78:0x01cf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int ba() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 481
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.just.agentweb.download.f.ba():int");
    }

    private void bb() {
        Context applicationContext = this.fT.getContext().getApplicationContext();
        if (applicationContext == null || !this.fT.aA()) {
            return;
        }
        this.ge = new e(applicationContext, this.fT.getId());
        this.ge.a(this.fT);
    }

    private final void cancel() {
        this.gf.set(true);
    }

    private final void d(DownloadTask downloadTask) {
        b(downloadTask);
        this.fT = downloadTask;
        this.fV = this.fT.getLength();
        this.gc = this.fT.aB();
        this.gd = this.fT.aC();
        if (downloadTask.aD()) {
            executeOnExecutor(i.bd().be(), null);
        } else {
            execute(new Void[0]);
        }
    }

    private String getEtag() {
        String string = this.fT.getContext().getSharedPreferences("AgentWeb", 0).getString(this.fT.aV().getName(), "-1");
        if (TextUtils.isEmpty(string) || "-1".equals(string)) {
            return null;
        }
        return string;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a */
    public Integer doInBackground(Void... voidArr) {
        int i;
        try {
            this.fZ = SystemClock.elapsedRealtime();
        } catch (IOException e) {
            this.gb = e;
            if (ag.ap()) {
                e.printStackTrace();
            }
            i = 1031;
        }
        if (aY()) {
            if (aZ()) {
                i = ba();
                return Integer.valueOf(i);
            }
            return 1024;
        }
        return 1026;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a */
    public void onPostExecute(Integer num) {
        boolean b;
        DownloadTask downloadTask;
        try {
            com.just.agentweb.download.a.aG().s(this.fT.getUrl());
            if (this.fT.aX() != null) {
                this.fT.aX().a(this.fT.getUrl(), this.fW + this.fU, this.fV, this.fX);
            }
            if (this.fT.aX() != null) {
                this.fT.aX().b(this.fT.getUrl(), this);
            }
            String str = TAG;
            ag.c(str, "msg:" + gh.get(num.intValue()));
            b = b(num);
        } catch (Throwable th) {
            try {
                if (ag.ap()) {
                    th.printStackTrace();
                }
                if (this.fT == null) {
                    return;
                }
            } finally {
                if (this.fT != null) {
                    this.fT.destroy();
                }
            }
        }
        if (num.intValue() > 512) {
            if (this.ge != null) {
                this.ge.cancel();
            }
            if (downloadTask != null) {
                return;
            }
            return;
        }
        if (this.fT.aA()) {
            if (b) {
                this.ge.cancel();
                if (this.fT != null) {
                    this.fT.destroy();
                    return;
                }
                return;
            } else if (this.ge != null) {
                this.ge.aS();
            }
        }
        if (!this.fT.aF()) {
            if (this.fT != null) {
                this.fT.destroy();
                return;
            }
            return;
        }
        Intent c = com.just.agentweb.h.c(this.fT.getContext(), this.fT.aV());
        if (c == null) {
            if (this.fT != null) {
                this.fT.destroy();
                return;
            }
            return;
        }
        if (!(this.fT.getContext() instanceof Activity)) {
            c.addFlags(268435456);
        }
        this.fT.getContext().startActivity(c);
        if (this.fT == null) {
            return;
        }
        this.fT.destroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a */
    public void onProgressUpdate(Integer... numArr) {
        long elapsedRealtime;
        synchronized (this) {
            try {
                elapsedRealtime = SystemClock.elapsedRealtime();
                this.fX = elapsedRealtime - this.fZ;
                if (this.fX == 0) {
                    this.ga = 0L;
                } else {
                    this.ga = (this.fU * 1000) / this.fX;
                }
            } catch (UnknownFormatConversionException e) {
                e.printStackTrace();
            }
            if (elapsedRealtime - this.fY < 800) {
                return;
            }
            this.fY = elapsedRealtime;
            if (this.ge != null) {
                this.ge.f((int) ((((float) (this.fW + this.fU)) / Float.valueOf((float) this.fV).floatValue()) * 100.0f));
            }
            if (this.fT.aX() != null) {
                this.fT.aX().a(this.fT.getUrl(), this.fW + this.fU, this.fV, this.fX);
            }
        }
    }

    @Override // com.just.agentweb.download.b
    public void aI() {
        cancel();
    }

    public void c(DownloadTask downloadTask) {
        d(downloadTask);
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        super.onPreExecute();
        if (this.fT.aX() != null) {
            this.fT.aX().a(this.fT.getUrl(), this);
        }
        com.just.agentweb.download.a.aG().a(this.fT.getUrl(), this);
        bb();
        if (this.ge != null) {
            this.ge.aR();
        }
    }
}
