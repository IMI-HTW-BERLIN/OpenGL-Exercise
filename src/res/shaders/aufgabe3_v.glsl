#version 330

layout(location = 0) in vec3 eckenAusJava;
layout(location = 1) in vec3 normalen;

out vec3 color;
uniform mat4 mat;
uniform mat4 projectMat;
out vec4 pixelPosition;
out vec3 normalVector;

uniform int theme;

void rainbow(){
    float tempR = abs(gl_Position.x);
    float tempG = abs(gl_Position.y);
    float tempB = abs(gl_Position.z);
    color = vec3(tempR,tempG,tempB);
}

void staticColorsCube() {
    if(eckenAusJava.x < 0 && eckenAusJava.y < 0 && eckenAusJava.z < -1.5){
        color = (vec3(1.0,1.0,1.0));
    }else if(eckenAusJava.x < 0 && eckenAusJava.y < 0 && eckenAusJava.z > -1.5) {
        color = (vec3(1.0,1.0,0));
    }else if(eckenAusJava.x < 0 && eckenAusJava.y > 0 && eckenAusJava.z < -1.5) {
            color = (vec3(1.0,0,1.0));
    }else if(eckenAusJava.x < 0 && eckenAusJava.y > 0 && eckenAusJava.z > -1.5) {
            color = (vec3(1.0,0,0));

    }else if(eckenAusJava.x > 0 && eckenAusJava.y < 0 && eckenAusJava.z < -1.5) {
            color = (vec3(0,1.0,1.0));

    }else if(eckenAusJava.x > 0 && eckenAusJava.y < 0 && eckenAusJava.z > -1.5) {
            color = (vec3(0.0,1.0,0));

    }else if(eckenAusJava.x > 0 && eckenAusJava.y > 0 && eckenAusJava.z < -1.5) {
            color = (vec3(0.0,0,1.0));
    }else   color = (vec3(0.5,0,0.5));
}

void staticColorsTriangeThing() {
    if(eckenAusJava.z < 0){
        color = (vec3(1.0,0,0));
    }else if(eckenAusJava.x > 0 && eckenAusJava.y > 0) {
        color = (vec3(0,1.0,1.0));
    }else if(eckenAusJava.x < 0 && eckenAusJava.y > 0) {
            color = (vec3(1.0,0,1.0));
    }else   color = (vec3(0.0,1.0,0));
}

void normalVecctorColor() {
    mat3 normalMat = inverse(transpose(mat3(mat)));
    vec3 normalColor = normalMat * normalen;
    color = normalColor;
}

void oneColor() {
    color = vec3(1.0,0,0);
}

void chooseTheme(){
    switch(theme){
        case(0):
        oneColor();
        break;
        case(1):
        normalVecctorColor();
        break;
        case(2):
        staticColorsCube();
        break;
    }
}

void main() {
    pixelPosition = mat * vec4(eckenAusJava,1.0);
    gl_Position = projectMat * pixelPosition;

    mat3 normalMat = inverse(transpose(mat3(mat)));
    normalVector = normalMat * normalen;

    chooseTheme();
}
