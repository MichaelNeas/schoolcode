#lang racket
"1"
(define (sum f n)
  (if (= n 1)
      (f 1)
      (+ (f n) (sum f (- n 1)))
      )
  )
(define (zeno n)
  (/ 1 (expt 2 n)))
(sum zeno 1)
(sum zeno 9)

"2a"
(define (g-sum f i n) ;f= function, i=starting index of summation (generally 0 or 1), n=ending index
  (if (= i n)
      (f 1)
      (+ (f n) (g-sum f i (- n 1)))
      ))
"2b"
(define (geometric n)
  (/ 1 (expt 2 n)))
(g-sum zeno 1 9)
(g-sum geometric 0 9)

"3a"
(define (identity x) x)
(define  (is-even? n) (= (modulo n 2) 0))
  (define (is-odd? n) (not (= (modulo n 2) 0)))
  (define (prime n)
    (define (divides a b) (= (modulo b a) 0))
    (define (smooth k)
      (and (>= k 2)
           (or (divides k n)
               (smooth (- k 1)))))
    (if (= n 1) #f
     (not (smooth (floor (sqrt n))))))

  (define (find sequence test n)
  (define (find-h i found)
    (let* ((ith (sequence i))
           (passed-test? (test ith)))
    (if passed-test?
        (if (= n (+ found 1))
            ith
            (find-h (+ i 1) (+ found 1)))
        (find-h (+ i 1) found))))
  (find-h 1 0))

  "3b"
(find identity is-even? 5)  
(find identity is-odd? 5)  

  "3c"
(define (fib n)
  (cond ((= n 0) 0)
        ((= n 1) 1)
        ((> n 1) (+ (fib (- n 1))
                    (fib (- n 2))))
        )
  )
 (find fib prime 5)

  


                   