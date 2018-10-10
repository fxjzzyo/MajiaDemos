package com.example.v_fanlulin.majiademo2;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.v_fanlulin.majiademo2.utils.MyUtil;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);


        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        initView();
        initData();
        initEvent();
    }

    private void initData() {

    }

    private void initEvent() {


    }

    private void initView() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                this.finish();
                break;
            default:
                break;
        }

        return true;
    }
    public void good(View view) {
        MyUtil.toastMessage(this, "评价成功！");
        this.finish();
    }

    public void bad(View view) {
        MyUtil.toastMessage(this, "评价成功！");
        this.finish();
    }

}
