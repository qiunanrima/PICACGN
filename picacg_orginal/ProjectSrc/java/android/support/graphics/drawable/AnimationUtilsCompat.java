package android.support.graphics.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.support.annotation.RestrictTo;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: picacg_1.jar:android/support/graphics/drawable/AnimationUtilsCompat.class */
public class AnimationUtilsCompat {
    private static Interpolator createInterpolatorFromXml(Context context, Resources resources, Resources.Theme theme, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth();
        Interpolator interpolator = null;
        while (true) {
            int next = xmlPullParser.next();
            if ((next != 3 || xmlPullParser.getDepth() > depth) && next != 1) {
                if (next == 2) {
                    AttributeSet asAttributeSet = Xml.asAttributeSet(xmlPullParser);
                    String name = xmlPullParser.getName();
                    if (name.equals("linearInterpolator")) {
                        interpolator = new LinearInterpolator();
                    } else if (name.equals("accelerateInterpolator")) {
                        interpolator = new AccelerateInterpolator(context, asAttributeSet);
                    } else if (name.equals("decelerateInterpolator")) {
                        interpolator = new DecelerateInterpolator(context, asAttributeSet);
                    } else if (name.equals("accelerateDecelerateInterpolator")) {
                        interpolator = new AccelerateDecelerateInterpolator();
                    } else if (name.equals("cycleInterpolator")) {
                        interpolator = new CycleInterpolator(context, asAttributeSet);
                    } else if (name.equals("anticipateInterpolator")) {
                        interpolator = new AnticipateInterpolator(context, asAttributeSet);
                    } else if (name.equals("overshootInterpolator")) {
                        interpolator = new OvershootInterpolator(context, asAttributeSet);
                    } else if (name.equals("anticipateOvershootInterpolator")) {
                        interpolator = new AnticipateOvershootInterpolator(context, asAttributeSet);
                    } else if (name.equals("bounceInterpolator")) {
                        interpolator = new BounceInterpolator();
                    } else if (!name.equals("pathInterpolator")) {
                        throw new RuntimeException("Unknown interpolator name: " + xmlPullParser.getName());
                    } else {
                        interpolator = new PathInterpolatorCompat(context, asAttributeSet, xmlPullParser);
                    }
                }
            }
        }
        return interpolator;
    }

    public static Interpolator loadInterpolator(Context context, int i) throws Resources.NotFoundException {
        XmlResourceParser xmlResourceParser;
        XmlResourceParser xmlResourceParser2;
        if (Build.VERSION.SDK_INT >= 21) {
            return AnimationUtils.loadInterpolator(context, i);
        }
        XmlResourceParser xmlResourceParser3 = null;
        try {
            try {
                if (i == 17563663) {
                    return new FastOutLinearInInterpolator();
                }
                if (i == 17563661) {
                    return new FastOutSlowInInterpolator();
                }
                if (i == 17563662) {
                    return new LinearOutSlowInInterpolator();
                }
                XmlResourceParser animation = context.getResources().getAnimation(i);
                try {
                    Interpolator createInterpolatorFromXml = createInterpolatorFromXml(context, context.getResources(), context.getTheme(), animation);
                    if (animation != null) {
                        animation.close();
                    }
                    return createInterpolatorFromXml;
                } catch (IOException e) {
                    xmlResourceParser2 = animation;
                    e = e;
                    XmlResourceParser xmlResourceParser4 = xmlResourceParser2;
                    XmlResourceParser xmlResourceParser5 = xmlResourceParser2;
                    StringBuilder sb = new StringBuilder();
                    XmlResourceParser xmlResourceParser6 = xmlResourceParser2;
                    sb.append("Can't load animation resource ID #0x");
                    XmlResourceParser xmlResourceParser7 = xmlResourceParser2;
                    sb.append(Integer.toHexString(i));
                    XmlResourceParser xmlResourceParser8 = xmlResourceParser2;
                    Resources.NotFoundException notFoundException = new Resources.NotFoundException(sb.toString());
                    XmlResourceParser xmlResourceParser9 = xmlResourceParser2;
                    notFoundException.initCause(e);
                    XmlResourceParser xmlResourceParser10 = xmlResourceParser2;
                    throw notFoundException;
                } catch (XmlPullParserException e2) {
                    xmlResourceParser = animation;
                    e = e2;
                    XmlResourceParser xmlResourceParser11 = xmlResourceParser;
                    XmlResourceParser xmlResourceParser12 = xmlResourceParser;
                    XmlResourceParser xmlResourceParser13 = xmlResourceParser;
                    StringBuilder sb2 = new StringBuilder();
                    XmlResourceParser xmlResourceParser14 = xmlResourceParser;
                    sb2.append("Can't load animation resource ID #0x");
                    XmlResourceParser xmlResourceParser15 = xmlResourceParser;
                    sb2.append(Integer.toHexString(i));
                    XmlResourceParser xmlResourceParser16 = xmlResourceParser;
                    Resources.NotFoundException notFoundException2 = new Resources.NotFoundException(sb2.toString());
                    XmlResourceParser xmlResourceParser17 = xmlResourceParser;
                    notFoundException2.initCause(e);
                    XmlResourceParser xmlResourceParser18 = xmlResourceParser;
                    throw notFoundException2;
                } catch (Throwable th) {
                    th = th;
                    xmlResourceParser3 = animation;
                    if (xmlResourceParser3 != null) {
                        xmlResourceParser3.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e3) {
            e = e3;
            xmlResourceParser2 = null;
        } catch (XmlPullParserException e4) {
            e = e4;
            xmlResourceParser = null;
        }
    }
}
