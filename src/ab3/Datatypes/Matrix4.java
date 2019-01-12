package ab3.Datatypes;

import java.util.Arrays;

//Alle Operationen �ndern das Matrixobjekt selbst und geben das eigene Matrixobjekt zur�ck
//Dadurch kann man Aufrufe verketten, z.B.
//Matrix4 m = new Matrix4().scale(5).translate(0,1,0).rotateX(0.5f);
public class Matrix4 {
    private float[] mat4;

    public Matrix4() {
        mat4 = new float[]{
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1,
        };
    }

    public Matrix4(Matrix4 copy) {
        mat4 = copy.mat4.clone();
    }

    public Matrix4(float near, float far, float borderWidth, float borderHeight) {
        Matrix4 temp = new Matrix4();
        temp.mat4[0] = (2*near)/borderWidth;
        temp.mat4[5] = (2*near)/borderHeight;
        temp.mat4[10] = (-far-near)/(far-near);
        temp.mat4[11] = -1;
        temp.mat4[14] = (-2*near*far)/(far-near);
        temp.mat4[15] = 0;
        this.mat4 = temp.mat4.clone();
    }

    private Matrix4 multiply(Matrix4 other) {
        float[] tempMat4 = new float[16];
        int m;
        for (int i = 0; i < 16; i++) {
            m = (int) (Math.floor(i / 4f));
            for (int j = 0; j < 4; j++) {
                tempMat4[i] += other.mat4[j*4 + (i%4)] * mat4[j + 4*m];
            }
        }
        mat4 = tempMat4.clone();

        return this;
    }

    public Matrix4 translate(float x, float y, float z) {
        Matrix4 temp = new Matrix4();
        temp.mat4[12] = x;
        temp.mat4[13] = y;
        temp.mat4[14] = z;
        this.multiply(temp);
        return this;
    }


    public Matrix4 scale(float uniformFactor) {
        Matrix4 temp = new Matrix4();
        temp.mat4[0] = uniformFactor;
        temp.mat4[5] = uniformFactor;
        temp.mat4[10] = uniformFactor;
        this.multiply(temp);
        return this;
    }

    public Matrix4 scale(float sx, float sy, float sz) {
        Matrix4 temp = new Matrix4();
        temp.mat4[0] = sx;
        temp.mat4[5] = sy;
        temp.mat4[10] = sz;
        this.multiply(temp);
        return this;
    }

    public Matrix4 rotateX(float angle) {
        Matrix4 temp = new Matrix4();
        angle = (float) Math.toRadians(angle);
        temp.mat4[5] = (float) Math.cos(angle);
        temp.mat4[6] = (float) Math.sin(angle);
        temp.mat4[9] = (float) -Math.sin(angle);
        temp.mat4[10] = (float) Math.cos(angle);
        this.multiply(temp);
        return this;
    }

    public Matrix4 rotateY(float angle) {
        Matrix4 temp = new Matrix4();
        angle = (float) Math.toRadians(angle);
        temp.mat4[0] = (float) Math.cos(angle);
        temp.mat4[2] = (float) Math.sin(angle);
        temp.mat4[8] = (float) -Math.sin(angle);
        temp.mat4[10] = (float) Math.cos(angle);
        this.multiply(temp);
        return this;
    }

    public Matrix4 rotateZ(float angle) {
        Matrix4 temp = new Matrix4();
        angle = (float) Math.toRadians(angle);
        temp.mat4[0] = (float) Math.cos(angle);
        temp.mat4[1] = (float) Math.sin(angle);
        temp.mat4[4] = (float) -Math.sin(angle);
        temp.mat4[5] = (float) Math.cos(angle);
        this.multiply(temp);
        return this;
    }

    public float[] getValuesAsArray() {
        return mat4;
    }
}