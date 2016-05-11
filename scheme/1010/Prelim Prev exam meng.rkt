#lang racket
(define e 2.7182)
e
(/ (+ (expt (- 3 5) 2) 10) 2)
(define (trapeze-area 1b sb h) 
  (/ (* (+ 1b sb) h) 2))
(trapeze-area 1 2 3)

(define (tanh x)
  (let ((e 2.7182)
       (f (expt e (* 2 x))))
    (/ (- f 1) (+ f 1))))
(tanh 3)

(define (sign x) 
  (if (>= x 0)
      "+1"
      "-1"))
(sign 4)
(sign 0)
(sign -4)

(define (e-approx k)
  (define (factorial n)
    (if (= n 0)
        1
        (* n (factorial (- n 1)))))
  (if (= k 0)
      1.0
      (+ (/ 1 (factorial k)) (e-approx (- k 1)))))
(e-approx 90)

(define (prod f a b)
  (if (= a b)
      (f a)
      (* (f a) (prod f (+ a 1) b))))
(prod (lambda (x) x) 1 5)

(define (dfact n) 
  (define (even x) (= (modulo x 2) 0))
  (cond ((even n) (prod (lambda (i) (* 2 i)) 1 (/ n 2)))
        (else (prod (lambda (i) (- (* 2 i) 1)) 1 (/ (+ n 1) 2)))))
                     
(dfact 5)

(define (pow a b)
  (if (= b 0)
      1
      (* (pow a (- b 1)) a )))
(pow 2 6)

(define (poww a b)
  (define (pow-tr a b)
    (if (= b 1)
        a
        (* (pow-tr a (- b 1)) a)))
  (pow-tr a b))
(poww 3 6)