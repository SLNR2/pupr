import java.io.File;
import javax.imageio.*;
import java.awt.image.BufferedImage;

public class DefaultDogs {
   public static void main(String args[]) {
   
   // File sourceImage = new File(System.getProperty("user.dir") + (File)("\\\Megan.jpg"));
   
 //  System.out.print(sourceImage);
   
   Dog megan = new Dog("Megan", "sloan.lipman@gmail.com", "Look how cute she is!!! 5 stars please!", new Picture("C:\\Users\\sloan\\Google Drive\\Grad School\\KSU\\2018 - Fall\\SWE 6623\\SWE 6623 Group Project\\Java code\\Megan.jpg")); //need to do something about local directory issues
   
   
   }
}