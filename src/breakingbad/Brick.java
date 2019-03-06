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
    private boolean alive;
    private int index;
    private Animation Meth;
    private int prevY;

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param game
     */
    public Brick(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.index = 0;
        this.Meth = new Animation(Assets.BrickImages, 100);
        this.prevY = y;
        this.alive = true;
    }

    /**
     * 
     * @return 
     */
    
    public boolean isAlive(){
        return alive;
    }

    /**
     *
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     *
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     *
     */
    @Override
    public void tick() {
        this.Meth.tick();
        Meth.setStaticIndex(getIndex());
    }
    
    /**
     *
     * @return
     */
    public int getPreY(){
        return prevY;
    }

    /**
     *
     * @return
     */
    public int getIndex(){
        return index;
    }  

    /**
     *
     * @param index
     */
    public void setIndex(int index){
        this.index = index;
    }
    /**
     * esta funcion va a llamar a la clase animation para avanzar al 
     * siguiente tipo de bloque 
     */
    public void nextBrick(){
       setIndex(getIndex()+1);
       if(getIndex()>= 4){
           setY(-1000);
           alive = false;
       }
       //actualiza nuestra animacion de bloque
       Meth.setStaticIndex(getIndex());
    }

    /**
     *
     * @return
     */
    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
    /**
     *
     * @param obj
     * @return
     */
    public boolean intersecta(Ball obj){
        return obj instanceof Ball  && getPerimetro().intersects(((Ball) obj).getPerimetro());
     }
       
    /**
     *
     * @param g
     */
    @Override
    public void render(Graphics g) { 
        g.drawImage(Meth.getBlockFrame(), getX(), getY(), getWidth(), getHeight(), null);   
    }
}

