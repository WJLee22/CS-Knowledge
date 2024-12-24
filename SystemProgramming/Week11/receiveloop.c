//signal: process to process. 
#include<stdio.h>
#include<unistd.h>


int main(int argc, char * argv[]){

  while(1){
    sleep(2);
    printf("my process ID = %d \n", getpid());
  }

  return 0; 
}
