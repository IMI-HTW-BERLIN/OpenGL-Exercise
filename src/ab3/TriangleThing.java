package ab3;

class TriangleThing extends Object{

    TriangleThing(Vector3 position, float size, Object.Transformation transformation, String shader) {
        init(triangleThing(), position, transformation,shader, uvCoordiantes());
    }

    private float[] triangleThing() {
        float val = (float) (Math.sqrt(3) / 2);
        return new float[]{
                //front
                0.5f, val, 0,
                -0.5f, val, 0,
                0, -val, 0.5f,

                //left
                -0.5f, val, 0,
                0, -val, -0.5f,
                0, -val, 0.5f,

                //right
                0.5f, val, 0,
                0, -val, 0.5f,
                0, -val, -0.5f,

                //back
                -0.5f, val, 0,
                0.5f, val, 0,
                0, -val, -0.5f
        };
    }

    private float[] uvCoordiantes() {
        return new float[]{
                1,-1,
                1,1,
                -1,-1,

                1,-1,
                1,1,
                -1,-1,

                1,-1,
                1,1,
                -1,-1,

                1,-1,
                1,1,
                -1,-1,


        };
    }
}
