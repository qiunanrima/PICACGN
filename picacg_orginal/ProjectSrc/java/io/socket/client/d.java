package io.socket.client;

import io.socket.b.a;
import io.socket.client.Manager;
import io.socket.client.c;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: picacg_1.jar:io/socket/client/d.class */
public class d extends io.socket.b.a {
    private static final Logger logger = Logger.getLogger(d.class.getName());
    protected static Map<String, Integer> xA = new HashMap<String, Integer>() { // from class: io.socket.client.Socket$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            put("connect", 1);
            put("connect_error", 1);
            put("connect_timeout", 1);
            put("connecting", 1);
            put("disconnect", 1);
            put("error", 1);
            put("reconnect", 1);
            put("reconnect_attempt", 1);
            put("reconnect_failed", 1);
            put("reconnect_error", 1);
            put("reconnecting", 1);
            put("ping", 1);
            put("pong", 1);
        }
    };
    private volatile boolean connected;
    String id;
    private Queue<c.a> wV;
    private int xB;
    private String xC;
    private Manager xD;
    private Map<Integer, a> xE = new HashMap();
    private final Queue<List<Object>> xF = new LinkedList();
    private final Queue<io.socket.f.b<JSONArray>> xG = new LinkedList();

    public d(Manager manager, String str) {
        this.xD = manager;
        this.xC = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONArray a(JSONArray jSONArray, int i) {
        Object obj;
        JSONArray jSONArray2 = new JSONArray();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            if (i2 != i) {
                try {
                    obj = jSONArray.get(i2);
                } catch (JSONException e) {
                    obj = null;
                }
                jSONArray2.put(obj);
            }
        }
        return jSONArray2;
    }

    private static Object[] a(JSONArray jSONArray) {
        Object obj;
        int length = jSONArray.length();
        Object[] objArr = new Object[length];
        for (int i = 0; i < length; i++) {
            try {
                obj = jSONArray.get(i);
            } catch (JSONException e) {
                logger.log(Level.WARNING, "An error occured while retrieving data from JSONArray", (Throwable) e);
                obj = null;
            }
            if (JSONObject.NULL.equals(obj)) {
                obj = null;
            }
            objArr[i] = obj;
        }
        return objArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aG(String str) {
        logger.fine(String.format("close (%s)", str));
        this.connected = false;
        this.id = null;
        b("disconnect", str);
    }

    private a ai(final int i) {
        final boolean[] zArr = {false};
        return new a() { // from class: io.socket.client.d.3
            @Override // io.socket.client.a
            public void a(final Object... objArr) {
                io.socket.g.a.b(new Runnable() { // from class: io.socket.client.d.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (zArr[0]) {
                            return;
                        }
                        zArr[0] = true;
                        d.logger.fine(String.format("sending ack %s", objArr.length != 0 ? objArr : null));
                        JSONArray jSONArray = new JSONArray();
                        for (Object obj : objArr) {
                            jSONArray.put(obj);
                        }
                        io.socket.f.b bVar = new io.socket.f.b(io.socket.d.a.e(jSONArray) ? 6 : 3, jSONArray);
                        bVar.id = i;
                        this.b(bVar);
                    }
                });
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(io.socket.f.b bVar) {
        bVar.xC = this.xC;
        this.xD.b(bVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(io.socket.f.b<?> bVar) {
        if (this.xC.equals(bVar.xC)) {
            switch (bVar.type) {
                case 0:
                    eK();
                    return;
                case 1:
                    eM();
                    return;
                case 2:
                    d((io.socket.f.b<JSONArray>) bVar);
                    return;
                case 3:
                    e((io.socket.f.b<JSONArray>) bVar);
                    return;
                case 4:
                    b("error", bVar.data);
                    return;
                case 5:
                    d((io.socket.f.b<JSONArray>) bVar);
                    return;
                case 6:
                    e((io.socket.f.b<JSONArray>) bVar);
                    return;
                default:
                    return;
            }
        }
    }

    private void d(io.socket.f.b<JSONArray> bVar) {
        ArrayList arrayList = new ArrayList(Arrays.asList(a(bVar.data)));
        logger.fine(String.format("emitting event %s", arrayList));
        if (bVar.id >= 0) {
            logger.fine("attaching ack callback to event");
            arrayList.add(ai(bVar.id));
        }
        if (!this.connected) {
            this.xF.add(arrayList);
        } else if (arrayList.isEmpty()) {
        } else {
            super.b(arrayList.remove(0).toString(), arrayList.toArray());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void destroy() {
        if (this.wV != null) {
            for (c.a aVar : this.wV) {
                aVar.destroy();
            }
            this.wV = null;
        }
        this.xD.a(this);
    }

    private void e(io.socket.f.b<JSONArray> bVar) {
        a remove = this.xE.remove(Integer.valueOf(bVar.id));
        if (remove == null) {
            logger.fine(String.format("bad ack %s", Integer.valueOf(bVar.id)));
            return;
        }
        logger.fine(String.format("calling ack %s with %s", Integer.valueOf(bVar.id), bVar.data));
        remove.a(a(bVar.data));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void eB() {
        logger.fine("transport is open - connecting");
        if ("/".equals(this.xC)) {
            return;
        }
        b(new io.socket.f.b(0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void eH() {
        if (this.wV != null) {
            return;
        }
        final Manager manager = this.xD;
        this.wV = new LinkedList<c.a>() { // from class: io.socket.client.Socket$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                add(c.a(manager, "open", new a.InterfaceC0017a() { // from class: io.socket.client.Socket$2.1
                    @Override // io.socket.b.a.InterfaceC0017a
                    public void a(Object... objArr) {
                        d.this.eB();
                    }
                }));
                add(c.a(manager, "packet", new a.InterfaceC0017a() { // from class: io.socket.client.Socket$2.2
                    @Override // io.socket.b.a.InterfaceC0017a
                    public void a(Object... objArr) {
                        d.this.c((io.socket.f.b) objArr[0]);
                    }
                }));
                add(c.a(manager, "close", new a.InterfaceC0017a() { // from class: io.socket.client.Socket$2.3
                    @Override // io.socket.b.a.InterfaceC0017a
                    public void a(Object... objArr) {
                        d.this.aG(objArr.length > 0 ? (String) objArr[0] : null);
                    }
                }));
            }
        };
    }

    private void eK() {
        this.connected = true;
        b("connect", new Object[0]);
        eL();
    }

    private void eL() {
        while (true) {
            List<Object> poll = this.xF.poll();
            if (poll == null) {
                break;
            }
            super.b((String) poll.get(0), poll.toArray());
        }
        this.xF.clear();
        while (true) {
            io.socket.f.b<JSONArray> poll2 = this.xG.poll();
            if (poll2 == null) {
                this.xG.clear();
                return;
            }
            b(poll2);
        }
    }

    private void eM() {
        logger.fine(String.format("server disconnect (%s)", this.xC));
        destroy();
        aG("io server disconnect");
    }

    static /* synthetic */ int h(d dVar) {
        int i = dVar.xB;
        dVar.xB = i + 1;
        return i;
    }

    @Override // io.socket.b.a
    public io.socket.b.a b(final String str, final Object... objArr) {
        io.socket.g.a.b(new Runnable() { // from class: io.socket.client.d.2
            /* JADX WARN: Type inference failed for: r1v22, types: [T, org.json.JSONArray] */
            @Override // java.lang.Runnable
            public void run() {
                if (d.xA.containsKey(str)) {
                    d.super.b(str, objArr);
                    return;
                }
                ArrayList<Object> arrayList = new ArrayList(objArr.length + 1);
                arrayList.add(str);
                arrayList.addAll(Arrays.asList(objArr));
                JSONArray jSONArray = new JSONArray();
                for (Object obj : arrayList) {
                    jSONArray.put(obj);
                }
                io.socket.f.b bVar = new io.socket.f.b(io.socket.d.a.e(jSONArray) ? 5 : 2, jSONArray);
                if (arrayList.get(arrayList.size() - 1) instanceof a) {
                    d.logger.fine(String.format("emitting packet with ack id %d", Integer.valueOf(d.this.xB)));
                    d.this.xE.put(Integer.valueOf(d.this.xB), (a) arrayList.remove(arrayList.size() - 1));
                    bVar.data = d.a(jSONArray, jSONArray.length() - 1);
                    bVar.id = d.h(d.this);
                }
                if (d.this.connected) {
                    d.this.b(bVar);
                } else {
                    d.this.xG.add(bVar);
                }
            }
        });
        return this;
    }

    public d eI() {
        io.socket.g.a.b(new Runnable() { // from class: io.socket.client.d.1
            @Override // java.lang.Runnable
            public void run() {
                if (d.this.connected) {
                    return;
                }
                d.this.eH();
                d.this.xD.eA();
                if (Manager.ReadyState.OPEN == d.this.xD.wG) {
                    d.this.eB();
                }
                d.this.b("connecting", new Object[0]);
            }
        });
        return this;
    }

    public d eJ() {
        return eI();
    }

    public d eN() {
        io.socket.g.a.b(new Runnable() { // from class: io.socket.client.d.4
            @Override // java.lang.Runnable
            public void run() {
                if (d.this.connected) {
                    d.logger.fine(String.format("performing disconnect (%s)", d.this.xC));
                    d.this.b(new io.socket.f.b(1));
                }
                d.this.destroy();
                if (d.this.connected) {
                    d.this.aG("io client disconnect");
                }
            }
        });
        return this;
    }

    public d eO() {
        return eN();
    }
}
