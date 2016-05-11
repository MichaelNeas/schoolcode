#lang racket
(define (incrementer x)
  (lambda (z) (+ z x)))
(define add-5 (incrementer 5))
(add-5 10)

(define (new-account initial-balance)
  (let ((balance initial-balance))
    (define (deposit f)
      (begin 
        (set! balance (+ f balance))
        balance))
    (define (withdrawl f)
      (if (> f balance)
          "You broke"
          (begin 
            (set! balance (- balance f))
            balance)))
    (define (balance-inq)
      balance)
    (lambda (method)
      (cond ((eq? method 'deposit) deposit)
            ((eq? method 'withdraw) withdrawl)
            ((eq? method 'balance) balance-inq)))))
(define boa (new-account 100))
((boa 'deposit) 100)
((boa 'withdraw) 99)
((boa 'balance))

(define (set-object)
  (let ((S-object '()))
    (define (is-member? x)
      (define (member-iter remnant)
        (cond ((null? remnant) #f)
              ((eq? x (car remnant)) #t)
              (else (member-iter (cdr remnant)))))
      (member-iter S-object))
    (define (insert x)
      (set! S-object (cons x S-object)))
    (define (empty) (eq? S-object '()))
    (lambda (method)
      (cond ((eq? method 'member?) is-member?)
            ((eq? method 'insert) insert)
            ((eq? method 'object) S-object)
            ((eq? method 'empty) empty)))))
(define object (set-object))
((object 'empty))
((object 'insert) 2)
(object 'object)

(define (make-complex x y)
  (define (square z) (* z z))
  (define (length)
    (sqrt (+ (square x)
             (square y))))
  (define (real-part) x)
  (define (imag-part) y)
  (define (conjugate) (make-complex x (- y)))
  (define (add c) (make-complex (+ x ((c 'real-part)))
                                (+ y ((c 'imag-part)))))
  (lambda (method)
    (cond ((eq? method 'real-part) real-part)
          ((eq? method 'imag-part) imag-part)
          ((eq? method 'length) length)
          ((eq? method 'conjugate) conjugate)
          ((eq? method 'add) add))))
(define a (make-complex 2 3))
((a 'imag-part))
(define b ((a 'conjugate)))
((b 'imag-part))
((((b 'add) (make-complex 5 9)) 'length))

(define (hailstone x)
  (define (loop)
    (if (eq? (modulo x 2) 0)
        (begin (set! x (/ x 2))
               x)
        (begin (set! x (+ (* 3 x) 1))
               x)))
  loop)
(define hseq (hailstone 4))
(hseq)
(hseq)
(hseq)
(modulo 9 2)
(modulo 8 2)
(modulo 9 3)

(define (make-account b)
  (let ((balance b))
    (define (get) balance)
    (define (withdraw x) (set! balance (- balance x)))
    (define (deposit x) (set! balance (+ balance x)))
    (define (dispatch m)
      (cond ((eq? m 'get) get)
            ((eq? m 'withdraw) withdraw)
            ((eq? m 'deposit) deposit)))
    dispatch))
(define d (make-account 100))
((d 'deposit) 50)
((d 'get))


(define (make-stack)
  (let ((S '()))
    (define (empty) (null? S))
    (define (top) (car S))
    (define (pop) (let ((top (car S)))
                    (begin (set! S (cdr S))
                           top)))
    (define (push x) (set! S (cons x S)))
    (define (dispatcher method)
      (cond ((eq? method 'empty) empty)
            ((eq? method 'top) top)
            ((eq? method 'pop) pop)
            ((eq? method 'push) push)))
    dispatcher))
(define e (make-stack))
((e 'push) 4)
((e 'push) 5)
((e 'empty))
((e 'top))
((e 'pop))
((e 'top))



;(define (make-queue)
;  (let ((head '()) ;private data
;        (tail '()))
;    (define (value n) (mcar n)); private functions
;    (define (next n) (mcdr n))
;    (define (empty?) (null? head))
;    (define (front) (value head))
;    (define (enqueue x) ;methods
;      (let ((new-node (mcons x '())))
;        (begin
;          (if (empty?)
;              (set! head new-node)
;              (set-mcdr! tail new-node))
;          (mset! tail new-node))))
;    (define (dequeue)
;      (let ((return (value head)))
;        (if (eq? head tail)
;            (begin
;              (mset! head '())
;              (mset! tail '())
;              return)
;            (begin (mset! head (next head))
;                   return))))
;    (define (dispatcher method) ;dispatcher
;      (cond ((eq? method 'empty) empty?)
;            ((eq? method 'enqueue) enqueue)
;            ((eq? method 'dequeue) dequeue)
;            ((eq? method 'front) front)))
;    dispatcher))

(define (hailstone-r x)
  (define (loop)
    (if (eq? (modulo x 2) 0)
        (hailstone (/ x 2))
        (hailstone (+ (* 2 x) 1))))
  (define (head) x)
  (define (dispatcher method)
    (cond ((eq? method 'rest) loop)
          ((eq? method 'head) head)))
  dispatcher)
(define test (hailstone-r 100))
((test 'head))

(define (head s) (car s))
(define (rest s) ((cdr s)))

(define (delay something) (lambda () something))
(define (integer-stream x)
  (cons x
        (lambda () (integer-stream (+ 1 x)))))
        ;        (lambda () (integer-stream (+ 1 x)))))
(head (integer-stream 0))
(head (integer-stream 0))
(define (integers a b)
  (if (< b a) 
      '()
      (cons a (integers (+ a 1) b))))
(integers 3 6)

(define (filter f elements)
  (cond ((null? elements) '()) ;if the list of elements is empty return the list
        ((f (car elements)) ;otherwise apply f to the car of the list 
         (cons (car elements) ;if goodie then cons that value with the rest of the stream
               (filter f (cdr elements))))
        (else (filter f (cdr elements))))) ;or else just pass it by

(filter odd? (integers 1 50))
         
(define (fib-stream current next)
  (cons current 
        (lambda () (fib-stream next (+ next current)))))
(define fibs (fib-stream 0 1))
fibs
(head (rest (rest (rest fibs))))

(define (s-map f s)
  (if (null? s)
      '()
      (cons (f (car s))
            (delay (s-map f (rest s))))))

(define (triangulate stream)
  (if (null? stream)
      '()
      (cons (car stream)
            (lambda () (s-map (lambda (x) (+ x (car stream)))
                              (triangulate (rest stream)))))))

(define (vector-add v1 v2)
  (let ((result (make-vector (vector-length v1))))
    (do ((i 0 (+ i 1))) ;so starting from 0 up till the vector length
      ((>= i (vector-length v1)) result) ;if i is >= vector length give the result
      (vector-set! result i ;otherwise do vector-set! on result on the ith cell where you add the vector ref of that cell of the first to the second vector
                   (+ (vector-ref v1 i)
                      (vector-ref v2 i))))))
(define v (make-vector 4))
v
(vector-add '#(0 1 2 3) '#(1 2 3 4))

(define (zero-l v) (vector-set! v 1 0))
(define a1 (make-vector 3))
(vector-fill! a1 3)
a1
(zero-l a1)
a1
;change of pointers

(define (vector-sum v)
  (define (sum-accumulate i)
    (if (>= i (vector-length v))
        0
        (+ (vector-ref v i)
           (sum-accumulate (+ i 1)))))
  (sum-accumulate 0))
(vector-sum (vector 1 2 3 4 5))

(define (vector-sum-d v)
  (let ((result 0)) ;the result starts at 0
    (do ((index 0 (+ index 1))) ;index where you start from 0 and go up index +1
      ((>= index (vector-length v)) result) ;if the index is greater or equal to vector length then show result
      (set! result ;otherwise set! result 
            (+ result (vector-ref v index))))))

(vector-sum-d (vector 1 2 3 4 5))