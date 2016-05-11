#lang racket
"1"
(define (sum f n)
  (if (= n 1)
      (f 1)
      (+ (f n) (sum f (- n 1)))))
(define (harmonic n)
  (/ 1 n))
(sum harmonic 3.0) ;1+.5+.3333333333 repeated is 1.83333
(sum harmonic 14.0) ;checks out with what is needed

"2a"
;lambda defines a function where a variable is needed not a word
(define (der f h) 
  (lambda (x) (/ (- (f (+ x h)) (f x)) 
                               h)
    )
  )
(define (square x) (* x x))
(define der-of-sqr (der square .0000001)) ;smaller the h the more accurate of a reading
(der-of-sqr 10) ;10 squared = 100... but the derivative of x^2 is 2x....when x =10 then we should get 20 
(define (cube x) (* x x x))
(define der-of-cube (der cube .000000000001))
(der-of-cube 3) ; works perfectly! 2x^3 is the derivative of x^3 which in this case checks out when x=3
(define (quant x) (* x x x x))
(define der-of-quant (der quant .000000000001))
(der-of-quant 2)

"2b"
(define (many-der f h n)
  (if (= n 0)
      f
      (der (many-der f h (- n 1)) h)
      )
  )
(define der-of-cube-n (many-der cube .00001 2)) ;important to ensure that h is not too small
(der-of-cube-n 5)

"2c"
(define sine-curve-der (der sin .5)) ;the use of parenthesis throws everything into procedure... important to remember after using lambda and define that when defining the function to use that you do not use parenthesis
(sine-curve-der 1) ;the .5 for h allows us to see the difference in the lines, if the number was very close to 0 the lines would over lap and we would never see it

(require plot) ;package
(plot (mix (line sine-curve-der #:color 'blue)
            (line cos #:color 'red))
             #:x-min -15 #:x-max 15 #:y-min -15 #:y-max 15)

"2d"
(define sine-curve-four (many-der sin .1 4))
(plot (mix (line sine-curve-four #:color 'brown) ;as you can see the two lines are identical and that is why i graphed both of them, to show my reasoning described below
           (line sin #:color 'yellow)) #:x-min -15 #:x-max 15 #:y-min -15 #:y-max 15)
"The fourth derivative of sin is itself, and because of this the graphical representation of sin^4(x) is the same as sinx.  To prove this I graphed sin normally and the fourth derivative of sine, they would directly overlap if h was the smallest it could be without causing error."

"3a"
(define (newt f IG n) ;3 parameters, the function, initial geuss, and n iterations
(define (newtshelper IG i) ;helper function for newt using the same initial geuss but i to count up 
   (if (= n i) ;when recursion = desire then show the geuss
       IG
     (let ((pass (- IG (/ (f IG) ((der f .01) IG))))) ;newtons method is correlated to 'pass' and the der function from before takes place in order to allow for the derivative of a function to take place
       (newtshelper pass (+ i 1))))) ;recursion, adds to i until n starting from 0
    (newtshelper IG 0)
  ) 

(define (first y) (newt (lambda (x) (+ (* 2 x) 1)) 10 30))  ;x =6 , 30 iterations
(first 6) ;-.5 for all values checks out because the derivative is 2 in the denominator 


"3b"
(define (poly z)
(newt (lambda (x) (- (* x x) x 1)) 2 30)) 
(poly 90) ;GOLDEN RATIO

"4"
(define (newtonsqrt n)
 (newt (lambda (x) (- (* x x) n)) 1 40)) ;40 iterations, 1 initial geuss
(newtonsqrt 25) ;square root of 25 is 5
(newtonsqrt 2) ;same calculation checks out here

"5" ;most difficult code yet for me..
(define (simp f a b n) ;simpsons rule allows us to approximate the integral of a function between a and b, n iterations for specificity
   (define (sum-help term a b dx) ;takes term and evaluates between a and b over var slices
     (cond ((= 0 dx) (+ (term a) (sum-help term (+ a 1) b (+ dx 1)))) ;when dx = 0 then we add term a with the helper function where we incriment a and dx by 1
           ((= a b) (term b)) ;when a = b it produces the function of b
           ((= 0 (modulo a 2)) (+ (* 2 (term a)) ;when a is even then the term of a is multiplied by 2 and added to sum-help term
             (sum-help term (+ a 1) b dx)))
                 (else (+ (* 4 (term a)) ;otherwise add 4 times term a to help function
                    (sum-help term (+ a 1) b dx))))) ;recursive set
  (let ((h (/ (- b a) ;h=(b−a)/n is given in the sicp text
               n)))
   (* h 1/3 (sum-help 
       (lambda (k) (f (+ a (* h k)))) 0 n 0))));multiply h for 1/3 of approximation to the help function where k becomes

(define (f1 x) x)

(simp f1 0 1 12) ;checks out
(simp square 0 1 12) ;checks out f2
(simp quant 0 1 100) ;just about as close to 1/5 as possible

