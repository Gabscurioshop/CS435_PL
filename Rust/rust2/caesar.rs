//Gabrielle Curcio
//CS 435-01
//9/17/20
//Rust PA2:Concurrency and File I/O
//This program determines an integer shift
//for decoded and encoded words defined by Caesar cipher

use std::fs;//handling file I/O
use std::thread;//handling threads
use std::time::Duration;
use std::sync::mpsc;//handling channels

fn caesar(cipher: &Vec<i32>, plain: &Vec<i32>, shift: i32){
  //this function verifies the integer shift used to decode the word
  let mut decode = 0;
  //To decode a letter, use the following formula:
  //(x-k) mod 26, where the x is a number from 0-25 representing a letter from the encoded word, and k is
  //the integer shift
  if cipher[0] >= 0 && cipher[0] <= 25{
    //checks if the letter is a value from 0-25
    decode = cipher[0] - shift;
    //if decode is negative, add 26 to 
    //get a positive val
    if decode < 0{
      decode = decode + 26;
    }
  }
  //if the decoded letter matches the plain text letter,
  //then print out the integer shift
  if decode == plain[0]{
    println!("{}", shift);
  }
}

fn text_to_num(map: String ) -> Vec<i32>{
  //this function converts letters in the decoded and encoded string to their corresponding numbers
  //a = 0, b = 1, ... y = 24, z = 25 
 let alpha = "abcdefghijklmnopqrstuvwxyz";
 let mut test = Vec::new();
 //convert alpha string to a vector
 let num: Vec<char> = alpha.chars().collect();

 //get the index of matching encoded/decoded letters
 //from the alpha string
 for m in map.chars(){
   for i in 0..26{
     if m == num[i]{
       test.push(i as i32);
     }
   }
 }
 //return encoded/decoded word as a vector with numbers
test
}

fn main() -> std::io::Result<()> {
  //Read file from current directory 
  //and save it to a string
  let s = fs::read_to_string("word.txt")?;

  //split file input into separate strings
  let words:Vec<&str> = s.split("\n").collect();
  let cipher = words[0].to_string();//holds cipher text
  let plain = words[1].to_string();//hold plain text
  //create decoded/encoded strings with numbers
  let num_cipher = text_to_num(cipher);
  let num_plain = text_to_num(plain);

  let (tx, rx) = mpsc::channel();
  //creates multiple senders
  thread::spawn(move || {
    //creates 26 threads representing shift values
    for i in 0..26{
      tx.send(i).unwrap();//send threads
      thread::sleep(Duration::from_millis(1));//wait
    }
  });
  for received in rx{
    //determine shift
    caesar(&num_cipher,&num_plain,received);
  }
  Ok(())
}
