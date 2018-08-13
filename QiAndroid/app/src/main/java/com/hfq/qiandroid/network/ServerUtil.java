package com.hfq.qiandroid.network;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

public class ServerUtil {

    public static final int HTTP_GET = 0;
    public static final int HTTP_POST = 1;

    public interface IServerOk {
        void onSuccess(String response);
    }

    public interface IServerFailed {
        void onFailed();
    }


    public static class NetCallback extends Callback<String> {
        private IServerFailed mFailed;
        private IServerOk mOk;

        private NetCallback(IServerFailed failed, IServerOk ok) {
            this.mFailed = failed;
            this.mOk = ok;
        }

        @Override
        public String parseNetworkResponse(Response response, int id) throws Exception {
            return response.body().string();
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            mFailed.onFailed();
        }

        @Override
        public void onResponse(String response, int id) {
            mOk.onSuccess(response);
        }
    }

    public static void postRequest(int mode, String action, Map<String, String> paras, IServerOk ok, IServerFailed failed) {
        createRequest(mode, action, paras).execute(new NetCallback(failed, ok));
    }

    private static RequestCall createRequest(int mode, String action, Map<String, String> paras) {
        if (mode == HTTP_GET) {
            return OkHttpUtils.get().url(ServerUrls.ROOT_URL + action).params(paras).build();
        } else {
            return OkHttpUtils.post().url(ServerUrls.ROOT_URL + action).params(paras).build();
        }
    }


}
