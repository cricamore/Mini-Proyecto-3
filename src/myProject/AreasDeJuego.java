package myProject;

import javax.swing.*;
import javax.swing.event.SwingPropertyChangeSupport;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.beans.PropertyChangeListener;
import java.util.*;


public class AreasDeJuego extends JPanel implements Runnable{
    private int estado;
    private boolean rodar;
    private int tableroPosicion[][] = new int[10][10];
    private boolean bTableroPosicion[][] = new boolean[10][10];
    private int tableroPrincipal[][] = new int[10][10];

    ImageIcon agua;
    private int pFila=0;
    private int pColumna=0;
    private int pTamano=5;
    private int pHorizontal=0;


    public AreasDeJuego(){

        ImageIcon i = new ImageIcon() {
        };
        agua = new ImageIcon(getClass().getResource("resources/water.jpg"));

        setPreferredSize(new Dimension(1000,500));


        addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int pDFila = 0;
                        int pDColumna = 0;
                        if (pHorizontal == 1) {
                            pDFila = 1;
                        } else {
                            pDColumna = 1;
                        }
                        for(int m=pFila;m<=pFila+pTamano*pDFila;m++) {
                            for(int n=pColumna;n<=pColumna+pTamano*pDColumna;n++) {
                                tableroPosicion[n][m]=pTamano;
                            }
                        }
                    }
                }
        );
        addMouseMotionListener(
                new MouseMotionAdapter() {
                    @Override
                    public void mouseMoved(MouseEvent e) {

                        int x = e.getX();
                        int y = e.getY();
                        if (estado == 0 && x >= 50 && y >= 50 && x < 50 + 40 * 10 && y < 50 + 40 * 10) {
                            int fila = (y - 50) / 40;
                            int columna = (x - 50) / 40;
                            if (fila != pFila || columna != pColumna) {
                                pFila = fila;
                                pColumna = columna;
                                int pDFila=0;
                                int pDColumna=0;
                                if(pHorizontal==1){
                                    pDFila=1;
                                }else {
                                    pDColumna=1;
                                }
                                if(pFila+pTamano*pDFila>=10){
                                    pFila=10-pTamano*pDFila;
                                }
                                if(pColumna+pTamano*pDColumna>=10){
                                    pColumna=10-pTamano*pDColumna;
                                }
                                repaint();
                            }

                        }
                    }
                });

    }




    public boolean casillaEnTablero(int fila, int columna){
        if(fila<0) return false;
        if(columna<0) return false;
        if(fila>=10) return false;
        if(columna>=10) return false;
        return true;
    }

    public boolean verificarUbicacion(int tablero[][], int tamano, int fila, int columna, int horizontal){
        int aFila=0, aColumna=0;
        if(horizontal==1) aFila=1;
        else aColumna=1;
        for (int columnaDos=columna;columnaDos<=columna+tamano*aColumna;columnaDos++){
            for (int filaDos=fila;filaDos<=fila+tamano*aFila;filaDos++){
                if(!casillaEnTablero(filaDos,columnaDos)){
                    return false;
                }
            }
        }
        for (int filaDos=fila-1;filaDos<=fila+1+aFila*tamano;filaDos++){
            for(int columnaDos=columna-1;columnaDos<=columna+1+aColumna*tamano;columnaDos++){
                if(casillaEnTablero(filaDos,columnaDos)){
                    if(tablero[filaDos][columnaDos]!=0){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void ubicarNave(int tablero[][], int tamano){
        int fila, columna, horizontal;
        do{
            fila=(int)(Math.random()*10);
            columna=(int)(Math.random()*10);
            horizontal=(int)(Math.random()*2);
        }while(!verificarUbicacion(tablero,tamano,fila,columna,horizontal));
        int aFila=0, aColumna=0;
        if(horizontal==1) aFila=1;
        else aColumna=1;
        for (int filaDos=fila;filaDos<=fila+(tamano-1)*aFila;filaDos++){
            for (int columnaDos=columna;columnaDos<=columna+(tamano-1)*aColumna;columnaDos++){
                tablero[filaDos][columnaDos]=tamano;
            }
        }
    }

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

    public void pintarTablero(Graphics g, int tablero[][], int x, int y){
        for (int i=0;i<10;i++){
            for (int j=0;j<10;j++){
                if(tablero[i][j]> 0){
                    g.setColor(Color.RED);
                    g.fillRect(x+j*40,y+i*40,40,40);
                }
                g.setColor(Color.BLACK);
                g.drawRect(x+j*40,y+i*40,40,40);

                if(tablero==tableroPosicion) {
                    int pDFila = 0;
                    int pDColumna = 0;
                    if (pHorizontal == 1) {
                        pDFila = 1;
                    } else {
                        pDColumna = 1;
                    }
                    if (i >= pFila && j >= pColumna && i <= pFila + pTamano * pDFila && j <= pColumna + pTamano * pDColumna) {
                        g.setColor(Color.GREEN);
                        g.fillRect(x+j*40,y+i*40,40,40);
                    }
                }
            }
        }
    }


    public void paint(Graphics g){
        if(estado==0) {
            g.setFont(new Font(Font.DIALOG,Font.BOLD,17));
            g.setColor(Color.BLACK);
            g.drawString("Tablero Posicion", 50, 35);

            g.setFont(new Font(Font.DIALOG,Font.BOLD,17));
            g.setColor(Color.BLACK);
            g.drawString("Tablero Principal", 550, 35);

            g.drawImage(agua.getImage(),0,0,this);
            g.setColor(Color.RED);
            pintarTablero(g, tableroPosicion, 50, 50);
            pintarTablero(g, tableroPrincipal, 550, 50);

        }

    }

    public int getEstado() {
        return estado;
    }

    public void iniciarPartida(){

        for (int i=0;i<10;i++){
            for (int j=0;j<10;j++){
                tableroPosicion[i][j]=0;
                tableroPrincipal[i][j]=0;
                bTableroPosicion[i][j]=false;
            }
        }
        ubicarNave(tableroPrincipal,1);
        ubicarNave(tableroPrincipal,1);
        ubicarNave(tableroPrincipal,1);
        ubicarNave(tableroPrincipal,2);
        ubicarNave(tableroPrincipal,2);
        ubicarNave(tableroPrincipal,2);
        ubicarNave(tableroPrincipal,3);
        ubicarNave(tableroPrincipal,3);
        ubicarNave(tableroPrincipal,4);


    }

    public int getPFila() {
        return pFila;
    }



    public int getPColumna(){
        return pColumna;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AreasDeJuego().setVisible(true);
            }
        });
    }

    @Override
    public void run() {

    }
}
