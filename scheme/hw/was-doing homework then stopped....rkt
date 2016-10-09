#lang racket
(define (inc x) (+ x 1))
(define (inc2 x) (inc (inc x)))
(inc2 3)
(define (fourth x) (* x x x x))
(define (sixteenth x) (fourth (fourth x)))
(sixteenth 4)
"2"
(define (number-sum n)
  (if (= n 0)
      0
      (+ n (number-sum (- n 1)))))
(define (pos-square n) 
  (if (= n 0)
      0
      (+ (* n n) (pos-square (- n 1)))))
(pos-square 4)

(define (pos-even n)
  (if (= n 0)
      0
      (+ (* 2 n) (pos-even (- n 1)))))
(pos-even 4)

(define (my-prod k)
  (if (= k 1)
      1
      (* (- 1 (/ 1 k)) (my-prod (- k 1)))))
(my-prod 3)
     
(define (divides a b) (= 0 (modulo b a)))

(define (divisors-upto n k)
  (cond ((= k 0) 0)
        ((= n 0) 0)
        ((= k 1) 1)
        ((divides k n) (+ 1 (divisors-upto n (- k 1))))
        (else (divisors-upto n (- k 1)))))
(define (divisors n) (divisors-upto n n))
(divisors 5)
(divisors 10)

(define (pos-int k)
  (if (= k 0)
      0.0
      (+ (* -1 (expt -1 k) (/ 4 (- (* 2 k) 1)))
         (pos-int (- k 1)))))
(pos-int 100000)

(define (new-sin x k)
  (define (factorial n) 
    (if (= n 0)
        1
        (* n (factorial (- n 1)))))
  (if (= k 0)  
      x 
  (let ((m (+ (* 2 k) 1)))
  (+ (* (expt -1 k) 
        (expt x m) 
        (/ 1 (factorial m)))
     (new-sin x (- k 1))))))
(new-sin 2 3)
  
(define (euler n)
  (if (= n 1)
      1.0
      (+ (euler (- n 1)) (/ 1 n))))
(abs (- (euler 1000) (log 1000)))
(euler 100)
  
(define (lucas n)
  (cond ((= n 0) 2)
        ((= n 1) 1)
        ((> n 1) (+ (lucas (- n 1)) (lucas (- n 2))))))
(lucas 5)

(define (sum f n)
  (if (= n 1)
      (f 1.0)
      (+ (f n) (sum f (- n 1)))))
(define (harmonic n)
  (/ 1 n)
)
(sum harmonic 100)

(define (der f h)
  (lambda (x) (/ (- (f (+ x h)) (f x))
                         h)))
(define (new-der f n h)
  (if (= n 1) (der f h)
      (new-der (der f h) (- n 1) h)))
  
  
  
  
  