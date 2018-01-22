package com.azilen.demoapp.ui.room.add;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.azilen.demoapp.R;
import com.azilen.demoapp.ui.BaseActivity;
import com.azilen.demoapp.ui.room.db.AppDatabase;
import com.azilen.demoapp.ui.room.db.User;
import com.azilen.demoapp.utils.Const;
import com.azilen.demoapp.utils.helper.ErrorWatcher;

/**
 * Created by paresh on 19-01-2018
 */

public class AddUserActivity extends BaseActivity implements AddUserContract.EspressoView, View.OnClickListener, ErrorWatcher.Watcher {

    private AddUserPresenter ePresenter;

    private Toolbar mToolbar;
    private EditText etFName;
    private EditText etLName;
    private EditText etAge;
    private Button btnSubmit;

    private ErrorWatcher errorWatcherFName;
    private ErrorWatcher errorWatcherLName;
    private ErrorWatcher errorWatcherAge;

    private User user;

    @Override
    protected int getLayoutXML() {
        return R.layout.activity_add_user;
    }

    @Override
    protected void initImpl() {
        Bundle b = getIntent().getExtras();
        if (null != b && b.containsKey(Const.ARG_EX_USER)) {
            user = b.getParcelable(Const.ARG_EX_USER);
        }

        ePresenter = new AddUserPresenter(this, AppDatabase.getInstance(this));

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
        etFName = findViewById(R.id.etFName);
        etLName = findViewById(R.id.etLName);
        etAge = findViewById(R.id.etAge);
        btnSubmit = findViewById(R.id.btnSubmit);

        attachTextChangeListeners();
        bindData();

        btnSubmit.setOnClickListener(this);
    }

    private void attachTextChangeListeners() {
        errorWatcherFName = new ErrorWatcher(this, etFName);
        errorWatcherLName = new ErrorWatcher(this, etLName);
        errorWatcherAge = new ErrorWatcher(this, etAge);

        etFName.addTextChangedListener(errorWatcherFName);
        etLName.addTextChangedListener(errorWatcherLName);
        etAge.addTextChangedListener(errorWatcherAge);
    }

    private void bindData() {
        if (user == null) return;

        etFName.setText(user.getFirstName());
        etLName.setText(user.getLastName());
        etAge.setText(String.valueOf(user.getAge()));

        mToolbar.setTitle(R.string.update_user);
        btnSubmit.setText(R.string.update);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:
                String fName = etFName.getText().toString().trim();
                String lName = etLName.getText().toString().trim();
                String age = etAge.getText().toString().trim();

                if (validateInputs(fName, lName, age)) {
                    User user = new User();
                    if (this.user != null) {
                        user.setUid(this.user.getUid());
                    }
                    user.setFirstName(fName);
                    user.setLastName(lName);
                    user.setAge(Integer.parseInt(age));
                    if (this.user != null) {
                        ePresenter.updateUser(user);
                    } else {
                        ePresenter.addUser(user);
                    }
                }
                break;
        }
    }

    /**
     * Perform necessary validations before further process
     * @param fName First name
     * @param lName Last name
     * @param age age
     * @return true if all is well, false if not
     */
    private boolean validateInputs(String fName, String lName, String age) {
        if (fName.isEmpty()) {
            etFName.setError(getString(R.string.field_required));
            etFName.requestFocus();
            return false;
        }

        if (lName.isEmpty()) {
            etLName.setError(getString(R.string.field_required));
            etLName.requestFocus();
            return false;
        }

        if (age.isEmpty()) {
            etAge.setError(getString(R.string.field_required));
            etAge.requestFocus();
            return false;
        }

        if (Integer.parseInt(age) == 0) {
            etAge.setError(getString(R.string.age_not_zero));
            etAge.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void onError(String message) {
        Snackbar.make(etAge, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onUserAdded(User... user) {
        Intent intent = getIntent();
        intent.putExtra(Const.ARG_EX_F_NAME, user[0].getFirstName());
        intent.putExtra(Const.ARG_EX_IS_UPDATE, this.user != null);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        ePresenter.clear();
        etFName.removeTextChangedListener(errorWatcherFName);
        etLName.removeTextChangedListener(errorWatcherLName);
        etAge.removeTextChangedListener(errorWatcherAge);
        super.onDestroy();
    }

    @Override
    public void onTextChange(EditText editText) {
        editText.setError(null);
    }
}
