#lang racket
;simple expressions in scheme
(+ 4 8 15 16 23 42)
(* 653854321 241304201)
(/ (+ 5 4 (- 2 (- 3 (+ 6 (/ 4 5)))))
   (* 3 (- 6 2) (- 2 7)))
;Functions
"Simple Functions"
;(define (absolute x) (sqrt (* x x)))
(define (absolute x)
  (if (< x 0)
      (* x -1)
      x))
(absolute -11)

(define (change-to-fahrenheit C)
  (+ (* (/ 9 5) C) 32))
(define (change-to-celsius F)
  (* (/ 5 9) (- F 32)))
(change-to-fahrenheit 10)
(change-to-celsius 10)

(define (discount price discount%)
  (- price (* price (/ discount% 100.0))))
(discount 89 12)

(define (discount-easy price percent)
  (* price (- 1 percent)))
(discount-easy 89 .12)

(define (tip bill)
  (ceiling (+ bill (* .15 bill)))) ;ceiling will round up
(tip 57.23)
 
(define latex 160000.0)
(define stains&varnishes 250000.0)

(define (how-many-gallons-to-cover type-of-cover length width)
  (define (how-many-gallons-to-cover-helper type length width count) 
    (if (< (* length width) type)
        (+ count (/ (* length width) type))
        (how-many-gallons-to-cover-helper (+ type type) length width (+ 1 count))))
  (how-many-gallons-to-cover-helper type-of-cover length width 0))
 
(how-many-gallons-to-cover latex 900 300)

"Recurssion---towers of hanoi (really)"
(define (print-move from to)
  (printf "Move disk from ~s to ~s~%" from to))

(define (towers-of-hanoi n source temp dest)
  (cond ((= n 0) "Done") ;when there's no more moves to make its done
        (else (begin (towers-of-hanoi (- n 1) source dest temp) ;recurssion of the function n -1 from the sorce using the destination to be a temperary holder
                     (print-move source dest) ;moves the source to the destination *which in this case is the temperary
                     (towers-of-hanoi (- n 1) temp source dest))))) ;then recurssively call the function allowing the temperary place to be the source and the source to be the temperary place and the destination to be the final place
(towers-of-hanoi 0 1 3 2)
(towers-of-hanoi 1 1 3 2)
(towers-of-hanoi 4 1 3 2)


(define (towers n origin dest temp)
  (cond ((not (= n 0))
         (begin (towers (- n 1) origin temp dest)
                (display "Move disk ")
                (display n)
                (display " to peg ")
                (display dest)
                (display #\newline)
                (towers (- n 1) temp dest origin)))))
(towers 4 'A 'B 'C)
  
  
"Tail Recursion"
(define (GCD-Euclids n1 n2)
  (define (GCD-helper n1 n2 GCD-holder)
    (if (and (integer? (and (/ n1 GCD-holder) 
                            (/ n2 GCD-holder)))
             (integer? (/ (absolute (- n1 n2)) GCD-holder)))
        GCD-holder
        (GCD-helper n1 n2 (- GCD-holder 1))))
  (GCD-helper n1 n2 (/ n1 2)))
(GCD-Euclids 252 105)
(GCD-Euclids 8 12)

(define (GCD a b)
  (cond ((= a 0) b)
        ((= b 0) a)
        ((< a b) (GCD a (- b a)))
        (else (GCD (- a b) b))))
(GCD 8 12)
         
"higher-order functions -- Practice"
(define (compose f g)
  (lambda (x) (f (g x)))) ;takes two functions f and g and put f of g of a variable
(define (repeated f n) ;this takes a function and a number when n iterations is done then print value of f
  (if (= n 1)
      f
      (compose f (repeated f (- n 1))))) ;use f and compose it as one function on repeated f on - n 1 for recursive properties
((repeated (lambda (x) (* x x)) 2) 5)

(define (smooth f dx)
  (lambda (x)
    (/ (+ (f (- x dx))
          (f x)
          (f (+ x dx))) ;average of everything...
       3)))

"LISt Processing:pairs"
(define (line p1 p2)
  (define slope (/ (- (cdr p2) (cdr p1))
                   (- (car p2) (car p1))))
  (define y-intercept (- (cdr p1) (* slope (car p1))))
  (cons slope y-intercept))
(line (cons 44.2 -22.8) (cons 25.2 34.2))


(define (UC num denom)
  (let ((x-num (floor (/ num 10))) ;x numerator is the numerator divided by 10 rounding down.
        (y-num (modulo num 10)) ;y numberator is the last value out of 10
        (x-denom (floor (/ denom 10)))
        (y-denom (modulo denom 10)))
    (if (= x-num y-denom) ;if x numerator is equal to the denominator 
        (= (/ num denom) (/ y-num x-denom)) ;check if numerator/denominator is equal to y num/ x denom
        #f)))
 
"Lists"
(define (largest-distance-from-adjacents lst)
  (define (largest-help l accum)
    (if (null? (cdr l))
        accum
        (largest-help (cdr l) (if (< accum (absolute (- (car l) (cadr l))))
                                  (absolute (- (car l) (cadr l)))
                                  accum))))
  (largest-help lst 0))
(largest-distance-from-adjacents '(1 5 2 9 10 7 21 12 98 101 79))

(define (difference-list lst)
  (if (null? (cdr lst)) ;if the cdr is empty give back the list
      (list)
      (cons (- (cadr lst) (car lst)) ;other wise cons the car of the cdr of the list - the car of the list with the recurssion of the list till its empty
            (difference-list (cdr lst)))))
(difference-list '(4 9 1 11 0 7))
 
 
(define (min-max LoN)
  (define (the-help L min-value max-value)
    (if (null? L)
        (cons min-value max-value)
        (the-help (cdr L) 
                  (if (> min-value (car L))
                              (car L)
                              min-value)
                  (if (< max-value (car L))
                      (car L)
                      max-value))))
  (the-help LoN (car LoN) (car LoN)))
(min-max '(1 8 2 9 4 10 5 100 299 6 -1))

;this one is bad
(define (list-unusual-canceling)
  (define (list-unusual-canceling-aux num denom)
    (cond ((> num 99) (list))
          ((> denom 99)
           (list-unusual-canceling-aux (+ num 1) 10))
          ((UC num denom) (cons (cons num denom)
                                (list-unusual-canceling-aux num (+ denom 1))))
          (else (list-unusual-canceling-aux num (+ denom 1)))))
  (list-unusual-canceling-aux 10 10))



(define (sum-list l)
  (define (sum-list-help lst accum)
    (if (null? lst)
        accum
        (sum-list-help (cdr lst) (+ accum (car lst)))))
  (sum-list-help l 0))

  (sum-list '(1 2 3 4 5))

(define (knapsack pack-list)
  (define (knap-help lst left-side right-side)
    (if (null? lst)
        (cons left-side right-side)
        (knap-help (cdr lst) 
                   (if (<= (sum-list left-side) (sum-list right-side))
                       (cons (car lst) left-side)
                       left-side)
                   (if (> (sum-list left-side) (sum-list right-side))
                       (cons (car lst) right-side)
                       right-side))))
  (knap-help pack-list '() '()))

(knapsack '(1 2 3 4 5 6 7 8 9 10))
  
"Trees"
(define (make-tree v left-tree right-tree)
  (list v left-tree right-tree))
(define (value T) (car T))
(define (left T) (cadr T))
(define (right T) (caddr T))

(define (insert x T)
  (cond ((null? T) (make-tree x '() '()))
        ((eq? x (value T)) T)
        ((< x (value T)) (make-tree (value T)
                                    (insert x (left T))
                                    (right T)))
        ((> x (value T)) (make-tree (value T)
                                    (left T)
                                    (insert x (right T))))))
(define (insert-all L T)
  (if (null? L)
      T
      (insert-all (cdr L) (insert (car L) T))))
(define mytree (insert-all '(4 3 2 5) '()))
mytree

(define (is-leaf? T)
  (and (null? (left T)) 
       (null? (right T))))

(define (count-one-child T)
  (cond ((is-leaf? T) 0)
        ((and (not (null? (left T))) (not (null? (right T))))
         (+ (count-one-child (left T))
            (count-one-child (right T))))
        ((null? (left T)) (+ 1 (count-one-child (right T))))
        (else (+ 1 (count-one-child (left T))))))

;
;(define (count-one-child tree) 
;  (let ((left-child (left tree))
;        (right-child (right tree))) 
;    (cond ((and (null? left-child)
;                (null? right-child)) 0) 
;          ((and (not (null? left-child))
;                (not (null? right-child))) 
;           (+ (count-one-child left-child)
;              (count-one-child right-child))) 
;          ((null? left-child)
;           (+ 1 (count-one-child right-child)))
;          (else (+ 1 (count-one-child left-child))))))
(count-one-child mytree)
      
(define (num-occurances T val)
  (if (null? T) 0
      (if (= (value T) val)
          (+ 1 
             (num-occurances (left T) val)
             (num-occurances (right T) val))
          (+ (num-occurances (left T) val) ;remeber the addition sign
             (num-occurances (right T) val)))))


(define (random-insert v t)
  (define (r-help v used)
    (if (= used (vector-length v)) 
        "done"
        (begin
          (let* ((raddress (random (vector-length v)))
                 (trial (vector-ref v raddress)))
            (cond ((not (number? trial)) (r-help v used))
                  (#t (begin
                        ((t 'insert) trial)
                        (vector-set! v raddress "Nan")
                        (r-help v (+ 1 used)))))))))
  (r-help v 0))
;7 page code, this takes hours on hours on hours on hours



"Singly-Linked Lists" ;needs work
(define (listInsert linkedlist index item)
  (define (next node) (mcdr (cdr node)))
  (define (prev node) (mcar (cdr node)))
  (define (set-next! node value)
    (let ((pointers (cdr node)))
      (set-mcar! pointers value)))
  (define (set-prev! node value)
    (begin
      (let ((pointers (cdr node)))
        (set-mcar! pointers value))))
  (define (find-index node index k)
    (begin
      (if (= index k) 
          (begin
            (display node)
            (newline)
            node)
          (find-index (next node) index (+ k 1)))))
  (define (linkIn ll nodeUp nodeNew)
    (let* ((downStream (next nodeUp)))
      (begin
        (set-prev! nodeNew nodeUp)
        (set-next! nodeNew downStream)
        (set-next! nodeUp nodeNew)
        (if (null? downStream)
            "nothing to do"
            (set-prev! downStream nodeNew))))
    ll)
  (let ((upstream (find-index linkedlist index 1)))
    (linkIn linkedlist upstream item)))








(define (position x linked-list)
  (define (pos-help link count)
    (if (eq? (car link) x)
        count
        (pos-help (cdr link) (+ 1 count))))
  (pos-help linked-list 0))

(define (Josephus-vacation circlinkedlist)
  (if (null? (cdr circlinkedlist))
      (car circlinkedlist)
      (Josephus-vacation (cdr circlinkedlist))))

             
"Doubly-Linked Lists"
;skipped josephus of doubly
"vectors"

(define first-vector (make-vector 4))
(vector-set! first-vector 0 1)
(vector-set! first-vector 1 2)
(vector-set! first-vector 3 4)
first-vector
(vector-length first-vector)
(length '())
(define (vector-to-list V)
  (define (vector-helper build-list k)
    (if (eq? (length build-list) (vector-length V))
        build-list
        (vector-helper (cons (vector-ref V k)
                             build-list)
                       (- k 1))))
  (vector-helper '() (- (vector-length V) 1)))
(vector-to-list first-vector)

;(define (vector-to-list vec) 
;  (let ((vlist (list)))
;    (do ((i (- (vector-length vec) 1) (- i 1)))
;      ((< i 0) vlist)
;      (set! vlist (cons (vector-ref vec i) vlist)))))
;(define myvector (vector 1 2 3 4 5 6)) 
;(vector-to-list myvector)
;(vector-to-list first-vector)
;do loop......

(define (vector-of-diff v)
  (let ((diff-vec (make-vector (- (vector-length v) 1))))
    (do ((i 0 (+ i 1)))
      ((>= i (vector-length diff-vec)) diff-vec)
      (vector-set! diff-vec i (- (vector-ref v (+ i 1))
                                 (vector-ref v i))))))


(define (wind-chill-temp T W)
  (- (+ 1.05 (* .93 T) (* 3.62 (sqrt W)) (* .103 T (sqrt W)) (* .0439 W W)) (* 3.65 W)))



"objects"

(define (new-account initial-balance) 
  (let ((balance initial-balance)
        (interestrate 0.01)) 
    (define (deposit f)
      (begin
        (set! balance
              (+ balance f))
        balance ))
    (define (withdraw f) 
      (begin
        (set! balance
              (- balance f))
        balance ))
    (define (bal-inq) balance)
    (define (accrue) (begin (set! balance 
                                  (+ balance (* balance 1 interestrate)))
                            balance))
    (define (setrate r) (set! interestrate r))
(lambda (method)
  (cond ((eq? method 'deposit) deposit)
        ((eq? method 'withdraw) withdraw)
        ((eq? method 'balance-inquire) bal-inq) 
        ((eq? method 'accrue) accrue)
        ((eq? method 'setrate) setrate)))))

"streams"


      

