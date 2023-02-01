@a = global [1 x i8]* @str_0

define i32 @func() {
	%1 = alloca i32
	%3 = add nsw i32 %1, 1
	store i32 %3, i32 %4
	ret i32 %4

}

define i32 @main() {
	%1 = alloca i32
	%2 = load i32, i32*@a
	%3 = add nsw i32 %2, 1
	store i32 %3, i32*@a
	store i32 %2, i32* %1
	br label %_while_cond_1

_while_cond_1:
	%4 = load i32, i32*@a
	%5 = load i32, i32* %1
	%6 = icmp slt i32 %4, %5
	br i1 %6, label %while_loop_2, label %_while_next_3

while_loop_2:
	%8 = load i32, i32* %1
	%7 = call i32 @func(i32 %8)
	store i32 %7, i32* %1
	br label %_while_cond_1

_while_next_3:
	ret i32 0

}

