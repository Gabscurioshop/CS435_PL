%Gabrielle Curcio
%CSC 435-01
%10/22/20
%Erlang-1
% This function takes a list of cities and prints
%their temperatures in Fahrenheit

-module(temperature).
-export([format_temps/1,start/0]).

%this is the driver function
%if the list is empty, don't return anything
format_temps([])-> ok;

%CurrCity is the head of the city list
%NextCity is the list of remaining cities
format_temps([CurrCity | NextCity]) ->
    print_temp(convert_to_fahrenheit(CurrCity)),
    %After formatting first city, format the next one
    format_temps(NextCity).

%This function converts the temperature values 
%from Celsius to Fahrenhiet
% c = Celsisus,
%f = Fahrenheit

convert_to_fahrenheit({City, {c, Temp}}) ->
% if temp unit is c, convert to Fahrenheit
%F = 1.8(Temp in Celsius)+32
    {City, {c, 1.8 *(Temp)+32}};
convert_to_fahrenheit({City, {f, Temp}}) -> 
%Otherwise, do nothing
    {City, {c, Temp}}.

%This function prints the city and temperature 
%in degrees fahrenheit
print_temp({City, {c, Temp}}) ->
    io:format("~w: ~w f~n", [City, Temp]).

%This is the main function
%Entries in list are written in the following format:
%{city, {temperature unit, temperature value}
start() -> 
   L =[{ewing,{c,20}},{trenton,{f,70}}],
   format_temps(L).
