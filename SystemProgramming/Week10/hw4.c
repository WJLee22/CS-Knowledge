#include <stdio.h>
#include <stdlib.h>

extern char _etext;  // End of the text (code) segment
extern char _edata;  // End of the data segment
extern char _end;    // End of the BSS segment

int global_var = 10;             // Initialized global variable (data segment)
static int static_uninitialized; // Uninitialized static variable (BSS segment)

int main() {
    printf("Code (text) segment end address (_etext):     %p\n", &_etext);
    printf("Data segment end address (_edata):            %p\n", &_edata);
    printf("BSS segment end address (_end):               %p\n", &_end);
    printf("\n");
    ////////local variables
    int stackLocal1  = 10; 
    int stackLocal2  = 20;    
    int stackLocal3  = 30;    
    
    printf("Address of stackLocal1 (Stack segment):       %p\n", &stackLocal1);
    printf("Address of stackLocal2 (Stack segment):       %p\n", &stackLocal2);
    printf("Address of stackLocal3 (Stack segment):       %p\n", &stackLocal3);
        printf("\n");
    ////////////////////
    
    int *heapAddr1, *heapAddr2, *heapAddr3;
    heapAddr1 = (int*) malloc(sizeof(int));
    heapAddr2 = (int*) malloc(sizeof(int));
    heapAddr3 = (int*) malloc(sizeof(int));
    printf("Address of heapAddr1 (Heap segment):          %p\n", heapAddr1);
    printf("Address of heapAddr2 (Heap segment):          %p\n", heapAddr2);
    printf("Address of heapAddr3 (Heap segment):          %p\n", heapAddr3);
    printf("\n");
    static int dataGlobal1=1; // initialized static variable (data segment)
    static int dataGlobal2=2; // initialized static variable (data segment)
    static int dataGlobal3=3; // initialized static variable (data segment)
    
    static int bssGlobal1; // Uninitialized static variable (BSS segment)
    static int bssGlobal2; // Uninitialized static variable (BSS segment)
    static int bssGlobal3; // Uninitialized static variable (BSS segment)   
    
    
    printf("Address of dataGlobal1 (data segment):        %p\n", &dataGlobal1);
    printf("Address of dataGlobal2 (data segment):        %p\n",&dataGlobal2);   
    printf("Address of dataGlobal3 (data segment):        %p\n", &dataGlobal3);
    printf("\n");
    
    printf("Address of bssGlobal1 (BSS segment):          %p\n",&bssGlobal1);
    printf("Address of bssGlobal2 (BSS segment):          %p\n",&bssGlobal2);
    printf("Address of bssGlobal3 (BSS segment):          %p\n",&bssGlobal3);
    printf("\n");
    
    return 0;
}
