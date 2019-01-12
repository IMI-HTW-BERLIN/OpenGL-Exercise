package ab3.Primary;

import static org.lwjgl.opengl.GL30.*;

import ab3.Objects.*;
import ab3.Datatypes.*;
import ab3.Objects.Object;
import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;

import java.util.ArrayList;
import java.util.List;

public class Aufgabe3undFolgende extends AbstractOpenGLBase {
    float focus;
    float ambientLightIntensity;
    float ambientStrength;
    float diffuseStrength;
    float specularStrength;
    float xRotation;
    float yRotation;
    float rotationSpeed;
    int theme = 0;
    boolean[] objectSelected;
    /*------------------------------------------------------------------------------------------------------------*/
    private ShaderProgram shaderProgram;
    LightSource light = new LightSource(new Vector3(4f, 0f, 1f), 5f);
    private List<Object> objects = new ArrayList<>();
    private String shader = "aufgabe3";
    //----------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        new Aufgabe3undFolgende().start("CG Aufgabe 3", 2000, 2000);
    }


    Aufgabe3undFolgende() {
    }

    @Override
    protected void init() {
        shaderProgram = new ShaderProgram(shader);
        glUseProgram(shaderProgram.getId());
        addObjects();



        /*------------------------------------------------------------------------------------------------------------*/
        Matrix4 projectMat = new Matrix4(0.4F, 10F, 2, 2);
        int loc = glGetUniformLocation(shaderProgram.getId(), "projectMat");
        glUniformMatrix4fv(loc, false, projectMat.getValuesAsArray());

        /*------------------------------------------------------------------------------------------------------------*/

        int lightPosID = glGetUniformLocation(shaderProgram.getId(), "lightPos");
        glUniform3fv(lightPosID, light.getPos().getAsArray());

        //----------------------------------------------------------------------------------------------------
    }

    private void addObjects() {
        objects.clear();
        objects.add(new Cube(new Vector3(0, 0, -2), 1, Object.Transformation.ROTATE_XY, shader));
        objects.add(new Cube(new Vector3(-1, 2, -2), 1, Object.Transformation.ROTATE_Y, shader));
        objects.add(new Cube(new Vector3(2, -1, -3), 1, Object.Transformation.ROTATE_X, shader));
        objects.add(new Cube(new Vector3(0, -1, -6), 1, Object.Transformation.ROTATE_XY, shader));

        objects.add(new TriangleThing(new Vector3(-1, -2, -3), 1, Object.Transformation.ROTATE_X, shader));
        objects.add(new TriangleThing(new Vector3(1, 2, -3), 1, Object.Transformation.ROTATE_Y, shader));
    }

    @Override
    public void update() {
        for (int i = 0; i < objects.size(); i++) {
            if (objectSelected[i]) {
                objects.get(i).update();
                objects.forEach(e -> e.setAngleX(xRotation));
                objects.forEach(e -> e.setAngleY(yRotation));
                objects.forEach(e -> e.setRotationSpeed(rotationSpeed));
            }
        }

    }

    @Override
    protected void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        for (int i = 0; i < objects.size(); i++) {
            if (objectSelected[i]) objects.get(i).render();
        }


        int focusID = glGetUniformLocation(shaderProgram.getId(), "focus");
        int ambientLightID = glGetUniformLocation(shaderProgram.getId(), "ambientLightIntensity");
        int ambientStrengthID = glGetUniformLocation(shaderProgram.getId(), "ambientStrength");
        int diffuseStrengthID = glGetUniformLocation(shaderProgram.getId(), "diffuseStrength");
        int specularStrengthID = glGetUniformLocation(shaderProgram.getId(), "specularStrength");

        glUniform1f(focusID, focus);
        glUniform1f(ambientLightID, ambientLightIntensity);
        glUniform1f(ambientStrengthID, ambientStrength);
        glUniform1f(diffuseStrengthID, diffuseStrength);
        glUniform1f(specularStrengthID, specularStrength);

        int lightIntensityID = glGetUniformLocation(shaderProgram.getId(), "lightIntensity");
        glUniform1f(lightIntensityID, light.getIntensity());

        int themeID = glGetUniformLocation(shaderProgram.getId(), "theme");
        glUniform1i(themeID, theme);


    }

    @Override
    public void destroy() {
    }
}