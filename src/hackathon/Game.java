package hackathon;

import hackathon.spriteAd.SpriteSheet;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jose Segura <com.segura.jd>
 */
public class Game extends Canvas implements Runnable {

    private boolean isRunning = false;
    private Thread thread;
    private Handler handler;

    private BufferedImage menu = null;
    private BufferedImage control = null;
    private BufferedImage slots = null;
    private BufferedImage level1 = null;

    private BufferedImage fondo = null;
    private BufferedImage corazon = null;
    private BufferedImage loose = null;
    private BufferedImage win = null;
    private BufferedImage sprite_anton = null;
    private BufferedImage sprite_esfera = null;
    private BufferedImage sprite_bala = null;
    private BufferedImage sprite_Boss1 = null;
    private BufferedImage sprite_Boss2 = null;
    private BufferedImage sprite_Enemy2 = null;
    private BufferedImage sprite_Enemy3 = null;

    SpriteSheet ss;
    SpriteSheet ss01;
    SpriteSheet ss02;
    SpriteSheet ss03;
    SpriteSheet ss04;
    SpriteSheet ss05;
    SpriteSheet ss06;

    private int CurrentBoss = 1;
    private int bossCount = 0;
    public int muertes = 0;

    private int[] save = {CurrentBoss, bossCount, muertes};

    private String CurrentSlot = "";

    public int energia = 100;
    public int vida = 300;

    boolean pasarNivel = false;
    public int Enem = 0;
    public static int LEVEL = 1;

    public static enum STATE {
        Slots,
        Menu,
        Habilidades,
        Instrucciones,
        Game,
        WIN,
        LOOSE;
    };

    public static STATE State = STATE.Slots;

    public Game() {
        Window window = new Window(1024, 520, "Dimensionis", this);
        start();

        BufferedImageLoader loader = new BufferedImageLoader();
        menu = loader.loadImage("/Images/menu.png");
        slots = loader.loadImage("/Images/slots.png");
        control = loader.loadImage("/Images/control.png");
        level1 = loader.loadImage("/Images/Level1.png");

        loose = loader.loadImage("/Images/loose.png");
        win = loader.loadImage("/Images/win.png");
        sprite_anton = loader.loadImage("/Images/sprites/anton01-Sheet.png");
        sprite_esfera = loader.loadImage("/Images/sprites/esfera.png");
        sprite_bala = loader.loadImage("/Images/sprites/basico.png");
        sprite_Enemy2 = loader.loadImage("/Images/sprites/Enemy2-Sheet.png");
        sprite_Enemy3 = loader.loadImage("/Images/sprites/Enemy3-Sheet.png");
        sprite_Boss1 = loader.loadImage("/Images/sprites/Boss1-Sheet.png");
        sprite_Boss2 = loader.loadImage("/Images/sprites/Boss2-Sheet.png");
        fondo = loader.loadImage("/Images/fondo.png");
        corazon = loader.loadImage("/Images/sprites/corazon.png");

        ss = new SpriteSheet(sprite_anton);
        ss01 = new SpriteSheet(sprite_esfera);
        ss02 = new SpriteSheet(sprite_bala);
        ss03 = new SpriteSheet(sprite_Enemy2);
        ss04 = new SpriteSheet(sprite_Enemy3);
        ss05 = new SpriteSheet(sprite_Boss1);
        ss06 = new SpriteSheet(sprite_Boss2);

        handler = new Handler(this);

        this.addMouseListener(new MouseInput(this, handler));
        this.addKeyListener(new KeyInput(handler, this, ss02));

    }

    public int getEnem() {
        return Enem;
    }

    public void setEnem(int Enem) {
        this.Enem = Enem;
    }
    

    public BufferedImage getLevel1() {
        return level1;
    }

    public void setLevel1(BufferedImage level1) {
        this.level1 = level1;
    }

    public void run() {
        //Bucle de refrescamiento de frames programado por Jebb el creador de Minecraft

        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("Updates: " + updates + " Frames:" + "" + frames);
                frames = 0;
                updates = 0;

            }

        }
        // System.out.println("is running");
        savePlayer();
        stop();
    }

    public void tick() {
        if (State == STATE.Game) {
            handler.tick();
        }
        if (this.energia > 100) {
            this.energia = 100;
        }
        if (this.energia < 0) {
            this.energia = 0;
        }
        if (this.Enem <= 0 && pasarNivel) {
            PonerPortal();

        }
        if (vida <= 0) {
            Game.State = Game.STATE.LOOSE;
            handler.ClearLevel();
            vida = 100;
            muertes++;
            Enem = 0;
        }
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(2);
            return;
        }

        /////// Aca pa dibujar cosas en pantalla///////
        Graphics g = bs.getDrawGraphics();

        if (State == STATE.Game) {
            g.drawImage(fondo, 0, 0, null);

            handler.render(g); //pongo el render de los objetos aqui para q esten por encima del fondo
            //vida

            for (int i = 1; i <= vida; i += 25) {
                int e = 1;
                e++;
                g.drawImage(corazon, i + 2, 5, null);

            }
            //energia
            g.setColor(Color.gray);
            g.fillRect(1, 17, 200, 10);
            g.setColor(Color.blue);
            g.fillRect(1, 17, energia * 2, 10);
            g.setColor(Color.black);
            g.drawRect(1, 17, 200, 10);
////////////////////////////////////////

            for (int i = 1; i <= vida; i += 25) {
                int e = 1;
                e++;
                g.drawImage(corazon, i + 2, 5, null);

            }
            //energia
            g.setColor(Color.gray);
            g.fillRect(1, 17, 200, 10);
            g.setColor(Color.CYAN);
            g.fillRect(1, 17, energia * 2, 10);
            g.setColor(Color.black);
            g.drawRect(1, 17, 200, 10);
////////////////////////////////////////

        } else if (State == STATE.Slots) {
            Enem =0;
            g.drawImage(slots, 0, 0, null);
        } else if (State == STATE.Menu) {
            g.drawImage(menu, 0, 0, null);
            Font f = new Font("arial", Font.BOLD, 30);
            g.setColor(Color.red);
            g.setFont(f);
            g.drawString("MUERTES: " + muertes, 400, 450);
        } else if (State == STATE.Instrucciones) {
            Enem =0;
            g.drawImage(control, 0, 0, null);
        } else if (State == STATE.WIN) {
            Enem =0;
            g.drawImage(win, 0, 0, null);
        } else if (State == STATE.LOOSE) {
            g.drawImage(loose, 0, 0, null);
        }

        ////////////////////////////////////////////////
        g.dispose();

        bs.show();
    }

    public void PonerPortal() {
        handler.addObj(new Next(512, 55, ID.Portal, ss));
        pasarNivel = false;
    }

    public String getCurrentSlot() {
        return CurrentSlot;
    }

    public void setCurrentSlot(String CurrentSlot) {
        this.CurrentSlot = CurrentSlot;
    }

    public int getCurrentBoss() {
        return CurrentBoss;
    }

    public void setCurrentBoss(int CurrentBoss) {
        this.CurrentBoss = CurrentBoss;
    }

    public int getBossCount() {
        return bossCount;
    }

    public void setBossCount(int bossCount) {
        this.bossCount = bossCount;
    }

    //Actualizando datos
    public void updatePlayer() {

        CurrentBoss = save[0];
        bossCount = save[1];
        muertes = save[2];

    }

    //Cargando datos
    public void readPlayer() {
        try {
            File inputFile;
            BufferedReader inputReader;

            inputFile = new File(CurrentSlot);
            inputReader = new BufferedReader(new FileReader(CurrentSlot));

            for (int i = 0; i < save.length; i++) {
                try {
                    save[i] = Integer.parseInt(inputReader.readLine());
                } catch (IOException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            inputReader.close();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        updatePlayer();
    }

    //Guardando datos
    public void savePlayer() {
        if (!CurrentSlot.isEmpty()) {

            try {

                save[0] = CurrentBoss;
                save[1] = bossCount;
                save[2] = muertes;
                File outputFile;
                BufferedWriter outputWriter;

                outputFile = new File(CurrentSlot);
                outputWriter = new BufferedWriter(new FileWriter(CurrentSlot));

                for (int i = 0; i < save.length; i++) {
                    try {
                        outputWriter.write(Integer.toString(save[i]));
                        outputWriter.newLine();
                    } catch (IOException ex) {
                        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                outputWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println(CurrentSlot + CurrentBoss + bossCount);
        }

    }

    public void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {

        isRunning = false;
        try {
            thread.join();

        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }

    public static void main(String... args) {
        Game game = new Game();
    }
}
