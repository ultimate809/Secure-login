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
class faltu
{
	static String str;
	static void set_string(String s)
	{
		str=s;
	}
	static String give_string()
	{
		return str;
	}
	static int fun()
	{
		return 4;
	}
}

class server {
    
    
    public server() {
        
                try {
                Socket socket = new Socket("127.0.0.1", 8001);
            BufferedImage image = ImageIO.read(socket.getInputStream());
            File outputfile = new File("tiger1.png");
			ImageIO.write(image, "png", outputfile);
			
			BufferedReader input =
            new BufferedReader(new InputStreamReader(socket.getInputStream()));
 String str = input.readLine();
 String str1 = input.readLine();
			faltu.set_string(str1);
			
			 System.out.println("dfs");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
    }
    
}
class HiddenMark 
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
static String write(String img, String mark)
{
 try
 {
    File fileImg = new File(img);
    BufferedImage bImg = ImageIO.read(fileImg);
    //matrix like data structure that holds the pixels of the image
    WritableRaster wraster = bImg.getRaster();
    
    //prepare the mark 
    //mark : computer ---> 8,computer
    mark = mark.length() + "," + mark;

    int w, h;
    int x, y;
    int r,g,b;
    int i;
    char current;
    int bits;
    
    w = wraster.getWidth();
    h = wraster.getHeight();
    i =0 ;
    
    for(y =0; y < h && i < mark.length(); y++)
    {
        for(x =0 ; x < w && i < mark.length(); x++)
        {
            r = wraster.getSample(x, y, 0);//red band
            g = wraster.getSample(x, y, 1);//green band
            b = wraster.getSample(x, y, 2);//blue band
    
            //idea is to overwrite 
            //3 lsb of r, 3 lsb of g and 2 lsb of b
            //say : r 11010001
            
            r = r & (~0x7);
            g = g & (~0x7);
            b = b & (~0x3);
            
            current = mark.charAt(i++);
            //break current into 3 bits 3 bits 2 bits
            bits = (current >> 5 ) & 0x7;
            r = r | bits;
            bits = (current >> 2 ) & 0x7;
            g = g | bits;
            bits = current & 0x3;
            b = b | bits;
            
            //update the raster
            wraster.setSample(x, y, 0, r);
            wraster.setSample(x, y, 1, g);
            wraster.setSample(x, y, 2, b);
            
        }//for(x
    }//for(y
    
    //update the bImg with the new raster
    bImg.setData(wraster);
    
    //save back the bufferedImage
    String newName = getNewName(img);
    File target = new File("tiger.png");
    
    ImageIO.write(bImg, "PNG", target);
    return "tiger.png";
 }
 catch(Exception ex)
 {
    return null;
 }
 
}

private static String getNewName(String fn)
{//fn : d:/images/tiger.png
    File f = new File(fn);
    String parent = f.getParent();//d:/images
    String name = "copy_" + f.getName();//copy_tiger.png
    return parent + "/" + name;
}


}
public class skser
{
    public static void main(String[] args) 
    {
             
        
        String q="q", w;
        
        q= HiddenMark.write("tiger.jpeg", args[0]);
        //System.out.println(q);
        //System.out.println("f");
       
        server s=new server();
        System.out.println(faltu.give_string());
    }
}
