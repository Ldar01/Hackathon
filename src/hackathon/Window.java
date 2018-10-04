package hackathon;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jose Segura <com.segura.jd>
 */
public class Window  extends javax.swing.JFrame{

    public Window(int width, int height, String titulo, Game game) {

        

        this.setPreferredSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));
        this.setMinimumSize(new Dimension(width, height));
        this.add(game);
        this.setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                game.savePlayer();
                
                JOptionPane.showMessageDialog(rootPane,"Guardando antes de salir.....");
                System.exit(0);
                dispose();
                
                
                
                
                //close();
            }
        });

    }
    
     private void close(){
         
        if (JOptionPane.showConfirmDialog(rootPane, "Guardando..........."+"\n"+"¿Desea salir del juego?",
                "Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            System.exit(0);
    }    
}


