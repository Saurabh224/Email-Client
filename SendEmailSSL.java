/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailclient1;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

class SendEmailSSL {
    String username=null;
    String password=null;
    String rec=null;
    String msg=null;
    String sub=null;
    String attatch=null;
    SendEmailSSL(String su,String re,String mg,String att)
    {
        username="example@gmail.com";
        password="password";
        sub=su;
        rec=re;
        msg=mg;
        attatch=att;
        System.out.println(rec+"   "+msg);
    }
    public String sendr()
    {
        if(checkInternetConnection.check().equals("yes"))
        {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() 
                    {
                        protected PasswordAuthentication getPasswordAuthentication() 
                        {
                            return new PasswordAuthentication(username,password);
                        }
                    });

            try {

                    Message message = new MimeMessage(session);
                    BodyPart messageBodyPart = new MimeBodyPart();
                    message.setFrom(new InternetAddress(username));
                    message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(rec));
                    message.setSubject(sub);
                    message.setText(msg);
                    Multipart multipart = new MimeMultipart();
                    if(attatch!=null)
                    {
                        while(attatch.contains(","))
                        {
                            System.out.println(attatch);
                            String attatchTemp=attatch.substring(0,attatch.indexOf(","));
                            attatch=attatch.substring(attatch.indexOf(",")+1);
                            DataSource source = new FileDataSource(attatchTemp);
                            messageBodyPart.setDataHandler(new DataHandler(source));
                            messageBodyPart.setFileName(attatchTemp);
                            multipart.addBodyPart(messageBodyPart);
                            message.setContent(multipart);
                        }
                        DataSource source = new FileDataSource(attatch);
                        messageBodyPart.setDataHandler(new DataHandler(source));
                        messageBodyPart.setFileName(attatch);
                        multipart.addBodyPart(messageBodyPart);
                        message.setContent(multipart);
                        // Send the complete message parts
                    }
                    Transport.send(message);
                    System.out.println("Done");
                    return "sent successfully";

            } catch (Exception e) {
                System.out.println(e.getMessage());
                    return e.getMessage();
            }
        }
        else
        {
            outboxMsgs ot=new outboxMsgs(rec,sub,msg,attatch);
            ot.start();
            return "no internet will be sent";
        }
    }
}
