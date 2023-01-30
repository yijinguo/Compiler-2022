a=@ab=@b
define int main(i32** %2, i32 %14) {
3:
	%15 = load i32, i32* %14
	%16 = load i32, i32* 1
	%17 = icmpeq i1 %15, %16
	%18 = load i8, i1* %17
	%19 = snez i1 %18, 0
	br i1 %19, label %6, label %7

5:
	ret i32 0

	%3 = load i32**, i32*** %2
	%4 = getelementptr i32**, i32** %3, i32 0
	%5 = load i32*, void* null
	store i32** %4, i32**%5
	%6 = load i32**, i32*** %2
	%7 = getelementptr i32**, i32** %6, i32 1
	%8 = call operator new[](unsigned int), i32
	%9 = load i32*, i32** %8
	store i32** %7, i32**%9
	%10 = load i32**, i32*** %2
	%11 = getelementptr i32**, i32** %10, i32 2
	%12 = call operator new[](unsigned int), i32
	%13 = load i32*, i32** %12
	store i32** %11, i32**%13
	%14 = alloca i32
	store i32 %14, i32*0
	br label %3

4:
	br label %4

}
%class.A = type { }
define int A::f() {
	%1 = load i32, i32* @a
	store i32@b, i32*%1
	ret i32@b

}
