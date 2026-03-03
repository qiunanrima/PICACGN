package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/OneTimeUpdateQAFragment_ViewBinding.class */
public class OneTimeUpdateQAFragment_ViewBinding implements Unbinder {
    private OneTimeUpdateQAFragment qt;

    @UiThread
    public OneTimeUpdateQAFragment_ViewBinding(OneTimeUpdateQAFragment oneTimeUpdateQAFragment, View view) {
        this.qt = oneTimeUpdateQAFragment;
        oneTimeUpdateQAFragment.editText_question_1 = (EditText) Utils.findRequiredViewAsType(view, 2131296477, "field 'editText_question_1'", EditText.class);
        oneTimeUpdateQAFragment.editText_question_2 = (EditText) Utils.findRequiredViewAsType(view, 2131296478, "field 'editText_question_2'", EditText.class);
        oneTimeUpdateQAFragment.editText_question_3 = (EditText) Utils.findRequiredViewAsType(view, 2131296479, "field 'editText_question_3'", EditText.class);
        oneTimeUpdateQAFragment.editText_answer_1 = (EditText) Utils.findRequiredViewAsType(view, 2131296471, "field 'editText_answer_1'", EditText.class);
        oneTimeUpdateQAFragment.editText_answer_2 = (EditText) Utils.findRequiredViewAsType(view, 2131296472, "field 'editText_answer_2'", EditText.class);
        oneTimeUpdateQAFragment.editText_answer_3 = (EditText) Utils.findRequiredViewAsType(view, 2131296473, "field 'editText_answer_3'", EditText.class);
        oneTimeUpdateQAFragment.button_update = (Button) Utils.findRequiredViewAsType(view, 2131296409, "field 'button_update'", Button.class);
        oneTimeUpdateQAFragment.toolbar = (Toolbar) Utils.findRequiredViewAsType(view, 2131297037, "field 'toolbar'", Toolbar.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        OneTimeUpdateQAFragment oneTimeUpdateQAFragment = this.qt;
        if (oneTimeUpdateQAFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.qt = null;
        oneTimeUpdateQAFragment.editText_question_1 = null;
        oneTimeUpdateQAFragment.editText_question_2 = null;
        oneTimeUpdateQAFragment.editText_question_3 = null;
        oneTimeUpdateQAFragment.editText_answer_1 = null;
        oneTimeUpdateQAFragment.editText_answer_2 = null;
        oneTimeUpdateQAFragment.editText_answer_3 = null;
        oneTimeUpdateQAFragment.button_update = null;
        oneTimeUpdateQAFragment.toolbar = null;
    }
}
