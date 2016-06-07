package olab.ringring.network;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.annimon.stream.function.BiConsumer;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import olab.ringring.network.response.Success;
import olab.ringring.network.response.users.ValidateMessage;
import olab.ringring.network.response.users.ValidateSuccess;
import olab.ringring.util.functioninterface.TriConsumer;
import olab.ringring.network.cencel.RequestCancel;
import olab.ringring.network.okhttpclient.OKHttpClientFactory;
import olab.ringring.network.okhttpclient.RingRingHttpClientFactory;

/**
 * Created by 재화 on 2016-05-18.
 */

public class NetworkManager {

    private OkHttpClient client;
    private RequestCancel requestCancel;
    private Handler handler;

    private static final class SingletonHolder {
        private static final NetworkManager INSTANCE = new NetworkManager();
    }

    private NetworkManager() {
        OKHttpClientFactory ringRingClientFactory = new RingRingHttpClientFactory();
        client = ringRingClientFactory.buildOkHttpClient();
        requestCancel = new RequestCancel(client);
        handler = new Handler(Looper.getMainLooper());
    }

    public static NetworkManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public <T> Request sendRequest(Request request, Class<T> resultDataClass, BiConsumer<Request, T> onSuccess, TriConsumer<Request, NetworkResponseCode, Throwable> onFailure) {
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(() -> {
                    Log.e("fail", request.toString());
                    onFailure.accept(request,NetworkResponseCode.CONNECTION_FAIL, e);
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
                            onFailure.accept(request,NetworkResponseCode.WRONG_DATA ,new IOException(response.message()));
                        });
                    }
                } else {
                    handler.post(() -> {
                        onFailure.accept(request,NetworkResponseCode.RESPONSE_FAIL ,new IOException(response.message()));
                    });
                }
            }
        });
        return request;
    }

    public Request sendSignUpValidateRequest(Request request, BiConsumer<NetworkResponseCode, ValidateSuccess> onSuccess, TriConsumer<Request, NetworkResponseCode, Throwable> onFailure) {
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(() -> {
                    Log.e("fail", request.toString());
                    onFailure.accept(request, NetworkResponseCode.CONNECTION_FAIL, e);
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    ValidateSuccess searchResult = gson.fromJson(response.body().charStream(), ValidateSuccess.class);
                    if (searchResult.getMessage().equals(ValidateMessage.OK.getMessageType())) {
                        handler.post(() -> {
                            onSuccess.accept(NetworkResponseCode.CHECK_SUCCESS, searchResult);
                        });
                    } else {
                        handler.post(() -> {
                            Log.e("fail", request.toString());
                            onFailure.accept(request, NetworkResponseCode.CHECK_FAIL, new IOException(response.message()));
                        });
                    }
                }
            }
        });
        return request;
    }
}
