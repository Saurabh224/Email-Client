/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailclient1;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class ReceiveEmail {

    private static int getScreenHeight() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
    }
    private static int getScreenWidth() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
    }
   public static JPanel check(String host, String storeType, String user,
      String password) 
   {
       JPanel jp =new JPanel();
          JTextArea ja=new JTextArea();
      try {

          jp=new JPanel();
        jp.setBounds(301,0,getScreenWidth()-300,getScreenHeight());
        jp.setLayout(null);
        Border border = BorderFactory.createLineBorder(Color.blue);
        jp.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(10, 10, 10, 10)));
      //create properties field
      ja.setBounds(100,100,500,500);
      
      JScrollPane sp2=new JScrollPane(ja);
        sp2.setBounds(100,100,500,500);
        jp.add(sp2);
      Properties properties = new Properties();

      properties.put("mail.pop3.host", host);
      properties.put("mail.pop3.port", "995");
      //properties.put("mail.pop3.starttls.enable", "true");
      Session emailSession = Session.getDefaultInstance(properties);
  
      //create the POP3 store object and connect with the pop server
      Store store = emailSession.getStore("pop3s");

      store.connect(host, user, password);

      //create the folder object and open it
      Folder emailFolder = store.getFolder("INBOX");
      emailFolder.open(Folder.READ_ONLY);

      // retrieve the messages from the folder in an array and print it
      Message[] messages = emailFolder.getMessages();
      String Msgs="";
      System.out.println("messages.length---" + messages.length);
      Msgs=Msgs+"messages.length---" + messages.length+"\n";
      
      for (int i = 0, n = messages.length; i < n; i++) {
         Message message = messages[i];
         Msgs=Msgs+"---------------------------------"+"\n"+"Email Number " + (i + 1)+"\n"+"Subject: " + message.getSubject()+"\n";
         Msgs=Msgs+"From: " + message.getFrom()[0]+"\n"+"Text: " + message.getContent().toString()+"\n";

      }
      ja.setText(Msgs);

      //close the store and folder objects
      emailFolder.close(false);
      store.close();
      System.out.println("complete");

      } catch (NoSuchProviderException e) {
         e.printStackTrace();
      } catch (MessagingException e) {
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return jp;
   }

}