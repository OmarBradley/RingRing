package olab.ringring.network.okhttpclient;

import okhttp3.OkHttpClient;

/**
 * Created by 재화 on 2016-05-26.
 */
// use template method pattern
public abstract class OKHttpClientFactory {
    OkHttpClient.Builder builder;

    public final OkHttpClient buildOkHttpClient() {
        builder = new OkHttpClient().newBuilder();
        setCookieOfOKHttpClientBuilder();
        setClientCacheOfOKHttpClientBuilder();
        setTimeOutOfOKHttpClientBuilder();
        return builder.build();
    }

    abstract protected void setCookieOfOKHttpClientBuilder();

    abstract protected void setClientCacheOfOKHttpClientBuilder();

    abstract protected void setTimeOutOfOKHttpClientBuilder();
}