# 说明



- 在构造的时候，如果一个rule被识别成了空，但是还是会在parse tree上，如何在打印的时候忽略这个节点
- 或者是如何在构建的时候就不构建这个节点，或者是设计语法的时候尽量减少这样节点的出现
- visitor模式以及listener模式的区别，listener的触发和visitor的触发点
- if else太蠢了，可以每个规则设计一个alias，但是这样也很麻烦来着 问题就在于识别目前是进入到了哪个rule alternative中
- 需要分清各个类以及各个接口的区别 很复杂
- ParseTreeVisitor里面有visitTerminal()方法

``` java

public T visitChildren(RuleNode node) {
    T result = defaultResult();
    int n = node.getChildCount();
    for (int i=0; i<n; i++) {
        if (!shouldVisitNextChild(node, result)) {
            break;
        }

        ParseTree c = node.getChild(i);
        T childResult = c.accept(this);
        result = aggregateResult(result, childResult);
    }

    return result;
}

```







accept会调用visitor，不同类型的ParseTree有不同的实现，Rule类型的xxxContext，Antlr帮忙构造了
Terminal类型的，会调用默认的accept，在TerminalNodeImpl里面
可以重写visitTerminal，也可以不重写，不重写默认返回的就是null，但不能通过terminalNode获取depth来着
重写visitTerminal个人觉得没啥用，因为这个要么自己显示调用，要么就是默认的在visitChildren的时候用accept调用
想要默认调用的话，还得调用一遍visitChildren，所以就算了，显示调用的话，缺少depth，还不如新建一个函数


TerminalNode就比ParseTree多了一个Symbol
如果通过getChild只能得到ParseTree

继承 接口

注意代码的冗余控制

# 错误恢复

每个规则对应的函数都有一个错误回复机制 `_errHandler.reportError(this, re);`

首先是通过errHandler（默认是DefaultErrorStrategy）的默认方法，通过判断re的类型，然后得到不同的msg，然后再调用this.notifyErrorListeners，也就是parser中的notifyErrorListeners
然后通过parser的默认listener（也就是ConsoleErrorListener）在parser中调用他的syntaxError

（lexer会简单一点，lexer里面没有errHandler，遇到错误直接notifyListeners（默认只有这个ConsoleErrorListener），然后调用listener的方法syntaxError）

parser和lexer中都使用了ProxyErrorListener来代理，方便一个lexer或者一个parser有多个listener


_errHandler is a variable holding a reference to an instance of DefaultErrorStrategy. 
Methods reportError and recover embody the error reporting and sync-and-return functionality.


# 注意事项
- 每行只用打印一个错误，如果有多个错误也只用打印一个
- Antlr在构造树的时候，如果一个rule进入了empty分支，也会在树中给这个rule构造一个节点，但是他的children是空的，打印树的时候请避免打印这种类型的节点

# 样例

1. 
float sub(){
  return 10 - 5.5e-3;     
}

Program (1)
  ExtDef (1)
    Specifier (1)
      TYPE: float
    FunDec (1)
      ID: sub
      LP
      RP
    CompSt (1)
      LC
      StmtList (2)
        Stmt (2)
          RETURN
          Exp (2)
            Exp (2)
              INT: 10
            MINUS
            Exp (2)
              FLOAT: 0.005500
          SEMI
      RC

2.
int add(int a, int b){
    a[1][2][3] = 0;
    return a+b;
}

Program (1)
  ExtDef (1)
    Specifier (1)
      TYPE: int
    FunDec (1)
      ID: add
      LP
      VarList (1)
        ParamDec (1)
          Specifier (1)
            TYPE: int
          VarDec (1)
            ID: a
        COMMA
        ParamDec (1)
          Specifier (1)
            TYPE: int
          VarDec (1)
            ID: b
      RP
    CompSt (1)
      LC
      StmtList (2)
        Stmt (2)
          Exp (2)
            Exp (2)
              Exp (2)
                Exp (2)
                  Exp (2)
                    ID: a
                  LB
                  Exp (2)
                    INT: 1
                  RB
                LB
                Exp (2)
                  INT: 2
                RB
              LB
              Exp (2)
                INT: 3
              RB
            ASSIGNOP
            Exp (2)
              INT: 0
          SEMI
        Stmt (3)
          RETURN
          Exp (3)
            Exp (3)
              ID: a
            PLUS
            Exp (3)
              ID: b
          SEMI
      RC


3. 
struct YHgzGWi_utk5ll04F6MV jTwiUq3NhNmoi ( ) {
};

int main(){
  int i;
  int j,
}

Error type B at Line 2: extraneous input ';' expecting {<EOF>, 'struct', TYPE}
Error type B at Line 7: mismatched input '}' expecting ID

4. 
int main(){
     int a[1][2]
     [1.1]= 3;
     int i = 0;
     int j = 1;
}

Error type B at Line 3: array size must be an integer constant, not 1.1
   
# 一些用例


```c

// 1
int main() {
int 
a
[
1
]
[
2.2
]
[
n
]
[
m
]
;
}
```







