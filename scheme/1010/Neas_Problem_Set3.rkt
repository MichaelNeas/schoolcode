#lang racket
"Opening Remark"
(+ 1 1.0) ;type-casting is when you put a decimal so the interpreter can return a decimal number rather than a fraction
"1"
(define (harmonic n) ;as n increases they turn to be close to ln n |Hn-ln|= Euler's constant.
  (if (= n 0)
      0
      (+ (/ 1 n) (harmonic (- n 1)))
      )
  )
(define (euler n) ;eulers constant is .5772156649, when i did (euler 10000) the number was pretty close...but I don't have it in the results to spare my own and anyone who looks at my code's processors 
  (if (= n 0)
      0
      (- (harmonic n) (log n))
      )
  )
         
(harmonic 3.0)        
(euler 100)
(euler 400)
(euler 1000)
"2a"
(define (lucas n)
  (cond ((= n 0) 2.0)
        ((= n 1.0) 1.0)
        ((> n 1.0) (+ (lucas (- n 1.0)) 
                    (lucas (- n 2.0))))
        )
  )
(lucas 9) ;expected
"2b"
(define (fib n)
           (cond ((= n 0.0) 0.0)
                 ((= n 1.0) 1.0)
                 ((> n 1.0) (+ (fib (- n 1.0))
                             (fib (- n 2.0))))
                 )
  )
(lucas 5)
(fib 5)
(lucas 6) ;lucas numbers are always bigger than fibonacci numbers due to the base case difference
(fib 6)
(lucas 7)
(fib 7)
"------------------------------------------------------------------------------"
(define (adjacent-l n)
  (if (= n 0.0)
      0.0
      (/ (lucas n) (lucas (- n 1.0)))
      )
  )
(adjacent-l 4)
(adjacent-l 20.0)
(adjacent-l 21.0)
(adjacent-l 22.0)
(define (fib-r n)
  (if (= n 0.0)
      0.0
      (/ (fib n) (fib (- n 1)))
      )
  )
(fib-r 4)
(fib-r 20.0)
(fib-r 21.0)
(fib-r 22.0) ;heyy the results of the fib ratio are very similar to that of the lucas ratio, conversion? golden ratio?
"2c"
;continually going up 30, 35, 40, 50 takes a long time for the interpreter to compute
(define (fast-Lucas-help n k luc-a luc-b)
  (if (= n k)
      luc-a
      (fast-Lucas-help n (+ k 1) (+ luc-a luc-b) luc-a)))
(define (fast-Lucas n) (fast-Lucas-help n 1 1 2))
"lucas recursive calls" ;im not sure if its possible to make a table in drracket
(lucas 1) "0 recursive calls for n=1"
(lucas 2) "2 recursive calls for n=2"
(lucas 3) "4 recursive calls for n=3";like a tree diagram
(lucas 4) "8 recursive calls for n=4"
(lucas 5) "14 recursive calls for n=5"
(lucas 6) "24 recursive calls for n=6"
"fast-Lucas recursive calls"
(fast-Lucas 1) "0 recursive calls for n=1"
(fast-Lucas 2) "1 recursive call for n=2";checks out with previous definition of lucas
(fast-Lucas 3) "2 recursive calls for n=3"
(fast-Lucas 4) "3 recursive calls for n=4"
(fast-Lucas 5) "4 recursive calls for n=5"
(fast-Lucas 6) "5 recursive calls for n=6";50000 just made me laugh at the amount of numbers produced so quickly
"3a"
(define (golden-ratio n)
  (if (= n 1)
      2.0
      (+ 1 (/ 1 (golden-ratio (- n 1))))
      )
  )
(golden-ratio 10) 
(golden-ratio 15) ;the higher the value of n the closer and closer it gets to that 1.618 number
"3b"
(define (golden-ratio-sqrt n)
  (if (= n 0)
      1
      (sqrt (+ 1 (golden-ratio-sqrt (- n 1))))
      )
  )
(golden-ratio-sqrt 10) ;the golden-ratio-sqrt function is able to register the ratio much quicker than the previous definition, (get to the end result)
(golden-ratio-sqrt 15)
"4"
(define (radius x y)
  (sqrt(+ (* x x) (* y y))))
(define (random-throw)
  (radius (-(* 2(random)) 1) (-(* 2(random)) 1)))
(define (num-hits n)
  (if (= n 0)
      0
      (let ((this-hit (random-throw)))
        (cond
          ((<= this-hit 1) (+ 1 (num-hits (- n 1))))
          (else (num-hits (- n 1)))))))
(define (hit n) (* (/ (num-hits n) n) 4.0))
(hit 100000) ;near the value of pi as it should be =) FINALLY program took me a lot longer than I anticipated!