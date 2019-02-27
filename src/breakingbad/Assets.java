/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingbad;

import java.awt.image.BufferedImage;

/**
 *
 * @author karymenahleacosta
 */
public class Assets {
    
    public static BufferedImage background; // to store background image 
    public static BufferedImage player; // to store the player image
    public static BufferedImage brick;
    public static BufferedImage ball;


/**
* initializing the images of the game */
public static void init() {
background = ImageLoader.loadImage("/images/breaking_bad_logo.png");
player = ImageLoader.loadImage("/images/van.png"); 
brick = ImageLoader.loadImage("/images/brick.png"); 
ball = ImageLoader.loadImage("/images/ball.png"); 

}
    
}

