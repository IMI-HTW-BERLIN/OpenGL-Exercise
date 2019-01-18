package ab3.Primary;

import ab3.Datatypes.Position;
import ab3.Datatypes.Vector3;
import ab3.Objects.LightSource;
import org.lwjgl.glfw.GLFW;

class InputHandler {
    private long window;
    private LightSource lightSource;
    private Vector3 oldPos;

    InputHandler(long window, LightSource lightSource) {
        this.window = window;
        this.lightSource = lightSource;
        this.oldPos = lightSource.getPos();

        GLFW.glfwWaitEvents();

    }

    void moveLight() {
        float moveSpeed = 0.005f;
        Vector3 pos = lightSource.getPos();
        float x = pos.x();
        float y = pos.y();
        int w = GLFW.glfwGetKey(window, GLFW.GLFW_KEY_W);
        int a = GLFW.glfwGetKey(window, GLFW.GLFW_KEY_A);
        int s = GLFW.glfwGetKey(window, GLFW.GLFW_KEY_S);
        int d = GLFW.glfwGetKey(window, GLFW.GLFW_KEY_D);
        int r = GLFW.glfwGetKey(window, GLFW.GLFW_KEY_R);

        if (w != GLFW.GLFW_RELEASE) y += moveSpeed;
        if (a == GLFW.GLFW_PRESS) x -= moveSpeed;
        if (s == GLFW.GLFW_PRESS) y -= moveSpeed;
        if (d == GLFW.GLFW_PRESS) x += moveSpeed;
        if (r == GLFW.GLFW_PRESS) {
            x = oldPos.x();
            y = oldPos.y();
        }


        x = (x < -15) ? -15 : (x > 15) ? 15 : x;
        y = (y < -15) ? -15 : (y > 15) ? 15 : y;

        lightSource.setPos(x, y, pos.z());
    }

}
