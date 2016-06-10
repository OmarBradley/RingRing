package olab.ringring.util.preperance;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import olab.ringring.init.application.RingRingApplication;
import olab.ringring.main.ringdesign.ringattribute.jewelry.RingJewelry;
import olab.ringring.main.ringdesign.ringattribute.material.RingMaterial;
import olab.ringring.main.ringdesign.ringattribute.shape.RingShape;
import olab.ringring.network.response.mymenu.home.SuccessMyMenuIntro;

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
    private static final String USER_NAME = "user_name";
    private static final String USER_PROFILE_IMAGE_URL = "user_profile_image_url";
    private static final String USER_EMAIL = "user_email";
    private static final String USER_PASSWORD = "user_password";
    private static final String USER_INDEX_ID ="user_index_id";
    private static final String LOVER_INDEX_ID = "lover_index_id";

    public static final String DEFAULT_JEWELRY = "RUBY";
    public static final String DEFAULT_SHAPE ="CIRCLE";
    public static final String DEFAULT_MATERIAL = "GOLD";
    public static final String DEFAULT_USER_NAME ="";
    public static final String DEFAULT_USER_PROFILE_IMAGE = "";
    public static final String DEFAULT_USER_EMAIL = "";
    public static final String DEFAULT_USER_PASSWORD = "";
    public static final int DEFAULT_USER_INDEX_ID = 0;
    public static final int DEFAULT_LOVER_INDEX_ID = 1;


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

    public void setUserName(String userName){
        editor.putString(USER_NAME, userName);
        editor.commit();
    }

    public void setUserProfileImageUrl(String userProfileImageUrl){
        editor.putString(USER_PROFILE_IMAGE_URL, userProfileImageUrl);
        editor.commit();
    }

    public String getUserName() {
        return prefs.getString(USER_NAME, DEFAULT_USER_NAME);
    }

    public String getUserProfileImageUrl() {
        return prefs.getString(USER_PROFILE_IMAGE_URL, DEFAULT_USER_PROFILE_IMAGE);
    }

    public boolean isDefaultUserProperty(){
        if(getUserName().equals(DEFAULT_USER_NAME) || getUserProfileImageUrl().equals(DEFAULT_USER_PROFILE_IMAGE)){
            return true;
        } else {
            return false;
        }
    }

    public void setUserProperty(SuccessMyMenuIntro data){
        setUserName(data.getUserNickname());
        setUserProfileImageUrl(data.getUserProfile());
    }

    public void setUserEmail(String userEmail){
        editor.putString(USER_EMAIL, userEmail);
        editor.commit();
    }

    public String getUserEmail() {
        return prefs.getString(USER_EMAIL, DEFAULT_USER_EMAIL);
    }

    public void setUserPassword(String password){
        editor.putString(USER_PASSWORD, password);
        editor.commit();
    }

    public String getUserPassword() {
        return prefs.getString(USER_PASSWORD, DEFAULT_USER_PASSWORD);
    }

    public boolean isDefaultUserLoginProperty(){
        if(getUserPassword().equals(DEFAULT_USER_PASSWORD) || getUserEmail().equals(DEFAULT_USER_EMAIL)){
            return true;
        } else {
            return false;
        }
    }

    public void setUserIndexId(int userIndexId){
        editor.putInt(USER_INDEX_ID, userIndexId);
        editor.commit();
    }

    public int getUserIndexId(){
        return prefs.getInt(USER_INDEX_ID, DEFAULT_USER_INDEX_ID);
    }

    public void setLoverIndexId(int loverIndexId){
        editor.putInt(LOVER_INDEX_ID, loverIndexId);
        editor.commit();
    }

    public int getLoverIndexId(){
        return prefs.getInt(LOVER_INDEX_ID, DEFAULT_LOVER_INDEX_ID);
    }


}
