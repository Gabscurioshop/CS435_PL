%Gabrielle Curcio
%CSC 435-01
%10/29/20
%Erlang 2 Programming Assignment
%This program monitors the status 
%of a Mystery Doors game
%It notifies the user when the game is over 
% and when it resets

-module(monitor).
-export([loop/0]).

%recursive flag
loop() ->
  %gives the exit status of the program
  process_flag(trap_exit, true),

  receive
   %To create process, enter input in the following format:
   % PID-name ! new.
    new ->
       io:format("Creating and monitoring process.~n"),
       %register a linked process to doors.erl
       register(game, spawn_link(fun doors:loop/0)),
       loop();

    %If you found a bomb or the prize, reset the game
    {'EXIT', From, Reason} ->
     %print exit status of program 
      io:format("Process ID: ~p and reason ~p. ~n", [From, Reason]),

      %generate a new process
      io:format("Restarting. ~n"),
      self() ! new, 
      loop();

  %User types the wrong input
    _ ->
      io:format("Invalid input! Please enter: PID-name ! new. ~n"),
      loop()

end.