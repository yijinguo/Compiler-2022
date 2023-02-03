.L.str:
		.asciz	""
a:
		.word	null
_GLOBAL__sub_I_example.cpp:
.L0:
	addi	sp, sp, 0
	sw	ra, 0(sp)
	call	__global_new_init_a
	lw	ra, 0(sp)
	addi	sp, sp, 0

__global_new_init_a:
.L0:
	addi	sp, sp, -20
	sw	ra, 0(sp)
	li	t1, 20
	mv	a0, t1
	call	__malloc
	mv	t0, a0
	sw	t0, 0(sp)
	lw	t1, 0(sp)
	mv	t0, t1
	sw	t0, 4(sp)
	lw	t1, 4(sp)
	li	t0, 4
	sw	t0, 0(t1)
	li	t1, 1
	slli	t0, t1, 2
	sw	t0, 8(sp)
	lw	t1, 4(sp)
	lw	t0, 8(sp)
	add	t0, t1, t0
	sw	t0, 12(sp)
	lw	t1, 16(sp)
	lw	t0, 12(sp)
	sw	t0, 0(t1)
	lw	ra, 0(sp)
	addi	sp, sp, 20

main:
.L0:
	addi	sp, sp, -464
	sw	ra, 0(sp)
	call	_GLOBAL__sub_I_example.cpp
	li	t0, 0
	add	t0, sp, t0
	sw	t0, 8(sp)
	li	t1, 20
	mv	a0, t1
	call	__malloc
	mv	t0, a0
	sw	t0, 12(sp)
	lw	t1, 12(sp)
	mv	t0, t1
	sw	t0, 16(sp)
	lw	t1, 16(sp)
	li	t0, 4
	sw	t0, 0(t1)
	li	t1, 1
	slli	t0, t1, 2
	sw	t0, 20(sp)
	lw	t1, 16(sp)
	lw	t0, 20(sp)
	add	t0, t1, t0
	sw	t0, 24(sp)
	lw	t1, 24(sp)
	mv	t0, t1
	sw	t0, 28(sp)
	lw	t1, 8(sp)
	lw	t0, 28(sp)
	sw	t0, 0(t1)
	li	t0, 4
	add	t0, sp, t0
	sw	t0, 32(sp)
	lw	t1, 32(sp)
	li	t0, 1
	sw	t0, 0(t1)
	lw	t1, 8(sp)
	lw	t0, 0(t1)
	sw	t0, 36(sp)
	li	t1, 0
	slli	t0, t1, 2
	sw	t0, 40(sp)
	lw	t1, 36(sp)
	lw	t0, 40(sp)
	add	t0, t1, t0
	sw	t0, 44(sp)
	lw	t1, 24(sp)
	lw	t0, 0(t1)
	sw	t0, 48(sp)
	lw	t1, 44(sp)
	lw	t0, 48(sp)
	sw	t0, 0(t1)
	lw	t1, 8(sp)
	lw	t0, 0(t1)
	sw	t0, 52(sp)
	li	t1, 1
	slli	t0, t1, 2
	sw	t0, 56(sp)
	lw	t1, 52(sp)
	lw	t0, 56(sp)
	add	t0, t1, t0
	sw	t0, 60(sp)
	lw	t1, 24(sp)
	lw	t0, 0(t1)
	sw	t0, 64(sp)
	lw	t1, 60(sp)
	lw	t0, 64(sp)
	sw	t0, 0(t1)
	lw	t1, 8(sp)
	lw	t0, 0(t1)
	sw	t0, 68(sp)
	li	t1, 2
	slli	t0, t1, 2
	sw	t0, 72(sp)
	lw	t1, 68(sp)
	lw	t0, 72(sp)
	add	t0, t1, t0
	sw	t0, 76(sp)
	lw	t1, 24(sp)
	lw	t0, 0(t1)
	sw	t0, 80(sp)
	lw	t1, 76(sp)
	lw	t0, 80(sp)
	sw	t0, 0(t1)
	lw	t1, 8(sp)
	lw	t0, 0(t1)
	sw	t0, 84(sp)
	li	t1, 3
	slli	t0, t1, 2
	sw	t0, 88(sp)
	lw	t1, 84(sp)
	lw	t0, 88(sp)
	add	t0, t1, t0
	sw	t0, 92(sp)
	lw	t1, 24(sp)
	lw	t0, 0(t1)
	sw	t0, 96(sp)
	lw	t1, 92(sp)
	lw	t0, 96(sp)
	sw	t0, 0(t1)
	lw	t1, 8(sp)
	lw	t0, 0(t1)
	sw	t0, 100(sp)
	li	t1, 0
	slli	t0, t1, 2
	sw	t0, 104(sp)
	lw	t1, 100(sp)
	lw	t0, 104(sp)
	add	t0, t1, t0
	sw	t0, 108(sp)
	lw	t1, 108(sp)
	lw	t0, 0(t1)
	sw	t0, 112(sp)
	li	t1, -1
	slli	t0, t1, 2
	sw	t0, 116(sp)
	lw	t1, 112(sp)
	lw	t0, 116(sp)
	add	t0, t1, t0
	sw	t0, 120(sp)
	lw	t1, 120(sp)
	lw	t0, 0(t1)
	sw	t0, 124(sp)
	lw	t1, 124(sp)
	mv	a0, t1
	call	toString
	mv	t0, a0
	sw	t0, 128(sp)
	lw	t1, 128(sp)
	mv	a0, t1
	call	println
	lw	t1, 32(sp)
	li	t0, 0
	sw	t0, 0(t1)
	j	.L1
.L1:
	lw	t1, 8(sp)
	lw	t0, 0(t1)
	sw	t0, 132(sp)
	li	t1, 0
	slli	t0, t1, 2
	sw	t0, 136(sp)
	lw	t1, 132(sp)
	lw	t0, 136(sp)
	add	t0, t1, t0
	sw	t0, 140(sp)
	lw	t1, 140(sp)
	lw	t0, 0(t1)
	sw	t0, 144(sp)
	li	t1, -1
	slli	t0, t1, 2
	sw	t0, 148(sp)
	lw	t1, 144(sp)
	lw	t0, 148(sp)
	add	t0, t1, t0
	sw	t0, 152(sp)
	lw	t1, 32(sp)
	lw	t0, 0(t1)
	sw	t0, 156(sp)
	lw	t1, 152(sp)
	lw	t0, 0(t1)
	sw	t0, 160(sp)
	lw	t1, 156(sp)
	lw	t0, 160(sp)
	slt	t0, t1, t0
	sw	t0, 168(sp)
	lw	t1, 168(sp)
	beqz	t1, .L3
	j	.L2
.L2:
	lw	t1, 32(sp)
	lw	t0, 0(t1)
	sw	t0, 172(sp)
	lw	t1, 172(sp)
	li	t0, 1
	add nsw	t0, t1, t0
	sw	t0, 176(sp)
	lw	t1, 32(sp)
	lw	t0, 176(sp)
	sw	t0, 0(t1)
	lw	t1, 8(sp)
	lw	t0, 0(t1)
	sw	t0, 180(sp)
	li	t1, 0
	slli	t0, t1, 2
	sw	t0, 184(sp)
	lw	t1, 180(sp)
	lw	t0, 184(sp)
	add	t0, t1, t0
	sw	t0, 188(sp)
	lw	t1, 188(sp)
	lw	t0, 0(t1)
	sw	t0, 192(sp)
	lw	t1, 32(sp)
	lw	t0, 0(t1)
	sw	t0, 196(sp)
	lw	t1, 196(sp)
	slli	t0, t1, 2
	sw	t0, 200(sp)
	lw	t1, 192(sp)
	lw	t0, 200(sp)
	add	t0, t1, t0
	sw	t0, 204(sp)
	call	getInt
	mv	t0, a0
	sw	t0, 208(sp)
	lw	t1, 204(sp)
	lw	t0, 208(sp)
	sw	t0, 0(t1)
	j	.L1
.L3:
	lw	t1, 32(sp)
	li	t0, 0
	sw	t0, 0(t1)
	j	.L4
.L4:
	lw	t1, 8(sp)
	lw	t0, 0(t1)
	sw	t0, 212(sp)
	li	t1, 1
	slli	t0, t1, 2
	sw	t0, 216(sp)
	lw	t1, 212(sp)
	lw	t0, 216(sp)
	add	t0, t1, t0
	sw	t0, 220(sp)
	lw	t1, 220(sp)
	lw	t0, 0(t1)
	sw	t0, 224(sp)
	li	t1, -1
	slli	t0, t1, 2
	sw	t0, 228(sp)
	lw	t1, 224(sp)
	lw	t0, 228(sp)
	add	t0, t1, t0
	sw	t0, 232(sp)
	lw	t1, 32(sp)
	lw	t0, 0(t1)
	sw	t0, 236(sp)
	lw	t1, 232(sp)
	lw	t0, 0(t1)
	sw	t0, 240(sp)
	lw	t1, 236(sp)
	lw	t0, 240(sp)
	slt	t0, t1, t0
	sw	t0, 248(sp)
	lw	t1, 248(sp)
	beqz	t1, .L6
	j	.L5
.L5:
	lw	t1, 32(sp)
	lw	t0, 0(t1)
	sw	t0, 252(sp)
	lw	t1, 252(sp)
	li	t0, 1
	add nsw	t0, t1, t0
	sw	t0, 256(sp)
	lw	t1, 32(sp)
	lw	t0, 256(sp)
	sw	t0, 0(t1)
	lw	t1, 8(sp)
	lw	t0, 0(t1)
	sw	t0, 260(sp)
	li	t1, 1
	slli	t0, t1, 2
	sw	t0, 264(sp)
	lw	t1, 260(sp)
	lw	t0, 264(sp)
	add	t0, t1, t0
	sw	t0, 268(sp)
	lw	t1, 268(sp)
	lw	t0, 0(t1)
	sw	t0, 272(sp)
	lw	t1, 32(sp)
	lw	t0, 0(t1)
	sw	t0, 276(sp)
	lw	t1, 276(sp)
	slli	t0, t1, 2
	sw	t0, 280(sp)
	lw	t1, 272(sp)
	lw	t0, 280(sp)
	add	t0, t1, t0
	sw	t0, 284(sp)
	lw	t1, 284(sp)
	lw	t0, 0(t1)
	sw	t0, 288(sp)
	lw	t1, 288(sp)
	mv	a0, t1
	call	toString
	mv	t0, a0
	sw	t0, 292(sp)
	lw	t1, 292(sp)
	mv	a0, t1
	call	print
	j	.L4
.L6:
	li	t1, 0
	slli	t0, t1, 2
	sw	t0, 296(sp)
	li	t1, 0
	lw	t0, 296(sp)
	add	t0, t1, t0
	sw	t0, 300(sp)
	lw	t1, 300(sp)
	mv	a0, t1
	call	println
	lw	t1, 32(sp)
	li	t0, 0
	sw	t0, 0(t1)
	j	.L7
.L7:
	lw	t1, 8(sp)
	lw	t0, 0(t1)
	sw	t0, 304(sp)
	li	t1, 2
	slli	t0, t1, 2
	sw	t0, 308(sp)
	lw	t1, 304(sp)
	lw	t0, 308(sp)
	add	t0, t1, t0
	sw	t0, 312(sp)
	lw	t1, 312(sp)
	lw	t0, 0(t1)
	sw	t0, 316(sp)
	li	t1, -1
	slli	t0, t1, 2
	sw	t0, 320(sp)
	lw	t1, 316(sp)
	lw	t0, 320(sp)
	add	t0, t1, t0
	sw	t0, 324(sp)
	lw	t1, 32(sp)
	lw	t0, 0(t1)
	sw	t0, 328(sp)
	lw	t1, 324(sp)
	lw	t0, 0(t1)
	sw	t0, 332(sp)
	lw	t1, 328(sp)
	lw	t0, 332(sp)
	slt	t0, t1, t0
	sw	t0, 340(sp)
	lw	t1, 340(sp)
	beqz	t1, .L9
	j	.L8
.L8:
	lw	t1, 32(sp)
	lw	t0, 0(t1)
	sw	t0, 344(sp)
	lw	t1, 344(sp)
	li	t0, 1
	add nsw	t0, t1, t0
	sw	t0, 348(sp)
	lw	t1, 32(sp)
	lw	t0, 348(sp)
	sw	t0, 0(t1)
	lw	t1, 8(sp)
	lw	t0, 0(t1)
	sw	t0, 352(sp)
	li	t1, 2
	slli	t0, t1, 2
	sw	t0, 356(sp)
	lw	t1, 352(sp)
	lw	t0, 356(sp)
	add	t0, t1, t0
	sw	t0, 360(sp)
	lw	t1, 360(sp)
	lw	t0, 0(t1)
	sw	t0, 364(sp)
	lw	t1, 32(sp)
	lw	t0, 0(t1)
	sw	t0, 368(sp)
	lw	t1, 368(sp)
	slli	t0, t1, 2
	sw	t0, 372(sp)
	lw	t1, 364(sp)
	lw	t0, 372(sp)
	add	t0, t1, t0
	sw	t0, 376(sp)
	lw	t1, 376(sp)
	li	t0, 0
	sw	t0, 0(t1)
	j	.L7
.L9:
	lw	t1, 32(sp)
	li	t0, 0
	sw	t0, 0(t1)
	j	.L10
.L10:
	lw	t1, 8(sp)
	lw	t0, 0(t1)
	sw	t0, 380(sp)
	li	t1, 3
	slli	t0, t1, 2
	sw	t0, 384(sp)
	lw	t1, 380(sp)
	lw	t0, 384(sp)
	add	t0, t1, t0
	sw	t0, 388(sp)
	lw	t1, 388(sp)
	lw	t0, 0(t1)
	sw	t0, 392(sp)
	li	t1, -1
	slli	t0, t1, 2
	sw	t0, 396(sp)
	lw	t1, 392(sp)
	lw	t0, 396(sp)
	add	t0, t1, t0
	sw	t0, 400(sp)
	lw	t1, 32(sp)
	lw	t0, 0(t1)
	sw	t0, 404(sp)
	lw	t1, 400(sp)
	lw	t0, 0(t1)
	sw	t0, 408(sp)
	lw	t1, 404(sp)
	lw	t0, 408(sp)
	slt	t0, t1, t0
	sw	t0, 416(sp)
	lw	t1, 416(sp)
	beqz	t1, .L12
	j	.L11
.L11:
	lw	t1, 32(sp)
	lw	t0, 0(t1)
	sw	t0, 420(sp)
	lw	t1, 420(sp)
	li	t0, 1
	add nsw	t0, t1, t0
	sw	t0, 424(sp)
	lw	t1, 32(sp)
	lw	t0, 424(sp)
	sw	t0, 0(t1)
	lw	t1, 8(sp)
	lw	t0, 0(t1)
	sw	t0, 428(sp)
	li	t1, 3
	slli	t0, t1, 2
	sw	t0, 432(sp)
	lw	t1, 428(sp)
	lw	t0, 432(sp)
	add	t0, t1, t0
	sw	t0, 436(sp)
	lw	t1, 436(sp)
	lw	t0, 0(t1)
	sw	t0, 440(sp)
	lw	t1, 32(sp)
	lw	t0, 0(t1)
	sw	t0, 444(sp)
	lw	t1, 444(sp)
	slli	t0, t1, 2
	sw	t0, 448(sp)
	lw	t1, 440(sp)
	lw	t0, 448(sp)
	add	t0, t1, t0
	sw	t0, 452(sp)
	lw	t1, 452(sp)
	lw	t0, 0(t1)
	sw	t0, 456(sp)
	lw	t1, 456(sp)
	mv	a0, t1
	call	toString
	mv	t0, a0
	sw	t0, 460(sp)
	lw	t1, 460(sp)
	mv	a0, t1
	call	print
	j	.L10
.L12:
	li	t1, 0
	mv	a0, t1
	lw	ra, 0(sp)
	addi	sp, sp, 464

	.file	"builtin.c"
	.option nopic
	.attribute arch, "rv32i2p0_m2p0_a2p0"
	.attribute unaligned_access, 0
	.attribute stack_align, 16
	.text
	.section	.rodata
	.align	2
.LC0:
	.string	"%s"
	.text
	.align	2
	.globl	print
	.type	print, @function
print:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	addi	s0,sp,32
	sw	a0,-20(s0)
	lw	a1,-20(s0)
	lui	a5,%hi(.LC0)
	addi	a0,a5,%lo(.LC0)
	call	printf
	nop
	lw	ra,28(sp)
	lw	s0,24(sp)
	addi	sp,sp,32
	jr	ra
	.size	print, .-print
	.align	2
	.globl	println
	.type	println, @function
println:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	addi	s0,sp,32
	sw	a0,-20(s0)
	lw	a0,-20(s0)
	call	puts
	nop
	lw	ra,28(sp)
	lw	s0,24(sp)
	addi	sp,sp,32
	jr	ra
	.size	println, .-println
	.section	.rodata
	.align	2
.LC1:
	.string	"%d"
	.text
	.align	2
	.globl	printInt
	.type	printInt, @function
printInt:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	addi	s0,sp,32
	sw	a0,-20(s0)
	lw	a1,-20(s0)
	lui	a5,%hi(.LC1)
	addi	a0,a5,%lo(.LC1)
	call	printf
	nop
	lw	ra,28(sp)
	lw	s0,24(sp)
	addi	sp,sp,32
	jr	ra
	.size	printInt, .-printInt
	.section	.rodata
	.align	2
.LC2:
	.string	"%d\n"
	.text
	.align	2
	.globl	printlnInt
	.type	printlnInt, @function
printlnInt:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	addi	s0,sp,32
	sw	a0,-20(s0)
	lw	a1,-20(s0)
	lui	a5,%hi(.LC2)
	addi	a0,a5,%lo(.LC2)
	call	printf
	nop
	lw	ra,28(sp)
	lw	s0,24(sp)
	addi	sp,sp,32
	jr	ra
	.size	printlnInt, .-printlnInt
	.align	2
	.globl	getString
	.type	getString, @function
getString:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	addi	s0,sp,32
	li	a0,256
	call	malloc
	mv	a5,a0
	sw	a5,-20(s0)
	lw	a1,-20(s0)
	lui	a5,%hi(.LC0)
	addi	a0,a5,%lo(.LC0)
	call	scanf
	lw	a5,-20(s0)
	mv	a0,a5
	lw	ra,28(sp)
	lw	s0,24(sp)
	addi	sp,sp,32
	jr	ra
	.size	getString, .-getString
	.align	2
	.globl	getInt
	.type	getInt, @function
getInt:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	addi	s0,sp,32
	addi	a5,s0,-20
	mv	a1,a5
	lui	a5,%hi(.LC1)
	addi	a0,a5,%lo(.LC1)
	call	scanf
	lw	a5,-20(s0)
	mv	a0,a5
	lw	ra,28(sp)
	lw	s0,24(sp)
	addi	sp,sp,32
	jr	ra
	.size	getInt, .-getInt
	.align	2
	.globl	toString
	.type	toString, @function
toString:
	addi	sp,sp,-48
	sw	ra,44(sp)
	sw	s0,40(sp)
	addi	s0,sp,48
	sw	a0,-36(s0)
	li	a0,256
	call	malloc
	mv	a5,a0
	sw	a5,-20(s0)
	lw	a2,-36(s0)
	lui	a5,%hi(.LC1)
	addi	a1,a5,%lo(.LC1)
	lw	a0,-20(s0)
	call	sprintf
	lw	a5,-20(s0)
	mv	a0,a5
	lw	ra,44(sp)
	lw	s0,40(sp)
	addi	sp,sp,48
	jr	ra
	.size	toString, .-toString
	.align	2
	.globl	__mx_length
	.type	__mx_length, @function
__mx_length:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	addi	s0,sp,32
	sw	a0,-20(s0)
	lw	a0,-20(s0)
	call	strlen
	mv	a5,a0
	mv	a0,a5
	lw	ra,28(sp)
	lw	s0,24(sp)
	addi	sp,sp,32
	jr	ra
	.size	__mx_length, .-__mx_length
	.align	2
	.globl	__mx_substring
	.type	__mx_substring, @function
__mx_substring:
	addi	sp,sp,-48
	sw	ra,44(sp)
	sw	s0,40(sp)
	addi	s0,sp,48
	sw	a0,-36(s0)
	sw	a1,-40(s0)
	sw	a2,-44(s0)
	lw	a4,-44(s0)
	lw	a5,-40(s0)
	sub	a5,a4,a5
	addi	a5,a5,1
	sw	a5,-20(s0)
	lw	a5,-20(s0)
	mv	a0,a5
	call	malloc
	mv	a5,a0
	sw	a5,-24(s0)
	lw	a5,-40(s0)
	lw	a4,-36(s0)
	add	a5,a4,a5
	lw	a4,-20(s0)
	mv	a2,a4
	mv	a1,a5
	lw	a0,-24(s0)
	call	memcpy
	lw	a5,-20(s0)
	addi	a5,a5,-1
	lw	a4,-24(s0)
	add	a5,a4,a5
	sb	zero,0(a5)
	lw	a5,-24(s0)
	mv	a0,a5
	lw	ra,44(sp)
	lw	s0,40(sp)
	addi	sp,sp,48
	jr	ra
	.size	__mx_substring, .-__mx_substring
	.align	2
	.globl	__mx_parseInt
	.type	__mx_parseInt, @function
__mx_parseInt:
	addi	sp,sp,-48
	sw	ra,44(sp)
	sw	s0,40(sp)
	addi	s0,sp,48
	sw	a0,-36(s0)
	addi	a5,s0,-20
	mv	a2,a5
	lui	a5,%hi(.LC1)
	addi	a1,a5,%lo(.LC1)
	lw	a0,-36(s0)
	call	sscanf
	lw	a5,-20(s0)
	mv	a0,a5
	lw	ra,44(sp)
	lw	s0,40(sp)
	addi	sp,sp,48
	jr	ra
	.size	__mx_parseInt, .-__mx_parseInt
	.align	2
	.globl	__mx_ord
	.type	__mx_ord, @function
__mx_ord:
	addi	sp,sp,-32
	sw	s0,28(sp)
	addi	s0,sp,32
	sw	a0,-20(s0)
	sw	a1,-24(s0)
	lw	a5,-24(s0)
	lw	a4,-20(s0)
	add	a5,a4,a5
	lbu	a5,0(a5)
	mv	a0,a5
	lw	s0,28(sp)
	addi	sp,sp,32
	jr	ra
	.size	__mx_ord, .-__mx_ord
	.align	2
	.globl	__str_eq
	.type	__str_eq, @function
__str_eq:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	addi	s0,sp,32
	sw	a0,-20(s0)
	sw	a1,-24(s0)
	lw	a1,-24(s0)
	lw	a0,-20(s0)
	call	strcmp
	mv	a5,a0
	seqz	a5,a5
	andi	a5,a5,0xff
	mv	a0,a5
	lw	ra,28(sp)
	lw	s0,24(sp)
	addi	sp,sp,32
	jr	ra
	.size	__str_eq, .-__str_eq
	.align	2
	.globl	__str_ne
	.type	__str_ne, @function
__str_ne:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	addi	s0,sp,32
	sw	a0,-20(s0)
	sw	a1,-24(s0)
	lw	a1,-24(s0)
	lw	a0,-20(s0)
	call	strcmp
	mv	a5,a0
	snez	a5,a5
	andi	a5,a5,0xff
	mv	a0,a5
	lw	ra,28(sp)
	lw	s0,24(sp)
	addi	sp,sp,32
	jr	ra
	.size	__str_ne, .-__str_ne
	.align	2
	.globl	__str_slt
	.type	__str_slt, @function
__str_slt:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	addi	s0,sp,32
	sw	a0,-20(s0)
	sw	a1,-24(s0)
	lw	a1,-24(s0)
	lw	a0,-20(s0)
	call	strcmp
	mv	a5,a0
	srli	a5,a5,31
	andi	a5,a5,0xff
	mv	a0,a5
	lw	ra,28(sp)
	lw	s0,24(sp)
	addi	sp,sp,32
	jr	ra
	.size	__str_slt, .-__str_slt
	.align	2
	.globl	__str_sle
	.type	__str_sle, @function
__str_sle:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	addi	s0,sp,32
	sw	a0,-20(s0)
	sw	a1,-24(s0)
	lw	a1,-24(s0)
	lw	a0,-20(s0)
	call	strcmp
	mv	a5,a0
	slti	a5,a5,1
	andi	a5,a5,0xff
	mv	a0,a5
	lw	ra,28(sp)
	lw	s0,24(sp)
	addi	sp,sp,32
	jr	ra
	.size	__str_sle, .-__str_sle
	.align	2
	.globl	__str_sgt
	.type	__str_sgt, @function
__str_sgt:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	addi	s0,sp,32
	sw	a0,-20(s0)
	sw	a1,-24(s0)
	lw	a1,-24(s0)
	lw	a0,-20(s0)
	call	strcmp
	mv	a5,a0
	sgt	a5,a5,zero
	andi	a5,a5,0xff
	mv	a0,a5
	lw	ra,28(sp)
	lw	s0,24(sp)
	addi	sp,sp,32
	jr	ra
	.size	__str_sgt, .-__str_sgt
	.align	2
	.globl	__str_sge
	.type	__str_sge, @function
__str_sge:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	addi	s0,sp,32
	sw	a0,-20(s0)
	sw	a1,-24(s0)
	lw	a1,-24(s0)
	lw	a0,-20(s0)
	call	strcmp
	mv	a5,a0
	not	a5,a5
	srli	a5,a5,31
	andi	a5,a5,0xff
	mv	a0,a5
	lw	ra,28(sp)
	lw	s0,24(sp)
	addi	sp,sp,32
	jr	ra
	.size	__str_sge, .-__str_sge
	.align	2
	.globl	__str_add
	.type	__str_add, @function
__str_add:
	addi	sp,sp,-48
	sw	ra,44(sp)
	sw	s0,40(sp)
	sw	s1,36(sp)
	addi	s0,sp,48
	sw	a0,-36(s0)
	sw	a1,-40(s0)
	lw	a0,-36(s0)
	call	strlen
	mv	s1,a0
	lw	a0,-40(s0)
	call	strlen
	mv	a5,a0
	add	a5,s1,a5
	addi	a5,a5,1
	mv	a0,a5
	call	malloc
	mv	a5,a0
	sw	a5,-20(s0)
	lw	a1,-36(s0)
	lw	a0,-20(s0)
	call	strcpy
	lw	a1,-40(s0)
	lw	a0,-20(s0)
	call	strcat
	lw	a5,-20(s0)
	mv	a0,a5
	lw	ra,44(sp)
	lw	s0,40(sp)
	lw	s1,36(sp)
	addi	sp,sp,48
	jr	ra
	.size	__str_add, .-__str_add
	.align	2
	.globl	__malloc
	.type	__malloc, @function
__malloc:
	addi	sp,sp,-32
	sw	ra,28(sp)
	sw	s0,24(sp)
	addi	s0,sp,32
	sw	a0,-20(s0)
	lw	a5,-20(s0)
	mv	a0,a5
	call	malloc
	mv	a5,a0
	mv	a0,a5
	lw	ra,28(sp)
	lw	s0,24(sp)
	addi	sp,sp,32
	jr	ra
	.size	__malloc, .-__malloc
	.ident	"GCC: (GNU) 10.1.0"

