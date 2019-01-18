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
    LightSource light = new LightSource(new Vector3(4f, 0f, 0f), 5f);
    private List<Object> objects = new ArrayList<>();
    private String shader = "aufgabe3";
    private Themes currentTexture = Themes.TEX1;
    private InputHandler inputHandler;
    //----------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        new Aufgabe3undFolgende().start("CG Aufgabe 3", 1000, 1000);
    }


    Aufgabe3undFolgende() {
    }

    @Override
    protected void init() {
        shaderProgram = new ShaderProgram(shader);
        glUseProgram(shaderProgram.getId());
        addObjects();

        /*------------------------------------------------------------------------------------------------------------*/
        Matrix4 projectMat = new Matrix4(0.6F, 100F, 2, 2);
        int loc = glGetUniformLocation(shaderProgram.getId(), "projectMat");
        glUniformMatrix4fv(loc, false, projectMat.getValuesAsArray());

        /*------------------------------------------------------------------------------------------------------------*/

        //----------------------------------------------------------------------------------------------------
        inputHandler = new InputHandler(window,light);

    }

    private void addObjects() {
        objects.clear();
        objects.add(new Cube(new Vector3(0, 0, -2), 1, Object.Transformation.ROTATE_XY, shader, currentTexture));
        objects.add(new Cube(new Vector3(-1, 2, -2), 1, Object.Transformation.ROTATE_Y, shader, currentTexture));
        objects.add(new Cube(new Vector3(2, -1, -3), 1, Object.Transformation.ROTATE_X, shader, currentTexture));
        objects.add(new Cube(new Vector3(0, 0, -6), 6, Object.Transformation.ROTATE_XY, shader, currentTexture));

        objects.add(new TriangleThing(new Vector3(-1, -2, -3), 1, Object.Transformation.ROTATE_X, shader, currentTexture));
        objects.add(new TriangleThing(new Vector3(1, 2, -3), 1, Object.Transformation.ROTATE_Y, shader, currentTexture));
        objects.add(new TriangleThing(new Vector3(0, -1, -2), 1, Object.Transformation.ROTATE_Y, shader, currentTexture));
        objects.add(new TriangleThing(new Vector3(0, 0, -6), 6, Object.Transformation.ROTATE_Y, shader, currentTexture));

    }

    @Override
    public void update() {
        inputHandler.moveLight();
        switch (theme) {
            case 10:
                if (!shader.equals("aufgabeTexture")) changeShader("aufgabeTexture");
                break;
            case 20:
            case 21:
            case 22:
                if (!shader.equals("textureGenerated")) changeShader("textureGenerated");
                break;
            default:
                if (!shader.equals("aufgabe3")) changeShader("aufgabe3");
                break;
        }


        objects.forEach(Object::update);
        objects.forEach(e -> e.setAngleX(xRotation));
        objects.forEach(e -> e.setAngleY(yRotation));
        objects.forEach(e -> e.setRotationSpeed(rotationSpeed));

    }

    @Override
    protected void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        for (int i = 0; i < objects.size(); i++) {
            if (objectSelected[i]) {
                objects.get(i).render();
            }
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

        int lightPosID = glGetUniformLocation(shaderProgram.getId(), "lightPos");
        glUniform3fv(lightPosID, light.getPos().getAsArray());
    }

    private void changeShader(String shader) {
        this.shader = shader;
        init();
    }

    void setTexture(Themes themes) {
        this.currentTexture = themes;
        init();
    }

    @Override
    public void destroy() {
    }
}