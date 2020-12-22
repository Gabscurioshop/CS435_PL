-- Gabrielle Curcio
-- CS 435-01
-- 11/19/20
-- Haskell 1-2

--This program returns the second element of a list
--using the dot operator and functional composition

--Take a list as input and return a list as output
secondElement :: [a] -> a
--split the list by its head and tail
--return the head of the tail
secondElement = head . tail
