package ab3;

import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;

import java.util.Arrays;
import java.util.Random;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Cube extends Object{

    public Cube(Vector3 position, float size, Transformation transformation,String shader) {
        init(cube(size), position, transformation,shader);
    }

    private float[] cube(float size) {
        return new float[]{
                size/2, -size/2, size/2,
                size/2, size/2, size/2,
                -size/2, -size/2, size/2,

                -size/2, size/2, size/2,
                -size/2, -size/2, size/2,
                size/2, size/2, size/2,


                size/2, -size/2, size/2,
                size/2, -size/2, -size/2,
                size/2, size/2, -size/2,

                size/2, size/2, -size/2,
                size/2, size/2, size/2,
                size/2, -size/2, size/2,


                -size/2, size/2, size/2,
                size/2, size/2, size/2,
                size/2, size/2, -size/2,

                size/2, size/2, -size/2,
                -size/2, size/2, -size/2,
                -size/2, size/2, size/2,


                -size/2, size/2, -size/2,
                size/2, size/2, -size/2,
                size/2, -size/2, -size/2,

                size/2, -size/2, -size/2,
                -size/2, -size/2, -size/2,
                -size/2, size/2, -size/2,


                -size/2, -size/2, size/2,
                -size/2, -size/2, -size/2,
                size/2, -size/2, -size/2,

                size/2, -size/2, -size/2,
                size/2, -size/2, size/2,
                -size/2, -size/2, size/2,


                -size/2, -size/2, size/2,
                -size/2, size/2, size/2,
                -size/2, size/2, -size/2,

                -size/2, size/2, -size/2,
                -size/2, -size/2, -size/2,
                -size/2, -size/2, size/2,

        };
    }
}
