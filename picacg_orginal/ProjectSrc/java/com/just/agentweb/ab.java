package com.just.agentweb;

import android.os.Build;
import android.webkit.JavascriptInterface;
import com.just.agentweb.AgentWeb;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
/* loaded from: picacg_1.jar:com/just/agentweb/ab.class */
public abstract class ab implements ae {
    private AgentWeb.SecurityType cJ;

    /* JADX INFO: Access modifiers changed from: protected */
    public ab(AgentWeb.SecurityType securityType) {
        this.cJ = securityType;
    }

    public boolean a(Object obj) {
        boolean z;
        if (Build.VERSION.SDK_INT >= 17 && c.dv != 2) {
            Method[] methods = obj.getClass().getMethods();
            int length = methods.length;
            int i = 0;
            boolean z2 = false;
            while (true) {
                boolean z3 = z2;
                z = z3;
                if (i >= length) {
                    break;
                }
                Annotation[] annotations = methods[i].getAnnotations();
                int length2 = annotations.length;
                int i2 = 0;
                while (true) {
                    z = z3;
                    if (i2 >= length2) {
                        break;
                    } else if (annotations[i2] instanceof JavascriptInterface) {
                        z = true;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (z) {
                    break;
                }
                i++;
                z2 = z;
            }
            return z;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean an() {
        boolean z = true;
        if (this.cJ == AgentWeb.SecurityType.STRICT_CHECK && c.dv != 2 && Build.VERSION.SDK_INT <= 17) {
            z = false;
        }
        return z;
    }
}
