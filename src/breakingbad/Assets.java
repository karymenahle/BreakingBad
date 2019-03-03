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
    
    public static BufferedImage BrickImages[];     // pictures to go down
    public static BufferedImage sprites; // to store the sprites 
/**
* initializing the images of the game */
public static void init() {
background = ImageLoader.loadImage("/images/breaking_bad_logo.png");
player = ImageLoader.loadImage("/images/van.png"); 
brick = ImageLoader.loadImage("/images/brick.png"); 
ball = ImageLoader.loadImage("/images/ball.png"); 

sprites = ImageLoader.loadImage("/images/Meth.png");
SpriteSheet spritesheet = new SpriteSheet(sprites);
BrickImages = new BufferedImage[4];

        for (int i = 0; i<4; i++){
            BrickImages[i] = spritesheet.crop(i*64,20,64,64);
        }
}
    
}

