#lang racket
"a"
(* (+ 11 22) (- 44 33))
(+ 4 8 15 16 23 42)
(+ (/ (- 3 2) 4) (* 5 (- 6 7)))
(+ (* 3 (- 4 (expt 2 15))))
(define (x-256 x)
  (let* ((x4 (* x x x x))
        (x16 (* x4 x4 x4 x4))
        (x64 (* x16 x16 x16 x16))
        (x256 (* x64 x64 x64 x64))) 
    x256))
(x-256 2)
"Answer"
"10, 5/6, 1440, 10, 48"
(- 15 5)
(+ (/ 1 2) (/ 1 3))
(* 18 8 10)
(- (+ 10 4) (* 4 1))
(* (+ 2 2) (/ (* (+ 3 5) (/ 30 10)) 2))
(define (even x) (= (modulo x 2) 0))
(even 7)
(even? 7)