#version 400

in vec4 pixelPosition;
in vec3 normalVector;
in vec2 uv;

uniform int theme;

uniform float lightIntensity;
uniform vec3 lightPos;

uniform float ambientStrength;
uniform float diffuseStrength;
uniform float specularStrength;

uniform float ambientLightIntensity;
uniform float focus;

uniform sampler2D smplr;


out vec3 pixelColor;



vec3 generateChessBoard(){
    vec3 color;
    float size = 0.01;
    if(mod(round(uv.x/size),2) != 0 && mod(round(uv.y/size),2) != 0) color = vec3(1.0,1.0,1.0);
    else color = vec3(0,0,0);
    return color;
}

vec3 generateTexture(){
    vec3 color;
    float size = 0.005;
    if(mod(round(uv.x/size),round(uv.y/size)) < 0.2 || mod(round(uv.y/size),round(uv.x/size)) < 0.2 ||
    mod(round(1-uv.x/size),round(uv.y/size)) > 0.2 && mod(round(1-uv.y/size),round(uv.x/size)) > 0.2) color = vec3(0.2,0.2,0.2);
    else color = vec3(1.0,1.0,1.0);
    return color;
}


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

    switch(theme){
     case 20:
        pixelColor = generateChessBoard() * light;
     break;
     case 21:
        pixelColor = generateTexture() * light;
     break;
     case 22:
        pixelColor = generateChessBoard() * light;
     break;
    }


}
