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
import javax.swing.JFrame;

public class Lab_02_Task_one implements GLEventListener {

    private GLU glu;

    public void init(GLAutoDrawable gld) {
        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // black canvas
        gl.glViewport(-250, -150, 250, 150);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-250.0, 250.0, -150.0, 150.0);
    }

    /**
     * Take care of drawing here.
     **/
    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

        // points should be in the same zone

        // for 6 
        DrawMPL(gl,-40,0,-70,0);
        DrawMPL(gl,-70,0,-70,-60);
        DrawMPL(gl,-70,-60,-40,-60);
        DrawMPL(gl,-40,-60,-40,-35);
        DrawMPL(gl,-40,-35,-70,-35);

        // for 1
        DrawMPL(gl,0,0,0,-60);
        DrawMPL(gl,0,0,-20,-20);
        DrawMPL(gl,-20,-60,20,-60);

    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        // do nothing
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
        // do nothing
    }

    private void DrawMPL(GL2 gl, int x1, int y1, int x2, int y2) {

        gl.glPointSize(5.0f);
        gl.glColor3d(1, 0, 0); // red 

        int zone = findZone(x1, y1, x2, y2);

        System.out.println("Given Coordinates: x1 = " + x1 + " y1 = " + y1 + " x2 = " + x2 + " y2 = " + y2);
        System.out.println("Zone: " + zone);

        int convertedZones[] = convertZone0(zone, x1, y1, x2, y2);

        x1 = convertedZones[0];
        y1 = convertedZones[1];
        x2 = convertedZones[2];
        y2 = convertedZones[3];

        int dxUpdated = convertedZones[2] - convertedZones[0];
        int dyUpdated = convertedZones[3] - convertedZones[1];

        int d = 2 * dyUpdated - dxUpdated;
        int nE = 2 * (dyUpdated - dxUpdated);
        int e = 2 * dyUpdated;

        System.out.println("d init = " + d + ", nE = " + nE + " , e = " + e);
        int x = x1;
        int y = y1;

        while (x <= x2) {
            int[] zone0toOriginal = zone0toOriginal(zone, x, y);

            System.out.println("x' = " + x + " y' = " + y + " D = " + d + " x = " + zone0toOriginal[0] + " y = "
                    + zone0toOriginal[1]);

            gl.glBegin(GL2.GL_POINTS);
            gl.glVertex2d(zone0toOriginal[0], zone0toOriginal[1]);
            gl.glEnd();

            x++;
            if (d > 0) {
                y++;
                d = d + nE;
            }

            else {
                d = d + e;
            }

        }
    }

    int findZone(int x1, int y1, int x2, int y2) {
        int zone = 0;
        int dy = y2 - y1;
        int dx = x2 - x1;

        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx >= 0 && dy >= 0) {
                zone = 0;
            }

            else if (dx <= 0 && dy >= 0) {
                zone = 3;
            }

            else if (dx <= 0 && dy <= 0) {
                zone = 4;
            }

            else if (dx > 0 && dy < 0) {
                zone = 7;
            }

        }

        else {
            if (dx >= 0 && dy >= 0) {
                zone = 1;
            }

            else if (dx <= 0 && dy >= 0) {
                zone = 2;
            }

            else if (dx <= 0 && dy <= 0) {
                zone = 5;
            }

            else if (dx >= 0 && dy <= 0) {
                zone = 6;
            }
        }

        return zone;
    }

    int[] convertZone0(int zone, int x1, int y1, int x2, int y2) {
        int[] convertedZones = new int[4];
        switch (zone) {
            case 0:
                convertedZones[0] = x1;
                convertedZones[1] = y1;
                convertedZones[2] = x2;
                convertedZones[3] = y2;
                break;

            case 1:
                convertedZones[0] = y1;
                convertedZones[1] = x1;
                convertedZones[2] = y2;
                convertedZones[3] = x2;
                break;

            case 2:
                convertedZones[0] = y1;
                convertedZones[1] = -1 * x1;
                convertedZones[2] = y2;
                convertedZones[3] = -1 * x2;
                break;

            case 3:
                convertedZones[0] = -1 * x1;
                convertedZones[1] = y1;
                convertedZones[2] = -1 * x2;
                convertedZones[3] = y2;
                break;
            case 4:
                convertedZones[0] = -1 * x1;
                convertedZones[1] = -1 * y1;
                convertedZones[2] = -1 * x2;
                convertedZones[3] = -1 * y2;
                break;
            case 5:
                convertedZones[0] = -1 * y1;
                convertedZones[1] = -1 * x1;
                convertedZones[2] = -1 * y2;
                convertedZones[3] = -1 * x2;
                break;
            case 6:
                convertedZones[0] = -1 * y1;
                convertedZones[1] = x1;
                convertedZones[2] = -1 * y2;
                convertedZones[3] = x2;
                break;
            case 7:
                convertedZones[0] = x1;
                convertedZones[1] = -1 * y1;
                convertedZones[2] = x2;
                convertedZones[3] = -1 * y2;
                break;
            default:
                break;
        }
        return convertedZones;

    }

    int[] zone0toOriginal(int zone, int x1, int y1) {
        int[] zone0toOriginal = new int[2];

        switch (zone) {
            case 0:
                zone0toOriginal[0] = x1;
                zone0toOriginal[1] = y1;
                break;

            case 1:
                zone0toOriginal[0] = y1;
                zone0toOriginal[1] = x1;
                break;

            case 2:
                zone0toOriginal[0] = -1 * y1;
                zone0toOriginal[1] = x1;
                break;

            case 3:
                zone0toOriginal[0] = -1 * x1;
                zone0toOriginal[1] = y1;
                break;
            case 4:
                zone0toOriginal[0] = -1 * x1;
                zone0toOriginal[1] = -1 * y1;
                break;
            case 5:
                zone0toOriginal[0] = -1 * y1;
                zone0toOriginal[1] = -1 * x1;
                break;
            case 6:
                zone0toOriginal[0] = y1;
                zone0toOriginal[1] = -1 * x1;
                break;
            case 7:
                zone0toOriginal[0] = x1;
                zone0toOriginal[1] = -1 * y1;
                break;
            default:
                break;
        }

        return zone0toOriginal;

    }

    public void dispose(GLAutoDrawable arg0) {
        // do nothing
    }

    public static void main(String[] args) {
        // getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        Lab_02_Task_one l = new Lab_02_Task_one();
        glcanvas.addGLEventListener(l);
        glcanvas.setSize(1000, 1000);
        // creating frame
        final JFrame frame = new JFrame("Student ID: 19301261. Let's draw: 61");
        // adding canvas to frame
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
    }// end of main
}// end of classimport javax.media.opengl.GL2;
