/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailclient1;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class sentFolder {

  public sentFolder() {}

  //
  // inspired by :
  // http://www.mikedesjardins.net/content/2008/03/using-javamail-to-read-and-extract/
  //
private static int getScreenHeight() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
    }
    private static int getScreenWidth() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
    }
  public static JPanel doit() throws MessagingException, IOException {
    Folder folder = null;
    Store store = null;
    JPanel jp;
    JTextArea ja=new JTextArea();
    String Msgs="";
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
      Properties props = System.getProperties();
      props.setProperty("mail.store.protocol", "imaps");

      Session session = Session.getDefaultInstance(props, null);
      // session.setDebug(true);
      store = session.getStore("imaps");
      store.connect("imap.gmail.com","gssstudies@gmail.com", "gsproducts96");
      folder = store.getFolder("[Gmail]/Sent Mail");
      /* Others GMail folders :
       * [Gmail]/All Mail   This folder contains all of your Gmail messages.
       * [Gmail]/Drafts     Your drafts.
       * [Gmail]/Sent Mail  Messages you sent to other people.
       * [Gmail]/Spam       Messages marked as spam.
       * [Gmail]/Starred    Starred messages.
       * [Gmail]/Trash      Messages deleted from Gmail.
       */
      folder.open(Folder.READ_WRITE);
      Message messages[] = folder.getMessages();
      System.out.println("No of Messages : " + folder.getMessageCount());
      System.out.println("No of Unread Messages : " + folder.getUnreadMessageCount());
      Msgs=Msgs+"No of Messages : " + folder.getMessageCount()+"\n";
      Msgs=Msgs+"No of Unread Messages : " + folder.getUnreadMessageCount()+"\n";
      for (int i=0; i < messages.length; ++i) {
        Msgs=Msgs+"MESSAGE #" + (i + 1) + ":"+"\n";
        Message msg = messages[i];
        /*
          if we don''t want to fetch messages already processed
          if (!msg.isSet(Flags.Flag.SEEN)) {
             String from = "unknown";
             ...
          }
        */
         Msgs=Msgs+"Subject: " + msg.getSubject()+"\n";
         Msgs=Msgs+"From: " + msg.getReplyTo()[0]+"\n"+"Text: " + msg.getContent().toString()+"\n";
        String from = "unknown";
        if (msg.getReplyTo().length >= 1) {
          from = msg.getReplyTo()[0].toString();
        }
        else if (msg.getFrom().length >= 1) {
          from = msg.getFrom()[0].toString();
        }
        String subject = msg.getSubject();
        //Msgs=Msgs+"Saving ... " + subject +" " + from+"\n";
        Msgs=Msgs+"----------------------------------------\n";
        // you may want to replace the spaces with "_"
        // the TEMP directory is used to store the files
        String filename = "c:/temp/" +  subject;
        //saveParts(msg.getContent(), filename);
        //msg.setFlag(Flags.Flag.SEEN,true);
        // to delete the message
        // msg.setFlag(Flags.Flag.DELETED, true);
      }
      ja.setText(Msgs);
    }
    finally {
      if (folder != null) { folder.close(true); }
      if (store != null) { store.close(); }
    }
    return jp;
  }

  public static void saveParts(Object content, String filename)
  throws IOException, MessagingException
  {
    OutputStream out = null;
    InputStream in = null;
    try {
      if (content instanceof Multipart) {
        Multipart multi = ((Multipart)content);
        int parts = multi.getCount();
        for (int j=0; j < parts; ++j) {
          MimeBodyPart part = (MimeBodyPart)multi.getBodyPart(j);
          if (part.getContent() instanceof Multipart) {
            // part-within-a-part, do some recursion...
            saveParts(part.getContent(), filename);
          }
          else {
            String extension = "";
            if (part.isMimeType("text/html")) {
              extension = "html";
            }
            else {
              if (part.isMimeType("text/plain")) {
                extension = "txt";
              }
              else {
                //  Try to get the name of the attachment
                extension = part.getDataHandler().getName();
              }
              filename = filename + "." + extension;
              System.out.println("... " + filename);
              out = new FileOutputStream(new File(filename));
              in = part.getInputStream();
              int k;
              while ((k = in.read()) != -1) {
                out.write(k);
              }
            }
          }
        }
      }
    }
    finally {
      if (in != null) { in.close(); }
      if (out != null) { out.flush(); out.close(); }
    }
  }
}