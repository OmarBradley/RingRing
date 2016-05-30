package olab.ringring.network.request.ring;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

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

    public static class SetWindow {
        public static final Request makeSetShapeWindowRequest(Context tag){
            return new RequestBuilder()
                    .setTag(tag)
                    .setUrl(RingProtocolUrl.SET_SHAPE_WINDOW.getUrl())
                    .addPageSegment(RingProtocolUrl.SET_SHAPE_WINDOW.getPageSegment())
                    .build();
        }

        public static final Request makeSetMaterialWindowRequest(Context tag){
            return new RequestBuilder()
                    .setTag(tag)
                    .setUrl(RingProtocolUrl.SET_MATERIAL_WINDOW.getUrl())
                    .addPageSegment(RingProtocolUrl.SET_MATERIAL_WINDOW.getPageSegment())
                    .build();
        }

        public static final Request makeSetJewelryWindowRequest(Context tag){
            return new RequestBuilder()
                    .setTag(tag)
                    .setUrl(RingProtocolUrl.SET_JEWELRY_WINDOW.getUrl())
                    .addPageSegment(RingProtocolUrl.SET_JEWELRY_WINDOW.getPageSegment())
                    .build();
        }
    }

    public static class Select{
        public static final Request makeSelectShapeRequest(Context tag, String selectedShape){
            Map<String, String> bodyParameters = new HashMap<>();
            bodyParameters.put("ringShape", selectedShape);
            return new RequestBuilder()
                    .setTag(tag)
                    .setUrl(RingProtocolUrl.SELECT_SHAPE.getUrl())
                    .addPageSegment(RingProtocolUrl.SELECT_SHAPE.getPageSegment())
                    .addBodyParameters(bodyParameters)
                    .build();
        }

        public static final Request makeSelectMaterialRequest(Context tag, String selectedMaterial){
            Map<String, String> bodyParameters = new HashMap<>();
            bodyParameters.put("ringMaterial", selectedMaterial);
            return new RequestBuilder()
                    .setTag(tag)
                    .setUrl(RingProtocolUrl.SELECT_MATERIAL.getUrl())
                    .addPageSegment(RingProtocolUrl.SELECT_MATERIAL.getPageSegment())
                    .addBodyParameters(bodyParameters)
                    .build();
        }


        public static final Request makeSelectJewelryRequest(Context tag, String selectedJewelry){
            Map<String, String> bodyParameters = new HashMap<>();
            bodyParameters.put("ringJewelry", selectedJewelry);
            return new RequestBuilder()
                    .setTag(tag)
                    .setUrl(RingProtocolUrl.SELECT_JEWELRY.getUrl())
                    .addPageSegment(RingProtocolUrl.SELECT_JEWELRY.getPageSegment())
                    .addBodyParameters(bodyParameters)
                    .build();
        }
    }
}
