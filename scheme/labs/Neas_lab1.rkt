#lang racket
"1a"
(define (dollars-to-euros $)
(* $ 0.75))
(dollars-to-euros 175)

"1b"
(define (euros-to-yen €)
(* € 132.04))
(euros-to-yen 240)

"1c"
(define (dollars-to-yen $)
(euros-to-yen(dollars-to-euros $)))
(dollars-to-yen 175)

"2a"
(define (N a b c d)
  (- (* a d) (* b c)))
(N -3 1 2 7)
(define (M a b c d)
   (- (* a d) (* b c)))
(M 2 -4 -6 12)

"2b"
(define (invertible? a b c d)
  (not (= 0(N a b c d))))
(invertible? -3 1 2 7)
(invertible? 2 -4 -6 12)

"2c"
(define (prod-inv a1 b1 c1 d1 a2 b2 c2 d2)
  (define a (+ (* a1 a2) (* b1 c2)))
  (define b (+ (* a1 b2) (* b1 d2)))
  (define c (+ (* c1 a2) (* d1 c2)))
  (define d (+ (* c1 b2) (* d1 d2)))
  (invertible? a b c d))
(prod-inv 2 -4 -6 12 -3 1 2 7)

"2cii"
(define (prod-inv? a1 b1 c1 d1 a2 b2 c2 d2)
  (not (= 0(*(N a1 b1 c1 d1)
             (N a2 b2 c2 d2)))))
(prod-inv? 2 -4 -6 12 -3 1 2 7)

"2d"
(define (det3x3 a b c d e f g h i)
  (+ (- (* a (N e f h i)) 
        (* b (N d f g i)))
     (* c (N d e g h))))
(det3x3 0 5 -6 8 -11 4 5 1 1)
     
