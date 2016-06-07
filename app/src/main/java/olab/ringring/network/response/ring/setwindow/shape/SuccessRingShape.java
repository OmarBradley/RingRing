package olab.ringring.network.response.ring.setwindow.shape;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by 재화 on 2016-05-27.
 */
@Data
public class SuccessRingShape {
    @SerializedName("RING_INDEX")
    private int ringIndex;

    @SerializedName("COUPLE_ID")
    private int coupleId;

    @SerializedName("RING_SHAPE")
    private String ringShape;

    @SerializedName("RING_SHAPE_LEVEL")
    private String ringShapeLevel;

    @SerializedName("SHAPE_CIRCLE_LEVEL")
    private String shapeCircleLevel;

    @SerializedName("SHAPE_TRIANGLE_LEVEL")
    private String shapeTriangleLevel;

    @SerializedName("SHAPE_RECTANGLE_LEVEL")
    private String shapeRectangleLevel;

    @SerializedName("SHAPE_PENTAGON_LEVEL")
    private String shapePentagonLevel;

    @SerializedName("SHAPE_HEXAGON_LEVEL")
    private String shapeHexagonLevel;

    @SerializedName("SHAPE_OCTAGON_LEVEL")
    private String shapeOctagonLevel;
}
