package com.picacomic.fregata.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.picacomic.fregata.b.d;
import com.picacomic.fregata.objects.ComicPageObject;
import com.picacomic.fregata.objects.databaseTable.DbComicDetailObject;
import com.picacomic.fregata.objects.databaseTable.DownloadComicEpisodeObject;
import com.picacomic.fregata.objects.databaseTable.DownloadComicPageObject;
import com.picacomic.fregata.objects.responses.DataClass.ComicPageResponse.ComicPagesResponse;
import com.picacomic.fregata.objects.responses.GeneralResponse;
import com.picacomic.fregata.utils.c;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.f;
import com.picacomic.fregata.utils.g;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import retrofit2.Call;
import retrofit2.Response;
/* loaded from: picacg_1.jar:com/picacomic/fregata/services/DownloadService.class */
public class DownloadService extends IntentService {
    public static final String TAG = "DownloadService";
    public static final String tN = DownloadService.class.getName() + ".progress_update";
    Call<GeneralResponse<ComicPagesResponse>> hZ;
    private ExecutorService tO;
    private CompletionService<b> tP;
    private LocalBroadcastManager tQ;
    private List<a> tR;
    boolean tS;
    int tT;
    long tU;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: picacg_1.jar:com/picacomic/fregata/services/DownloadService$a.class */
    public class a implements Callable<b> {
        String comicId;
        String episodeId;

        public a(String str, String str2) {
            this.comicId = str;
            this.episodeId = str2;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: dR */
        public b call() throws Exception {
            DownloadService.this.B(this.comicId, this.episodeId);
            String str = DownloadService.TAG;
            f.E(str, "Finish TaskId " + this.episodeId);
            return new b(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: picacg_1.jar:com/picacomic/fregata/services/DownloadService$b.class */
    public class b {
        boolean tW;

        private b(boolean z) {
            this.tW = z;
        }
    }

    public DownloadService() {
        super(TAG);
        this.tS = false;
        this.tT = 4000;
        this.tO = Executors.newFixedThreadPool(1);
        this.tP = new ExecutorCompletionService(this.tO);
        this.tR = new ArrayList();
    }

    public DownloadService(String str) {
        super(str);
        this.tS = false;
        this.tT = 4000;
        this.tO = Executors.newFixedThreadPool(1);
        this.tP = new ExecutorCompletionService(this.tO);
        this.tR = new ArrayList();
    }

    private void a(String str, String str2, String str3, String str4, int i, int i2) {
        synchronized (this) {
            this.tU = System.currentTimeMillis();
            Intent intent = new Intent();
            intent.setAction(tN);
            intent.putExtra("COMIC_ID", str);
            intent.putExtra("EPISODE_ID", str2);
            intent.putExtra("COMIC_NAME", str3);
            intent.putExtra("EPISODE_TITLE", str4);
            intent.putExtra("PROGRESS_CURRENT", i);
            intent.putExtra("PROGRESS_TOTAL", i2);
            this.tQ.sendBroadcast(intent);
        }
    }

    public void B(String str, String str2) {
        int i;
        Response<GeneralResponse<ComicPagesResponse>> execute;
        int total;
        int limit;
        String title;
        int i2;
        f.D(TAG, "Call Comic Page ?");
        d dVar = new d(this);
        DbComicDetailObject aw = com.picacomic.fregata.utils.b.aw(str);
        String title2 = aw != null ? aw.getTitle() : "";
        DownloadComicEpisodeObject ay = com.picacomic.fregata.utils.b.ay(str2);
        int i3 = 1;
        boolean z = false;
        int i4 = 0;
        while (true) {
            int i5 = i4;
            if (i5 >= i3) {
                return;
            }
            if (ay != null) {
                ay.setStatus(2);
                ay.save();
            } else {
                f.D(TAG, "DB error, missing DownloadComicEpisodeObject, episodeId = " + str2);
            }
            int i6 = i5 + 1;
            this.hZ = dVar.dO().e(e.z(this), str2, i6);
            try {
                execute = this.hZ.execute();
            } catch (IOException e) {
                e = e;
                i = i3;
            }
            if (execute != null && execute.code() == 200) {
                f.F(TAG, execute.body().data.getPages().toString());
                i = execute.body().data.getPages().getPages();
                try {
                    total = execute.body().data.getPages().getTotal();
                    limit = execute.body().data.getPages().getLimit();
                    title = execute.body().data.getEp().getTitle();
                    if (!z && ay != null) {
                        try {
                            ay.setTotal(total);
                            ay.save();
                            z = true;
                        } catch (IOException e2) {
                            e = e2;
                            e.printStackTrace();
                            i3 = i;
                            i4 = i6;
                        }
                    }
                    i2 = i6;
                } catch (IOException e3) {
                    e = e3;
                }
                try {
                    if (execute.body().data != null && execute.body().data.getPages().getDocs() != null) {
                        if (ay != null) {
                            ay.setStatus(2);
                            ay.save();
                        } else {
                            String str3 = TAG;
                            StringBuilder sb = new StringBuilder();
                            sb.append("DB error, missing DownloadComicEpisodeObject, episodeId = ");
                            sb.append(str2);
                            f.D(str3, sb.toString());
                        }
                        int i7 = 0;
                        while (true) {
                            int i8 = i7;
                            if (i8 >= execute.body().data.getPages().getDocs().size()) {
                                break;
                            }
                            ComicPageObject comicPageObject = execute.body().data.getPages().getDocs().get(i8);
                            String ec = c.ec();
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(str2);
                            sb2.append("/");
                            sb2.append(comicPageObject.getMedia().getPath());
                            File file = new File(ec, sb2.toString());
                            if (!file.exists() && !file.getParentFile().exists()) {
                                file.getParentFile().mkdirs();
                            }
                            i2 = i6;
                            try {
                                g.a(g.b(comicPageObject.getMedia()), file, false);
                            } catch (Exception e4) {
                                e4.printStackTrace();
                            }
                            DownloadComicPageObject downloadComicPageObject = new DownloadComicPageObject(str, str2, ec, comicPageObject);
                            com.picacomic.fregata.utils.b.a(downloadComicPageObject);
                            String str4 = TAG;
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append("Downloaded ");
                            sb3.append(str2);
                            sb3.append(" Image");
                            int i9 = i8 + 1;
                            sb3.append(i9);
                            sb3.append("/");
                            sb3.append(total);
                            sb3.append(": ");
                            sb3.append(file.getAbsolutePath());
                            sb3.append("\n");
                            sb3.append(downloadComicPageObject);
                            f.F(str4, sb3.toString());
                            try {
                                a(str, str2, title2, title, (i5 * limit) + i8 + 1, total);
                                i7 = i9;
                            } catch (IOException e5) {
                                e = e5;
                                e.printStackTrace();
                                i3 = i;
                                i4 = i6;
                            }
                        }
                        if (ay != null) {
                            ay.setStatus(4);
                            ay.save();
                            i6 = i6;
                        } else {
                            f.D(TAG, "DB error, missing DownloadComicEpisodeObject, episodeId = " + str2);
                            i6 = i6;
                        }
                    }
                } catch (IOException e6) {
                    e = e6;
                    i6 = i2;
                    e.printStackTrace();
                    i3 = i;
                    i4 = i6;
                }
                i3 = i;
            }
            i4 = i6;
        }
    }

    public void dQ() {
        try {
            b bVar = this.tP.take().get();
            if (bVar == null || !bVar.tW) {
                return;
            }
            String str = TAG;
            f.E(str, "Download Success /" + this.tR.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    @Override // android.app.IntentService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        f.E(TAG, "Run Destroy and ShutDown");
        if (this.hZ != null) {
            this.hZ.cancel();
        }
        this.tO.shutdown();
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        String stringExtra = intent.getStringExtra("COMIC_ID");
        String stringExtra2 = intent.getStringExtra("EPISODE_ID");
        if (this.tS) {
            a aVar = new a(stringExtra, stringExtra2);
            this.tR.add(aVar);
            f.E(TAG, "Add Intent Task" + stringExtra2 + " Total Task " + this.tR.size());
            this.tP.submit(aVar);
            f.E(TAG, "Submit Intent Task" + stringExtra2 + " Total Task " + this.tR.size());
            dQ();
            f.E(TAG, "Finish intent Tasks - Total:" + this.tR.size());
            return;
        }
        this.tS = true;
        this.tQ = LocalBroadcastManager.getInstance(this);
        this.tR.add(new a(stringExtra, stringExtra2));
        for (a aVar2 : this.tR) {
            this.tP.submit(aVar2);
            f.E(TAG, "Submit Task " + this.tT + " Total Task " + this.tR.size());
        }
        for (int i = 0; i < this.tR.size(); i++) {
            dQ();
        }
        f.E(TAG, "Finish All Tasks - Total:" + this.tR.size());
    }

    @Override // android.app.IntentService, android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        boolean z = this.tS;
        return super.onStartCommand(intent, i, i2);
    }
}
