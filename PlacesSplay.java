
/**
 * PlacesSplay class 
 * 
 * @author Joowon Kim
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PlacesSplay {
   public static void main(String[] args) throws FileNotFoundException {

      File zips = new File("zips.txt");

      if (!zips.isFile() || !zips.exists()) {

         System.out.println("File does not exist");
         System.exit(0);
      }

      Scanner in = new Scanner(zips);
      SplayTree<Place> places = new SplayTree<>();

      in.nextLine(); 

      while (in.hasNextLine()) {
         String eachLine = in.nextLine();
         String[] split = eachLine.split("\t");

         SplayNode<Place> node = places.search(new Place("", split[3]));
         if (node != null) {

            node.getElement().addZips(split[0]);
         } else {

            places.insert(new Place(split[0], split[3]));
         }
      }
      in.close();

      Scanner scanner = new Scanner(System.in);
      System.out.println("You Should Enter" + " City Name (start with capital letter)" + " ," + "State(2 letters, all capital letters)");

      while (true) {
         System.out.print("CITY NAME and STATE: ");
         String answer = scanner.nextLine();
         SplayNode<Place> node = places.search(new Place("", answer));

         if (node != null) {

            System.out.printf("The number of comparisons needed to find the entry: %d \n", places.getcompCount());
            System.out.println("The zipcode that belong to " + answer + " are: "
                  + node.getElement().toString());
         } else {
            System.out.println("No such city name");
            System.out.printf("The number of comparisons needed to find the entry: %d \n", places.getcompCount());
         }
         System.out.print("Do you want me to search again? (Yes / No) ");
         answer = scanner.nextLine();
         if (!answer.equals("Yes")) {
            break;
         }
      }

      System.out.println("\nExit!");
      scanner.close();
   }

}