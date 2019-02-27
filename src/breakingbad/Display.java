/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingbad;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;


public class Display {
    private JFrame jframe;  // this is the app class
    private Canvas canvas;  // to display images
    
    private String title;   // title of the window
    private int width;      // width of the window
    private int height;     // height of the window
    
/**
 *
 * @author karymenahleacosta
 */
    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;        
        createDisplay();
    }
    
  
    public void createDisplay() {
        // create the app window
        jframe = new JFrame(title);
        
        // set the size of the window
        jframe.setSize(width, height);
        
        // setting not resizable, visible and possible to close
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(false);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
        
        // creating the canvas to paint and setting size
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setFocusable(false);
        
        // adding the canvas to the app window and packing to
        // get the right dimensions
        jframe.add(canvas);
        jframe.pack();
    }

   
    public JFrame getJframe() {
        return jframe;
    }
    
  
    public Canvas getCanvas() {
        return canvas;
    }
}





