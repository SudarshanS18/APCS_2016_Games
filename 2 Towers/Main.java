import javax.swing.*; 
import java.awt.*;
import java.awt.EventQueue;
public class Main extends JFrame {
    public Main() 
    {
        initUI();
    }

    private void initUI()
    {

        JPanel pane = new Board();

        setSize(600, 600); 
        setResizable(false); 

        setTitle("2 Towers"); 
        Container con = this.getContentPane();

        pane.setBackground(Color.yellow); 
        con.add(pane);
        add(pane); 

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) 
    { 
        Main mFrame = new Main(); 
        mFrame.setVisible(true);
    }

}


