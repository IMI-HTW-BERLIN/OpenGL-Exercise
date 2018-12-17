package ab3;

import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;

import java.util.Arrays;
import java.util.Random;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Cube extends AbstractOpenGLBase {
    private ShaderProgram shaderProgram;
    private String shader;
    private float x,y,z;
    private float[] edges;
    private Matrix4 mat = new Matrix4();
    private float angleX;
    private float angleY;

    public Cube(float x, float y, float z, String shader) {
        this.shader = shader;
        this.x = x;
        this.y = y;
        this.z = z;
        this.init();
    }

    @Override
    protected void init() {
        shaderProgram = new ShaderProgram(shader);
        glUseProgram(shaderProgram.getId());

        edges = dice(-0.5f, -0.5f, 0.5f, 1F);

        int vaold = glGenVertexArrays();
        glBindVertexArray(vaold);
        int vbold = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbold);
        glBufferData(GL_ARRAY_BUFFER, edges, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(0);

        float[] normals = getNormals();

        vbold = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbold);
        glBufferData(GL_ARRAY_BUFFER, normals, GL_STATIC_DRAW);
        glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(1);

        glEnable(GL_DEPTH_TEST); // z-Buffer aktivieren
        glEnable(GL_CULL_FACE); // backface culling aktivieren
    }

    @Override
    protected void update() {
        rotate();
    }

    @Override
    protected void render() {
        //glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); <----------------
        glDrawArrays(GL_TRIANGLES, 0, edges.length / 3);

        int loc = glGetUniformLocation(shaderProgram.getId(), "mat");
        glUniformMatrix4fv(loc, false, mat.getValuesAsArray());
    }

    @Override
    protected void destroy() {

    }
    //----------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------

    private void rotate() {
        angleX = ((angleX + 0.01f) % 360);
        angleY = ((angleY + 0.01f) % 360);
        mat = new Matrix4().rotateX(angleX).rotateY(angleY).translate(x, y, z);
    }

    private void rotateRandom() {
        angleX = ((angleX + (new Random().nextInt(100) + 1) * 0.001f) % 360);
        angleY = ((angleY + (new Random().nextInt(100) + 1) * 0.001f) % 360);
        mat = new Matrix4().rotateX(angleX).rotateY(angleY).translate(x, y, z);
    }

    private float[] getNormals() {
        float[] normals = new float[edges.length];
        for (int i = 0; i < edges.length; i += 9) {
            Vector3 firstVector = Vector3.getVector(edges[i], edges[i + 1], edges[i + 2], edges[i + 3], edges[i + 4], edges[i + 5]);
            Vector3 secondVector = Vector3.getVector(edges[i], edges[i + 1], edges[i + 2], edges[i + 6], edges[i + 7], edges[i + 8]);
            Vector3 normal = Vector3.getNormal(firstVector, secondVector);
            for (int j = 0; j < 3; j++) {
                normals[i + j * 3] = normal.getAsArray()[0];
                normals[i + j * 3 + 1] = normal.getAsArray()[1];
                normals[i + j * 3 + 2] = normal.getAsArray()[2];
            }
        }
        return normals;
    }

    //----------------------------------------------------------------------------------------------------
    private float[] dice(float x, float y, float z, float size) {
        return new float[]{
                x + size, y, z,
                x + size, y + size, z,
                x, y, z,

                x, y + size, z,
                x, y, z,
                x + size, y + size, z,


                x + size, y, z,
                x + size, y, z - size,
                x + size, y + size, z - size,

                x + size, y + size, z - size,
                x + size, y + size, z,
                x + size, y, z,


                x, y + size, z,
                x + size, y + size, z,
                x + size, y + size, z - size,

                x + size, y + size, z - size,
                x, y + size, z - size,
                x, y + size, z,


                x, y + size, z - size,
                x + size, y + size, z - size,
                x + size, y, z - size,

                x + size, y, z - size,
                x, y, z - size,
                x, y + size, z - size,


                x, y, z,
                x, y, z - size,
                x + size, y, z - size,

                x + size, y, z - size,
                x + size, y, z,
                x, y, z,


                x, y, z,
                x, y + size, z,
                x, y + size, z - size,

                x, y + size, z - size,
                x, y, z - size,
                x, y, z,

        };
    }
}
