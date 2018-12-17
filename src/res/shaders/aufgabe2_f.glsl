#version 330
#define PI 3.1415926535897932384626433832795

out vec3 pixelColor;
in vec3 color;
in vec2 coord;

vec3 transparent(){
    return vec3((color.z+1)/2, (color.y+1)/2, (color.x+1)/2);
}

vec3 reverse(){
    return vec3(1-color.x,1-color.y,1-color.z);
}

void drawCircle(vec2 position, float radius) {
    float distance = distance(position, coord);
    if(distance <= radius){
        pixelColor = reverse();
    }
}

vec2 rotate(vec2 vector, float angle){
    float sin = sin(PI/180*angle);
    float cos = cos(PI/180*angle);
    mat2 rotMat = mat2 (cos, sin, -sin, cos);
    return rotMat*vector;
    //return vector;
}

void drawRectangleRotated(vec2 position, vec2 size, float angle) {
    position = vec2(position.x - size.x/2, position.y - size.y/2);
    vec2 coordTemp = position + size/2 + (rotate((coord - position - size/2),angle));
    if(coordTemp.x >= position.x && coordTemp.x < position.x + size.x &&
        coordTemp.y >= position.y && coordTemp.y < position.y + size.y){
        pixelColor = reverse();
    }
}

void main() {
    pixelColor = color;
    drawCircle(vec2(-0.4,0.4),0.2);
    drawCircle(vec2(-0.4,-0.4),0.2);
    drawCircle(vec2(0.4,0.4),0.2);
    drawCircle(vec2(0.4,-0.4),0.2);
    drawRectangleRotated(vec2(0.2,0.2),vec2(0.1,0.1),0);
    drawRectangleRotated(vec2(0.1,0.1),vec2(0.1,0.1),0);
    drawRectangleRotated(vec2(0,0),vec2(0.1,0.1),0);
    drawRectangleRotated(vec2(-0.1,-0.1),vec2(0.1,0.1),0);
    drawRectangleRotated(vec2(-0.2,-0.2),vec2(0.1,0.1),0);
    drawRectangleRotated(vec2(-0.1,0.1),vec2(0.1,0.1),0);
    drawRectangleRotated(vec2(-0.2,0.2),vec2(0.1,0.1),0);
    drawRectangleRotated(vec2(0.1,-0.1),vec2(0.1,0.1),0);
    drawRectangleRotated(vec2(0.2,-0.2),vec2(0.1,0.1),0);
}
