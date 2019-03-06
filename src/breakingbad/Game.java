/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingbad;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
private int x; //to move image
private int direction; // to set the direction of the player
private Player player; // to use a player

private Ball ball; //To use the ball
private boolean start;//to start the game

private LinkedList<Poder> poder;//to use powers
private LinkedList<Brick> bricks;

private KeyManager keyManager; //to manage the keyboard
private boolean PowerUp;
private int score; // puntaje
private String num; //to display score
private boolean pausa;//to pause the game
private int state; //to know if 1=running 2= endgame 3= pause 4= win 5=gameover
private boolean empty;
private int TotalBricks;//to keep track of total bricks
private int Win;//to keep score of destroyed bricks
private int BricksAlive;
    /**
     *
     * @param title
     * @param width
     * @param height
     */
    public Game(String title, int width, int height) { 
        this.title = title;
        this.width = width;
        this.height = height;
        keyManager = new KeyManager();
        bricks = new LinkedList<Brick>(); 
        poder = new LinkedList<Poder>();
        this.PowerUp = false;
        this.start = false;//we initialize the game as false meaning it wont start
        score = 0;
        num = "Score:"+score;
        this.pausa = false;// se inicializa la variable en falso por que no esta en pausa
        this.state = 0; //Se inicializa en 0 que significa que el juego todavia no empieza
        this.empty = false;
        this.Win = 0;
        this.TotalBricks = 0;
    }

    /**
     *<code> empty<code>isBoolean
     * @return empty
     */
    public boolean isEmpty() {
        return empty;
    }

    /**
     *<code> empty<code>changeBoolean
     * @param empty
     */
    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    /**
     *
     * @return pause
     */
    public boolean isPausa() {
        return pausa;
    }

    /**
     *
     * @param pausa
     */
    public void setPausa(boolean pausa) {
        this.pausa = pausa;
    }

    /**
     *
     * @return
     */
    public String getNum() {
        return num;
    }

    /**
     *
     * @param num
     */
    public void setNum(String num) {
        this.num = num;
    }

    /**
     *
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     *
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }
    
    private int getDirection() {
        return direction;
    }
    
    private void setDirection(int i) {
       this.direction = i;
    }
    
    /**
     *
     * @return
     */
    public int getWidth(){
        return width;
    }
    
    /**
     *
     * @return
     */
    public int getHeight(){
        return height;
    }
    
    /**
     *
     */
    public void loseLife(){
        this.player.setLives(this.player.getLives()-1);
    }
    
    /**
     *
     * @return
     */
    public KeyManager getKeyManager() {
            return keyManager;
    }

    /**
     *
     * @return
     */
    public boolean isStart() {
        return start;
    }

    /**
     *
     * @param start
     */
    public void setStart(boolean start) {
        this.start = start;
    }
    
    /**
     *
     * @return
     */
    
    private int getTotalBricks() {
        return TotalBricks;
    }
    
    private void setTotalBricks(int i) {
       this.TotalBricks = i;
    }
    private int getWin() {
        return Win;
    }
    
    private void setWin(int i) {
       this.Win = i;
    }
    
    /**
     *this returns the state of our game
     * @return
     */
    public int getState(){
        return state;
    }
    /**
     * here we initialize the game
     */
    private void init() { 
        display = new Display(title, getWidth(), getHeight());
        //we add our assets from our Assets class
        Assets.init(); 
        Assets.song.play();//we play Megalovania by toby fox
        //we add the player
        player = new Player(320, getHeight()-100, 1, 150, 60, this);
        //we add the ball on top of the player          
        ball = new Ball(370, getHeight()-130, 1, 40, 40, this);
        //we create a block matrix
        for(int j = 1; j <= 3; j++) {
            for (int i = 1; i <= 7; i++) {
                 bricks.add(new Brick(getWidth()-60 - 100*i ,getHeight()-290- 60*j, 100, 50, this)); 
                 poder.add(new Poder(100*j+50*i,getHeight()*2,40,40,this));   
                 setTotalBricks(getTotalBricks()+1);
            } 
        }
        display.getJframe().addKeyListener(keyManager);
    }

    private void tick() {
        keyManager.tick();
        
        if(getKeyManager().save){
            saveGame();
        }
        
        if(getKeyManager().load){
            loadGame();
        }
        
        if(getKeyManager().again){
        }
        //starting the game with spacebar
        if(getKeyManager().space){
            setStart(true);
        }
        if (getKeyManager().pause && !isPausa()){
            state=(state == 1 ? 3:1);
            setPausa(true);
        }
        else if (!getKeyManager().pause){
            setPausa(false);
        }
        //pause logic
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
             //we actualize the bricks for rendering
             for (int i = 0; i < bricks.size(); i++) {
               Brick ladrillo =  bricks.get(i);
               ladrillo.tick();
               Poder erlenmeyer = poder.get(i);
               erlenmeyer.tick();
                if(erlenmeyer.intersect(player)){
                    player.setbGrow(true);
                    erlenmeyer.setY(1000);
                    setScore(getScore() + 30);
                    setNum("Score: "+ getScore());
                }
               if(ladrillo.intersecta(ball)){
                  ladrillo.nextBrick();
                  ball.oppositeDirection();
                  int iNum = (int) (Math.random() * 10);
                  if(ladrillo.getY() < 0 ){
                      setWin(getWin()+1);
                      if(iNum > 5){
                            //we set the powerup in position and drop the power up
                            erlenmeyer.setX(ladrillo.getX()+ladrillo.getWidth()/2 );
                            erlenmeyer.setY(ladrillo.getPreY() );
                            erlenmeyer.isDropping();
                            ball.setSpeed(ball.getSpeed()+1);
                        }
                    }
                  //actualize score
                  setScore(getScore() + 10);
                  setNum("Score: "+ getScore());
                }

             }


             //logic for when the player loses a live
             if(ball.getY() > getHeight() && player.getLives() >0 ){
                 loseLife();
                 ball.setSpeed(ball.getSpeed()-1);
                 setScore(getScore() - 50);
                 setNum("Score: "+ getScore());
                 setStart(false);
                 player.setX(320);
                 ball.setX(370);
                 ball.setY(player.getY()-40); 
             }
             //sets our lose ocndition
             else if (player.getLives() == 0){ 
                 state = 5;
                // bricks.clear();
             }
             //sets our win condition
             if(getTotalBricks() == getWin()){
                 state = 4;
             }
             //restart the game
             if(getKeyManager().again){//s is pressed 
                if(state == 4 || state == 5){//if win or game over
                    state = 1;
                    setScore(0);
                    setNum("Score:"+score);
                    player.setbGrow(false);
                    player.setX(320);
                    player.setY(getHeight()-100);
                    player.setLives(3);
                    Assets.song.play();
                    setStart(false);
                    ball.changeVisibility(true);
                    ball.setX(370);
                    ball.setY(getHeight()-130);
                    ball.setSpeed(4);
                   for(int j = 1; j <= 3; j++) {
                       for (int i = 1; i <= 7; i++) {
                            bricks.add(new Brick(getWidth()-60 - 100*i ,getHeight()-290- 60*j, 100, 50, this)); 
                            poder.add(new Poder(100*j+50*i,getHeight()*2,40,40,this));   
                           setTotalBricks(getTotalBricks()+1);
                       } 
                   }
                   render();
                }
             }
    }
    }

/**
 * renders elements in canvas
 */
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
        //we add images
        Graphics g = bs.getDrawGraphics();
        g.drawImage(Assets.background, 0, 0, width, height, null); 
        player.render(g);//render the player

        //loopfor rendering all bricks
        for (int i = 0; i < bricks.size(); i++) {
            Brick brickz =  bricks.get(i);
            brickz.render(g);
        }
        //we render powerups
        for(int i = 0; i < poder.size();i++ ){
            Poder GreenErlenmeyer =  poder.get(i);
            GreenErlenmeyer.render(g);
        }
        //pause case
        if (state == 3) {
            g.drawImage(Assets.pause, width / 2 - 98, height / 2 - 27, 196, 54, null);
            Assets.song.play();
        }
        //win case
        if (state == 4 ) {
            g.drawImage(Assets.win, width / 2 - 112, height / 2 - 32, 224, 64, null);
        } 
        //game over case
        if (state == 5 ) { 
               g.drawImage(Assets.gameover, 0, 0, getWidth(), getHeight(), null); 
               Assets.song.stop();
               ball.changeVisibility(false);
               setPausa(true);     
        }
        //graw score
        g.drawString(num, 700, 20);
        //draw ball
        ball.render(g);
        bs.show();
        g.dispose();
    }
}

    /**
     *for runing our game
     */
    public synchronized void start() { 
        if (state == 0) {
            state = 1;
            thread = new Thread(this); 
            thread.start();
         }
    }

    /**
     *In this function we manage time
     */
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
  while (state != 2 ) {
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
	while (state == 2 ) {
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

    /**
     *stops our game
     */
    public synchronized void stop() { 
       state = 2;
        try {
            thread.join();
        } 
        catch (InterruptedException ie) {
            ie.printStackTrace(); 
           }
    } 
    
   
   private void saveGame(){
    try{
    FileWriter fw = new FileWriter("save.txt");
    
    fw.write(String.valueOf(player.getX())+ "\n");   
    fw.write(String.valueOf(player.getY())+ "\n");
    fw.write(String.valueOf(player.getLives())+ "\n");
    fw.write(String.valueOf(player.isbGrow())+ "\n");
    fw.write(String.valueOf(getScore())+ "\n");
 //   fw.write(String.valueOf(getNum())+ "\n");
    fw.write(String.valueOf(ball.getX())+ "\n");
    fw.write(String.valueOf(ball.getY())+ "\n");
    fw.write(String.valueOf(ball.getDirection())+ "\n");
    fw.write(String.valueOf(ball.getSpeed())+ "\n");

//Bricks
// //           for(int i = 0; i < bricks.size(); i++){
// //                Brick ladrillo =  bricks.get(i);
// //                if(ladrillo.isAlive()){
// //                    fw.write("1/n");
// //                }else{
// //                    fw.write("0/n");
// //                }
// //           }
// //           fw.write(String.valueOf(bricks.size())+"/n");

    fw.close();
        }
    catch (IOException ex){
        ex.printStackTrace();
    }
      }
    
    
    private void loadGame(){
        try{
        BufferedReader br = new BufferedReader(new FileReader("save.txt"));
        
        player.setX(Integer.parseInt(br.readLine()));
        player.setY(Integer.parseInt(br.readLine()));
        player.setLives(Integer.parseInt(br.readLine()));
        player.setbGrow(Boolean.parseBoolean(br.readLine()));
        setScore(Integer.parseInt(br.readLine()));
  //      setNum(Integer.parseInt(br.readLine()));
        
        ball.setX(Integer.parseInt(br.readLine()));
        ball.setY(Integer.parseInt(br.readLine()));
        ball.setDirection(Integer.parseInt(br.readLine()));
        ball.setSpeed(Integer.parseInt(br.readLine()));
        
        //Bricks
 //      for(int i = 0; i < bricks.size(); i++){
//              
////          }
        
        br.close();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
    }