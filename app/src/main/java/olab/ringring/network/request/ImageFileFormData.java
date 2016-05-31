package olab.ringring.network.request;

import java.io.File;

import lombok.Data;

/**
 * Created by 재화 on 2016-05-26.
 */
@Data
public class ImageFileFormData {
    private String bodyName;
    private String fileName;
    private File imageFile;
}
