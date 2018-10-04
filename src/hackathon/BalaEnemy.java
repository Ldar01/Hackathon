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
public class BalaEnemy extends ObjetoG {

    private Handler handler;
    private BufferedImage[] bala_sprite = new BufferedImage[3];
    Animation anim;
    Game game;
  

    public BalaEnemy(int x, int y, ID id, Handler handler, int mx, int my, SpriteSheet ss, Game game) {
        super(x, y, id, ss);
        this.handler = handler;
        velx = mx;
        vely = my;

        this.game = game;

        bala_sprite[0] = ss.grabbImage(4, 1, 32, 32);
        bala_sprite[1] = ss.grabbImage(5, 1, 32, 32);
        bala_sprite[2] = ss.grabbImage(6, 1, 32, 32);

        anim = new Animation(3, bala_sprite[0], bala_sprite[1], bala_sprite[2]);
    }

    @Override
    public void tick() {
        x += velx;
        y += vely;

       

        anim.runAnimation();
        if (x > 1000 || x < 0) {
            handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g
    ) {
        anim.drawAnimation(g, x, y, 0);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 1, 1);

    }

}
