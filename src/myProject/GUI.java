package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * This class is used for ...
 * @autor Paola-J Rodriguez-C paola.rodriguez@correounivalle.edu.co
 * @version v.1.0.0 date:21/11/2021
 */
public class GUI extends JFrame {

    private Header headerProject;
    private AreasDeJuego jugador;
    private TableroPrincipal tableroPrincipal;
    private ImageIcon image;
    private Escucha escucha;
    private JButton mostrar, iniciar;
    private static final String PATH = "resources/agua.jpeg/";


    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("Batalla Naval");
        //this.setSize(1000,600);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        //Create Listener Object and Control Object
        //Set up JComponents
        headerProject = new Header("BATALLA NAVAL", Color.BLACK);

        this.add(headerProject,BorderLayout.NORTH); //Change this line if you change JFrame Container's Layout


        jugador = new AreasDeJuego();
        add(jugador, BorderLayout.SOUTH);
        jugador.setLayout(null);

        mostrar = new JButton("Visualizar");
        //jugador.add(mostrar);
        mostrar.setBounds(702,460,100,30);




    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
