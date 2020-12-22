;Gabrielle Curcio
;CSC 435-01
;11/5/20
;Prolog1-2
;This program exchanges money between 2 accounts
;using dosync

;Create 2 bank accounts
(def acc1 (ref 100))
(def acc2 (ref 200))
;print current amount from both accounts
(println @acc1 @acc2)

;This function adjusts the money in both accounts
(defn transfer-money [a1 a2]
  (println "How much money do you want to transfer from acc1 to acc2?")
  ;convert user input amount into long type
  (def amount (Long/parseLong (read-line)))
    ;subtract amount from acc1
    ;add amount to acc2
    (dosync
      (alter a1 - amount)
      (alter a2 +  amount)
    );end of dosync
  ;print new amounts
  (println "acc1 contains "@a1 "$")
  (println "acc2 contains"@a2 "$" )
amount);end of transfer-money function
