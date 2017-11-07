package emailclient1;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class EmailClient {
    public static void main(String[] args) {
        new startFrame();
    }
    
}
class startFrame extends JFrame implements ActionListener
{
    JPanel newAccount,selectAccount;
    JButton newAccountBt,selectAccountBt,exitBt;
    JLabel background,heading;
    JFrame frame;
    startFrame()
    {
        frame = new JFrame("Welcome to Email Client");
        background=new JLabel(new ImageIcon("icon.jpg"));
        frame.setLayout(null);
        newAccountBt=new JButton("New account");
        newAccountBt.setBounds(100,100,200,50);
        frame.add(newAccountBt);
        newAccountBt.addActionListener(this);
        
        selectAccountBt=new JButton("Select account");
        selectAccountBt.setBounds(500,100,200,50);
        frame.add(selectAccountBt);
        selectAccountBt.addActionListener(this);
        
        exitBt=new JButton("Exit Email Client");
        exitBt.setBounds(300,200,200,50);
        frame.add(exitBt);
        exitBt.addActionListener(this);
        frame.setLayout(new BorderLayout());
        
        frame.add(background);
       
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(getScreenWidth() - 100, getScreenHeight() - 100);
        frame.setVisible(true);
         
    }
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource()==exitBt)
        {
            System.exit(0);
        }
        else if(e.getSource()==selectAccountBt)
        {
            new selectAccountClass();
        }
        else if(e.getSource()==newAccountBt)
        {
            new newAccountClass();
        }
    }
    private static int getScreenWidth() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
    }
    private static int getScreenHeight() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
    }
    
}