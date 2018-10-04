/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hackathon.spriteAd;

import java.awt.image.BufferedImage;

/**
 *
 * @author Jose Segura <com.segura.jd>
 */
public class SpriteSheet {
    
    private BufferedImage image;

    public SpriteSheet(BufferedImage image) {
        this.image = image;
    }
    
    public BufferedImage grabbImage(int col,int row,int width,int height){
        return image.getSubimage((col*width)-width, (row*height)-height, width, height);
    }
   
}
