package emailclient1;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ComposeEmail {

    ComposeEmail() {
        PostMark_GUI pg=new PostMark_GUI();
        pg.createAndShowGUI();
    }
    
}
class PostMark_GUI extends JPanel implements ActionListener {

    JPanel recipientTextPanel, textPanel, panelForTextFields, panelForRecipientFields, panelForSendButton,panelForAttatchList;
    JLabel recipientLabel, subjectLabel, addMoreRecipients;
    JLabel statusLabel;
    JTextField attatchList;
    JTextField recipientField, subjectField;
    JTextArea messageField, recipientField2;
    JButton SendButton,AttatchButton;
    JFrame frame;
    int y;
    
        
    SendEmailSSL client;
    String filePath=null;

    public PostMark_GUI() {
        super(new GridLayout(1,1));

        JTabbedPane tab1 = new JTabbedPane();
        JComponent panel1 = makeTab1();
        tab1.addTab("Send Single Email", panel1);
        tab1.setMnemonicAt(0, KeyEvent.VK_1);
        add(tab1);
    }
    private JComponent makeTab1() {
        // We create a bottom JPanel to place everything on.
        JPanel Tab1Panel = new JPanel();
      //Tab1Panel.setLayout(new GridLayout(1,1));
        Tab1Panel.setLayout(null);
        // Creation of a Panel to contain the JLabels
        textPanel = new JPanel();
        textPanel.setLayout(null);
        textPanel.setLocation(30, 35);
        textPanel.setSize(810, 40);
        Tab1Panel.add(textPanel);
        // Subject Label
        subjectLabel = new JLabel("Subject");
        subjectLabel.setLocation(0, 0);
        subjectLabel.setSize(70, 40);
        subjectLabel.setHorizontalAlignment(4);
        textPanel.add(subjectLabel);
        // Add more recipients Label
        addMoreRecipients = new JLabel("Add More Recipients");
        addMoreRecipients.setLocation(590, 0);
        addMoreRecipients.setSize(170, 40);
        addMoreRecipients.setHorizontalAlignment(4);
        textPanel.add(addMoreRecipients);
        //Add more recipients Panel container
        panelForRecipientFields = new JPanel();
        panelForRecipientFields.setLayout(null);
        panelForRecipientFields.setLocation(670, 80);
        panelForRecipientFields.setSize(300, 70);
        Tab1Panel.add(panelForRecipientFields);

        recipientField2 = new JTextArea();
        recipientField2.setLocation(0, 0);
        recipientField2.setSize(300,70);
        Border border = BorderFactory.createLineBorder(Color.gray);
        recipientField2.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        JScrollPane sp=new JScrollPane(recipientField2);
        sp.setBounds(0,0,300,70);
        panelForRecipientFields.add(sp);
        // TextFields Panel Container
        panelForTextFields = new JPanel();
        panelForTextFields.setLayout(null);
        panelForTextFields.setLocation(30, 40);
        panelForTextFields.setSize(650, 500);
        Tab1Panel.add(panelForTextFields);
        // subject Textfield
        subjectField = new JTextField(8);
        subjectField.setLocation(30, 40);
        subjectField.setSize(600, 30);
        panelForTextFields.add(subjectField);

        messageField = new JTextArea();
        messageField.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        messageField.setLocation(30, 80);
        messageField.setSize(600, 400);
        JScrollPane sp2=new JScrollPane(messageField);
        sp2.setBounds(30,80,600,400);
        panelForTextFields.add(sp2);

        panelForSendButton = new JPanel();
        panelForSendButton.setLayout(null);
        panelForSendButton.setLocation(175,550);
        panelForSendButton.setSize(500, 100);
        Tab1Panel.add(panelForSendButton);

        // Button for sending email
        SendButton = new JButton("Send");
        SendButton.setLocation(90,0);
        SendButton.setSize(80, 30);
        SendButton.addActionListener(this);
        panelForSendButton.add(SendButton);
        
        statusLabel=new JLabel();
        statusLabel.setLocation(170,0);
        statusLabel.setSize(350,30);
        panelForSendButton.add(statusLabel);
        
        panelForAttatchList=new JPanel();
        panelForAttatchList.setLocation(770,480);
        panelForAttatchList.setSize(300,300);
        Tab1Panel.add(panelForAttatchList);
        
        // Button for attatching email
        AttatchButton = new JButton("Attatch");
        AttatchButton.setLocation(0,0);
        AttatchButton.setSize(80, 30);
        AttatchButton.addActionListener(this);
        panelForAttatchList.add(AttatchButton);
        
        attatchList=new JTextField(25);
        attatchList.setBounds(770,480,50,30);
        //attatchList.setSize(50,30);
        panelForAttatchList.add(attatchList);
        
        Tab1Panel.setOpaque(true);
        return Tab1Panel;
    }
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == SendButton) {
            client = new SendEmailSSL(subjectField.getText(),recipientField2.getText(),messageField.getText(),filePath);
            String sta=client.sendr();
            if(sta.equals("sent successfully"))
            {
                statusLabel.setForeground(Color.green);
            }
            else
            {
                statusLabel.setForeground(Color.red);
            }
            statusLabel.setText(sta);
            frame.dispose();
        } 
        else if(e.getSource()==AttatchButton)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            if(filePath!=null)
            {
                filePath=filePath+","+selectedFile.getAbsolutePath();
            }
            else
            {
                filePath=selectedFile.getAbsolutePath();
            }
            System.out.println(filePath);
            attatchList.setForeground(Color.blue);
            attatchList.setText(filePath);
            }
        }
    }
    void createAndShowGUI() {

        frame = new JFrame("sending Email");

        PostMark_GUI demo = new PostMark_GUI();
        frame.add(new PostMark_GUI(), BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(getScreenWidth() - 100, getScreenHeight() - 100);
        frame.setVisible(true);
    }

    private static int getScreenWidth() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
    }

    private static int getScreenHeight() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
    }
}