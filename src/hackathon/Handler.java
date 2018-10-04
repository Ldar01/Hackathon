package hackathon;

import hackathon.ObjetoGInterface.ObjetoG;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Handler {

    ArrayList<ObjetoG> obj = new ArrayList<>();
    public Game game;
    
    private BufferedImage levelBoss1 = null;
  
    public boolean shoot = false;
    public Handler(Game game) {
        this.game = game;
        BufferedImageLoader loader = new BufferedImageLoader();
        levelBoss1 = loader.loadImage("/Images/LevelBoss.png");
    }
    

    private boolean up = false, down = false, right = false, left = false, SD = false, SR = false, SU = false, SL = false;

    public boolean isSD() {
        return SD;
    }

    public void setSD(boolean SD) {
        this.SD = SD;
    }

    
    

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }
    //Cargando nivel
    public  void loadlevel(BufferedImage image) {
        game.vida = 100;
        game.energia  = 100;
        setDown(false);
        setLeft(false);
        setRight(false);
        setSD(false);
        setUp(false);
        
        int h = image.getHeight();
        int w = image.getWidth();

        for (int xx = 0; xx < w; xx++) {
            for (int yy = 0; yy < h; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

               
                if (red == 174) {
                    if(game.getCurrentBoss()== 2)
                        addObj(new Boss2(xx * 32, yy * 32, ID.Boss, this, game, game.ss06));
                    else
                        addObj(new Boss1(xx * 32, yy * 32, ID.Boss, this, game, game.ss05));
                }
                
                if (red == 254 && green == 54 && blue == 254) {
                    addObj(new Block(xx * 32, yy * 32, ID.Block, game.ss,this));
                }

                if (blue == 255) {
                    addObj(new Navecita(xx * 32, yy * 32, ID.Player, this, game.ss, game));
                }
               
            }
        }
        if(Game.LEVEL == 1)
            game.pasarNivel = true;
       
    }
    
    void ClearLevel(){
        obj.clear();
    }
    
    public void SwitchLevel(){
        ClearLevel();
        switch(Game.LEVEL){
            case 1:
                game.pasarNivel = false;
                Game.LEVEL = 2;
                loadlevel(levelBoss1);
                
                break;
        }
    }
    
    public void tick() {
        for (int i = 0; i < obj.size(); i++) {
            ObjetoG tempObj = obj.get(i);

            tempObj.tick();
        }
    }

    public void render(Graphics g) {

        for (int i = 0; i < obj.size(); i++) {
            ObjetoG tempObj = obj.get(i);

            tempObj.render(g);
        }

    }

    public void addObj(ObjetoG tempObj) {
        obj.add(tempObj);
    }

    public void removeObject(ObjetoG tempObj) {
        obj.remove(tempObj);
    }
}
