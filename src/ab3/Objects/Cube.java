package ab3.Objects;

import ab3.Datatypes.*;
import ab3.Primary.Themes;

public class Cube extends Object{

    public Cube(Vector3 position, float size, Transformation transformation, String shader, Themes texture) {
        init(cube(size/2), position, transformation, uvCoordinates(), shader, texture);
    }

    private float[] cube(float size) {
        return new float[]{
                size, -size, size,
                size, size, size,
                -size, -size, size,

                -size, size, size,
                -size, -size, size,
                size, size, size,


                size, -size, size,
                size, -size, -size,
                size, size, -size,

                size, size, -size,
                size, size, size,
                size, -size, size,


                -size, size, size,
                size, size, size,
                size, size, -size,

                size, size, -size,
                -size, size, -size,
                -size, size, size,


                -size, size, -size,
                size, size, -size,
                size, -size, -size,

                size, -size, -size,
                -size, -size, -size,
                -size, size, -size,


                -size, -size, size,
                -size, -size, -size,
                size, -size, -size,

                size, -size, -size,
                size, -size, size,
                -size, -size, size,


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
                1,0,
                1,1,
                0,0,
                0,1,
                0,0,
                1,1,

                0,1,
                0,0,
                1,0,
                1,0,
                1,1,
                0,1,

                0,1,
                1,1,
                1,0,
                1,0,
                0,0,
                0,1,

                0,1,
                1,1,
                1,0,
                1,0,
                0,0,
                0,1,

                0,1,
                0,0,
                1,0,
                1,0,
                1,1,
                0,1,

                0,1,
                1,1,
                1,0,
                1,0,
                0,0,
                0,1,
        };
    }
}
