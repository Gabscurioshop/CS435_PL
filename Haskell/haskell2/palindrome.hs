--Gabrielle Curcio
--CSC 435-01
--11/20/20
--Haskell PA 2
--This program determines if a list
--is a palindrome, meaning:
--The order of the list stays the same
--when written backwards 

--Use type instancing to take a list as input and return true/false as output 
isPalindrome :: (Eq a) => [a] -> Bool
-- reverse function creates a new list
--with the elements in reverse order
--If the order of elements in the 
--original list is the same as the order 
--in the reverse list, return true
--otherwise, return false 
isPalindrome list = list == (reverse list)
