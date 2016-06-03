package olab.ringring.util.preperance;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import olab.ringring.init.application.RingRingApplication;
import olab.ringring.main.ringdesign.ringattribute.jewelry.RingJewelry;
import olab.ringring.main.ringdesign.ringattribute.material.RingMaterial;
import olab.ringring.main.ringdesign.ringattribute.shape.RingShape;
import olab.ringring.network.NetworkManager;
import olab.ringring.network.request.mymenu.MyMenuProtocol;
import olab.ringring.network.response.mymenu.intro.MyMenuIntroResult;

/**
 * Created by 재화 on 2016-06-01.
 */

public class PropertyManager {
    private static final class SingletonHolder{
        private static final PropertyManager INSTANCE = new PropertyManager();
    }

    public static PropertyManager getInstance(){
        return SingletonHolder.INSTANCE;
    }

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    private PropertyManager(){
        prefs = PreferenceManager.getDefaultSharedPreferences(RingRingApplication.getContext());
        editor = prefs.edit();
    }

    private static final String FIELD_REGISTRATION_ID = "regid";
    private static final String USER_JEWELRY = "user_jewelry";
    private static final String USER_SHAPE = "user_shape";
    private static final String USER_MATERIAL = "user_material";

    public static final String DEFAULT_JEWELRY = "RUBY";
    public static final String DEFAULT_SHAPE ="CIRCLE";
    public static final String DEFAULT_MATERIAL = "GOLD";

    public void setRegistrationToken(String token) {
        editor.putString(FIELD_REGISTRATION_ID, token);
        editor.commit();
    }
    public String getRegistrationToken(){
        return prefs.getString(FIELD_REGISTRATION_ID, "");
    }

    public void setUserJewelry(RingJewelry jewelry){
        editor.putString(USER_JEWELRY, jewelry.name());
        editor.commit();
    }

    public RingJewelry getUserJewelry(){
        return RingJewelry.valueOf(prefs.getString(USER_JEWELRY, DEFAULT_JEWELRY));
    }

    public void setUserShape(RingShape shape){
        editor.putString(USER_SHAPE, shape.name());
        editor.commit();
    }

    public RingShape getUserShape(){
        return RingShape.valueOf(prefs.getString(USER_SHAPE, DEFAULT_SHAPE));
    }

    public void setUserMaterial(RingMaterial material){
        editor.putString(USER_MATERIAL, material.name());
        editor.commit();
    }

    public RingMaterial getUserMaterial(){
        return RingMaterial.valueOf(prefs.getString(USER_MATERIAL, DEFAULT_MATERIAL));
    }

    public boolean isDefaultRingProperty() {
        if (getUserJewelry().equals(DEFAULT_JEWELRY) || getUserShape().equals(DEFAULT_SHAPE) || getUserMaterial().equals(DEFAULT_MATERIAL)) {
            return true;
        } else {
            return false;
        }
    }

    public void setAllRingAttribute(RingJewelry jewelry, RingShape shape, RingMaterial material) {
        setUserJewelry(jewelry);
        setUserShape(shape);
        setUserMaterial(material);
    }


}
