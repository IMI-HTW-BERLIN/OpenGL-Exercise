package ab3;

class Cube extends Object{

    Cube(Vector3 position, float size, Transformation transformation,String shader) {
        init(cube(size/2), position, transformation,shader, uvCoordiantes());
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
