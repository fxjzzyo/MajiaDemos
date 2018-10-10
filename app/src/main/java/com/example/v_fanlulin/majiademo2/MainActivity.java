package com.example.v_fanlulin.majiademo2;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.v_fanlulin.majiademo2.adapter.MyFragmentPagerAdapter;
import com.example.v_fanlulin.majiademo2.fragment.FoundFragment;
import com.example.v_fanlulin.majiademo2.fragment.HomeFragment;
import com.example.v_fanlulin.majiademo2.fragment.MeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private ViewPager mViewPager;
    private ImageView ivHome, ivPhone, ivMe;
    private TextView tvHome, tvPhone, tvMe;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    public static MainActivity mainActivityInstance;
    private List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_main);


        initView();

        initData();
        initEvent();

    }



    private void initView() {

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        ivHome = (ImageView) findViewById(R.id.iv_home);
        ivPhone = (ImageView) findViewById(R.id.iv_phone);
        ivMe = (ImageView) findViewById(R.id.iv_me);
        tvHome = (TextView) findViewById(R.id.tv_home);
        tvPhone = (TextView) findViewById(R.id.tv_phone);
        tvMe = (TextView) findViewById(R.id.tv_me);
    }


    private void initData() {
        //初始化fragment
        mFragments = new ArrayList<>();
        HomeFragment homeFragment = HomeFragment.newInstance("home", "home");
        FoundFragment foundFragment = FoundFragment.newInstance("found", "found");
        MeFragment meFragment = MeFragment.newInstance("me", "me");

        mFragments.add(homeFragment);
        mFragments.add(foundFragment);
        mFragments.add(meFragment);
        //初始化适配器
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragments);
        //为ViewPager设置设适配器
        mViewPager.setAdapter(myFragmentPagerAdapter);
        mViewPager.setOffscreenPageLimit(2);
        //为viewPager添加监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if(position == 1)
                {
                    if(positionOffset!=0)
                    {

                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
                setItemSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //初始化Fragment为首页
        setItemSelected(0);
    }


    private void initEvent() {
        ivHome.setOnClickListener(this);
        ivPhone.setOnClickListener(this);
        ivMe.setOnClickListener(this);
    }
    /**
     * 设置选中项
     *
     * @param position
     */
    private void setItemSelected(int position) {
        //先reset所有项
        ivHome.setSelected(false);
        ivPhone.setSelected(false);
        ivMe.setSelected(false);
        resetTextColor();
        //再设置选择项
        switch (position) {
            case 0:
                ivHome.setSelected(true);
                tvHome.setTextColor(getResources().getColor(R.color.colorPrimary));
                setTitle("首页");
                break;
            case 1:
                ivPhone.setSelected(true);
                tvPhone.setTextColor(getResources().getColor(R.color.colorPrimary));
                setTitle("发现");
                break;
            case 2:
                ivMe.setSelected(true);
                tvMe.setTextColor(getResources().getColor(R.color.colorPrimary));
                setTitle("我的");
                break;
            default:
                break;
        }
        mViewPager.setCurrentItem(position);
    }

    public void resetTextColor() {
        tvMe.setTextColor(getResources().getColor(R.color.Gray));
        tvPhone.setTextColor(getResources().getColor(R.color.Gray));
        tvHome.setTextColor(getResources().getColor(R.color.Gray));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_home:
                setItemSelected(0);
                break;
            case R.id.iv_phone:
                setItemSelected(1);
                break;
            case R.id.iv_me:
                setItemSelected(2);
                break;
        }
    }




}
