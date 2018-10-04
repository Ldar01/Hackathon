/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hackathon;

import hackathon.ObjetoGInterface.ObjetoG;
import hackathon.spriteAd.SpriteSheet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author ~J.D
 */
public class Next extends ObjetoG {

    public Next(int x, int y, ID id,SpriteSheet ss) {
        super(x, y, id,ss);
    }

    
    public void tick() {
        
    }

   
    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillOval(x, y, 50, 20);
       
    }

   
    public Rectangle getBounds() {
        return new Rectangle(x, y, 50, 20);
    }
    
}