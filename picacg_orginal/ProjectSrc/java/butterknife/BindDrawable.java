package butterknife;

import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.CLASS)
/* loaded from: picacg_1.jar:butterknife/BindDrawable.class */
public @interface BindDrawable {
    @AttrRes
    int tint() default 0;

    @DrawableRes
    int value();
}
