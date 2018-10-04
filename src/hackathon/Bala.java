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
public class Bala extends ObjetoG {

    private Handler handler;
    private BufferedImage[] bala_sprite = new BufferedImage[3];
    Animation anim;


    public Bala(int x, int y, ID id,Handler handler, int mx, int my,SpriteSheet ss) {
        super(x, y, id,ss);
        this.handler = handler;
        velx = mx;
        vely = my;
        
        bala_sprite[0] = ss.grabbImage(1, 1, 16, 16);
        bala_sprite[1] = ss.grabbImage(2, 1, 16, 16);
        bala_sprite[2] = ss.grabbImage(3, 1, 16, 16);
       
       
        anim = new Animation(3,bala_sprite[0],bala_sprite[1],bala_sprite[2]);
    }



    @Override
    public void tick() {
        x += velx;
        y += vely;

        for (int i = 0; i < handler.obj.size(); i++) {
            ObjetoG tempObj = handler.obj.get(i);

            if (tempObj.getId() == ID.Block ) {
                if (getBounds().intersects(tempObj.getBounds())) {
                    handler.removeObject(this);
                    handler.shoot=false;
                }
            }
            
        }
        anim.runAnimation();
    }

    @Override
    public void render(Graphics g
    ) {
       anim.drawAnimation(g, x, y, 0);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 8, 8);

    }

}
