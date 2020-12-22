%Gabrielle Curcio
%CSC 435-01
%9/24/20
%Prolog 1: Problem 3
%This program duplicates the elements in a list
%
%Solution: Use list unification
%Take the head of the list and duplicate it
%Store the duplicates in a new list and
%remove the head from the original list
%Repeat the steps above until the
%original list is empty

dupli([],[]).
dupli([H|X],[H,H|Y]):- dupli(X,Y).
