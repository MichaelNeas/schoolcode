#lang racket
"1a"
(define (sum-of-list lst)
  (if (null? lst) ;remember no = sign
      0
      (+ (car lst) (sum-of-list (cdr lst)))))
(sum-of-list (list 1 2 3 4 5 6))
(sum-of-list (list 4 8 23 23 4 10))
"1b"
(define (avg-X lst)  ;length finds how many #'s in list
  (/ (sum-of-list lst) (length lst)))
(avg-X (list 1 2 3 4 5 6))
(avg-X (list 4 8 23 23 4 10))   

"1c"
(define (square-of-deviation li)
      (map (lambda (x) (expt (- x (avg-X li)) 2)) li)) ;map applies a function to each element of a list
(square-of-deviation (list 1 2 3 4 5))
(square-of-deviation (list 23 8 9 1 4))
(square-of-deviation (list 1 3 3 5))

"1d"
(define (standard-deviation li)
  (sqrt(* (/ 1 (length li)) (sum-of-list (square-of-deviation li)))))
(standard-deviation (list 1 3 3 5)) ;prof said in forum should be equal to sqrt(2)
(sqrt 2) ;testing to make sure it was the same as above
(standard-deviation (list 42 5 123 9))
(standard-deviation '(1 2 3))
"1e"
(define (map2 f xli yli)
 (map f xli yli)) ;so much simpler than what I was trying to do before
(define circle (lambda (x y) (+ (* x x) (* y y))))  
(map2 circle (list 1 2 3) (list 1 2 3)) 
(map2 circle (list 2 6 6) (list 1 2 5)) ;test with moodle

"1f"
(define (covariance X Y)
  (define (avg-Y lst) (/ (sum-of-list lst) (length lst)))
  (if (null? X)
      '()
      (map2 (lambda (x y) (* (- x (avg-X X)) (- y (avg-Y Y)))) X Y))) 
(covariance (list 1 3 3 5) (list 12 12 11 7)) ;checks out with moodle
(covariance (list 1 2 3 4) (list 1 2 3 4)) 

"1g"
(define (Pearson X Y)
    (/ (/ (sum-of-list (covariance X Y))
       (* (standard-deviation X) (standard-deviation Y)))
       (length X)))
(Pearson (list 1 3 3 5) (list 12 12 11 7)) ;CHECKS OUT WITH MOODLE!!!
(Pearson (list 1 2 3) (list 1 2 3))


"2a"
(require plot) ;dont need till final part of 2 but called for it anyway

(define (best-fit-line X Y)
  (define (avg-Y lst) (/ (sum-of-list lst) (length lst)))
      (let* ((b (* (Pearson X Y) (/ (standard-deviation Y) 
                                (standard-deviation X))))
             (a (- (avg-Y Y) (* b (avg-X X)))))
                 (cons b a))) ;in the pair given from this we get (a . b) which is the (slope . intercept)
(best-fit-line (list 1 2 3) (list 1 2 3))
(best-fit-line (list 160 180 200 220 240 260 280) 
                               (list 126 103 82 75 78 40 20))


"2b"
(define (fitline x)
  (define desired (best-fit-line (list 160 180 200 220 240 260 280) 
                                 (list 126 103 82 75 78 40 20)))
  (+ (* (car desired) x) (cdr desired))) ;y=mx+b formula for the points where u plug in x and get out y 
(fitline 12)
(fitline 153)

"2c"
(define (zip2 X Y)
  (map2 vector X Y)) ;use the list X and Y in the vector formula to zip into coordinates for the points along the graph

(zip2 (list 160 180 200 220 240 260 280) ;to test and see vectors
      (list 126 103 82 75 78 40 20))

(plot (list (axes)
            (function fitline 140 300) ;bounds to see fitline
            (points (zip2 (list 160 180 200 220 240 260 280) 
                          (list 126 103 82 75 78 40 20)))))

"3"
(define (merge l1 l2)
  (cond ((null? l1) l2) ;if list 1 is empty then go to list 2
        ((null? l2) l1) ;if list 2 is empty then go to list 1
        ((<= (car l1) (car l2)) ;when car of l1 is less than or equal to the car of l2 construct car of l1 with the recursion to allow for all the numbers in the list to be targeted
         (cons (car l1)
               (merge (cdr l1) l2)))
        ((cons (car l2) 
               (merge l1 (cdr l2)))))) ;or else make the list with l2 and recurr merge of l1 with the cdr of l2
               
             
(merge (list 1 2 4 5) (list 1 3 6 9))


  
  
  
            