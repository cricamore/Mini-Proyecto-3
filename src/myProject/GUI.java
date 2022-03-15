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
    private AreasDeJuego jugador, oponente;
    private TableroPrincipal tableroPrincipal;
    private ImageIcon image;
    private Escucha escucha;
    private JButton mostrar;
    private static final String PATH = "resources/agua.jpeg/";


    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("Batalla Naval");
        //this.setSize(850,450);
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
        image = new ImageIcon(getClass().getResource(PATH));

        JLabel tableroPosicion = new JLabel("Tablero Posicion");


        jugador = new AreasDeJuego();
        add(jugador, BorderLayout.CENTER);
        jugador.setLayout(null);
        jugador.iniciarPartida();


        mostrar = new JButton("Visualizar");
        jugador.add(mostrar);
        mostrar.setBounds(702,460,100,30);



    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
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
