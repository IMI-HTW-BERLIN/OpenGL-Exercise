package ab3;

class Vector3 {
    private float[] vector;
    Vector3(float x, float y, float z) {
        vector = new float[]{x,y,z};
    }

    private Vector3 cross(Vector3 second) {
        return new Vector3(this.vector[1]*second.vector[2] - this.vector[2] * second.vector[1],
                this.vector[2]*second.vector[0] - this.vector[0]*second.vector[2],
                this.vector[0]*second.vector[1] - this.vector[1]* second.vector[0]
        );
    }

    static Vector3 getVector(float[] pointA, float[] pointB){
        return new Vector3(
                pointB[0]-pointA[0],
                pointB[1]-pointA[1],
                pointB[2]-pointA[2]
        );
    }

    static Vector3 getVector(float x1, float y1,float z1,float x2,float y2,float z2){
        return new Vector3(x2-x1,y2-y1,z2-z1);
    }

    static Vector3 getNormal(Vector3 first, Vector3 second){
        return first.normalized().cross(second.normalized());
    }

    private Vector3 normalized(){
        float length = (float) Math.sqrt(Math.pow(vector[0],2) + Math.pow(vector[1],2) + Math.pow(vector[2],2));
        return new Vector3(vector[0]/length,vector[1]/length,vector[2]/length);
    }

    float[] getAsArray(){
        return vector;
    }
}
