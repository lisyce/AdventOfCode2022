package day13; 
 
import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.*; 
 
public class Main { 
   public static void main(String[] args) throws FileNotFoundException { 
       File input = new File("src/day13/test.txt"); 
       Scanner s = new Scanner(input);

       while(s.hasNextLine()) {
           String pack1 = s.nextLine();
           String pack2 = s.nextLine();

           System.out.println(inRightOrder(pack1, pack2));
       }
   }

   public static boolean inRightOrder(String p1, String p2) {
       // get the next complete piece of information from each packet
       List<String> listP1 = convertToParts(p1);
       List<String> listP2 = convertToParts(p2);


       return false;
   }

   public static List<String> convertToParts(String p) {
       List<String> l = new ArrayList<>();

       return l;
   }
} 
