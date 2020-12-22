-- Gabrielle Curcio
-- CS 435-01
-- 11/19/20
-- Haskell 1-1

--This program reverses a list using recursion

--Take a list as input and return a list as output
reverseList :: [a] -> [a]
--Base case: If the list is empty, return an empty list
reverseList [] = []
--Split list into head and tail recursively 
--until list is empty
--Add the head of each list to a new list
reverseList (h:t) = reverseList (t) ++ [h]
