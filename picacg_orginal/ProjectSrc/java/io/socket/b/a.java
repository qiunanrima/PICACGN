package io.socket.b;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
/* loaded from: picacg_1.jar:io/socket/b/a.class */
public class a {
    private ConcurrentMap<String, ConcurrentLinkedQueue<InterfaceC0017a>> xQ = new ConcurrentHashMap();

    /* renamed from: io.socket.b.a$a  reason: collision with other inner class name */
    /* loaded from: picacg_1.jar:io/socket/b/a$a.class */
    public interface InterfaceC0017a {
        void a(Object... objArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: picacg_1.jar:io/socket/b/a$b.class */
    public class b implements InterfaceC0017a {
        public final String xR;
        public final InterfaceC0017a xS;

        public b(String str, InterfaceC0017a interfaceC0017a) {
            this.xR = str;
            this.xS = interfaceC0017a;
        }

        @Override // io.socket.b.a.InterfaceC0017a
        public void a(Object... objArr) {
            a.this.c(this.xR, this);
            this.xS.a(objArr);
        }
    }

    private static boolean a(InterfaceC0017a interfaceC0017a, InterfaceC0017a interfaceC0017a2) {
        if (interfaceC0017a.equals(interfaceC0017a2)) {
            return true;
        }
        if (interfaceC0017a2 instanceof b) {
            return interfaceC0017a.equals(((b) interfaceC0017a2).xS);
        }
        return false;
    }

    public a a(String str, InterfaceC0017a interfaceC0017a) {
        ConcurrentLinkedQueue<InterfaceC0017a> concurrentLinkedQueue = this.xQ.get(str);
        ConcurrentLinkedQueue<InterfaceC0017a> concurrentLinkedQueue2 = concurrentLinkedQueue;
        if (concurrentLinkedQueue == null) {
            concurrentLinkedQueue2 = new ConcurrentLinkedQueue<>();
            ConcurrentLinkedQueue<InterfaceC0017a> putIfAbsent = this.xQ.putIfAbsent(str, concurrentLinkedQueue2);
            if (putIfAbsent != null) {
                concurrentLinkedQueue2 = putIfAbsent;
            }
        }
        concurrentLinkedQueue2.add(interfaceC0017a);
        return this;
    }

    public a aH(String str) {
        this.xQ.remove(str);
        return this;
    }

    public a b(String str, InterfaceC0017a interfaceC0017a) {
        a(str, new b(str, interfaceC0017a));
        return this;
    }

    public a b(String str, Object... objArr) {
        ConcurrentLinkedQueue<InterfaceC0017a> concurrentLinkedQueue = this.xQ.get(str);
        if (concurrentLinkedQueue != null) {
            Iterator<InterfaceC0017a> it = concurrentLinkedQueue.iterator();
            while (it.hasNext()) {
                it.next().a(objArr);
            }
        }
        return this;
    }

    public a c(String str, InterfaceC0017a interfaceC0017a) {
        ConcurrentLinkedQueue<InterfaceC0017a> concurrentLinkedQueue = this.xQ.get(str);
        if (concurrentLinkedQueue != null) {
            Iterator<InterfaceC0017a> it = concurrentLinkedQueue.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (a(interfaceC0017a, it.next())) {
                    it.remove();
                    break;
                }
            }
        }
        return this;
    }

    public a eQ() {
        this.xQ.clear();
        return this;
    }
}
