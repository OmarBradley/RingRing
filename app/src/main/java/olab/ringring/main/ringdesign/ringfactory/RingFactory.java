package olab.ringring.main.ringdesign.ringfactory;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

import olab.ringring.main.ringdesign.ringattribute.jewelry.RingJewelry;
import olab.ringring.main.ringdesign.ringattribute.material.RingMaterial;
import olab.ringring.main.ringdesign.ringattribute.shape.RingShape;

/**
 * Created by 재화 on 2016-05-24.
 */

// TODO: 2016-05-24 이 클래스는 abstract factory 패턴을 사용함
public interface  RingFactory {
    public void createRingJewelry(RingJewelry jewelry);
    public void createRingMaterial(RingMaterial material);
    public void createRingShape(RingShape shape);
}
