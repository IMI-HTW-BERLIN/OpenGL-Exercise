package ab3.Objects;

import ab3.Datatypes.*;
import ab3.Primary.Themes;

public class Cube extends Object{

    public Cube(Vector3 position, float size, Transformation transformation, String shader, Themes texture) {
        init(cube(size/2), position, transformation, uvCoordinates(), shader, texture);
    }

    private float[] cube(float size) {
        return new float[]{
                //front
                size, -size, size,
                size, size, size,
                -size, -size, size,

                -size, size, size,
                -size, -size, size,
                size, size, size,

                //right
                size, -size, size,
                size, -size, -size,
                size, size, -size,

                size, size, -size,
                size, size, size,
                size, -size, size,

                //top
                -size, size, size,
                size, size, size,
                size, size, -size,

                size, size, -size,
                -size, size, -size,
                -size, size, size,

                //back
                -size, size, -size,
                size, size, -size,
                size, -size, -size,

                size, -size, -size,
                -size, -size, -size,
                -size, size, -size,

                //bottom
                -size, -size, size,
                -size, -size, -size,
                size, -size, -size,

                size, -size, -size,
                size, -size, size,
                -size, -size, size,

                //left
                -size, -size, size,
                -size, size, size,
                -size, size, -size,

                -size, size, -size,
                -size, -size, -size,
                -size, -size, size,

        };
    }

    private float[] uvCoordinates() {
        return new float[]{
                //front
                1,1,
                1,0,
                0,1,
                0,0,
                0,1,
                1,0,
                //right
                0,1,
                1,1,
                1,0,
                1,0,
                0,0,
                0,1,
                //top
                0,1,
                1,1,
                1,0,
                1,0,
                0,0,
                0,1,
                //back
                1,0,
                0,0,
                0,1,
                0,1,
                1,1,
                1,0,
                //bottom
                1,1,
                1,0,
                0,0,
                0,0,
                0,1,
                1,1,
                //left
                1,1,
                1,0,
                0,0,
                0,0,
                0,1,
                1,1,
        };
    }
}
