package olab.ringring.main.ringdesign.choicedialog.attributeview;

import android.graphics.drawable.Drawable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import olab.ringring.main.ringdesign.levelpolicy.RingCollectCount;

/**
 * Created by 재화 on 2016-05-25.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RingDetailAttributeViewData {
    private String attributeName;
    private Drawable attributeImage;
    private Drawable setImage;
    private Drawable bigImage;
    private RingCollectCount collectCount;
    private String tag;

    @Data
    @Accessors(chain = true)
    public static class Builder{
        private String attributeName;
        private Drawable attributeImage;
        private Drawable setImage;
        private Drawable bigImage;
        private RingCollectCount collectCount;
        private String tag;


        public RingDetailAttributeViewData build(){
            RingDetailAttributeViewData data = new RingDetailAttributeViewData();
            data.setAttributeName(this.attributeName);
            data.setAttributeImage(this.attributeImage);
            data.setBigImage(this.bigImage);
            data.setCollectCount(this.collectCount);
            data.setSetImage(this.setImage);
            data.setTag(tag);
            return data;
        }
    }
}
