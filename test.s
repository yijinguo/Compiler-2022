	.text
	.section	.rodata
.L.str:
	.asciz	"YES"
w:
	.word	0
.L.str.1:
	.asciz	"NO"
	.text
	.globl	main
	.type	main, @function
main:
	addi	sp, sp, -80
	sw	ra, 0(sp)
	call	getInt
	mv	t0, a0
	sw	t0, 8(sp)
	lui	t1, %hi(w)
	addi	t1, t1, %lo(w)
	lw	t0, 8(sp)
	sw	t0, 0(t1)
	lui	t1, %hi(w)
	addi	t1, t1, %lo(w)
	lw	t0, 0(t1)
	sw	t0, 12(sp)
	lw	t1, 12(sp)
	li	t0, 2
	rem	t0, t1, t0
	sw	t0, 16(sp)
	lw	t1, 16(sp)
	li	t0, 0
	sub	t0, t1, t0
	sw	t0, 20(sp)
	lw	t1, 20(sp)
	seqz	t0, t1
	sw	t0, 24(sp)
	lui	t1, %hi(w)
	addi	t1, t1, %lo(w)
	lw	t0, 0(t1)
	sw	t0, 28(sp)
	li	t1, 2
	lw	t0, 28(sp)
	slt	t0, t1, t0
	sw	t0, 36(sp)
	addi	t0, sp, 4
	sw	t0, 40(sp)
	lw	t1, 24(sp)
	li	t0, 0
	sub	t0, t1, t0
	sw	t0, 44(sp)
	lw	t1, 44(sp)
	snez	t0, t1
	sw	t0, 48(sp)
	lw	t1, 48(sp)
	beqz	t1, .L3
	j	.L1
.L1:
	lw	t1, 36(sp)
	li	t0, 0
	sub	t0, t1, t0
	sw	t0, 52(sp)
	lw	t1, 52(sp)
	snez	t0, t1
	sw	t0, 56(sp)
	lw	t1, 56(sp)
	beqz	t1, .L3
	j	.L2
.L2:
	li	t0, 1
	sw	t0, 40(sp)
	j	.L4
.L3:
	li	t0, 0
	sw	t0, 40(sp)
	j	.L4
.L4:
	lw	t0, 40(sp)
	sw	t0, 60(sp)
	lw	t1, 60(sp)
	beqz	t1, .L6
	j	.L5
.L5:
	li	t1, 0
	slli	t0, t1, 2
	sw	t0, 64(sp)
	lui	t1, %hi(.L.str)
	addi	t1, t1, %lo(.L.str)
	lw	t0, 64(sp)
	add	t0, t1, t0
	sw	t0, 68(sp)
	lw	t1, 68(sp)
	mv	a0, t1
	call	print
	j	.L7
.L6:
	li	t1, 0
	slli	t0, t1, 2
	sw	t0, 72(sp)
	lui	t1, %hi(.L.str.1)
	addi	t1, t1, %lo(.L.str.1)
	lw	t0, 72(sp)
	add	t0, t1, t0
	sw	t0, 76(sp)
	lw	t1, 76(sp)
	mv	a0, t1
	call	print
	j	.L7
.L7:
	li	t1, 0
	mv	a0, t1
	lw	ra, 0(sp)
	addi	sp, sp, 80
	ret
	.size	main, .-main

