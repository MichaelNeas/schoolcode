#lang racket
"Priority-que"
(define (priority-queue f)
  (let ((heap '())
        (q-size 0))
    (define (create-heap h-min left right)
      (list h-min left right))
    (define (h-min H) (car H))
    (define (left H) (cadr H))
    (define (right H) (caddr H))
    
    (define (insert x H)
      (if (null? H)
          (create-heap x '() '())
      (if (f x (h-min H))
          (create-heap x 
                       (insert (h-min H) (right H))
                       (left H))
          (create-heap (h-min H)
                       (insert x (right H))
                       (left H)))))
    
    (define (combine-heaps h1 h2)
      (cond ((null? h1) h2)
            ((null? h2) h1)
            ((f (h-min h1) (h-min h2))
             (create-heap (h-min h1)
                          h2
                          (combine-heaps (left h1) (right h1))))
            (else
             (create-heap (h-min h2)
                          h1
                          (combine-heaps (left h2) (right h2))))))
    
    (define (empty) (null? heap))
    
    (define (insert-this x)
      (begin (set! q-size (+ 1 q-size))
             (set! heap (insert x heap))))
    
     (define (extract-min-this)
      (let ((min-value (h-min heap)))
        (begin (set! q-size (- q-size 1))
               (set! heap (combine-heaps (left heap) (right heap))))
        min-value))
    
    (define (current-size) q-size)
    
       
    
    (lambda (method)
      (cond ((eq? method 'empty?) empty)
            ((eq? method 'insert) insert-this)
            ((eq? method 'extract-min) extract-min-this)
            ((eq? method 'size) current-size)
            ((eq? method 'give-heap) heap)))))


(define test (priority-queue <))
((test 'insert) 1)
((test 'insert) 2)
((test 'insert) 3)
((test 'insert) 4)
((test 'insert) 5)
((test 'insert) 4)
((test 'insert) 2)
((test 'extract-min))
(test 'give-heap)
((test 'extract-min))
(test 'give-heap)
((test 'size))
((test 'empty?))
((test 'extract-min))
(test 'give-heap)
((test 'extract-min))
(test 'give-heap)
((test 'size))
((test 'empty?))
((test 'extract-min))
(test 'give-heap)
((test 'extract-min))
(test 'give-heap)
((test 'size))
((test 'empty?))
((test 'extract-min))
(test 'give-heap)
((test 'size))
((test 'empty?))
    

 (define (huff-tree<? ht1 ht2)
      (define (weight huff-tree)
        (if (null? huff-tree)
            0
            (cdar huff-tree)))
      (< (weight ht1) (weight ht2)))
 
(define hufftest (priority-queue huff-tree<?))
((hufftest 'insert) (list (cons #\a 2) '() '()))
((hufftest 'insert) (list (cons #\b 1) '() '()))
((hufftest 'insert) (list (cons #\c 3) '() '()))
((hufftest 'extract-min))
(hufftest 'give-heap)
    
    