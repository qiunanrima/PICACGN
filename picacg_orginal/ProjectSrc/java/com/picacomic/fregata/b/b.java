package com.picacomic.fregata.b;

import com.picacomic.fregata.MyApplication;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Dns;
/* loaded from: picacg_1.jar:com/picacomic/fregata/b/b.class */
public class b implements Dns {
    String[] sZ = com.picacomic.fregata.utils.e.ao(MyApplication.by());

    @Override // okhttp3.Dns
    public List<InetAddress> lookup(String str) throws UnknownHostException {
        com.picacomic.fregata.utils.f.aA("DNS lookup hostname: " + str);
        try {
            ArrayList arrayList = new ArrayList();
            if (this.sZ == null || this.sZ.length <= 0) {
                com.picacomic.fregata.utils.f.aA("DNS try empty address");
            } else {
                for (int i = 0; i < this.sZ.length; i++) {
                    arrayList.add(InetAddress.getByName(this.sZ[i]));
                }
            }
            com.picacomic.fregata.utils.f.aA("DNS try");
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return Dns.SYSTEM.lookup(str);
        }
    }
}
