
/**
 *
 * @author ahmed_ishraq
 */
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import java.lang.Math;
import javax.swing.JFrame;
import java.util.*;

class ThirdGLEventListener implements GLEventListener {

    /**
     * Interface to the GLU library.
     */
    private GLU glu;

    /**
     * Take care of initialization here.
     */
    public void init(GLAutoDrawable gld) {
        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glViewport(-250, -150, 250, 150);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-250.0, 250.0, -150.0, 150.0);
    }

    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width,
            int height) {
    }

    public void displayChanged(GLAutoDrawable drawable,
            boolean modeChanged, boolean deviceChanged) {
    }

    public void dispose(GLAutoDrawable arg0) {

    }
    
    public void DDA(GL2 gl, float x1, float x2, float y1, float y2){
        gl.glColor3d(255,0,0);
        gl.glPointSize(3.0f);
        
        float dx = x2 - x1;
        float dy = y2 - y1;
        int check = 0;
        
        if(dx < 0){
            dx = dx * -1;
        }
        if(dy < 0 ){
            dy = dy * -1;
        }
        if(dx > dy){
            check = Math.round(dx);
        }
        else{
            check = Math.round(dy);
        }
        
        float incX = dx/check;
        float incY = dy/check;
        
        float x = x1;
        float y = y1;
        
        for(int i=0, j=0;i<check;i++,j++){
            if (j == 7){
                j = 0;
            }
            if(j < 2){
                gl.glBegin(GL2.GL_POINTS);
                gl.glVertex2d(x,y);
                gl.glEnd();
            }
            x = x + incX;
            y = y + incY;
        }
    }
}

public class task_three {

    public static void main(String args[]) {
        //getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        ThirdGLEventListener b = new ThirdGLEventListener();
        glcanvas.addGLEventListener(b);
        glcanvas.setSize(400, 400);
        //creating frame
        final JFrame frame = new JFrame("Basic frame");
        //adding canvas to frame
        frame.add(glcanvas);
        frame.setSize(640, 480);
        frame.setVisible(true);
    }
}
