import java.util.*;
import java.io.*;
public class HuffmanTree
{
   private Node root;
   private HashMap<Integer, String> store;
   public HuffmanTree(int[] count)
   {
     Queue<Node> a = new PriorityQueue<Node>();
     
     store = new HashMap<Integer, String>();

     for(int i = 0; i < count.length; i++)
     {
       if(count[i] > 0)
       {
        Node cD = new Node(i, count[i]);
        a.offer(cD);
       } 
     }
     Node eoF = new Node(256, 1);
     a.offer(eoF);
    while(a.size() > 1)
    { 
     Node p = a.poll();
     Node b = a.poll();
     Node h = new Node(0, p.frequency + b.frequency);
     h.left = p;
     h.right = b;
     a.offer(h);   
    }
    root = a.poll();  
   }
   
   public String getV(Integer a)
   {
     return store.get(a);
   }
   
   
   public void printTree()
   {
     TreePrinter.printTree(root); 
   }
   
   public void write(String fileName)
   {
     String outputFileName = fileName;
	   PrintWriter diskFile = null;
 	 try { 
       diskFile = new PrintWriter(new File(outputFileName)); 
     }
	  catch (IOException io) { 
        System.out.println("Could not create file: " + outputFileName); 
     }
     wH(root, "", diskFile);
     diskFile.close();
   }
   
   public void wH(Node n, String s, PrintWriter d)
   {
     if(n.left == null && n.right == null)
     {
         store.put(n.val, s); 
         s = n.val + "\n" + s;
         
         d.println(s);   
     }
    if(n.right != null)
     {      
       wH(n.right, s + "1", d);
     }
     if(n.left != null)
     {     
       wH(n.left, s + "0", d);
     }
   }
   
   public HuffmanTree(String codeFile)
   {
     store = new HashMap<Integer, String>();
     try
     {
       Scanner i = new Scanner(new File(codeFile));     
       while(i.hasNextLine())
       {
         if(root == null)
         {
           root = new Node(0, 0);
         }
         int value = Integer.parseInt(i.nextLine());
         if(i.hasNextLine())
         {
          String d = i.nextLine();
          store.put(value, d);
          root = bT(value, d, root);
         } 
         
       }
     
     }
     catch (IOException io) 
     { 
       System.out.println("Could not create file" ); 
     }
   }
   
   public Node bT(int n, String t, Node r)
   {
     if(t == "")
     {
       r.val = n;
     }
     else if(t.charAt(0) == '1')
     {
       if(r.right == null)
       {
         r.right = new Node(0, 0);       
       }
        r.right = bT(n, t.substring(1), r.right);             
     }
     else if(t.charAt(0) == '0')
     {
       if(r.left == null)
       {
         r.left = new Node(0, 0);       
       }
        r.left = bT(n, t.substring(1), r.left);
     }
     
     return r;   
   }
   
   public void decode(BitInputStream in, String outFile)
   {
     int stream = in.readBit();
     String printW = "";
     Node head = root;
     while(head.val != 256)
     {
       if(head.val != 256)
       {
         if(stream == 0)
         {
           head = head.left;
         }
         else if(stream == 1)
         {
           head = head.right; 
         }
         if(head.right == null && head.left == null)
         {
           printW += (char)head.val;
           head = root;
           stream = in.readBit();
         }
       }
     }
     PrintWriter diskFile = null;
 	  try { 
       diskFile = new PrintWriter(outFile);
       diskFile.print(printW);
       diskFile.close(); 
     }
	  catch (IOException io) { 
        System.out.println("Could not create file: " + outFile); 
     }
   }
 }  

  class Node implements Comparable <Node>
  {
    public int val;
    public Node left, right;
    public Integer frequency;
    public Node(int val, Integer frequency) 
    {
      this.val = val;
      left = right = null;
      this.frequency = frequency;      
    }
      
    @Override
    public String toString() 
    { 
      char c = (char)this.val;
      return "" + c;
    }
    
    public int compareTo(Node o)
   {
     if(o.frequency == this.frequency)
     {
       return 0;
     }
     if(o.frequency > this.frequency)
     {
       return -1;
     }
     return 1;
   }

  }
