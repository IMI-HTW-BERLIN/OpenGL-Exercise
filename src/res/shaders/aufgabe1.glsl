#version 330
//globale Ausgabe unseres Programms (out)
//Typ ist vec3 für RGB (jeweils 0.0 bis 1.0)
#define PI 3.1415926535897932384626433832795


out vec3 pixelColor;
vec2 coord = gl_FragCoord.xy;

void drawRectangle(vec2 position, vec2 size) {
    if(coord.x >= position.x && coord.x < position.x + size.x &&
        coord.y >= position.y && coord.y < position.y + size.y){
        pixelColor = vec3(1.0, 0.0, 0.0);
    }
}

void drawCircle(vec2 position, float radius) {
    float distance = distance(position, coord);
    if(distance <= radius){
        pixelColor = vec3(1.0, 0.0, 0.0);
    }
}

vec2 rotate(vec2 vector, float angle){
    float sin = sin(PI/180*angle);
    float cos = cos(PI/180*angle);
    mat2 rotMat = mat2 (cos, -sin, sin, cos);
    return vector*rotMat;
}

void drawRectangleRotated(vec2 position, vec2 size, float angle) {
    coord = position + size/2 + (rotate((coord - position - size/2),angle));

    if(coord.x >= position.x && coord.x < position.x + size.x &&
        coord.y >= position.y && coord.y < position.y + size.y){
        pixelColor = vec3(1.0, 0.0, 0.0);
    }
    coord = position + size/2 + (rotate((coord - position - size/2),-angle));
}

void drawLine(vec2 start, vec2 end, float width){
    vec2 startEnd = (end - start);
    float length = length(end - start);

    float angle = acos(dot(startEnd,vec2(1,0)) / ((length(vec2(1,0))) * length));
    coord = start + (rotate((coord - start),-angle));
        if(coord.x >= start.x && coord.x < start.x + length &&
            coord.y >= start.y && coord.y < start.y + width){
            pixelColor = vec3(1.0, 0.0, 0.0);
        }
    coord = start + (rotate((coord - start),angle));
}

void main() {
    pixelColor = vec3(0.0, 1.0, 1.0);

    drawLine(vec2(100,100),vec2(200,500),3);
//    drawRectangle(vec2(600,500),vec2(10,100));
//    drawRectangleRotated(vec2(200,200),vec2(200,200),45);
//    drawRectangle(vec2(700,500),vec2(100,100));
//    drawRectangle(vec2(100,50),vec2(40,40));
//    drawRectangle(vec2(10,50),vec2(60,40));
}