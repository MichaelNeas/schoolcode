#lang racket
"Example"
(define (pressure h)
  (let ((p0 101325)
        (L .0065)
        (T0 288.15)
        (g 9.80665)
        (M .0289644)
        (R 8.31447))
    (let ((exponent (/ (* g M) (* R L)))
          (base (- 1 (/ (* L h) T0))))
      (* p0 (expt base exponent)))))

"1a"
(define (surface-area-of-sphere r)
  (let ((pi 3.14159))
     (* 4 pi (expt r 2))))
(surface-area-of-sphere 2)

"1b"
(define (volume-of-ball r)
  (* (/ (surface-area-of-sphere r) 3) r))
(volume-of-ball 2)

"1c"
(define (energy-from-mass m)
  (let ((c 299792458))
        (* m (expt c 2))))
(energy-from-mass 1) 

"2"
(define (quad-poly a b c)
  (let ((sqrt (- (expt b 2) (* 4 a c))))
  (cond 
    ((< sqrt 0) 'no-reals)
    ((= sqrt 0) 1)
    ((> sqrt 0) 2))))
 
(quad-poly 1 2 1)
(quad-poly 3 0 1)
(quad-poly 2 3 1)



"3a"
(define (dot2 a1 a2 b1 b2)
  (+ (* a1 b1) (* a2 b2)))
(define (dot3 a1 a2 a3 b1 b2 b3)
  (+ (* a1 b1) (* a2 b2) (* a3 b3)))
(dot2 1 2 3 4)
(dot3 1 2 3 4 5 6)
"3b"
(define (N a b c d)
  (- (* a d) (* b c)))
(define (M a b c d)
   (- (* a d) (* b c)))

(define (invertible? a b c d)
  (not (= 0(N a b c d))))

(define (prod-inv a1 b1 c1 d1 a2 b2 c2 d2)
  (define a (dot2 a1 b1 a2 c2))
  (define b (dot2 a1 b1 b2 d2))
  (define c (dot2 c1 d1 a2 c2))
  (define d (dot2 c1 d1 b2 d2))
  (invertible? a b c d))

(prod-inv 3 5 4 -7 -2 1 -6 3)

"3c" ;if p*q=0, then the vectors are perpendicular
(define (dot x1 y1 z1 x2 y2 z2)
  (cond 
  ((= (dot3 x1 y1 z1 x2 y2 z2) 0) 'perpendicular)
  ((> (dot3 x1 y1 z1 x2 y2 z2) 0) 'not-perpendicular)
  ((< (dot3 x1 y1 z1 x2 y2 z2) 0) 'not-perpendicular-either)))


(dot 9 8 3 4 5 6)
(dot 1 -2 1 3 5 7)
(dot 1 2 3 -4 -5 -6)

"4"
(define (sequence n)
  (define (recur x y z n numb)
    (if (= n numb)
        (+ x y z)
        (recur y z (+ x y z) n (+ numb 1))))
  (if (< n 4)
      'DNE
      (recur 1 2 3 (- n 4) 0)))
                 
(sequence 4)
(sequence 5)
(sequence 6)
(sequence 10);this one question took me 3 hours 

"5"
(define (zeno n)
  (if (= n 1)
      (/ 1 2)
      (+ (/ 1 (expt 2 n))
         (zeno (- n 1)))))
(zeno 3)
(zeno 9)


