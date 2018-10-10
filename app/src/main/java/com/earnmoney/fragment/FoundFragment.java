package com.earnmoney.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.baoyz.widget.PullRefreshLayout;
import com.earnmoney.DetailActivity;
import com.earnmoney.ExampleApplication;
import com.example.v_fanlulin.majiademo2.R;
import com.earnmoney.adapter.FoundListAdapter;
import com.earnmoney.javabean.Info;
import com.earnmoney.utils.MyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FoundFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoundFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private WebView mWebView;
    private String mUrl = "http://www.4y6y.com/article/2464.html";


    private ListView mListView;
    private FoundListAdapter myListAdapter;
    private List<Info> infos;


    private PullRefreshLayout layout;

    private int[] pics = {
            R.mipmap.p6,R.mipmap.p7,R.mipmap.p8,R.mipmap.p1,R.mipmap.p2,R.mipmap.p3,R.mipmap.p4,R.mipmap.p5
            ,R.mipmap.p9,R.mipmap.p10
    };

    public FoundFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FoundFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FoundFragment newInstance(String param1, String param2) {
        FoundFragment fragment = new FoundFragment();
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

        View view = inflater.inflate(R.layout.fragment_found, container, false);

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
        mListView = view.findViewById(R.id.lv_found);
        layout = view.findViewById(R.id.swipeRefreshLayout);

    }


    private void initData() {

        initInfos();

        myListAdapter = new FoundListAdapter(getActivity(), infos);

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

        // listen refresh event
        layout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // start refresh
                layout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ExampleApplication exampleApplication = ExampleApplication.getInstance();
                        int currentId = FoundFragment.this.infos.size()-1;
                        List<Info> infos =  exampleApplication.mWorkDB.getMoreTwoDatas(currentId);

                        //设置图片
                        for (int i = 0; i < infos.size(); i++) {
                            Info info = infos.get(i);
                            info.setPicture(pics[currentId+1+i]);
                            FoundFragment.this.infos.add(0,info);
                        }
                        myListAdapter.notifyDataSetChanged();
                        MyUtil.toastMessage(getActivity(),"刷新成功！");
                        if (infos.size() < 2) {
                            MyUtil.toastMessage(getActivity(),"已加载全部数据！");
                        }
                        layout.setRefreshing(false);
                    }
                }, 3000);

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

    }

}
