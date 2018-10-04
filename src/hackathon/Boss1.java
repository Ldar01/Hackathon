/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hackathon;

import hackathon.ObjetoGInterface.ObjetoG;
import hackathon.animacion.Animation;
import hackathon.spriteAd.SpriteSheet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author Jose Segura <com.segura.jd>
 */
public class Boss1 extends ObjetoG {

    private Handler handler;
    private Game game;
    private BufferedImage[] enemy_sprite = new BufferedImage[3];
    Animation anim;

    Random r = new Random();
    int choose = 0;
    int vida = 400;

    public Boss1(int x, int y, ID id, Handler handler, Game game, SpriteSheet ss) {

        super(x, y, id, ss);

        this.handler = handler;
        this.game = game;
        enemy_sprite[0] = ss.grabbImage(1, 1, 300, 200);
        enemy_sprite[1] = ss.grabbImage(2, 1, 300, 200);
        enemy_sprite[2] = ss.grabbImage(3, 1, 300, 200);

        anim = new Animation(3, enemy_sprite[0], enemy_sprite[1], enemy_sprite[2]);

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
                    velx = (r.nextInt(2 - -3) + (r.nextInt(1 - -3) / 3) * r.nextInt(1));
                    vely = (r.nextInt(1 - -1) + r.nextInt(0 - -1) * r.nextInt(1));
                } else if (choose == 55 || choose == 100) {
                    velx = (r.nextInt(5 - -5) + (r.nextInt(5 - -5) / 5) * r.nextInt(1));
                    vely = (r.nextInt(2 - -2) + r.nextInt(2 - -2) * r.nextInt(1));
                }
            }
            if (tempObj.getId() == ID.Bala) {
                if (getBounds().intersects(tempObj.getBounds())) {
                    vida -= 5;
                    handler.removeObject(tempObj);
                    game.energia += 5;
                }
            }
            if (tempObj.getId() == ID.Player) {
                if (getBounds().intersects(tempObj.getBounds())) {
                    vida += 30;
                    velx *= -1;
                    vely *= -1;
                    game.energia = 0;
                }
            }

        }

        if (vida <= 0) {
            handler.removeObject(this);
            game.setBossCount(game.getBossCount() + 1);
            if(game.getBossCount()>3)
                game.setCurrentBoss(2);
            Game.State = Game.STATE.WIN;
            handler.ClearLevel();
        }
        if(vida>400)
            vida = 400;

        anim.runAnimation();

    }

    @Override
    public void render(Graphics g) {
        anim.drawAnimation(g, x, y, 0);
        g.setColor(Color.gray);
        g.fillRect(1, 450, 400, 10);
        g.setColor(Color.red);
        g.fillRect(1, 450, this.vida, 10);
        g.setColor(Color.black);
        g.drawRect(1, 450, 400, 10);

    }

    @Override
    public Rectangle getBounds() {

        return new Rectangle(x+5, y, 290, 200);
    }

}
