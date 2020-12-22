%Gabrielle Curcio
%CSC 435-01
%10/22/20
%Erlang-2
%This program creates 2 threads recursively N times

-module(thread).
-export([start/0, say_hello/2]).

%This function calls print N times
say_hello(_, 0) ->
%base case
    done;
say_hello(Msg, N) ->
    print(Msg),
    say_hello(Msg, N - 1).

%This function prints the messages
print(X) ->
    io:format("~p~n", [X]).

%This function creates 2 threads
start() ->
    spawn(main, say_hello, ['Hello from 1', 3]),%Thread 1
    spawn(main, say_hello, ['Hello from 2', 3]),%Thread 2
    io:fwrite("Finished start~n").
