#version 330

layout(location = 0) in vec3 eckenAusJava;
layout(location = 1) in vec3 normalen;
layout(location = 2) in vec2 uvCoordinates;



uniform mat4 mat;
uniform mat4 projectMat;

out vec4 pixelPosition;
out vec3 normalVector;
out vec2 uv;


void main() {
    pixelPosition = mat * vec4(eckenAusJava,1.0);
    gl_Position = projectMat * pixelPosition;

    mat3 normalMat = inverse(transpose(mat3(mat)));
    normalVector = normalMat * normalen;

    uv = uvCoordinates;
}
