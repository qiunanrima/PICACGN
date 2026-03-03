package butterknife;

import android.support.annotation.BoolRes;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.CLASS)
/* loaded from: picacg_1.jar:butterknife/BindBool.class */
public @interface BindBool {
    @BoolRes
    int value();
}
