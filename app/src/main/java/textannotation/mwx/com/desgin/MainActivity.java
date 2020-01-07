package textannotation.mwx.com.desgin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.io.IOException;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;

import textannotation.mwx.com.desgin.model.User;
import textannotation.mwx.com.desgin.network.OkHttpUtil;
import textannotation.mwx.com.desgin.network.Url;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity_tag";
    private Button mNetworkTestButton;
    private TextView textView;
    private int  count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNetworkTestButton = findViewById(R.id.network_test_button);
        textView = findViewById(R.id.textView);
    }

    String info = "";
    public static final MediaType JSONType = MediaType.parse("application/json");

    public void checkFileContentById(View view){
        Toast.makeText(this, "fileContent click button", Toast.LENGTH_SHORT).show();
        OkHttpUtil.sendGetRequest(Url.extradotaskUrl+"?docId=15&status=全部&taskId=9&userId=3", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("获取信息失败");
                    }
                });
            }
            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                String temp = response.body().string();

                JSONObject jsonObject = (JSONObject) JSON.parse(temp);
                JSONObject userData = (JSONObject) jsonObject.get("data");
                //info = new String(jsonObject.toJSONString().getBytes("ISO-8859-1"),"UTF-8");
                info = jsonObject.toJSONString();
                //info = userData.get("userId").toString()+" "+userData.toJSONString();
                count++;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(info + " " + count);
                    }
                });
            }
        });
    }

    public void registerNewUser(View view){
        Toast.makeText(this, "register click button", Toast.LENGTH_SHORT).show();
        User user = new User();
        user.setEmail("2345345@qq.com");
        user.setUsername("2021");
        user.setPassword("2024");
        user.setBirthday("2020-01-12 12:12:12");
        user.setSex("girl");
        user.setJob("worker");

        RequestBody requestBody = RequestBody.create(JSONType, new Gson().toJson(user));
        //RequestBody.
        OkHttpUtil.sendPostRequest(Url.userRegisterUrl, requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("获取信息失败");
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String temp = response.body().string();
                JSONObject jsonObject = (JSONObject) JSON.parse(temp);
                info = jsonObject.toJSONString();
                //info = userData.get("userId").toString()+" "+userData.toJSONString();
                count++;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(info + " " + count);
                    }
                });
            }
        });

    }


    public void tryLogin(View view) {
        Toast.makeText(this, "click button", Toast.LENGTH_SHORT).show();

        RequestBody requestBody = new FormBody.Builder()
                .add("username", "test")
                .add("password", "123456")
                .build();

        OkHttpUtil.sendPostRequest(Url.userloginUrl, requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("获取信息失败");
                    }
                });
            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                String temp = response.body().string();

                JSONObject jsonObject = (JSONObject) JSON.parse(temp);
                JSONObject userData = (JSONObject) jsonObject.get("data");
                //info = new String(jsonObject.toJSONString().getBytes("ISO-8859-1"),"UTF-8");
                info = jsonObject.toJSONString();
                //info = userData.get("userId").toString()+" "+userData.toJSONString();
                count++;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(info + " " + count);
                    }
                });
            }
        });
    }

}
