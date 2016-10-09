#lang racket
(define (der f h)
 (lambda (x)
 (/ (- (f(+ x h)) (f x))
    h)))
(define (newtons-method2 f guess n)
(define (newton-transform f)
 (lambda (x)
  (- x (/ (f x) ((der f 0.5) x)))))
(let ((next (newton-transform guess)))
(if (= 0 n)
    next
    (newtons-method2 f next (- n 1)))))
(newtons-method2 (lambda (x) (+ (* 2 x) 1)) 1 100) 
(newtons-method2 (lambda (x) (- (* x x) x 1)) 2 100)

(define (newtons-method f guess n)
(define (newtons-method-h guess k)
 (if(= k n)
    guess
    (let ((next (- guess (/ (f guess) ((der f 0.1) guess)))))
    (newtons-method-h next (+ k 1)))))
  (newtons-method-h guess 0))
 
(define (sqrt-newt n)
 (newtons-method (lambda (x) (- (* x x) n)) 1.0 40))
 
 (sqrt-newt 2)
 