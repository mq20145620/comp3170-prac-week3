package comp3170.week3;

import static com.jogamp.opengl.GL.GL_COLOR_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_FLOAT;
import static com.jogamp.opengl.GL.GL_LINE_LOOP;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLContext;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;

import comp3170.GLException;
import comp3170.Shader;

public class Week3 extends JFrame implements GLEventListener {

	// I find it easier to compute angles in radians using TAU instead of PI 
	// TAU represents 1 full rotation (i.e. 360 degrees)
	// https://www.youtube.com/watch?v=jG7vhMMXagQ

	private final float TAU = (float)Math.PI * 2;
	
	private GLCanvas canvas;
	private Shader shader;
	
	final private File DIRECTORY = new File("src/comp3170/week3"); 
	final private String VERTEX_SHADER = "vertex.glsl";
	final private String FRAGMENT_SHADER = "fragment.glsl";

	private float[] vertices;
	private int vertexBuffer;

	private float[] colours;
	private int colourBuffer;

	private InputManager input;

	public Week3() {
		super("Week 3 example");
		
		// create an OpenGL canvas and add this as a listener
		
		this.canvas = new GLCanvas();
		this.canvas.addGLEventListener(this);
		this.add(canvas);

		// create an input manager to listen for keypresses
		this.input = new InputManager();
		this.canvas.addKeyListener(this.input);

		// set up the JFrame
		
		this.setSize(800,800);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});	
		
	}

	@Override
	/**
	 * Initialise the GLCanvas
	 */
	public void init(GLAutoDrawable drawable) {
		GL4 gl = (GL4) GLContext.getCurrentGL();
		
		// set the background colour to black
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

		// Compile the shader
		try {
			File vertexShader = new File(DIRECTORY, VERTEX_SHADER);
			File fragementShader = new File(DIRECTORY, FRAGMENT_SHADER);
			this.shader = new Shader(vertexShader, fragementShader);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (GLException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		// create the shape
		
		// vertices of a square as (x,y) pairs
		this.vertices = new float[] {
				 0.5f,	0.5f,
				-0.5f,	0.5f,
				-0.5f, -0.5f,
				 0.5f, -0.5f
		};
		
		// copy the data into a Vertex Buffer Object in graphics memory		
	    this.vertexBuffer = this.shader.createBuffer(this.vertices);

		// vertices of a square as (x,y) pairs
		this.colours = new float[] {
				 1.0f,	0.0f, 0.0f,
				 1.0f,	1.0f, 0.0f,
				 0.0f,	1.0f, 0.0f,
				 0.0f,	0.0f, 1.0f,
		};
		
		// copy the data into a Vertex Buffer Object in graphics memory		
	    this.colourBuffer = this.shader.createBuffer(this.colours);

	    
	}

	@Override
	/**
	 * Called when we dispose of the canvas 
	 */
	public void dispose(GLAutoDrawable drawable) {
		// nothing to do
	}

		
	@Override
	/**
	 * Called when the canvas is redrawn
	 */
	public void display(GLAutoDrawable drawable) {
		GL4 gl = (GL4) GLContext.getCurrentGL();

		gl.glClear(GL_COLOR_BUFFER_BIT);		

		this.shader.enable();
		
	    this.shader.setAttribute("a_position", vertexBuffer, 2, GL_FLOAT);
	    this.shader.setAttribute("a_colour", colourBuffer, 3, GL_FLOAT);
        this.shader.setUniform("u_angle", TAU/6);	

        // draw the shape as a series of lines in a loop
        gl.glDrawArrays(GL_LINE_LOOP, 0, vertices.length / 2);           	
        
	}

	@Override
	/**
	 * Called when the canvas is resized
	 */
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		// nothing to do
	}


	public static void main(String[] args) throws IOException, GLException {
		new Week3();
	}


}
