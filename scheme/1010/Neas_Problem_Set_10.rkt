#lang racket
"1"
;in the directions it says leaf nodes can have the form (s () ()) so my code is designed for that.
(define (create-heap h-min left right)
  (list h-min left right))
(define (h-min H) (car H))
(define (left H) (cadr H))
(define (right H) (caddr H))
    
(define (priority-queue f)
  (let ((heap '())
        (q-size 0))
    
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
    
    (define (insert-list L T)
      (if (null? L)
          T
          (insert-list (cdr L) (insert-this (car L)))))
       
    
    (lambda (method)
      (cond ((eq? method 'empty?) empty)
            ((eq? method 'insert) insert-this)
            ((eq? method 'extract-min) extract-min-this)
            ((eq? method 'size) current-size)
            ((eq? method 'insert-all) insert-list)
            ((eq? method 'give-heap) heap)))))
    
 (define (huff-tree<? ht1 ht2)
      (define (weight huff-tree)
        (if (null? huff-tree)
            0
            (cdar huff-tree)))
      (< (weight ht1) (weight ht2)))
 
 (define (leaf-assignments L)
   (define (leaf-assignments-h Lst newlist)
     (if (null? Lst)
         newlist
         (leaf-assignments-h (cdr Lst) (cons (create-heap (car Lst) '() '())
                                           newlist))))
   (leaf-assignments-h L '()))
   
 
 (define (make-internal-node 0-tree 1-tree)
   (create-heap (cons 'internal 
                      (+ (cdr (car 0-tree)) (cdr (car 1-tree)))) 
                1-tree 
                0-tree))
          
 
 (define (huffman L)
   (let ((mylist L)
         (tree '()))
     (define make-queue (priority-queue huff-tree<?))
     (define (loop)
           (if (eq? ((make-queue 'size)) 1)
               ((make-queue 'extract-min))
               (begin ((make-queue 'insert) (make-internal-node ((make-queue 'extract-min)) 
                                                                ((make-queue 'extract-min))))
                      (loop))))
                 (begin ((make-queue 'insert-all) mylist tree)
                        (loop))))
 
 (make-internal-node '((#\a . 2013) '() '()) '((#\b . 507) '() '()))
 
 (define test (leaf-assignments '((#\a . 2013) (#\b . 507) (#\c . 711))))
 test
 (huffman test)
 
 (define english-stats (leaf-assignments 
'((#\space . 40345)
(#\E . 21912)
(#\T . 16587)
(#\A . 14810)
(#\O . 14003)
(#\I . 13318)
(#\N . 12666)
(#\S . 11450)
(#\R . 10977)
(#\H . 10795)
(#\D . 7874)
(#\L . 7253)
(#\U . 5246)
(#\C . 4943)
(#\M . 4761)
(#\F . 4200)
(#\Y . 3853)
(#\W . 3819)
(#\G . 3693)
(#\P . 3316)
(#\B . 2715)
(#\V . 2019)
(#\K . 1257)
(#\X . 315)
(#\Q . 205)
(#\J . 188)
(#\Z . 128))))
 
(huffman english-stats)
 
"2"
(define (is-leaf? T)
  (if (and (null? (left T)) (null? (right T)))
      #t
      #f))
                   

(define (get-code T)
  (define (get-codes T code)
      (if (is-leaf? T)
          (list (cons (caar T) (list->string (reverse code))))
          (append (get-codes (left T) (cons #\0 code))
                  (get-codes (right T) (cons #\1 code)))))
                     
  (get-codes T '()))
      

(get-code (huffman test))
(get-code (huffman english-stats))

"3"
(define (word-freq-list mystring)
  (define (accumulator letter L)
    (if (null? L)
        0
        (if (eq? (car L) letter)
            (+ 1 (accumulator letter (cdr L)))
            (accumulator letter (cdr L)))))
  
  (define (combine letter L)
    (cons letter (accumulator letter L)))
  
  (define (put-together L)
    (if (null? L) 
        '()
        (cons (combine (car L) (string->list mystring))
              (put-together (cdr L)))))
  (define (remove-duplicates l)
    (cond ((null? l)'())
          ((member (car l) (cdr l))
           (remove-duplicates (cdr l)))
          (else
           (cons (car l) (remove-duplicates (cdr l))))))
  (remove-duplicates (put-together (string->list mystring))))

(word-freq-list "hello")



(define (encode astring frequency-list)
  (define (find letter T accum)
    (if (null? T) 
        '()
        (if (eq? letter (caar T))
             (reverse accum)
          (append (find letter (left T) (cons #\0 accum))
                  (find letter (right T) (cons #\1 accum))))))
  (define (encode-helper L)
    (if (null? L)
        '()
        (append (find (car L) (huffman (leaf-assignments frequency-list)) '())
                (encode-helper (cdr L)))))
  (list->string (encode-helper (string->list astring))))

(define hi (leaf-assignments '((#\h . 1) (#\e . 1) (#\l . 2) (#\o . 1))))
(huffman hi)
(encode "hello" (word-freq-list "hello"))

"4"
(define (decode astring codingtree)
    (define (letterfind mylist T accum)
          (if (is-leaf? T)
              (letterfind mylist codingtree (cons (caar T) accum))             
              (if (null? mylist)
                  (list->string (reverse accum))
                  (if (eq? (car mylist) #\0)
                      (letterfind (cdr mylist) (left T) accum)
                      (letterfind (cdr mylist) (right T) accum)))))
  (letterfind (string->list astring) codingtree '()))
(huffman hi)
(string->list "0110000011")
(decode "0110000011" (huffman hi))

"5"
(define (H-code-object listoffreqpairs)
  (let ((L listoffreqpairs)
        (huff (huffman (leaf-assignments listoffreqpairs))))
    (define (encoder mystring) (encode mystring L))
    (define (decoder L) (decode L huff))
    (define reminder (get-code huff))
    
    (lambda (method)
      (cond ((eq? method 'encode) encoder)
            ((eq? method 'decode) decoder)
            ((eq? method 'reminder) reminder)
            ((eq? method 'give-tree) huff)))))
(define huffobject (H-code-object (word-freq-list "hello")))
((huffobject 'encode) "hello")
((huffobject 'decode) "0110000011")
(huffobject 'reminder)
(huffobject 'give-tree)
      

               
             
           
                                   
 
 

  
 
