package olab.ringring.network.response;

import android.os.Bundle;

/**
 * Created by 재화 on 2016-06-09.
 */
public interface BundleMapper<T> {
    public T mapBundle(Bundle data);
}
