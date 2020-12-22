//Gabrielle Curcio
//CSC 435-01
//10/8/20
//Scala 1 - gcd
//This program determines the greatest common divisor
//of two integers entered by the user
import scala.io.StdIn.readInt

object Main {
  //this function determines the gcd using Euclid's algorithm
  def gcd(a:Int, b:Int): Int ={
    //Solve for the remainder using b mod a
    //If the remainder is 0, then b is the gcd
    //otherwise, recursively solve the gcd with 
    //a as the new b, and the remainder as the new a 
    if (a == 0) b
    else gcd(b%a,a)
  }

  def main(args: Array[String]): Unit = {
    //read two integers from command-line
    println("Please enter the 1st number:")
    val a = readInt()
    println("Please enter the 2nd number:")
    val b = readInt()
    //calculate the gcd
    val result = gcd(a,b)
    println("The greatest common divisor is: "+ result)
  }
}
