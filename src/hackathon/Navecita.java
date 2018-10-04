package hackathon;

import hackathon.Handler;
import hackathon.ID;
import hackathon.ObjetoGInterface.ObjetoG;
import hackathon.animacion.Animation;
import hackathon.spriteAd.SpriteSheet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jose Segura <com.segura.jd>
 */
public class Navecita extends ObjetoG {

    Handler handler;
    private BufferedImage[] anton_sprite = new BufferedImage[24];
    Animation animAbajo;
    Animation animArriba;
    Animation animDerecha;
    Animation animIzquierda;
    int dir = 0;
    private Game game;
    boolean run1 = true;

    public Navecita(int x, int y, ID id, Handler handler, SpriteSheet ss, Game game) {
        super(x, y, id, ss);
        velx = 0;
        vely = 0;

        this.handler = handler;
        this.game = game;
        anton_sprite[0] = ss.grabbImage(1, 2, 32, 64);


    }

    @Override
    public void tick() {
        x += velx;
        y += vely;

        colision();

        //Movimiento del jugador
        if (handler.isUp()) {
            
            dir = 1;
            if (handler.isSD() && game.energia > 0) {
                vely = -7;
                game.energia--;
            } else {
                vely = -3;
            }
        } else if (!handler.isDown()) {
            vely = 0;
        }

        if (handler.isDown()) {
            
            if (handler.isSD() && game.energia > 0) {
                vely = 7;
                game.energia--;
            } else {
                vely = 3;
            }
            dir = 2;
        } else if (!handler.isUp()) {
            vely = 0;
        }

        if (handler.isRight()) {
            
            dir = 3;
            if (handler.isSD() && game.energia > 0) {
                velx = 7;
                game.energia--;
            } else {
                velx = 3;
            }
        } else if (!handler.isLeft()) {
            velx = 0;
        }

        if (handler.isLeft()) {
           
            if (handler.isSD() && game.energia > 0) {
                velx = -7;
                game.energia--;
            } else {
                velx = -3;
            }
            dir = 4;
        } else if (!handler.isRight()) {
            velx = 0;
        }
        if (game.vida <= 0) {
            handler.removeObject(this);
        }

    }

    private void colision() {
        for (int i = 0; i < handler.obj.size(); i++) {
            ObjetoG tempObj = handler.obj.get(i);

            if (tempObj.getId() == ID.Block) {

                if (getBounds().intersects(tempObj.getBounds())) {
                    x += velx * -1;
                    y += vely * -1;

                }
            }

            if (tempObj.getId() == ID.Enemy) {

                if (getBounds().intersects(tempObj.getBounds())) {
                    game.vida -= 5;

                    x += velx * -2;
                    y += vely * -2;

                }
            }

            if (tempObj.getId() == ID.Enemy) {

                if (getBounds().intersects(tempObj.getBounds())) {
                    game.vida -= 10;

                    x += velx * -2;
                    y += vely * -2;

                }
            }

            if (tempObj.getId() == ID.Boss) {

                if (getBounds().intersects(tempObj.getBounds())) {
                    game.vida -= 5;

                    x += velx * -1;
                    y += vely * -1;

                }
            }

            if (tempObj.getId() == ID.Portal) {

                if (getBounds().intersects(tempObj.getBounds())) {
                    game.pasarNivel = false;
                    handler.SwitchLevel();

                }
            }
            if (tempObj.getId() == ID.BalaEnemy) {

                if (getBounds().intersects(tempObj.getBounds())) {

                    game.energia = 0;

                }
            }

        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(anton_sprite[0], x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 64);
    }

}
