package olab.ringring.network;

import android.os.Handler;
import android.os.Looper;

import com.annimon.stream.function.BiConsumer;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import olab.ringring.network.response.Success;
import olab.ringring.util.functioninterface.TriConsumer;
import olab.ringring.network.cencel.RequestCancel;
import olab.ringring.network.okhttpclient.OKHttpClientFactory;
import olab.ringring.network.okhttpclient.RingRingHttpClientFactory;

/**
 * Created by 재화 on 2016-05-18.
 */

// TODO: 2016-05-18 뷰부분 끝난 후 network manager 만들기
public class NetworkManager {

    private OkHttpClient client;
    private RequestCancel requestCancel;

    private static final class SingletonHolder {
        private static final NetworkManager INSTANCE = new NetworkManager();
    }

    private NetworkManager() {
        OKHttpClientFactory ringRingClientFactory = new RingRingHttpClientFactory();
        client = ringRingClientFactory.buildOkHttpClient();
        requestCancel = new RequestCancel(client);
    }

    public static NetworkManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public <T> Request getResult(Request request, Class<T> resultDataClass, BiConsumer<Request, T> onSuccess, TriConsumer<Request, Integer, Throwable> onFailure) {
        Handler handler = new Handler(Looper.getMainLooper());
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(() -> {
                    onFailure.accept(request, -1, e);
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    Gson gson = new Gson();
                    Success searchResult = gson.fromJson(response.body().charStream(), Success.class);
                    if(searchResult.getSuccess() == 1){
                        T successData = gson.fromJson(gson.toJson(searchResult.getUserResults().get(0)), resultDataClass);
                        handler.post(() -> {
                            onSuccess.accept(request, successData);
                        });
                    } else {
                        handler.post(() -> {
                            onFailure.accept(request,-2 ,new IOException(response.message()));
                        });
                    }
                } else {
                    handler.post(() -> {
                        onFailure.accept(request,-3 ,new IOException(response.message()));
                    });
                }
            }
        });
        return request;
    }

}
