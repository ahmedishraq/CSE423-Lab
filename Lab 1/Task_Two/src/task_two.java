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
    
    // house upper part
    gl.glColor3d(139,0,0);
    gl.glPointSize(10.0f);
    gl.glBegin(GL2.GL_TRIANGLES);
    gl.glVertex2d(100,50);
    gl.glVertex2d(0,130);
    gl.glVertex2d(-100,50);
    gl.glEnd();
    
    // house boundary wall 
    gl.glColor3d(0,255,0);
    gl.glPointSize(10.0f);
    gl.glBegin(GL2.GL_LINES);
    // right wall
    gl.glVertex2d(100,-100);
    gl.glVertex2d(100,50);
    // lower wall 
    gl.glVertex2d(100,-100);
    gl.glVertex2d(-100,-100);
    // left wall
    gl.glVertex2d(-100,-100);
    gl.glVertex2d(-100,50);
    gl.glEnd();
    
    // house door 
    gl.glColor3d(0,0,255);
    gl.glPointSize(10.0f);
    gl.glBegin(GL2.GL_LINES);
    // left door wall 
    gl.glVertex2d(20,-100);
    gl.glVertex2d(20,-30);
    // left door wall 
    gl.glVertex2d(-20,-30);
    gl.glVertex2d(-20,-100);
    // upper door wall 
    gl.glVertex2d(20,-30);
    gl.glVertex2d(-20,-30);
    gl.glEnd();
    
    // house door handle 
    gl.glColor3d(255,0,0);
    gl.glPointSize(10.0f);
    gl.glBegin(GL2.GL_POINTS);
    gl.glVertex2d(10,-70);
    gl.glEnd();
    
    // house window 
    gl.glColor3d(0,255,255);
    gl.glPointSize(10.0f);
    gl.glBegin(GL2.GL_LINES);
    // left window 
    gl.glVertex2d(-40,0);
    gl.glVertex2d(-40,30);
    
    gl.glVertex2d(-80,0);
    gl.glVertex2d(-40,0);
    
    gl.glVertex2d(-80,30);
    gl.glVertex2d(-80,0);
    
    gl.glVertex2d(-40,30);
    gl.glVertex2d(-80,30);
    
    // right window 
    gl.glVertex2d(80,0);
    gl.glVertex2d(80,30);
    
   gl.glVertex2d(40,0);
   gl.glVertex2d(80,0);
    
    gl.glVertex2d(40,30);
    gl.glVertex2d(40,0);
    
    gl.glVertex2d(80,30);
    gl.glVertex2d(40,30);
    
    
    gl.glEnd();
}

public void reshape(GLAutoDrawable drawable, int x, int y, int width,
        int height) {
}

public void displayChanged(GLAutoDrawable drawable,
        boolean modeChanged, boolean deviceChanged) {
}

public void dispose(GLAutoDrawable arg0)
{
 
}
}
public class task_two
{
public static void main(String args[])
{
 //getting the capabilities object of GL2 profile
 final GLProfile profile=GLProfile.get(GLProfile.GL2);
 GLCapabilities capabilities=new GLCapabilities(profile);
 // The canvas
 final GLCanvas glcanvas=new GLCanvas(capabilities);
 ThirdGLEventListener b=new ThirdGLEventListener();
 glcanvas.addGLEventListener(b);
 glcanvas.setSize(400, 400);
 //creating frame
 final JFrame frame=new JFrame("Basic frame");
 //adding canvas to frame
 frame.add(glcanvas);
 frame.setSize(640,480);
 frame.setVisible(true);
}
}
