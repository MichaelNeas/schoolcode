		
#############################################################################################
#                                 Assignment 5. Merge Sort									#
#																							#
#  Group 21: Michael Neas                                                                   #
#																							#
#																							#
#############################################################################################

Merge_Sort:	
	beqz 	$a0, HitBaseCase						#null pointer check
	blt 	$a0, 0x10000000, Problem				#memory range catch!!!!!
	srl 	$t0, $a0, 2								#shift the maze bit to the right to check the conditions 
	sll 	$t0, $t0, 2								#shift back to the left
	bne 	$a0, $t0, Problem						#not equal somehow, should definitely be a problem

	lw 		$t0, 0($a0)	   		 					#Load the array length from the first call to merge sort.
	blt 	$t0, 0, Problem 						#negative array length

	li 		$t1, 2									#load 2 into t1 to check the base case of this code. 

	blt 	$t0, $t1, HitBaseCase					#if the length is less than 2 we break out and return		
													#otherwise we havent' hit the base case
	addi 	$sp, $sp, -20
	sw   	$a0, 0($sp)								#store address on a stack! Each recursive calls gens a new stack!
	sw 		$ra, 16($sp)							#store the return address on the stack
	div 	$t2,$t0, $t1							#quotient of array.length space /2
	sub     $t3,$t0, $t2							#array.length space - t2
	#t2 = sub1.length #t3 = sub2.length

	sll     $t1, $t1, 1								#2 turns into 4 to account for byte space.
	mul 	$t4,$t2, $t1							#multiply sub1.length * 4 to get total space for internal cells
	add 	$t4, $t4, $t1							#add another 4 bits to provide space for the size of the array in 0th cell
	bgt     $t4, $a0, HeapToStackProblem			#too large and wants to go overboard
	move 	$a0, $t4							    #allocate enough space for half of the array
	li 		$v0, 9 					    		    #syscall 9 (for the memory location)
	syscall
													#address of the allocated space is now in $v0 from syscall
	move    $t4 ,$v0								#Store the addess of the new half in the old t4 

	mul 	$t5, $t3, $t1							#multiply sub2. length by 4
	add 	$t5, $t5, $t1							#add another 4 bits to provide space for the size which will be stored in the 0th cell
	move	$a0, $t5							    #allocate enough space for half of the array
	li 		$v0, 9 							 	    #syscall 9 (sbrk)
	syscall

	move 	$t5, $v0								#load the address of array length - sub1 to t5
	#at this point t4 is the location address for sub 1 and t5 for sub 2	
	#we need to fill this array with the previous values now.

	#########################################################################################################################	
	# a0 will be the original array arguments 																				#
	# a1 will be the new empty array that we want to fill 																	#
	# a2 will be the size of the empty array, to put in 0th space. 															#
	# a3 will be the starting pointer for the original array.                         										#
	#########################################################################################################################	
	
	lw		$a0, 0($sp)								#mem location loaded from above stored in stack
	move 	$a1, $t4								#t4 for location of sub1 allocation
	move 	$a2, $t2 								#t2 for the length of this sub1
	li  	$a3, 4									#start from 4 in original array

	jal FillArray
	sw $v0, 4($sp)									#save the full sub1 address in 4th space of stack
	
	# Now we need to fill the second array, same arguments
	lw	$a0, 0($sp)									#reload original pointer
	move 	$a1, $t5								#load the sub2 allocated from before
	move 	$a2, $t3								#load the length of sub into the argument register
	move 	$a3, $v1								#load the counter for the next jal from the previous!

	jal FillArray									#call the function
	sw $v0, 8($sp)									#save sub2 in 8th space on the stack

	# Now we need to recursively call that on the filled arrays.
	# This is where it's messed up

	lw $a0, 4($sp) 									#move into register for recursive call
	jal Merge_Sort									#call Merge_sort
	sw $v0, 12($sp) 								#store the result in the stack argument register to be used on merging portion

	lw $a0, 8($sp)									#recursively call the second half
	jal Merge_Sort 									#call to Merge_Sort
	move $a2, $v0 									#store this result in a2 to also use in merging portion
	lw $a0, 0($sp)									#get the original array
	lw $a1, 12($sp)									#merge sorted arg sub array

	jal MergeHalves									#merging step returns a v0
	lw $ra, 16($sp)
	addi $sp, $sp, 20								#reallocating
	jr $ra 											#return it up
										

	#########################################################################################################################	
	# FillArray:																											#	
	# a0 will be the original array arguments 																				#
	# a1 will be the new empty array that we want to fill 																	#
	# a2 will be the size of the empty array, to put in 0th space. 															#
	# a3 will be the starting pointer for the original array.                         										#
	#	You have a counter for the original array and the sub array and you keep track of both, where we terminate 			#
	# 	When the original array's counter reaches :: subarray size in bytes + previous counter which is bytes as well 		#
	#	In return you get v0 = the address of the sub array's 0th element 													#
	#					  v1 = the counter variable. 																		#
	#########################################################################################################################

FillArray:
		add $a0, $a0, $a3							#The original array location with incoming counter
		move $t1, $a1								#store the address of the sub array
		sw $a2, 0($a1)								#store size in the first cell
		li $t6, 4									#4 increment variable, also counter for sub array
		mul $a2, $a2, $t6							#multiply the size of sub array by 4
		add $a2, $a2, $a3							#add for limit of loop, a2 becomes total end size
		addi $a1, $a1, 4							#add 4 to sub array
	fillLoop:
		bge $a3, $a2, filledExit 					#once the counter through big array is larger than the sub array size we can return it
		lw $t0, 0($a0)								#load a0 values to be stored.
		sw $t0, 0($a1)								#store them in a1
		addi $a0, $a0, 4							#add 4 to big array location
		addi $a1, $a1, 4							#add 4 to sub array location
		addi $a3, $a3, 4							#counter of all things
		j fillLoop 									#keep looping until array is filled
	filledExit:
		move $v0, $t1								#return the address of sub array at 0th position
		move $v1, $a2								#return the counter place.
		jr $ra 										#return to merge sort

	#########################################################################################################################	
	# MergeHalves:																											#
	# 	We pass a0 original, a1 and a2, both containing the sub arrays from the recursion									#
	#	This should combine sub arrays and add keep the length of the larger array 											#
	#	Returning a combined, sorted array in V0																			#
	#########################################################################################################################	
	
MergeHalves:
	addi $sp, $sp, -8								#store the original array location and the return address!
	sw $a0, 0($sp) 									#store array location
	sw $ra, 4($sp)									#store return address

	li $t0, 0										#counter for c1 
	li $t1,	0										#counter for c2 
	li $t5, 0										#i for the for loop

	lw $t9, 0($a0)									#length of large array, should be returned at the end
	lw $t2, 0($a1)									#get the size of sub1
	lw $t3, 0($a2) 									#get the size of sub2

	addi $a0, $a0, 4								#1st cell of the array with information a0
	addi $a1, $a1, 4								#add 4 to the address of sub1 to get information inside sub arrays
	addi $a2, $a2, 4 								#add 4 to the address of sub2 to get information inside sub arrays

	mergeLoop:
	bge $t5,$t9,mergeExit							#for loop conditions i<l

		bge $t0,$t2,takeFromSub2					#If sub-array #1 is empty, then take the next item from sub-array #2.

		bge $t1,$t3,takeFromSub1 					#Otherwise, take which ever available value is lower.

		lw $t6, 0($a1)								#sub1[c1] 
		lw $t7, 0($a2)								#sub2[c2]
		blt $t6,$t7,takeFromSub1 					#compare array vals :: sub1[c1] < sub2[c2]

		b takeFromSub2								#else take from sub2
	mergeExit:
		lw $v0, 0($sp)
		lw $ra, 4($sp)
		addi $sp, $sp, 8	
		jr $ra 										#return sorted array

takeFromSub2:										#a[i] = sub2[c2++];
	addi $t1, $t1, 1								#increment c2 by 1
	lw $t7, 0($a2)									#sub2[c2++]
	sw $t7, 0($a0)									#we're storing ai from the next sub value
	addi $t5, $t5, 1								#increment for loop
	addi $a2, $a2, 4								#increment sub2 position
	addi $a0, $a0, 4								#increment the original array
	j mergeLoop										#go back to the loop
takeFromSub1:										#a[i] = sub1[c1++];
	addi $t0, $t0, 1								#increment c1 by 1
	lw $t7, 0($a1)									#sub1[c1++]
	sw $t7, 0($a0)									#we're storing ai from the next sub value
	addi $t5, $t5, 1								#increment for loop
	addi $a1, $a1, 4								#increment sub1 position
	addi $a0, $a0, 4 								#increment original array
	j mergeLoop										#go back to the loop

#error catching to satisfy test suite.
Problem:
	li $v0, 0					#put the out of bounds on
	jr $ra          			#cance it if theres no where to go.

HeapToStackProblem:
	lw $ra, 16($sp)
	addi $sp, $sp, 20								#reallocating
	li $v0, 0 										#put in v0
	jr $ra 	

HitBaseCase:										#we hit the base case so we have to return to v0 so it can be checked
	move $v0, $a0 									#put in v0
	jr $ra 											#return register