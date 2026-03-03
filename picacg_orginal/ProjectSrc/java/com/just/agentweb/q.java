package com.just.agentweb;

import java.util.Map;
/* loaded from: picacg_1.jar:com/just/agentweb/q.class */
public class q {
    private Map<String, String> ez;

    public boolean ai() {
        return this.ez == null || this.ez.isEmpty();
    }

    public Map<String, String> getHeaders() {
        return this.ez;
    }

    public String toString() {
        return "HttpHeaders{mHeaders=" + this.ez + '}';
    }
}
