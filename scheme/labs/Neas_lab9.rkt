#lang racket
"1"
(define a 2)
a
(set! a 3)
a

(define (factorial n)
  (define goal 1)
  (define accum 1)
  (define (loop)
    (if (> goal n)
        accum
        (begin (set! accum (* accum goal))
               (set! goal (+ 1 goal))
               (loop))))
  (loop))
(factorial 5)
(factorial 4)
(factorial 3)
(factorial 2)

"2"
(define (hailstone n)
  (define (loop)
    (if (= n 1)
        (newline)
        (if (= (modulo n 2) 0)
            (begin (set! n (/ n 2))
                   (display n)
                   (display #\space)
                   (loop))
            (begin (set! n (+ (* 3 n) 1))
                   (display n)
                   (display #\space)
                   (loop)))))
  (begin (display n) 
         (display #\space) 
         (loop)))
(hailstone 7)

(define (hailstone-newline n)
  (define (loop)
    (if (= n 1)
        (display 'done)
        (if (= (modulo n 2) 0)
            (begin (set! n (/ n 2))
                   (display n)
                   (newline)
                   (loop))
            (begin (set! n (+ (* 3 n) 1))
                   (display n)
                   (newline)
                   (loop)))))
  (begin (display n) 
         (newline) 
         (loop)))
(hailstone-newline 7)

(newline)

"3"
(define (new-account initial-balance)
   (let ((balance initial-balance)
         (rate 0.01))
     (define (deposit d)
       (begin 
         (set! balance (+ balance d))
         balance))
     (define (withdraw w)
           (begin
             (set! balance (- balance w))
             balance))
     (define (bal-ing) balance)
     (define (accrue) (begin
                        (set! balance (+ (* balance rate) balance))
                        balance))
     (define (setrate n) (set! rate n))
                        
         (lambda (method)
           (cond ((eq? method 'withdraw) withdraw)
                 ((eq? method 'accrue) accrue)
                 ((eq? method 'deposit) deposit)
                 ((eq? method 'setrate) setrate)
                 ((eq? method 'balance-inquire) bal-ing)))))

(define checking (new-account 1000))
((checking 'deposit)200)
((checking 'withdraw)1199)
((checking 'deposit) 99)
((checking 'withdraw) 110)
((checking 'deposit) 1010)
((checking 'balance-inquire))
((checking 'accrue))
((checking 'setrate) .05)
((checking 'accrue))


"4" ;already have a checking account so now we open a savings account!

(define savings (new-account 500))
((savings 'deposit) 100)
((checking 'deposit) 100)

"5"
(define (set-object)
  (let ((my-tree '()))
(define (make-tree v left right)
  (list v left right))
(define (value t) (car t))
(define (left t) (cadr t))
(define (right t) (caddr t))

(define (element? x T)
  (if (null? T)
      #f
      (if (= x (value T))
          #t
      (if (< x (value T))
          (element? x (left T))
          (element? x (right T))))))

(define (insert x T)
  (cond ((null? T) (make-tree x '() '()))
        ((eq? x (value T)) T)
        ((< x (value T)) (make-tree (value T)
                                    (insert x (left T))
                                    (right T)))
        (else (make-tree (value T)
                         (left T)
                         (insert x (right T))))))

(define (extract-sorted T)
  (if (null? T)
      '()
      (append (extract-sorted (left T))
              (list (value T))
              (extract-sorted (right T)))))
      

    (define (h-element? x)
      (element? x my-tree))
    (define (display-tree)
      my-tree)
    (define (h-insert x) 
      (set! my-tree (insert x my-tree)))
    (define (extract-sort)
      (extract-sorted my-tree))
    (lambda (method)
      (cond ((eq? method 'element?) h-element?)
            ((eq? method 'insert) h-insert)
            ((eq? method 'displayt) display-tree)
            ((eq? method 'extract-sorted) extract-sort)))))

(define thistree (set-object))
((thistree 'insert) 5)
((thistree 'insert) 2)
((thistree 'insert) 9)
((thistree 'insert) 1)
((thistree 'insert) 8)
((thistree 'insert) 3)
((thistree 'displayt))
((thistree 'extract-sorted))
((thistree 'element?) 8)
      





        
