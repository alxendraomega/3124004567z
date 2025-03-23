#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
// 定义链栈节点结构
typedef struct StackNode {
    int data;
    struct StackNode *next;
} StackNode;

// 定义链栈结构
typedef struct Stack {
    StackNode *top;
} Stack;

// 初始化链栈
void initStack(Stack *s) {
    s->top = NULL;
}

// 入栈操作
void push(Stack *s, int data) {
    StackNode *newNode = (StackNode *)malloc(sizeof(StackNode));
    newNode->data = data;
    newNode->next = s->top;
    s->top = newNode;
}

// 出栈操作
int pop(Stack *s) {
    if (s->top == NULL) {
        printf("栈为空，无法出栈\n");
        return -1; 
    }
    StackNode *temp = s->top;
    int data = temp->data;
    s->top = s->top->next;
    free(temp);
    return data;
}

// 判断栈是否为空
int isEmpty(Stack *s) {
    return s->top == NULL;
}

// 获取运算符优先级
int precedence(char op) {
    if (op == '+' || op == '-')
        return 1;
    if (op == '*' || op == '/')
        return 2;
    return 0;
}

// 执行运算
int applyOp(int a, int b, char op) {
    switch (op) {
    case '+': return a + b;
    case '-': return a - b;
    case '*': return a * b;
    case '/': return a / b;
    }
    return 0;
}

// 计算表达式的值
int evaluate(char *exp) {
    Stack values, operators;
    initStack(&values);
    initStack(&operators);

    int i = 0;
    while (exp[i] != '\0') {
        if (isdigit(exp[i])) {
            int val = 0;
            while (isdigit(exp[i])) {
                val = (val * 10) + (exp[i] - '0');
                i++;
            }
            push(&values, val);
            i--;
        } else if (exp[i] == '(') {
            push(&operators, exp[i]);
        } else if (exp[i] == ')') {
            while (operators.top != NULL && operators.top->data != '(') {
                int val2 = pop(&values);
                int val1 = pop(&values);
                char op = pop(&operators);
                push(&values, applyOp(val1, val2, op));
            }
            pop(&operators); 
        } else { 
            while (operators.top != NULL && precedence(operators.top->data) >= precedence(exp[i])) {
                int val2 = pop(&values);
                int val1 = pop(&values);
                char op = pop(&operators);
                push(&values, applyOp(val1, val2, op));
            }
            push(&operators, exp[i]);
        }
        i++;
    }

    while (operators.top != NULL) {
        int val2 = pop(&values);
        int val1 = pop(&values);
        char op = pop(&operators);
        push(&values, applyOp(val1, val2, op));
    }

    return pop(&values);
}
int main() {
    char expression[] = "3+(4*2)";
    printf("表达式 %s 的结果是: %d\n", expression, evaluate(expression));
    getchar();
    return 0;
}