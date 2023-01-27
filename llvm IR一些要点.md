From:[Evian-Zhang/llvm-ir-tutorial: LLVM IR入门指南 (github.com)](https://github.com/Evian-Zhang/llvm-ir-tutorial)

官方文档：[About — LLVM 16.0.0git documentation](https://llvm.org/docs/)

Yx：[ZYHowell/Yx at 1c1a74e8e636cf64d2e6f73975cfb2cf50f69cca (github.com)](https://github.com/ZYHowell/Yx/tree/1c1a74e8e636cf64d2e6f73975cfb2cf50f69cca)

[TOC]

我们写编译器的最终目的，是将源代码交给LLVM后端处理，让LLVM后端帮我们优化，并编译到相应的平台。而LLVM后端为我们提供的中介，就是LLVM IR。我们只需要将内存中的AST转化为LLVM IR就可以放手不管了，接下来的所有事都是LLVM后端帮我们实现。

## 数据表示

### 汇编层次的数据表示

```
+------------------------------+
|          stack_data          |
|         heap_pointer         |  <------------- stack
+------------------------------+
|                              |
|                              |  <------------- available memory space
|                              |
+------------------------------+
| data pointed by heap_pointer |  <------------- heap
+------------------------------|
|          global_data         |  <------------- .DATA section
+------------------------------+
```

我们在程序中可以用来表示的数据，一共分为三类：

- 寄存器中的数据
- 栈上的数据
- 数据区里的数据

### llvm IR层次的数据表示

#### 数据区的数据

将需要全程序使用的变量放在数据区中。

一般来说，在LLVM IR中定义一个存储在数据区中的全局变量，其格式为：

```
@global_variable = global i32 0
```

这个语句定义了一个`i32`类型的全局变量`@global_variable`，并且将其初始化为`0`。

如果是只读的全局变量，也就是常量，我们可以用`constant`来代替`global`：

```
@global_constant = constant i32 0
```

这个语句定义了一个`i32`类型的全局常量`@global_constant`，并将其初始化为`0`。

用`private`，则代表这个变量的名字不会出现在符号表中。我们将原来的代码改写成

```
@global_variable = private global i32 0
```

#### 寄存器内的数据和栈上的数据

在不开优化的情况下，一个C的函数中的局部变量（包括传入参数）和返回值都应该存储在函数本身的栈帧中。

LLVM IR引入了虚拟寄存器的概念。在LLVM IR中，一个函数的局部变量可以是寄存器或者栈上的变量。对于寄存器而言，我们只需要像普通的赋值语句一样操作，但需要注意名字必须以`%`开头：

```
%local_variable = add i32 1, 2
```

此时，`%local_variable`这个变量就代表一个寄存器，它此时的值就是`1`和`2`相加的结果。

粗略地理解LLVM IR对寄存器的使用：（calling虚）

- 当所需寄存器数量较少时，直接使用called-saved register，即不需要保留的寄存器
- 当called-saved register不够时，将calling-saved register原本的值压栈，然后使用calling-saved register
- 当寄存器用光以后，就把多的虚拟寄存器的值压栈

LLVM IR对栈的使用十分简单，直接使用`alloca`指令即可。如：

```
%local_variable = alloca i32
```

就可以声明一个在栈上的变量了。



#### 全局变量和栈上的变量皆为指针

全局变量和栈上的变量，LLVM IR把它们都看作指针。也就是说，对于全局变量：

```
@global_variable = global i32 0
```

和栈上变量

```
%local_variable = alloca i32
```

这两个变量实际上都是`i32*`类型的指针，指向它们所处的内存区域。

如果要操作这些值，必须使用`load`和`store`这两个命令。如果我们要获取`@global_variable`的值，就需要

```
%1 = load i32, i32* @global_variable
```

这个指令的意思是，把一个`i32*`类型的指针`@global_variable`的`i32`类型的值赋给虚拟寄存器`%1`。

如果我们要将值存储到全局变量或栈上变量里，会需要`store`命令：

```
store i32 1, i32* @global_variable
```

这个代表将`i32`类型的值`1`赋给`i32*`类型的全局变量`@global_variable`所指的内存区域中。

#### SSA

LLVM IR是一个严格遵守SSA(Static Single Assignment)策略的语言。SSA的要求很简单：每个变量只被赋值一次。

可变变量放到全局变量或者栈内变量里，虚拟寄存器只存储不可变的变量。



## 系统类型

LLVM IR中比较基本的数据类型包括：

- 空类型（`void`）
- 整型（`iN`）
- 浮点型（`float`、`double`等）

整型是指`i1`, `i8`, `i16`, `i32`, `i64`这类的数据类型。这里`iN`的`N`可以是任意正整数。但最常用，最符合常理的就是`i1`以及8的整数倍。`i1`有两个值：`true`和`false`。

#### 符号

有一点需要注意的是，在LLVM IR中，整型默认是有符号整型，也就是说我们可以直接将`-128`以补码形式赋值给`i32`类型的变量。在LLVM IR中，整型的有无符号是体现在操作指令而非类型上的，比方说，对于两个整型变量的除法，LLVM IR分别提供了`udiv`和`sdiv`指令分别适用于无符号整型除法和有符号整型除法：

```
%1 = udiv i8 -6, 2	; get (256 - 6) / 2 = 125
%2 = sdiv i8 -6, 2	; get (-6) / 2 = -3
```

#### 数组

数组类型很简单，我们要声明一个类似C语言中的`int a[4]`，只需要

```
%a = alloca [4 x i32]
```

也就是说，C语言中的`int[4]`类型在LLVM IR中可以写成`[4 x i32]`。注意，这里面是个`x`不是`*`。

我们也可以使用类似地语法进行初始化：

```
@global_array = global [4 x i32] [i32 0, i32 1, i32 2, i32 3]
```

特别地，我们知道，字符串在底层可以看作字符组成的数组，所以LLVM IR为我们提供了语法糖：

```
@global_string = global [12 x i8] c"Hello world\00"
```

在字符串中，转义字符必须以`\xy`的形式出现，其中`xy`是这个转义字符的ASCII码。比如说，字符串的结尾，C语言中的`\0`，在LLVM IR中就表现为`\00`。



## 控制语句

### 汇编层次的控制语句

根据我们在汇编语言中积累的经验，我们得出，要实现大多数高级语言的控制语句，我们需要四个东西：

- 标签
- 无条件跳转
- 比较大小的指令
- 条件跳转

### llvm IR层次的控制语句

#### 标签

以`:`结尾作标记

#### 比较指令

LLVM IR提供的比较指令为`icmp`。其接受三个参数：比较方案以及两个比较参数。

一个最简单的比较指令的例子：

```
%comparison_result = icmp uge i32 %a, %b
```

这个例子转化为C++语言就是

```
bool comparison_result = ((unsigned int)a >= (unsigned int)b);
```

这里，`uge`是比较方案，`%a`和`%b`就是用来比较的两个数，而`icmp`则返回一个`i1`类型的值，也就是C++中的`bool`值，用来表示结果是否为真。

`icmp`支持的比较方案很广泛：

- 首先，最简单的是`eq`与`ne`，分别代表相等或不相等。
- 然后，是无符号的比较`ugt`, `uge`, `ult`, `ule`，分别代表大于、大于等于、小于、小于等于。我们之前在数的表示中提到，LLVM IR中一个整型变量本身的符号是没有意义的，而是需要看在其参与的指令中被看作是什么符号。这里每个方案的`u`就代表以无符号的形式进行比较。
- 最后，是有符号的比较`sgt`, `sge`, `slt`, `sle`，分别是其无符号版本的有符号对应。

#### 条件跳转

LLVM IR为我们提供的条件跳转指令是`br`，其接受三个参数，第一个参数是`i1`类型的值，用于作判断；第二和第三个参数分别是值为`true`和`false`时需要跳转到的标签。比方说，在我们的例子中，就应该是

```
br i1 %comparison_result, label %A, label %B
```

#### 无条件跳转

无条件跳转更好理解，直接跳转到某一标签处。在LLVM IR中，我们同样可以使用`br`进行条件跳转。如，如果要直接跳转到`start`标签处，则可以

```
br label %start
```

#### 一个例子：

```
for (int i = 0; i < 4; i++) {
	// do something A
}
// do something B
```

```
	%i = alloca i32 ; int i = ...
	store i32 0, i32* %i ; ... = 0
	br label %start
start:
	%i_value = load i32, i32* %i
	%comparison_result = icmp slt i32 %i_value, 4 ; test if i < 4=
	br i1 %comparison_result, label %A, label %B
A:
	; do something A
	%1 = add i32 %i_value, 1 ; ... = i + 1
	store i32 %1, i32* %i ; i = ...
	br label %start
B:
	; do something B
```

#### 属性

最后，还有一种叫做属性的概念。属性并不是类型，其一般用于函数。比如说，告诉编译器这个函数不会抛出错误，不需要某些优化等等。我们可以看到

```
define void @foo() nounwind {
	; ...
}
```

这里`nounwind`就是一个属性。

有时候，一个函数的属性会特别特别多，并且有多个函数都有相同的属性。那么，就会有大量重复的篇幅用来给每一个函数说明属性。因此，LLVM IR引入了属性组的概念，我们在将一个简单的C程序编译成LLVM IR时，会发现代码中有

```
attributes #0 = { noinline nounwind optnone ssp uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "darwin-stkchk-strong-link" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "probe-stack"="___chkstk_darwin" "stack-protector-buffer-size"="8" "target-cpu"="penryn" "target-features"="+cx16,+cx8,+fxsr,+mmx,+sahf,+sse,+sse2,+sse3,+sse4.1,+ssse3,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
```

这种一大长串的，就是属性组。属性组总是以`#`开头。当我们函数需要它的时候，只需要

```
define void @foo #0 {
	; ...
}
```

直接使用`#0`即可。



## 函数

### 函数定义

例子：

```
define i32 @foo(i32 %a, i64 %b) {
	ret i32 0
}
```

### 函数调用

使用`call`指令可以像高级语言那样直接调用函数。我们来仔细分析一下这里做了哪几件事：

- 传递参数
- 执行函数
- 获得返回值

例子

```
define i32 @foo(i32 %a) {
	; ...
}

define void @bar() {
	%1 = call i32 @foo(i32 1)
}
```

#### 执行函数

1. 把函数返回地址压栈
2. 跳转到相应函数的地址

#### 传递参数和获得返回值

谈到这两点，就不得不说调用约定了。我们知道，在汇编语言中，是没有参数传递和返回值的概念的，有的仅仅是让当前的控制流跳转到指定函数执行。所以，一切的参数传递和返回值都需要我们人为约定。也就是说，我们需要约定两件事：

- 被调用的函数希望知道参数是放在哪里的
- 调用者希望知道调用函数的返回值是放在哪里的

这就是调用约定。不同的调用约定会产生不同的特效，也就产生了许多高级语言的feature。

**C调用约定**

最广泛使用的调用约定是C调用约定，也就是各个操作系统的标准库使用的调用约定。在x86_64架构下，C调用约定是System V版本的，所有参数按顺序放入指定寄存器，如果寄存器不够，剩余的则从右往左顺序压栈。而返回值则是按先后顺序放入寄存器或者放入调用者分配的空间中，如果只有一个返回值，那么就会放在`rax`里。

在LLVM IR中，函数的调用默认使用C调用约定。为了验证，我们可以写一个简单的程序：

```
; calling_convention_test.ll
%ReturnType = type { i32, i32 }
define %ReturnType @foo(i32 %a1, i32 %a2, i32 %a3, i32 %a4, i32 %a5, i32 %a6, i32 %a7, i32 %a8) {
	ret %ReturnType { i32 1, i32 2 }
}

define i32 @main() {
	%1 = call %ReturnType @foo(i32 1, i32 2, i32 3, i32 4, i32 5, i32 6, i32 7, i32 8)
	ret i32 0
}
```

我们在x86_64架构的macOS上查看其编译出来的汇编代码。在`main`函数中，参数传递是：

```
movl	$1, %edi
movl	$2, %esi
movl	$3, %edx
movl	$4, %ecx
movl	$5, %r8d
movl	$6, %r9d
movl	$7, (%rsp)
movl	$8, 8(%rsp)
callq	_foo
```

而在`foo`函数内部，返回值传递是：

```
movl	$1, %eax
movl	$2, %edx
retq
```

如果大家去查阅System V的指南的话，会发现完全符合。

这种System V的调用约定有什么好处呢？其最大的特点在于，当寄存器数量不够时，剩余的参数是按**从右向左**的顺序压栈。这就让基于这种调用约定的高级语言可以更轻松地实现可变参数的feature。所谓可变参数，最典型的例子就是C语言中的`printf`：

```
printf("%d %d %d %d", a, b, c, d);
```

`printf`可以接受任意数量的参数，其参数的数量是由第一个参数`"%d %d %d %d"`决定的。有多少个需要格式化的变量，接下来就还有多少个参数。

那么，System V的调用约定又是为什么能满足这样的需求呢？假设我们不考虑之前传入寄存器内的参数，只考虑压入栈内的参数。那么，如果是从右往左的顺序压栈，栈顶就是`"%d %d %d %d"`的地址，接着依次是`a`, `b`, `c`, `d`。那么，我们的程序就可以先读栈顶，获得字符串，然后确定有多少个参数，接着就继续在栈上读多少个参数。相反，如果是从左往右顺序压栈，那么程序第一个读到的是`d`，程序也不知道该读多少个参数。

![image-20230124093044407](C:\Users\guoyijin-繁花似锦\AppData\Roaming\Typora\typora-user-images\image-20230124093044407.png)



![image-20221203150019942](C:\Users\guoyijin-繁花似锦\AppData\Roaming\Typora\typora-user-images\image-20221203150019942.png)