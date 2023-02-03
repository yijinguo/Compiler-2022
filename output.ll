@str_7 = private unnamed_addr constant [8 x i8] c"x * y: \00"
@str_6 = private unnamed_addr constant [8 x i8] c"x + y: \00"
@str_0 = private unnamed_addr constant [3 x i8] c"( \00"
@str_2 = private unnamed_addr constant [3 x i8] c" )\00"
@str_1 = private unnamed_addr constant [3 x i8] c", \00"
@str_4 = private unnamed_addr constant [9 x i8] c"excited!\00"
@str_5 = private unnamed_addr constant [11 x i8] c"vector y: \00"
@str_3 = private unnamed_addr constant [11 x i8] c"vector x: \00"
@str_8 = private unnamed_addr constant [15 x i8] c"(1 << 3) * y: \00"

%class.vector = type { i32* }

define i32 @vector.getDim(%class.vector* %0) {
	%2 = alloca %class.vector*
	store %class.vector* %0, %class.vector** %2
	%3 = load %class.vector*, %class.vector** %2
	%4 = getelementptr %class.vector, %class.vector* %3, i32 0, i32 0
	%5 = icmp eq i32** %4, null
	br i1 %5, label %_if_then_1, label %_if_else_2

_if_then_1:
	ret i32 0

_if_else_2:
	br label %_if_next_3

_if_next_3:
	%6 = getelementptr %class.vector, %class.vector* %3, i32 0, i32 0
	%7 = load i32*, i32** %6
	%8 = getelementptr i32, i32* %7, i32 -1
	%9 = load i32, i32* %8
	ret i32 %9

}

define %class.vector @vector.add(%class.vector* %0, %class.vector %1) {
	%3 = alloca %class.vector
	store %class.vector %1, %class.vector* %3
	%4 = alloca %class.vector*
	store %class.vector* %0, %class.vector** %4
	%5 = load %class.vector*, %class.vector** %4
	%6 = call i32 @vector.getDim(%class.vector* %5)
	%7 = call i32 @vector.getDim(%class.vector* %3)
	%8 = icmp ne i32 %6, %7
	%9 = call i32 @vector.getDim(%class.vector* %5)
	%10 = icmp eq i32 %9, 0
	%11 = zext i1 %8 to i8
	%12 = zext i1 %10 to i8
	%13 = icmp ne i8 %11, 0
	br i1 %13, label %_lor_next_2, label %_lor_rhs_1

_lor_rhs_1:
	%14 = icmp ne i8 %12, 0
	br label %_lor_next_2

_lor_next_2:
	%15 = icmp ne i1 %13, %14
	br i1 %15, label %_if_then_3, label %_if_else_4

_if_then_3:
	%16 = load %class.vector, %class.vector* null
	ret %class.vector %16

_if_else_4:
	br label %_if_next_5

_if_next_5:
	%17 = alloca %class.vector
	%18 = call i8* @__malloc(i32 4)
	%19 = bitcast i8* %18 to %class.vector*
	call void @vector.build(%class.vector* %19)
	%20 = load %class.vector, %class.vector* %19
	store %class.vector %20, %class.vector* %17
	%21 = alloca i32
	%22 = getelementptr %class.vector, %class.vector* %17, i32 0, i32 0
	%23 = call i32 @vector.getDim(%class.vector* %5)
	%24 = mul nsw i32 %23, 4
	%25 = add nsw i32 %24, 32
	%26 = call i8* @__malloc(i32 %25)
	%27 = bitcast i8* %26 to i32*
	store i32 %23, i32* %27
	%28 = getelementptr i32, i32* %27, i32 1
	store i32* %28, i32** %22
	store i32 0, i32* %21
	br label %_for_cond_6

_for_cond_6:
	%29 = call i32 @vector.getDim(%class.vector* %5)
	%30 = load i32, i32* %21
	%31 = icmp slt i32 %30, %29
	br i1 %31, label %_for_loop_7, label %_for_next_8

_for_loop_7:
	%32 = load i32, i32* %21
	%33 = add nsw i32 %32, 1
	store i32 %33, i32* %21
	%34 = getelementptr %class.vector, %class.vector* %17, i32 0, i32 0
	%35 = load i32*, i32** %34
	%36 = load i32, i32* %21
	%37 = getelementptr i32, i32* %35, i32 %36
	%38 = getelementptr %class.vector, %class.vector* %5, i32 0, i32 0
	%39 = load i32*, i32** %38
	%40 = load i32, i32* %21
	%41 = getelementptr i32, i32* %39, i32 %40
	%42 = getelementptr %class.vector, %class.vector* %3, i32 0, i32 0
	%43 = load i32*, i32** %42
	%44 = load i32, i32* %21
	%45 = getelementptr i32, i32* %43, i32 %44
	%46 = load i32, i32* %41
	%47 = load i32, i32* %45
	%48 = add nsw i32 %46, %47
	store i32 %48, i32* %37
	br label %_for_cond_6

_for_next_8:
	%49 = load %class.vector, %class.vector* %17
	ret %class.vector %49

}

define void @vector.init(%class.vector* %0, i32* %1) {
	%3 = alloca i32*
	store i32* %1, i32** %3
	%4 = alloca %class.vector*
	store %class.vector* %0, %class.vector** %4
	%5 = load %class.vector*, %class.vector** %4
	%6 = icmp eq i32** %3, null
	br i1 %6, label %_if_then_1, label %_if_else_2

_if_then_1:
	ret void

_if_else_2:
	br label %_if_next_3

_if_next_3:
	%7 = getelementptr %class.vector, %class.vector* %5, i32 0, i32 0
	%8 = load i32*, i32** %3
	%9 = getelementptr i32, i32* %8, i32 -1
	%10 = load i32, i32* %9
	%11 = mul nsw i32 %10, 4
	%12 = add nsw i32 %11, 32
	%13 = call i8* @__malloc(i32 %12)
	%14 = bitcast i8* %13 to i32*
	store i32 %10, i32* %14
	%15 = getelementptr i32, i32* %14, i32 1
	store i32* %15, i32** %7
	%16 = alloca i32
	store i32 0, i32* %16
	br label %_for_cond_4

_for_cond_4:
	%17 = load i32*, i32** %3
	%18 = getelementptr i32, i32* %17, i32 -1
	%19 = load i32, i32* %16
	%20 = load i32, i32* %18
	%21 = icmp slt i32 %19, %20
	br i1 %21, label %_for_loop_5, label %_for_next_6

_for_loop_5:
	%22 = load i32, i32* %16
	%23 = add nsw i32 %22, 1
	store i32 %23, i32* %16
	%24 = getelementptr %class.vector, %class.vector* %5, i32 0, i32 0
	%25 = load i32*, i32** %24
	%26 = load i32, i32* %16
	%27 = getelementptr i32, i32* %25, i32 %26
	%28 = load i32*, i32** %3
	%29 = load i32, i32* %16
	%30 = getelementptr i32, i32* %28, i32 %29
	%31 = load i32, i32* %30
	store i32 %31, i32* %27
	br label %_for_cond_4

_for_next_6:
	ret void

}

define %class.vector @vector.scalarInPlaceMultiply(%class.vector* %0, i32 %1) {
	%3 = alloca i32
	store i32 %1, i32* %3
	%4 = alloca %class.vector*
	store %class.vector* %0, %class.vector** %4
	%5 = load %class.vector*, %class.vector** %4
	%6 = getelementptr %class.vector, %class.vector* %5, i32 0, i32 0
	%7 = icmp eq i32** %6, null
	br i1 %7, label %_if_then_1, label %_if_else_2

_if_then_1:
	%8 = load %class.vector, %class.vector* null
	ret %class.vector %8

_if_else_2:
	br label %_if_next_3

_if_next_3:
	%9 = alloca i32
	store i32 0, i32* %9
	br label %_for_cond_4

_for_cond_4:
	%10 = call i32 @vector.getDim(%class.vector* %5)
	%11 = load i32, i32* %9
	%12 = icmp slt i32 %11, %10
	br i1 %12, label %_for_loop_5, label %_for_next_6

_for_loop_5:
	%13 = load i32, i32* %9
	%14 = add nsw i32 %13, 1
	store i32 %14, i32* %9
	%15 = getelementptr %class.vector, %class.vector* %5, i32 0, i32 0
	%16 = load i32*, i32** %15
	%17 = load i32, i32* %9
	%18 = getelementptr i32, i32* %16, i32 %17
	%19 = getelementptr %class.vector, %class.vector* %5, i32 0, i32 0
	%20 = load i32*, i32** %19
	%21 = load i32, i32* %9
	%22 = getelementptr i32, i32* %20, i32 %21
	%23 = load i32, i32* %3
	%24 = load i32, i32* %22
	%25 = mul nsw i32 %23, %24
	store i32 %25, i32* %18
	br label %_for_cond_4

_for_next_6:
	%26 = load %class.vector, %class.vector* %5
	ret %class.vector %26

}

define i8 @vector.copy(%class.vector* %0, %class.vector %1) {
	%3 = alloca %class.vector
	store %class.vector %1, %class.vector* %3
	%4 = alloca %class.vector*
	store %class.vector* %0, %class.vector** %4
	%5 = load %class.vector*, %class.vector** %4
	%6 = icmp eq %class.vector* %3, null
	br i1 %6, label %_if_then_1, label %_if_else_2

_if_then_1:
	ret i8 0

_if_else_2:
	br label %_if_next_3

_if_next_3:
	%7 = call i32 @vector.getDim(%class.vector* %3)
	%8 = icmp eq i32 %7, 0
	br i1 %8, label %_if_then_4, label %_if_else_5

_if_then_4:
	%9 = getelementptr %class.vector, %class.vector* %5, i32 0, i32 0
	store i32* null, i32** %9
	br label %_if_next_6

_if_else_5:
	%10 = getelementptr %class.vector, %class.vector* %5, i32 0, i32 0
	%11 = call i32 @vector.getDim(%class.vector* %3)
	%12 = mul nsw i32 %11, 4
	%13 = add nsw i32 %12, 32
	%14 = call i8* @__malloc(i32 %13)
	%15 = bitcast i8* %14 to i32*
	store i32 %11, i32* %15
	%16 = getelementptr i32, i32* %15, i32 1
	store i32* %16, i32** %10
	%17 = alloca i32
	store i32 0, i32* %17
	br label %_for_cond_7

_for_cond_7:
	%18 = call i32 @vector.getDim(%class.vector* %5)
	%19 = load i32, i32* %17
	%20 = icmp slt i32 %19, %18
	br i1 %20, label %_for_loop_8, label %_for_next_9

_for_loop_8:
	%21 = load i32, i32* %17
	%22 = add nsw i32 %21, 1
	store i32 %22, i32* %17
	%23 = getelementptr %class.vector, %class.vector* %5, i32 0, i32 0
	%24 = load i32*, i32** %23
	%25 = load i32, i32* %17
	%26 = getelementptr i32, i32* %24, i32 %25
	%27 = getelementptr %class.vector, %class.vector* %3, i32 0, i32 0
	%28 = load i32*, i32** %27
	%29 = load i32, i32* %17
	%30 = getelementptr i32, i32* %28, i32 %29
	%31 = load i32, i32* %30
	store i32 %31, i32* %26
	br label %_for_cond_7

_for_next_9:
	br label %_if_next_6

_if_next_6:
	ret i8 1

}

define i32 @vector.dot(%class.vector* %0, %class.vector %1) {
	%3 = alloca %class.vector
	store %class.vector %1, %class.vector* %3
	%4 = alloca %class.vector*
	store %class.vector* %0, %class.vector** %4
	%5 = load %class.vector*, %class.vector** %4
	%6 = alloca i32
	store i32 0, i32* %6
	%7 = alloca i32
	store i32 0, i32* %7
	br label %_while_cond_1

_while_cond_1:
	%8 = call i32 @vector.getDim(%class.vector* %5)
	%9 = load i32, i32* %6
	%10 = icmp slt i32 %9, %8
	br i1 %10, label %while_loop_2, label %_while_next_3

while_loop_2:
	%11 = getelementptr %class.vector, %class.vector* %5, i32 0, i32 0
	%12 = load i32*, i32** %11
	%13 = load i32, i32* %6
	%14 = getelementptr i32, i32* %12, i32 %13
	%15 = getelementptr %class.vector, %class.vector* %3, i32 0, i32 0
	%16 = load i32*, i32** %15
	%17 = load i32, i32* %6
	%18 = getelementptr i32, i32* %16, i32 %17
	%19 = load i32, i32* %14
	%20 = load i32, i32* %18
	%21 = mul nsw i32 %19, %20
	store i32 %21, i32* %7
	%22 = load i32, i32* %6
	%23 = add nsw i32 %22, 1
	store i32 %23, i32* %6
	br label %_while_cond_1

_while_next_3:
	%24 = load i32, i32* %7
	ret i32 %24

}

define void @vector.build(%class.vector* %0) {
	ret void

}

define i32 @main() {
	%1 = alloca %class.vector
	%2 = call i8* @__malloc(i32 4)
	%3 = bitcast i8* %2 to %class.vector*
	call void @vector.build(%class.vector* %3)
	%4 = load %class.vector, %class.vector* %3
	store %class.vector %4, %class.vector* %1
	%5 = alloca i32*
	%6 = call i8* @__malloc(i32 44)
	%7 = bitcast i8* %6 to i32*
	store i32 10, i32* %7
	%8 = getelementptr i32, i32* %7, i32 1
	store i32* %8, i32** %5
	%9 = alloca i32
	store i32 0, i32* %9
	br label %_for_cond_1

_for_cond_1:
	%10 = load i32, i32* %9
	%11 = icmp slt i32 %10, 10
	br i1 %11, label %_for_loop_2, label %_for_next_3

_for_loop_2:
	%12 = load i32, i32* %9
	%13 = add nsw i32 %12, 1
	store i32 %13, i32* %9
	%14 = load i32*, i32** %5
	%15 = load i32, i32* %9
	%16 = getelementptr i32, i32* %14, i32 %15
	%17 = load i32, i32* %9
	%18 = sub nsw i32 9, %17
	store i32 %18, i32* %16
	br label %_for_cond_1

_for_next_3:
	%19 = load i32*, i32** %5
	call void @vector.init(%class.vector* %1, i32* %19)
	%20 = getelementptr [11 x i8], [11 x i8]* @str_3, i32 0, i32 0
	call void @print(i8* %20)
	%21 = call i8* @vector.tostring(%class.vector* %1)
	call void @println(i8* %21)
	%22 = alloca %class.vector
	%23 = call i8* @__malloc(i32 4)
	%24 = bitcast i8* %23 to %class.vector*
	call void @vector.build(%class.vector* %24)
	%25 = load %class.vector, %class.vector* %24
	store %class.vector %25, %class.vector* %22
	%26 = load %class.vector, %class.vector* %1
	%27 = call i8 @vector.copy(%class.vector* %22, %class.vector %26)
	%28 = call i8 @vector.set(%class.vector* %22, i32 3, i32 817)
	%29 = trunc i8 %28 to i1
	br i1 %29, label %_if_then_4, label %_if_else_5

_if_then_4:
	%30 = getelementptr [9 x i8], [9 x i8]* @str_4, i32 0, i32 0
	call void @println(i8* %30)
	br label %_if_next_6

_if_else_5:
	br label %_if_next_6

_if_next_6:
	%31 = getelementptr [11 x i8], [11 x i8]* @str_5, i32 0, i32 0
	call void @print(i8* %31)
	%32 = call i8* @vector.tostring(%class.vector* %22)
	call void @println(i8* %32)
	%33 = getelementptr [8 x i8], [8 x i8]* @str_6, i32 0, i32 0
	call void @print(i8* %33)
	%34 = load %class.vector, %class.vector* %22
	%35 = call %class.vector @vector.add(%class.vector* %1, %class.vector %34)
	%36 = alloca %class.vector
	store %class.vector %35, %class.vector* %36
	%37 = call i8* @vector.tostring(%class.vector* %36)
	call void @println(i8* %37)
	%38 = getelementptr [8 x i8], [8 x i8]* @str_7, i32 0, i32 0
	call void @print(i8* %38)
	%39 = load %class.vector, %class.vector* %22
	%40 = call i32 @vector.dot(%class.vector* %1, %class.vector %39)
	%41 = call i8* @toString(i32 %40)
	call void @println(i8* %41)
	%42 = getelementptr [15 x i8], [15 x i8]* @str_8, i32 0, i32 0
	call void @print(i8* %42)
	%43 = call %class.vector @vector.scalarInPlaceMultiply(%class.vector* %22, i32 8)
	%44 = alloca %class.vector
	store %class.vector %43, %class.vector* %44
	%45 = call i8* @vector.tostring(%class.vector* %44)
	call void @println(i8* %45)
	ret i32 0

}

define i8* @vector.tostring(%class.vector* %0) {
	%2 = alloca %class.vector*
	store %class.vector* %0, %class.vector** %2
	%3 = load %class.vector*, %class.vector** %2
	%4 = alloca i8*
	%5 = getelementptr [3 x i8], [3 x i8]* @str_0, i32 0, i32 0
	store i8* %5, i8** %4
	%6 = call i32 @vector.getDim(%class.vector* %3)
	%7 = icmp sgt i32 %6, 0
	br i1 %7, label %_if_then_1, label %_if_else_2

_if_then_1:
	%8 = getelementptr %class.vector, %class.vector* %3, i32 0, i32 0
	%9 = load i32*, i32** %8
	%10 = getelementptr i32, i32* %9, i32 0
	%11 = load i32, i32* %10
	%12 = call i8* @toString(i32 %11)
	%13 = load i8*, i8** %4
	%14 = call i8* @__str_add(i8* %13, i8* %12)
	store i8* %14, i8** %4
	br label %_if_next_3

_if_else_2:
	br label %_if_next_3

_if_next_3:
	%15 = alloca i32
	store i32 1, i32* %15
	br label %_for_cond_4

_for_cond_4:
	%16 = call i32 @vector.getDim(%class.vector* %3)
	%17 = load i32, i32* %15
	%18 = icmp slt i32 %17, %16
	br i1 %18, label %_for_loop_5, label %_for_next_6

_for_loop_5:
	%19 = load i32, i32* %15
	%20 = add nsw i32 %19, 1
	store i32 %20, i32* %15
	%21 = load i8*, i8** %4
	%22 = getelementptr [3 x i8], [3 x i8]* @str_1, i32 0, i32 0
	%23 = call i8* @__str_add(i8* %21, i8* %22)
	%24 = getelementptr %class.vector, %class.vector* %3, i32 0, i32 0
	%25 = load i32*, i32** %24
	%26 = load i32, i32* %15
	%27 = getelementptr i32, i32* %25, i32 %26
	%28 = load i32, i32* %27
	%29 = call i8* @toString(i32 %28)
	%30 = call i8* @__str_add(i8* %23, i8* %29)
	store i8* %30, i8** %4
	br label %_for_cond_4

_for_next_6:
	%31 = load i8*, i8** %4
	%32 = getelementptr [3 x i8], [3 x i8]* @str_2, i32 0, i32 0
	%33 = call i8* @__str_add(i8* %31, i8* %32)
	store i8* %33, i8** %4
	%34 = load i8*, i8** %4
	ret i8* %34

}

define i8 @vector.set(%class.vector* %0, i32 %1, i32 %2) {
	%4 = alloca i32
	store i32 %1, i32* %4
	%5 = alloca i32
	store i32 %2, i32* %5
	%6 = alloca %class.vector*
	store %class.vector* %0, %class.vector** %6
	%7 = load %class.vector*, %class.vector** %6
	%8 = call i32 @vector.getDim(%class.vector* %7)
	%9 = load i32, i32* %4
	%10 = icmp slt i32 %8, %9
	br i1 %10, label %_if_then_1, label %_if_else_2

_if_then_1:
	ret i8 0

_if_else_2:
	br label %_if_next_3

_if_next_3:
	%11 = getelementptr %class.vector, %class.vector* %7, i32 0, i32 0
	%12 = load i32*, i32** %11
	%13 = load i32, i32* %4
	%14 = getelementptr i32, i32* %12, i32 %13
	%15 = load i32, i32* %5
	store i32 %15, i32* %14
	ret i8 1

}

; ModuleID = 'builtin/builtin.c'
source_filename = "builtin/builtin.c"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@.str = private unnamed_addr constant [3 x i8] c"%s\00", align 1
@.str.1 = private unnamed_addr constant [4 x i8] c"%s\0A\00", align 1
@.str.2 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str.3 = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @print(i8* %0) #0 {
  %2 = alloca i8*, align 8
  store i8* %0, i8** %2, align 8
  %3 = load i8*, i8** %2, align 8
  %4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i8* %3)
  ret void
}

declare dso_local i32 @printf(i8*, ...) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @println(i8* %0) #0 {
  %2 = alloca i8*, align 8
  store i8* %0, i8** %2, align 8
  %3 = load i8*, i8** %2, align 8
  %4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str.1, i64 0, i64 0), i8* %3)
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @printInt(i32 %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i32 %3)
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @printlnInt(i32 %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str.3, i64 0, i64 0), i32 %3)
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i8* @getString() #0 {
  %1 = alloca i8*, align 8
  %2 = call noalias i8* @malloc(i64 256) #5
  store i8* %2, i8** %1, align 8
  %3 = load i8*, i8** %1, align 8
  %4 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i8* %3)
  %5 = load i8*, i8** %1, align 8
  ret i8* %5
}

; Function Attrs: nounwind
declare dso_local noalias i8* @malloc(i64) #2

declare dso_local i32 @__isoc99_scanf(i8*, ...) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @getInt() #0 {
  %1 = alloca i32, align 4
  %2 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i32* %1)
  %3 = load i32, i32* %1, align 4
  ret i32 %3
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i8* @toString(i32 %0) #0 {
  %2 = alloca i32, align 4
  %3 = alloca i8*, align 8
  store i32 %0, i32* %2, align 4
  %4 = call noalias i8* @malloc(i64 256) #5
  store i8* %4, i8** %3, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i32, i32* %2, align 4
  %7 = call i32 (i8*, i8*, ...) @sprintf(i8* %5, i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i32 %6) #5
  %8 = load i8*, i8** %3, align 8
  ret i8* %8
}

; Function Attrs: nounwind
declare dso_local i32 @sprintf(i8*, i8*, ...) #2

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @__mx_length(i8* %0) #0 {
  %2 = alloca i8*, align 8
  store i8* %0, i8** %2, align 8
  %3 = load i8*, i8** %2, align 8
  %4 = call i64 @strlen(i8* %3) #6
  %5 = trunc i64 %4 to i32
  ret i32 %5
}

; Function Attrs: nounwind readonly
declare dso_local i64 @strlen(i8*) #3

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i8* @__mx_substring(i8* %0, i32 %1, i32 %2) #0 {
  %4 = alloca i8*, align 8
  %5 = alloca i32, align 4
  %6 = alloca i32, align 4
  %7 = alloca i32, align 4
  %8 = alloca i8*, align 8
  store i8* %0, i8** %4, align 8
  store i32 %1, i32* %5, align 4
  store i32 %2, i32* %6, align 4
  %9 = load i32, i32* %6, align 4
  %10 = load i32, i32* %5, align 4
  %11 = sub nsw i32 %9, %10
  %12 = add nsw i32 %11, 1
  store i32 %12, i32* %7, align 4
  %13 = load i32, i32* %7, align 4
  %14 = sext i32 %13 to i64
  %15 = mul i64 1, %14
  %16 = call noalias i8* @malloc(i64 %15) #5
  store i8* %16, i8** %8, align 8
  %17 = load i8*, i8** %8, align 8
  %18 = load i8*, i8** %4, align 8
  %19 = load i32, i32* %5, align 4
  %20 = sext i32 %19 to i64
  %21 = getelementptr inbounds i8, i8* %18, i64 %20
  %22 = load i32, i32* %7, align 4
  %23 = sext i32 %22 to i64
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* align 1 %17, i8* align 1 %21, i64 %23, i1 false)
  %24 = load i8*, i8** %8, align 8
  %25 = load i32, i32* %7, align 4
  %26 = sub nsw i32 %25, 1
  %27 = sext i32 %26 to i64
  %28 = getelementptr inbounds i8, i8* %24, i64 %27
  store i8 0, i8* %28, align 1
  %29 = load i8*, i8** %8, align 8
  ret i8* %29
}

; Function Attrs: argmemonly nounwind willreturn
declare void @llvm.memcpy.p0i8.p0i8.i64(i8* noalias nocapture writeonly, i8* noalias nocapture readonly, i64, i1 immarg) #4

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @__mx_parseInt(i8* %0) #0 {
  %2 = alloca i8*, align 8
  %3 = alloca i32, align 4
  store i8* %0, i8** %2, align 8
  %4 = load i8*, i8** %2, align 8
  %5 = call i32 (i8*, i8*, ...) @__isoc99_sscanf(i8* %4, i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i32* %3) #5
  %6 = load i32, i32* %3, align 4
  ret i32 %6
}

; Function Attrs: nounwind
declare dso_local i32 @__isoc99_sscanf(i8*, i8*, ...) #2

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @__mx_ord(i8* %0, i32 %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i32, align 4
  store i8* %0, i8** %3, align 8
  store i32 %1, i32* %4, align 4
  %5 = load i8*, i8** %3, align 8
  %6 = load i32, i32* %4, align 4
  %7 = sext i32 %6 to i64
  %8 = getelementptr inbounds i8, i8* %5, i64 %7
  %9 = load i8, i8* %8, align 1
  %10 = sext i8 %9 to i32
  ret i32 %10
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local signext i8 @__str_eq(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* %5, i8* %6) #6
  %8 = icmp eq i32 %7, 0
  %9 = zext i1 %8 to i32
  %10 = trunc i32 %9 to i8
  ret i8 %10
}

; Function Attrs: nounwind readonly
declare dso_local i32 @strcmp(i8*, i8*) #3

; Function Attrs: noinline nounwind optnone uwtable
define dso_local signext i8 @__str_ne(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* %5, i8* %6) #6
  %8 = icmp ne i32 %7, 0
  %9 = zext i1 %8 to i32
  %10 = trunc i32 %9 to i8
  ret i8 %10
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local signext i8 @__str_slt(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* %5, i8* %6) #6
  %8 = icmp slt i32 %7, 0
  %9 = zext i1 %8 to i32
  %10 = trunc i32 %9 to i8
  ret i8 %10
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local signext i8 @__str_sle(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* %5, i8* %6) #6
  %8 = icmp sle i32 %7, 0
  %9 = zext i1 %8 to i32
  %10 = trunc i32 %9 to i8
  ret i8 %10
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local signext i8 @__str_sgt(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* %5, i8* %6) #6
  %8 = icmp sgt i32 %7, 0
  %9 = zext i1 %8 to i32
  %10 = trunc i32 %9 to i8
  ret i8 %10
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local signext i8 @__str_sge(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* %5, i8* %6) #6
  %8 = icmp sge i32 %7, 0
  %9 = zext i1 %8 to i32
  %10 = trunc i32 %9 to i8
  ret i8 %10
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i8* @__str_add(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  %5 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %6 = load i8*, i8** %3, align 8
  %7 = call i64 @strlen(i8* %6) #6
  %8 = load i8*, i8** %4, align 8
  %9 = call i64 @strlen(i8* %8) #6
  %10 = add i64 %7, %9
  %11 = add i64 %10, 1
  %12 = mul i64 1, %11
  %13 = call noalias i8* @malloc(i64 %12) #5
  store i8* %13, i8** %5, align 8
  %14 = load i8*, i8** %5, align 8
  %15 = load i8*, i8** %3, align 8
  %16 = call i8* @strcpy(i8* %14, i8* %15) #5
  %17 = load i8*, i8** %5, align 8
  %18 = load i8*, i8** %4, align 8
  %19 = call i8* @strcat(i8* %17, i8* %18) #5
  %20 = load i8*, i8** %5, align 8
  ret i8* %20
}

; Function Attrs: nounwind
declare dso_local i8* @strcpy(i8*, i8*) #2

; Function Attrs: nounwind
declare dso_local i8* @strcat(i8*, i8*) #2

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i8* @__malloc(i32 %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = sext i32 %3 to i64
  %5 = call noalias i8* @malloc(i64 %4) #5
  ret i8* %5
}

attributes #0 = { noinline nounwind optnone uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #1 = { "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #2 = { nounwind "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #3 = { nounwind readonly "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #4 = { argmemonly nounwind willreturn }
attributes #5 = { nounwind }
attributes #6 = { nounwind readonly }

!llvm.module.flags = !{!0}
!llvm.ident = !{!1}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{!"clang version 10.0.0-4ubuntu1 "}
