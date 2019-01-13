package ab3.Objects;

import ab3.Datatypes.*;
import ab3.Primary.Themes;

public class TriangleThing extends Object {

    public TriangleThing(Vector3 position, float size, Object.Transformation transformation, String shader, Themes texture) {
        init(triangleThing(size / 2), position, transformation, uvCoordinates(), shader, texture);
    }

    private float[] triangleThing(float size) {
        float val = (float) (size * (Math.sqrt(3) / 2));
        return new float[]{
                //front
                size, val, 0,
                -size, val, 0,
                0, -val, size,

                //left
                -size, val, 0,
                0, -val, -size,
                0, -val, size,

                //right
                size, val, 0,
                0, -val, size,
                0, -val, -size,

                //back
                -size, val, 0,
                size, val, 0,
                0, -val, -size
        };
    }

    private float[] uvCoordinates() {
        return new float[]{
                1, 0,
                0, 0,
                0.5f, 1,

                0.5f, 1,
                0, 0,
                1, 0,

                0.5f, 1,
                0, 0,
                1, 0,

                1, 0,
                0, 0,
                0.5f, 1,


        };
    }
}
