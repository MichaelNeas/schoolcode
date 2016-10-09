#lang racket
 ;compound data types
"hi"
(string #\m #\i #\k #\e) ;string combines things
(define hi "whats up")
hi
(string-ref hi 1) ;pulls out a certain letter in the condition
(string-append "I " "am " "king") ;req spacing after words
;vectors are like strings, but elements can be anything
(vector 1 2 4)
;car & cdr for combined values cons for the value input
(define p (cons 1 2))
(car p)
(cdr p)
p