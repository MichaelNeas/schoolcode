#lang racket
"stream-review"
(define (first str) (car str))
(define (rest str) ((cdr str)))
(define (int-stream a)
  (cons a (lambda () (int-stream (+ 1 a)))))
(define a (int-stream 5))
a
(rest a)
(rest (rest a))
(first (rest (rest a)))
(rest (rest (rest (rest (rest (rest a))))))

"1"
(define (triangle-numbers start-at)
  (define (tri-stream x n)
    (cons x (lambda () (tri-stream (+ x n) (+ n 1)))))
  (tri-stream start-at (+ start-at 1)))

(define testing (triangle-numbers 1))
(rest testing)
(rest (rest testing))
(rest (rest (rest testing)))

"2"
(define (squared-numbers start-at)
  (define (square-stream x n)
    (cons x (lambda () (square-stream (+ x n) (+ n 2)))))
  (square-stream start-at (- (* (+ (sqrt start-at) 1) 2) 1)))
(define test (squared-numbers 1))
test
(rest test)
(rest (rest test))

"3"
(define (geometric-sequence start comm-ratio)
  (define (geo-stream a r n)
    (cons a (lambda () (geo-stream (* a r) (expt r n) (+ n 1)))))
  (geo-stream start comm-ratio 1))
(define test-g (geometric-sequence 2 3))
test-g
(rest test-g)
(rest (rest test-g))
(rest (rest (rest test-g)))

"4"
(define (Frat-Bro start)
    (cons (/ (+ (* start start) start 2) 2) (lambda () (Frat-Bro (+ start 1)))))
 
(define bro-test (Frat-Bro 0))
bro-test
(rest bro-test)
(rest (rest bro-test))
(rest (rest (rest bro-test)))
(rest (rest (rest (rest bro-test))))
(rest (rest (rest (rest (rest bro-test)))))
(rest (rest (rest (rest (rest (rest bro-test))))))
(rest (rest (rest (rest (rest (rest (rest bro-test)))))))
(rest (rest (rest (rest (rest (rest (rest (rest (rest (rest (rest (rest bro-test))))))))))))
              