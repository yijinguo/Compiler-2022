	.text
	.globl	main
	.type	main, @function
main:
	addi	sp, sp, 0
	sw	ra, -4(sp)
	li	t1, 0
	mv	a0, t1
	lw	ra, -4(sp)
	addi	sp, sp, 0
	ret
	.size	main, .-main

