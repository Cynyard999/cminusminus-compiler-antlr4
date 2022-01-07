没有使用全局变量 但是用不用问题都不大

需要处理连等号



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



struct Score
{
int normalPart;
int hardPart;
};

int getScore(struct Score score)
{
return score.normalPart+score.hardPart;
}

int main(){
struct Score s;
s.normalPart = read();
s.hardPart = read();
write(getScore(s));
return getScore(s);
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
o.o1 = read();
o.o2 = 2;
write(o.o1);
write(o.o2);
return 0;
}


int main(){
int n = read();
while(n<10){
    n = read();
}
return n;
}


struct Operands
{
int o1;
int o2;
};



struct Operands
{
int o1;
int o2;
};
int main(){
struct Operands o[2];
o[0].o1 = 1;
o[0].o2 = 2;
o[1].o1 = 3;
o[1].o2 = 4;
write(o[0].o1);
write(o[1].o2);
return 0;
}