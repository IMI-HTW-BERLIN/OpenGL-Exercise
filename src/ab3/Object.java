package ab3;

import lenz.opengl.ShaderProgram;

import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public abstract class Object {
    private ShaderProgram shaderProgram;
    private float[] edges;
    private Vector3 position;
    private Matrix4 mat = new Matrix4();
    private float angleX = 0;
    private float angleY = 0;
    private float currentAngleX = 0;
    private float currentAngleY = 0;

    public enum Transformation {ROTATE_X, ROTATE_Y, ROTATE_XY, ROTATE_RND}

    private Transformation transformation;


    void init(float[] edges, Vector3 position, Transformation transformation, String shader) {
        shaderProgram = new ShaderProgram(shader);
        glUseProgram(shaderProgram.getId());

        this.edges = edges;
        this.position = position;
        this.transformation = transformation;

        int vaold = glGenVertexArrays();
        glBindVertexArray(vaold);
        int vbold = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbold);
        glBufferData(GL_ARRAY_BUFFER, edges, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(0);

        float[] normals = getNormals(edges);

        vbold = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbold);
        glBufferData(GL_ARRAY_BUFFER, normals, GL_STATIC_DRAW);
        glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(1);

        glEnable(GL_DEPTH_TEST); // z-Buffer aktivieren
        glEnable(GL_CULL_FACE); // backface culling aktivieren
    }

    void update() {
        transform(transformation);
        mat.translate(position.x(), position.y(), position.z());
    }

    void render() {
        glDrawArrays(GL_TRIANGLES, 0, edges.length / 3);

        int loc = glGetUniformLocation(shaderProgram.getId(), "mat");
        glUniformMatrix4fv(loc, false, mat.getValuesAsArray());
    }
    //----------------------------------------------------------------------------------------------------

    private float[] getNormals(float[] edges) {
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

    void setAngleX(float angleX) {
        this.angleX = angleX;
    }

    void setAngleY(float angleY) {
        this.angleY = angleY;
    }

    private void transform(Transformation transformation) {
        mat = new Matrix4();
        switch (transformation) {
            case ROTATE_X:
                currentAngleX = (currentAngleX + 0.01f) % 360;
                break;
            case ROTATE_Y:
                currentAngleY = (currentAngleY + 0.01f) % 360;
                break;
            case ROTATE_XY:
                currentAngleX = (currentAngleX + 0.01f) % 360;
                currentAngleY = (currentAngleY + 0.01f) % 360;
                break;
            case ROTATE_RND:

                break;
        }
        float nextAngleX = (currentAngleX + angleX) % 360;
        float nextAngleY = (currentAngleY + angleY) % 360;
        mat.rotateX(nextAngleX).rotateY(nextAngleY);
    }
}
