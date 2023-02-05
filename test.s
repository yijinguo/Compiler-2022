	.text
	.section	.rodata
	.text
	.globl	main
	.type	main, @function
main:
	addi	sp, sp, -120
	sw	ra, 0(sp)
	addi	t0, sp, 4
	sw	t0, 20(sp)
	li	t0, 10
	sw	t0, 20(sp)
	addi	t0, sp, 8
	sw	t0, 24(sp)
	li	t0, 0
	sw	t0, 24(sp)
	addi	t0, sp, 12
	sw	t0, 28(sp)
	li	t0, 1
	sw	t0, 28(sp)
	j	.L1
.L1:
	lw	t0, 28(sp)
	sw	t0, 32(sp)
	lw	t0, 20(sp)
	sw	t0, 36(sp)
	lw	t1, 36(sp)
	lw	t0, 32(sp)
	slt	t0, t1, t0
	sw	t0, 40(sp)
	lw	t1, 40(sp)
	xori	t0, t1, 1
	sw	t0, 44(sp)
	lw	t1, 44(sp)
	beqz	t1, .L3
	j	.L2
.L2:
	lw	t0, 24(sp)
	sw	t0, 48(sp)
	lw	t0, 28(sp)
	sw	t0, 52(sp)
	lw	t1, 48(sp)
	lw	t0, 52(sp)
	add	t0, t1, t0
	sw	t0, 56(sp)
	lw	t0, 56(sp)
	sw	t0, 24(sp)
	lw	t0, 28(sp)
	sw	t0, 60(sp)
	lw	t1, 60(sp)
	addi	t0, t1, 1
	sw	t0, 64(sp)
	lw	t0, 64(sp)
	sw	t0, 28(sp)
	j	.L1
.L3:
	addi	t0, sp, 16
	sw	t0, 68(sp)
	li	t0, 1
	sw	t0, 68(sp)
	j	.L4
.L4:
	lw	t0, 68(sp)
	sw	t0, 72(sp)
	lw	t0, 20(sp)
	sw	t0, 76(sp)
	lw	t1, 76(sp)
	lw	t0, 72(sp)
	slt	t0, t1, t0
	sw	t0, 80(sp)
	lw	t1, 80(sp)
	xori	t0, t1, 1
	sw	t0, 84(sp)
	lw	t1, 84(sp)
	beqz	t1, .L6
	j	.L5
.L5:
	lw	t0, 24(sp)
	sw	t0, 88(sp)
	lw	t1, 88(sp)
	addi	t0, t1, 10
	sw	t0, 92(sp)
	lw	t0, 68(sp)
	sw	t0, 96(sp)
	lw	t1, 92(sp)
	lw	t0, 96(sp)
	add	t0, t1, t0
	sw	t0, 100(sp)
	lw	t0, 100(sp)
	sw	t0, 24(sp)
	lw	t0, 68(sp)
	sw	t0, 104(sp)
	lw	t1, 104(sp)
	addi	t0, t1, 1
	sw	t0, 108(sp)
	lw	t0, 108(sp)
	sw	t0, 68(sp)
	j	.L4
.L6:
	lw	t0, 24(sp)
	sw	t0, 112(sp)
	lw	t1, 112(sp)
	mv	a0, t1
	call	printInt
	lw	t0, 24(sp)
	sw	t0, 116(sp)
	lw	t1, 116(sp)
	mv	a0, t1
	mv	ra, a0
	lw	ra, 0(sp)
	addi	sp, sp, 120
	ret
	.size	main, .-main

