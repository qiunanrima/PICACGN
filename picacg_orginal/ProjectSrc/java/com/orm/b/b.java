package com.orm.b;
/* loaded from: picacg_1.jar:com/orm/b/b.class */
public class b {
    private String content;

    public b(String str) {
        this.content = str.replaceAll("(\\/\\*([\\s\\S]*?)\\*\\/)|(--(.)*)|(\n)", "");
    }

    public String[] br() {
        return this.content.split(";");
    }
}
