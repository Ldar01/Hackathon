package hackathon.ObjetoGInterface;

import hackathon.ID;
import hackathon.spriteAd.SpriteSheet;
import java.awt.Graphics;
import java.awt.Rectangle;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jose Segura <com.segura.jd>
 */
public abstract class ObjetoG {

    protected int x, y;
    protected float velx = 0, vely = 0;
    protected ID id;
    protected SpriteSheet ss;

    public ObjetoG(int x, int y, ID id, SpriteSheet ss) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.ss = ss;

    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract Rectangle getBounds();

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getVelx() {
        return velx;
    }

    public void setVelx(float velx) {
        this.velx = velx;
    }

    public float getVely() {
        return vely;
    }

    public void setVely(float vely) {
        this.vely = vely;
    }

}
