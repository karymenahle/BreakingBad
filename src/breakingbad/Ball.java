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
public class Ball extends Item{

    private int width;
    private int height;
    private Game game;
    private int speed;
    private int direction;
    private Animation skull;
    private boolean bVisible;
    
    public Ball(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.direction = 1;
        this.speed = 4;
        this.skull = new Animation(Assets.BallImages, 100);
        this.bVisible = true;
    }
    public boolean isVisible(){
        return bVisible;
    }
    public void changeVisibility(boolean b){
        this.bVisible = b;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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
        this.skull.tick();
        
        if(game.isStart()){
            if (game.isPausa()==false){
        
        //Este if sirve para asignar que la direccion 1 va en diagonal hacia arriba y la derecha
       if (getDirection() == 1){
       setX(getX() + getSpeed());
       setY(getY() - getSpeed());
       }
       
       //Este if sirve para asignar que la direccion 2 va en diagonal hacia arriba y la izquierda
       if (getDirection() == 2){
       setX(getX() - getSpeed());
       setY(getY() - getSpeed());
       }
       
       //Este if sirve para asignar que la direccion 3 va hacia abajo y la derecha
       if (getDirection() == 3){
       setX(getX() + getSpeed());
       setY(getY() + getSpeed());
       }
       
       //Este if sirve para asignar que la direccion 4 va en diagonal hacia abajo y a la izquierda
       if (getDirection() == 4){
       setX(getX() - getSpeed());
       setY(getY() + getSpeed());
       }
     

       //sirve para saber si choca con una pared y tiene direccion 1 se cambie a 2
       if(getX()+50 >= game.getWidth() && getDirection() == 1){
       setDirection(2);
       }
       
       //sirve para saber si choca con una pared y tiene direccion 2 se cambie a 1
       else if(getX() <= 0  && getDirection() == 2){
       setDirection(1);
       }
       
       //sirve para saber si choca con una pared y tiene direccion 3 se cambie a 4
       else if(getX()+50 >= game.getWidth() && getDirection() == 3){
       setDirection(4);
       }
       
       //sirve para saber si choca con una pared y tiene direccion 4 se cambie a 3
       else if(getX() <= 0 && getDirection() == 4){
       setDirection(3);
       }
       
       //sirve para saber si choca con el techo y tiene direccion 1 se cambie a 3
       if(getY() <= 0 && getDirection() == 1){
       setDirection(3);
       }
       //sirve para saber si choca con el techi y tiene direccion 2 se cambie a 4
       else if (getY() <= 0 && getDirection() == 2){
       setDirection(4);
       }
       
            }
    }
    }
    
    public void oppositeDirection(){
              //sirve para saber si choca con una pared y tiene direccion 4 se cambie a 3
       if(getDirection() == 4){
       setDirection(3);
       }
       
       //sirve para saber si choca con el techo y tiene direccion 1 se cambie a 3
       if(getDirection() == 1){
       setDirection(3);
       }
       //sirve para saber si choca con el techi y tiene direccion 2 se cambie a 4
       if (getDirection() == 2){
            setDirection(4);
       }       
       if (getDirection() == 3){
       setDirection(4);
       } 
       
    }
    public boolean intersecta(Brick obj){
     return obj instanceof Brick  && getPerimetro().intersects(((Brick) obj).getPerimetro());
     }
           
    public Rectangle getPerimetro() {
     return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
            
    @Override
    public void render(Graphics g) {
        if(isVisible()){
          g.drawImage(skull.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);  
        }
        
    }
}
