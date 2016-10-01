#Michael Neas Program 2
main:
	.data
	getPrincipal: 		.asciiz "Enter the principal:  $"   			#User enters principal, p
    getInterestRate: 	.asciiz "Enter the interest rate: "   			#User enters interest rate, r
    getTime: 			.asciiz "Enter the amount of time: "			#User enters time, t
    outBalance:			.asciiz "The ending balance is $"				#Ending balance, 𝑏 = 𝑝(1 + 𝑟)^𝑡
    outPennies:			.asciiz "."										#correct magnitude can be calculated by dividing by 100^t
    quitOption:			.asciiz "Enter the principal or 0 to quit:  $"	#continue or end program
    newLine:			.asciiz "\n" 									#break for continuation or quit

    .text   
    li $v0, 4								#Load 4 in v0 to tell it to print a string
	la $a0, getPrincipal					#load principal into the string cannon, first register in path from v0
	syscall									#execute what's in v0

	li $v0, 5								#get an integer input
	syscall									#execute read
	move $s3, $v0							#load argument register a0, for principal into temp register t0
	
top:
	li $v0, 4								#Load 4 in v0 to tell it to print a string
	la $a0, getInterestRate					#load getX into the first register in path from v0
	syscall									#execute what's in v0

	li $v0, 5								#get an integer input
	syscall									#execute read
	move $t1, $v0							#load argument register a0, for interest rate into temp register t1

	li $v0, 4								#Load 4 in v0 to tell it to print a string
	la $a0, getTime							#load getTime into the first register in path from v0
	syscall									#execute what's in v0

	li $v0, 5								#get an integer input  
	syscall									#execute read
	move $s0, $v0							#load argument register a0, for time into temp register s0 to save

	addi $t1, $t1, 100						#add 100 to a in the same register where interest rate is stored
	li $s1, 100								#load 100 for the penny division
	move $s2,$t1							#copy 1+r to s2 to use to multiply by itself for the loop simulation

	mul $t0, $s3, $s1						#100 * prinicipal for pennies, p
	mul $t0, $t0, $t1						#pennies*percentage

	li $t2, 1 								#t2 is a counter to go to t for power
loop:
	beq $s0, $t2, end 						#if t2 == t we are done
	div $t0, $s1							#divide by 100 for percentage
	mflo $t0								#snag the lo for future mulitplication
	mul $t0, $t0, $s2						#multiply by percentage to be looped on
	addi $t2, $t2, 1 						#add 1 to t3 to incriment + 1
	j loop 									#jump back to the top
end:

	div $t0, $s1							#remove percentage
	mflo $t0								#/100 to remove the last percentage
	div $t0, $s1							#remove pennies
	mflo $t2								#now its back in dollars
	mfhi $t3								#cents on the dollar

	li $v0, 4								#address a string made before
	la $a0, outBalance						#b= to be printed
	syscall

	li $v0, 1								#address an integer 
	move $a0, $t2							#print the whole number
	syscall

	li $v0, 4								#address a string
	la $a0, outPennies						#snag the . string
	syscall

	li $v0, 1								#load an integer
	move $a0, $t3							#move remainder to be printed
	syscall

	la $a0, newLine							#new line to abide by hw requirements
	li $v0, 4								#address a string
	syscall
	syscall

	li $v0, 4								#check the user input for an int value
	la $a0, quitOption						#determine which one
	syscall

	li $v0, 5								#get an integer input  
	syscall									#execute read
	move $s3, $v0							#load argument register s3, for future principal if exists

	li $s4, 0								#quitOption value to compare to

	beq $s3, $s4, quit 						#if input is 0 then quit
	j top  									#else go to the top

quit:
	xor $t0, $t0, $t0						#clear registers
	xor $t1, $t1, $t1						#clear registers
	xor $t2, $t2, $t2						#clear registers
	xor $t3, $t3, $t3						#clear registers
	xor $s0, $s0, $s0						#clear registers
	xor $s1, $s1, $s1						#clear registers
	xor $s2, $s2, $s2						#clear registers

	li $v0, 10								#exit program
	syscall
