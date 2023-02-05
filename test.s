	.text
	.section	.rodata
r:
	.word	0
c:
	.word	0
i:
	.word	0
j:
	.word	0
n:
	.word	0
	.text
	.globl	abs
	.type	abs, @function
abs:
	addi	sp, sp, -36
	sw	ra, 0(sp)
	addi	t0, sp, 4
	sw	t0, 8(sp)
	sw	a0, 8(sp)
	lw	t0, 8(sp)
	sw	t0, 12(sp)
	li	t1, 0
	lw	t0, 12(sp)
	slt	t0, t1, t0
	sw	t0, 20(sp)
	lw	t1, 20(sp)
	beqz	t1, .L2.abs
	j	.L1.abs
.L1.abs:
	lw	t0, 8(sp)
	sw	t0, 24(sp)
	lw	t1, 24(sp)
	mv	a0, t1
	lw	ra, 0(sp)
.L2.abs:
	j	.L3.abs
.L3.abs:
	lw	t0, 8(sp)
	sw	t0, 28(sp)
	li	t1, 0
	lw	t0, 28(sp)
	sub	t0, t1, t0
	sw	t0, 32(sp)
	lw	t1, 32(sp)
	mv	a0, t1
	lw	ra, 0(sp)
	addi	sp, sp, 36
	ret
	.size	abs, .-abs

	.globl	main
	.type	main, @function
main:
	addi	sp, sp, -96
	sw	ra, 0(sp)
	lui	t1, %hi(i)
	addi	t1, t1, %lo(i)
	li	t0, 0
	sw	t0, 0(t1)
	j	.L1.main
.L1.main:
	lui	t1, %hi(i)
	addi	t1, t1, %lo(i)
	lw	t0, 0(t1)
	sw	t0, 4(sp)
	lw	t1, 4(sp)
	li	t0, 5
	slt	t0, t1, t0
	sw	t0, 12(sp)
	lw	t1, 12(sp)
	beqz	t1, .L3.main
	j	.L2.main
.L2.main:
	lui	t1, %hi(j)
	addi	t1, t1, %lo(j)
	li	t0, 0
	sw	t0, 0(t1)
	j	.L4.main
.L4.main:
	lui	t1, %hi(j)
	addi	t1, t1, %lo(j)
	lw	t0, 0(t1)
	sw	t0, 16(sp)
	lw	t1, 16(sp)
	li	t0, 5
	slt	t0, t1, t0
	sw	t0, 24(sp)
	lw	t1, 24(sp)
	beqz	t1, .L6.main
	j	.L5.main
.L5.main:
	call	getInt
	mv	t0, a0
	sw	t0, 28(sp)
	lui	t1, %hi(n)
	addi	t1, t1, %lo(n)
	lw	t0, 28(sp)
	sw	t0, 0(t1)
	lui	t1, %hi(n)
	addi	t1, t1, %lo(n)
	lw	t0, 0(t1)
	sw	t0, 32(sp)
	lw	t1, 32(sp)
	li	t0, 1
	sub	t0, t1, t0
	sw	t0, 36(sp)
	lw	t1, 36(sp)
	seqz	t0, t1
	sw	t0, 40(sp)
	lw	t1, 40(sp)
	beqz	t1, .L8.main
	j	.L7.main
.L7.main:
	lui	t1, %hi(i)
	addi	t1, t1, %lo(i)
	lw	t0, 0(t1)
	sw	t0, 44(sp)
	lui	t1, %hi(r)
	addi	t1, t1, %lo(r)
	lw	t0, 44(sp)
	sw	t0, 0(t1)
	lui	t1, %hi(j)
	addi	t1, t1, %lo(j)
	lw	t0, 0(t1)
	sw	t0, 48(sp)
	lui	t1, %hi(c)
	addi	t1, t1, %lo(c)
	lw	t0, 48(sp)
	sw	t0, 0(t1)
	j	.L9.main
.L8.main:
	j	.L9.main
.L9.main:
	lui	t1, %hi(j)
	addi	t1, t1, %lo(j)
	lw	t0, 0(t1)
	sw	t0, 52(sp)
	lw	t1, 52(sp)
	addi	t0, t1, 1
	sw	t0, 56(sp)
	lui	t1, %hi(j)
	addi	t1, t1, %lo(j)
	lw	t0, 56(sp)
	sw	t0, 0(t1)
	j	.L4.main
.L6.main:
	lui	t1, %hi(i)
	addi	t1, t1, %lo(i)
	lw	t0, 0(t1)
	sw	t0, 60(sp)
	lw	t1, 60(sp)
	addi	t0, t1, 1
	sw	t0, 64(sp)
	lui	t1, %hi(i)
	addi	t1, t1, %lo(i)
	lw	t0, 64(sp)
	sw	t0, 0(t1)
	j	.L1.main
.L3.main:
	lui	t1, %hi(r)
	addi	t1, t1, %lo(r)
	lw	t0, 0(t1)
	sw	t0, 68(sp)
	li	t1, 2
	lw	t0, 68(sp)
	sub	t0, t1, t0
	sw	t0, 72(sp)
	lw	t1, 72(sp)
	mv	a0, t1
	call	abs
	mv	t0, a0
	sw	t0, 76(sp)
	lui	t1, %hi(c)
	addi	t1, t1, %lo(c)
	lw	t0, 0(t1)
	sw	t0, 80(sp)
	li	t1, 2
	lw	t0, 80(sp)
	sub	t0, t1, t0
	sw	t0, 84(sp)
	lw	t1, 84(sp)
	mv	a0, t1
	call	abs
	mv	t0, a0
	sw	t0, 88(sp)
	lw	t1, 76(sp)
	lw	t0, 88(sp)
	add	t0, t1, t0
	sw	t0, 92(sp)
	lw	t1, 92(sp)
	mv	a0, t1
	call	printInt
	li	t1, 0
	mv	a0, t1
	mv	ra, a0
	lw	ra, 0(sp)
	addi	sp, sp, 96
	ret
	.size	main, .-main

