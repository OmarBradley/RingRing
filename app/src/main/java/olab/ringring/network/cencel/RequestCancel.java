package olab.ringring.network.cencel;

import com.annimon.stream.Stream;

import okhttp3.Call;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

/**
 * Created by 재화 on 2016-05-26.
 */
public class RequestCancel {
    Dispatcher dispatcher;

    public RequestCancel(OkHttpClient client) {
        dispatcher = client.dispatcher();
    }

    public void cancelAll(){
        dispatcher.cancelAll();
    }

    public void cancelTag(Object tag){
        cancelWaitingRequests(tag);
        cancelRunningRequests(tag);
    }

    private void cancelWaitingRequests(Object tag){
        Stream.of(dispatcher.queuedCalls())
                .filter(call->{return call.request().tag().equals(tag);})
                .forEach(Call::cancel);
    }

    private void cancelRunningRequests(Object tag){
        Stream.of(dispatcher.runningCalls())
                .filter(call->{return call.request().tag().equals(tag);})
                .forEach(Call::cancel);
    }
}