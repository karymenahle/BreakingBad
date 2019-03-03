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
    public static SoundClip song;
    
    public static BufferedImage BallImages[]; 
    public static BufferedImage ballSprites; // to store the sprites 
    
    public static BufferedImage BrickImages[];     // pictures to go down
    public static BufferedImage brickSprites; // to store the sprites 
/**
* initializing the images of the game */
public static void init() {
background = ImageLoader.loadImage("/Images/desert.jpeg");
player = ImageLoader.loadImage("/Images/van.png"); 
brick = ImageLoader.loadImage("/Images/brick.png"); 
ball = ImageLoader.loadImage("/Images/ball.png"); 
song = new SoundClip("/sounds/Undertale.mp3");

brickSprites = ImageLoader.loadImage("/Images/Meth.png");
SpriteSheet spritesheet = new SpriteSheet(brickSprites);

ballSprites = ImageLoader.loadImage("/Images/SpriteBall.png");
SpriteSheet ballspritesheet = new SpriteSheet(ballSprites);

BrickImages = new BufferedImage[5];       
BrickImages[0] = spritesheet.crop(0,0,256,150);
BrickImages[1] = spritesheet.crop(256,0,256,150);
BrickImages[3] = spritesheet.crop(0,155,256,150);
BrickImages[2] = spritesheet.crop(256,155,256,150);

BallImages = new BufferedImage[8];
for (int i = 0; i < 8; i++){
    BallImages[i] = ballspritesheet.crop(0+40*i,0,32,32);
}
}

}

