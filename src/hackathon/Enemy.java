/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hackathon;

import hackathon.ObjetoGInterface.ObjetoG;
import hackathon.animacion.Animation;
import hackathon.spriteAd.SpriteSheet;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author Jose Segura <com.segura.jd>
 */
public class Enemy extends ObjetoG {

    private Handler handler;
    private Game game;
    private BufferedImage[] enemy_sprite = new BufferedImage[3];
    Animation anim;

    Random r = new Random();
    int choose = 0;
    int vida = 100;

    public Enemy(int x, int y, ID id, Handler handler, Game game, SpriteSheet ss) {

        super(x, y, id, ss);

        this.handler = handler;
        this.game = game;
        enemy_sprite[0] = ss.grabbImage(1, 1, 32, 32);
        enemy_sprite[1] = ss.grabbImage(2, 1, 32, 32);
        enemy_sprite[2] = ss.grabbImage(3, 1, 32, 32);
       
        anim = new Animation(3,enemy_sprite[0],enemy_sprite[1],enemy_sprite[2]);
        
    }

    @Override
    public void tick() {
        x += velx;
        y += vely;

        choose = r.nextInt(250);

        for (int i = 0; i < handler.obj.size(); i++) {
            ObjetoG tempObj = handler.obj.get(i);

            if (tempObj.getId() == ID.Block || (tempObj != this && tempObj.getId() == ID.Enemy)) {
                if (getBounds().intersects(tempObj.getBounds())) {
                    x += (velx * 1) * -1;
                    y += (vely * 1) * -1;
                    velx *= -1;
                    vely *= -1;
                } else if (choose == 11) {
                    velx = (r.nextInt(3 - -3) + (r.nextInt(3 - -3) / 3) * r.nextInt(1));
                    vely = (r.nextInt(2 - -2) + r.nextInt(2 - -2) * r.nextInt(1));
                }
            }
            else if (tempObj.getId() == ID.Bala) {
                if (getBounds().intersects(tempObj.getBounds())) {
                    vida -= 10;
                    game.energia+=5;
                    handler.removeObject(tempObj);
                    handler.shoot=false;
                }
            }
             if (tempObj.getId() == ID.Player) {
                if (getBounds().intersects(tempObj.getBounds())) {
                    x += (velx * 1) * -1;
                    y += (vely * 1) * -1;
                    velx *= -1;
                    vely *= -1;
                } else if (choose == 11) {
                    velx = (r.nextInt(3 - -3) + (r.nextInt(3 - -3) / 3) * r.nextInt(1));
                    vely = (r.nextInt(2 - -2) + r.nextInt(2 - -2) * r.nextInt(1));
                }
            }

        }

        if (vida <= 0) {
            handler.removeObject(this);
            game.Enem--;
        }
        
        anim.runAnimation();

    }

    @Override
    public void render(Graphics g) {
        anim.drawAnimation(g, x, y, 0);
    }

    @Override
    public Rectangle getBounds() {

        return new Rectangle(x, y, 32, 32);
    }



}
