package olab.ringring.network.okhttpclient;

import android.content.Context;

import java.io.File;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.JavaNetCookieJar;
import olab.ringring.init.application.RingRingApplication;
import olab.ringring.network.cookie.PersistentCookieStore;

/**
 * Created by 재화 on 2016-05-26.
 */
public class RingRingHttpClientFactory extends OKHttpClientFactory {

    private static final int MAX_CACHE_SIZE = 10 * 1024 * 1024;
    private static final String DEFAULT_CACHE_DIRECTORY = "mycache";
    private static final long TIME_OUT = 10000;
    private static final TimeUnit TIME_UNIT = TimeUnit.MILLISECONDS;
    Context context = RingRingApplication.getContext();

    @Override
    protected void setCookieOfOKHttpClientBuilder() {
        CookieManager cookieManager = new CookieManager(new PersistentCookieStore(context), CookiePolicy.ACCEPT_ALL);
        builder.cookieJar(new JavaNetCookieJar(cookieManager));
    }

    @Override
    protected void setClientCacheOfOKHttpClientBuilder() {
        Context context = RingRingApplication.getContext();
        File dir = new File(context.getCacheDir(), DEFAULT_CACHE_DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Cache cache = new Cache(dir, MAX_CACHE_SIZE);
        builder.cache(cache);
    }

    @Override
    protected void setTimeOutOfOKHttpClientBuilder() {
        builder.connectTimeout(TIME_OUT, TIME_UNIT);
        builder.readTimeout(TIME_OUT, TIME_UNIT);
        builder.writeTimeout(TIME_OUT, TIME_UNIT);
    }
}
