	.text
	.section	.rodata
s:
	.word	0
t:
	.word	0
i:
	.word	0
l:
	.word	0
	.text
	.globl	main
	.type	main, @function
main:
	addi	sp, sp, -108
	sw	ra, 0(sp)
	call	getInt
	mv	t0, a0
	sw	t0, 4(sp)
	lui	t1, %hi(t)
	addi	t1, t1, %lo(t)
	lw	t0, 4(sp)
	sw	t0, 0(t1)
	lui	t1, %hi(i)
	addi	t1, t1, %lo(i)
	li	t0, 0
	sw	t0, 0(t1)
	j	.L1
.L1:
	lui	t1, %hi(i)
	addi	t1, t1, %lo(i)
	lw	t0, 0(t1)
	sw	t0, 8(sp)
	lui	t1, %hi(t)
	addi	t1, t1, %lo(t)
	lw	t0, 0(t1)
	sw	t0, 12(sp)
	lw	t1, 8(sp)
	lw	t0, 12(sp)
	slt	t0, t1, t0
	sw	t0, 20(sp)
	lw	t1, 20(sp)
	beqz	t1, .L3
	j	.L2
.L2:
	lui	t1, %hi(i)
	addi	t1, t1, %lo(i)
	lw	t0, 0(t1)
	sw	t0, 24(sp)
	lw	t1, 24(sp)
	addi	t0, t1, 1
	sw	t0, 28(sp)
	lui	t1, %hi(i)
	addi	t1, t1, %lo(i)
	lw	t0, 28(sp)
	sw	t0, 0(t1)
	call	getString
	mv	t0, a0
	sw	t0, 32(sp)
	lui	t1, %hi(s)
	addi	t1, t1, %lo(s)
	lw	t0, 32(sp)
	sw	t0, 0(t1)
	lui	t1, %hi(s)
	addi	t1, t1, %lo(s)
	lw	t0, 0(t1)
	sw	t0, 36(sp)
	lw	t1, 36(sp)
	mv	a0, t1
	call	__mx_length
	mv	t0, a0
	sw	t0, 40(sp)
	lui	t1, %hi(l)
	addi	t1, t1, %lo(l)
	lw	t0, 40(sp)
	sw	t0, 0(t1)
	lui	t1, %hi(l)
	addi	t1, t1, %lo(l)
	lw	t0, 0(t1)
	sw	t0, 44(sp)
	li	t1, 10
	lw	t0, 44(sp)
	slt	t0, t1, t0
	sw	t0, 52(sp)
	lw	t1, 52(sp)
	beqz	t1, .L5
	j	.L4
.L4:
	lui	t1, %hi(s)
	addi	t1, t1, %lo(s)
	lw	t0, 0(t1)
	sw	t0, 56(sp)
	lw	t1, 56(sp)
	mv	a0, t1
	li	t1, 0
	mv	a1, t1
	li	t1, 1
	mv	a2, t1
	call	__mx_substring
	mv	t0, a0
	sw	t0, 60(sp)
	lui	t1, %hi(l)
	addi	t1, t1, %lo(l)
	lw	t0, 0(t1)
	sw	t0, 64(sp)
	lw	t1, 64(sp)
	li	t0, 2
	sub	t0, t1, t0
	sw	t0, 68(sp)
	lw	t1, 68(sp)
	mv	a0, t1
	call	toString
	mv	t0, a0
	sw	t0, 72(sp)
	lw	t1, 60(sp)
	mv	a0, t1
	lw	t1, 72(sp)
	mv	a1, t1
	call	__str_add
	mv	t0, a0
	sw	t0, 76(sp)
	lui	t1, %hi(s)
	addi	t1, t1, %lo(s)
	lw	t0, 0(t1)
	sw	t0, 80(sp)
	lui	t1, %hi(l)
	addi	t1, t1, %lo(l)
	lw	t0, 0(t1)
	sw	t0, 84(sp)
	lw	t1, 84(sp)
	li	t0, 1
	sub	t0, t1, t0
	sw	t0, 88(sp)
	lui	t1, %hi(l)
	addi	t1, t1, %lo(l)
	lw	t0, 0(t1)
	sw	t0, 92(sp)
	lw	t1, 80(sp)
	mv	a0, t1
	lw	t1, 88(sp)
	mv	a1, t1
	lw	t1, 92(sp)
	mv	a2, t1
	call	__mx_substring
	mv	t0, a0
	sw	t0, 96(sp)
	lw	t1, 76(sp)
	mv	a0, t1
	lw	t1, 96(sp)
	mv	a1, t1
	call	__str_add
	mv	t0, a0
	sw	t0, 100(sp)
	lw	t1, 100(sp)
	mv	a0, t1
	call	println
	j	.L6
.L5:
	lui	t1, %hi(s)
	addi	t1, t1, %lo(s)
	lw	t0, 0(t1)
	sw	t0, 104(sp)
	lw	t1, 104(sp)
	mv	a0, t1
	call	println
	j	.L6
.L6:
	j	.L1
.L3:
	li	t1, 0
	mv	a0, t1
	mv	ra, a0
	lw	ra, 0(sp)
	addi	sp, sp, 108
	ret
	.size	main, .-main

