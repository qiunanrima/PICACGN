package io.socket.engineio.client;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: picacg_1.jar:io/socket/engineio/client/a.class */
public class a {
    public String xW;
    public String[] xX;
    public long xY;
    public long xZ;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(String str) throws JSONException {
        this(new JSONObject(str));
    }

    a(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray("upgrades");
        int length = jSONArray.length();
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = jSONArray.getString(i);
        }
        this.xW = jSONObject.getString("sid");
        this.xX = strArr;
        this.xY = jSONObject.getLong("pingInterval");
        this.xZ = jSONObject.getLong("pingTimeout");
    }
}
