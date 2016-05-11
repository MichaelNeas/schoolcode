#lang racket
;The first program "semi:colin for notes too"
(display "My name is Mike Neas")
 (newline)
;scheme has a bunch of data types like boolean (either true or false)...need to learn them
 (boolean? #t)
 (boolean? "hi")
 
 (not #f)
 
;numbers
 (number? 29993)
 (complex? 2+3i)
 (real? 42)
 (rational? 2+3i)
 (integer? 1/3)
 ;can use binary (eqv = =)
 (eqv? 31 34)
 (= 31 31)
 ;<,<=,>,>=
 (>= 3.1 3)
 ;prefix +,-,*,/,expt, max, min, abs
 (+ 2 2)
 (/ 4)
 (max 1 90 38 47)
 (min 3 100 92 38 10291)
 (abs -4)
 
 #t ;will repeat simple data types
 ;to specify a symbol without scheme thinking its a variable quote the symbol
 (quote mike) ;quote = '
 (symbol? 'mine)
 (symbol? 4)
 (eqv? 'mike 'mine)
 (define x 9)
 x
 (set! x 8) ;set or another define function to change variable
 x