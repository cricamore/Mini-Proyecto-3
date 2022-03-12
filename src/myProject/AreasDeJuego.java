package myProject;

import javax.swing.*;
import javax.swing.event.SwingPropertyChangeSupport;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

public class AreasDeJuego extends JPanel{
    private ImageIcon img;
    public static final int FILA = 11;
    public static final int COLUMNA = 11;
    public static final String MOUSE_PRESSED = "Mouse Pressed";
    private Map<String, JLabel> labelMap = new HashMap<String, JLabel>();
    private SwingPropertyChangeSupport propChangeSupport = new SwingPropertyChangeSupport(this);
    private JTextArea textArea;
    private String selectedCellName;
    private JLabel selectedLabel;


    public AreasDeJuego(ImageIcon img, int cellSideLength){
        this.img = img;
        setLayout(new GridLayout(11, 11));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        for(int filas=0; filas < FILA; filas++){
            for(int columnas=0; columnas<COLUMNA;columnas++){
                String rowStr = String.valueOf((char)('A'+filas-1));
                String colStr = String.valueOf(columnas);
                String name = "";
                String text = "";

                // create JLabel to add to grid
                JLabel label = new JLabel("", SwingConstants.CENTER);

                // text to display if label is a row header at col 0
                if (columnas == 0 && filas != 0) {
                    text = "" + rowStr;
                } else

                    // text to display if label is a col header at row 0
                    if (filas == 0 && columnas != 0) {
                        text = "" + colStr;
                    } else

                        // name to give to label if label is on the grid and not a
                        // row or col header
                        if (filas != 0 && columnas != 0) {
                            name = rowStr + " " + colStr;
                            labelMap.put(name, label);
                        }
                label.setText(text);
                label.setName(name);
                label.setPreferredSize(new Dimension(cellSideLength, cellSideLength));
                label.setBorder(BorderFactory.createLineBorder(Color.black));
                add(label);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        if (img!=null){
            int w0 = getWidth();
            int h0 = getHeight();
            int x = w0 / 11;
            int y = h0 / 11;

            int iW = img.getIconWidth();
            int iH = img.getIconHeight();

            g.drawImage(img.getImage(), x, y, w0, h0, 0, 0, iW, iH, null);
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propChangeSupport.addPropertyChangeListener(listener);
    }







}
