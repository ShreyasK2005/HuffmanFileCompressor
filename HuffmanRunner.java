import java.util.*;
import java.io.*;
import static java.lang.System.*;

public class HuffmanRunner {

   public static long startTime;
   
   public static void main(String[] args) {
      
      String name = "mccoy";
      
      String originalFile = name+".txt";
      String shortFile = name+".short";
      String codeFile = name+".code";
      String newFile = name+".new";
   
      out.println("Compressing " + originalFile + " into " + shortFile);
      startTimer();
      HuffmanCompressor.compress(originalFile, shortFile, codeFile);
      stopTimer();   
   
      out.println("\nExpanding " + shortFile + " into " + newFile);
      startTimer();
      HuffmanCompressor.expand(shortFile, codeFile, newFile);
      stopTimer(); 
      
      out.println();
      try {
         File og = new File(originalFile);
         File comp = new File(shortFile);
         File exp = new File(newFile);
         out.printf("Size of original file   >> %12d%n", og.length());
         out.printf("Size of compressed file >> %12d%n", comp.length());     
         out.printf("Size of expanded file   >> %12d%n", exp.length());
         out.printf("Compression efficiency  >> %d%% reduction in size%n%n", (100*comp.length()/og.length())); 
            
         if (exp.length() == og.length())
            out.println("Expanded file is same size as original file.  You mighta done it right.");
         else  {
            out.println("Expanded file is NOT same size as original file.  Uh oh.");
            out.println("If you are only off by 1 byte, it might be an extra line feed ");
            out.println("or unnecessary inclusion of the pseudo-EOF character at the end of your new file.");
         }
      } 
      catch (Exception e) { out.println("Some files didn't exist."); }
   }
   
   public static void startTimer() {
      startTime = System.currentTimeMillis();
   }
   
   public static void stopTimer() {
      long end = System.currentTimeMillis();
      float sec = (end - startTime) / 1000F; 
      System.out.println("Run time = " + sec + " seconds");
   }
      
   
}