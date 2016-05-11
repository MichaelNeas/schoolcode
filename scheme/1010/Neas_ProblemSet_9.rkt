#lang racket
"1"
(define (basic-set)
  (let ((my-list '()))
    (define (mydelete x)
      (define (delete x li)
        (if (null? li)
            '()
            (if (eq? x (car li))
                (delete x (cdr li))
              (cons (car li)
                    (delete x (cdr li))))))
      (set! my-list (delete x my-list)))
    (define (element? x)
      (define (iselement? x li)
        (if (null? li)
            #f
            (if (eq? x (car li))
              #t
              (iselement? x (cdr li)))))
      (iselement? x my-list))
    (define (insert x)
      (set! my-list (cons x my-list)))
    (define (isempty?)
      (eq? my-list '()))
    (define (givelist)
      my-list)
    (lambda (method)
      (cond ((eq? method 'element?) element?)
            ((eq? method 'delete) mydelete)
            ((eq? method 'insert) insert)
            ((eq? method 'givelist) givelist)
            ((eq? method 'empty) isempty?)))))
(define newset (basic-set))
((newset 'empty))
((newset 'insert) 2)
((newset 'empty))
((newset 'insert) 3)
((newset 'element?) 3)
((newset 'givelist))
((newset 'insert) 5)
((newset 'givelist))
((newset 'delete) 3)
((newset 'givelist))
((newset 'empty))
((newset 'insert)9)
((newset 'insert)8)
((newset 'givelist))
((newset 'element?)2)
((newset 'delete) 5)
((newset 'givelist))
((newset 'delete) 8)
((newset 'delete) 2)
((newset 'delete) 9)
((newset 'empty))
((newset 'delete) 15)
((newset 'givelist))

"2"
(define (stat-set)
  (let ((stat-list '())
        (current-min 0)
        (current-max 0)
        (myaverage 0))
    (define (empty?)
      (eq? stat-list '()))
    (define (insert x)
      (begin (set! stat-list (cons x stat-list))
             (if (= current-length 0)
                 (begin 
                   (set! current-max x) 
                   (set! current-min x))
                 (begin
                   (set! current-max current-max) 
                   (set! current-min current-min)))
             (set! current-length (+ 1 current-length))
             (set! current-sum (+ x current-sum))
             (set! myaverage (/ current-sum current-length))
             (if (> x current-max)
                 (set! current-max x)
                 (set! current-max current-max))
             (if (< x current-min)
                 (set! current-min x)
                 (set! current-min current-min))))
    
    (define (element? x)
      (define (iselement? x li)
        (if (null? li)
            #f
            (if (eq? x (car li))
              #t
              (iselement? x (cdr li)))))
      (iselement? x stat-list))
    
    (define current-length 0)
    (define current-sum 0)
    
    (lambda (method)
      (cond ((eq? method 'empty?) empty?)
            ((eq? method 'insert) insert)
            ((eq? method 'element?) element?)
            ((eq? method 'largest) current-max)
            ((eq? method 'smallest) current-min)
            ((eq? method 'size) current-length)
            ((eq? method 'average) myaverage)
            ((eq? method 'sum) current-sum)
            ((eq? method 'givelist) stat-list)))))
(define num2 (stat-set))
((num2 'empty?))
((num2 'insert) 2)
((num2 'insert) 7)
((num2 'insert) 210)
((num2 'insert) 21)
((num2 'insert) 1)
((num2 'insert) 249)
((num2 'insert) 24)
((num2 'insert) 4)
((num2 'insert) 3)
((num2 'empty?))
(num2 'givelist)
(num2 'largest)
(num2 'smallest)
(num2 'size)
(num2 'sum)
(num2 'average)
((num2 'element?) 21)
((num2 'element?) 100)

;"If we had to impliment a delete function then the current-length would need to subtract 1 once a deletion occurs, and all the other private variables must be rechecked to find the next thing in the list that may need to replace it and the sum would have to be subtracted, it really is much more of a hastle than a good thing, though I don't think it'd be impossible by any means."


"3"
(define (multiset)
  (let ((myset '()))
    
    (define (empty?)
      (eq? myset '()))
    
    (define (insert x)
      (set! myset (cons x myset)))
              
    
    (define (multiplicity x)
      (define (multiplicity-helper x lst accum)
        (if (null? lst)
            (cons x
                  accum)
            (if (= x (car lst))
                (multiplicity-helper x (cdr lst) (+ 1 accum))
                (multiplicity-helper x (cdr lst) accum))))
      (multiplicity-helper x myset 0))
    
    (define (delete-one x)
      (define (delete-help x lst accum)
        (if (null? lst)
            '()
            (if (eq? x (car lst))
                (append accum
                      (cdr lst))
                (delete-help x (cdr lst) (cons (car lst) accum)))))
      (set! myset (delete-help x myset '())))
        
    
    
    (define (delete-all x)
      (define (delete-help x li)
        (if (null? li)
            '()
            (if (eq? x (car li))
                (delete-help x (cdr li))
              (cons (car li)
                    (delete-help x (cdr li))))))
      (set! myset (delete-help x myset)))   
    
    (lambda (method)
      (cond ((eq? method 'empty?) empty?)
            ((eq? method 'insert) insert)
            ((eq? method 'multiplicity?) multiplicity)
            ((eq? method 'delete) delete-one)
            ((eq? method 'delete-all) delete-all)
            ((eq? method 'giveset) myset)))))

(define tmultiset (multiset))
((tmultiset 'empty?))
((tmultiset 'insert) 2)
((tmultiset 'insert) 2)
((tmultiset 'insert) 2)
((tmultiset 'insert) 2)
((tmultiset 'insert) 5)
((tmultiset 'insert) 5)
((tmultiset 'insert) 1)
((tmultiset 'insert) 2)
(tmultiset 'giveset)
((tmultiset 'delete) 2)
(tmultiset 'giveset)
((tmultiset 'delete) 2)
(tmultiset 'giveset) 
((tmultiset 'delete-all) 2)
(tmultiset 'giveset)
((tmultiset 'multiplicity?)5)
((tmultiset 'multiplicity?)1)
((tmultiset 'multiplicity?)9)
((tmultiset 'empty?))
    
    
    

















          