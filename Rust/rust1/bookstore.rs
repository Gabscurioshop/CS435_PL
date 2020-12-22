//Gabrielle Curcio
//CSC 435-01
//9/10/20
//Rust PA1: Ownership and Borrowing
//This program completes three tasks for 
//TCNJ's bookstore:

fn main() {
  let mut book = Vec::new();//book log
  let mut id = 1; //student id
  write(&mut book, &mut id);//add id 1 to book log
  write(&mut book, &mut id);//add id 2 to book log
  read(&book);
  buy(book);
}

fn read(book: &Vec<i32>){
//This allows a student to rent a book with 
//read only access
//Done with aliasing borrow
//take aliased book log as parameter
  println!("Read mode engaged!");
  //print contents of book log w/o pushing to vector
  for item in book{
    println!("{}", item);
  }
}

fn write(book: &mut Vec<i32>, id: &mut i32){
//This adds a student's ID to the book log
//Done with mutable borrow
//mutable book log and id as parameters
book.push(*id);
*id += 1;//increment id after each write
}

fn buy(book: Vec<i32>){
  //This lets a student buy a book and 
  //remove it from the book store
  //Done with ownership
  //book log as parameter
  println!("Buy mode engaged!");
  //print contents of book log
  for item in book{
    println!("{}", item);
  }
}
