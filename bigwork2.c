#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
// ������ջ�ڵ�ṹ
typedef struct StackNode {
    int data;
    struct StackNode *next;
} StackNode;

// ������ջ�ṹ
typedef struct Stack {
    StackNode *top;
} Stack;

// ��ʼ����ջ
void initStack(Stack *s) {
    s->top = NULL;
}

// ��ջ����
void push(Stack *s, int data) {
    StackNode *newNode = (StackNode *)malloc(sizeof(StackNode));
    newNode->data = data;
    newNode->next = s->top;
    s->top = newNode;
}

// ��ջ����
int pop(Stack *s) {
    if (s->top == NULL) {
        printf("ջΪ�գ��޷���ջ\n");
        return -1; 
    }
    StackNode *temp = s->top;
    int data = temp->data;
    s->top = s->top->next;
    free(temp);
    return data;
}

// �ж�ջ�Ƿ�Ϊ��
int isEmpty(Stack *s) {
    return s->top == NULL;
}

// ��ȡ��������ȼ�
int precedence(char op) {
    if (op == '+' || op == '-')
        return 1;
    if (op == '*' || op == '/')
        return 2;
    return 0;
}

// ִ������
int applyOp(int a, int b, char op) {
    switch (op) {
    case '+': return a + b;
    case '-': return a - b;
    case '*': return a * b;
    case '/': return a / b;
    }
    return 0;
}

// ������ʽ��ֵ
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
    printf("���ʽ %s �Ľ����: %d\n", expression, evaluate(expression));
    getchar();
    return 0;
}