package com.earnmoney;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.earnmoney.utils.MyUtil;
import com.earnmoney.utils.SharedPreferenceUtil;

public class MyInfoActivity extends AppCompatActivity {

    private EditText etName, etAge, etPhone, etAddress, etGraduate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        initView();
        initData();
        initEvent();

    }

    private void initData() {
        String name = SharedPreferenceUtil.getData(this, SharedPreferenceUtil.kEY_REAL_NAME);
        String age = SharedPreferenceUtil.getData(this, SharedPreferenceUtil.kEY_AGE);
        String address = SharedPreferenceUtil.getData(this, SharedPreferenceUtil.kEY_ADDRESS);
        String phone = SharedPreferenceUtil.getData(this, SharedPreferenceUtil.kEY_PHONE);
        String graduate = SharedPreferenceUtil.getData(this, SharedPreferenceUtil.kEY_GRADUATE);

        etAddress.setText(address);
        etAge.setText(age);
        etPhone.setText(phone);
        etGraduate.setText(graduate);
        etName.setText(name);
    }

    private void initEvent() {


    }

    private void initView() {
        etName = findViewById(R.id.et_name);
        etAddress = findViewById(R.id.et_address);
        etAge = findViewById(R.id.et_age);
        etGraduate = findViewById(R.id.et_graduate);
        etPhone = findViewById(R.id.et_phone);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
            default:
                break;
        }

        return true;
    }

    public void save(View view) {

        String name = etName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String graduate = etGraduate.getText().toString().trim();
        String age = etAge.getText().toString().trim();
        String address = etAddress.getText().toString().trim();


        SharedPreferenceUtil.saveData(this, SharedPreferenceUtil.kEY_REAL_NAME, name);
        SharedPreferenceUtil.saveData(this, SharedPreferenceUtil.kEY_AGE, age);
        SharedPreferenceUtil.saveData(this, SharedPreferenceUtil.kEY_GRADUATE, graduate);
        SharedPreferenceUtil.saveData(this, SharedPreferenceUtil.kEY_PHONE, phone);
        SharedPreferenceUtil.saveData(this, SharedPreferenceUtil.kEY_ADDRESS, address);
        MyUtil.toastMessage(this, "保存成功");


    }


}
