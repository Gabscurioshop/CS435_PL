%Gabrielle Curcio
%CSC 435-01
%10/29/20
%Erlang 2 Programming Assignment
%This program develops a Mystery Doors game
%The user enters a door number 
%and the program prints out a certain outcome

-module(doors).
-export([loop/0]).

%recursive loop
%User picks a door from 1 to 9
% Doors 1, 2, 3, 6 and 9 are empty
% Doors 5, 7 and 8 have bombs
%Door 4 has the prize

loop() ->
  io:format("Pick a door from 1 to 9 in the following format: PID-name ! integer.~n"),
  receive
    1 ->
      %Nothing there, pick a different door
      io:format("There was nothing behind that door.~n"),
      loop();

    2 ->
      %Nothing there, pick a different door
      io:format("There was nothing behind that door.~n"),
      loop();

    3 ->
       %Nothing there, pick a different door
      io:format("There was nothing behind that door.~n"),
      loop();

    4 ->
      %You found the prize. Now, reset the game.
      io:format("Congratulations! You won the prize behind the door! ~n"),
      exit({doors, die, at, erlang:time()});

    5 -> 
      %There's a bomb here! Game over and reset the game
      io:format("Oh no! There's a bomb behind that door! .... Boom! ~n"),
      io:format("The game ended.~n"),
      exit({doors, die, at, erlang:time()});

    6 ->
      %Nothing there, pick a different door
      io:format("There was nothing behind that door.~n"),
      loop();

    7 ->
      %There's a bomb here! Game over and reset the game
      io:format("Oh no! There's a bomb behind that door! .... Boom! ~n"),
      io:format("The game ended.~n"),
      exit({doors, die, at, erlang:time()});

    8 ->
      %There's a bomb here! Game over and reset the game
      io:format("Oh no! There's a bomb behind that door! .... Boom! ~n"),
      io:format("The game ended.~n"),
      exit({doors, die, at, erlang:time()});

    9 ->
      %Nothing there, pick a different door
      io:format("There was nothing behind that door.~n"),
      loop();

  %Invalid input, prompt user to enter different integer
    _ ->
      io:format("Door doesn't exist! Please try again. ~n"),
      loop()  

end.