import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class CalcView extends JFrame implements ActionListener {
    
    JPanel[] row = new JPanel[5];
    JButton[] button = new JButton[16];
    String[] buttonString = {"7", "8", "9", "+",
        "4", "5", "6", "-",
        "1", "2", "3", "*",
        "0", ".", "/", "="};
    int[] dimW = {300,45,100,90};
    int[] dimH = {35, 40};
    Dimension displayDimension = new Dimension(dimW[0], dimH[0]);
    Dimension regularDimension = new Dimension(dimW[1], dimH[1]);
    Dimension rColumnDimension = new Dimension(dimW[2], dimH[1]);
    Dimension zeroButDimension = new Dimension(dimW[3], dimH[1]);
    JTextArea display = new JTextArea(1,20);
    Font font = new Font("Times new Roman", Font.BOLD, 14);
    
    CalcView() {
        super("Calculator");
        setDesign();
        setSize(380, 250);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GridLayout grid = new GridLayout(5,5);
        setLayout(grid);
        
        FlowLayout f1 = new FlowLayout(FlowLayout.CENTER);
        FlowLayout f2 = new FlowLayout(FlowLayout.CENTER,1,1);
        for(int i = 0; i < 5; i++)
            row[i] = new JPanel();
        row[0].setLayout(f1);
        for(int i = 1; i < 5; i++)
            row[i].setLayout(f2);
        
        for(int i = 0; i < 16; i++) {
            button[i] = new JButton();
            button[i].setText(buttonString[i]);
            button[i].setFont(font);
            button[i].addActionListener(this);
        }
        
        display.setFont(font);
        display.setEditable(false);
        display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        display.setPreferredSize(displayDimension);
        for(int i = 0; i < 16; i++)
            button[i].setPreferredSize(regularDimension);
        
        row[0].add(display);
        
        for (int k = 0; k < 16; k++) {
            if (k / 4 == 0) row[1].add(button[k]);
            else if (k / 4 == 1) row[2].add(button[k]);
            else if (k / 4 == 2) row[3].add(button[k]);
            else row[4].add(button[k]);
        }

        for (int i = 0; i < 5; i++) {
            add(row[i]);
        }
        
        setVisible(true);
    }
    
    public final void setDesign() {
        try {
            UIManager.setLookAndFeel(
                                     "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception e) {   
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
    }
    
    public static void main(String[] arguments) {
        CalcView c = new CalcView();
    }
}

