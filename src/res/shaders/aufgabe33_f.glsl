#version 400

out vec3 pixelColor;
in vec3 color;
in vec4 pixelPosition;
in vec3 normalVector;

uniform float lightIntensity;
uniform vec3 lightPos;

uniform float ambientStrength;
uniform float diffuseStrength;
uniform float specularStrength;

uniform float ambientLightIntensity;
uniform float focus;



void main() {
    float maxLight = ambientStrength + diffuseStrength + specularStrength;
    float ambient = ambientStrength / maxLight;
    float diffuse = diffuseStrength / maxLight;
    float specular = specularStrength / maxLight;


    vec3 unitNormalVector = normalize(normalVector);
    vec3 unitToLightVector = normalize(lightPos - pixelPosition.xyz);
    vec3 unitToCameraVector = normalize(-pixelPosition.xyz);
    vec3 reflectionVector = reflect(-unitToLightVector, unitNormalVector);

    float lightAmbient = ambient * ambientLightIntensity;
    float lightDiffuse = max(0,dot(unitToLightVector,unitNormalVector)) * diffuse;
    float lightSpecular = lightIntensity * (pow(max(0,dot(reflectionVector, unitToCameraVector)), focus) * specular);

    float light = lightAmbient + lightDiffuse + lightSpecular;

    pixelColor = color * light;
}
