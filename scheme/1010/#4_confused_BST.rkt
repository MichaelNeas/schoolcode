#lang racket
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