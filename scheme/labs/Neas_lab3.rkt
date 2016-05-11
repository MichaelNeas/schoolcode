#lang racket
"1a"
(define (ancestor-gen n)
  (if (= n 0)
      1
      (* 2 (ancestor-gen (- n 1)))
      )
  )
(ancestor-gen 2)
(ancestor-gen 3)
(ancestor-gen 4)

"1b"
(define (num-ancestors n)
  (if (= n 0)
      0
      (+ (ancestor-gen n) (num-ancestors (- n 1)))
      )
  )
(num-ancestors 2)
(num-ancestors 3)
(num-ancestors 4)

"2a"
(define (pell-num n)
  (cond ((= n 0) 0)
        ((= n 1) 1)
        ((> n 1) (+ (* 2 (pell-num (- n 1)))
                    (pell-num (- n 2))))
        )
  )
(pell-num 2)
(pell-num 3)
(pell-num 4)

"2b"
(define (comp-pell-num n)
  (cond ((= n 0) 2)
        ((= n 1) 2)
        ((> n 1) (+ (* 2 (comp-pell-num (- n 1)))
                    (comp-pell-num (- n 2))))
        )
  )
(comp-pell-num 2)
(comp-pell-num 3)
(comp-pell-num 4)

"2c"
(define (combo-pell n)
  (/ (*(/ 1.0 2.0) (comp-pell-num n))
    (pell-num n))
  )
(combo-pell 6.0)
(combo-pell 20.0) ;we expect the number to be close to the sqrt of 2 which is in fact 1.414213562

"3 example"
(define (power base exp)
  (cond ((= exp 0) 1)
        (#t (* base (power base (- exp 1)))))
  )
(power 3 2)
(power 4 8)

"3a"
(define (even x) (= (modulo x 2) 0))
(define (odd x) (not (= (modulo x 2) 0)))
(define (square x) (* x x))
(define (fastexp b e)
  (cond ((= e 0) 1)
        ((even e)(square (fastexp b (/ e 2))))
        ((odd e)(* (fastexp b (- e 1)) b))
        )
  )
(fastexp 3 2)
(fastexp 4 8)

"3b"
"fastexp is faster than the power function because it starts off by cutting the possibilities in half either odd or even.  In the power function the base was multiplied by the power base until the exp reached 0."

"4"
(define (new-sqrt x n) ;x = number of sqrt we wanto find, n = # of continued fractions
  (define (cont-frac-help n);x is already in the environment so we dont need to use it again
  (if (= n 0)
      0
      (/ (- x 1)
         (+ 2 (cont-frac-help (- n 1))))))
  (+ 1 (cont-frac-help n)))    
(new-sqrt 2.0 20.0)
(sqrt 2)
(new-sqrt 25.0 90.0) ;the sqrt of 25 is 5 
(sqrt 25)

"5"        
(define (random-roll)
      (+ (+ (random 6) 1) (+ (random 6) 1)))
(define (num-random-rolls n)
  (if (= n 0)
      0
      (let ((this-roll (random-roll)))
        (cond
          ((or (= this-roll 7)
               (= this-roll 11))
               (+ 1 (num-random-rolls (- n 1))))
            (else (num-random-rolls (- n 1))))))
      )
(define (percent n)
  (/ (num-random-rolls n) n))
(num-random-rolls 100.0)
(percent 1000)
         
         

    
  
  
  
  
           


  