package olab.ringring.network.request;

import android.content.Context;

import com.annimon.stream.Stream;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by 재화 on 2016-05-26.
 */
public class RequestBuilder {

    private HttpUrl.Builder httpUrlBuilder;
    private Request.Builder requestBuilder;
    private FormBody.Builder requestBodyBuilder;
    private MultipartBody.Builder requestMultipartBodyBuilder;

    private static final String IMAGE_MEDIA_TYPE = "image/jpeg";
    public static final String BASE_URL = "http://52.36.101.232:3000";

    public RequestBuilder setTag(Context tag) {
        requestBuilder = new Request.Builder();
        requestBuilder.tag(tag);
        return this;
    }

    public RequestBuilder setUrl(String url) {
        httpUrlBuilder = HttpUrl.parse(url).newBuilder();
        return this;
    }

    public RequestBuilder addQueryParameters(Map<String, String> queryParameters) {
        Stream.of(queryParameters).forEach(queryParameter -> {
            httpUrlBuilder.addQueryParameter(queryParameter.getKey(), queryParameter.getValue());
        });
        return this;
    }

    public RequestBuilder addPageSegment(String pageSegment) {
        httpUrlBuilder.addPathSegment(pageSegment);
        return this;
    }

    public RequestBuilder addHeaderParameters(Map<String, String> headerParameters) {
        requestBuilder.headers(Headers.of(headerParameters));
        return this;
    }

    public RequestBuilder addBodyParameters(Map<String, String> bodyParameters) {
        requestBodyBuilder = new FormBody.Builder();
        Stream.of(bodyParameters).forEach(bodyParameter -> {
            requestBodyBuilder.add(bodyParameter.getKey(), bodyParameter.getValue());
        });
        requestBuilder.post(requestBodyBuilder.build());
        return this;
    }

    public RequestBuilder addImageFileParameter(Map<String, String> bodyParameters, ImageFileFormData imageFileFormData) {
        requestMultipartBodyBuilder = new MultipartBody.Builder();
        requestMultipartBodyBuilder.setType(MultipartBody.FORM);
        Stream.of(bodyParameters).forEach(bodyParameter -> {
            requestMultipartBodyBuilder.addFormDataPart(bodyParameter.getKey(), bodyParameter.getValue());
        });
        requestMultipartBodyBuilder.addFormDataPart(imageFileFormData.getBodyName(), imageFileFormData.getFileName(), RequestBody.create(MediaType.parse(IMAGE_MEDIA_TYPE), imageFileFormData.getImageFile()));
        requestBuilder.post(requestMultipartBodyBuilder.build());
        return this;
    }

    public RequestBuilder addImageFileParameter(ImageFileFormData imageFileFormData) {
        requestMultipartBodyBuilder = new MultipartBody.Builder();
        requestMultipartBodyBuilder.setType(MultipartBody.FORM);
        requestMultipartBodyBuilder.addFormDataPart(imageFileFormData.getBodyName(), imageFileFormData.getFileName(), RequestBody.create(MediaType.parse(IMAGE_MEDIA_TYPE), imageFileFormData.getImageFile()));
        requestBuilder.post(requestMultipartBodyBuilder.build());
        return this;
    }

    public Request build() {
        requestBuilder.url(httpUrlBuilder.build());
        return requestBuilder.build();
    }
}
