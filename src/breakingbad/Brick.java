/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingbad;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Stack;

/**
 *
 * @author antoniomejorado
 */
public class Brick extends Item{

    private int width;
    private int height;
    private Game game;

    private int index;
    private Animation Meth;
    
    public Brick(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.index = 0;
        
        this.Meth = new Animation(Assets.BrickImages, 100);
    }

    /**
     * 
     * @return 
     */

    public Animation getMeth(){
        return Meth;
    }



    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }



    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void tick() {
        this.Meth.tick();

        if (getX() <= -30) {
            setX(-30);
        }


    }
    
    public void setIndex(int index){
        this.index = index;
    }
    
    public int getIndex(){
        return index;
    }
    public int upgradeIndex(){ 
        setIndex(getIndex()+1);
        return getIndex();
    }

public void nextBrick(){
       upgradeIndex();
       if(getIndex()>=4){
           setY(-1000);
       }
       
       Meth.setStaticIndex(index);
       Meth.getBlockFrame();

    }


    public Rectangle getPerimetro() {

        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
    public boolean intersecta(Ball obj){
        return obj instanceof Ball  && getPerimetro().intersects(((Ball) obj).getPerimetro());
     }
    
    
    @Override
    public void render(Graphics g) {
        
        g.drawImage(Meth.getBlockFrame(), getX(), getY(), getWidth(), getHeight(), null);   
        

    }//Meth.getCurrentFrame()
}

