package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * This class is used as a view AreasDeJuego Class
 * @autor Cristian Camilo Montaño Rentería cristian.camilo.montano@correounivalle.edu.co
 * @autor Maicol Jair Ordoñez Montenegro maicol.ordonez@correounivalle.edu.co
 * @version v.1.0.0 date:17/03/2022
 */
public class GUI extends JFrame {

    private Header headerProject;
    private AreasDeJuego jugador;
    private NavesEnemigas navesEnemigas;
    private Escucha escucha;
    private JButton mostrar;
    private static final String PATH = "resources/agua.jpeg/";
    public static final String INSTRUCCIONES = "El juego consiste en una batalla naval en la cual deberás intentar derribar\n"
            +"toda la flota de naves enemigas para ganar.\n"
            +"\nDispondrás de 10 naves: 1 portaaviones, 2 submarinos, 3 destructores y 4 fragatas.\n"
            +"\nAl iniciar la partida deberás ubicar tu flota. PRESIONA EL BOTÓN CLICK DERECHO\n"
            +"PARA CAMBIAR DE HORIZONTAL A VERTICAL.\n"
            +"\nLuego de ubicar todas tus naves, procederás a dispararle al oponente para intentar derribar toda su flota.\n"
            +"El color amarillo en el Tablero Principal significa que has tocado una nave;\n"
            +"el color azul, que has disparado al agua; y el rojo, que has undido una nave completa.\n"
            +"\nSi logras derribar toda su flota, resultarás ganador; si el oponente derriba tu flota primero, resultarás perdedor.";



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
        escucha = new Escucha();



        jugador = new AreasDeJuego();
        add(jugador, BorderLayout.SOUTH);
        jugador.setLayout(null);
        navesEnemigas = new NavesEnemigas();

        jugador.getAyuda().addActionListener(escucha);


        jugador.getVerNavesEnemigas().addActionListener(escucha);
        jugador.getQuitarNavesEnemigas().addActionListener(escucha);

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
            if(e.getSource()==jugador.getAyuda()){
                JOptionPane.showMessageDialog(null, INSTRUCCIONES);
            }


            if(e.getSource()==jugador.getVerNavesEnemigas()){
                jugador.getPosicionT();
                //navesEnemigas.setVisible(true);
            }else if(e.getSource()==jugador.getQuitarNavesEnemigas()){
                jugador.getPosicionF();
            }


        }

    }
}
