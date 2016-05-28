package olab.ringring.main.ringdesign.choicedialog.attributeview;

import android.graphics.drawable.Drawable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import olab.ringring.main.ringdesign.levelpolicy.RingCollectCount;

/**
 * Created by 재화 on 2016-05-25.
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class RingDetailAttributeViewData {
    private String attributeName;
    private Drawable attributeImage;
    private Drawable setImage;
    private Drawable bigImage;
    private RingCollectCount collectCount;



}
