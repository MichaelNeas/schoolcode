#lang racket
"1"
(define (num-occurs c L)
  (define (num-occurs-help c L count)
  (cond ((null? L) count)
        ((char=? c (car L)) (num-occurs-help c (cdr L) (+ 1 count))) ;remember char=?
        (else (num-occurs-help c (cdr L) count)))) ;else makes it easier rather than defining a not statement
    (num-occurs-help c L 0))
(num-occurs #\e '(#\S #\c #\h #\e #\m #\e))

"2"
(define (value T) (car T))
(define (right T) (caddr T))
(define (left T) (cadr T))

(define (make-tree value left right)
  (list value left right))

(define (insert x T)
  (cond ((null? T) (make-tree x '() '()))
        ((equal? x (value T)) T)
        ((char<? x (value T)) (make-tree (value T)
                                          (insert x (left T))
                                          (right T)))
        ((char>? x (value T)) (make-tree (value T)
                                          (left T)
                                          (insert x (right T))))))
(define (insert-all lst T)
  (if (null? lst)
      T
      (insert-all (cdr lst) (insert (car lst) T))))

(define (extract-all T)
  (define (extract-to T lst)
    (if (null? T)
        lst
        (cons (value T)
              (extract-to (left T)
                          (extract-to (right T)
                                      lst)))))
  (extract-to T (list)))

(define (extract-alphabet lst)
  (extract-all (insert-all lst '())))
(extract-alphabet '(#\S #\c #\h #\e #\m #\e));test

(define (freq-list LoC)
  (define (freq-aux lst unique-elements)
    (if (null? unique-elements)
        '()
        (cons (cons (car unique-elements) 
                    (num-occurs (car unique-elements) lst))
              (freq-aux lst (cdr unique-elements))))) 
  (freq-aux LoC (extract-alphabet LoC)))
(freq-list '(#\S #\c #\h #\e #\m #\e))

"3"
(define (create-heap max-v lefty righty)
  (list max-v lefty righty))

(define (h-max h) (car h))
(define (lefty h) (cadr h))
(define (righty h) (caddr h))
(define (max-pair p1 p2) 
  (if (> (cdr p1) (cdr p2))
      p1
      p2))
(define (min-pair p1 p2) 
  (if (> (cdr p1) (cdr p2))
      p2
      p1))

(define (insert-pair cfP H)
  (if (null? H)
      (create-heap cfP '() '())
      (let ((root-value (max-pair cfP (h-max H)))
            (child-value (min-pair cfP (h-max H))))
        (create-heap root-value
                     (righty H)
                     (insert-pair child-value 
                                  (lefty H))))))
(insert-pair '(#\c . 1) '()) ;test

(define (insert-list LocfP H)
  (if (null? LocfP)
      H
      (insert-list (cdr LocfP) (insert-pair (car LocfP) H))))

(insert-list '((#\S . 1) (#\c . 1) (#\h . 1) (#\e . 2) (#\m . 1)) '())

(define (combine-heaps h1 h2)
  (cond ((null? h1) h2)
        ((null? h2) h1)
        ((> (cdr (h-max h1)) (cdr (h-max h2)))
        (create-heap (h-max h1)
                     h2
                     (combine-heaps (lefty h1) (righty h2))))
        (else (create-heap (h-max h2)
                           h1
                           (combine-heaps (lefty h2) (righty h2))))))
(define (remove-max h)
  (combine-heaps (lefty h) (righty h)))
          
(define (extract-most-frequent H)
  (cons (h-max H)(remove-max H)))
(extract-most-frequent (insert-list '((#\S . 1) (#\c . 1) (#\h . 1) (#\e . 2) (#\m . 1)) '()))
(define a (extract-most-frequent (insert-list '((#\S . 1) (#\c . 1) (#\h . 1) (#\e . 2) (#\m . 1)) '())))
(car a)
(cdr a);scheme doesn't know when you cons a pair to a list

"4"
(freq-list '(#\I #\space #\a #\m #\space #\i #\n #\space #\s #\c #\h #\e #\m #\e #\space 
                 #\l #\a #\b #\space #\w #\i #\t #\h #\space #\G #\r #\e #\g #\space 
                 #\r #\i #\g #\h #\t #\space #\n #\o #\w #\.)) 

(extract-most-frequent (insert-list (freq-list '(#\I #\space #\a #\m #\space #\i #\n #\space 
                                          #\s #\c #\h #\e #\m #\e #\space 
                 #\l #\a #\b #\space #\w #\i #\t #\h #\space #\G #\r #\e #\g #\space 
                 #\r #\i #\g #\h #\t #\space #\n #\o #\w #\.)) '()))

(define (top-3 H)
  (let* ((a (extract-most-frequent H))
         (b (extract-most-frequent (cdr a)))
         (c (extract-most-frequent (cdr b))))
    (list (car a) (car b) (car c))))
(top-3 (insert-list (freq-list '(#\I #\space #\a #\m #\space #\i #\n #\space 
                                          #\s #\c #\h #\e #\m #\e #\space 
                 #\l #\a #\b #\space #\w #\i #\t #\h #\space #\G #\r #\e #\g #\space 
                 #\r #\i #\g #\h #\t #\space #\n #\o #\w #\.)) '()))

