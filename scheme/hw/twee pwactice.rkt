#lang racket

(define (make-tree value left right)
  (list value left right))


(define (value t) (car t))
(define (right t) (caddr t))
(define (left t) (cadr t))

(define (element? x t)
  (cond ((null? t) #f)
        ((eq? x (value t)) #t)
        ((< x (value t)) (element? x (left t)))
        ((> x (value t)) (element? x (right t)))
        ))

(define (insert x t)
  (cond ((null? t) (make-tree x '() '()))
        ((eq? x (value t)) t)
         ((< x (value t)) (make-tree (value t)
                                    (insert x (left t))
                                    (right t)))
         ((> x (value t)) (make-tree (value t)
                                     (left t)
                                     (insert x (right t))))
         )
        )

(define twee (make-tree 1 '() '()))
twee