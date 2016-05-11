#lang racket
(define (bar a b c) (* a b c))
  (bar 1 2 3)
  
 "string"
 
 "symbol"
 'symbol
 true 
 false
 
 (< 4 2)
 
 (define (iseven? a) (even? a))
 (iseven? 6)
 
 (define (check-num a)
   (cond [(even? a) 'Even]
         [(odd? a) 'Odd]))
 (check-num 5)
 
 (define [count-down num] 
   (cond [(zero? num) 'Done!]
         [else (count-down (- num 1))]))
 
 (define (count-even-assis x count) "HELPER FUNCTION"
   (cond [(zero? x) (+ count 1)]
          [(even? x) (count-even-assis (- x 1) (+ count 1))]
          [(odd? x) (count-even-assis (- x 1) count)]))
 
 (define [count-evens x]
   (count-even-assis x 0))
 
 (count-evens 8)