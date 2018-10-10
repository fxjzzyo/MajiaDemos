package com.example.v_fanlulin.majiademo2;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.v_fanlulin.majiademo2.javabean.Info;

import java.io.Serializable;

public class DetailActivity extends AppCompatActivity {

    private Info info;
    private ImageView ivPic;
    private TextView tvTitle,tvTime,tvSalary,tvPersons,tvAddress,tvDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        setTitle("详情");

        info = (Info) getIntent().getSerializableExtra("info");



        initViews();
        initData();
    }

    private void initData() {
        ivPic.setImageResource(info.getPicture());

        tvTitle.setText(info.getTitle());
        tvTime.setText(info.getTime());
        tvSalary.setText(info.getSalary());
        tvPersons.setText(info.getPersonCount());
        tvAddress.setText(info.getAddress());
        tvDetail.setText(info.getDetail());

    }

    private void initViews() {
         ivPic = findViewById(R.id.iv_pic);
        tvAddress = findViewById(R.id.tv_address);
        tvDetail = findViewById(R.id.tv_detail);
        tvPersons = findViewById(R.id.tv_person_count);
        tvSalary = findViewById(R.id.tv_salary);
        tvTime = findViewById(R.id.tv_time);
        tvTitle = findViewById(R.id.tv_title);
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
}
