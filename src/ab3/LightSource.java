package ab3;

public class LightSource {
    private Vector3 pos;

    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }

    private float intensity;

    public LightSource(Vector3 pos, float intensity) {
        this.pos = pos;
        this.intensity = intensity;
    }

    Vector3 getPos(){
        return  pos;
    }

    float getIntensity(){
        return  intensity;
    }


}
