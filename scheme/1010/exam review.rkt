#lang racket
;Chapters 1 -> 3 of SICP text.... 
;review your homework and lab assignments, try them all again
;Review the old exams and slides
;know dynamic scope for fun
;practice functional iterators, data structures that don't inclue pairs or lists
;tail recursion vs normal recursion
;A list is either empty or a pair where the car is a specific value and the cdr is everything else
;list processing skills, creating lists and all that good good, traverse and manipulation of lists
;first element of a list is easy, while getting the rest is difficult
;construction of lists requires and iterator most of the time, wrap this definition with a main...(helpers)
;map function -takes a function and applies it to each element of a list 
(map (lambda (x) (* x x)) '(0 1 2 3 4 5 6)) ;hands off unnamed function to map to apply it to the list
;sorthing algorithms, to understand technology, requires thought and structure. 
;remove smallest item and cons it to the front and and calling selection sort to the rest of the list
;quicksort, pivot element tha tpartitions a list to bigger and smaller, so u have two parts, recursion on the left and the right append them together
;more elaborate data structures, the tree is either empty or a node with pointers to sub trees
;BST (binary search tree) every node contains a number, left sub tree is smaller, right is larger
(define (make-tree value left right)
  (list value left right))
(define (value t) (car t))
(define (right t) (caddr t))
(define (left t) (cadr t));helps keep things in your head straight
;know the difference between the functional and the distructive version of tree insertion
;heap is another tree structure, which is another way to represent a list of numbers
;the heap inserts in the left and switches with the right, insuring that the smallest element is at the top
;smallest element is easiest to get in a heap compared to a normal tree
;removing an item from a heap is bound to re-heapifying things know heap extractor
;Abstract Data type- set, stack, queue,  set must offer insert and such, lists bst splay and hash
;constant time-optimal solution for set implementation...hash tables are the best
;for stack you push, top, pop and check if its empty, plate holder *u can do all these in constant time*
;the queue is like a stack but you add to the end and pull off the front, which makes a substantial change
;lead to mutable data so that we don't have to traverse down an entire list
;mutators build an enviornment holding a local variable with a bunch of methods which go to a dispatcher
;the only changes that are ever made to the local variable are due to the methods **** 
;set ADT defines an empty set and returns that object that maintains a set, setting up an enviroment
;this set is a list that gets altered, remember dispatch with tokens
;only perpose of the function sets up the world where this list can be altered
;the linked list queue has a pointer to the front of the queue and a pointer to the front of the queue
;this allows for enqueue and dequeue to occur in constant time
;UNDERSTAND THIS -- mutable, queue and linked list all together in one happy family
;streams are a convention for defining a data structure that looks like an inifnite list
;second part of the pair is "voodoo" that provides a function that allows termination
;know two vectors, how they work and how they are added together that can be printed with do iterators
;how the do function works...probably going to have to know how to do that 

(define (partition l pivot left right)
  (cond ((null? l) (cons left right)) ;empty list cons the left and the right together
        ((< (car l) pivot) (partition (cdr l) ;if the car of the list is less than the pivot number cons the car to the left side
                                      pivot 
                                      (cons (car l) left)
                                      right))
        (else (partition (cdr l)
                       pivot
                        left
                        (cons (car l) right))))) ;otherwise cons the car to the right
(define (qSort l)
    (if (null? l)
        l
        (let* ((pivot (car l)) ;pivot becomes the first item in the list
               (parts (partition (cdr l) pivot '() '()))  ;the parts of the sort rely on pivot
               (left (qSort (car parts)))  ;left can be the car of the parts right is the cdr
               (right (qSort (cdr parts))))
          (append left (cons pivot right))))) ;append the left with the pivot and the right
(qSort '(5 9 1 8 3 7 0 2))

(define (vector-add v1 v2)
  (let ((new-v (make-vector (vector-length v1))))
    (define (adder k)
      (if (< k (vector-length v1))
          (begin (vector-set! new-v k (+ (vector-ref v1 k)
                                          (vector-ref v2 k)))
                 (adder (+ k 1)))
          new-v))
    (adder 0)))
(define a (vector 1 2 3 4 5 6))
a
(vector-ref a 0)
(vector-set! a 2 (+ (vector-ref a 5)
                    (vector-ref a 4)))
a
(vector-set! a 5 9)
a
(vector-length a)
(define b (mcons 5 2))
(set-mcdr! b 8)
b
(set! b 4)
b

(define (sieve k numbers)
  (cond ((null? numbers) '())
        ((integer? (/ (car numbers) k))
         (sieve k (rest numbers)))
        (else (cons (car numbers) (lambda () (sieve k (rest numbers)))))))

(define (kill x) (set! x 0))
(define ab 4)
(begin (kill ab)
       ab)
ab
