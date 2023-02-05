@e = global %class.Edge* null
@ans = global i32 0
@rk = global i32* null
@fa = global i32* null
@m = global i32 0
@n = global i32 0

%class.Edge = type { i32, i32, i32 }

define void @qsort(%class.Edge* %0, i32 %1, i32 %2) {
	%4 = alloca %class.Edge*
	store %class.Edge* %0, %class.Edge** %4
	%5 = alloca i32
	store i32 %1, i32* %5
	%6 = alloca i32
	store i32 %2, i32* %6
	%7 = load i32, i32* %5
	%8 = load i32, i32* %6
	%9 = icmp slt i32 %7, %8
	br i1 %9, label %_if_then_1, label %_if_else_2

_if_then_1:
	%10 = alloca i32
	%11 = load i32, i32* %5
	store i32 %11, i32* %10
	%12 = alloca i32
	%13 = load i32, i32* %6
	store i32 %13, i32* %12
	%14 = alloca %class.Edge
	%15 = load %class.Edge*, %class.Edge** %4
	%16 = load i32, i32* %5
	%17 = getelementptr %class.Edge, %class.Edge* %15, i32 %16
	%18 = load %class.Edge, %class.Edge* %17
	store %class.Edge %18, %class.Edge* %14
	br label %_while_cond_4

_while_cond_4:
	%19 = load i32, i32* %10
	%20 = load i32, i32* %12
	%21 = icmp slt i32 %19, %20
	br i1 %21, label %while_loop_5, label %_while_next_6

while_loop_5:
	br label %_while_cond_7

_while_cond_7:
	%22 = load i32, i32* %10
	%23 = load i32, i32* %12
	%24 = icmp slt i32 %22, %23
	%25 = load %class.Edge*, %class.Edge** %4
	%26 = load i32, i32* %12
	%27 = getelementptr %class.Edge, %class.Edge* %25, i32 %26
	%28 = getelementptr %class.Edge, %class.Edge* %27, i32 0, i32 2
	%29 = getelementptr %class.Edge, %class.Edge* %14, i32 0, i32 2
	%30 = load i32, i32* %28
	%31 = load i32, i32* %29
	%32 = icmp sge i32 %30, %31
	%33 = zext i1 %24 to i8
	%34 = zext i1 %32 to i8
	%35 = alloca i1
	%36 = icmp ne i8 %33, 0
	br i1 %36, label %_land_rhs_10, label %_land_false_12

_land_rhs_10:
	%37 = icmp ne i8 %34, 0
	br i1 %37, label %_land_true_11, label %_land_false_12

_land_true_11:
	store i1 true, i1* %35
	br label %_land_next_13

_land_false_12:
	store i1 false, i1* %35
	br label %_land_next_13

_land_next_13:
	%38 = load i1, i1* %35
	br i1 %38, label %while_loop_8, label %_while_next_9

while_loop_8:
	%39 = load i32, i32* %12
	%40 = add nsw i32 %39, -1
	store i32 %40, i32* %12
	br label %_while_cond_7

_while_next_9:
	%41 = load i32, i32* %10
	%42 = load i32, i32* %12
	%43 = icmp slt i32 %41, %42
	br i1 %43, label %_if_then_14, label %_if_else_15

_if_then_14:
	%44 = load %class.Edge*, %class.Edge** %4
	%45 = load i32, i32* %10
	%46 = getelementptr %class.Edge, %class.Edge* %44, i32 %45
	%47 = load %class.Edge*, %class.Edge** %4
	%48 = load i32, i32* %12
	%49 = getelementptr %class.Edge, %class.Edge* %47, i32 %48
	%50 = load %class.Edge, %class.Edge* %49
	store %class.Edge %50, %class.Edge* %46
	%51 = load i32, i32* %10
	%52 = add nsw i32 %51, 1
	store i32 %52, i32* %10
	br label %_if_next_16

_if_else_15:
	br label %_if_next_16

_if_next_16:
	br label %_while_cond_17

_while_cond_17:
	%53 = load i32, i32* %10
	%54 = load i32, i32* %12
	%55 = icmp slt i32 %53, %54
	%56 = load %class.Edge*, %class.Edge** %4
	%57 = load i32, i32* %10
	%58 = getelementptr %class.Edge, %class.Edge* %56, i32 %57
	%59 = getelementptr %class.Edge, %class.Edge* %58, i32 0, i32 2
	%60 = getelementptr %class.Edge, %class.Edge* %14, i32 0, i32 2
	%61 = load i32, i32* %59
	%62 = load i32, i32* %60
	%63 = icmp slt i32 %61, %62
	%64 = zext i1 %55 to i8
	%65 = zext i1 %63 to i8
	%66 = alloca i1
	%67 = icmp ne i8 %64, 0
	br i1 %67, label %_land_rhs_20, label %_land_false_22

_land_rhs_20:
	%68 = icmp ne i8 %65, 0
	br i1 %68, label %_land_true_21, label %_land_false_22

_land_true_21:
	store i1 true, i1* %66
	br label %_land_next_23

_land_false_22:
	store i1 false, i1* %66
	br label %_land_next_23

_land_next_23:
	%69 = load i1, i1* %66
	br i1 %69, label %while_loop_18, label %_while_next_19

while_loop_18:
	%70 = load i32, i32* %10
	%71 = add nsw i32 %70, 1
	store i32 %71, i32* %10
	br label %_while_cond_17

_while_next_19:
	%72 = load i32, i32* %10
	%73 = load i32, i32* %12
	%74 = icmp slt i32 %72, %73
	br i1 %74, label %_if_then_24, label %_if_else_25

_if_then_24:
	%75 = load %class.Edge*, %class.Edge** %4
	%76 = load i32, i32* %12
	%77 = getelementptr %class.Edge, %class.Edge* %75, i32 %76
	%78 = load %class.Edge*, %class.Edge** %4
	%79 = load i32, i32* %10
	%80 = getelementptr %class.Edge, %class.Edge* %78, i32 %79
	%81 = load %class.Edge, %class.Edge* %80
	store %class.Edge %81, %class.Edge* %77
	%82 = load i32, i32* %12
	%83 = add nsw i32 %82, -1
	store i32 %83, i32* %12
	br label %_if_next_26

_if_else_25:
	br label %_if_next_26

_if_next_26:
	br label %_while_cond_4

_while_next_6:
	%84 = load %class.Edge*, %class.Edge** %4
	%85 = load i32, i32* %10
	%86 = getelementptr %class.Edge, %class.Edge* %84, i32 %85
	%87 = load %class.Edge, %class.Edge* %14
	store %class.Edge %87, %class.Edge* %86
	%88 = load %class.Edge*, %class.Edge** %4
	%89 = load i32, i32* %5
	%90 = load i32, i32* %10
	%91 = sub nsw i32 %90, 1
	call void @qsort(%class.Edge* %88, i32 %89, i32 %91)
	%92 = load %class.Edge*, %class.Edge** %4
	%93 = load i32, i32* %10
	%94 = add nsw i32 %93, 1
	%95 = load i32, i32* %6
	call void @qsort(%class.Edge* %92, i32 %94, i32 %95)
	br label %_if_next_3

_if_else_2:
	br label %_if_next_3

_if_next_3:
	ret void

}

define void @init() {
	%1 = load i32, i32* @n
	%2 = add nsw i32 %1, 1
	%3 = mul nsw i32 %2, 4
	%4 = add nsw i32 %3, 32
	%5 = call i8* @__malloc(i32 %4)
	%6 = bitcast i8* %5 to i32*
	store i32 %2, i32* %6
	%7 = getelementptr i32, i32* %6, i32 1
	store i32* %7, i32** @fa
	%8 = load i32, i32* @n
	%9 = add nsw i32 %8, 1
	%10 = mul nsw i32 %9, 4
	%11 = add nsw i32 %10, 32
	%12 = call i8* @__malloc(i32 %11)
	%13 = bitcast i8* %12 to i32*
	store i32 %9, i32* %13
	%14 = getelementptr i32, i32* %13, i32 1
	store i32* %14, i32** @rk
	%15 = alloca i32
	store i32 1, i32* %15
	br label %_for_cond_1

_for_cond_1:
	%16 = load i32, i32* %15
	%17 = load i32, i32* @n
	%18 = icmp sle i32 %16, %17
	br i1 %18, label %_for_loop_2, label %_for_next_3

_for_loop_2:
	%19 = load i32*, i32** @fa
	%20 = load i32, i32* %15
	%21 = getelementptr i32, i32* %19, i32 %20
	%22 = load i32, i32* %15
	store i32 %22, i32* %21
	%23 = load i32*, i32** @rk
	%24 = load i32, i32* %15
	%25 = getelementptr i32, i32* %23, i32 %24
	store i32 1, i32* %25
	%26 = load i32, i32* %15
	%27 = add nsw i32 %26, 1
	store i32 %27, i32* %15
	br label %_for_cond_1

_for_next_3:
	ret void

}

define void @Edge.build(%class.Edge* %0) {
	ret void

}

define i32 @find(i32 %0) {
	%2 = alloca i32
	store i32 %0, i32* %2
	%3 = load i32*, i32** @fa
	%4 = load i32, i32* %2
	%5 = getelementptr i32, i32* %3, i32 %4
	%6 = load i32, i32* %2
	%7 = load i32, i32* %5
	%8 = icmp eq i32 %6, %7
	br i1 %8, label %_if_then_1, label %_if_else_2

_if_then_1:
	%9 = load i32, i32* %2
	ret i32 %9

_if_else_2:
	br label %_if_next_3

_if_next_3:
	%10 = load i32*, i32** @fa
	%11 = load i32, i32* %2
	%12 = getelementptr i32, i32* %10, i32 %11
	%13 = load i32, i32* %12
	%14 = call i32 @find(i32 %13)
	store i32 %14, i32* %2
	%15 = load i32*, i32** @fa
	%16 = load i32, i32* %2
	%17 = getelementptr i32, i32* %15, i32 %16
	%18 = load i32, i32* %17
	ret i32 %18

}

define i32 @main() {
	%1 = call i32 @getInt()
	store i32 %1, i32* @n
	%2 = call i32 @getInt()
	store i32 %2, i32* @m
	%3 = load i32, i32* @m
	%4 = mul nsw i32 %3, 12
	%5 = add nsw i32 %4, 32
	%6 = call i8* @__malloc(i32 %5)
	%7 = bitcast i8* %6 to i32*
	store i32 %3, i32* %7
	%8 = getelementptr i32, i32* %7, i32 1
	%9 = bitcast i32* %8 to %class.Edge*
	store %class.Edge* %9, %class.Edge** @e
	%10 = alloca i32
	store i32 0, i32* %10
	br label %_for_cond_1

_for_cond_1:
	%11 = load i32, i32* %10
	%12 = load i32, i32* @m
	%13 = icmp slt i32 %11, %12
	br i1 %13, label %_for_loop_2, label %_for_next_3

_for_loop_2:
	%14 = alloca %class.Edge
	%15 = call i8* @__malloc(i32 12)
	%16 = bitcast i8* %15 to %class.Edge*
	call void @Edge.build(%class.Edge* %16)
	%17 = load %class.Edge, %class.Edge* %16
	store %class.Edge %17, %class.Edge* %14
	%18 = getelementptr %class.Edge, %class.Edge* %14, i32 0, i32 0
	%19 = call i32 @getInt()
	store i32 %19, i32* %18
	%20 = getelementptr %class.Edge, %class.Edge* %14, i32 0, i32 1
	%21 = call i32 @getInt()
	store i32 %21, i32* %20
	%22 = getelementptr %class.Edge, %class.Edge* %14, i32 0, i32 2
	%23 = call i32 @getInt()
	store i32 %23, i32* %22
	%24 = load %class.Edge*, %class.Edge** @e
	%25 = load i32, i32* %10
	%26 = getelementptr %class.Edge, %class.Edge* %24, i32 %25
	%27 = load %class.Edge, %class.Edge* %14
	store %class.Edge %27, %class.Edge* %26
	%28 = load i32, i32* %10
	%29 = add nsw i32 %28, 1
	store i32 %29, i32* %10
	br label %_for_cond_1

_for_next_3:
	%30 = load %class.Edge*, %class.Edge** @e
	%31 = load i32, i32* @m
	%32 = sub nsw i32 %31, 1
	call void @qsort(%class.Edge* %30, i32 0, i32 %32)
	call void @init()
	store i32 0, i32* %10
	%33 = alloca i32
	store i32 0, i32* %33
	br label %_while_cond_4

_while_cond_4:
	%34 = load i32, i32* @n
	%35 = sub nsw i32 %34, 1
	%36 = load i32, i32* %10
	%37 = icmp slt i32 %36, %35
	br i1 %37, label %while_loop_5, label %_while_next_6

while_loop_5:
	%38 = load i32, i32* %33
	%39 = load i32, i32* @m
	%40 = icmp sge i32 %38, %39
	br i1 %40, label %_if_then_7, label %_if_else_8

_if_then_7:
	call void @printInt(i32 -1)
	ret i32 0

_if_else_8:
	br label %_if_next_9

_if_next_9:
	%41 = alloca %class.Edge
	%42 = load %class.Edge*, %class.Edge** @e
	%43 = load i32, i32* %33
	%44 = getelementptr %class.Edge, %class.Edge* %42, i32 %43
	%45 = load %class.Edge, %class.Edge* %44
	store %class.Edge %45, %class.Edge* %41
	%46 = load i32, i32* %33
	%47 = add nsw i32 %46, 1
	store i32 %47, i32* %33
	%48 = getelementptr %class.Edge, %class.Edge* %41, i32 0, i32 0
	%49 = load i32, i32* %48
	%50 = getelementptr %class.Edge, %class.Edge* %41, i32 0, i32 1
	%51 = load i32, i32* %50
	%52 = call i8 @union(i32 %49, i32 %51)
	%53 = trunc i8 %52 to i1
	br i1 %53, label %_if_then_10, label %_if_next_12

_if_then_10:
	%54 = load i32, i32* %10
	%55 = add nsw i32 %54, 1
	store i32 %55, i32* %10
	%56 = getelementptr %class.Edge, %class.Edge* %41, i32 0, i32 2
	%57 = load i32, i32* @ans
	%58 = load i32, i32* %56
	%59 = add nsw i32 %57, %58
	store i32 %59, i32* @ans
	br label %_if_next_12

_if_else_11:
	br label %_if_next_12

_if_next_12:
	br label %_while_cond_4

_while_next_6:
	%60 = load i32*, i32** @rk
	%61 = call i32 @find(i32 1)
	%62 = getelementptr i32, i32* %60, i32 %61
	%63 = load i32, i32* %62
	%64 = load i32, i32* @n
	%65 = icmp eq i32 %63, %64
	br i1 %65, label %_if_then_13, label %_if_else_14

_if_then_13:
	%66 = load i32, i32* @ans
	call void @printInt(i32 %66)
	br label %_if_next_15

_if_else_14:
	call void @printInt(i32 -1)
	br label %_if_next_15

_if_next_15:
	ret i32 0

}

define i8 @union(i32 %0, i32 %1) {
	%3 = alloca i32
	store i32 %0, i32* %3
	%4 = alloca i32
	store i32 %1, i32* %4
	%5 = load i32, i32* %3
	%6 = call i32 @find(i32 %5)
	store i32 %6, i32* %3
	%7 = load i32, i32* %4
	%8 = call i32 @find(i32 %7)
	store i32 %8, i32* %4
	%9 = load i32, i32* %3
	%10 = load i32, i32* %4
	%11 = icmp eq i32 %9, %10
	br i1 %11, label %_if_then_1, label %_if_else_2

_if_then_1:
	ret i8 0

_if_else_2:
	br label %_if_next_3

_if_next_3:
	%12 = load i32*, i32** @rk
	%13 = load i32, i32* %3
	%14 = getelementptr i32, i32* %12, i32 %13
	%15 = load i32*, i32** @rk
	%16 = load i32, i32* %4
	%17 = getelementptr i32, i32* %15, i32 %16
	%18 = load i32, i32* %14
	%19 = load i32, i32* %17
	%20 = icmp sgt i32 %18, %19
	br i1 %20, label %_if_then_4, label %_if_else_5

_if_then_4:
	%21 = load i32*, i32** @fa
	%22 = load i32, i32* %4
	%23 = getelementptr i32, i32* %21, i32 %22
	%24 = load i32, i32* %3
	store i32 %24, i32* %23
	%25 = load i32*, i32** @rk
	%26 = load i32, i32* %3
	%27 = getelementptr i32, i32* %25, i32 %26
	%28 = load i32*, i32** @rk
	%29 = load i32, i32* %3
	%30 = getelementptr i32, i32* %28, i32 %29
	%31 = load i32*, i32** @rk
	%32 = load i32, i32* %4
	%33 = getelementptr i32, i32* %31, i32 %32
	%34 = load i32, i32* %30
	%35 = load i32, i32* %33
	%36 = add nsw i32 %34, %35
	store i32 %36, i32* %27
	br label %_if_next_6

_if_else_5:
	%37 = load i32*, i32** @fa
	%38 = load i32, i32* %3
	%39 = getelementptr i32, i32* %37, i32 %38
	%40 = load i32, i32* %4
	store i32 %40, i32* %39
	%41 = load i32*, i32** @rk
	%42 = load i32, i32* %4
	%43 = getelementptr i32, i32* %41, i32 %42
	%44 = load i32*, i32** @rk
	%45 = load i32, i32* %4
	%46 = getelementptr i32, i32* %44, i32 %45
	%47 = load i32*, i32** @rk
	%48 = load i32, i32* %3
	%49 = getelementptr i32, i32* %47, i32 %48
	%50 = load i32, i32* %46
	%51 = load i32, i32* %49
	%52 = add nsw i32 %50, %51
	store i32 %52, i32* %43
	br label %_if_next_6

_if_next_6:
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
