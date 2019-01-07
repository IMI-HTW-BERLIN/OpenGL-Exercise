package ab3;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class testController {


    public Slider lightIntensity;
    public Slider focus;
    public Slider ambientLightIntensity;
    public Slider ambientStrength;
    public Slider diffuseStrength;
    public Slider specularStrength;
    public Slider xRotation;
    public Slider yRotation;

    public Label lightIntensityLabel;
    public Label focusLabel;
    public Label ambientIntensityLabel;
    public Label ambientStrengthLabel;
    public Label diffuseStrengthLabel;
    public Label specularStrengthLabel;
    public Label xRotationLabel;
    public Label yRotationLabel;

    public ComboBox themes;
    public Pane checkBoxList;
    public Slider rotationSpeed;
    public Label rotationSpeedLabel;

    private Aufgabe3undFolgende aufgabe;


    public void go() {
        aufgabe = new Aufgabe3undFolgende();
        changeObject();
        aufgabe.start("CG Aufgabe 3", 1000, 1000);

    }

    public void update() {
        setAmbientLightIntensity();
        setAmbientStrength();
        setDiffuseStrength();
        setFocus();
        setLightIntensity();
        setSpecularStrength();
        setXRotation();
        setYRotation();
    }

    public void setLightIntensity() {
        aufgabe.light.setIntensity((float) lightIntensity.getValue());
        lightIntensityLabel.setText("Light Intensity: " + String.format("%.1f", lightIntensity.getValue()));
    }

    public void setFocus() {
        aufgabe.focus = (float) focus.getValue();
        focusLabel.setText("Focus: " + String.format("%.1f", focus.getValue()));
    }

    public void setAmbientLightIntensity() {
        aufgabe.ambientLightIntensity = (float) ambientLightIntensity.getValue();
        ambientIntensityLabel.setText("Ambient Intensity: " + String.format("%.2f", ambientLightIntensity.getValue()));
    }

    public void setAmbientStrength() {
        aufgabe.ambientStrength = (float) ambientStrength.getValue();
        ambientStrengthLabel.setText("Ambient Strength: " + String.format("%.2f", ambientStrength.getValue()));
    }

    public void setDiffuseStrength() {
        aufgabe.diffuseStrength = (float) diffuseStrength.getValue();
        diffuseStrengthLabel.setText("Diffuse Strength: " + String.format("%.2f", diffuseStrength.getValue()));
    }

    public void setSpecularStrength() {
        aufgabe.specularStrength = (float) specularStrength.getValue();
        specularStrengthLabel.setText("Specular Strength: " + String.format("%.2f", specularStrength.getValue()));
    }

    public void setXRotation() {
        aufgabe.xRotation = (float) xRotation.getValue();
        xRotationLabel.setText("X Rotation: " + String.format("%.2f", xRotation.getValue()));
    }

    public void setYRotation() {
        aufgabe.yRotation = (float) yRotation.getValue();
        yRotationLabel.setText("Y Rotation: " + String.format("%.2f", yRotation.getValue()));
    }

    public void changeTheme() {
        switch (themes.getValue().toString()) {
            case ("Red"):
                aufgabe.theme = 0;
                break;
            case ("Normals"):
                aufgabe.theme = 1;
                break;
            case ("Different"):
                aufgabe.theme = 2;
                break;
            case ("Different Triangle"):
                aufgabe.theme = 3;
                break;
            case ("Rainbow"):
                aufgabe.theme = 4;
                break;
            case ("Texture1"):
                aufgabe.theme = 10;
                break;
            case ("Texture2"):
                aufgabe.theme = 11;
                break;
            case ("Texture3"):
                aufgabe.theme = 12;
                break;
            default:
                aufgabe.theme = 0;
                break;
        }
    }

    public void changeObject() {
        boolean[] objectSelected = new boolean[checkBoxList.getChildren().size()];
        for (int i = 0; i < checkBoxList.getChildren().size(); i++) {
            objectSelected[i] = ((CheckBox) checkBoxList.getChildren().get(i)).isSelected();
        }
        aufgabe.objectSelected = objectSelected;
    }

    public void setRotationSpeed() {
        aufgabe.rotationSpeed = (float) rotationSpeed.getValue();
        rotationSpeedLabel.setText("Rotation Speed: " + String.format("%.2f", rotationSpeed.getValue()));
    }
}
