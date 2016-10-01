#Michael Neas Program 3
main:
	.data
	easy: 			.asciiz "1. An easy puzzle \n"  					#easy puzzle choice
    medium: 		.asciiz "2. A medium puzzle \n"   					#medium puzzle choice
    hard: 			.asciiz "3. A hard puzzle \n"						#hard puzzle choice
    evil:			.asciiz "4. An evil puzzle \n"						#evil puzzle choice
    unsolvable:		.asciiz "5. An unsolvable puzzle \n"				#unsolvable puzzle choice
    quit:			.asciiz "6. Quit \n"								#quit choice
    userChoice:		.asciiz "Choose an option: " 						#option coice
    warning:		.asciiz "You input a number too large or small! \n" #warning

    .text   
top:    
    li $v0, 4								#Load 4 in v0 to tell it to print a string
	la $a0, easy							#load easy into the string cannon, first register in path from v0
	syscall									#execute what's in v0
    li $v0, 4								#Load 4 in v0 to tell it to print a string
	la $a0, medium							#load medium into the string cannon, first register in path from v0
	syscall									#execute what's in v0
	li $v0, 4								#Load 4 in v0 to tell it to print a string
	la $a0, hard							#load hard into the string cannon, first register in path from v0
	syscall									#execute what's in v0
	li $v0, 4								#Load 4 in v0 to tell it to print a string
	la $a0, evil							#load evil into the string cannon, first register in path from v0
	syscall									#execute what's in v0
	li $v0, 4								#Load 4 in v0 to tell it to print a string
	la $a0, unsolvable						#load unsolvable into the string cannon, first register in path from v0
	syscall									#execute what's in v0
	li $v0, 4								#Load 4 in v0 to tell it to print a string
	la $a0, quit							#load quit into the string cannon, first register in path from v0
	syscall									#execute what's in v0
	li $v0, 4								#Load 4 in v0 to tell it to print a string
	la $a0, userChoice						#load user into the string cannon, first register in path from v0
	syscall									#execute what's in v0

	li $v0, 5								#get an integer input from user
	syscall									#execute read
	move $s0, $v0							#load argument register v0, for principal into temp register s0
	
	li $t0, 6								#for check if greater than 6 and exit
	blt $s0,$zero,warningLabel				#less than 0 input
	bgt $s0,$t0,warningLabel				#greater than 6
	beq $s0, $t0, quIt 						#if input is 6 then quit

	li $t0, 1								#easy option
	la $a0, Easy 							#load easy for functions.asm
	beq $s0, $t0, activateFunctionCalls		#if input is 1 then go to function

	li $t0, 2								#medium option
	la $a0, Medium 							#load easy for functions.asm
	beq $s0, $t0, activateFunctionCalls		#if input is 1 then go to function

	li $t0, 3								#hard option
	la $a0, Hard 							#load easy for functions.asm
	beq $s0, $t0, activateFunctionCalls		#if input is 1 then go to function

	li $t0, 4								#evil option
	la $a0, Evil 							#load easy for functions.asm
	beq $s0, $t0, activateFunctionCalls		#if input is 1 then go to function
	
	li $t0, 5								#unsolvable option
	la $a0, Unsolvable						#load easy for functions.asm
	beq $s0, $t0, activateFunctionCalls		#if input is 1 then go to function

activateFunctionCalls:

	move $s1, $a0							#copy to a1 for the pre-print

	jal Sudoku_solver						#call Sudoku_solver, holds return address for a subroutine
	
	move $a0, $v0							#copy the solved to print location
	move $a1, $s1							#save it
	
	jal Print_Sudoku						#print solved puzzle

	j top

warningLabel:
	li $v0, 4								#Load 4 in v0 to tell it to print a string
	la $a0, warning							#load warning into the string cannon, first register in path from v0
	syscall									#execute what's in v0
	j top  									#else go to the top

quIt:
	xor $t0, $t0, $t0						#clear registers
	xor $s0, $s0, $s0						#clear registers
	li $v0, 10								#exit program
	syscall
