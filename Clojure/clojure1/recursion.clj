;Gabrielle Curcio
;CSC 435-01
;11/5/20
;Clojure 1- Task 1
;This program finds the factorial
;of an integer with recursion

;Take an integer n as input in the following format:
;(factorial n)
(def factorial
  (fn [n]
    (loop [counter n
           accumul 1]
      ; if counter is 0, exit the loop and return accumulator
       (if (zero? counter)
            accumul
          ;Otherwise multiply counter to accumulator,
          ;decrease counter by 1
          ;And use recur to enter new values in the loop
          (recur (dec counter) (* accumul counter))
      );end of if stmt
    );end of loop
  );end of function call
);end of def
