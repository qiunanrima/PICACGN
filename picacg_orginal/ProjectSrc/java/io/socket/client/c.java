package io.socket.client;

import io.socket.b.a;
/* loaded from: picacg_1.jar:io/socket/client/c.class */
public class c {

    /* loaded from: picacg_1.jar:io/socket/client/c$a.class */
    public interface a {
        void destroy();
    }

    public static a a(final io.socket.b.a aVar, final String str, final a.InterfaceC0017a interfaceC0017a) {
        aVar.a(str, interfaceC0017a);
        return new a() { // from class: io.socket.client.c.1
            @Override // io.socket.client.c.a
            public void destroy() {
                io.socket.b.a.this.c(str, interfaceC0017a);
            }
        };
    }
}
