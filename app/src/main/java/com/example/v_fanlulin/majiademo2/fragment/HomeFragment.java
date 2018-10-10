package com.example.v_fanlulin.majiademo2.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import com.baoyz.widget.PullRefreshLayout;
import com.example.v_fanlulin.majiademo2.DetailActivity;
import com.example.v_fanlulin.majiademo2.ExampleApplication;
import com.example.v_fanlulin.majiademo2.R;
import com.example.v_fanlulin.majiademo2.adapter.MyListAdapter;
import com.example.v_fanlulin.majiademo2.javabean.Info;
import com.example.v_fanlulin.majiademo2.utils.MyUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGALocalImageSize;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private WebView mWebView;
    private String mUrl = "http://www.4y6y.com/";
//    http://www.4y6y.com/article/2464.html

    private ListView mListView;
    private MyListAdapter myListAdapter;
    private List<Info> infos;

    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    private BGABanner mBgaBanner;

    private PullRefreshLayout layout;

    private int[] pics = {
            R.mipmap.p6,R.mipmap.p7,R.mipmap.p8,R.mipmap.p1,R.mipmap.p2,R.mipmap.p3,R.mipmap.p4,R.mipmap.p5
     ,R.mipmap.p9,R.mipmap.p10
    };

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);


        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initData();

    }


    private void initView() {

        View view = getView();
        mListView = view.findViewById(R.id.lv_home);
        mBgaBanner = view.findViewById(R.id.banner_guide_content);
        layout = view.findViewById(R.id.swipeRefreshLayout);

    }


    private void initData() {



        initInfos();

        myListAdapter = new MyListAdapter(getActivity(),infos);

        mListView.setAdapter(myListAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                Info info = infos.get(position);

                intent.putExtra("info",info);
                startActivity(intent);
            }
        });

// Bitmap 的宽高在 maxWidth maxHeight 和 minWidth minHeight 之间
        BGALocalImageSize localImageSize = new BGALocalImageSize(720, 1280, 320, 640);
// 设置数据源
        mBgaBanner.setData(localImageSize, ImageView.ScaleType.CENTER_CROP,
                R.drawable.banner_1,
                R.drawable.banner_2,
                R.drawable.banner_3,
                R.drawable.banner_4,
                R.drawable.banner_5
                );
        // listen refresh event
        layout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // start refresh
                layout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ExampleApplication exampleApplication = ExampleApplication.getInstance();
                        int currentId = HomeFragment.this.infos.size()-1;
                        List<Info> infos =  exampleApplication.mWorkDB.getMoreTwoDatas(currentId);

                        //设置图片
                        for (int i = 0; i < infos.size(); i++) {
                            Info info = infos.get(i);
                            info.setPicture(pics[currentId+1+i]);
                            HomeFragment.this.infos.add(info);
                        }
                        myListAdapter.notifyDataSetChanged();
                        MyUtil.toastMessage(getActivity(),"刷新成功！");
                        if (infos.size() < 2) {
                            MyUtil.toastMessage(getActivity(),"已加载全部数据！");
                        }
                        layout.setRefreshing(false);
                    }
                }, 2000);

            }
        });


    }


    private void initInfos() {
        ExampleApplication exampleApplication = ExampleApplication.getInstance();
        List<Info> infos =  exampleApplication.mWorkDB.getFirstFiveDatas();
        this.infos = new ArrayList<>();
        //设置图片
        for (int i = 0; i < infos.size(); i++) {
            Info info = infos.get(i);
            info.setPicture(pics[i]);
            this.infos.add(info);
        }


       /* infos = new ArrayList<>();

        Info info1 = new Info();
        info1.setTitle("办公室文员");
        info1.setType("长期");
        info1.setSalary("2000-2500元/月");
        info1.setPicture(R.mipmap.p1);

        Info info2 = new Info();
        info2.setTitle("办公室文员");
        info2.setType("长期");
        info2.setSalary("2000-2500元/月");
        info2.setPicture(R.mipmap.p1);

        Info info3 = new Info();
        info3.setTitle("办公室文员");
        info3.setType("长期");
        info3.setSalary("2000-2500元/月");
        info3.setPicture(R.mipmap.p1);


        Info info4 = new Info();
        info4.setTitle("办公室文员");
        info4.setType("长期");
        info4.setSalary("2000-2500元/月");
        info4.setPicture(R.mipmap.p1);

        Info info5 = new Info();
        info5.setTitle("办公室文员");
        info5.setType("长期");
        info5.setSalary("2000-2500元/月");
        info5.setPicture(R.mipmap.p1);

        infos.add(info1);
        infos.add(info2);
        infos.add(info3);
        infos.add(info4);
        infos.add(info5);*/

    }


}
