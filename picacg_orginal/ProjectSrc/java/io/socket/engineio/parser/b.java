package io.socket.engineio.parser;
/* loaded from: picacg_1.jar:io/socket/engineio/parser/b.class */
public class b<T> {
    public T data;
    public String type;

    public b(String str) {
        this(str, null);
    }

    public b(String str, T t) {
        this.type = str;
        this.data = t;
    }
}
