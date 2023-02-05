@str_0 = private unnamed_addr constant [2 x i8] c" \00"
@hashsize = global i32 100
@table = global %class.node* null

%class.node = type { i32, i32, %class.node }

define void @node.build(%class.node* %0) {
	ret void

}

define i32 @getHash(i32 %0) {
	%2 = alloca i32
	store i32 %0, i32* %2
	%3 = load i32, i32* %2
	%4 = mul nsw i32 %3, 237
	%5 = load i32, i32* @hashsize
	%6 = srem i32 %4, %5
	ret i32 %6

}

define i32 @get(i32 %0) {
	%2 = alloca i32
	store i32 %0, i32* %2
	%3 = alloca %class.node
	%4 = zext void null to %class.node
	store %class.node %4, %class.node* %3
	%5 = load %class.node*, %class.node** @table
	%6 = load i32, i32* %2
	%7 = call i32 @getHash(i32 %6)
	%8 = getelementptr %class.node, %class.node* %5, i32 %7
	%9 = load %class.node, %class.node* %8
	store %class.node %9, %class.node* %3
	br label %_while_cond_1

_while_cond_1:
	%10 = getelementptr %class.node, %class.node* %3, i32 0, i32 0
	%11 = load i32, i32* %10
	%12 = load i32, i32* %2
	%13 = icmp ne i32 %11, %12
	br i1 %13, label %while_loop_2, label %_while_next_3

while_loop_2:
	%14 = getelementptr %class.node, %class.node* %3, i32 0, i32 2
	%15 = load %class.node, %class.node* %14
	store %class.node %15, %class.node* %3
	br label %_while_cond_1

_while_next_3:
	%16 = getelementptr %class.node, %class.node* %3, i32 0, i32 1
	%17 = load i32, i32* %16
	ret i32 %17

}

define i32 @main() {
	%1 = alloca i32
	%2 = call i8* @__malloc(i32 1204)
	%3 = bitcast i8* %2 to i32*
	store i32 100, i32* %3
	%4 = getelementptr i32, i32* %3, i32 1
	%5 = bitcast i32* %4 to %class.node*
	store %class.node* %5, %class.node** @table
	store i32 0, i32* %1
	br label %_for_cond_1

_for_cond_1:
	%6 = load i32, i32* %1
	%7 = load i32, i32* @hashsize
	%8 = icmp slt i32 %6, %7
	br i1 %8, label %_for_loop_2, label %_for_next_3

_for_loop_2:
	%9 = load i32, i32* %1
	%10 = add nsw i32 %9, 1
	store i32 %10, i32* %1
	%11 = load %class.node*, %class.node** @table
	%12 = load i32, i32* %1
	%13 = getelementptr %class.node, %class.node* %11, i32 %12
	store %class.node null, %class.node* %13
	br label %_for_cond_1

_for_next_3:
	store i32 0, i32* %1
	br label %_for_cond_4

_for_cond_4:
	%14 = load i32, i32* %1
	%15 = icmp slt i32 %14, 1000
	br i1 %15, label %_for_loop_5, label %_for_next_6

_for_loop_5:
	%16 = load i32, i32* %1
	%17 = add nsw i32 %16, 1
	store i32 %17, i32* %1
	%18 = load i32, i32* %1
	%19 = load i32, i32* %1
	call void @put(i32 %18, i32 %19)
	br label %_for_cond_4

_for_next_6:
	store i32 0, i32* %1
	br label %_for_cond_7

_for_cond_7:
	%20 = load i32, i32* %1
	%21 = icmp slt i32 %20, 1000
	br i1 %21, label %_for_loop_8, label %_for_next_9

_for_loop_8:
	%22 = load i32, i32* %1
	%23 = add nsw i32 %22, 1
	store i32 %23, i32* %1
	%24 = load i32, i32* %1
	%25 = call i8* @toString(i32 %24)
	%26 = getelementptr [2 x i8], [2 x i8]* @str_0, i32 0, i32 0
	%27 = call i8* @__str_add(i8* %25, i8* %26)
	%28 = load i32, i32* %1
	%29 = call i32 @get(i32 %28)
	%30 = call i8* @toString(i32 %29)
	%31 = call i8* @__str_add(i8* %27, i8* %30)
	call void @println(i8* %31)
	br label %_for_cond_7

_for_next_9:
	ret i32 0

}

define void @put(i32 %0, i32 %1) {
	%3 = alloca i32
	store i32 %0, i32* %3
	%4 = alloca i32
	store i32 %1, i32* %4
	%5 = alloca i32
	%6 = alloca %class.node
	%7 = zext void null to %class.node
	store %class.node %7, %class.node* %6
	%8 = load i32, i32* %3
	%9 = call i32 @getHash(i32 %8)
	store i32 %9, i32* %5
	%10 = load %class.node*, %class.node** @table
	%11 = load i32, i32* %5
	%12 = getelementptr %class.node, %class.node* %10, i32 %11
	%13 = icmp eq %class.node* %12, null
	br i1 %13, label %_if_then_1, label %_if_else_2

_if_then_1:
	%14 = load %class.node*, %class.node** @table
	%15 = load i32, i32* %5
	%16 = getelementptr %class.node, %class.node* %14, i32 %15
	%17 = call i8* @__malloc(i32 12)
	%18 = bitcast i8* %17 to %class.node*
	call void @node.build(%class.node* %18)
	%19 = load %class.node, %class.node* %18
	store %class.node %19, %class.node* %16
	%20 = load %class.node*, %class.node** @table
	%21 = load i32, i32* %5
	%22 = getelementptr %class.node, %class.node* %20, i32 %21
	%23 = getelementptr %class.node, %class.node* %22, i32 0, i32 0
	%24 = load i32, i32* %3
	store i32 %24, i32* %23
	%25 = load %class.node*, %class.node** @table
	%26 = load i32, i32* %5
	%27 = getelementptr %class.node, %class.node* %25, i32 %26
	%28 = getelementptr %class.node, %class.node* %27, i32 0, i32 1
	%29 = load i32, i32* %4
	store i32 %29, i32* %28
	%30 = load %class.node*, %class.node** @table
	%31 = load i32, i32* %5
	%32 = getelementptr %class.node, %class.node* %30, i32 %31
	%33 = getelementptr %class.node, %class.node* %32, i32 0, i32 2
	store %class.node null, %class.node* %33
	ret void

_if_else_2:
	br label %_if_next_3

_if_next_3:
	%34 = load %class.node*, %class.node** @table
	%35 = load i32, i32* %5
	%36 = getelementptr %class.node, %class.node* %34, i32 %35
	%37 = load %class.node, %class.node* %36
	store %class.node %37, %class.node* %6
	br label %_while_cond_4

_while_cond_4:
	%38 = getelementptr %class.node, %class.node* %6, i32 0, i32 0
	%39 = load i32, i32* %38
	%40 = load i32, i32* %3
	%41 = icmp ne i32 %39, %40
	br i1 %41, label %while_loop_5, label %_while_next_6

while_loop_5:
	%42 = getelementptr %class.node, %class.node* %6, i32 0, i32 2
	%43 = icmp eq %class.node* %42, null
	br i1 %43, label %_if_then_7, label %_if_else_8

_if_then_7:
	%44 = getelementptr %class.node, %class.node* %6, i32 0, i32 2
	%45 = call i8* @__malloc(i32 12)
	%46 = bitcast i8* %45 to %class.node*
	call void @node.build(%class.node* %46)
	%47 = load %class.node, %class.node* %46
	store %class.node %47, %class.node* %44
	%48 = getelementptr %class.node, %class.node* %6, i32 0, i32 2
	%49 = getelementptr %class.node, %class.node* %48, i32 0, i32 0
	%50 = load i32, i32* %3
	store i32 %50, i32* %49
	%51 = getelementptr %class.node, %class.node* %6, i32 0, i32 2
	%52 = getelementptr %class.node, %class.node* %51, i32 0, i32 2
	store %class.node null, %class.node* %52
	br label %_if_next_9

_if_else_8:
	br label %_if_next_9

_if_next_9:
	%53 = getelementptr %class.node, %class.node* %6, i32 0, i32 2
	%54 = load %class.node, %class.node* %53
	store %class.node %54, %class.node* %6
	br label %_while_cond_4

_while_next_6:
	%55 = getelementptr %class.node, %class.node* %6, i32 0, i32 1
	%56 = load i32, i32* %4
	store i32 %56, i32* %55
	ret void

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
