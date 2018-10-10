package com.earnmoney.fragment;


import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.earnmoney.AboutActivity;
import com.earnmoney.FeedbackActivity;
import com.earnmoney.Global;
import com.earnmoney.LoginActivity;
import com.earnmoney.MyInfoActivity;
import com.earnmoney.R;
import com.earnmoney.utils.MyUtil;
import com.earnmoney.utils.SharedPreferenceUtil;

import static com.earnmoney.Global.user;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageView ivMotifyMe;
    private TextView tvUserName,tvLoveCount;
    private ImageView sivUserPic;
    private RelativeLayout rlFeedBack,rlAbout,rlLogout,rlMotifyInfo,rlClearCache;
    private BroadcastReceiver mBroadcastReceiver;

    public MeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MeFragment newInstance(String param1, String param2) {
        MeFragment fragment = new MeFragment();
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
        return inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

        initEvent();
    }



    private void initView() {

        View view = getView();
        sivUserPic = (ImageView) view.findViewById(R.id.siv_user_pic);
        ivMotifyMe = (ImageView) view.findViewById(R.id.iv_motify_me);
        tvUserName = (TextView) view.findViewById(R.id.tv_user_name);
        rlFeedBack = (RelativeLayout) view.findViewById(R.id.rl_feed_back);
        rlAbout= (RelativeLayout) view.findViewById(R.id.rl_about);
        rlLogout = (RelativeLayout) view.findViewById(R.id.rl_logout);

        rlMotifyInfo = (RelativeLayout) view.findViewById(R.id.rl_my_info);
        rlClearCache = (RelativeLayout) view.findViewById(R.id.rl_clear_cache);
    }

    private void initEvent() {
        if(null!=user)//已登录
        {
            tvUserName.setText(user.getUserName());

        }
        rlFeedBack.setOnClickListener(new MyOnClickListener());
        rlAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent4);
            }
        });
        rlClearCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Toast.makeText(getActivity(), "清除成功！", Toast.LENGTH_SHORT).show();
            }
        });
        rlLogout.setOnClickListener(new MyOnClickListener());
        rlMotifyInfo.setOnClickListener(new MyOnClickListener());
        ivMotifyMe.setOnClickListener(new MyOnClickListener());
        //点击用户名，如果没登录，跳转到登录页，否则跳转到修改me页
        tvUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Global.user!=null)
                {
                    Intent intent4 = new Intent(getActivity(), MyInfoActivity.class);
                    startActivity(intent4);
                }else {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });
    }

    class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if(user==null)
            {
                MyUtil.toastMessage(getActivity(),"你还没有登录！");
                return;
            }
            switch (view.getId())
            {

                case R.id.rl_feed_back:
                    Intent fdIntent = new Intent(getActivity(), FeedbackActivity.class);
                    startActivity(fdIntent);
                    break;
                case R.id.rl_my_info:
                    Intent infoIntent = new Intent(getActivity(), MyInfoActivity.class);
                    startActivity(infoIntent);
                    break;
                case R.id.iv_motify_me:

                    break;

                case R.id.rl_logout:
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    user = null;
                    //设置下次取消登录
                    SharedPreferenceUtil.saveData(MeFragment.this.getActivity(),SharedPreferenceUtil.IS_LOGIN_KEY,null);

                    getActivity().finish();
                    break;

            }
        }
    }


}
