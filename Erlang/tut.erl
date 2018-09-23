% https://www.youtube.com/watch?v=IEhwc2q1zG4
-module(tut).

-import(string, [len/1, concat/2]).

-export([hello_world/0]).

hello_world() ->
    io:fwrite("hello_world 2\n").
