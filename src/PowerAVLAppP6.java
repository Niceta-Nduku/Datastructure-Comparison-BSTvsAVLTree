import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*; 
import java.util.*;

/**
*  PowerAVLAppP6 is a version of PowerAVLApp that takes in a sorted CSV file.
*  It reads a CSV file and adds the data to an AVL Tree and 
*  prints out the data in the tree.
*  @author Niceta Nduku NDKNIC001
*/
public class PowerAVLAppP6 { 

  private static AVLTree powerAVL;// AVL tree to be used in class

  /**
  *    This is the method that reads from the CVS file and captures all the required data into an AVL tree.
  *    It updates the variable powerAVL tree with data from the CSV file.
  *    @exception IOException
  *    @exception FileNotFoundException
  *    @see IOException
  *    @see FileNotFoundException
  */
  private static void getData() throws IOException, FileNotFoundException {
      
    powerAVL = new AVLTree();

    // read from the CVS file 
    BufferedReader bRead = new BufferedReader(new FileReader("../Tests/SortedData.csv"));

    String line = bRead.readLine(); // the first line that contains the required data

    // initialise the values to be stored in the powerData object
    String dateTime;
    String power;
    String voltage;
  
    while (line!=null){

      String[] allValues = line.split(",");// split at each comma to have an array of all the values
      dateTime=allValues[0];
      power=allValues[1];
      voltage=allValues[3];

      // add the data to the tree by creating a new powerArray object with the above values
      powerAVL.insert(new PowerData(dateTime,power,voltage));

      line = bRead.readLine();     

    }
  }

  /**
  *  This method takes in the date/time from the user,
  *  searches through the data in the AVL treee to find a matching date/time 
  *  and prints out the data 
  *  @param dateTime 
  *  @exception IOException
  *  @see IOException
  */
  public static void printDateTime(String dateTime) throws IOException{

    if(powerAVL.find(dateTime) == null){
      System.out.println("Date/Time not found");

    }

    else{
      powerAVL.getFindOpcount();// to rest the operation count if not cound
      System.out.println("Date/Time: "+powerAVL.find(dateTime));
  
    }
  }
   
  public static void printAllDateTimes() throws IOException{
    /**
    *  This method prints out all the data in the AVL Tree
    *  @exception IOException
    *  @see IOException
    */

    powerAVL.display();

  }

  /**
  * This is the main method that runs the application 
  * based on the user input.         
  */
  public static void main(String [] args) throws IOException {

    getData();
    if (args.length==0){ //if the input is null, print all the powerData items in the array

      printAllDateTimes();
      powerAVL.getOpcount();// print insert and find operations

    }

    else{

      File file = new File(args[0]);

      if(file.exists()){

        BufferedReader bRead = new BufferedReader(new FileReader(args[0]));// read from the txt file 
        
        String line = bRead.readLine();

        while (line!=null){

          powerAVL.find(line);
          System.out.print(powerAVL.getFindOpcount()+ " " + powerAVL.getInsertOpcount()+" ");// tabbing here is to be used when inserting into an xlsx file 
          line = bRead.readLine();

        }
      }

      else { // if the user inputs a string
        printDateTime(args[0]);
        powerAVL.getOpcount();
      }
    }
  }
}