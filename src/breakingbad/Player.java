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
 * @author karymenahleacosta
 */
public class Player extends Item{
private int direction;
private int width;
private int height;
private Game game;
private int speed;
private int lives;
private boolean bGrow;

    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        this.speed = 5;
        this.lives = 4;
        this.bGrow = false;
    }

    public int getDirection() {
        return direction;
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
    
    public int getLives(){
        return lives;
    }
    
    public void setDirection(int direction) {
        this.direction = direction;
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
    
    public void setLives(int life){
        this.lives = life;
    }
    public boolean isbGrow(){
       return bGrow;
    }
    public void changeImg(){
        bGrow = true;
    }
    
    @Override 
      public void tick(){

          //Si se presiona space empieza el juego
          if (game.isStart()){
              if (game.isPausa() == false){
        //Solo se mueve a la derecha o a la izquierda 
        
          if(game.getKeyManager().left){
          setX(getX() - getSpeed());
          }
          
          if(game.getKeyManager().right){
          setX(getX() + getSpeed());
          }
          
         
          //reset x if colision
       if (getX() + getWidth() >= game.getWidth()){
       setX(game.getWidth() - getWidth());
       }   
      //colision with left wall
      else if (getX() <= 0){
       setX(0); 
        }
       
       //change size if collision with powerup
       if(isbGrow()){
           setWidth(200);
       }
          }
          }
      }
    
      
       public Rectangle getPerimetro() {
         return new Rectangle(getX(), getY(), 75, getHeight());
        }
       
       public Rectangle getPerimetro2() {
         return new Rectangle(getX()+76, getY(), 75, getHeight());
        }
       
       public boolean intersecta(Ball obj){
            return obj instanceof Ball  && getPerimetro().intersects(((Ball) obj).getPerimetro());
            }
       public boolean intersecta2(Ball obj){
        return obj instanceof Ball  && getPerimetro2().intersects(((Ball) obj).getPerimetro());
            }

       
    //To paint the item
     @Override 
    public void render(Graphics g){
        if(bGrow){
            g.drawImage(Assets.playerGrow, getX(), getY(), getWidth(), getHeight(), null);
        }else{
            g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);
         }
        
        
        //draws player lives
        for(int i = 0; i < getLives();i++){
            g.drawImage(Assets.lives, 15+35*i, game.getHeight()-40, 30, 30, null);
        }
    }

}
