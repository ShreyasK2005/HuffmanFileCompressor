  import java.util.*;
 public class testR
 {
  public static void main(String[] args)
  {
     int[] freq = new int[256];
     freq[97] = 12;
     freq[98] = 4;
     freq[99] = 7;
     HuffmanTree ht = new HuffmanTree(freq);
     ht.printTree();
     String fileName = "test.code";
     ht.write(fileName); 
     
     HuffmanTree r = new HuffmanTree(fileName);
     r.printTree();
     System.out.println(ht.getV(99));
  }
 } 