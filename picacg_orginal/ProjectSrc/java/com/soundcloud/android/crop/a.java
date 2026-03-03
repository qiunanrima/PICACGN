package com.soundcloud.android.crop;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.soundcloud.android.crop.c;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/* loaded from: picacg_1.jar:com/soundcloud/android/crop/a.class */
class a {

    /* renamed from: com.soundcloud.android.crop.a$a  reason: collision with other inner class name */
    /* loaded from: picacg_1.jar:com/soundcloud/android/crop/a$a.class */
    private static class RunnableC0014a extends c.a implements Runnable {
        private final Handler handler;
        private final ProgressDialog vA;
        private final Runnable vB;
        private final Runnable vC = new Runnable() { // from class: com.soundcloud.android.crop.a.a.1
            @Override // java.lang.Runnable
            public void run() {
                RunnableC0014a.this.vz.a(RunnableC0014a.this);
                if (RunnableC0014a.this.vA.getWindow() != null) {
                    RunnableC0014a.this.vA.dismiss();
                }
            }
        };
        private final c vz;

        public RunnableC0014a(c cVar, Runnable runnable, ProgressDialog progressDialog, Handler handler) {
            this.vz = cVar;
            this.vA = progressDialog;
            this.vB = runnable;
            this.vz.b(this);
            this.handler = handler;
        }

        @Override // com.soundcloud.android.crop.c.a, com.soundcloud.android.crop.c.b
        public void a(c cVar) {
            this.vC.run();
            this.handler.removeCallbacks(this.vC);
        }

        @Override // com.soundcloud.android.crop.c.a, com.soundcloud.android.crop.c.b
        public void b(c cVar) {
            this.vA.hide();
        }

        @Override // com.soundcloud.android.crop.c.a, com.soundcloud.android.crop.c.b
        public void c(c cVar) {
            this.vA.show();
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.vB.run();
            } finally {
                this.handler.post(this.vC);
            }
        }
    }

    @Nullable
    public static File a(Context context, ContentResolver contentResolver, Uri uri) {
        Cursor cursor;
        if (uri == null) {
            return null;
        }
        if ("file".equals(uri.getScheme())) {
            return new File(uri.getPath());
        }
        try {
            if ("content".equals(uri.getScheme())) {
                try {
                    cursor = contentResolver.query(uri, new String[]{"_data", "_display_name"}, null, null, null);
                    if (cursor != null) {
                        try {
                            if (cursor.moveToFirst()) {
                                int columnIndex = uri.toString().startsWith("content://com.google.android.gallery3d") ? cursor.getColumnIndex("_display_name") : cursor.getColumnIndex("_data");
                                if (columnIndex != -1) {
                                    String string = cursor.getString(columnIndex);
                                    if (!TextUtils.isEmpty(string)) {
                                        File file = new File(string);
                                        if (cursor != null) {
                                            cursor.close();
                                        }
                                        return file;
                                    }
                                }
                            }
                        } catch (IllegalArgumentException e) {
                            File b = b(context, contentResolver, uri);
                            if (cursor != null) {
                                cursor.close();
                            }
                            return b;
                        } catch (SecurityException e2) {
                            if (cursor == null) {
                                return null;
                            }
                            cursor.close();
                            return null;
                        } catch (Throwable th) {
                            th = th;
                            if (cursor != null) {
                                cursor.close();
                            }
                            throw th;
                        }
                    }
                    if (cursor == null) {
                        return null;
                    }
                } catch (IllegalArgumentException e3) {
                    cursor = null;
                } catch (SecurityException e4) {
                    cursor = null;
                }
                cursor.close();
                return null;
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
    }

    public static void a(c cVar, String str, String str2, Runnable runnable, Handler handler) {
        new Thread(new RunnableC0014a(cVar, runnable, ProgressDialog.show(cVar, str, str2, true, false), handler)).start();
    }

    public static boolean a(File file, File file2) {
        if (file == null || file2 == null) {
            return false;
        }
        try {
            ExifInterface exifInterface = new ExifInterface(file.getAbsolutePath());
            ExifInterface exifInterface2 = new ExifInterface(file2.getAbsolutePath());
            exifInterface2.setAttribute("Orientation", exifInterface.getAttribute("Orientation"));
            exifInterface2.saveAttributes();
            return true;
        } catch (IOException e) {
            b.a("Error copying Exif data", e);
            return false;
        }
    }

    private static String ay(Context context) throws IOException {
        return File.createTempFile("image", "tmp", context.getCacheDir()).getAbsolutePath();
    }

    @Nullable
    private static File b(Context context, ContentResolver contentResolver, Uri uri) {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2;
        String ay;
        if (uri == null) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(contentResolver.openFileDescriptor(uri, "r").getFileDescriptor());
            try {
                ay = ay(context);
                fileOutputStream2 = new FileOutputStream(ay);
            } catch (IOException e) {
                fileOutputStream2 = null;
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
            }
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        File file = new File(ay);
                        b(fileInputStream);
                        b(fileOutputStream2);
                        return file;
                    }
                    fileOutputStream2.write(bArr, 0, read);
                }
            } catch (IOException e2) {
                b(fileInputStream);
                b(fileOutputStream2);
                return null;
            } catch (Throwable th2) {
                fileOutputStream = fileOutputStream2;
                th = th2;
                b(fileInputStream);
                b(fileOutputStream);
                throw th;
            }
        } catch (IOException e3) {
            fileOutputStream2 = null;
            fileInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            fileOutputStream = null;
        }
    }

    public static void b(@Nullable Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (Throwable th) {
        }
    }

    public static int h(File file) {
        if (file == null) {
            return 0;
        }
        try {
            int attributeInt = new ExifInterface(file.getAbsolutePath()).getAttributeInt("Orientation", 0);
            if (attributeInt != 3) {
                if (attributeInt != 6) {
                    return attributeInt != 8 ? 0 : 270;
                }
                return 90;
            }
            return 180;
        } catch (IOException e) {
            b.a("Error getting Exif data", e);
            return 0;
        }
    }
}
