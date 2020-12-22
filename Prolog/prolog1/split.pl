%Gabrielle Curcio
%CSC 435-01
%9/24/20
%Prolog 1: Problem 4
%This program splits a list into 2 parts at a given index
%The list index must be a positive integer
% Solution: Remove the head from the original list and
% store it in a second list.
% Repeat this process until the last element in the new list is
% the element at index K from the original list

split(L,0,[],L).
split([H|X],K,[H|Y],L1) :- K > 0, N is K - 1, split(X,N,Y,L1).
