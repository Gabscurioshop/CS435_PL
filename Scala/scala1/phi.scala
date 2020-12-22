//Gabrielle Curcio
//CSC 435-01
//10/8/20
//Scala 1 - totient
//This program determines Euler's totient for an integer entered by the user
import scala.io.StdIn.readInt

object Main {

  def phi(m:Int): Int ={
  //this function determines the number of 
  //positive integers that are coprime to m
  //If the gcd of m and another integer is 1, 
  //then it's considered a coprime of m  
    var coprime = 1;//coprime counter
    for(i <- 2 to m){
      if (gcd(i,m) == 1){
        coprime = coprime + 1
      }
    }
    coprime
  }

  def gcd(a:Int, b:Int): Int ={
  //this function determines the gcd of 2 integers
    if (a == 0) b
    else gcd(b%a,a)
  }


  def main(args: Array[String]): Unit = {
    //read one integer from command-line
    println("Please enter m:")
    val m = readInt()
    //calculate the number of coprimes
    val coprime = phi(m)
    println("Phi(m) evaluates to "+ coprime +" coprimes.")
  }
}
