/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingbad;

import java.awt.Graphics;

/**
 
 * @author karymenahleacosta
 */
public abstract class Item {
    protected int x; // to store x position
    protected int y;  //to store y position
    
    
      //set The initial values to create the item
    
    public Item (int x, int y){
    this.x = x;
    this.y = y;
    
    }

 
    // get x value
   // retunr x;
    public int getX() {
        return x;
    }
   // get y value
   // retunr y;
    public int getY() {
        return y;
    }

    
    public void setX(int x) {
        this.x = x;
    }
   public void setY(int y) {
        this.y = y;
    }
    
    //To update posotion of the item for every tick 
    public abstract void tick();
    
    //To paint the item
   
    public abstract void render(Graphics g);
    
}

