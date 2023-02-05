	.text
	.globl	func
	.type	func, @function
func:
	addi	sp, sp, -32
	sw	ra, 0(sp)
	li	t0, 0
	add	t0, sp, t0
	sw	t0, 8(sp)
	lw	t1, 8(sp)
	sw	a0, 0(t1)
	li	t0, 4
	add	t0, sp, t0
	sw	t0, 12(sp)
	lw	t1, 12(sp)
	sw	a1, 0(t1)
	lw	t1, 8(sp)
	lw	t0, 0(t1)
	sw	t0, 16(sp)
	lw	t1, 12(sp)
	lw	t0, 0(t1)
	sw	t0, 20(sp)
	lw	t1, 16(sp)
	lw	t0, 20(sp)
	add	t0, t1, t0
	sw	t0, 24(sp)
	lw	t1, 24(sp)
	mv	a0, t1
	lw	ra, 0(sp)
	addi	sp, sp, 32
	ret
	.size	func, .-func

	.globl	main
	.type	main, @function
main:
	addi	sp, sp, -16
	sw	ra, 0(sp)
	li	t0, 0
	add	t0, sp, t0
	sw	t0, 4(sp)
	li	t1, 1
	mv	a0, t1
	li	t1, 2
	mv	a1, t1
	call	func
	mv	t0, a0
	sw	t0, 8(sp)
	lw	t1, 4(sp)
	lw	t0, 8(sp)
	sw	t0, 0(t1)
	li	t1, 0
	mv	a0, t1
	lw	ra, 0(sp)
	addi	sp, sp, 16
	ret
	.size	main, .-main

