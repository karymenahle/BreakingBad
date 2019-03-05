/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingbad;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author karymenahleacosta
 */
public class KeyManager implements KeyListener {
    public boolean up; // para mover hacia arriba
    public boolean down; // para mover hacia abajo
    public boolean left; // para mover hacia la izquierda
    public boolean right; // para mover a la derecha
    public boolean space; //Para ver si esta oprimida la tecla space
    public boolean pause; //para poner el juego en pausa
    public boolean restart;
    
    private boolean keys[]; //para guardar los movimientos
    
    public KeyManager(){
    keys = new boolean[256];
    }

    @Override
    public void keyTyped(KeyEvent e) {
       //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
       //set true to every key pressed
    keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
       //set false to every key released
   keys[e.getKeyCode()] = false;
    }
    
    public void tick(){
        
       // to enable o disable every key 
    up = keys[KeyEvent.VK_UP];
    down = keys[KeyEvent.VK_DOWN];
    left = keys[KeyEvent.VK_LEFT];
    right = keys[KeyEvent.VK_RIGHT];
    space = keys[KeyEvent.VK_SPACE];
    pause=keys[KeyEvent.VK_P];
    restart=keys[KeyEvent.VK_R];

    }
    
}
