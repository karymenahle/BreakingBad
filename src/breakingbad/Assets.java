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
    public static BufferedImage playerGrow; // to store the player image
    public static BufferedImage grow;
    public static BufferedImage shrink;
    public static BufferedImage lives;
    public static BufferedImage pause;
    public static BufferedImage gameover;
    public static BufferedImage win;
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
playerGrow = ImageLoader.loadImage("/Images/MegaVan.png"); 
pause = ImageLoader.loadImage("/Images/pause.png"); 
gameover = ImageLoader.loadImage("/Images/gameover.png");
win = ImageLoader.loadImage("/Images/win.png");

grow = ImageLoader.loadImage("/Images/GreenPower.png");
shrink = ImageLoader.loadImage("/Images/RedPower.png"); 

lives = ImageLoader.loadImage("/Images/Heisenberg.png"); 
song = new SoundClip("/sounds/Megalovania.wav", -3f,true);

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

