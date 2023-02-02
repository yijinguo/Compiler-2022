#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void print(char *str) { printf("%s", str); }

void println(char *str) { printf("%s\n", str); }

void printInt(int x) { printf("%d", x); }

void printlnInt(int x) { printf("%d\n", x); }

char *getString(){
    char *str = (char *) malloc(sizeof(char) * (1<<8));
    scanf("%s", str);
    return str;
}

int getInt() {
    int x;
    scanf("%d", &x);
    return x;
}

char *toString(int i) {
    char *str = (char *) malloc(sizeof(char) * (1<<8));
    sprintf(str, "%d", i);
    return str;
}


int __mx_length(char *str){ return strlen(str); }

char *__mx_substring(int left, int right, char *str){
    int len = right - left + 1;
    char *substr = (char *) malloc(sizeof(char) * len);
    memcpy(substr, str + left, len);
    substr[len - 1] = '\0';
    return substr;
}

int __mx_parseInt(char *str){
    int x;
    sscanf(str, "%d", &x);
    return x;
}

int __mx_ord(int pos, char *str){
    return str[pos];
}

char __str_eq(char *s1, char *s2) { return strcmp(s1, s2) == 0; }
char __str_ne(char *s1, char *s2) { return strcmp(s1, s2) != 0; }
char __str_slt(char *s1, char *s2) { return strcmp(s1, s2) < 0; }
char __str_sle(char *s1, char *s2) { return strcmp(s1, s2) <= 0; }
char __str_sgt(char *s1, char *s2) { return strcmp(s1, s2) > 0; }
char __str_sge(char *s1, char *s2) { return strcmp(s1, s2) >= 0; }
char *__str_add(char *s1, char *s2) {
    char *str = (char *) malloc(sizeof(char) * (strlen(s1) + strlen(s2) + 1));
    strcpy(str, s1);
    strcat(str, s2);
    return str;
}

char *__malloc(int size) {
    return (char *) malloc(size);
}