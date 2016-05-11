#lang r5rs
(define (make-tree)
  (let ((bst '()));private data and functions
    (define (value n) (car n)) ;first element
    (define (pointers n) (cdr n)) ;rest of the elements
    (define (right n) (cdr (pointers n))) ;cdr of the rest of the elements
    (define (left n) (car (pointers n))) ;car of the rest of the elemts
    (define (make-tree value left right) ;make the tree cons-ing the value to the left and the right sub trees
      (cons value (cons left right)))
    (define (empty?) (null? bst));the methods
    (define (element? x) 
      (define (search n)
        (cond ((null? n) #f) ;if n is empty then its not the element
              ((eq? x (value n)) #t) ;if its equal to the search then yay truee
              ((< x (value n)) (search (left n))) ;if x is less then the value of n then search the left
              ((> x (value n)) (search (right n))))) ;otherwise search the right
      (search bst)) ;search it
    (define (insert x) 
      (let ((new-node (make-tree x '() '()))) ;making a new home for the new little guy
        (define (insert-internal n) ;helper
          (cond ((>= x (value n)) ;if the x is greater than or equal to the value 
                 (if (null? (right n)) ;check if the right is null, if so then set the cdr of pointers to be the new node
                     (set-cdr! (pointers n) new-node)
                     (insert-internal (right n))));otherwise check continuously down the right
                ((< x (value n)) ;if x is less than the value
                 (if (null? (left n)) ;and the left is empty
                     (set-car! (pointers n) new-node) ;set the car of the pointers to the new node
                     (insert-internal (left n)))))) ;otherwise keep traversing the list
        (if (null? bst) ;if the bst is empty
            (set! bst new-node) ;set bst to be that new node that you want to insert
            (insert-internal bst)))) ;otherwise call the insert internal function
    (define (list-elements) ;listing the elements
      (define (extract-sorted n)
        (if (null? n) ;if n is empty then return the list
            '()
            (append (extract-sorted (left n)) ;otherwise extract sorted on left appended with the middle
                    (list (value n))
                    (extract-sorted (right n)))));untill the right is reached and BST says itll be in order
      (extract-sorted bst))
    (define (dispatcher method) ;dispatcher passed back
      (cond ((eq? method 'insert) insert)
            ((eq? method 'element?) element?)
            ((eq? method 'list-elements) list-elements)
            ((eq? method 'empty?) empty?)))
    dispatcher))
                
              
