package com.azilen.demoapp.utils.helper;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by paresh on 19-01-2018
 */

public class ErrorWatcher implements TextWatcher {

    private Watcher watcher;
    private EditText targetEt;

    public ErrorWatcher(Watcher watcher, EditText editText) {
        this.watcher = watcher;
        this.targetEt = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        watcher.onTextChange(targetEt);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public interface Watcher {
        void onTextChange(EditText editText);
    }
}
