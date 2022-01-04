
对结构体内成员 以及数组元素的访问 肯定有一个target
如果类似如下的代码：

x.a这一行都不用打印出来

struct A{
int a;
}
int main(){
    struct A x;
    x.a;
}


struct A{
int a[10];
int b;
}x;
int main(){
return x.a[1];
}

int main(){
struct A{
    struct B{int c;} b;
}a ;
a.b.c = 1;
return a.b.c;
}

结构体数组有可能出现吗？
结构体的嵌套域？



struct Operands
{
int o1;
int o2;
};

int add(struct Operands temp)
{
int n = read();
temp.o1 = 1;
return temp.o1+n;
}

int main(){
struct Operands temp2;
temp2.o1 = 1;
return 0;
}


int main(){
int a[10];
a[2] = read();
write(a[2]);
return a[1];
}



struct Operands
{
int o1;
int o2;
};
int main(){
struct Operands o;
o.o1 = 1;
o.o2 = 2;
write(o.o1);
return 0;
}