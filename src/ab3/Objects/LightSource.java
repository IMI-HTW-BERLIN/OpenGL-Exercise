package ab3.Objects;
import ab3.Datatypes.*;

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

    public Vector3 getPos(){
        return  pos;
    }

    public float getIntensity(){
        return  intensity;
    }

    public void setPos(Vector3 pos){
        this.pos = new Vector3(pos.x(),pos.y(),this.pos.z());
    }

    public void setPos(float x, float y, float z){
        this.pos = new Vector3(x,y,z);
    }


}
