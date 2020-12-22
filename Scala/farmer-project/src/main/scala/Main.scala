/*
Gabrielle Curcio
CS 435-01
10/15/20
Scala2- Farmer's Project
This program displays a user menu 
that farmers can pick from
*/
import java.util.Scanner
import java.io.IOException
import java.nio.file._
import java.util.function._
import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import akka.util.Timeout
import scala.concurrent.duration._
import akka.pattern.ask
import akka.actor.Actor
import akka.actor.Props
import akka.actor.ActorSystem
import akka.dispatch.ExecutionContexts._

// Encapsulates the information in the file
class Farmer(var name: String, var food: String, var quantity: Int)

// Singleton Object that handles reading of the file
object FileOperations {
  
  // This is the main function to read a file for the application
  def readFile(name: String) : Option[List[Farmer]] = {
    try {
      	var farmerList = new ListBuffer[Farmer]()
      // IMPLEMENT YOUR FILE IO CODE HERE
	//First, retrieve the path of the file
	//Then, read and store the fileContents
	var readPath = Paths.get(name)
	var fileContent = Files.readAllLines(readPath)

	//split each line into separate tokens by commas
	//Save the tokens in a new Farmer object
	//id(0) = name, id(1) = food, id(2) = quantity
	//add the Farmer object to farmerList
	for(line <- fileContent) yield {
		val id = line.split(",")
		var farmer = new Farmer(id(0), id(1), id(2).toInt)
                farmerList += farmer
	}
		return Option(farmerList.toList)
    } 
    catch {
      	case io: IOException => return None
    }
   
  }
}

// This singleton object handles all the farmer operations
object FarmerOperations {
  
  // Main entry point to the main method
  def main(list: List[Farmer], scan: Scanner) {
    var run = true
    var farmerList = list // List of lines from the file
    var farmerNameSet: Set[String] = Set() // Helper object with all farmer names
    for (farmer <- list) {
      farmerNameSet = farmerNameSet + farmer.name
    }
    while(run) {
      println("Menu operations: ")
      println("1. Filter by farmer's name to print food and quantity for each food for that farmer")
      println("2. Allow for someone to buy food from a farmer if they have the quantity requested")
      println("3. Compute an average for all food categories between all farmers")
      println("4. Exit program")
      var choice = scan.nextLine().toInt
      choice match {
        case 1 => filterByFarmer(farmerList, farmerNameSet, scan)
        case 2 => buyFoodFromFarmer(farmerList, farmerNameSet, scan)
        case 3 => averageFood(farmerList)
        case 4 => run = false
      }
    }
    scan.close()
  }

  // This method is for menu item 1, filter by farmer name
  // This method checks to make sure the farmer specified exists and if so, prints it's inventory
  // params -> list: A list of farmers from the file, farmerNames: A set of farmer names for conveince, scan: Scanner object for IO
  def filterByFarmer(list: List[Farmer], farmerNames: Set[String], scan: Scanner) = {
    println("List of available farmers: ")
    // IMPLEMENT YOUR CODE HERE
    //iterate through set and print farmer names
    for(name <- farmerNames)
	println(name)

    println("Input Farmer Name: ")
    //let user enter farmer name
    var scan = new Scanner(System.in)
    var user_farmer = scan.nextLine()

    //check that user input is in the farmers list  
    if (farmerNames.contains(user_farmer) == true){
	 println("Food for farmer " + user_farmer)
         //use high order filter() function to filter list by farmer name 
	 var inventory = list.filter(farmer => farmer.name == user_farmer)
       //print out the farmer's inventory
	 for(i <- inventory)
		println(i.food + ": "  + i.quantity)
    }
    else println("Farmer doesn't exist.")
  }
  
  // This method is for menu item 2: buy food from a farmer
  // If a valid farmer and food is chosen, as well if the quantity asked
  // params -> list: A list of farmers from the file, farmerNames: A set of farmer names for conveince, scan: Scanner object for IO
  def buyFoodFromFarmer(list: List[Farmer], farmerNames: Set[String], scan: Scanner)  = {
    // IMPLEMENT YOUR CODE HERE

   var curr_amount = 0
    println("List of available farmers: ")
    //iterate through set and print farmer names
    for(name <- farmerNames)
	println(name)

    println("Input Farmer Name: ")
    //let user enter farmer name
    var scan = new Scanner(System.in)
    var user_farmer = scan.nextLine()

    //check that user input is in the farmers list  
    if (farmerNames.contains(user_farmer) == true){
	 println("Available food for farmer selected ")
         //use high order filter() function to filter list by farmer name 
	 var inventory = list.filter(farmer => farmer.name == user_farmer)
       //print out the farmer's inventory
	 for(i <- inventory)
		println(i.food + ": "  + i.quantity)

        println("Please select a food")
    	//let user enter food name
    	var scan = new Scanner(System.in)
    	var user_food = scan.nextLine()
        
	//check that the farmer has the food the user wants
	var checkFood = inventory.filter(food => food.food == user_food) 
        if (checkFood.isEmpty == false){//food is in farmer's inventory

           //retrieve current quantity for food
	   for(i <- checkFood) yield{
	       curr_amount = i.quantity
           }

	   println("Please specify how much you would like to buy")
    	   //let user enter food quantity
    	   var scan = new Scanner(System.in)
    	   var user_amount = scan.nextLine().toInt

           //check that there's enough food
           if(user_amount <= curr_amount || curr_amount - user_amount >=0){
	      curr_amount = curr_amount-user_amount
              println("Successfully purchased!")
              println("Farmer " + user_farmer + " now has in his inventory: " + curr_amount + " " + user_food)

              
              //update farmerlist with new quantity of food
	      for( i <- list) yield{
                if (i.name == user_farmer && i.food == user_food){
		   i.quantity = curr_amount
		}
              } //end of updating list 
           }//end of checking quantity
           else println("There's not enough.")
	}//end of checking food
        else println("That food is unavailable.")

	
    }//end of checking farmer
    else println("Farmer doesn't exist.")
  }

  // This is for menu item 3: Average of each food category across all farmers using Akka
  // Calls the akka actor and blocks until the averages are computed
  // params -> list: A list of farmers
  def averageFood(list: List[Farmer]) = {
    // IMPLEMENT YOUR CODE HERE. NOTE: MUST USE THE AKKA ACTOR
   case class ProcessFoodAvg(list: List[Farmer])
    //our Akka Actor class
   class FoodCounterActor extends Actor {
     def receive = {

       case ProcessFoodAvg(list) => {
          
          var oravg = 0 //orange average
	  var appavg = 0 //apple avergae
          var peavg = 0 //peach average
    
          //filter out the list by food
          var oranges = list.filter(farmer => farmer.food == "oranges")
	  var apples = list.filter(farmer => farmer.food == "apples")
          var peaches = list.filter(farmer => farmer.food == "peaches")

          
          //take the sum of all 3 foods
          for (o <-oranges)
		oravg = oravg + o.quantity

          for (a <-apples)
		appavg = appavg + a.quantity

          for (p <-peaches)
		peavg = peavg + p.quantity

          //divide all food totals by 3
          oravg = oravg/3
          appavg = appavg/3
          peavg = peavg/3

          //print results
          println("oranges: " + oravg)
          println("apples: " + appavg)
          println("peaches: " + peavg)
      }
      case _ => println("Error: list not recognized")
    }
    context.system.shutdown()
  }
   implicit val ec = global
   //declare system and actor
   val system = ActorSystem("System")
   val actor = system.actorOf(Props(new FoodCounterActor()))
   actor ! ProcessFoodAvg(list)
   system.awaitTermination()
  }
}

// Main program entry point to start the application
object Main extends App {
  println("Please input a file path")
  var scan = new Scanner(System.in)
  var path = scan.nextLine()
  FileOperations.readFile(path) match {
    case Some(list) => FarmerOperations.main(list, scan)
    case None => println("File not found! Exiting program")
  }
  
}