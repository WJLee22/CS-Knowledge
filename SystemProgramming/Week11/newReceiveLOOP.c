//signal: process to process. 
#include<stdio.h>
#include<unistd.h>
#include<signal.h>

void sig_handler(int signo){
    //do it. 
    printf("received signal = %d \n", signo);
    printf(" not exit~~~~~~");
    return;
}

int main(int argc, char * argv[]){
  
  signal(SIGINT, sig_handler);
  
  
  while(1){
    sleep(2);
    printf("my process ID = %d \n", getpid());
  }

  return 0; 
}
