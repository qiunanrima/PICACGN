package android.support.transition;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.Transition;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: picacg_1.jar:android/support/transition/TransitionManager.class */
public class TransitionManager {
    private static final String LOG_TAG = "TransitionManager";
    private static Transition sDefaultTransition = new AutoTransition();
    private static ThreadLocal<WeakReference<ArrayMap<ViewGroup, ArrayList<Transition>>>> sRunningTransitions = new ThreadLocal<>();
    private static ArrayList<ViewGroup> sPendingTransitions = new ArrayList<>();
    private ArrayMap<Scene, Transition> mSceneTransitions = new ArrayMap<>();
    private ArrayMap<Scene, ArrayMap<Scene, Transition>> mScenePairTransitions = new ArrayMap<>();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: picacg_1.jar:android/support/transition/TransitionManager$MultiListener.class */
    public static class MultiListener implements View.OnAttachStateChangeListener, ViewTreeObserver.OnPreDrawListener {
        ViewGroup mSceneRoot;
        Transition mTransition;

        MultiListener(Transition transition, ViewGroup viewGroup) {
            this.mTransition = transition;
            this.mSceneRoot = viewGroup;
        }

        private void removeListeners() {
            this.mSceneRoot.getViewTreeObserver().removeOnPreDrawListener(this);
            this.mSceneRoot.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            ArrayList<Transition> arrayList;
            removeListeners();
            if (TransitionManager.sPendingTransitions.remove(this.mSceneRoot)) {
                final ArrayMap<ViewGroup, ArrayList<Transition>> runningTransitions = TransitionManager.getRunningTransitions();
                ArrayList<Transition> arrayList2 = runningTransitions.get(this.mSceneRoot);
                ArrayList arrayList3 = null;
                if (arrayList2 == null) {
                    arrayList = new ArrayList<>();
                    runningTransitions.put(this.mSceneRoot, arrayList);
                } else {
                    arrayList = arrayList2;
                    if (arrayList2.size() > 0) {
                        arrayList3 = new ArrayList(arrayList2);
                        arrayList = arrayList2;
                    }
                }
                arrayList.add(this.mTransition);
                this.mTransition.addListener(new Transition.TransitionListenerAdapter() { // from class: android.support.transition.TransitionManager.MultiListener.1
                    @Override // android.support.transition.Transition.TransitionListenerAdapter, android.support.transition.Transition.TransitionListener
                    public void onTransitionEnd(@NonNull Transition transition) {
                        ((ArrayList) runningTransitions.get(MultiListener.this.mSceneRoot)).remove(transition);
                    }
                });
                this.mTransition.captureValues(this.mSceneRoot, false);
                if (arrayList3 != null) {
                    Iterator it = arrayList3.iterator();
                    while (it.hasNext()) {
                        ((Transition) it.next()).resume(this.mSceneRoot);
                    }
                }
                this.mTransition.playTransition(this.mSceneRoot);
                return true;
            }
            return true;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            removeListeners();
            TransitionManager.sPendingTransitions.remove(this.mSceneRoot);
            ArrayList<Transition> arrayList = TransitionManager.getRunningTransitions().get(this.mSceneRoot);
            if (arrayList != null && arrayList.size() > 0) {
                Iterator<Transition> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().resume(this.mSceneRoot);
                }
            }
            this.mTransition.clearValues(true);
        }
    }

    public static void beginDelayedTransition(@NonNull ViewGroup viewGroup) {
        beginDelayedTransition(viewGroup, null);
    }

    public static void beginDelayedTransition(@NonNull ViewGroup viewGroup, @Nullable Transition transition) {
        if (sPendingTransitions.contains(viewGroup) || !ViewCompat.isLaidOut(viewGroup)) {
            return;
        }
        sPendingTransitions.add(viewGroup);
        Transition transition2 = transition;
        if (transition == null) {
            transition2 = sDefaultTransition;
        }
        Transition mo53clone = transition2.mo53clone();
        sceneChangeSetup(viewGroup, mo53clone);
        Scene.setCurrentScene(viewGroup, null);
        sceneChangeRunTransition(viewGroup, mo53clone);
    }

    private static void changeScene(Scene scene, Transition transition) {
        ViewGroup sceneRoot = scene.getSceneRoot();
        if (sPendingTransitions.contains(sceneRoot)) {
            return;
        }
        if (transition == null) {
            scene.enter();
            return;
        }
        sPendingTransitions.add(sceneRoot);
        Transition mo53clone = transition.mo53clone();
        mo53clone.setSceneRoot(sceneRoot);
        Scene currentScene = Scene.getCurrentScene(sceneRoot);
        if (currentScene != null && currentScene.isCreatedFromLayoutResource()) {
            mo53clone.setCanRemoveViews(true);
        }
        sceneChangeSetup(sceneRoot, mo53clone);
        scene.enter();
        sceneChangeRunTransition(sceneRoot, mo53clone);
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0014, code lost:
        if (r0.get() == null) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static android.support.v4.util.ArrayMap<android.view.ViewGroup, java.util.ArrayList<android.support.transition.Transition>> getRunningTransitions() {
        /*
            java.lang.ThreadLocal<java.lang.ref.WeakReference<android.support.v4.util.ArrayMap<android.view.ViewGroup, java.util.ArrayList<android.support.transition.Transition>>>> r0 = android.support.transition.TransitionManager.sRunningTransitions
            java.lang.Object r0 = r0.get()
            java.lang.ref.WeakReference r0 = (java.lang.ref.WeakReference) r0
            r5 = r0
            r0 = r5
            if (r0 == 0) goto L17
            r0 = r5
            r6 = r0
            r0 = r5
            java.lang.Object r0 = r0.get()
            if (r0 != 0) goto L2d
        L17:
            java.lang.ref.WeakReference r0 = new java.lang.ref.WeakReference
            r1 = r0
            android.support.v4.util.ArrayMap r2 = new android.support.v4.util.ArrayMap
            r3 = r2
            r3.<init>()
            r1.<init>(r2)
            r6 = r0
            java.lang.ThreadLocal<java.lang.ref.WeakReference<android.support.v4.util.ArrayMap<android.view.ViewGroup, java.util.ArrayList<android.support.transition.Transition>>>> r0 = android.support.transition.TransitionManager.sRunningTransitions
            r1 = r6
            r0.set(r1)
        L2d:
            r0 = r6
            java.lang.Object r0 = r0.get()
            android.support.v4.util.ArrayMap r0 = (android.support.v4.util.ArrayMap) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.TransitionManager.getRunningTransitions():android.support.v4.util.ArrayMap");
    }

    private Transition getTransition(Scene scene) {
        Scene currentScene;
        ArrayMap<Scene, Transition> arrayMap;
        Transition transition;
        ViewGroup sceneRoot = scene.getSceneRoot();
        if (sceneRoot == null || (currentScene = Scene.getCurrentScene(sceneRoot)) == null || (arrayMap = this.mScenePairTransitions.get(scene)) == null || (transition = arrayMap.get(currentScene)) == null) {
            Transition transition2 = this.mSceneTransitions.get(scene);
            if (transition2 == null) {
                transition2 = sDefaultTransition;
            }
            return transition2;
        }
        return transition;
    }

    public static void go(@NonNull Scene scene) {
        changeScene(scene, sDefaultTransition);
    }

    public static void go(@NonNull Scene scene, @Nullable Transition transition) {
        changeScene(scene, transition);
    }

    private static void sceneChangeRunTransition(ViewGroup viewGroup, Transition transition) {
        if (transition == null || viewGroup == null) {
            return;
        }
        MultiListener multiListener = new MultiListener(transition, viewGroup);
        viewGroup.addOnAttachStateChangeListener(multiListener);
        viewGroup.getViewTreeObserver().addOnPreDrawListener(multiListener);
    }

    private static void sceneChangeSetup(ViewGroup viewGroup, Transition transition) {
        ArrayList<Transition> arrayList = getRunningTransitions().get(viewGroup);
        if (arrayList != null && arrayList.size() > 0) {
            Iterator<Transition> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().pause(viewGroup);
            }
        }
        if (transition != null) {
            transition.captureValues(viewGroup, true);
        }
        Scene currentScene = Scene.getCurrentScene(viewGroup);
        if (currentScene != null) {
            currentScene.exit();
        }
    }

    public void setTransition(@NonNull Scene scene, @NonNull Scene scene2, @Nullable Transition transition) {
        ArrayMap<Scene, Transition> arrayMap = this.mScenePairTransitions.get(scene2);
        ArrayMap<Scene, Transition> arrayMap2 = arrayMap;
        if (arrayMap == null) {
            arrayMap2 = new ArrayMap<>();
            this.mScenePairTransitions.put(scene2, arrayMap2);
        }
        arrayMap2.put(scene, transition);
    }

    public void setTransition(@NonNull Scene scene, @Nullable Transition transition) {
        this.mSceneTransitions.put(scene, transition);
    }

    public void transitionTo(@NonNull Scene scene) {
        changeScene(scene, getTransition(scene));
    }
}
