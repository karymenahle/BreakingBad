/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingbad;


import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author antoniomejorado
 */
public class Brick extends Item{

    private int width;
    private int height;
    private Game game;

    private int index;
    public Animation Meth;
    
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

        // reset x position and y position if colision
//        if (getX() + 60 >= game.getWidth()) {
//            setX(game.getWidth() - 60);
//        }
//        else 
        if (getX() <= -30) {
            setX(-30);
        }
//        if (getY() + 80 >= game.getHeight()) {
//            setY(game.getHeight() - 80);
//        }
        else if (getY() <= -20) {
            setY(-20);
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
       Meth.setStaticIndex(index);
       Meth.getBlockFrame();
    }
    public Rectangle getPerimetro() {

        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void render(Graphics g) {
        
        g.drawImage(Meth.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);   
        

    }//Meth.getCurrentFrame()
}

