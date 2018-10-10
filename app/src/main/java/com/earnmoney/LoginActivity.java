package com.earnmoney;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.earnmoney.utils.MyUtil;
import com.earnmoney.utils.SharedPreferenceUtil;
import com.earnmoney.view.CustomProgressDialog;

import static com.earnmoney.Global.user;

public class LoginActivity extends AppCompatActivity {

    private static final int SUCCESS = 1;
    private static final int PASS_ERROR = 0;
    private static final int UN_REGISTER = 2;
    private static final int FAILED = 3;

    private EditText etName, etPassword;
    private CustomProgressDialog progressDialog;

    @SuppressLint("HandlerLeak")
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS:
                    Bundle bundle = msg.getData();
                    MyUtil.toastMessage(LoginActivity.this, "登录成功！");

                    //记录登录状态
                    SharedPreferenceUtil.saveData(LoginActivity.this,SharedPreferenceUtil.IS_LOGIN_KEY,"isLogin");

                    user = new User();
                    user.setUserName((String) bundle.get("userName"));
                    user.setUserPassword((String) bundle.get("userPass"));
                    //跳到主页
                    jumpToActivity(MainActivity.class);
                    LoginActivity.this.finish();

                    break;
                case PASS_ERROR:
                    MyUtil.toastMessage(LoginActivity.this, "密码错误！");
                    break;
                case UN_REGISTER:
                    MyUtil.toastMessage(LoginActivity.this, "用户未注册！");
                    break;
                case FAILED:
                    MyUtil.toastMessage(LoginActivity.this, "登录失败！");
                    break;

            }
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
                progressDialog = null;
            }

        }
    };

    /**
     * 跳转到某个activity
     *
     * @param cla
     */
    private void jumpToActivity(Class<?> cla) {
        Intent intent = new Intent(LoginActivity.this, cla);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initData();
        initEvent();
    }

    private void initView() {
        setTitle("登录");
        etName = (EditText) findViewById(R.id.et_name);
        etPassword = (EditText) findViewById(R.id.et_password);
    }

    private void initData() {
        //判断是否已经登录
        String isLogin = SharedPreferenceUtil.getData(LoginActivity.this, SharedPreferenceUtil.IS_LOGIN_KEY);
        if (isLogin != null) {
            String name = SharedPreferenceUtil.getData(LoginActivity.this, SharedPreferenceUtil.KEY_NAME);
            String password = SharedPreferenceUtil.getData(LoginActivity.this, SharedPreferenceUtil.KEY_PASSWORD);

            if (Global.isNetAvailable||true) {
                progressDialog = CustomProgressDialog.newInstance(this, "登录中...", false, null);
                progressDialog.show();

                new LoginThread(name, password).start();
            } else {
                Toast.makeText(this, "网络不可用！", Toast.LENGTH_SHORT).show();
            }
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        //设置输入框
        String name = SharedPreferenceUtil.getData(LoginActivity.this, SharedPreferenceUtil.KEY_NAME);
        String password = SharedPreferenceUtil.getData(LoginActivity.this, SharedPreferenceUtil.KEY_PASSWORD);
        if (name != null) {
            etName.setText(name);
        }

        if (password != null) {
            etPassword.setText(password);
        }

    }

    private void initEvent() {

    }

    /**
     * 根据是否记住密码，初始化登录框
     */
    private void initUserEditText() {
        String name = SharedPreferenceUtil.getData(this, SharedPreferenceUtil.KEY_NAME);
        String password = SharedPreferenceUtil.getData(this, SharedPreferenceUtil.KEY_PASSWORD);
        if (name != null) {
            etName.setText(name);
            etName.setSelection(name.length());
        }
        if (password != null) {
            etPassword.setText(password);
            etPassword.setSelection(password.length());
        }

    }

    /**
     * 点击 登录 按钮
     *
     * @param view
     */
    public void login(View view) {
        String name = etName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (check(name, password)) {

            if (Global.isNetAvailable||true) {
                progressDialog = CustomProgressDialog.newInstance(this, "登录中...", false, null);
                progressDialog.show();


                new LoginThread(name, password).start();
            } else {
                Toast.makeText(this, "网络不可用！", Toast.LENGTH_SHORT).show();
            }

        }

    }

    /**
     * 检查输入的信息是否合法
     *
     * @param name
     * @param password
     * @return
     */
    private boolean check(String name, String password) {

        if (name.isEmpty()) {
            MyUtil.toastMessage(this, "用户名不能为空！");
            return false;
        }
        if (password.isEmpty()) {
            MyUtil.toastMessage(this, "密码不能为空！");
            return false;
        } else {
            if (password.length() != 6) {
                MyUtil.toastMessage(this, "请输入6位密码！");
                return false;
            }
        }

        return true;

    }

    /**
     * 用于登录的子线程
     */
    class LoginThread extends Thread {
        private String name, password;

        public LoginThread(String name, String password) {
            this.name = name;
            this.password = password;
        }

        @Override
        public void run() {

            try {
                Thread.sleep(2000);

                String name2 = SharedPreferenceUtil.getData(LoginActivity.this, SharedPreferenceUtil.KEY_NAME);
                String pass2 = SharedPreferenceUtil.getData(LoginActivity.this, SharedPreferenceUtil.KEY_PASSWORD);

                if (name2!=null&&pass2!=null&& name2.equals(name) && pass2.equals(password)) {
                    //存储用户名密码
//                    saveUserName(name, password);

                    Message message = new Message();
                    message.what = SUCCESS;
                    Bundle bundle = new Bundle();
                    bundle.putString("userName", name);
                    bundle.putString("userPass", password);
                    message.setData(bundle);
                    mHandler.sendMessage(message);
                } else {
                    Message message = new Message();
                    message.what = FAILED;
                    mHandler.sendMessage(message);

                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 是否记住用户名，密码
     *
     * @param name
     * @param password
     */
    private void saveUserName(String name, String password) {
        SharedPreferenceUtil.saveData(this, SharedPreferenceUtil.KEY_NAME, name);
        SharedPreferenceUtil.saveData(this, SharedPreferenceUtil.KEY_PASSWORD, password);


    }
    /**
     * 点击 随便看看 按钮
     *
     * @param view
     */
    public void scan(View view) {

        jumpToActivity(MainActivity.class);
        LoginActivity.this.finish();

    }
    /**
     * 点击 还没有账号
     *
     * @param view
     */
    public void jumpToRegister(View view) {
        jumpToActivity(RegisterActivity.class);
    }

}
