package textannotation.mwx.com.desgin.network;

import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpUtil {


    private static OkHttpClient mOkHttpClient;

    static {
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10,TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .build();
    }

    /**
     * 异步get 请求
     * @param address  get请求的url
     * @param callback 需要调用方重写的 请求成功(onResponse) 和 请求失败(onFailure) 的回调接口
     */
    public static void sendGetRequest(String address, Callback callback){
        Request request = new Request.Builder().url(address).build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }

    /**
     * 异步post请求
     * @param address 请求的url
     * @param requestBody 请求参数体 可以用
     *                    new FormBody.Builder().add(String,String)...build()
     *                    和
     *                    RequestBody.create(JSONType, new Gson().toJson(user))两种方式创建
     * @param callback  重写的回调接口
     */
    public static void sendPostRequest(String address, RequestBody requestBody, Callback callback){
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }




}
