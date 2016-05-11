#lang racket
"1"
(define (sum-square li)
  (if (null? li)
      0
      (+ (* (car li) (car li))
         (sum-square (cdr li)))))
(sum-square (list 1 2 3))
(sum-square (list 4 5 6))

"2"
(define (num-zeros lst)
  (define (num-zeros-help my-lst count)
  (if (null? my-lst)
      count
      (if (= (car my-lst) 0)
          (num-zeros-help (cdr my-lst) (+ 1 count))
          (num-zeros-help (cdr my-lst) count))))
  (num-zeros-help lst 0))
(num-zeros (list 1 0 2 3 0 45 0 5 8 1 5416 2 0 98411 0 981))

"3"
(define (seesaw-balance? li)
  (define (is-balanced-help objects rule)
  (if (null? objects)
           rule
       (is-balanced-help (cdr objects)(+ rule (* (caar objects) (cdar objects))))))
  (is-balanced-help li 0))
(= (seesaw-balance? '((-4 . 5) (4 . 5) (4 . 2) (-2 . 3))) 0)
(= (seesaw-balance? '((-2 . 4) (2 . 4) (-9 . 5) (9 . 5))) 0)

"4a"
;cadr-car of the cdr
;cdar-cdr of the car
;caar-car of the car
;cddr-cdr of the cdr
(define (swap-first-two li)
  (if (null? li)
      (list)
      (cons (cadr li) 
            (cons (car li)
                  (cddr li)))))

(swap-first-two (list 3 2 1 4 5 8))

"4b"
(define (mod-swap-first-two li)
  (if (null? (cdr li))
      (cons '() 
            (car li))
      (list '( () (car li)))))

(mod-swap-first-two (list 2))

"4c"
(define (bubble-up li n)
  (if (= n 0)
      li
      (if (> (car li)
             (cadr li))
          (cons (cadr li) (bubble-up (cdr (swap-first-two li)) (- n 1)))
      (cons (car li) (bubble-up (cdr li) (- n 1))))))
                                       
     
(bubble-up (list 5 4 3 2 1 0) 5)

"4d"
(define (bubble-sort-aux li n)
  (if (= n 0)
      li
      (bubble-sort-aux (bubble-up li n) (- n 1))
     ))
(bubble-sort-aux (list 5 4 3 2 1 0) 5)

"4e"
(define (bubble-sort li)
  (if (null? li)
      '()
      (bubble-sort-aux li (- (length li) 1)))) ;length tells how many parts of a list
(bubble-sort (list 5 4 3 2 1))
                   
      

