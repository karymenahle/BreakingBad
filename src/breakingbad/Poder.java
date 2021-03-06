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
private boolean drop;
private boolean color;

    public Poder(int x, int y, int width, int height, Game game) {
        super(x, y);        
        this.width = width;
        this.height = height;
        this.game = game;
        this.speed = 2;
        this.drop = true;
        this.color = true;
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
        this.speed = speed;
    }
    public void isDropping(){
        this.drop = !this.drop;
    }
    
    public void changeColor(){
        this.color = !this.color;
    }
  
    @Override 
      public void tick(){
        //Solo se mueve hacia abajo
       if(drop){
          setY(getY() + getSpeed());  
       }
        
      }
    
      
       public Rectangle getPerimetro() {
         return new Rectangle(getX(), getY(), getWidth(), getHeight());
        }
           
       public boolean intersect(Player obj){
            return obj instanceof Player  && getPerimetro().intersects(((Player) obj).getPerimetro());
            }

       
    //To paint the item
     @Override 
    public void render(Graphics g){
        if(color){
           g.drawImage(Assets.grow, getX(), getY(), getWidth(), getHeight(), null); 
        }else{
           g.drawImage(Assets.shrink, getX(), getY(), getWidth(), getHeight(), null); 
        }
        
    
    }
}

