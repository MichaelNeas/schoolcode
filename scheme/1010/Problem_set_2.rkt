#lang racket
"1a"
(define (positive-squares n)
  (if (= n 0)
      0
      (+ (expt n 2) (positive-squares(- n 1))))) ;recursive takes 1 away from original and goes through the function with the new number.
(positive-squares 4)
(positive-squares 6)
"1b"
(define (even-num n)
  (if (= n 0) ;when n hits 0 after the recursion takes place the sequence comes to an end.
      0
      (+ (* n 2) (even-num(- n 1)))))
(even-num 1)
(even-num 2)
(even-num 3)
(even-num 4)
(even-num 5)
(even-num 6)
(even-num 7) ;pattern shows a continual increase just like expected

"2"
(define (int k)
  (if (= k 2)
      (/ 1 2)
      (* (- 1 (/ 1 k)) (int(- k 1))))) ;each k value is a positive integer that allows the function to become smaller and smaller
(int 3)
(int 100)

"3"
(define (divides k n) (= 0 (modulo n k))) ;modulo is new but allows for a true or false interpretation which can signal a tally mark for the interpreter to use to count
(define (divisors n) (divisors-upto n n))
(define (divisors-upto n k) ;helper functions can help better define what I need
  (if (= n 1)
      1
      (+ (divisors-upto (- n 1) k)
         (if (divides n k)
             1 0))))
(divides 5 5)
(divides 1 5)  ;true or false values
(divisors 5)
(divisors 10)

"4"
(define (inf-series n)
  (if (= n 1)
      4.0
      (+ (* (expt -1 (+ n 1))(form n)) ;expt allows for the alternation of signs as the equation is evaluated
         (inf-series (- n 1)))))
(define (form n) (/ 4.0 (- (* 2 n) 1))) ;made a form function to simplify my definition

(inf-series 4)
(inf-series 100)
(inf-series 100000)

"5a"
"To compute 300 terms, 299 calls to expt are made because when the final
term reaches 1 the 'then' statement is triggered and the need to use the
'else' statement ends, the base is valued as -1 in the expt function and the 
exponent is every number before n in order.  This allows for the mathematical
property to be called to allow the +,-,+,- pattern to find the limit."

"5b"
(define (inf-series-r n) ;needs a conditional statement so then the series can alternate signs without the exponent process that took place previously
  (cond ((= n 1) 4.0)
        ((helper-i 2 n) (- (inf-series-r (- n 1))
                           (form n)))
        ((helper-i 1 n) (+ (inf-series-r (- n 1))
                           (form n)))
        )
  )
(define (helper-i a b)
  (= 0 (modulo b a)))
(inf-series-r 4)
(inf-series-r 100)
(inf-series-r 100000)

"6"
(define (new-if predicate then-clause else-clause)
(if predicate then-clause else-clause))
(define (factorials n)
  (new-if (= n 0)
      1
      (* n (factorials (- n 1))))) ;INFINITY&BEYOND!
"When I ran (factorial 3) for example DrRacket instantly didn't know what 
to do and froze, almost terminating my unsaved work.  The reason for this
is infinite recurssion.  In the code the new-if function allows factorial to go
on forever and Racket attempts to continue multiplying until it reaches some 
end that I never designated it to reach.  The factorial program says to multiply
the n variable by itself then subtract 1 from the initial and multiply that by
the previous value, all the way to 0, and the new-if does not allow it to reach"

"7" ;may be unethical but I defined a lot of things to keep the code orginized for myself and allow me to fully writed the code because I process it step by step.
(define (factorial f)
  (if (= f 0)
      1
      (* f (factorial (- f 1)))))
(define (alt-sign p);not using but I could
  (if (= p 1)
     -1
  (expt -1 p)))
(define (maj-var k)
  (+ (* 2 k) 1))
(define (the-function x n)
  (* (expt -1 n) (/ (expt x (maj-var n)) 
                               (factorial (maj-var n)))
         ))
(define (new-sin x n) ;for all real x
  (if (= n 0) x
      (+ (new-sin x (- n 1)) (the-function x n))
         )
      )
  
(factorial 3)
(alt-sign 3)
(maj-var 2)
(new-sin 2 1)
(new-sin 2 2)
(new-sin 2 3) ;if we were able to put x in place of 2 I would get the desired return
;of x-(x^3)/3!+(x^5)/5!-(x^7)/7!

