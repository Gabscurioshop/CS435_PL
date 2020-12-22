%Gabrielle Curcio
%CSC 435-01
%10/1/20
%Prolog 2
%This program prints an M x N rectangle made from asterisks
%to the console and saves the output in a text file
%Let M = number of rows
%Let N = number of columns

%This function prints M asterisks for each row in the rectangle
%Once the counter reaches 0, it stops printing.
row(0).
row(M) :- M > 0, write('*'), Z is M - 1, row(Z).

%This function recursively calls row() N times
%Once the column counter reaches 0, the rectangle has been created.
make_rectangle(_,0).
make_rectangle(M,N) :- N > 0, row(M), nl, A is N-1, make_rectangle(M,A).

%This function writes the rectangle to a file
%tell() opens up a file to store the output
%told closes the output stream
write_to_file(M,N):-
    tell('rectangle.txt'),
    make_rectangle(M,N),
    told.

%This function checks if the dimensions of the rectangle are positive
rectangle(M,N):- M>0, N>0, make_rectangle(M,N), write_to_file(M,N).
