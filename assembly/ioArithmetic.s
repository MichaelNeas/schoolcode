#Michael Neas Program 1
main:
	.data
	getA: .asciiz "Enter a value for a: "   #User enters a
    getX: .asciiz "Enter a value for x: "   #User enters x
    outY: .asciiz "y="						#Output after calculation
    outR: .asciiz " R"						#Remainder output
    outDot: .asciiz "."						#Period after the remainder output

	.text
	li $v0, 4								#Load 4 in v0 to tell it to print a string
	la $a0, getA							#load getA into the string cannon, first register in path from v0
	syscall									#execute what's in v0

	li $v0, 5								#get an integer input
	syscall									#execute read
	move $t0, $v0							#load argument register a0, for a into temp register t0

	li $v0, 4								#Load 4 in v0 to tell it to print a string
	la $a0, getX							#load getX into the first register in path from v0
	syscall									#execute what's in v0

	li $v0, 5								#get an integer input
	syscall									#execute read
	move $t1, $v0							#load argument register a0, for x into temp register t1

	addi $t0, $t0, 1						#add 1 to a in the same register where a is stored

	li $t2, 4 								#t2 is a constant 4, the max for the loop
	li $t3, 0 								#t3 is the counter
	move $t4,$t1							#copy x to t4 to use to multiply by itself
loop:
	beq $t2, $t3, end 						#if t3 == 4 we are done
	mul $t1, $t1, $t4						#multiple x by itself
	addi $t3, $t3, 1 						#add 1 to t3 to incriment + 1
	j loop 									#jump back to the top
end:

	mul $t0, $t1, $t0						#multiply x^5 by a+1 and store where a was cause its done now

	add $t1, $t4, $t4						#multiply copy of x by 2 by adding them together

	add $t1, $t0, $t1						#add together t0 and t1

	addi $t4, $zero, 7						#assign t4 to be 7
	div $t1, $t4							#divide what we added by 7

	mfhi $t1								#move from high is remainder

	mflo $t2								#move from low is whole

	li $v0, 4								#address a string made before
	la $a0, outY							#y= to be printed
	syscall

	li $v0, 1								#address an integer 
	move $a0, $t2							#print the whole number
	syscall

	li $v0, 4								#address a string
	la $a0, outR							#snag the R string
	syscall

	li $v0, 1								#load an integer
	move $a0, $t1							#move remainder to be printed
	syscall

	li $v0, 4								#load a string
	la $a0, outDot							#the dot!!!
	syscall

	xor $t0, $t0, $t0						#clear registers
	xor $t1, $t1, $t1						#clear registers
	xor $t2, $t2, $t2						#clear registers
	xor $t3, $t3, $t3						#clear registers
	xor $t4, $t4, $t4						#clear registers

	li $v0, 10								#exit program
	syscall
