
package emailclient1;

import java.io.*;
import java.util.Scanner;

public class outboxMsgs extends Thread
{
    SendEmailSSL client;
    String recipient,subject,message,attatch;
    outboxMsgs(String rec,String sub,String msg,String att)
    {
        recipient=rec;
        subject=sub;
        message=msg;
        attatch=att;
        try
        {
            Scanner sc=new Scanner(new BufferedReader(new FileReader("outboxMessages.txt")));
            PrintWriter fos=new PrintWriter("outboxMsgs.txt");
            String content="notSent     "+recipient+"     "+attatch+"     "+subject+"     "+message;
            fos.println(content);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void run()
    {
        while(true)
        {
            if(checkInternetConnection.check().equals("yes"))
            {
                break;
            }
        }
        client = new SendEmailSSL(subject,recipient,message,attatch);
        String sta=client.sendr();
        if(sta.equals("sent successfully"))
        {
            String content="notSent     "+recipient+"     "+attatch+"     "+subject+"     "+message;
            try
            {
                Scanner sc=new Scanner(new BufferedReader(new FileReader("outboxMessages.txt")));
                PrintWriter fos=new PrintWriter("outboxMsgs.txt");
                while(sc.hasNext())
                {
                    if(sc.nextLine().equals(content))
                    {
                        
                    }
                }
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
}
