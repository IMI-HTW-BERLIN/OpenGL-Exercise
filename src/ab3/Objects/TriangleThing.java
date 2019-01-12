package ab3.Objects;
import ab3.Datatypes.*;

public class TriangleThing extends Object{

    public TriangleThing(Vector3 position, float size, Object.Transformation transformation, String shader) {
        init(triangleThing(), position, transformation, uvCoordinates(), shader);
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

    private float[] uvCoordinates() {
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
