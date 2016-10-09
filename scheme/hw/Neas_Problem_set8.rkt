#lang racket
"1"
(define (reverse lst)
  (if (null? lst)
      '()
      (append (reverse (cdr lst)) 
          (list (car lst)))))
(reverse '(1 2 3 4 5)) ;there is a reverse function in racket but for the practice..
;these two functions are essential in order to allow the program to have recursive properties
(modulo (+ 9 8 1) 10)
(quotient (- 13 3) 10)
(define (car-help lst) (if (null? lst) ;if the car of the list is empty then 0
                           0 
                           (car lst))) ;otherwise give the car of the list until there are no more values in the list
(define (cdr-help lst) (if (null? lst) ;similar to above accept if the list is empty then tell the computer it is empty to finalize recursion
                           empty 
                           (cdr lst)));otherwise give the cdr of the list until its empty for each call

(define (apa-add l1 l2)
    (define (apa-add-help l1 l2 var-move solution) ;for my helper i chose a tail recurssive method because I'm better at tail recursion, this helper function will take the lists and it will hold what value to carry over during the addition, then there is the solution which starts in an empty set
    (if (and (null? l1) (null? l2) (= 0 var-move)) ;if the first and second lists are empty and there is no more values of 10 to move in the addition we can have the solution
          solution
          (apa-add-help (cdr-help l1) (cdr-help l2) ;otherwise call the helper function on the cdr's of both list to get the recursion started
                        (quotient (- (+ (car-help l1) (car-help l2) var-move) ;the quotient allows for the production of a value 0-9 in a place of the list, in this situation you subtract the 10's place from the total between the first list, second list, and the carried value (ie. 1) and compare it to 10 to show which value fits in that part of the list
                                     (modulo (+ (car-help l1) (car-help l2) var-move) 10)) 10) ;this first modulo will allow for us to know if the one must be carried over
                        (cons (modulo (+ (car-help l1) (car-help l2) var-move) 10) ;then you cons together a list with the modulo from the addition to the solution, allowing for the '1' to be used in the next recurring step
                              solution))))
  (apa-add-help (reverse l1) (reverse l2) 0 '())) ;the hint said to reverse the lists to allow for easier nav of variables and I started with no need to move a variable to the left because its an empty list...

(apa-add '(4 7 9) '(7 8 4))
(apa-add '(1 2 5 8) '(2 5 2))

"2"
(define (d-mult lst n)
  (define (d-mult-help lst n var-move solution)
    (cond ((= n 0) '())
          ((= n 1) lst)
          ((and (null? lst) (= var-move 0)) 
           solution)
          (else (d-mult-help (cdr-help lst) n
                        (quotient (- (+ (* (car-help lst) n) var-move)
                                     (modulo (+ (* (car-help lst) n) var-move) 10)) 10)
                        (cons (modulo (+ (* (car-help lst) n) var-move) 10)
                              solution)))))
  (d-mult-help (reverse lst) n 0 '()))

(d-mult '(1 2 3) 3)
(d-mult '(9 9 9) 4)  

"3"
(d-mult '(1 2 3) 6)
(d-mult (d-mult '(1 2 3) 5) 10)
(d-mult (d-mult (d-mult '(1 2 3) 4) 10) 10) 

(define (apa-mult l1 l2)
  (define (apa-mult-help l1 l2)
    (if (null? l2)
        '()
        (apa-add (d-mult l1 (car l2))
                 (d-mult (apa-mult-help l1 (cdr l2))
                         10))))
                                                
                                                   
  (apa-mult-help l1 (reverse l2)))
  
(apa-mult '(1 2 3) '(4 5 6))
(apa-mult '(4 8 1) '(9 2 4))


"4"
(define (value T) (car T))
(define (right T) (caddr T))
(define (left T) (cadr T))

(define (make-tree value left right)
  (list value left right)) 

(define (rotate-right tree)
  (if (null? tree)
      '()
      (if (null? (left tree))
          (rotate-left tree)
          (make-tree (value (left tree))
                     (left  (left tree))
                     (make-tree (value tree) (right (left tree)) (right tree))))))

(define (rotate-left tree)
  (if (null? tree)
      '()
        (if (null? (right tree))
            (rotate-right tree)
            (make-tree (value (right tree))
                       (make-tree (value tree) (left tree) (left (right tree)))
                       (right (right tree))))))
(define (depth tree) 
  (if (null? tree) 
      0 
      (if (and (null? (left tree)) (null? (right tree)))
          0
      (+ 1 (max(depth(left tree))(depth(right tree))))))) 

  
(define (tree-repair BST)
  (cond ((or (= (depth (left BST)) (depth (right BST)))
             (= (depth (left BST)) (+ (depth (right BST)) 1))
             (= (depth (right BST)) (+ (depth (left BST)) 1)))
         BST)
        ((> (depth (left BST)) (+ (depth (right BST)) 1)) (tree-repair (rotate-right BST)))
        ((> (depth (right BST)) (+ (depth (left BST)) 1)) (tree-repair (rotate-left BST))))) 

(define (insert x T)
  (cond ((null? T) (make-tree x '() '()))
        ((eq? x (value T)) T)
        ((< x (value T)) (make-tree (value T)
                                    (insert x (left T))
                                    (right T)))
        ((> x (value T)) (make-tree (value T)
                                    (left T)
                                    (insert x (right T))))))

;implimentation 
(define (insert-all lst T)
  (if (null? lst)
      T
      (insert-all (cdr lst) (insert (car lst) T))))

(define test (insert-all '(7 4 3 2 5 6) '()))
test
(define smallt (insert-all '(5 1 2 3 7) '()))
smallt
(rotate-right smallt)

(tree-repair test)


"5"
(define (create-heap v H1 H2)
   (list v H1 H2))
(define (h-min H) (car H))

(define (insert-h x H)
   (if (null? H)
       (create-heap x '() '())
       (let ((child-value (max x (h-min H)))
             (root-value  (min x (h-min H))))
         (create-heap root-value
                      (right H)
                      (insert-h child-value (left H))))))
(define (insert-list lst H) 
  (if (null? lst)
      H
      (insert-list (cdr lst) (insert-h (car lst) H))))
(define (combine-heaps H1 H2)
    (cond ((null? H1) H2)
          ((null? H2) H1)
          ((< (h-min H1) (h-min H2))
           (create-heap (h-min H1)
                        H2
                        (combine-heaps (left H1) (right H1))))
          (else
           (create-heap (h-min H2)
                        H1
                        (combine-heaps (left H2) (right H2))))))
(define (remove-minimum H)
   (combine-heaps (left H) (right H)))
(define (extract-min H)
  (cons (h-min H)(remove-minimum H)))
(define myheap (insert-list '(1 9 2 5 8 18 4 6 7) '()))
myheap

(define (heapsort H)
  (if (null? H)
      '()
      (cons (car (extract-min H))
              (heapsort (cdr (extract-min H))))))
(heapsort myheap)