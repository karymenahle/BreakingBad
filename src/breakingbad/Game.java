/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingbad;

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

private LinkedList<Poder> poder;//to use powers
private LinkedList<Brick> bricks;

private KeyManager keyManager; //to manage the keyboard
private boolean PowerUp;
private int score; // puntaje
private String num; //despliega el puntaje
private boolean pausa;//para poner el juego en pausa
private int state; //para saber si el juego esta en 1=corriendo 2=game over 3= pausa
private boolean empty;

    public Game(String title, int width, int height) { 
        this.title = title;
        this.width = width;
        this.height = height;
        running = false; 
        keyManager = new KeyManager();
        bricks = new LinkedList<Brick>(); 
        poder = new LinkedList<Poder>();
        this.PowerUp = false;
        this.start = false;//inicializo el juego como false que todavia no inicia
        score = 0;
        num = "Score:"+score;
        this.pausa = false;// se inicializa la variable en falso por que no esta en pausa
        this.state = 0; //Se inicializa en 0 que significa que el juego todavia no empieza
        this.empty = false;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    
    public boolean isPausa() {
        return pausa;
    }

    public void setPausa(boolean pausa) {
        this.pausa = pausa;
    }

    
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    
    private int getDirection() {
        return direction;
    }
    
    private void setDirection(int i) {
       this.direction = i;
    }
    
    public int getWidth(){
    return width;
    }
    
    public int getHeight(){
    return height;
    }
    
    public void loseLife(){
        this.player.setLives(this.player.getLives()-1);
    }
    
    public KeyManager getKeyManager() {
            return keyManager;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }
    
    public boolean isPowerUp(){
        return PowerUp;
    }
    public void changePowerUp(){
        this.PowerUp = !this.PowerUp;
    } 
    

    private void init() { 
        display = new Display(title, getWidth(), getHeight());
        Assets.init(); 
        Assets.song.play();
        
        //Se pone la barra que es el jugador 
        player = new Player(320, getHeight()-100, 1, 150, 60, this);

         //Se inicializa la pelota a mitad de la barra            
         ball = new Ball(370, getHeight()-130, 1, 40, 40, this);
        
         //despliega una matriz de bloques
        for(int j = 1; j <= 3; j++) {
            for (int i = 1; i <= 7; i++) {
                 bricks.add(new Brick(getWidth()-60 - 100*i ,getHeight()-290- 60*j, 100, 50, this)); 
                 poder.add(new Poder(100*j+50*i,getHeight()*2,40,40,this));   
            }
            
        }
        
        display.getJframe().addKeyListener(keyManager);
    }

    private void tick() {
        keyManager.tick();
        
        //para empezar el juego necesita presionar space 
        if(getKeyManager().space){
        setStart(true);
        }
        
        //logica para boton de pausa
        if (getKeyManager().pause && !isPausa()){
          state=(state == 1 ? 3:1);
        setPausa(true);
        }
        else if (!getKeyManager().pause){
        setPausa(false);
        }
        
        //logica para cuando no es pausa
       if (state !=3){
            //advancing player with colition
            player.tick();
            ball.tick();
            

            if (player.intersecta(ball)){
                ball.setDirection(2);
             }

               if (player.intersecta2(ball)){
                ball.setDirection(1);
             }
               
               //hacemos ticks en los ladrillos
             for (int i = 0; i < bricks.size(); i++) {
               Brick ladrillo =  bricks.get(i);
               ladrillo.tick();
               Poder erlenmeyer = poder.get(i);
               erlenmeyer.tick();
               if(ladrillo.intersecta(ball)){
                 ladrillo.nextBrick();
                 ball.oppositeDirection();
                 int iNum = (int) (Math.random() * 10);
                  if(ladrillo.getY() < 0 && iNum > 5){
                      if(!isPowerUp()){
                          erlenmeyer.changeColor();
                          changePowerUp();
                      }else{
                          changePowerUp();
                      }
                      erlenmeyer.setX(ladrillo.getX()+ladrillo.getWidth()/2 );
                      erlenmeyer.setY(ladrillo.getPreY() );
                      erlenmeyer.isDropping();
                      
                 }
                  
                  //Se actualiza el score
                  setScore(getScore()+10);
                  setNum("Score: "+ getScore());
                  }
             }


             //cuando la pelota toca el suelo se pierde una vida y sale de la troca 
             //mientras el jugador siga teniendo vidas
             if(ball.getY() > getHeight() && player.getLives() >0 ){
                 loseLife();
                 setStart(false);
                 player.setX(320);
                 ball.setX(370);
                 ball.setY(player.getY()-40); 
             }
             else if (player.getLives()== 0){ 
                 state = 2;
             }
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
    player.render(g);//dibujamos al jugador
    
    //dibujamos los ladrillos en la pantalla
    for (int i = 0; i < bricks.size(); i++) {
        Brick brickz =  bricks.get(i);
        brickz.render(g);
    }
    for(int i = 0; i < poder.size();i++ ){
        Poder GreenErlenmeyer =  poder.get(i);
        GreenErlenmeyer.render(g);
    }
    
    	    if (state == 3) {
		g.drawImage(Assets.pause, width / 2 - 98, height / 2 - 27, 196, 54, null);
                Assets.song.play();
	    }
	    
		if (bricks.isEmpty()) {
		    g.drawImage(Assets.win, width / 2 - 112, height / 2 - 32, 224, 64, null);
		} 
                if (state == 2) { 
		    g.drawImage(Assets.gameover, width / 2 - 112, height / 2 - 32, 224, 64, null);
		
	    }
            
    g.drawString(num, 700, 20);
    ball.render(g);
    bs.show();
    g.dispose();

    }


    }

public synchronized void start() { 
    if (state == 0) {
        state = 1;
        thread = new Thread(this); 
        thread.start();
     }
}

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
  while (state != 2) {
	    now = System.nanoTime();
	    delta += (now - lastTime) / timeTick;
	    lastTime = now;
	    
	    if (delta >= 1) {
		tick();
		render();
		delta--;
	    }
	}
	
	// Game over loop
	while (state == 2) {
	    now = System.nanoTime();
	    delta += (now - lastTime) / timeTick;
	    lastTime = now;
	    
	    if (delta >= 1) {
		render();
		delta--;
	    }
   }
   stop(); 
}

    public synchronized void stop() { 
       // if (running) {
       //         running = false; 
       state = 2;
            try {
                thread.join();
            } 
            catch (InterruptedException ie) {
                ie.printStackTrace(); 
               }
         } 
  //  }

}


