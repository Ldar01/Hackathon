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
public class Enemy3 extends ObjetoG {

    private Handler handler;
    private Game game;
    private BufferedImage[] enemy_sprite = new BufferedImage[3];
    Animation anim;

    Random r = new Random();
    int choose = 0;
    int vida = 100;

    public Enemy3(int x, int y, ID id, Handler handler, Game game, SpriteSheet ss) {

        super(x, y, id, ss);

        this.handler = handler;
        this.game = game;
        enemy_sprite[0] = ss.grabbImage(1, 1, 32, 32);
        enemy_sprite[1] = ss.grabbImage(2, 1, 32, 32);
        enemy_sprite[2] = ss.grabbImage(3, 1, 32, 32);
        
         choose = r.nextInt(250);

        vely = (r.nextInt(1 - -1) + r.nextInt(1 - -1) * r.nextInt(1));
       
        anim = new Animation(3,enemy_sprite[0],enemy_sprite[1],enemy_sprite[2]);
        
    }

    @Override
    public void tick() {
       
       y += vely;

        choose = r.nextInt(250);

        for (int i = 0; i < handler.obj.size(); i++) {
            ObjetoG tempObj = handler.obj.get(i);

            if (tempObj.getId() == ID.Block) {
                if (getBounds().intersects(tempObj.getBounds())) {

                    y += (vely * 1) * -1;

                    vely *= -1;
                } else if (choose ==50 ) {

                    handler.addObj(new BalaEnemy(this.getX() + 12, this.getY() + 12, ID.BalaEnemy, handler, -5, 0, ss, game));
                }
            }
           

        }

        if (vida <= 0) {
            handler.removeObject(this);
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
