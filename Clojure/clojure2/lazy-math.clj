;Gabrielle Curcio
;CSC 435-01
;11/12/20
;Clojure 2
;This program implements multiplication, factorials, 
;and fibonacci sequence using 
;multimethods and lazy evaluation 

;Create a multimethod
;To call the multimethod,
;enter the term: order
(defmulti order
  (fn[x] (get x "order")))

;This method handles the multiplication of a sequence of integers
;(1 2 3 4 5) -> (1, 1*2, 1*2*3, 1*2*3*4, 1*2*3*4*5)
;base case: 1*1
(defmethod order Multiplication [params]

  ;Function for calculating product
 (defn multn ([] (multn 1 1))
   ;store the current product in a sequence
  ([product x]
    ;Multiply current product with the current number from the sequence
   (let [new-product (* product x)]
      ;iterate through sequence
     (lazy-seq
      ;Repeat this function with 
      ;the new product and next number in the sequence
      ;as parameters
      ;Stop until you reach the end of the sequence
      (cons new-product (multn new-product (inc x)))
     );end of lazy-seq
    );end of let
   );end of [product x]
  );end of multn function      
 ;Generate the sequence [1,2,3,4,5]
(take 5 (multn))
);end of Multiplication method
 
;This method calculates the factorial of 
;each integer in the sequence
;(0!, 1!, 2!, 3!, 4!) -> (1 , 1, 2*1, 3*2*1, 4*3*2*1)
;base cases:1! = 1

(defmethod order Factorial [params] 

; Function for calculating factorial
;The initial parameters are 1,1 because 
;our sequence is from 1-5. 
;To compensate for 0!=1, we'll calculate 1! twice
  (defn facn ([] (facn 1 1))
    ;Store current product and current integer from sequence
    ;in a new sequence
    ([x product]
      ;Set up new parameters:
      ;Next number in original sequence and current product
      (let [new-x (inc x) new-product (* x product)]
            ;For each integer in original sequence,
            ;Multiply it with its preceding integers
            ;Repeat this until you reach the end of the sequence
        (cons product (lazy-seq (facn new-x new-product)))
      ); end of let
    );end of [x product]]
  ); end of function
 ;Generate the sequence [1,2,3,4,5]
 (take 5 (facn))
); end of Factorial method

;This method determines the first five integers
;in the Fibonacci sequence
;fib_n = fib_(n-1) + fib_(n-2)- Add the 2 previous terms together
;Base Case: fib_0 = 0, fib_1 = 1

(defmethod order Fibonacci [params] 

;Function for calculating fibonacci sequence
;The initial parameters are 1,1 because 
;our sequence is from 1-5. 
;Since 0 is not a part of the sequence, we'll list 1 twice
  (defn fib ([] (fib 1 1))
    ;Save the previous two integers in a new sequence
    ([m n]
      ;iterate through sequnce
      (lazy-seq 
        ;Add the two previous integers together
        ;Repeat this process until 
        ;you reach the end of the sequence
        (cons m (fib n (+ m n)))
      );end of lazy-seq
    ); end of [m n]
  );end of fib function
;generate the sequence [1,2,3,4,5]
(take 5 (fib))
); end of Fibonacci method

;;Method calls
;;User must input method calls
;;on command line in the following format:
;(order Calculation_Name)
(def Multiplication {"id" "1", "order" Multiplication})
(def Factorial {"id" "2", "order" Factorial})
(def Fibonacci {"id" "3", "order" Fibonacci})
