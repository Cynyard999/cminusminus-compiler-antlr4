
其实只有几个地方需要用到fieldList

第一个是函数的传参数那里，这里是paramDec

另一个是struct的内部field，这里是DefList

还有一个地方是args，需要和传参的

他们需要返回一个FieldList变量

exp需要返回一个Type，表示这个exp的计算值

struct A{
    int i, j, k;
    float x, y, z;
};

有可能结构体的第一个就错了，那要继承下去
int i(){}

struct A{
    int i;
    float x, y, z;
};

def也完全可能返回一个null
defList也是

struct A{
    int i;
    float x, y, z;
} a(){
    struct B{
        int j;
        float m,n,i,k;
    } b;
    b.j = 1;
    return b;
};

如果exp返回的是null


为什么不用visitXXX而是使用visit呢，因为如果加上了alias，你并不知道visit哪个

只有浮点数和int才能赋值？