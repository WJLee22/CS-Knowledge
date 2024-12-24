
#include<sys/types.h> 
#include<sys/stat.h> 
#include<fcntl.h> 
#include<unistd.h> 
#include<stdlib.h> 
#include<stdio.h> 
#include<string.h> 
 
char strArray[56]; 
void createStr(); 
 
int main(){ 

  int fd1,fd2; 
  int n; 
  char buf[80]; 
 
  fd1=open("./NewHansung.txt",O_RDONLY); 
 
  if(fd1==-1){ 
    perror("Error"); 
    exit(1); 
  } 
  fd2=open("./Computer.txt",O_WRONLY | O_CREAT | O_APPEND,0644); 
  
  if(fd2==-1){ 
    perror("Error"); 
    close(fd1); 
    exit(1); 
  } 
  
  printf("fdRead = %d\nfdWrite = %d\n\n", fd1, fd2);
  
  createStr(); // add frontStr to strArray[].  
  
  write(fd2, strArray, strlen(strArray)); 
  write(fd2, "\n", 1); // add \n 
  n = read(fd1,buf,5); 
  write(fd2,buf,n); 
  
  while(n>0){ 
    n = read(fd1,buf,5); 
    write(fd2,buf,n);   
  } 
  close(fd1); 
  close(fd2); 
  printf(" ./Computer.txt created!!\n"); 
  return 0; 
} 

void createStr() { 

int index = 0; 

for (int i = 0; i < 53; i++) { 
      strArray[index++] = '=';  
    } 
 
    const char* str = "\n==== 241008 HansungUniversity Computer LeeWonJun ====\n"; 
 
    strcpy(&strArray[index], str); 
    index += strlen(str); 
 
    for (int j = 0; j < 53; j++) { 
        strArray[index++] = '=';  
    } 
 
    strArray[index] = '\0'; 
} 
