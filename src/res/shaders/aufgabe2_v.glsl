#version 330
#define PI 3.1415926535897932384626433832795

layout(location = 0) in vec2 eckenAusJava;
out vec3 color;
out vec2 coord;
uniform float winkel;

vec2 rotate(vec2 vector, float angle){
    float sin = sin(PI/180*angle);
    float cos = cos(PI/180*angle);
    mat2 rotMat = mat2 (cos, sin, -sin, cos);
    return rotMat*vector;
//    return vector;
}

void rainbow(){
    float tempR = abs(gl_Position.x);
    float tempG = abs(gl_Position.y);
    float tempB = abs(gl_Position.x + gl_Position.y)/2;
    color = vec3(tempR,tempG,tempB);
}

void staticColors() {
    if(eckenAusJava.x < 0 && eckenAusJava.y < 0){
        color = vec3(1.0,0.0,0);
    }else if(eckenAusJava.x > 0 && eckenAusJava.y < 0) {
        color = vec3(0.0,1.0,1.0);
    }else color = vec3(1.0,0.0,1.0);
}

void main() {
    gl_Position = vec4(rotate(eckenAusJava,winkel),0.0,1.0);
    rainbow();
    coord = eckenAusJava;
}
