#lang racket
(define e 2.7182)
(define (tanh x)
  (let ((m (expt e (* 2 x))))
    (/ (- m 1) (+ m 1))))
(tanh 4)

(define (e-approx k)
  (define (factorial n)
    (if (= n 1)
        1
        (* n (factorial (- n 1)))))
    (if (= k 0)
        1.0
        (+ (e-approx (- k 1)) (/ 1 (factorial k)))))
(e-approx 3)

(define (prod f b n)
  (if (= b n)
      (f b)
      (* (f b) (prod f (+ b 1) n))))
(define (dfact n)
  (define (even x) (= (modulo x 2) 0))
  (if (even n)
      (prod (lambda (i) (* 2 i)) 1 (/ n 2))
      (prod (lambda (i) (- (* 2 i) 1)) 1 (/ (+ n 1) 2))))
(dfact 5)

(define (pow a b)
  (if (= b 0)
      1
      (* a (pow a (- b 1)))))
(pow 2 50)

(define (poww a b)
  (define (pow-tr a b)
     (if (= b 1)
         a
         (* (pow-tr a (- b 1)) a)))
  (pow-tr a b))
(poww 2 50)