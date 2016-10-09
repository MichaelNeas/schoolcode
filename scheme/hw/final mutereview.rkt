#lang racket
(define a 3)
a
(set! a 4)
a
(set! a 5)
a
;begin will carry out a sequence of function calls, good for looping and it returns the value of the last call
(define (incrementer x)
  (lambda (z) (+ x z)))
(define add-5 (incrementer 5))
(add-5 13)
(define (make-hidden-value initial-value)
  (let ((value initial-value))
    (define (mult x) (begin
                       (set! value (* value x))
                       value))
    (define (add-to y) (begin 
                         (set! value (+ value y))
                         value))
    (cons mult add-to)))
(define function-pair (make-hidden-value 100))
((car function-pair) 5)
((cdr function-pair) 50)
;it chooses which of the pair to evaluate the number with
;destructive assignment is carried out behind an abstraction barrier is object-oriented programming
;pretty much showing that you don't change the original value directly
;token values are strings that can be only evaluated with equality
;there are methods that go in and define posibilities for the dispatcher when the token is called
;double the parrentheses when called upon if there is no parameters
;first-order, there is a bunch of calls that directly affect one number
;object implementation refers to lists
;ADT is an abstract data type which is a mathematical model for certain classes of data structures
;a data structure is a particular way of storing and organizing data on a computer
(define (set-object)
  (let ((S-list '()))
    (define (is-member? x)
      (define (member-iter remnant)
        (cond ((null? remnant) #f)
              ((eq? x (car remnant)) #t)
              (else (member-iter (cdr remnant)))))
      (member-iter S-list))
    (define (insert x)
      (set! S-list (cons x S-list)))
    (define (empty) (eq? S-list '()))
    (lambda (method)
      (cond ((eq? method 'empty) empty)
            ((eq? method 'member) is-member?)
            ((eq? method 'give-list) S-list)
            ((eq? method 'insert) insert)))))
(define S (set-object))
((S 'empty))
((S 'insert) 1)
((S 'insert) 0)
((S 'insert) 4)
(S 'give-list)

(define (make-complex x y) ;x and y are private variables that are altered later
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
(define c (make-complex 5 2))
((c 'real-part))
(define d ((c 'conjugate)))
((d 'imag-part))
((((d 'add) (make-complex 5 6)) 'length)) ;add that complex with d complex and give the length
(define (hailstone x)
  (define (loop)
    (if (eq? (modulo x 2) 0)
        (begin (set! x (/ x 2))
               x)
        (begin (set! x (+ (* 3 x) 1)) 
               x)))
  loop)

(define hseq (hailstone 25))
(hseq)
(hseq)
(hseq)
(hseq)
(hseq)
(hseq)
;distruction of the number manipulates x directly
;object example
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
(define e (make-account 100))
((e 'deposit) 50) ;could use begin to avoid having to use get
((e 'get))
;stack abstract data type, it models a stack of objects you can remove the top object, pop it off, or push on a new object.
(define (make-stack)
  (let ((S '()))
    (define (empty?) (null? S))
    (define (top) (car S))
    (define (pop) 
      (let ((top (car S)))
        (begin (set! S (cdr S))
               top)))
    (define (push x) (set! S (cons x S)))
    (define (dispatcher method)
      (cond ((eq? method 'top) top)
            ((eq? method 'pop) pop)
            ((eq? method 'push) push)
            ((eq? method 'give) S)
            ((eq? method 'empty) empty?)))
    dispatcher))
(define f (make-stack))
((f 'push) 2)
((f 'push) 1)
((f 'empty))
(f 'give)
((f 'top))
((f 'pop))
((f 'top))
;a stack is rather simple, you only have access to the top item and can choose what to do...
;as for the queue, u can either take of the top or put on the bottom like a line at the teller
;so scheme maintains a pair as a tuple of two pointers, this is useful because all pairs have the same size, pointers are just names of memory addresses that contain the objects
;in an array a memory cell holds a primitive SCHEME value of any kind
;so each pair has these pointers that fill cells with inputs
(define tail (mcons 0 1))
(define first (cons 'first tail))
(define second (cons 'second tail))
(set-mcar! tail 10)
first;the car pointer points to first, while the cdr points to the pair "tail" which points to teh values
second
;the set! provides the ability to change the direction of the pointer
(define g (list 1))
;can set an infinite loop by setting the cdr of a pair to be the car...and scheme would cry
;in racket we must use mcons, set-mcar!, and set-mcdr! because racket is a dick
;A queue is *linked* list which is always attached to eachother one by one
;each node of a doubly linked listmust remember two things, a value and a pointer to its successor
;maintaining the queue revolves around head and tail, head points to front, tail to back
(define (make-queue)
  (let ((head '())
        (tail '()))
    (define (value n) (car n))
    (define (next n) (cdr n))
    (define (empty?) (null? head))
    (define (front) (value head))
    (define (enqueue x)
      (let ((new-node (cons x '())))
        (begin
          (if (empty?)
              (set! head new-node)
              (set-mcdr! tail new-node))
          (set! tail new-node))))
    (define (dequeue)
      (let ((return (value head)))
        (if (eq? head tail)
            (begin (set! head '())
                   (set! tail '())
                   return)
            (begin (set! head (next head))
                   return))))
    (define (dispatcher method)
      (cond ((eq? method 'empty) empty?)
            ((eq? method 'enqueue) enqueue)
            ((eq? method 'dequeue) dequeue)
            ((eq? method 'front) front)))
    dispatcher))
;would work perfectly but the stupid racket mcons bs gets in the way
;so what should happen is every time there is enqueue the new pointer points to a new tail the value of whatever was added on
;this further goes into trees, which is a little scary...
;A stream is an abstact data type that consists of empty? head nad rest with the cdr as an infinite list
(define (head s) (car s))
(define (rest s) ((cdr s)))
(define (int-stream x)
  (cons x (lambda () (int-stream (+ 1 x)))))
;the procedure in the cdr allows for rest to be called and u to gain infinitely many information
(define (delay something)
  (lambda () something))
;lambda in streams provides a way to 'delay' the stream
(define (force c) c) ;gives the first value of the procedure
;we did a lot of works with streams so i'm gonna move to vectors now......
;A vector has a fixed length that is determined when it is constructed, the constructor takes a single numeric argument---the number of slots
;once vector is made the number of slots will never change
(define v (make-vector 4))
(vector-set! v 0 'a)
(vector-set! v 1 'b)
(vector-set! v 2 'c)
(vector-set! v 3 'd)
v
;with the vector made with 4 slots and 4 slots only starting at 0
(vector-ref v 2)
;have fixed length, but this allows retrieval in single time step, otherwise called arrays
;lists have pointers to other pairs on pairs, while a vector has individual pointers on numbers
;vectors CANNOT grow but you can add another vector....
(define (vector-add v1 v2)
  (let ((result (make-vector (vector-length v1))))
    (define (add-iterator k)
      (if (< k (vector-length v1))
          (begin (vector-set! result k
                              (+ (vector-ref v1 k)
                                 (vector-ref v2 k)))
                 (add-iterator (+ k 1)))
          result))
    (add-iterator 0)))
;not very easy to follow but we have a do command
;do ----- (do ((<var1> <init1> <step1.)
             ;...)
            ;(<test> <return-expression>)
            ;<command> ....)
;init, variables are bound to the values and the iteration phase begins kind of like forloops
(define (vector-add2.0 v1 v2)
  (let ((result (make-vector (vector-length v1))))
    (do ((i 0 (+ i 1)))
      ((>= i (vector-length v1)) result)
      (vector-set! result i
                   (+ (vector-ref v1 i)
                      (vector-ref v2 i))))))


          


