package hackathon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jose Segura <com.segura.jd>
 */
public class MouseInput extends MouseAdapter {

    private Game game;
    private Handler handler;

    public MouseInput(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = (int) e.getX();
        int my = (int) e.getY();

        //Slots
        if (Game.State == Game.STATE.Slots) {
            //Slot1
            if (mx >= 192 && mx <= 322) {
                if (my >= 336 && my <= 400) {
                    game.setCurrentSlot("slot1.txt");
                    game.readPlayer();
                    game.updatePlayer();
                    //Ir al menu
                    Game.State = Game.STATE.Menu;

                }
            }
            //Slot2
            if (mx >= 432 && mx <= 562) {
                if (my >= 336 && my <= 400) {
                    game.setCurrentSlot("slot2.txt");
                    game.readPlayer();
                    game.updatePlayer();
                    //Ir a instrucciones
                    Game.State = Game.STATE.Menu;

                }
            }

            //Slot3
            if (mx >= 706 && mx <= 836) {
                if (my >= 336 && my <= 400) {
                    game.setCurrentSlot("slot3.txt");
                    game.readPlayer();
                    game.updatePlayer();
                    //Ir a instrucciones
                    Game.State = Game.STATE.Menu;

                }
            }

        } //Menu
        else if (Game.State == Game.STATE.Menu) {
            
            

            //JUGAR
            if (mx >= 192 && mx <= 322) {
                if (my >= 336 && my <= 400) {
                    //Empezar juego
                    Game.State = Game.STATE.Game;
                    handler.loadlevel(game.getLevel1());

                }
            }
            //Controles
            if (mx >= 432 && mx <= 562) {
                if (my >= 336 && my <= 400) {
                    //Ir a instrucciones
                    Game.State = Game.STATE.Instrucciones;

                }
            }

            //Atras
            if (mx >= 706 && mx <= 836) {
                if (my >= 336 && my <= 400) {
                    //Ir a instrucciones
                    Game.State = Game.STATE.Slots;

                }
            }

        } //Instrucciones
        else if (Game.State == Game.STATE.Instrucciones) {
            //JUGAR
            if (mx >= 880 && mx <= 1008) {
                if (my >= 432 && my <= 465) {
                    //Empezar juego
                    Game.State = Game.STATE.Menu;

                }
            }
        } else if (Game.State == Game.STATE.LOOSE) {
            //JUGAR
            if (mx >= 370 && mx <= 592) {
                if (my >= 249 && my <= 309) {
                    //Empezar juego
                    Game.State = Game.STATE.Menu;

                }
            }
        }
        else if (Game.State == Game.STATE.WIN) {
            //JUGAR
            if (mx >= 370 && mx <= 592) {
                if (my >= 249 && my <= 309) {
                    //Empezar juego
                    Game.State = Game.STATE.Menu;

                }
            }
        }
    }
}
