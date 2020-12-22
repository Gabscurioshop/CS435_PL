-- Gabrielle Curcio
-- CS 435-01
-- 11/19/20
-- Haskell 1-3

--This program creates a multiplication table from 1-n
--It prints out a list in three tuple form:
--(multiplicand, multiplier, product)

--Take an integer as input and return a list
--with three iintegers in a tuple as output
multiplicationTable :: Integer -> [(Integer,Integer,Integer)]
--Multiplicand a: 1..n
--Multiplier b: 1..n
--Product c: a * b
multiplicationTable n = [(a,b,c) | a <- [1..n], b <- [1..n], let c = a * b]
