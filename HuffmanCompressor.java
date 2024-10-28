import java.util.*;
import java.io.*;
public class HuffmanCompressor
{
  public static int[] getFrequency(String fileName) {
        int[] f = new int[256];
        BufferedReader myFile = null;
        try {
            myFile = new BufferedReader(new FileReader(fileName));
        }
        catch (IOException e) { System.out.println("Could not find file:" + fileName); }
        try {
            int charValue;
            while (myFile.ready()) { //at least 1 additional char exists in file
                charValue = myFile.read(); // get ascii value of the next char in file
                f[charValue]+=1;
            }
        } catch (IOException e) { System.out.println("Error reading the file" + e); }
        return f;
    }
    
    public static void compress(String txtFile, String nameOfShortFileToBeCreated, String codeFileToBeCreated)
    {
      HuffmanTree a = new HuffmanTree(getFrequency(txtFile));
      a.write(codeFileToBeCreated);
      BufferedReader myFile = null;
        try {
            myFile = new BufferedReader(new FileReader(txtFile));
        }
        catch (IOException e) { System.out.println("Could not find file:" + txtFile); }
        try {
            BitOutputStream s = new BitOutputStream(nameOfShortFileToBeCreated);
            int charValue;
            while (myFile.ready()) { //at least 1 additional char exists in file
                charValue = myFile.read(); // get ascii value of the next char in file
                String d = a.getV(charValue);
                for(int i = 0; i < d.length();)
                {
                  if(d.charAt(0) == '1')
                  {
                    s.writeBit(1);
                  }
                  else if(d.charAt(0) == '0')
                  {
                    s.writeBit(0);
                  }
                  
                  d = d.substring(1);
                }
            }
            String end = a.getV(256);
            for(int i = 0; i < end.length();)
            {
               if(end.charAt(0) == '1')
                  {
                    s.writeBit(1);
                  }
                  else if(end.charAt(0) == '0')
                  {
                    s.writeBit(0);
                  }
                  
                  end = end.substring(1);
            }
            s.close();
          } catch (IOException e) { System.out.println("Error reading the file" + e); }
    }
    
    public static void expand(String shortFile, String codeFile, String nameOfNewFileToBeCreated)
    {
      HuffmanTree a = new HuffmanTree(codeFile);
      a.decode(new BitInputStream(shortFile), nameOfNewFileToBeCreated);
    }


}