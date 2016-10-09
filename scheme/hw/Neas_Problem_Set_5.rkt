#lang racket
"1"
(define (max x)
  (lambda (m)
     (if (> ((car x) m) ((cdr x) m))
            ((car x) m)
            ((cdr x) m))))

  (define one-function (lambda (x) (+ x 1)))
  (define second-function (lambda (x) (+ (* 2 x) 1)))
  
((max (cons one-function second-function)) 4)   
  


"2"
(define (zip a b)
  (if (null? (and a b)) ;if both list a and b are empty then produce the list of pairs
      (list)
      (cons (cons (car a) (car b)) ;take the car of and and b and make a pair
            (zip (cdr a) (cdr b))))) ;recursion to take the cdr and allow for the rest of the list to be evaluated till null
(zip (list 1 2 3) (list 1 2 3))

"3"
(define (unzip lst)
  (if (null? lst) 
      (cons (list) (list)) ;construct a list of a list
      (cons (cons (car (car lst)) 
                  (car (unzip (cdr lst)))) ;list of the car of the pairs
            (cons (cdr (car lst))  ;list of the cdr of the pairs
                  (cdr (unzip (cdr lst)))))))
(unzip '((1 . 1) (2 . 2) (3 . 3)))
(unzip (list (cons 4 3) ;there are two different ways to provide an input in this situation
             (cons 5 3)
             (cons 6 3)))

"4"
(define (change k l)
  (cond ((= k 0) 1) 
        ((or (< k 0) (null? l)) 0) ;if k becomes less than zero or an empty list then 0 is added
        ((+ (change k (cdr l)) ;otherwise we recursivly call the cdr of change (1 5 10 25)
            (change (- k (car l)) l))))) ;to allow for all possibly outcomes to be expressed we must subtract from k till satisfies a base case
(change 15 (list 1 5 10 25))
(change 12 (list 1 5 10 25))
(change 5 (list 1 5 10 25))


"5a"
(define (encode p)
  (+ (* .5 (+ (car p) (cdr p)) (+ (car p) (cdr p) 1)) (cdr p))) ;remember parenthases
(encode (cons 5 3))
(encode (cons 9 0))

"5b"
(define (decode p)
  (let* ((w (floor (/ (- (sqrt(+ (* 8 p) 1)) 1) 2))) ;let* allows for things to get rendered in a specific order flowing down the list as needed
         (t (/ (+ (* w w) w) 2))
         (y (- p t))
         (x (- w y)))
    (cons x y)))
(decode 39)
(decode 45)
    
