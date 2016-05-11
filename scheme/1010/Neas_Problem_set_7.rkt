#lang racket
(define (make-tree value left right) (list value left right))
(define (value tree) (car tree))
(define (left tree) (cadr tree))
(define (right tree) (caddr tree))

"1" 
(define (tree-equal? tree1 tree2)
  (or (eq? tree1 tree2) ;if tree1 = tree2 then #t
      (and (eq? (value tree1) (value tree2)) ;or if all the parts of the tree1 are equal to tree2 then #t
           (eq? (left tree1) (left tree2))
           (eq? (right tree1) (right tree2)))))

(define one-tree (make-tree 87 '() '()))
(define two-tree (make-tree 3 '() '()))
(define three-tree (make-tree 3 '() '()))
(tree-equal? one-tree two-tree)
(tree-equal? two-tree three-tree)
(tree-equal? '(4 53 '()) '(6 3 3))

"2"
(define (insert n T);number and a tree HELPER FUNCTION
  (if (null? n) ;if there is no number then its just the tree
      T
      (cond ((null? T) (make-tree n '() '())) ;if the tree is empty then put n where value(T) is
            ((eq? n (value T)) T) ;if the number is the value of the tree then just give back the tree
            ((< n (value T)) (make-tree (value T) ;if the number is less than the car of T then make a new tree where you put n in the left side of the tree
                                        (insert n (left T))
                                        (right T)))
            ((> n (value T)) (make-tree (value T) ;if the number is greater than the car T then make a new tree where you put the n in the right side of the tree
                                        (left T)
                                        (insert n (right T)))))))
(define (insert-list L T) ;put a list in a tree
  (if (null? L) ;if theres nothing in the list then everythings easy
      T
      (insert-list (cdr L) (insert (value L) T)))) ;otherwise you have to call insert-list for the rest of the list as you insert the car of L into the tree
(define (sort-extract T) ;sorting the tree 
  (if (null? T) ;if the tree is empty give back the list
      '()
      (append (sort-extract (left T)) ;otherwise append the reccuring left side of the tree since all the values on the left are smaller than the value of the tree
              (list (value T)) ;then put the value in the middle cause thats what the root is...
              (sort-extract (right T))))) ;then add on to the value the rest of the right tree since it should be greater than value

(define testing (insert-list '(2 5 3 24 56 9 22) '()))
(sort-extract testing)

(define (tree-sort lst) ;putting it all together
  (if (null? lst);list is empty then give me the list back
      '()
  (sort-extract (insert-list lst '())))) ;otherwise call sort-extract on with the tree being made from the insert list into an empty list and produce the list in order!

(tree-sort '(2 94 8 1 59 23 19 100 3))

"3"
(define (is-leaf? tree) ;just to make things easier find out if the number you're trying to delete is a leaf (last number in one direction of the tree)
  (if (and (null? (left tree)) (null? (right tree))) ;if both the right and the left tree are empty then it is a leaf otherwise its a node or a root
      #t
      #f))

(define (combine-trees t1 t2) ;another helper function to allow for the trees to combine if i want to delete the root
  (if (null? t2)
      t1
      (make-tree (value t2) ;so the tree is split because u get rid of the node and you have to pull up one of the sides
                 (left t2) ;i have it so to combine the trees we need to make a new tree where the value of t1(right tree) becomes the new root
                 (combine-trees t1 (right t2)))));then the left side of the tree stays at s it is supposed to 
                        ;from there we build the right side of the tree with the rest of right t2 and t1
(define (delete-node val tree) ;so i want to delete a value from a tree
  (if (null? tree) ;if the tree is empty then simple...give me back the list
      '()
      (cond ((eq? val (value tree)) ;otherwise we have to do work, so first see if the value you want to delete is the root of the tree
             (cond ((is-leaf? tree)'()) ;if it is then you need to check if its the leaf ie, theres only one value in the tree, at that point return an empty list
                   ((and (null? (right tree)) (not (null? (left tree))))(left tree)) ;if the right tree is empty and the left tree is not then show left tree to move up
                   ((and (null? (left tree)) (not (null? (right tree))))(right tree)) ;if the left tree is empty and right tree is not then show right tree to move up
                   (else (combine-trees (right tree) ;otherwise if theres stuff in both we need to combine trees with the helper above where the right of the tree takes t1 and the left of the tree takes t2
                                        (left tree)))))
            ((> val (value tree)) (make-tree (value tree) ;then if the value is greater than the root you have to go into the right side of the tree to delete the value
                                             (left tree)
                                             (delete-node val (right tree)))) ;build a new list without the value
            ((< val (value tree)) (make-tree (value tree) ;and if it was less than the root, you have to go to the left side of the tree and build a new list on the left side
                                             (delete-node val (left tree)) ;following recurssion
                                             (right tree))))))

(define testdelete (insert-list '(8 2 0 9 11 4 1 3) '()))
(delete-node 9 testdelete)
(delete-node 8 testdelete) 
(delete-node 2 testdelete) 

"4"
(define (tree-map f T) ;tree to use apply a function to all the nodes within
  (cond ((null? T) ;if the tree is empty give me back the list
         '())
        ((and (null? (right T)) 
              (null? (left T))) 
         (make-tree (f (value T)) '() '())) ;if the right and the left is empty then make a tree where you apply the function to the value and make a tree with that value
        ((and (null? (right T)) 
              (not (null? (left T))))
         (make-tree (f (value T)) ;if the right is empty and the left isnt then make a new tree where you apply the function to the value 
                    (tree-map f (left T)) ;then apply the function recursively to the left side of the tree
                    '()));the right will be empty...
        ((and (null? (left T)) 
              (not (null? (right T))))
         (make-tree (f (value T));same thing as above just switched
                    '()
                    (tree-map f (right T))))
        (else (make-tree (f (value T)) ;for when the tree is nice and full apply the function to the root
                        (tree-map f (left T)) ;then to the left side
                        (tree-map f (right T)))))) ;then to the right side!

(define test-function-tree (insert-list '(2 1 3) '()))
(define test-function-tree1 (insert-list '(2 1 3 5 4) '()))
(define example5-test-function (lambda (x) (* x x)))
(tree-map example5-test-function test-function-tree) ;test is good since 1^2 is 1, 2^2 is 4, and 3^2 is 9 and the tree is still in contact
(tree-map example5-test-function test-function-tree1)
"5"
(define (One-Subtree S) 
  (if (null? (right S))
             (left S)
             (right S)))

(define (arithmetic-parse-tree T) ;yay parse tree
  (cond ((null? T) ;if the trees empty its easy
         '())
        ((is-leaf? T) (value T)) ;if the tree is just a leaf then give back the value
        
        ((and (and (not (null? (left T))) 
                   (not (null? (right T)))) ;if both the trees are not empty and the value of T is equal to the operator + 
              (eq? (value T) #\+))
         (+ (arithmetic-parse-tree (left T)) (arithmetic-parse-tree (right T)))) ;Then add the value of the left tree with the value of the right tree
       
        ((and (and (not (null? (left T))) 
                   (not (null? (right T)))) ;if both the trees are not empty and the value of T is equal to the operator * 
              (eq? (value T) #\*))
         (* (arithmetic-parse-tree (left T)) (arithmetic-parse-tree (right T))));Then multiply the value of the left tree with the value of the right tree
        
        ((and (or (and (null? (left T)) 
                       (not (null? (right T)))) ;If theres only  one subtree and the value is -
                  (and (not (null? (left T))) 
                       (null? (right T))))
              (eq? (value T) #\-))
         (arithmetic-parse-tree (- (arithmetic-parse-tree (One-Subtree T))))) ;then minus the value
        
        ((and (or (and (null? (left T)) 
                       (not (null? (right T)))) ;if theres one one subtree and the value is /
                  (and (not (null? (left T))) 
                       (null? (right T))))
              (eq? (value T) #\/))
         (/ 1 (arithmetic-parse-tree (One-Subtree T)))))) ;then divide 1 by the value of the subtree
         
(define example (list #\+ (list #\*
                                (list 4 '() '())
                                (list 5 '() '()))
                      (list #\+
                            (list #\/ (list 6 '() '()) '()) 
                            (list 7 '() '()))))
example
(arithmetic-parse-tree example)
 
"6"
(define (prepare x)
  (cond ((number? x) (number->string x))
        ((char? x) (string x))))

(define (prepared T) ;we have to first prepare the tree before we can add it all together!
  (tree-map prepare T))
"a"
(define (traverse-tree T)
  (if (null? T)
      '()
   (append (string->list (value T))
           (traverse-tree (left T))
           (traverse-tree (right T)))))
(prepared example)
(traverse-tree (prepared example)) ;test

(define (polish-notation T) ;polish notation cause i never knew prefix was called polish before
  (traverse-tree (prepared T)))
(polish-notation example)


"b"
(define (reverse lst) ;just reverse the list for post order
  (if (null? lst)
     '()
     (append (reverse (cdr lst)) 
             (list (car lst)))))

(define (reverse-polish-notation T)
  (reverse (traverse-tree (prepared T))))
(reverse-polish-notation example)