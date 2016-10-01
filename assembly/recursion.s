
#####################################################################################
#                                                                                   #
#                 Michael Neas  Program 4                                           #
#																					#
#####################################################################################
#																					#
#  					  	Linked List Maze Maze_Solver		 						#
#																					#
#																					#
# linked_list Maze_Solver (maze m, int x, int y, int previous_direction) { 			#
#		int directions = m.get(x, y);												#	
# 		if (directions & 16 > 0)													#
# 			return add_head(null, “X”);												#
# 		if ((directions & 8 > 0) && (previous_direction != 8)) {					#
#           linked_list l = Maze_Solver (m, x, y+1, 4);								#
#           if (l != null)															#
#                 return add_head(l, “U”);											#	
# 		}																			#
# 		if ((directions & 4 > 0) && (previous_direction != 4)) {					#
# 			linked_list l = Maze_Solver (m, x, y-1, 8); if (l != null)				#
#                 return add_head(l, “D”);											#
# 		}																			#
# 		if ((directions & 2 > 0) && (previous_direction != 2)) {					#
# 			linked_list l = Maze_Solver (m, x-1, y, 1); if (l != null)				#
#                 return add_head(l, “L”);											#
# 		}																			#
# 		if ((directions & 1 > 0) && (previous_direction != 1)) {					#
# 			linked_list l = Maze_Solver (m, x+1, y, 2); if (l != null)				#
#                 return add_head(l, “R”);											#
#       }																			#
#       return null;																#
# }																					#
#																					#
#####################################################################################
#																					#
# 			$a0 contains the address of the maze object 							#
#			$a1 contains the parameter x 											#
#			$a2 contains the parameter y 											#
#			$a3 contains the direction that you just moved							#	
#			return results in $v0													#
#																					#
#####################################################################################
#																					#	
#    add_head Parameters:															#
#    $a0:  A pointer to an existing solution linked list.							#
#    $a1:  The new data to add to the solution linked list.							#
#																					#
#    maze_get Parameters:															#
#    $a0:  A pointer to a maze.														#
#    $a1:  The column (x) to read from. 											#
#    $a2:  The row (y) to read from.												#
#																					#
#																					#
#####################################################################################

Maze_Solver:    
	#  Save our parameters and return address on da stack
	# Maze_Solver (maze m, int x, int y, int previous_direction)
	blt $a0, 0x10000000, Problem	# memory range catch!!!!!
	srl $t0, $a0, 2				#shift the maze bit to the right to check the conditions 
	sll $t0, $t0, 2				#shift back to the left
	#la $t0, $a0 				#I tried to store it directly before and that didn't work so lab told me to srl and sll
	bne $a0, $t0, Problem		# not equal somehow, should definitely be a problem

	#set up the stack for the maze variables
	addi $sp, $sp, -24			#stack pointer to -24 to allocate for 6 params
	sw $a0,  0 ($sp)			#maze address as a word
	sw $a1,  4 ($sp)			#x parameter 
	sw $a2,  8 ($sp)			#y parameter
	sw $a3, 12 ($sp)			#direction from which the previous move came from
	sw $ra, 16 ($sp)			#return allocation for the various function call outputs
	sw $v0, 20 ($sp)			#places to go.

	jal maze_get				#int directions = m.get(x, y) called to get the current maze situation.

	#first condition in the c++ code
	andi $t0, $v0, 16 			#bitwise and with directions & 16, compares each bit and the result is the smaller number
	bgt $t0, $zero, firstOutput	#check if greater than 0
	j MoveUP					#otherwise check the move up stuffs

firstOutput:
	li $a0, 0					#at solution, don't need maze reference anymore
	li $a1, 88		 			#exit char, capital X
	jal add_head				#add the last head to linklist

	lw $ra 16 ($sp) 			#load the return from add head
	addi $sp, $sp, 24			#deacollocate everything!
	jr $ra 						#return the steps

	#second condition
MoveUP:
	lw $a3, 12($sp)				#where it came from
	andi $t0, $v0, 8			#bitwise with directions & 8
	blt $t0, 1, MoveDown 		#cond1: branch if directions&8 > 0 try to go down
	beq $a3, 8, MoveDown		#is where we came from not equal to 8, second cond
	j secondOutput				#both satisfy

secondOutput:
	lw $a0, 0($sp)  			#(m, from stack
	lw $a1, 4($sp)  			# x, from stack
	lw $a2, 8($sp)  			# y, from stack
	li $a3, 4					# 4); immediate

	addi $a2, $a2, 1			# add 1 to y as in the recursive method call

	jal Maze_Solver				#call Maze Solver again!

	beqz $v0, MoveDown			#check for neq 0

	move $a0, $v0				#copy v0 into a0
	li $a1, 85					#asci for 85 is U

	jal add_head				#add it to linked list
	lw $ra, 16 ($sp)			#the list returned should be stored from the stack

	addi $sp, $sp, 24			#clean up
	jr $ra 						#return it!
	
	#3rd condition
MoveDown:
	lw $a3, 12($sp)				#where it came from previously
	#lw $v0, 20($sp)
	andi $t0, $v0, 4			#bitwise with directions & 4, if offset, move down
	blt $t0, 1, MoveLeft		#first condition to check, so its equal 
	beq $a3, 4, MoveLeft		#second condition.
	j thirdOutput				#both satisfied

thirdOutput:
	lw $a0, 0($sp)  			#(m,
	lw $a1, 4($sp)  			#x,
	lw $a2, 8($sp)  			#y-1,
	li $a3, 8					#8 because we came from up

	addi $a2, $a2, -1			#gives the subtraction to y

	jal Maze_Solver				#recursively call it

	beqz $v0, MoveLeft			#error cause no place to go.

	move $a0, $v0				#copy into a0, returned maze
	li $a1, 68					#a1 is D

	jal add_head				#add it to the linked list
	lw $ra, 16 ($sp)			#put the returned list on the stack

	addi $sp, $sp, 24			#clean up
	jr $ra						#return it!
	
	#4th condition
MoveLeft:
	lw $a3, 12($sp)				#previous direction 
	#lw $v0, 20($sp)				#ahhhh
	andi $t0, $v0, 2			#bitwise with directions & 2
	blt $t0, 1, MoveRight		#first check
	beq $a3, 2, MoveRight 		#then check this
	j fourthOutput 				#both satisfy

fourthOutput:
	lw $a0, 0($sp)  			#(m,
	lw $a1, 4($sp)  			#x-1
	lw $a2, 8($sp)  			#y
	li $a3, 1					#1 because we came from the right

	addi $a1, $a1, -1			#gives the x-1 we need for moving

	jal Maze_Solver				#call the maze solver with correct parameterrs

	beqz $v0, MoveRight			#null pointer moves right

	move $a0, $v0				#otherwise we're copying it to a0 to add to linked list
	li $a1, 76					#with the L ascii

	jal add_head				#Adds a direction to the head of a linked list representing the solution to the maze.	
	lw $ra, 16 ($sp)			#put this list back in the stack	

	addi $sp, $sp, 24			#clean up
	xor $t0, $t0, $t0			#clear registers
	jr $ra  					#return it!
	
	#5th condition
MoveRight:
	lw $a3, 12($sp)				#load the places it's been
	lw $v0, 20($sp)				#load the places it can go
	andi $t0, $v0, 1			#bitwise with directions & 1
	blt $t0, 1, Explode			#cond1: branch if directions&8 > 0 its all over
	beq $a3, 1, Explode			#is where we came from not equal to 1, second cond
	j fifthOutput

fifthOutput:
	lw $a0, 0($sp)  			#(m,
	lw $a1, 4($sp)  			# x+1,
	lw $a2, 8($sp)  			# y
	li $a3, 2					#2 because we came from the left			

	addi $a1, $a1, 1			#add 1 to x
	
	jal Maze_Solver				#recursively call maze solver

	beqz $v0, Explode			#error cause no place to go.

	move $a0, $v0 				#if there is nothing wrong and it's a path to move on add it
	li $a1, 82					#ascii for R

	jal add_head				#add it to the linked list.
	lw $ra, 16 ($sp)			#put this list back in the stack

	addi $sp, $sp, 24			#clean up
	xor $t0, $t0, $t0			#clear registers
	jr $ra 						#return it.

#error catching to satisfy test suite.
Problem:
	li $v0, -1					#put the out of bounds on
	jr $ra          			#cance it if theres no where to go.

#return null when none of the conditions are satisfied.
Explode:
	li $v0, 0  					#there's no more possibilities
	lw $ra, 16 ($sp)			#return address loaded in, it got this far
	addi $sp, $sp, 24 			#clean up
	xor $t0, $t0, $t0			#clear registers
	jr $ra 						#return it!