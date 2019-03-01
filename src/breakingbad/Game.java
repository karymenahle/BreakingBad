/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingbad;

//Hola

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

/**
 *
 * @author karymenahleacosta
 */
public class Game implements Runnable {

private BufferStrategy bs; // to have several buffers when displaying private Graphics g; // to paint objects
private Display display; // to display in the game
String title; // title of the window
private int width;// width of the window
private int height;// height of the window
private Thread thread;
private boolean running; // to set the game
private int x; //to move image
private int direction; // to set the direction of the player
private Player player; // to use a player

private Ball ball; //To use the ball

private boolean start;//para hacer que el juego comience

private LinkedList<Brick> brick;

private KeyManager keyManager; //to manage the keyboard

    @Override
public void run() {
    init();
    //Frames per second
    int fps = 50;
    //time for each thick in nano segs
    double timeTick = 1000000000 / fps;
    //initializing delta
    double delta = 0;
    // define now to use inside the loop
    long now;
    //initializing last time to the computer time in nanosecs
    long lastTime = System.nanoTime();
    
 //To change body of generated methods, choose Tools | Templates.
   while (running) {
       
       //Setting the time to the computer tine in nanosecs
       now = System.nanoTime();
       //acumulating to delta the difference between times in timetick
       delta +=(now - lastTime) / timeTick;
       //updating the last time
       lastTime = now;
       
       
      if (delta >=1){ 
   tick();
   render(); 
   delta --;
      }
   }
   stop(); 
}


public Game(String title, int width, int height) { 
    this.title = title;
    this.width = width;
    this.height = height;
    running = false; 
    keyManager = new KeyManager();
    this.start=false;//inicializo el juego como false que todavia no inicia
    
    //
    brick = new LinkedList<Brick>();
}

    private int getDirection() {
        return direction;
    }

    private void setDirection(int i) {
       this.direction=i;
    }



public int getWidth(){
return width;
}

public int getHeight(){
return height;
}

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }



private void init() {
display = new Display(title, getWidth(), getHeight());
Assets.init(); 

//Se pone la barra que es el jugador 
player = new Player(350, getHeight()-100, 1, 150, 60, this);


//Se inicializa la pelota a mitad de la barra            
ball = new Ball(400, getHeight()-130, 1, 40, 40, this);
         

display.getJframe().addKeyListener(keyManager);

}

public KeyManager getKeyManager() {
        return keyManager;
    }


private void tick() {
    keyManager.tick();
    //advancing player with colition
   
   if (getKeyManager().space) {
   setStart(true);
   }
   
   player.tick(); 
   ball.tick();
   
   
   if (player.intersecta(ball)){
       ball.setDirection(2);
    }
   
      if (player.intersecta2(ball)){
       ball.setDirection(1);
    }
}
   

private void render() {
// get the buffer strategy from the display
bs = display.getCanvas().getBufferStrategy();
/* if it is null, we define one with 3 buffers to display images of the game, if not null, then we display every image of the game but after clearing the Rectanlge, getting the graphic object from the buffer strategy element.
show the graphic and dispose it to the trash system
*/
if (bs == null) {
display.getCanvas().createBufferStrategy(3); 
}
else
{

//ponemos imagenes
Graphics g = bs.getDrawGraphics();
g.drawImage(Assets.background, 0, 0, width, height, null); 
player.render(g);

ball.render(g);
bs.show();
g.dispose();

}


}

public synchronized void start() { 

    if (!running) {
running = true;
thread = new Thread(this); 
thread.start();
 }
}


public synchronized void stop() { 
if (running) {
running = false; 
try {
thread.join();
} 
catch (InterruptedException ie) {
ie.printStackTrace(); 
   }
 } 
}}

