package com.orm;

import android.content.Context;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
/* loaded from: picacg_1.jar:com/orm/b.class */
public class b {
    private static b gO;
    private c gP;
    private Map<Object, Long> gQ = Collections.synchronizedMap(new WeakHashMap());

    private b(Context context) {
        this.gP = new c(context);
    }

    public static b bj() {
        if (gO != null) {
            return gO;
        }
        throw new NullPointerException("SugarContext has not been initialized properly. Call SugarContext.init(Context) in your Application.onCreate() method and SugarContext.terminate() in your Application.onTerminate() method.");
    }

    public static void bk() {
        if (gO == null) {
            return;
        }
        gO.bl();
    }

    private void bl() {
        if (this.gP != null) {
            this.gP.bo().close();
        }
    }

    public static void init(Context context) {
        gO = new b(context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public c bm() {
        return this.gP;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<Object, Long> bn() {
        return this.gQ;
    }
}
