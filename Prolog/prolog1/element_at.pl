%Gabrielle Curcio
%CSC 435-01
%9/24/20
%Prolog 1: Problem 2
%This program finds the Nth element of a list.
%
%Fact: The first element of a list is at index 1.
% Solution: Starting at the first element, traverse the list until we
% reach the Nth index.

element_at(L,[L|_],1).
element_at(L,[_|T],N):- N>1, K is N-1,element_at(L,T,K).
