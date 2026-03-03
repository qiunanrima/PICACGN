package com.qiniu.android.dns.util;

import java.util.LinkedHashMap;
import java.util.Map;
/* loaded from: picacg_1.jar:com/qiniu/android/dns/util/LruCache.class */
public final class LruCache<K, V> extends LinkedHashMap<K, V> {
    private int size;

    @Override // java.util.LinkedHashMap
    protected boolean removeEldestEntry(Map.Entry<K, V> entry) {
        return size() > this.size;
    }
}
