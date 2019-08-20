package a2;

import static org.lwjgl.opengl.GL30.*;

import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;

public class Aufgabe2 extends AbstractOpenGLBase {

    private ShaderProgram shaderProgram;

    //public static void main(String[] args) {
        //new Aufgabe2().start("CG Aufgabe 2", 1000, 1000);
    //}

    float[] dreiecke;

    @Override
    protected void init() {
        // folgende Zeile läd automatisch "aufgabe2.v" (vertex) und "aufgabe2.f" (fragment)
        shaderProgram = new ShaderProgram("aufgabe2");
        glUseProgram(shaderProgram.getId());

        dreiecke = new float[]{
                -0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f,
                0.5f, 0.5f, -0.5f, 0.5f, -0.5f, -0.5f,

        };

        int vaold = glGenVertexArrays();
        glBindVertexArray(vaold);
        int vbold = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbold);
        glBufferData(GL_ARRAY_BUFFER, dreiecke, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(0);
        // Koordinaten, VAO, VBO, ... hier anlegen und im Grafikspeicher ablegen
    }

    private float winkel = 0;

    @Override
    public void update() {
        winkel = (winkel + 1f) % 360;
        glUniform1f(glGetUniformLocation(shaderProgram.getId(), "winkel"), winkel);
    }

    @Override
    protected void render() {
        glClear(GL_COLOR_BUFFER_BIT); // Zeichenfläche leeren

        glDrawArrays(GL_TRIANGLES, 0, 6);
        // hier vorher erzeugte VAOs zeichnen
    }

    @Override
    public void destroy() {
    }
}
