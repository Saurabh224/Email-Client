
package emailclient1;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class newAccountClass extends JFrame implements ActionListener{
    JFrame frame;
    JButton validate;
    JTextField user,pass;
    JLabel userLabel,passLabel;
     private static int getScreenHeight() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
    }
    private static int getScreenWidth() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
    }
    newAccountClass()
    {
        frame = new JFrame("Create Email");
        frame.setLayout(null);
        
        user =new JTextField(200);
        user.setBounds(250,100,200,30);
        pass=new JTextField(200);
        pass.setBounds(250,200,200,30);
        userLabel=new JLabel("USERNAME");
        userLabel.setBounds(175,100,75,30);
        passLabel=new JLabel("PASSWORD");
        passLabel.setBounds(175,200,75,30);
        validate=new JButton("validate");
        validate.setBounds(250,300,150,50);
        validate.addActionListener(this);
        frame.add(user);
        frame.add(userLabel);
        frame.add(pass);
        frame.add(passLabel);
        frame.add(validate);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(getScreenWidth() - 100, getScreenHeight() - 100);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==validate)
        {
            
        }
    }
}
