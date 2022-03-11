package myProject;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class TableroPosicion {
    private JPanel mainPanel = new JPanel(){
        // it draws an image if available
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            myPaint(g);
        };
    };
    private Image img=null;
    public static final int ROW_COUNT = 11;
    public static final int COL_COUNT = 11;
    private Map<String, JLabel> labelMap = new HashMap<String, JLabel>();
    private JTextArea textArea;
    private JLabel label;

    public TableroPosicion(Image img, int cellSideLength){
        this.img=img;
        mainPanel.setLayout(new GridLayout(11,11));
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        for(int row=0;row < ROW_COUNT;row++){
            for(int col=0;col<COL_COUNT;col++){
                String rowStr = String.valueOf((char)('A'+row-1));
                String colStr = String.valueOf(col);
                String name = "";
                String text = "";

                label = new JLabel("", SwingConstants.CENTER);

                if(col==0&&row!=0){
                    text = "" + rowStr;
                }else
                    if(row==0&&col!=0){
                        text=""+colStr;
                    }else
                        if(row!=0&&col!=0){
                            name = rowStr+""+colStr;
                            labelMap.put(name, label);
                        }
                label.setPreferredSize(new Dimension(cellSideLength, cellSideLength));
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                label.setText(text);
                label.setName(name);
                mainPanel.add(label);
            }
        }

    }

    @Override
    protected void myPaint(Graphics g){
        if(img != null){
            int w0 = mainPanel.getWidth();
            int h0 = mainPanel.getHeight();
            int x = w0/11;
            int y = h0/11;

            int iW= img.getWidth(null);
            int iH= img.getHeight(null);

            g.drawImage(img,x,y,w0,h0,0,0,iW,iH,null);
        }
    }




}
