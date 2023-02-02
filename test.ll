target datalayout = "e-m:e-p:32:32-p270:32:32-p271:32:32-p272:64:64-f64:32:64-f80:32-n8:16:32-S128"
target triple = "i386-pc-linux-gnu"



declare i32 @getInt ()

declare void @print (i8*)

declare void @println (i8*)

declare void @printInt (i32)

declare void @printlnInt (i32)

declare i8* @toString (i32)

declare i8* @getString ()

declare i32 @__string_parseInt (i8*)

declare i32 @__string_ord (i32, i8*)

declare i32 @__string_length (i8*)

declare i8* @__string_substring (i32, i32, i8*)

define void @__mx_initialize_globalVar () {
entry:
 
 
  ret void

}

declare i8* @__malloc (i32)


declare i8 @___str_eq (i8*, i8*)

declare i8 @___str_ne (i8*, i8*)

declare i8 @___str_slt (i8*, i8*)

declare i8 @___str_sle (i8*, i8*)

declare i8 @___str_sgt (i8*, i8*)

declare i8 @___str_sge (i8*, i8*)

declare i8* @___str_add (i8*, i8*)

define i32 @main () {
entry:
 
  %retval = alloca i32
  %.phi = alloca i32
  %graph = alloca i32**
  %.tmp6 = alloca i32**
  %.tmp17 = alloca i32*
  %.tmp26 = alloca i32*
 
  call void @__mx_initialize_globalVar()
  %0 = mul i32 5, 4
  %1 = add i32 %0, 4
  %2 = call i8* @__malloc(i32 %1)
  %3 = bitcast i8* %2 to i32*
  store i32 5, i32* %3
  %4 = bitcast i8* %2 to i32**
  %5 = getelementptr inbounds i32*, i32** %4, i32 1
  store i32** %5, i32*** %.tmp6
  %6 = load i32**, i32*** %.tmp6
  store i32** %6, i32*** %graph
  %7 = load i32**, i32*** %graph
  %8 = getelementptr inbounds i32*, i32** %7, i32 0
  store i32* null, i32** %8
  %9 = load i32**, i32*** %graph
  %10 = getelementptr inbounds i32*, i32** %9, i32 1
  %11 = mul i32 10, 4
  %12 = add i32 %11, 4
  %13 = call i8* @__malloc(i32 %12)
  %14 = bitcast i8* %13 to i32*
  store i32 10, i32* %14
  %15 = bitcast i8* %13 to i32*
  %16 = getelementptr inbounds i32, i32* %15, i32 1
  store i32* %16, i32** %.tmp17
  %17 = load i32*, i32** %.tmp17
  store i32* %17, i32** %10
  %18 = load i32**, i32*** %graph
  %19 = getelementptr inbounds i32*, i32** %18, i32 2
  %20 = mul i32 30, 4
  %21 = add i32 %20, 4
  %22 = call i8* @__malloc(i32 %21)
  %23 = bitcast i8* %22 to i32*
  store i32 30, i32* %23
  %24 = bitcast i8* %22 to i32*
  %25 = getelementptr inbounds i32, i32* %24, i32 1
  store i32* %25, i32** %.tmp26
  %26 = load i32*, i32** %.tmp26
  store i32* %26, i32** %19
  br label %return

return:
  %27 = load i32, i32* %retval
  ret i32 %27
}

