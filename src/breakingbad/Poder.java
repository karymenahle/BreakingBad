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
 * @author Electel
 */
public class Poder extends Item {
    /**
 *
 * @author karymenahleacosta
 */

private int width;
private int height;
private Game game;
private int speed;

    public Poder(int x, int y, int width, int height, Game game) {
        super(x, y);        
        this.width = width;
        this.height = height;
        this.game = game;
        this.speed = 5;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    
    public int getSpeed() {
        return speed;
    }
    

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setSpeed(int speed) {
        this.speed = 1;
    }
    
  
    @Override 
      public void tick(){
        //Solo se mueve hacia abajo
       setX(getX()-2);  
      }
    
      
       public Rectangle getPerimetro() {
         return new Rectangle(getX(), getY(), 60, getHeight());
        }
           
       public boolean intersecta(Ball obj){
            return obj instanceof Ball  && getPerimetro().intersects(((Ball) obj).getPerimetro());
            }

       
    //To paint the item
     @Override 
    public void render(Graphics g){
        g.drawImage(Assets.grow, getX(), getY(), getWidth(), getHeight(), null);
    
    }
}

