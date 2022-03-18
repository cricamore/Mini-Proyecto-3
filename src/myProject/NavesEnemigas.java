package myProject;
import javax.swing.*;
import java.awt.*;

public class NavesEnemigas extends JFrame implements Runnable {

    private JPanel panel;
    private AreasDeJuego posicionesEnemgias;
    private int tableroPrincipal[][] = new int[10][10];
    private boolean bTableroPrincipal[][] = new boolean[10][10];
    private ImageIcon water;

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

    public NavesEnemigas(){
        this.setTitle("Naves Enemigas");
        this.setLocationRelativeTo(null);
        this.setSize(500,500);

        water = new ImageIcon(getClass().getResource("resources/water.jpg"));

        posicionesEnemgias = new AreasDeJuego();

        posicionesEnemgias.ubicarNave(posicionesEnemgias.getTableroPrincipal(), tamano11);
        posicionesEnemgias.ubicarNave(posicionesEnemgias.getTableroPrincipal(),tamano12);
        posicionesEnemgias.ubicarNave(posicionesEnemgias.getTableroPrincipal(),tamano13);
        posicionesEnemgias.ubicarNave(posicionesEnemgias.getTableroPrincipal(),tamano14);
        posicionesEnemgias.ubicarNave(posicionesEnemgias.getTableroPrincipal(),tamano21);
        posicionesEnemgias.ubicarNave(posicionesEnemgias.getTableroPrincipal(),tamano22);
        posicionesEnemgias.ubicarNave(posicionesEnemgias.getTableroPrincipal(),tamano23);
        posicionesEnemgias.ubicarNave(posicionesEnemgias.getTableroPrincipal(),tamano31);
        posicionesEnemgias.ubicarNave(posicionesEnemgias.getTableroPrincipal(),tamano32);
        posicionesEnemgias.ubicarNave(posicionesEnemgias.getTableroPrincipal(),tamano4);


    }


    @Override
    public void run() {
        new NavesEnemigas();


    }

    /*public void ubicarNave(int tablero[][], int tamano){
        do{
            filaM=posicionesEnemgias.getFilaM();
            columnaM=posicionesEnemgias.getColumnaM();
            horizontalM=posicionesEnemgias.getHorizontalM();
        }while(!puedePonerNave(tablero,tamano,filaM,columnaM,horizontalM));
        int aFila=0, aColumna=0;
        if(horizontalM==1) aFila=1;
        else aColumna=1;
        for (int filaDos=filaM;filaDos<=filaM+(tamano-1)*aFila;filaDos++){
            for (int columnaDos=columnaM;columnaDos<=columnaM+(tamano-1)*aColumna;columnaDos++){
                tablero[filaDos][columnaDos]=tamano;
            }
        }
    }*/

    public void pintarTablero(Graphics g, int tablero[][], int x, int y, boolean bVisible[][]){

        for (int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(tablero[i][j]> 0 && tablero==posicionesEnemgias.getTableroPrincipal() && !bVisible[i][j]){
                        g.setColor(Color.GRAY);
                        g.fillRect(x+j*40,y+i*40,40,40);
                }
                g.setColor(Color.BLACK);
                g.drawRect(x+j*40,y+i*40,40,40);
            }
        }

    }

    public void paint(Graphics g){
        g.drawImage(water.getImage(),0,0,null);
        posicionesEnemgias.pintarTableroEnemigo(g, posicionesEnemgias.getTableroPrincipal(), 50,50, posicionesEnemgias.getBTableroPrincipal());
    }


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

    public boolean casillaEnTablero(int fila, int columna){
        if(fila<0) return false;
        if(columna<0) return false;
        if(fila>=10) return false;
        if(columna>=10) return false;
        return true;
    }


}
