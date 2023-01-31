@a = global i32 1

define i32 func() {
	ret i32 0

}
define i32 main() {
	%1 = alloca i32
	store i32 1, i32* %1
	%2 = alloca i32
	store i32* %1, i32* %2
	br label %1

1:
	%3 = alloca i32
	store i32 0, i32* %3
	%4 = load i32*, i32** %3
	%5 = load i32, i32* 10
	%6 = icmp slt i1 %4, %5
	%7 = zext i1 %6 to i32
	br i32 %7, label %2, label %3

2:
	%8 = load i32*, i32** %3
	%9 = add nsw i32 %8, 1
	store i32 %9, i32* %3
	%10 = load i32*, i32** %1
	%11 = add nsw i32 %10, 1
	store i32 %11, i32* %1
	br label %1

3:
	ret i32 0

}
