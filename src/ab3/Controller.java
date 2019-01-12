package ab3;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Controller {


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

    public ComboBox<Themes> themes;
    public Slider rotationSpeed;
    public Label rotationSpeedLabel;
    public GridPane objectList;

    private Aufgabe3undFolgende aufgabe;

    @FXML
    public void initialize() {
        themes.getItems().addAll(Themes.values());
        themes.setValue(Themes.RED);
    }

    public void start(){
        aufgabe = new Aufgabe3undFolgende();
        setValues();
        selectedObjects();
        aufgabe.start("CG Aufgabe 3", 1000, 1000);
    }

    private void setValues(){
        setAmbientLightIntensity();
        setAmbientStrength();
        setDiffuseStrength();
        setFocus();
        setLightIntensity();
        setSpecularStrength();
        setXRotation();
        setYRotation();
        setRotationSpeed();
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

    public void setRotationSpeed() {
        aufgabe.rotationSpeed = (float) rotationSpeed.getValue();
        rotationSpeedLabel.setText("Rotation Speed: " + String.format("%.2f", rotationSpeed.getValue()));
    }

    //----------------------------------------------------------------------------------------------------
    public void changeTheme() {
        switch (themes.getValue()) {
            case RED:
                aufgabe.theme = 0;
                break;
            case NOR:
                aufgabe.theme = 1;
                break;
            case DIF:
                aufgabe.theme = 2;
                break;
            case DIF3:
                aufgabe.theme = 3;
                break;
            case RAI:
                aufgabe.theme = 4;
                break;
            case TEX1:
                break;
            case TEX2:
                break;
            case TEX3:
                break;
        }
    }

    public void selectedObjects() {
        boolean[] objectSelected = new boolean[objectList.getChildren().size()];
        for (int i = 0; i < objectList.getChildren().size(); i++) {
            objectSelected[i] = ((CheckBox) objectList.getChildren().get(i)).isSelected();
        }
        aufgabe.objectSelected = objectSelected;
    }

}
