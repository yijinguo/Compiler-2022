	.text
	.section	.rodata
	.text
	.globl	main
	.type	main, @function
main:
	addi	sp, sp, -504
	sw	ra, 0(sp)
	addi	t0, sp, 4
	sw	t0, 36(sp)
	li	t1, 404
	mv	a0, t1
	call	__malloc
	mv	t0, a0
	sw	t0, 40(sp)
	lw	t1, 40(sp)
	mv	t0, t1
	sw	t0, 44(sp)
	li	t0, 100
	sw	t0, 44(sp)
	li	t1, 1
	slli	t0, t1, 2
	sw	t0, 48(sp)
	lw	t1, 44(sp)
	lw	t0, 48(sp)
	add	t0, t1, t0
	sw	t0, 52(sp)
	lw	t1, 52(sp)
	mv	t0, t1
	sw	t0, 56(sp)
	lw	t0, 56(sp)
	sw	t0, 36(sp)
	addi	t0, sp, 8
	sw	t0, 60(sp)
	addi	t0, sp, 12
	sw	t0, 64(sp)
	li	t0, 0
	sw	t0, 60(sp)
	j	.L1
.L1:
	lw	t0, 60(sp)
	sw	t0, 68(sp)
	lw	t1, 68(sp)
	li	t0, 100
	slt	t0, t1, t0
	sw	t0, 76(sp)
	lw	t1, 76(sp)
	beqz	t1, .L3
	j	.L2
.L2:
	lw	t0, 60(sp)
	sw	t0, 80(sp)
	lw	t1, 80(sp)
	addi	t0, t1, 1
	sw	t0, 84(sp)
	lw	t0, 84(sp)
	sw	t0, 60(sp)
	lw	t0, 36(sp)
	sw	t0, 88(sp)
	lw	t0, 60(sp)
	sw	t0, 92(sp)
	lw	t1, 92(sp)
	slli	t0, t1, 2
	sw	t0, 96(sp)
	lw	t1, 88(sp)
	lw	t0, 96(sp)
	add	t0, t1, t0
	sw	t0, 100(sp)
	li	t1, 404
	mv	a0, t1
	call	__malloc
	mv	t0, a0
	sw	t0, 104(sp)
	lw	t1, 104(sp)
	mv	t0, t1
	sw	t0, 108(sp)
	li	t0, 100
	sw	t0, 108(sp)
	li	t1, 1
	slli	t0, t1, 2
	sw	t0, 112(sp)
	lw	t1, 108(sp)
	lw	t0, 112(sp)
	add	t0, t1, t0
	sw	t0, 116(sp)
	lw	t0, 116(sp)
	sw	t0, 100(sp)
	j	.L1
.L3:
	addi	t0, sp, 16
	sw	t0, 120(sp)
	li	t0, 0
	sw	t0, 120(sp)
	li	t0, 0
	sw	t0, 60(sp)
	j	.L4
.L4:
	lw	t0, 60(sp)
	sw	t0, 124(sp)
	lw	t1, 124(sp)
	li	t0, 100
	slt	t0, t1, t0
	sw	t0, 132(sp)
	lw	t1, 132(sp)
	beqz	t1, .L6
	j	.L5
.L5:
	lw	t0, 60(sp)
	sw	t0, 136(sp)
	lw	t1, 136(sp)
	addi	t0, t1, 1
	sw	t0, 140(sp)
	lw	t0, 140(sp)
	sw	t0, 60(sp)
	li	t0, 0
	sw	t0, 64(sp)
	j	.L7
.L7:
	lw	t0, 64(sp)
	sw	t0, 144(sp)
	lw	t1, 144(sp)
	li	t0, 100
	slt	t0, t1, t0
	sw	t0, 152(sp)
	lw	t1, 152(sp)
	beqz	t1, .L9
	j	.L8
.L8:
	lw	t0, 64(sp)
	sw	t0, 156(sp)
	lw	t1, 156(sp)
	addi	t0, t1, 1
	sw	t0, 160(sp)
	lw	t0, 160(sp)
	sw	t0, 64(sp)
	lw	t0, 36(sp)
	sw	t0, 164(sp)
	lw	t0, 60(sp)
	sw	t0, 168(sp)
	lw	t1, 168(sp)
	slli	t0, t1, 2
	sw	t0, 172(sp)
	lw	t1, 164(sp)
	lw	t0, 172(sp)
	add	t0, t1, t0
	sw	t0, 176(sp)
	lw	t0, 176(sp)
	sw	t0, 180(sp)
	lw	t0, 64(sp)
	sw	t0, 184(sp)
	lw	t1, 184(sp)
	slli	t0, t1, 2
	sw	t0, 188(sp)
	lw	t1, 180(sp)
	lw	t0, 188(sp)
	add	t0, t1, t0
	sw	t0, 192(sp)
	li	t0, 0
	sw	t0, 192(sp)
	j	.L7
.L9:
	j	.L4
.L6:
	addi	t0, sp, 20
	sw	t0, 196(sp)
	addi	t0, sp, 24
	sw	t0, 200(sp)
	li	t0, 0
	sw	t0, 60(sp)
	j	.L10
.L10:
	lw	t0, 60(sp)
	sw	t0, 204(sp)
	lw	t1, 204(sp)
	li	t0, 100
	slt	t0, t1, t0
	sw	t0, 212(sp)
	lw	t1, 212(sp)
	beqz	t1, .L12
	j	.L11
.L11:
	lw	t0, 60(sp)
	sw	t0, 216(sp)
	lw	t1, 216(sp)
	addi	t0, t1, 1
	sw	t0, 220(sp)
	lw	t0, 220(sp)
	sw	t0, 60(sp)
	lw	t0, 60(sp)
	sw	t0, 224(sp)
	li	t1, 20
	lw	t0, 224(sp)
	slt	t0, t1, t0
	sw	t0, 232(sp)
	lw	t0, 60(sp)
	sw	t0, 236(sp)
	lw	t1, 236(sp)
	li	t0, 80
	slt	t0, t1, t0
	sw	t0, 244(sp)
	addi	t0, sp, 28
	sw	t0, 248(sp)
	lw	t1, 232(sp)
	li	t0, 0
	sub	t0, t1, t0
	sw	t0, 252(sp)
	lw	t1, 252(sp)
	snez	t0, t1
	sw	t0, 256(sp)
	lw	t1, 256(sp)
	beqz	t1, .L15
	j	.L13
.L13:
	lw	t1, 244(sp)
	li	t0, 0
	sub	t0, t1, t0
	sw	t0, 260(sp)
	lw	t1, 260(sp)
	snez	t0, t1
	sw	t0, 264(sp)
	lw	t1, 264(sp)
	beqz	t1, .L15
	j	.L14
.L14:
	li	t0, 1
	sw	t0, 248(sp)
	j	.L16
.L15:
	li	t0, 0
	sw	t0, 248(sp)
	j	.L16
.L16:
	lw	t0, 248(sp)
	sw	t0, 268(sp)
	lw	t1, 268(sp)
	beqz	t1, .L18
	j	.L17
.L17:
	li	t0, 0
	sw	t0, 64(sp)
	j	.L20
.L20:
	lw	t0, 64(sp)
	sw	t0, 272(sp)
	lw	t1, 272(sp)
	li	t0, 100
	slt	t0, t1, t0
	sw	t0, 280(sp)
	lw	t1, 280(sp)
	beqz	t1, .L22
	j	.L21
.L21:
	lw	t0, 64(sp)
	sw	t0, 284(sp)
	lw	t1, 284(sp)
	addi	t0, t1, 1
	sw	t0, 288(sp)
	lw	t0, 288(sp)
	sw	t0, 64(sp)
	lw	t0, 64(sp)
	sw	t0, 292(sp)
	li	t1, 5
	lw	t0, 292(sp)
	slt	t0, t1, t0
	sw	t0, 300(sp)
	lw	t0, 60(sp)
	sw	t0, 304(sp)
	lw	t1, 304(sp)
	li	t0, 90
	slt	t0, t1, t0
	sw	t0, 312(sp)
	addi	t0, sp, 32
	sw	t0, 316(sp)
	lw	t1, 300(sp)
	li	t0, 0
	sub	t0, t1, t0
	sw	t0, 320(sp)
	lw	t1, 320(sp)
	snez	t0, t1
	sw	t0, 324(sp)
	lw	t1, 324(sp)
	beqz	t1, .L23
	j	.L24
.L23:
	lw	t1, 312(sp)
	li	t0, 0
	sub	t0, t1, t0
	sw	t0, 328(sp)
	lw	t1, 328(sp)
	snez	t0, t1
	sw	t0, 332(sp)
	lw	t1, 332(sp)
	beqz	t1, .L25
	j	.L24
.L24:
	li	t0, 1
	sw	t0, 316(sp)
	j	.L26
.L25:
	li	t0, 0
	sw	t0, 316(sp)
	j	.L26
.L26:
	lw	t0, 316(sp)
	sw	t0, 336(sp)
	lw	t1, 336(sp)
	beqz	t1, .L28
	j	.L27
.L27:
	lw	t0, 64(sp)
	sw	t0, 340(sp)
	lw	t1, 340(sp)
	li	t0, 4
	mul	t0, t1, t0
	sw	t0, 344(sp)
	lw	t1, 344(sp)
	li	t0, 100
	div	t0, t1, t0
	sw	t0, 348(sp)
	lw	t0, 348(sp)
	sw	t0, 196(sp)
	lw	t0, 64(sp)
	sw	t0, 352(sp)
	lw	t1, 352(sp)
	li	t0, 4
	mul	t0, t1, t0
	sw	t0, 356(sp)
	lw	t1, 356(sp)
	li	t0, 100
	rem	t0, t1, t0
	sw	t0, 360(sp)
	lw	t0, 360(sp)
	sw	t0, 200(sp)
	lw	t0, 36(sp)
	sw	t0, 364(sp)
	lw	t0, 60(sp)
	sw	t0, 368(sp)
	lw	t0, 196(sp)
	sw	t0, 372(sp)
	lw	t1, 368(sp)
	lw	t0, 372(sp)
	add	t0, t1, t0
	sw	t0, 376(sp)
	lw	t1, 376(sp)
	slli	t0, t1, 2
	sw	t0, 380(sp)
	lw	t1, 364(sp)
	lw	t0, 380(sp)
	add	t0, t1, t0
	sw	t0, 384(sp)
	lw	t0, 384(sp)
	sw	t0, 388(sp)
	lw	t0, 200(sp)
	sw	t0, 392(sp)
	lw	t1, 392(sp)
	slli	t0, t1, 2
	sw	t0, 396(sp)
	lw	t1, 388(sp)
	lw	t0, 396(sp)
	add	t0, t1, t0
	sw	t0, 400(sp)
	lw	t0, 64(sp)
	sw	t0, 404(sp)
	lw	t1, 404(sp)
	addi	t0, t1, 50
	sw	t0, 408(sp)
	lw	t0, 408(sp)
	sw	t0, 400(sp)
	j	.L29
.L28:
	j	.L29
.L29:
	j	.L20
.L22:
	j	.L19
.L18:
	j	.L19
.L19:
	j	.L10
.L12:
	li	t0, 0
	sw	t0, 60(sp)
	j	.L30
.L30:
	lw	t0, 60(sp)
	sw	t0, 412(sp)
	lw	t1, 412(sp)
	li	t0, 100
	slt	t0, t1, t0
	sw	t0, 420(sp)
	lw	t1, 420(sp)
	beqz	t1, .L32
	j	.L31
.L31:
	lw	t0, 60(sp)
	sw	t0, 424(sp)
	lw	t1, 424(sp)
	addi	t0, t1, 1
	sw	t0, 428(sp)
	lw	t0, 428(sp)
	sw	t0, 60(sp)
	li	t0, 0
	sw	t0, 64(sp)
	j	.L33
.L33:
	lw	t0, 64(sp)
	sw	t0, 432(sp)
	lw	t1, 432(sp)
	li	t0, 100
	slt	t0, t1, t0
	sw	t0, 440(sp)
	lw	t1, 440(sp)
	beqz	t1, .L35
	j	.L34
.L34:
	lw	t0, 64(sp)
	sw	t0, 444(sp)
	lw	t1, 444(sp)
	addi	t0, t1, 1
	sw	t0, 448(sp)
	lw	t0, 448(sp)
	sw	t0, 64(sp)
	lw	t0, 36(sp)
	sw	t0, 452(sp)
	lw	t0, 60(sp)
	sw	t0, 456(sp)
	lw	t1, 456(sp)
	slli	t0, t1, 2
	sw	t0, 460(sp)
	lw	t1, 452(sp)
	lw	t0, 460(sp)
	add	t0, t1, t0
	sw	t0, 464(sp)
	lw	t0, 464(sp)
	sw	t0, 468(sp)
	lw	t0, 64(sp)
	sw	t0, 472(sp)
	lw	t1, 472(sp)
	slli	t0, t1, 2
	sw	t0, 476(sp)
	lw	t1, 468(sp)
	lw	t0, 476(sp)
	add	t0, t1, t0
	sw	t0, 480(sp)
	lw	t0, 120(sp)
	sw	t0, 484(sp)
	lw	t0, 480(sp)
	sw	t0, 488(sp)
	lw	t1, 484(sp)
	lw	t0, 488(sp)
	add	t0, t1, t0
	sw	t0, 492(sp)
	lw	t0, 492(sp)
	sw	t0, 120(sp)
	j	.L33
.L35:
	j	.L30
.L32:
	lw	t0, 120(sp)
	sw	t0, 496(sp)
	lw	t1, 496(sp)
	mv	a0, t1
	call	toString
	mv	t0, a0
	sw	t0, 500(sp)
	lw	t1, 500(sp)
	mv	a0, t1
	call	println
	li	t1, 0
	mv	a0, t1
	mv	ra, a0
	lw	ra, 0(sp)
	addi	sp, sp, 504
	ret
	.size	main, .-main

