package io.socket.f;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: picacg_1.jar:io/socket/f/a.class */
public class a {
    private static final Logger logger = Logger.getLogger(a.class.getName());

    /* renamed from: io.socket.f.a$a  reason: collision with other inner class name */
    /* loaded from: picacg_1.jar:io/socket/f/a$a.class */
    public static class C0026a {
        public b zS;
        public byte[][] zT;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [T, java.lang.Object] */
    public static b a(b bVar, byte[][] bArr) {
        bVar.data = a(bVar.data, bArr);
        bVar.zU = -1;
        return bVar;
    }

    private static Object a(Object obj, List<byte[]> list) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof byte[]) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("_placeholder", true);
                jSONObject.put("num", list.size());
                list.add((byte[]) obj);
                return jSONObject;
            } catch (JSONException e) {
                logger.log(Level.WARNING, "An error occured while putting data to JSONObject", (Throwable) e);
                return null;
            }
        } else if (obj instanceof JSONArray) {
            JSONArray jSONArray = new JSONArray();
            JSONArray jSONArray2 = (JSONArray) obj;
            int length = jSONArray2.length();
            for (int i = 0; i < length; i++) {
                try {
                    jSONArray.put(i, a(jSONArray2.get(i), list));
                } catch (JSONException e2) {
                    logger.log(Level.WARNING, "An error occured while putting packet data to JSONObject", (Throwable) e2);
                    return null;
                }
            }
            return jSONArray;
        } else if (obj instanceof JSONObject) {
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = (JSONObject) obj;
            Iterator<String> keys = jSONObject3.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    jSONObject2.put(next, a(jSONObject3.get(next), list));
                } catch (JSONException e3) {
                    logger.log(Level.WARNING, "An error occured while putting data to JSONObject", (Throwable) e3);
                    return null;
                }
            }
            return jSONObject2;
        } else {
            return obj;
        }
    }

    private static Object a(Object obj, byte[][] bArr) {
        if (obj instanceof JSONArray) {
            JSONArray jSONArray = (JSONArray) obj;
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                try {
                    jSONArray.put(i, a(jSONArray.get(i), bArr));
                } catch (JSONException e) {
                    logger.log(Level.WARNING, "An error occured while putting packet data to JSONObject", (Throwable) e);
                    return null;
                }
            }
            return jSONArray;
        } else if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            if (jSONObject.optBoolean("_placeholder")) {
                int optInt = jSONObject.optInt("num", -1);
                byte[] bArr2 = null;
                if (optInt >= 0) {
                    bArr2 = null;
                    if (optInt < bArr.length) {
                        bArr2 = bArr[optInt];
                    }
                }
                return bArr2;
            }
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    jSONObject.put(next, a(jSONObject.get(next), bArr));
                } catch (JSONException e2) {
                    logger.log(Level.WARNING, "An error occured while putting data to JSONObject", (Throwable) e2);
                    return null;
                }
            }
            return jSONObject;
        } else {
            return obj;
        }
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [T, java.lang.Object] */
    public static C0026a f(b bVar) {
        ArrayList arrayList = new ArrayList();
        bVar.data = a(bVar.data, arrayList);
        bVar.zU = arrayList.size();
        C0026a c0026a = new C0026a();
        c0026a.zS = bVar;
        c0026a.zT = (byte[][]) arrayList.toArray(new byte[arrayList.size()]);
        return c0026a;
    }
}
