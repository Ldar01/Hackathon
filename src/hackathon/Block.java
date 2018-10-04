package hackathon;


import hackathon.ObjetoGInterface.ObjetoG;
import hackathon.spriteAd.SpriteSheet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;



/**
 *
 * @author Jose Segura <com.segura.jd>
 */
public class Block extends ObjetoG {
    Handler handler;

    public Block(int x, int y, ID id,SpriteSheet ss, Handler handler) {
        super(x, y, id,ss);
        this.handler=handler;
    }

    
    public void tick() {
       
    }

   
    public void render(Graphics g) {
       
    }

   
    public Rectangle getBounds() {
        return new Rectangle(x, y, 34, 38);
    }
    
}
