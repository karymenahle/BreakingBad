/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingbad;

/**
 *
 * @author karymenahleacosta
 */

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author karymenahleacosta
 */
public class Brick extends Item{

    private int direction;
    private int width;
    private int height;
    private Game game;
    private int speed;
    
    public Brick(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        speed = 1;
    }

    public int getDirection() {
        return direction;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void tick() {

  
        if (game.getKeyManager().up) {
           setY(getY() - speed);
            //speed = speed + 1;
        }
        
        if (game.getKeyManager().down) {
           setY(getY() + speed);
          // speed = speed + 1;
        }
        if (game.getKeyManager().left) {
           setX(getX() - speed);
          // speed = speed + 1;
        }
        if (game.getKeyManager().right) {
           setX(getX() + speed);
           //speed = speed + 1;
        }
        


        
// reset x position and y position if colision
        if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - 60);
        }
        else if (getX() <= -30) {
            setX(-30);
        }
        if (getY() + 80 >= game.getHeight()) {
            setY(game.getHeight() - 80);
        }
        else if (getY() <= -20) {
            setY(-20);
      }
    }
    
    
        public Rectangle getPerimetro() {
         return new Rectangle(getX(), getY(), getWidth(), getHeight());
        }
        
    //    public boolean intersecta(ball obj){
           
     //       return obj instanceof ball  && getPerimetro().intersects(((ball) obj).getPerimetro());
            
       //     }
        
        
    
    
    
    
    
    @Override
    public void render(Graphics g) {
        
        g.drawImage(Assets.brick, getX(), getY(), getWidth(), getHeight(), null);
    }
}


