%Gabrielle Curcio
%CSC 435-01
%9/24/20
%Prolog 1: Problem 1
%This program finds the Greatest Common Divisor
%of 2 positive integers A and B
%
%Solution: Euclidean Algorithm
%Assume B > A
%If A = 0 and B is positive, then B is the GCD. Stop here.
%If A is positive, then R equals B mod A,where R is the remainder
%Repeat the above step with R as the new A and old A as the new B.

gcd(0,B,B) :- B>0.
gcd(A,B,D) :- A>0, R is B mod A, gcd(R,A,D).
