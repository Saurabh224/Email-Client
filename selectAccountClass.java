/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailclient1;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.border.Border;

public class selectAccountClass extends JPanel implements ActionListener {
    JPanel sidePanel,mainPanel;
    JButton composeBt,inboxBt,sentBt,outboxBt,logoutBt,closeBt;
    JFrame frame;
    selectAccountClass()
    {
        frame=new JFrame("Welcome to your mail");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(getScreenWidth() - 100, getScreenHeight() - 100);
        sidePanel=makeSidePanel();
        frame.add(sidePanel);
        frame.setVisible(true);
        //new ComposeEmail();
    }
    private JPanel makeSidePanel()
    {
        sidePanel=new JPanel();
        sidePanel.setBounds(0,0,300,getScreenHeight());
        sidePanel.setLayout(null);
        Border border = BorderFactory.createLineBorder(Color.ORANGE);
        sidePanel.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        composeBt=new JButton("Compose");
        composeBt.setBounds(80,120,200,50);
        composeBt.addActionListener(this);
        sidePanel.add(composeBt);
        
        inboxBt=new JButton("Inbox");
        inboxBt.setBounds(80,200,200,50);
        inboxBt.addActionListener(this);
        sidePanel.add(inboxBt);
        
        sentBt=new JButton("Sent");
        sentBt.setBounds(80,280,200,50);
        sentBt.addActionListener(this);
        sidePanel.add(sentBt);
        
        outboxBt=new JButton("Outbox");
        outboxBt.setBounds(80,360,200,50);
        outboxBt.addActionListener(this);
        sidePanel.add(outboxBt);
        
        logoutBt=new JButton("Log Out");
        logoutBt.setBounds(80,440,200,50);
        logoutBt.addActionListener(this);
        sidePanel.add(logoutBt);
        
        closeBt=new JButton("Close");
        closeBt.setBounds(80,520,200,50);
        closeBt.addActionListener(this);
        sidePanel.add(closeBt);
        
        return sidePanel;
    }
    private static int getScreenHeight() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
    }
    private static int getScreenWidth() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
    }
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource()==composeBt)
        {
            new ComposeEmail();
        }
        else if(e.getSource()==inboxBt)
        {
            ReceiveEmail re=new ReceiveEmail();
            String host = "pop.gmail.com";// change accordingly
            String mailStoreType = "pop3";
            String username = "example@gmail.com";// change accordingly
            String password = "*********";// change accordingly
            mainPanel=re.check(host, mailStoreType, username, password);
            frame.add(mainPanel);
            System.out.println("kkk");
        }
        else if(e.getSource()==sentBt)
        {
            sentFolder sf=new sentFolder();
            try {
                mainPanel=sf.doit();
            } catch (MessagingException ex) {
                Logger.getLogger(selectAccountClass.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(selectAccountClass.class.getName()).log(Level.SEVERE, null, ex);
            }
            frame.add(mainPanel);
            System.out.println();
        }
        else if(e.getSource()==outboxBt)
        {
            
        }
        else if(e.getSource()==logoutBt)
        {
            
        }
        else if(e.getSource()==closeBt)
        {
            frame.dispose();
        }
    }
}
