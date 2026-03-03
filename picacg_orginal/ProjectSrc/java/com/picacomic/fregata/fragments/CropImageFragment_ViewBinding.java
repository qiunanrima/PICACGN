package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageButton;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.isseiaoki.simplecropview.CropImageView;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/CropImageFragment_ViewBinding.class */
public class CropImageFragment_ViewBinding implements Unbinder {
    private CropImageFragment oJ;

    @UiThread
    public CropImageFragment_ViewBinding(CropImageFragment cropImageFragment, View view) {
        this.oJ = cropImageFragment;
        cropImageFragment.mCropView = (CropImageView) Utils.findRequiredViewAsType(view, 2131296431, "field 'mCropView'", CropImageView.class);
        cropImageFragment.imageButton_rotateLeft = (ImageButton) Utils.findRequiredViewAsType(view, 2131296558, "field 'imageButton_rotateLeft'", ImageButton.class);
        cropImageFragment.imageButton_rotateRight = (ImageButton) Utils.findRequiredViewAsType(view, 2131296559, "field 'imageButton_rotateRight'", ImageButton.class);
        cropImageFragment.imageButton_done = (ImageButton) Utils.findRequiredViewAsType(view, 2131296557, "field 'imageButton_done'", ImageButton.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        CropImageFragment cropImageFragment = this.oJ;
        if (cropImageFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.oJ = null;
        cropImageFragment.mCropView = null;
        cropImageFragment.imageButton_rotateLeft = null;
        cropImageFragment.imageButton_rotateRight = null;
        cropImageFragment.imageButton_done = null;
    }
}
