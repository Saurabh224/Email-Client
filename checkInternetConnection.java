
package emailclient1;

import java.net.URL;
import java.net.URLConnection; 

public class checkInternetConnection {
    public static String check()
    {
        try 
        {
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();   
            return "yes";
        }
        catch (Exception e)
        {
            return "no";     
        } 
    }
}
