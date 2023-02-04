	.text
	.globl	main
	.type	main, @function
main:
	addi	sp, sp, -4
	sw	ra, 0(sp)
	li	t1, 0
	mv	a0, t1
	lw	ra, 0(sp)
	addi	sp, sp, 4
	ret
	.size	main, .-main

