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
 * @author gerardogarzafox
 */
public class Brick extends Item{

    private int width;
    private int height;
    private Game game;

    private int index;
    private Animation Meth;
    private int prevY;
    public Brick(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.index = 0;
        this.Meth = new Animation(Assets.BrickImages, 100);
        this.prevY = y;
    }

    /**
     * 
     * @return 
     */

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
    }
    
    public void setIndex(int index){
        this.index = index;
    }
    public int getPreY(){
        return prevY;
    }
    public int getIndex(){
        return index;
    }  
    /**
     * esta funcion va a llamar a la clase animation para avanzar al 
     * siguiente tipo de bloque 
     */
    public void nextBrick(){
       setIndex(getIndex()+1);
       if(getIndex()>= 4){
           
           setY(-1000);
       }
       //actualiza nuestra animacion de bloque
       Meth.setStaticIndex(index);

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
    }
}

