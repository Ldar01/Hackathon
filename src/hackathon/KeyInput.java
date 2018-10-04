package hackathon;

import hackathon.ObjetoGInterface.ObjetoG;
import hackathon.spriteAd.SpriteSheet;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Jose Segura <com.segura.jd>
 */
public class KeyInput extends KeyAdapter {

    private Handler handler;
    private Game game;
    
    private SpriteSheet ss;

    public KeyInput(Handler handler, Game game, SpriteSheet ss) {
        this.handler = handler;
        this.game = game;
        this.ss = ss;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (game.State == Game.STATE.Game) {

            for (int i = 0; i < handler.obj.size(); i++) {
                ObjetoG tempObj = handler.obj.get(i);

                if (tempObj.getId() == ID.Player) {
                    //Movimiento ASWD
                    if (key == KeyEvent.VK_W) {
                        handler.setUp(true);
                    }
                    if (key == KeyEvent.VK_A) {
                        handler.setLeft(true);
                    }
                    if (key == KeyEvent.VK_S) {
                        handler.setDown(true);
                    }
                    if (key == KeyEvent.VK_D) {
                        handler.setRight(true);
                    }
                    if (key == KeyEvent.VK_SPACE&& !handler.shoot) {
                        handler.addObj(new Bala(tempObj.getX() + 12, tempObj.getY() + 12, ID.Bala, handler, 0, -10, ss));
                        handler.shoot=true;
                    } 

                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.obj.size(); i++) {
            ObjetoG tempObj = handler.obj.get(i);

            if (tempObj.getId() == ID.Player) {
                if (key == KeyEvent.VK_W) {
                    handler.setUp(false);
                }
                if (key == KeyEvent.VK_A) {
                    handler.setLeft(false);
                }
                if (key == KeyEvent.VK_S) {
                    handler.setDown(false);
                }
                if (key == KeyEvent.VK_D) {
                    handler.setRight(false);
                }
            }
        }
    }
}
