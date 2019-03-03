/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingbad;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.image.BufferedImage;

/**
 *
 * @author Electel
 */
public class Animation {
    private int speed;
    private int index;
    private int staticIndex;
    private long lastTime;
    private long timer;
    private boolean nxtblock;
    private BufferedImage[] frames;
    
    public Animation(BufferedImage[] frames, int speed){
        this.frames = frames;
        this.speed = speed;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
        staticIndex = 0;
        this.nxtblock = false;
    }
    
    public void nxtBlck(){
        this.staticIndex++;
    }
    
    public void setStaticIndex(int sI){
        this.staticIndex = sI;
    }
    
    public int getStaticIndex(){
        return staticIndex;
    }
    
    public void setNext(){
        setStaticIndex(getStaticIndex()+1);
    }
    
    public BufferedImage getBlockFrame(){
        return frames[staticIndex];
    }
    
    public BufferedImage getCurrentFrame(){
        return frames[index];
    }
    public void isNxtBlock(boolean block){
        this.nxtblock = block;
    }
    public void tick(){
    timer += System.currentTimeMillis() - lastTime;
    lastTime = System.currentTimeMillis();
    if( timer > speed){
        index++;
        timer = 0;
        if(index >= frames.length){
            index = 0;
        }
    }
    if (nxtblock){
        staticIndex++;
        isNxtBlock(!nxtblock);
    }
    
//    if(staticIndex>=4){
//        frames = null;
//    }
    }
        
}
