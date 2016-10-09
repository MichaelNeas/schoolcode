#lang racket
(require racket/draw)
(define (head s) (car s))
(define (rest s) ((cdr s)))
(define (delay something) (lambda () something))
"a"
(define (make-complex a b) (cons a b))
(define (c-add c1 c2)
  (make-complex (+ (car c1) (car c2)) (+ (cdr c1) (cdr c2))))
(define (c-multiply c1 c2)
  (make-complex (- (* (car c1) (car c2))
                   (* (cdr c1) (cdr c2)))
                (+ (* (car c1) (cdr c2))
                   (* (cdr c1) (car c2)))))
(define (length-c c)
  (sqrt (+ (* (car c) (car c))
           (* (cdr c) (cdr c)))))
"b"
(define (mandelbrot z a)
  (cons a (lambda () (mandelbrot z (c-add (c-multiply a a) z)))))
(define mtest (mandelbrot (cons (/ 1 2) (/ 1 2)) '(0 . 0)))
mtest
(rest mtest)
(rest (rest mtest))
(rest (rest (rest mtest)))
(rest (rest (rest (rest mtest))))
(rest (rest (rest (rest (rest mtest)))))

(define (mandelbrotz z)
  (mandelbrot z (cons 0 0)))

"c"                                         
(define (s-map f s)
  (if (null? s)
      '()
      (cons (f (car s))
            (lambda () (s-map f (rest s))))))

(define (mandelbrot-l z)
  (s-map length-c (mandelbrotz z)))
(define mandtest (mandelbrot-l '(1/2 . 1/2)))
mandtest
(rest mandtest)
(rest (rest mandtest))
(rest (rest (rest mandtest)))
(rest (rest (rest (rest mandtest))))

"d"
(define (depth z max)
  (define (depth-help ml index)
    (cond ((< 2 (head ml)) index)
          ((= max index) max)
          (else (depth-help (rest ml) (+ 1 index)))))
  (depth-help (mandelbrot-l z) 0))
        
(depth '(1/2 . 1/2) 10)


(define (iter->color i) 
  (if (= i 255)
      (make-object color% "black") 
      (make-object color% (* 5 (modulo i 15))
        (* 32 (modulo i 7))
        (* 8 (modulo i 31)))))

(define (mandelbroti width height)
  (define target (make-bitmap width height)) 
  (define dc (new bitmap-dc% [bitmap target])) 
  (for* ([x width] [y height])
    (define real-x (- (* 3.0 (/ x width)) 2.25))
    (define real-y (- (* 2.5 (/ y height)) 1.25))
    (send dc set-pen (iter->color (depth (cons real-x real-y)
                                         255))
          1 'solid)
    (send dc draw-point x y))
  (send target save-file "mandelbrot.png" 'png))
(mandelbroti 900 600)
  