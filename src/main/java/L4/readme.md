
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