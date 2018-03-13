import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;

import javax.imageio.ImageIO;

class Client {
ServerSocket serverSocket;
    Socket socket;
    public Client() {
        
        try {
        serverSocket = new ServerSocket(8001);
             	socket = serverSocket.accept();
             	
                	BufferedImage img = ImageIO.read(new File("tiger.jpeg"));
                    ImageIO.write(img, "JPEG", socket.getOutputStream());
                 String q, w;
        w = HiddenMark1.read("tiger1.jpeg");
        System.out.println(w);
        PrintWriter out =
       new PrintWriter(socket.getOutputStream(), true);
       out.flush();
        String str="Access Granted";
        String str1="Access Denied";
        out.println(str);
        
        
        if(w=="saurav")
        {
        	out.println(str);
        }
        else
        {
        	out.println(str1);
        }
                    
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.err.println(e);
        }
    }
}
class HiddenMark1 
{
    
static String read(String img)
{
 try
 {
    File fileImg = new File(img);
    BufferedImage bImg = ImageIO.read(fileImg);
    //matrix like data structure that holds the pixels of the image
    Raster raster = bImg.getData();
    
    String result = "";
    int w, h;
    int x, y;
    int r,g,b;
    int i, len;
    int current;
    int bits;
    
    
    w = raster.getWidth();
    h = raster.getHeight();
    i =-1 ;//flag
    len = 0;//flag value
    
    for(y =0; y < h  && i < len; y++)
    {
        for(x =0 ; x < w && i < len; x++)
        {
            r = raster.getSample(x, y, 0);//red band
            g = raster.getSample(x, y, 1);//green band
            b = raster.getSample(x, y, 2);//blue band
    
            //idea is to fetch
            //3 lsb of r, 3 lsb of g and 2 lsb of b
            
            r = r & 0x7;
            g = g & 0x7;
            b = b & 0x3;
            
            current = 0;
            current = (current | r) <<3;
            current = (current | g) <<2;
            current = current | b;
            
            if(len== 0)
            {
                if((char)current == ',')
                {
                    len = Integer.parseInt(result);
                    result = "";
                    i = 0;
                }
                else
                    result = result  +(char) current;
            }
            else
            {
                result = result + (char) current;
                i++;
            }
            
            
        }//for(x
    }//for(y
    
    return result;
 }
 catch(Exception ex)
 {
    return null;
 }
 
}

}
public class skcli
{
    public static void main(String[] args) 
    {
        
        new Client();
        
        
    }
}

