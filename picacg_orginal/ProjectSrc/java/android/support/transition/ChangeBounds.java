package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.Transition;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import java.util.Map;
/* loaded from: picacg_1.jar:android/support/transition/ChangeBounds.class */
public class ChangeBounds extends Transition {
    private boolean mReparent;
    private boolean mResizeClip;
    private int[] mTempLocation;
    private static final String PROPNAME_BOUNDS = "android:changeBounds:bounds";
    private static final String PROPNAME_PARENT = "android:changeBounds:parent";
    private static final String PROPNAME_WINDOW_X = "android:changeBounds:windowX";
    private static final String PROPNAME_WINDOW_Y = "android:changeBounds:windowY";
    private static final String[] sTransitionProperties = {PROPNAME_BOUNDS, PROPNAME_PARENT, PROPNAME_WINDOW_X, PROPNAME_WINDOW_Y};
    private static final Property<Drawable, PointF> DRAWABLE_ORIGIN_PROPERTY = new Property<Drawable, PointF>(PointF.class, "boundsOrigin") { // from class: android.support.transition.ChangeBounds.1
        private Rect mBounds = new Rect();

        @Override // android.util.Property
        public PointF get(Drawable drawable) {
            drawable.copyBounds(this.mBounds);
            return new PointF(this.mBounds.left, this.mBounds.top);
        }

        @Override // android.util.Property
        public void set(Drawable drawable, PointF pointF) {
            drawable.copyBounds(this.mBounds);
            this.mBounds.offsetTo(Math.round(pointF.x), Math.round(pointF.y));
            drawable.setBounds(this.mBounds);
        }
    };
    private static RectEvaluator sRectEvaluator = new RectEvaluator();

    /* loaded from: picacg_1.jar:android/support/transition/ChangeBounds$HorizontalOffsetProperty.class */
    private static class HorizontalOffsetProperty extends OffsetProperty {
        HorizontalOffsetProperty() {
            super("offsetLeftAndRight");
        }

        @Override // android.support.transition.ChangeBounds.OffsetProperty
        protected void offsetBy(View view, int i) {
            ViewCompat.offsetLeftAndRight(view, i);
        }
    }

    /* loaded from: picacg_1.jar:android/support/transition/ChangeBounds$OffsetProperty.class */
    private static abstract class OffsetProperty extends Property<View, Integer> {
        int mPreviousValue;

        OffsetProperty(String str) {
            super(Integer.class, str);
        }

        @Override // android.util.Property
        public Integer get(View view) {
            return null;
        }

        protected abstract void offsetBy(View view, int i);

        @Override // android.util.Property
        public void set(View view, Integer num) {
            offsetBy(view, num.intValue() - this.mPreviousValue);
            this.mPreviousValue = num.intValue();
        }
    }

    /* loaded from: picacg_1.jar:android/support/transition/ChangeBounds$VerticalOffsetProperty.class */
    private static class VerticalOffsetProperty extends OffsetProperty {
        VerticalOffsetProperty() {
            super("offsetTopAndBottom");
        }

        @Override // android.support.transition.ChangeBounds.OffsetProperty
        protected void offsetBy(View view, int i) {
            ViewCompat.offsetTopAndBottom(view, i);
        }
    }

    public ChangeBounds() {
        this.mTempLocation = new int[2];
        this.mResizeClip = false;
        this.mReparent = false;
    }

    public ChangeBounds(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTempLocation = new int[2];
        this.mResizeClip = false;
        this.mReparent = false;
    }

    private void captureValues(TransitionValues transitionValues) {
        View view = transitionValues.view;
        if (!ViewCompat.isLaidOut(view) && view.getWidth() == 0 && view.getHeight() == 0) {
            return;
        }
        transitionValues.values.put(PROPNAME_BOUNDS, new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
        transitionValues.values.put(PROPNAME_PARENT, transitionValues.view.getParent());
        if (this.mReparent) {
            transitionValues.view.getLocationInWindow(this.mTempLocation);
            transitionValues.values.put(PROPNAME_WINDOW_X, Integer.valueOf(this.mTempLocation[0]));
            transitionValues.values.put(PROPNAME_WINDOW_Y, Integer.valueOf(this.mTempLocation[1]));
        }
    }

    private boolean parentMatches(View view, View view2) {
        boolean z = true;
        if (this.mReparent) {
            TransitionValues matchedTransitionValues = getMatchedTransitionValues(view, true);
            if (matchedTransitionValues == null) {
                if (view == view2) {
                    z = true;
                }
                z = false;
            } else {
                if (view2 == matchedTransitionValues.view) {
                    z = true;
                }
                z = false;
            }
        }
        return z;
    }

    @Override // android.support.transition.Transition
    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override // android.support.transition.Transition
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override // android.support.transition.Transition
    @Nullable
    public Animator createAnimator(@NonNull final ViewGroup viewGroup, @Nullable TransitionValues transitionValues, @Nullable TransitionValues transitionValues2) {
        int i;
        ObjectAnimator mergeAnimators;
        if (transitionValues == null || transitionValues2 == null) {
            return null;
        }
        Map<String, Object> map = transitionValues.values;
        Map<String, Object> map2 = transitionValues2.values;
        ViewGroup viewGroup2 = (ViewGroup) map.get(PROPNAME_PARENT);
        ViewGroup viewGroup3 = (ViewGroup) map2.get(PROPNAME_PARENT);
        if (viewGroup2 == null || viewGroup3 == null) {
            return null;
        }
        final View view = transitionValues2.view;
        if (!parentMatches(viewGroup2, viewGroup3)) {
            int intValue = ((Integer) transitionValues.values.get(PROPNAME_WINDOW_X)).intValue();
            int intValue2 = ((Integer) transitionValues.values.get(PROPNAME_WINDOW_Y)).intValue();
            int intValue3 = ((Integer) transitionValues2.values.get(PROPNAME_WINDOW_X)).intValue();
            int intValue4 = ((Integer) transitionValues2.values.get(PROPNAME_WINDOW_Y)).intValue();
            if (intValue == intValue3 && intValue2 == intValue4) {
                return null;
            }
            viewGroup.getLocationInWindow(this.mTempLocation);
            Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
            view.draw(new Canvas(createBitmap));
            final BitmapDrawable bitmapDrawable = new BitmapDrawable(createBitmap);
            final float transitionAlpha = ViewUtils.getTransitionAlpha(view);
            ViewUtils.setTransitionAlpha(view, 0.0f);
            ViewUtils.getOverlay(viewGroup).add(bitmapDrawable);
            ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(bitmapDrawable, PropertyValuesHolderUtils.ofPointF(DRAWABLE_ORIGIN_PROPERTY, getPathMotion().getPath(intValue - this.mTempLocation[0], intValue2 - this.mTempLocation[1], intValue3 - this.mTempLocation[0], intValue4 - this.mTempLocation[1])));
            ofPropertyValuesHolder.addListener(new AnimatorListenerAdapter() { // from class: android.support.transition.ChangeBounds.5
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    ViewUtils.getOverlay(viewGroup).remove(bitmapDrawable);
                    ViewUtils.setTransitionAlpha(view, transitionAlpha);
                }
            });
            return ofPropertyValuesHolder;
        }
        Rect rect = (Rect) transitionValues.values.get(PROPNAME_BOUNDS);
        Rect rect2 = (Rect) transitionValues2.values.get(PROPNAME_BOUNDS);
        int i2 = rect.left;
        int i3 = rect2.left;
        int i4 = rect.top;
        int i5 = rect2.top;
        int i6 = rect.right;
        int i7 = rect2.right;
        int i8 = rect.bottom;
        int i9 = rect2.bottom;
        int i10 = i6 - i2;
        int i11 = i8 - i4;
        int i12 = i7 - i3;
        int i13 = i9 - i5;
        if (i10 == 0 || i11 == 0 || i12 == 0 || i13 == 0) {
            i = 0;
        } else {
            int i14 = i2 != i3 ? 1 : 0;
            int i15 = i14;
            if (i4 != i5) {
                i15 = i14 + 1;
            }
            int i16 = i15;
            if (i6 != i7) {
                i16 = i15 + 1;
            }
            i = i16;
            if (i8 != i9) {
                i = i16 + 1;
            }
        }
        if (i > 0) {
            if (this.mResizeClip) {
                if (i10 != i12) {
                    view.setRight(Math.max(i10, i12) + i3);
                }
                if (i11 != i13) {
                    view.setBottom(Math.max(i11, i13) + i5);
                }
                if (i2 != i3) {
                    view.setTranslationX(i2 - i3);
                }
                if (i4 != i5) {
                    view.setTranslationY(i4 - i5);
                }
                float f = i3 - i2;
                float f2 = i5 - i4;
                Animator mergeAnimators2 = TransitionUtils.mergeAnimators((f == 0.0f && f2 == 0.0f) ? null : ObjectAnimatorUtils.ofFloat(view, View.TRANSLATION_X, View.TRANSLATION_Y, getPathMotion().getPath(0.0f, 0.0f, f, f2)), (i12 - i10 == 0 && i13 - i11 == 0) ? null : ObjectAnimator.ofObject(view, (Property<View, V>) ViewUtils.CLIP_BOUNDS, (TypeEvaluator) sRectEvaluator, (Object[]) new Rect[]{new Rect(0, 0, i10, i11), new Rect(0, 0, i12, i13)}));
                if (view.getParent() instanceof ViewGroup) {
                    final ViewGroup viewGroup4 = (ViewGroup) view.getParent();
                    ViewGroupUtils.suppressLayout(viewGroup4, true);
                    addListener(new Transition.TransitionListenerAdapter() { // from class: android.support.transition.ChangeBounds.3
                        boolean mCanceled = false;

                        @Override // android.support.transition.Transition.TransitionListenerAdapter, android.support.transition.Transition.TransitionListener
                        public void onTransitionCancel(@NonNull Transition transition) {
                            ViewGroupUtils.suppressLayout(viewGroup4, false);
                            this.mCanceled = true;
                        }

                        @Override // android.support.transition.Transition.TransitionListenerAdapter, android.support.transition.Transition.TransitionListener
                        public void onTransitionEnd(@NonNull Transition transition) {
                            if (this.mCanceled) {
                                return;
                            }
                            ViewGroupUtils.suppressLayout(viewGroup4, false);
                        }

                        @Override // android.support.transition.Transition.TransitionListenerAdapter, android.support.transition.Transition.TransitionListener
                        public void onTransitionPause(@NonNull Transition transition) {
                            ViewGroupUtils.suppressLayout(viewGroup4, false);
                        }

                        @Override // android.support.transition.Transition.TransitionListenerAdapter, android.support.transition.Transition.TransitionListener
                        public void onTransitionResume(@NonNull Transition transition) {
                            ViewGroupUtils.suppressLayout(viewGroup4, true);
                        }
                    });
                }
                mergeAnimators2.addListener(new AnimatorListenerAdapter() { // from class: android.support.transition.ChangeBounds.4
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                        ViewCompat.setClipBounds(view, null);
                    }
                });
                return mergeAnimators2;
            }
            if (i10 == i12 && i11 == i13) {
                view.offsetLeftAndRight(i2 - view.getLeft());
                view.offsetTopAndBottom(i4 - view.getTop());
                mergeAnimators = ObjectAnimatorUtils.ofInt(view, new HorizontalOffsetProperty(), new VerticalOffsetProperty(), getPathMotion().getPath(0.0f, 0.0f, i3 - i2, i5 - i4));
            } else {
                if (i2 != i3) {
                    view.setLeft(i2);
                }
                if (i4 != i5) {
                    view.setTop(i4);
                }
                if (i6 != i7) {
                    view.setRight(i6);
                }
                if (i8 != i9) {
                    view.setBottom(i8);
                }
                mergeAnimators = TransitionUtils.mergeAnimators((i2 == i3 && i4 == i5) ? null : ObjectAnimatorUtils.ofInt(view, "left", "top", getPathMotion().getPath(i2, i4, i3, i5)), (i6 == i7 && i8 == i9) ? null : ObjectAnimatorUtils.ofInt(view, "right", "bottom", getPathMotion().getPath(i6, i8, i7, i9)));
            }
            if (view.getParent() instanceof ViewGroup) {
                final ViewGroup viewGroup5 = (ViewGroup) view.getParent();
                ViewGroupUtils.suppressLayout(viewGroup5, true);
                addListener(new Transition.TransitionListenerAdapter() { // from class: android.support.transition.ChangeBounds.2
                    boolean mCanceled = false;

                    @Override // android.support.transition.Transition.TransitionListenerAdapter, android.support.transition.Transition.TransitionListener
                    public void onTransitionCancel(@NonNull Transition transition) {
                        ViewGroupUtils.suppressLayout(viewGroup5, false);
                        this.mCanceled = true;
                    }

                    @Override // android.support.transition.Transition.TransitionListenerAdapter, android.support.transition.Transition.TransitionListener
                    public void onTransitionEnd(@NonNull Transition transition) {
                        if (this.mCanceled) {
                            return;
                        }
                        ViewGroupUtils.suppressLayout(viewGroup5, false);
                    }

                    @Override // android.support.transition.Transition.TransitionListenerAdapter, android.support.transition.Transition.TransitionListener
                    public void onTransitionPause(@NonNull Transition transition) {
                        ViewGroupUtils.suppressLayout(viewGroup5, false);
                    }

                    @Override // android.support.transition.Transition.TransitionListenerAdapter, android.support.transition.Transition.TransitionListener
                    public void onTransitionResume(@NonNull Transition transition) {
                        ViewGroupUtils.suppressLayout(viewGroup5, true);
                    }
                });
            }
            return mergeAnimators;
        }
        return null;
    }

    @Override // android.support.transition.Transition
    @Nullable
    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    public void setResizeClip(boolean z) {
        this.mResizeClip = z;
    }
}
