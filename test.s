	.text
	.section	.rodata
e:
	.word	0
ans:
	.word	0
rk:
	.word	0
fa:
	.word	0
m:
	.word	0
n:
	.word	0
	.text
	.globl	qsort
	.type	qsort, @function
qsort:
	addi	sp, sp, -452
	sw	ra, 0(sp)
	addi	t0, sp, 4
	sw	t0, 36(sp)
	sw	a0, 36(sp)
	addi	t0, sp, 8
	sw	t0, 40(sp)
	sw	a1, 40(sp)
	addi	t0, sp, 12
	sw	t0, 44(sp)
	sw	a2, 44(sp)
	lw	t0, 40(sp)
	sw	t0, 48(sp)
	lw	t0, 44(sp)
	sw	t0, 52(sp)
	lw	t1, 48(sp)
	lw	t0, 52(sp)
	slt	t0, t1, t0
	sw	t0, 60(sp)
	lw	t1, 60(sp)
	beqz	t1, .L2.qsort
	j	.L1.qsort
.L1.qsort:
	addi	t0, sp, 16
	sw	t0, 64(sp)
	lw	t0, 40(sp)
	sw	t0, 68(sp)
	lw	t0, 68(sp)
	sw	t0, 64(sp)
	addi	t0, sp, 20
	sw	t0, 72(sp)
	lw	t0, 44(sp)
	sw	t0, 76(sp)
	lw	t0, 76(sp)
	sw	t0, 72(sp)
	addi	t0, sp, 24
	sw	t0, 80(sp)
	lw	t0, 36(sp)
	sw	t0, 84(sp)
	lw	t0, 40(sp)
	sw	t0, 88(sp)
	lw	t1, 84(sp)
	lw	t0, 88(sp)
	add	t0, t1, t0
	sw	t0, 92(sp)
	lw	t0, 92(sp)
	sw	t0, 96(sp)
	lw	t0, 96(sp)
	sw	t0, 80(sp)
	j	.L4.qsort
.L4.qsort:
	lw	t0, 64(sp)
	sw	t0, 100(sp)
	lw	t0, 72(sp)
	sw	t0, 104(sp)
	lw	t1, 100(sp)
	lw	t0, 104(sp)
	slt	t0, t1, t0
	sw	t0, 112(sp)
	lw	t1, 112(sp)
	beqz	t1, .L6.qsort
	j	.L5.qsort
.L5.qsort:
	j	.L7.qsort
.L7.qsort:
	lw	t0, 64(sp)
	sw	t0, 116(sp)
	lw	t0, 72(sp)
	sw	t0, 120(sp)
	lw	t1, 116(sp)
	lw	t0, 120(sp)
	slt	t0, t1, t0
	sw	t0, 128(sp)
	lw	t0, 36(sp)
	sw	t0, 132(sp)
	lw	t0, 72(sp)
	sw	t0, 136(sp)
	lw	t1, 132(sp)
	lw	t0, 136(sp)
	add	t0, t1, t0
	sw	t0, 140(sp)
	li	t1, 2
	slli	t0, t1, 2
	sw	t0, 144(sp)
	lw	t1, 140(sp)
	lw	t0, 144(sp)
	add	t0, t1, t0
	sw	t0, 148(sp)
	li	t1, 2
	slli	t0, t1, 2
	sw	t0, 152(sp)
	lw	t1, 80(sp)
	lw	t0, 152(sp)
	add	t0, t1, t0
	sw	t0, 156(sp)
	lw	t0, 148(sp)
	sw	t0, 160(sp)
	lw	t0, 156(sp)
	sw	t0, 164(sp)
	lw	t1, 160(sp)
	lw	t0, 164(sp)
	slt	t0, t1, t0
	sw	t0, 168(sp)
	lw	t1, 168(sp)
	xori	t0, t1, 1
	sw	t0, 172(sp)
	addi	t0, sp, 28
	sw	t0, 176(sp)
	lw	t1, 128(sp)
	li	t0, 0
	sub	t0, t1, t0
	sw	t0, 180(sp)
	lw	t1, 180(sp)
	snez	t0, t1
	sw	t0, 184(sp)
	lw	t1, 184(sp)
	beqz	t1, .L12.qsort
	j	.L10.qsort
.L10.qsort:
	lw	t1, 172(sp)
	li	t0, 0
	sub	t0, t1, t0
	sw	t0, 188(sp)
	lw	t1, 188(sp)
	snez	t0, t1
	sw	t0, 192(sp)
	lw	t1, 192(sp)
	beqz	t1, .L12.qsort
	j	.L11.qsort
.L11.qsort:
	li	t0, 1
	sw	t0, 176(sp)
	j	.L13.qsort
.L12.qsort:
	li	t0, 0
	sw	t0, 176(sp)
	j	.L13.qsort
.L13.qsort:
	lw	t0, 176(sp)
	sw	t0, 196(sp)
	lw	t1, 196(sp)
	beqz	t1, .L9.qsort
	j	.L8.qsort
.L8.qsort:
	lw	t0, 72(sp)
	sw	t0, 200(sp)
	lw	t1, 200(sp)
	addi	t0, t1, -1
	sw	t0, 204(sp)
	lw	t0, 204(sp)
	sw	t0, 72(sp)
	j	.L7.qsort
.L9.qsort:
	lw	t0, 64(sp)
	sw	t0, 208(sp)
	lw	t0, 72(sp)
	sw	t0, 212(sp)
	lw	t1, 208(sp)
	lw	t0, 212(sp)
	slt	t0, t1, t0
	sw	t0, 220(sp)
	lw	t1, 220(sp)
	beqz	t1, .L15.qsort
	j	.L14.qsort
.L14.qsort:
	lw	t0, 36(sp)
	sw	t0, 224(sp)
	lw	t0, 64(sp)
	sw	t0, 228(sp)
	lw	t1, 224(sp)
	lw	t0, 228(sp)
	add	t0, t1, t0
	sw	t0, 232(sp)
	lw	t0, 36(sp)
	sw	t0, 236(sp)
	lw	t0, 72(sp)
	sw	t0, 240(sp)
	lw	t1, 236(sp)
	lw	t0, 240(sp)
	add	t0, t1, t0
	sw	t0, 244(sp)
	lw	t0, 244(sp)
	sw	t0, 248(sp)
	lw	t0, 248(sp)
	sw	t0, 232(sp)
	lw	t0, 64(sp)
	sw	t0, 252(sp)
	lw	t1, 252(sp)
	addi	t0, t1, 1
	sw	t0, 256(sp)
	lw	t0, 256(sp)
	sw	t0, 64(sp)
	j	.L16.qsort
.L15.qsort:
	j	.L16.qsort
.L16.qsort:
	j	.L17.qsort
.L17.qsort:
	lw	t0, 64(sp)
	sw	t0, 260(sp)
	lw	t0, 72(sp)
	sw	t0, 264(sp)
	lw	t1, 260(sp)
	lw	t0, 264(sp)
	slt	t0, t1, t0
	sw	t0, 272(sp)
	lw	t0, 36(sp)
	sw	t0, 276(sp)
	lw	t0, 64(sp)
	sw	t0, 280(sp)
	lw	t1, 276(sp)
	lw	t0, 280(sp)
	add	t0, t1, t0
	sw	t0, 284(sp)
	li	t1, 2
	slli	t0, t1, 2
	sw	t0, 288(sp)
	lw	t1, 284(sp)
	lw	t0, 288(sp)
	add	t0, t1, t0
	sw	t0, 292(sp)
	li	t1, 2
	slli	t0, t1, 2
	sw	t0, 296(sp)
	lw	t1, 80(sp)
	lw	t0, 296(sp)
	add	t0, t1, t0
	sw	t0, 300(sp)
	lw	t0, 292(sp)
	sw	t0, 304(sp)
	lw	t0, 300(sp)
	sw	t0, 308(sp)
	lw	t1, 304(sp)
	lw	t0, 308(sp)
	slt	t0, t1, t0
	sw	t0, 316(sp)
	addi	t0, sp, 32
	sw	t0, 320(sp)
	lw	t1, 272(sp)
	li	t0, 0
	sub	t0, t1, t0
	sw	t0, 324(sp)
	lw	t1, 324(sp)
	snez	t0, t1
	sw	t0, 328(sp)
	lw	t1, 328(sp)
	beqz	t1, .L22.qsort
	j	.L20.qsort
.L20.qsort:
	lw	t1, 316(sp)
	li	t0, 0
	sub	t0, t1, t0
	sw	t0, 332(sp)
	lw	t1, 332(sp)
	snez	t0, t1
	sw	t0, 336(sp)
	lw	t1, 336(sp)
	beqz	t1, .L22.qsort
	j	.L21.qsort
.L21.qsort:
	li	t0, 1
	sw	t0, 320(sp)
	j	.L23.qsort
.L22.qsort:
	li	t0, 0
	sw	t0, 320(sp)
	j	.L23.qsort
.L23.qsort:
	lw	t0, 320(sp)
	sw	t0, 340(sp)
	lw	t1, 340(sp)
	beqz	t1, .L19.qsort
	j	.L18.qsort
.L18.qsort:
	lw	t0, 64(sp)
	sw	t0, 344(sp)
	lw	t1, 344(sp)
	addi	t0, t1, 1
	sw	t0, 348(sp)
	lw	t0, 348(sp)
	sw	t0, 64(sp)
	j	.L17.qsort
.L19.qsort:
	lw	t0, 64(sp)
	sw	t0, 352(sp)
	lw	t0, 72(sp)
	sw	t0, 356(sp)
	lw	t1, 352(sp)
	lw	t0, 356(sp)
	slt	t0, t1, t0
	sw	t0, 364(sp)
	lw	t1, 364(sp)
	beqz	t1, .L25.qsort
	j	.L24.qsort
.L24.qsort:
	lw	t0, 36(sp)
	sw	t0, 368(sp)
	lw	t0, 72(sp)
	sw	t0, 372(sp)
	lw	t1, 368(sp)
	lw	t0, 372(sp)
	add	t0, t1, t0
	sw	t0, 376(sp)
	lw	t0, 36(sp)
	sw	t0, 380(sp)
	lw	t0, 64(sp)
	sw	t0, 384(sp)
	lw	t1, 380(sp)
	lw	t0, 384(sp)
	add	t0, t1, t0
	sw	t0, 388(sp)
	lw	t0, 388(sp)
	sw	t0, 392(sp)
	lw	t0, 392(sp)
	sw	t0, 376(sp)
	lw	t0, 72(sp)
	sw	t0, 396(sp)
	lw	t1, 396(sp)
	addi	t0, t1, -1
	sw	t0, 400(sp)
	lw	t0, 400(sp)
	sw	t0, 72(sp)
	j	.L26.qsort
.L25.qsort:
	j	.L26.qsort
.L26.qsort:
	j	.L4.qsort
.L6.qsort:
	lw	t0, 36(sp)
	sw	t0, 404(sp)
	lw	t0, 64(sp)
	sw	t0, 408(sp)
	lw	t1, 404(sp)
	lw	t0, 408(sp)
	add	t0, t1, t0
	sw	t0, 412(sp)
	lw	t0, 80(sp)
	sw	t0, 416(sp)
	lw	t0, 416(sp)
	sw	t0, 412(sp)
	lw	t0, 36(sp)
	sw	t0, 420(sp)
	lw	t0, 40(sp)
	sw	t0, 424(sp)
	lw	t0, 64(sp)
	sw	t0, 428(sp)
	lw	t1, 428(sp)
	li	t0, 1
	sub	t0, t1, t0
	sw	t0, 432(sp)
	lw	t1, 420(sp)
	mv	a0, t1
	lw	t1, 424(sp)
	mv	a1, t1
	lw	t1, 432(sp)
	mv	a2, t1
	call	qsort
	lw	t0, 36(sp)
	sw	t0, 436(sp)
	lw	t0, 64(sp)
	sw	t0, 440(sp)
	lw	t1, 440(sp)
	addi	t0, t1, 1
	sw	t0, 444(sp)
	lw	t0, 44(sp)
	sw	t0, 448(sp)
	lw	t1, 436(sp)
	mv	a0, t1
	lw	t1, 444(sp)
	mv	a1, t1
	lw	t1, 448(sp)
	mv	a2, t1
	call	qsort
	j	.L3.qsort
.L2.qsort:
	j	.L3.qsort
.L3.qsort:
	lw	ra, 0(sp)
	addi	sp, sp, 452
	ret
	.size	qsort, .-qsort

	.globl	init
	.type	init, @function
init:
	addi	sp, sp, -120
	sw	ra, 0(sp)
	lui	t1, %hi(n)
	addi	t1, t1, %lo(n)
	lw	t0, 0(t1)
	sw	t0, 8(sp)
	lw	t1, 8(sp)
	addi	t0, t1, 1
	sw	t0, 12(sp)
	lw	t1, 12(sp)
	li	t0, 4
	mul	t0, t1, t0
	sw	t0, 16(sp)
	lw	t1, 16(sp)
	addi	t0, t1, 32
	sw	t0, 20(sp)
	lw	t1, 20(sp)
	mv	a0, t1
	call	__malloc
	mv	t0, a0
	sw	t0, 24(sp)
	lw	t1, 24(sp)
	mv	t0, t1
	sw	t0, 28(sp)
	lw	t0, 12(sp)
	sw	t0, 28(sp)
	lw	t1, 28(sp)
	li	t0, 1
	add	t0, t1, t0
	sw	t0, 32(sp)
	lui	t1, %hi(fa)
	addi	t1, t1, %lo(fa)
	lw	t0, 32(sp)
	sw	t0, 0(t1)
	lui	t1, %hi(n)
	addi	t1, t1, %lo(n)
	lw	t0, 0(t1)
	sw	t0, 36(sp)
	lw	t1, 36(sp)
	addi	t0, t1, 1
	sw	t0, 40(sp)
	lw	t1, 40(sp)
	li	t0, 4
	mul	t0, t1, t0
	sw	t0, 44(sp)
	lw	t1, 44(sp)
	addi	t0, t1, 32
	sw	t0, 48(sp)
	lw	t1, 48(sp)
	mv	a0, t1
	call	__malloc
	mv	t0, a0
	sw	t0, 52(sp)
	lw	t1, 52(sp)
	mv	t0, t1
	sw	t0, 56(sp)
	lw	t0, 40(sp)
	sw	t0, 56(sp)
	lw	t1, 56(sp)
	li	t0, 1
	add	t0, t1, t0
	sw	t0, 60(sp)
	lui	t1, %hi(rk)
	addi	t1, t1, %lo(rk)
	lw	t0, 60(sp)
	sw	t0, 0(t1)
	addi	t0, sp, 4
	sw	t0, 64(sp)
	li	t0, 1
	sw	t0, 64(sp)
	j	.L1.init
.L1.init:
	lw	t0, 64(sp)
	sw	t0, 68(sp)
	lui	t1, %hi(n)
	addi	t1, t1, %lo(n)
	lw	t0, 0(t1)
	sw	t0, 72(sp)
	lw	t1, 72(sp)
	lw	t0, 68(sp)
	slt	t0, t1, t0
	sw	t0, 76(sp)
	lw	t1, 76(sp)
	xori	t0, t1, 1
	sw	t0, 80(sp)
	lw	t1, 80(sp)
	beqz	t1, .L3.init
	j	.L2.init
.L2.init:
	lui	t1, %hi(fa)
	addi	t1, t1, %lo(fa)
	lw	t0, 0(t1)
	sw	t0, 84(sp)
	lw	t0, 64(sp)
	sw	t0, 88(sp)
	lw	t1, 84(sp)
	lw	t0, 88(sp)
	add	t0, t1, t0
	sw	t0, 92(sp)
	lw	t0, 64(sp)
	sw	t0, 96(sp)
	lw	t0, 96(sp)
	sw	t0, 92(sp)
	lui	t1, %hi(rk)
	addi	t1, t1, %lo(rk)
	lw	t0, 0(t1)
	sw	t0, 100(sp)
	lw	t0, 64(sp)
	sw	t0, 104(sp)
	lw	t1, 100(sp)
	lw	t0, 104(sp)
	add	t0, t1, t0
	sw	t0, 108(sp)
	li	t0, 1
	sw	t0, 108(sp)
	lw	t0, 64(sp)
	sw	t0, 112(sp)
	lw	t1, 112(sp)
	addi	t0, t1, 1
	sw	t0, 116(sp)
	lw	t0, 116(sp)
	sw	t0, 64(sp)
	j	.L1.init
.L3.init:
	lw	ra, 0(sp)
	addi	sp, sp, 120
	ret
	.size	init, .-init

	.globl	Edge.build
	.type	Edge.build, @function
Edge.build:
	addi	sp, sp, -4
	sw	ra, 0(sp)
	lw	ra, 0(sp)
	addi	sp, sp, 4
	ret
	.size	Edge.build, .-Edge.build

	.globl	find
	.type	find, @function
find:
	addi	sp, sp, -80
	sw	ra, 0(sp)
	addi	t0, sp, 4
	sw	t0, 8(sp)
	sw	a0, 8(sp)
	lui	t1, %hi(fa)
	addi	t1, t1, %lo(fa)
	lw	t0, 0(t1)
	sw	t0, 12(sp)
	lw	t0, 8(sp)
	sw	t0, 16(sp)
	lw	t1, 12(sp)
	lw	t0, 16(sp)
	add	t0, t1, t0
	sw	t0, 20(sp)
	lw	t0, 8(sp)
	sw	t0, 24(sp)
	lw	t0, 20(sp)
	sw	t0, 28(sp)
	lw	t1, 24(sp)
	lw	t0, 28(sp)
	sub	t0, t1, t0
	sw	t0, 32(sp)
	lw	t1, 32(sp)
	seqz	t0, t1
	sw	t0, 36(sp)
	lw	t1, 36(sp)
	beqz	t1, .L2.find
	j	.L1.find
.L1.find:
	lw	t0, 8(sp)
	sw	t0, 40(sp)
	lw	t1, 40(sp)
	mv	a0, t1
	lw	ra, 0(sp)
.L2.find:
	j	.L3.find
.L3.find:
	lui	t1, %hi(fa)
	addi	t1, t1, %lo(fa)
	lw	t0, 0(t1)
	sw	t0, 44(sp)
	lw	t0, 8(sp)
	sw	t0, 48(sp)
	lw	t1, 44(sp)
	lw	t0, 48(sp)
	add	t0, t1, t0
	sw	t0, 52(sp)
	lw	t0, 52(sp)
	sw	t0, 56(sp)
	lw	t1, 56(sp)
	mv	a0, t1
	call	find
	mv	t0, a0
	sw	t0, 60(sp)
	lw	t0, 60(sp)
	sw	t0, 8(sp)
	lui	t1, %hi(fa)
	addi	t1, t1, %lo(fa)
	lw	t0, 0(t1)
	sw	t0, 64(sp)
	lw	t0, 8(sp)
	sw	t0, 68(sp)
	lw	t1, 64(sp)
	lw	t0, 68(sp)
	add	t0, t1, t0
	sw	t0, 72(sp)
	lw	t0, 72(sp)
	sw	t0, 76(sp)
	lw	t1, 76(sp)
	mv	a0, t1
	lw	ra, 0(sp)
	addi	sp, sp, 80
	ret
	.size	find, .-find

	.globl	main
	.type	main, @function
main:
	addi	sp, sp, -324
	sw	ra, 0(sp)
	call	getInt
	mv	t0, a0
	sw	t0, 20(sp)
	lui	t1, %hi(n)
	addi	t1, t1, %lo(n)
	lw	t0, 20(sp)
	sw	t0, 0(t1)
	call	getInt
	mv	t0, a0
	sw	t0, 24(sp)
	lui	t1, %hi(m)
	addi	t1, t1, %lo(m)
	lw	t0, 24(sp)
	sw	t0, 0(t1)
	lui	t1, %hi(m)
	addi	t1, t1, %lo(m)
	lw	t0, 0(t1)
	sw	t0, 28(sp)
	lw	t1, 28(sp)
	li	t0, 12
	mul	t0, t1, t0
	sw	t0, 32(sp)
	lw	t1, 32(sp)
	addi	t0, t1, 32
	sw	t0, 36(sp)
	lw	t1, 36(sp)
	mv	a0, t1
	call	__malloc
	mv	t0, a0
	sw	t0, 40(sp)
	lw	t1, 40(sp)
	mv	t0, t1
	sw	t0, 44(sp)
	lw	t0, 28(sp)
	sw	t0, 44(sp)
	lw	t1, 44(sp)
	li	t0, 1
	add	t0, t1, t0
	sw	t0, 48(sp)
	lw	t1, 48(sp)
	mv	t0, t1
	sw	t0, 52(sp)
	lui	t1, %hi(e)
	addi	t1, t1, %lo(e)
	lw	t0, 52(sp)
	sw	t0, 0(t1)
	addi	t0, sp, 4
	sw	t0, 56(sp)
	li	t0, 0
	sw	t0, 56(sp)
	j	.L1.main
.L1.main:
	lw	t0, 56(sp)
	sw	t0, 60(sp)
	lui	t1, %hi(m)
	addi	t1, t1, %lo(m)
	lw	t0, 0(t1)
	sw	t0, 64(sp)
	lw	t1, 60(sp)
	lw	t0, 64(sp)
	slt	t0, t1, t0
	sw	t0, 72(sp)
	lw	t1, 72(sp)
	beqz	t1, .L3.main
	j	.L2.main
.L2.main:
	addi	t0, sp, 8
	sw	t0, 76(sp)
	li	t1, 12
	mv	a0, t1
	call	__malloc
	mv	t0, a0
	sw	t0, 80(sp)
	lw	t1, 80(sp)
	mv	t0, t1
	sw	t0, 84(sp)
	lw	t1, 84(sp)
	mv	a0, t1
	call	Edge.build
	lw	t0, 84(sp)
	sw	t0, 88(sp)
	lw	t0, 88(sp)
	sw	t0, 76(sp)
	li	t1, 0
	slli	t0, t1, 2
	sw	t0, 92(sp)
	lw	t1, 76(sp)
	lw	t0, 92(sp)
	add	t0, t1, t0
	sw	t0, 96(sp)
	call	getInt
	mv	t0, a0
	sw	t0, 100(sp)
	lw	t0, 100(sp)
	sw	t0, 96(sp)
	li	t1, 1
	slli	t0, t1, 2
	sw	t0, 104(sp)
	lw	t1, 76(sp)
	lw	t0, 104(sp)
	add	t0, t1, t0
	sw	t0, 108(sp)
	call	getInt
	mv	t0, a0
	sw	t0, 112(sp)
	lw	t0, 112(sp)
	sw	t0, 108(sp)
	li	t1, 2
	slli	t0, t1, 2
	sw	t0, 116(sp)
	lw	t1, 76(sp)
	lw	t0, 116(sp)
	add	t0, t1, t0
	sw	t0, 120(sp)
	call	getInt
	mv	t0, a0
	sw	t0, 124(sp)
	lw	t0, 124(sp)
	sw	t0, 120(sp)
	lui	t1, %hi(e)
	addi	t1, t1, %lo(e)
	lw	t0, 0(t1)
	sw	t0, 128(sp)
	lw	t0, 56(sp)
	sw	t0, 132(sp)
	lw	t1, 128(sp)
	lw	t0, 132(sp)
	add	t0, t1, t0
	sw	t0, 136(sp)
	lw	t0, 76(sp)
	sw	t0, 140(sp)
	lw	t0, 140(sp)
	sw	t0, 136(sp)
	lw	t0, 56(sp)
	sw	t0, 144(sp)
	lw	t1, 144(sp)
	addi	t0, t1, 1
	sw	t0, 148(sp)
	lw	t0, 148(sp)
	sw	t0, 56(sp)
	j	.L1.main
.L3.main:
	lui	t1, %hi(e)
	addi	t1, t1, %lo(e)
	lw	t0, 0(t1)
	sw	t0, 152(sp)
	lui	t1, %hi(m)
	addi	t1, t1, %lo(m)
	lw	t0, 0(t1)
	sw	t0, 156(sp)
	lw	t1, 156(sp)
	li	t0, 1
	sub	t0, t1, t0
	sw	t0, 160(sp)
	lw	t1, 152(sp)
	mv	a0, t1
	li	t1, 0
	mv	a1, t1
	lw	t1, 160(sp)
	mv	a2, t1
	call	qsort
	call	init
	li	t0, 0
	sw	t0, 56(sp)
	addi	t0, sp, 12
	sw	t0, 164(sp)
	li	t0, 0
	sw	t0, 164(sp)
	j	.L4.main
.L4.main:
	lui	t1, %hi(n)
	addi	t1, t1, %lo(n)
	lw	t0, 0(t1)
	sw	t0, 168(sp)
	lw	t1, 168(sp)
	li	t0, 1
	sub	t0, t1, t0
	sw	t0, 172(sp)
	lw	t0, 56(sp)
	sw	t0, 176(sp)
	lw	t1, 176(sp)
	lw	t0, 172(sp)
	slt	t0, t1, t0
	sw	t0, 184(sp)
	lw	t1, 184(sp)
	beqz	t1, .L6.main
	j	.L5.main
.L5.main:
	lw	t0, 164(sp)
	sw	t0, 188(sp)
	lui	t1, %hi(m)
	addi	t1, t1, %lo(m)
	lw	t0, 0(t1)
	sw	t0, 192(sp)
	lw	t1, 188(sp)
	lw	t0, 192(sp)
	slt	t0, t1, t0
	sw	t0, 196(sp)
	lw	t1, 196(sp)
	xori	t0, t1, 1
	sw	t0, 200(sp)
	lw	t1, 200(sp)
	beqz	t1, .L8.main
	j	.L7.main
.L7.main:
	li	t1, -1
	mv	a0, t1
	call	printInt
	li	t1, 0
	mv	a0, t1
	mv	ra, a0
	lw	ra, 0(sp)
.L8.main:
	j	.L9.main
.L9.main:
	addi	t0, sp, 16
	sw	t0, 204(sp)
	lui	t1, %hi(e)
	addi	t1, t1, %lo(e)
	lw	t0, 0(t1)
	sw	t0, 208(sp)
	lw	t0, 164(sp)
	sw	t0, 212(sp)
	lw	t1, 208(sp)
	lw	t0, 212(sp)
	add	t0, t1, t0
	sw	t0, 216(sp)
	lw	t0, 216(sp)
	sw	t0, 220(sp)
	lw	t0, 220(sp)
	sw	t0, 204(sp)
	lw	t0, 164(sp)
	sw	t0, 224(sp)
	lw	t1, 224(sp)
	addi	t0, t1, 1
	sw	t0, 228(sp)
	lw	t0, 228(sp)
	sw	t0, 164(sp)
	li	t1, 0
	slli	t0, t1, 2
	sw	t0, 232(sp)
	lw	t1, 204(sp)
	lw	t0, 232(sp)
	add	t0, t1, t0
	sw	t0, 236(sp)
	lw	t0, 236(sp)
	sw	t0, 240(sp)
	li	t1, 1
	slli	t0, t1, 2
	sw	t0, 244(sp)
	lw	t1, 204(sp)
	lw	t0, 244(sp)
	add	t0, t1, t0
	sw	t0, 248(sp)
	lw	t0, 248(sp)
	sw	t0, 252(sp)
	lw	t1, 240(sp)
	mv	a0, t1
	lw	t1, 252(sp)
	mv	a1, t1
	call	union
	mv	t0, a0
	sb	t0, 256(sp)
	lb	t1, 256(sp)
	mv	t0, t1
	sw	t0, 260(sp)
	lw	t1, 260(sp)
	beqz	t1, .L12.main
	j	.L10.main
.L10.main:
	lw	t0, 56(sp)
	sw	t0, 264(sp)
	lw	t1, 264(sp)
	addi	t0, t1, 1
	sw	t0, 268(sp)
	lw	t0, 268(sp)
	sw	t0, 56(sp)
	li	t1, 2
	slli	t0, t1, 2
	sw	t0, 272(sp)
	lw	t1, 204(sp)
	lw	t0, 272(sp)
	add	t0, t1, t0
	sw	t0, 276(sp)
	lui	t1, %hi(ans)
	addi	t1, t1, %lo(ans)
	lw	t0, 0(t1)
	sw	t0, 280(sp)
	lw	t0, 276(sp)
	sw	t0, 284(sp)
	lw	t1, 280(sp)
	lw	t0, 284(sp)
	add	t0, t1, t0
	sw	t0, 288(sp)
	lui	t1, %hi(ans)
	addi	t1, t1, %lo(ans)
	lw	t0, 288(sp)
	sw	t0, 0(t1)
	j	.L12.main
.L11.main:
	j	.L12.main
.L12.main:
	j	.L4.main
.L6.main:
	lui	t1, %hi(rk)
	addi	t1, t1, %lo(rk)
	lw	t0, 0(t1)
	sw	t0, 292(sp)
	li	t1, 1
	mv	a0, t1
	call	find
	mv	t0, a0
	sw	t0, 296(sp)
	lw	t1, 292(sp)
	lw	t0, 296(sp)
	add	t0, t1, t0
	sw	t0, 300(sp)
	lw	t0, 300(sp)
	sw	t0, 304(sp)
	lui	t1, %hi(n)
	addi	t1, t1, %lo(n)
	lw	t0, 0(t1)
	sw	t0, 308(sp)
	lw	t1, 304(sp)
	lw	t0, 308(sp)
	sub	t0, t1, t0
	sw	t0, 312(sp)
	lw	t1, 312(sp)
	seqz	t0, t1
	sw	t0, 316(sp)
	lw	t1, 316(sp)
	beqz	t1, .L14.main
	j	.L13.main
.L13.main:
	lui	t1, %hi(ans)
	addi	t1, t1, %lo(ans)
	lw	t0, 0(t1)
	sw	t0, 320(sp)
	lw	t1, 320(sp)
	mv	a0, t1
	call	printInt
	j	.L15.main
.L14.main:
	li	t1, -1
	mv	a0, t1
	call	printInt
	j	.L15.main
.L15.main:
	li	t1, 0
	mv	a0, t1
	mv	ra, a0
	lw	ra, 0(sp)
	addi	sp, sp, 324
	ret
	.size	main, .-main

	.globl	union
	.type	union, @function
union:
	addi	sp, sp, -220
	sw	ra, 0(sp)
	addi	t0, sp, 4
	sw	t0, 12(sp)
	sw	a0, 12(sp)
	addi	t0, sp, 8
	sw	t0, 16(sp)
	sw	a1, 16(sp)
	lw	t0, 12(sp)
	sw	t0, 20(sp)
	lw	t1, 20(sp)
	mv	a0, t1
	call	find
	mv	t0, a0
	sw	t0, 24(sp)
	lw	t0, 24(sp)
	sw	t0, 12(sp)
	lw	t0, 16(sp)
	sw	t0, 28(sp)
	lw	t1, 28(sp)
	mv	a0, t1
	call	find
	mv	t0, a0
	sw	t0, 32(sp)
	lw	t0, 32(sp)
	sw	t0, 16(sp)
	lw	t0, 12(sp)
	sw	t0, 36(sp)
	lw	t0, 16(sp)
	sw	t0, 40(sp)
	lw	t1, 36(sp)
	lw	t0, 40(sp)
	sub	t0, t1, t0
	sw	t0, 44(sp)
	lw	t1, 44(sp)
	seqz	t0, t1
	sw	t0, 48(sp)
	lw	t1, 48(sp)
	beqz	t1, .L2.union
	j	.L1.union
.L1.union:
	li	t1, 0
	mv	a0, t1
	lw	ra, 0(sp)
.L2.union:
	j	.L3.union
.L3.union:
	lui	t1, %hi(rk)
	addi	t1, t1, %lo(rk)
	lw	t0, 0(t1)
	sw	t0, 52(sp)
	lw	t0, 12(sp)
	sw	t0, 56(sp)
	lw	t1, 52(sp)
	lw	t0, 56(sp)
	add	t0, t1, t0
	sw	t0, 60(sp)
	lui	t1, %hi(rk)
	addi	t1, t1, %lo(rk)
	lw	t0, 0(t1)
	sw	t0, 64(sp)
	lw	t0, 16(sp)
	sw	t0, 68(sp)
	lw	t1, 64(sp)
	lw	t0, 68(sp)
	add	t0, t1, t0
	sw	t0, 72(sp)
	lw	t0, 60(sp)
	sw	t0, 76(sp)
	lw	t0, 72(sp)
	sw	t0, 80(sp)
	lw	t1, 80(sp)
	lw	t0, 76(sp)
	slt	t0, t1, t0
	sw	t0, 88(sp)
	lw	t1, 88(sp)
	beqz	t1, .L5.union
	j	.L4.union
.L4.union:
	lui	t1, %hi(fa)
	addi	t1, t1, %lo(fa)
	lw	t0, 0(t1)
	sw	t0, 92(sp)
	lw	t0, 16(sp)
	sw	t0, 96(sp)
	lw	t1, 92(sp)
	lw	t0, 96(sp)
	add	t0, t1, t0
	sw	t0, 100(sp)
	lw	t0, 12(sp)
	sw	t0, 104(sp)
	lw	t0, 104(sp)
	sw	t0, 100(sp)
	lui	t1, %hi(rk)
	addi	t1, t1, %lo(rk)
	lw	t0, 0(t1)
	sw	t0, 108(sp)
	lw	t0, 12(sp)
	sw	t0, 112(sp)
	lw	t1, 108(sp)
	lw	t0, 112(sp)
	add	t0, t1, t0
	sw	t0, 116(sp)
	lui	t1, %hi(rk)
	addi	t1, t1, %lo(rk)
	lw	t0, 0(t1)
	sw	t0, 120(sp)
	lw	t0, 12(sp)
	sw	t0, 124(sp)
	lw	t1, 120(sp)
	lw	t0, 124(sp)
	add	t0, t1, t0
	sw	t0, 128(sp)
	lui	t1, %hi(rk)
	addi	t1, t1, %lo(rk)
	lw	t0, 0(t1)
	sw	t0, 132(sp)
	lw	t0, 16(sp)
	sw	t0, 136(sp)
	lw	t1, 132(sp)
	lw	t0, 136(sp)
	add	t0, t1, t0
	sw	t0, 140(sp)
	lw	t0, 128(sp)
	sw	t0, 144(sp)
	lw	t0, 140(sp)
	sw	t0, 148(sp)
	lw	t1, 144(sp)
	lw	t0, 148(sp)
	add	t0, t1, t0
	sw	t0, 152(sp)
	lw	t0, 152(sp)
	sw	t0, 116(sp)
	j	.L6.union
.L5.union:
	lui	t1, %hi(fa)
	addi	t1, t1, %lo(fa)
	lw	t0, 0(t1)
	sw	t0, 156(sp)
	lw	t0, 12(sp)
	sw	t0, 160(sp)
	lw	t1, 156(sp)
	lw	t0, 160(sp)
	add	t0, t1, t0
	sw	t0, 164(sp)
	lw	t0, 16(sp)
	sw	t0, 168(sp)
	lw	t0, 168(sp)
	sw	t0, 164(sp)
	lui	t1, %hi(rk)
	addi	t1, t1, %lo(rk)
	lw	t0, 0(t1)
	sw	t0, 172(sp)
	lw	t0, 16(sp)
	sw	t0, 176(sp)
	lw	t1, 172(sp)
	lw	t0, 176(sp)
	add	t0, t1, t0
	sw	t0, 180(sp)
	lui	t1, %hi(rk)
	addi	t1, t1, %lo(rk)
	lw	t0, 0(t1)
	sw	t0, 184(sp)
	lw	t0, 16(sp)
	sw	t0, 188(sp)
	lw	t1, 184(sp)
	lw	t0, 188(sp)
	add	t0, t1, t0
	sw	t0, 192(sp)
	lui	t1, %hi(rk)
	addi	t1, t1, %lo(rk)
	lw	t0, 0(t1)
	sw	t0, 196(sp)
	lw	t0, 12(sp)
	sw	t0, 200(sp)
	lw	t1, 196(sp)
	lw	t0, 200(sp)
	add	t0, t1, t0
	sw	t0, 204(sp)
	lw	t0, 192(sp)
	sw	t0, 208(sp)
	lw	t0, 204(sp)
	sw	t0, 212(sp)
	lw	t1, 208(sp)
	lw	t0, 212(sp)
	add	t0, t1, t0
	sw	t0, 216(sp)
	lw	t0, 216(sp)
	sw	t0, 180(sp)
	j	.L6.union
.L6.union:
	li	t1, 1
	mv	a0, t1
	lw	ra, 0(sp)
	addi	sp, sp, 220
	ret
	.size	union, .-union

