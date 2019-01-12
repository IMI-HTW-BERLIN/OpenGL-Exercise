package ab3.Objects;

import ab3.Datatypes.*;

public class Cube extends Object{

    public Cube(Vector3 position, float size, Transformation transformation, String shader) {
        init(cube(size/2), position, transformation, uvCoordiantes(), shader);
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

    private float[] uvCoordiantes() {
        return new float[]{
                1,-1,
                1,1,
                -1,-1,
                -1,1,
                -1,-1,
                1,1,

                -1,1,
                -1,-1,
                1,-1,
                1,-1,
                1,1,
                -1,1,

                -1,1,
                1,1,
                1,-1,
                1,-1,
                -1,-1,
                -1,1,

                -1,1,
                1,1,
                1,-1,
                1,-1,
                -1,-1,
                -1,1,

                -1,1,
                -1,-1,
                1,-1,
                1,-1,
                1,1,
                -1,1,

                -1,1,
                1,1,
                1,-1,
                1,-1,
                -1,-1,
                -1,1,
        };
    }
}
