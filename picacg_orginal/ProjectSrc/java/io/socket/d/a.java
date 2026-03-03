package io.socket.d;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: picacg_1.jar:io/socket/d/a.class */
public class a {
    private static final Logger logger = Logger.getLogger(a.class.getName());

    private a() {
    }

    public static boolean e(Object obj) {
        return f(obj);
    }

    private static boolean f(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof byte[]) {
            return true;
        }
        if (obj instanceof JSONArray) {
            JSONArray jSONArray = (JSONArray) obj;
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                try {
                    if (f(jSONArray.isNull(i) ? null : jSONArray.get(i))) {
                        return true;
                    }
                } catch (JSONException e) {
                    logger.log(Level.WARNING, "An error occured while retrieving data from JSONArray", (Throwable) e);
                    return false;
                }
            }
            return false;
        } else if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                try {
                    if (f(jSONObject.get(keys.next()))) {
                        return true;
                    }
                } catch (JSONException e2) {
                    logger.log(Level.WARNING, "An error occured while retrieving data from JSONObject", (Throwable) e2);
                    return false;
                }
            }
            return false;
        } else {
            return false;
        }
    }
}
