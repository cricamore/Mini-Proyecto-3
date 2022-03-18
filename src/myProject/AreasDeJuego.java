package myProject;

import javax.swing.*;
import javax.swing.event.SwingPropertyChangeSupport;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.beans.PropertyChangeListener;
import java.util.*;

/**
 * Class AreasDeJuego es encarga de proyectar la ejecución del juego.
 * @author Cristian Montaño 202024223
 * @author Maicol Montenegro 202026993
 * @version v.1.0.0 date 17/03/2022
 */
public class AreasDeJuego extends JPanel{

    private ImageIcon water;
    private ImageIcon playa;
    private int estado=0;

    private int filaM,columnaM,horizontalM;

    private int tableroPosicion[][] = new int[10][10];
    private boolean bTableroPosicion[][] = new boolean[10][10];
    private int tableroPrincipal[][] = new int[10][10];
    private boolean bTableroPrincipal[][] = new boolean[10][10];
    private JButton verNavesEnemigas, quitarNavesEnemigas, empezar, ayuda;
    private JFrame flotaEnemiga;

    private int pFila=0;
    private int pColumna=0;
    private int pTamano=4;
    private int pHorizontal=0;
    private int count=0;
    private boolean posicionT, posicionF;


    private int tamano4=4;
    private int tamano31=3;
    private int tamano32=3;
    private int tamano21=2;
    private int tamano22=2;
    private int tamano23=2;
    private int tamano11=1;
    private int tamano12=1;
    private int tamano13=1;
    private int tamano14=1;

    /**
     * Verifica si la casilla esta en el tablero
     */
    public boolean casillaEnTablero(int fila, int columna){
        if(fila<0) return false;
        if(columna<0) return false;
        if(fila>=10) return false;
        if(columna>=10) return false;
        return true;
    }
    /**
     * Determina si se puede ubicar o no un barco en el tablero
     */
    public boolean puedePonerNave(int tab[][], int tam, int f, int c, int hor){
        int df=0,dc=0;
        if (hor==1) df=1;
        else dc=1;
        for (int c2=c;c2<=c+tam*dc;c2++){
            for (int f2=f;f2<=f+tam*df;f2++){
                if (!casillaEnTablero(f2, c2)){
                    return false;
                }
            }
        }
        for (int f2=f-1;f2<=f+1+df*tam;f2++){
            for (int c2=c-1;c2<=c+1+dc*tam;c2++){
                if (casillaEnTablero(f2,c2)){
                    if (tab[f2][c2]!=0){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    /**
     * Determina una ubicacion aleatoria y libre del tablero
     */
    public void ubicarNave(int tablero[][], int tamano){
        do{
            filaM=(int)(Math.random()*10);
            columnaM=(int)(Math.random()*10);
            horizontalM=(int)(Math.random()*2);
        }while(!puedePonerNave(tablero,tamano,filaM,columnaM,horizontalM));
        int aFila=0, aColumna=0;
        if(horizontalM==1) aFila=1;
        else aColumna=1;
        for (int filaDos=filaM;filaDos<=filaM+(tamano-1)*aFila;filaDos++){
            for (int columnaDos=columnaM;columnaDos<=columnaM+(tamano-1)*aColumna;columnaDos++){
                tablero[filaDos][columnaDos]=tamano;
            }
        }
    }
    /**
     * Ubica de forma aleatoria los barcos/flota del rival(maquina) en su tablero
     */
    public void iniciarPartida(){

        for (int i=0;i<10;i++){
            for (int j=0;j<10;j++){
                tableroPosicion[i][j]=0;
                tableroPrincipal[i][j]=0;
                bTableroPosicion[i][j]=false;
                bTableroPrincipal[i][j]=false;
            }
        }

        ubicarNave(tableroPrincipal,tamano11);
        ubicarNave(tableroPrincipal,tamano12);
        ubicarNave(tableroPrincipal,tamano13);
        ubicarNave(tableroPrincipal,tamano14);
        ubicarNave(tableroPrincipal,tamano21);
        ubicarNave(tableroPrincipal,tamano22);
        ubicarNave(tableroPrincipal,tamano23);
        ubicarNave(tableroPrincipal,tamano31);
        ubicarNave(tableroPrincipal,tamano32);
        ubicarNave(tableroPrincipal,tamano4);
    }

    /**
     * Rectifica que una nave este en la posición correcta dentro del tablero
     */
    public void rectificarUbicacionNave(){
        int pDFila=0;
        int pDColumna=0;
        if(pHorizontal==1){
            pDFila=1;
        }else {
            pDColumna=1;
        }
        if(pFila+pTamano*pDFila>=10){
            pFila=9-pTamano*pDFila;
        }
        if(pColumna+pTamano*pDColumna>=10){
            pColumna=9-pTamano*pDColumna;
        }
    }
    /**
     * Si no se puede ubicar el barco, determina otra posicion donde se pueda ubitar el barco en el tablero
     */
    public boolean puedePonerNave(){
        return puedePonerNave(tableroPosicion,pTamano, pFila, pColumna, pHorizontal);
    }

    /**
     * Deternima cuando se ha ganado
     */
    public boolean victoria(int tablero[][], boolean bTablero[][]){
        for (int i=0;i<10;i++){
            for (int j=0;j<10;j++){
                if(bTablero[i][j]==false && tablero[i][j]!=0){
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Realiza los disparos del oponente (maquina)
     */
    public void disparoOponente(){
        int fila, columna;
        do{
            fila=(int)(Math.random()*10);
            columna=(int)(Math.random()*10);
        }while(bTableroPosicion[fila][columna]==true);
        bTableroPosicion[fila][columna]=true;
    }


    public AreasDeJuego(){
        water = new ImageIcon(getClass().getResource("resources/water.jpg"));
        playa = new ImageIcon(getClass().getResource("resources/ships.jpg"));
        verNavesEnemigas = new JButton("Ver posiciones");
        verNavesEnemigas.setBounds(600,460,140,30);
        quitarNavesEnemigas = new JButton("Quitar posiciones");
        quitarNavesEnemigas.setBounds(750,460,140,30);
        ayuda = new JButton("INSTRUCCIONES");
        ayuda.setBounds(400,300,200,100);
        ayuda.setBackground(new Color(75, 37, 10));
        ayuda.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        ayuda.setForeground(new Color(43, 187, 120, 174));

        flotaEnemiga = new JFrame();
        flotaEnemiga.setTitle("Posicion de naves enemigas");
        flotaEnemiga.setBounds(0,0,500,500);

        //setBounds(0,0,1000,550);
        setPreferredSize(new Dimension(1000,500));

        addMouseListener(
                new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if(e.getModifiers() == MouseEvent.BUTTON3_MASK && estado==1){
                            pHorizontal=1-pHorizontal;
                            rectificarUbicacionNave();
                            repaint();
                            return;
                        }
                        if(estado==0) {
                            estado=1;
                            iniciarPartida();
                            repaint();
                        }

                        if(estado==1) {
                            if(puedePonerNave()) {
                                int pDFila = 0;
                                int pDColumna = 0;
                                if (pHorizontal == 1) {
                                    pDFila = 1;
                                } else {
                                    pDColumna = 1;
                                }
                                for (int m = pFila; m <= pFila + (pTamano - 1) * pDFila; m++) {
                                    for (int n = pColumna; n <= pColumna + (pTamano - 1) * pDColumna; n++) {
                                        tableroPosicion[m][n] = pTamano;
                                    }
                                }
                                if(e.getClickCount()==1 || e.getClickCount()==2){
                                    count++;
                                    if(count==1 || count==2){
                                        pTamano=3;
                                    }else if(count== 3||count==4||count==5){
                                        pTamano=2;
                                    }else if(count==6||count==7||count==8||count==9){
                                        pTamano=1;
                                    }else {
                                        pTamano--;
                                    }
                                }
                                if (pTamano == 0) {
                                    estado = 2;
                                    repaint();
                                }
                            }
                        }else if (estado==2){
                            int fila = (e.getY()-50)/40;
                            int columna = (e.getX()-550)/40;
                            if (fila != pFila || columna != pColumna) {
                                pFila = fila;
                                pColumna = columna;
                                if(casillaEnTablero(fila,columna)) {
                                    if (bTableroPrincipal[fila][columna] == false) {
                                        bTableroPrincipal[fila][columna] = true;
                                        repaint();
                                        if(victoria(tableroPrincipal, bTableroPrincipal)){
                                            JOptionPane.showMessageDialog(null, "Has ganado");
                                            System.exit(0);
                                        }
                                        disparoOponente();
                                        repaint();
                                        if(victoria(tableroPosicion, bTableroPosicion)){
                                            JOptionPane.showMessageDialog(null, "Has perdido");
                                            System.exit(0);
                                        }
                                        repaint();
                                    }
                                }
                            }
                        }
                    }
                }
        );
        addMouseMotionListener(
                new MouseMotionAdapter() {
                    @Override
                    public void mouseMoved(MouseEvent e) {
                        int x=e.getX();
                        int y=e.getY();
                        if (estado==1 && x>=50 && y>=50 && x<50+40*10 && y<50+40*10){
                            int f=(y-50)/40;
                            int c=(x-50)/40;
                            if (f!=pFila || c!=pColumna){
                                pFila=f;
                                pColumna=c;
                                rectificarUbicacionNave();
                                repaint();
                            }
                        }
                    }
                }
        );

    }


    /**
     * Determina si los disparos han hundido completamente el barco
     */
    public boolean hundido(int tablero[][], int valor, boolean bVisible[][]){
        for (int i=0;i<10;i++){
            for (int j=0;j<10;j++){
                if (bVisible[i][j]==false){
                    if(tablero[i][j]==valor){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    /**
     * Realiza los disparos del oponente (maquina)
     */
    public void pintarTablero(Graphics g, int tablero[][], int x, int y, boolean bVisible[][]){

        for (int i=0;i<10;i++){
            for (int j=0;j<10;j++){
                if(tablero[i][j]> 0 && bVisible[i][j]){
                        g.setColor(Color.YELLOW);
                        if (hundido(tablero, tablero[i][j], bVisible)) {
                            g.setColor(Color.RED);
                        }
                        g.fillRect(x + j * 40, y + i * 40, 40, 40);

                }
                if(tablero[i][j]==0 && bVisible[i][j]){
                    g.setColor(Color.cyan);
                    g.fillRect(x+j*40,y+i*40,40,40);
                }
                if(tablero[i][j]> 0 && tablero==tableroPosicion && !bVisible[i][j]){
                    g.setColor(Color.GRAY);
                    g.fillRect(x+j*40,y+i*40,40,40);
                }
                if(tablero[i][j]> 0 && tablero==tableroPrincipal && !bVisible[i][j]){
                    if(posicionT==true){
                        posicionT=true;
                        g.setColor(Color.GRAY);
                        g.fillRect(x+j*40,y+i*40,40,40);
                        verNavesEnemigas.setVisible(false);
                        verNavesEnemigas.setEnabled(false);
                    }
                }
                if(tablero[i][j]> 0 && tablero==tableroPrincipal && !bVisible[i][j]){
                    if(posicionF==true){
                        posicionT=false;
                        quitarNavesEnemigas.setVisible(false);
                        quitarNavesEnemigas.setVisible(false);
                    }

                }
                g.setColor(Color.BLACK);
                g.drawRect(x+j*40,y+i*40,40,40);

                if(estado==1 && tablero==tableroPosicion) {
                    int pDFila = 0;
                    int pDColumna = 0;
                    if (pHorizontal == 1) {
                        pDFila = 1;
                    } else {
                        pDColumna = 1;
                    }
                    if (i >= pFila && j >= pColumna && i <= pFila + (pTamano-1) * pDFila && j <= pColumna + (pTamano-1) * pDColumna) {
                        g.setColor(Color.GREEN);
                        g.fillRect(x+j*40,y+i*40,40,40);
                    }
                }
            }
        }
    }

    /**
     * Pinta el tablero enemigo en una nueva ventana
     * @param g
     * @param tablero
     * @param x
     * @param y
     * @param bVisible
     */
    public void pintarTableroEnemigo(Graphics g, int tablero[][], int x, int y, boolean bVisible[][]){
        for (int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(tablero[i][j]> 0 && tablero==tableroPrincipal && !bVisible[i][j]){
                    g.setColor(Color.GRAY);
                    g.fillRect(x+j*40,y+i*40,40,40);
                }
                g.setColor(Color.BLACK);
                g.drawRect(x+j*40,y+i*40,40,40);
            }
        }
    }


    boolean bPrimeraVez=true;
    public void paint(Graphics g){

        if(bPrimeraVez) {
            g.drawImage(water.getImage(), 0, 0, 1,1,this);
            g.drawImage(playa.getImage(), 0, 0, 1,1,this);
            bPrimeraVez = false;
        }
        if(estado==0) {
            g.drawImage(playa.getImage(),0,0,this);
            g.drawRect(190,170,650,70);
            g.setColor(new Color(23, 77, 23));
            g.fillRect(190,170,650,70);


            g.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
            g.setColor(Color.ORANGE);
            g.drawString("¡Bienvenido al Juego de batalla naval!", 300, 200);
            g.drawString("Presione el botón click izquierdo para iniciar la partida", 200, 230);
            add(ayuda);

        }else {

            g.drawImage(water.getImage(), 0, 0, this);
            pintarTablero(g, tableroPosicion, 50, 50, bTableroPosicion);
            pintarTablero(g, tableroPrincipal, 550, 50, bTableroPrincipal);
            ayuda.setVisible(false);
            ayuda.setEnabled(false);

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
            g.setColor(Color.ORANGE);
            g.drawString("Tablero Posicion", 50, 35);

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
            g.setColor(Color.ORANGE);
            g.drawString("Tablero Principal", 550, 35);

            add(verNavesEnemigas);
            verNavesEnemigas.repaint();
            add(quitarNavesEnemigas);
            quitarNavesEnemigas.repaint();

        }

    }


    public JButton getVerNavesEnemigas(){
        return verNavesEnemigas;
    }

    public JButton getAyuda(){
        return ayuda;
    }

    public JFrame getFlotaEnemiga(){
        return flotaEnemiga;
    }

    public JButton getQuitarNavesEnemigas(){
        return quitarNavesEnemigas;
    }

    public int getEstado(){
        return estado;
    }


    public int[][] getTableroPrincipal(){
        return tableroPrincipal;
    }


    public boolean[][] getBTableroPrincipal(){
        return bTableroPrincipal;
    }


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AreasDeJuego().setVisible(true);
            }
        });
    }

    public boolean getPosicionT(){
        return posicionT=true;
    }

    public boolean getPosicionF(){
        return posicionF=true;
    }

}
