import java.io.*;
import java.net.*;
class TCPWebClient24v2 {
 public static void main(String argv[]) throws Exception
 {
   String inputLine;
   URI urobj = new URI("https://www.umbc.edu");
   URL obj = urobj.toURL();
   HttpURLConnection con = (HttpURLConnection) obj.openConnection();
   con.setRequestMethod("GET");
   con.setRequestProperty("User-Agent","Mozilla/5.0");
   BufferedReader in = new BufferedReader(
        new InputStreamReader(con.getInputStream())); 
                while ((inputLine = in.readLine()) != null) 
                   System.out.println(inputLine);        
 }
}

