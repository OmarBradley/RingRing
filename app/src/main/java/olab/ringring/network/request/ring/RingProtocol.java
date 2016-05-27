package olab.ringring.network.request.ring;

import android.content.Context;

import okhttp3.Request;
import olab.ringring.network.request.RequestBuilder;

/**
 * Created by 재화 on 2016-05-27.
 */
public class RingProtocol {

    public static final Request makeIntroRequest(Context tag){
        return new RequestBuilder()
                .setTag(tag)
                .setUrl(RingProtocolUrl.INTRO.getUrl())
                .addPageSegment(RingProtocolUrl.INTRO.getPageSegment())
                .build();
    }
}
