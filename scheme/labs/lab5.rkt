#lang racket
"1"

(define (num-sq x) (cons x (* x x)))
(num-sq 3)

"2"
(define (square x) (* x x))
(define (square-1 lst) 
  (if (null? lst) ; dont put an equal sign
       (list)
      (cons (square (car lst))
            (square-1 (cdr lst))) 
      )
  )
(square-1 '(1 2 3 4))

"3"
(define (sum-1 lst)
  (if (empty? lst)
      0
      (+ 1
         (sum-1 (cdr lst)))))
(sum-1 (list 1 2 3 4 9))

"4"
(define (range p)
  (if (> (car p) (cdr p))
       (list)
       (cons (car p) (range (cons (+ (car p) 1) 
                                  (cdr p))))))
(range (cons 0 10))

"5"
(define (sv-mult a x)
  (if (null? x)
      (list)
  (cons (* a (car x))
        (sv-mult 2 (cdr x)))
     )
  )

  
(sv-mult 2 (list 5 12 4))         
  
"6"
(define (v-add a b)
  (if (null? b)
      (list)
  (cons (+ (car a) (car b))
        (v-add (cdr a) (cdr b)))
  )
  )
(v-add (list 5 4 23) (list 20 21 2))

"7"
(define (v-sub a b)
    (if (null? b)
      (list)
  (cons (- (car a) (car b))
        (v-sub (cdr a) (cdr b)))
  )
  )
(v-sub (list 20 421 213) (list 20 21 2))

"8"
(define (dot-product a b)
  (if (null? b)
      0
    (+ (* (car a) (car b))
       (dot-product (cdr a) (cdr b)))))
(dot-product (list 1 2 3) (list 1 2 3))  