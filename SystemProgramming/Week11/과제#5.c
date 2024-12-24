#include<stdio.h>
#include<sys/types.h>
#include<unistd.h>
#include<stdlib.h>

int main(int argc, char * argv[]){

pid_t aID,bID,cID;
aID=fork();

switch(aID){

  case 0:  //child#1 process.
    printf("child#1 pid= %d \n", getpid());
    printf("child#1 ppid= %d \n", getppid());
    printf("child#1 aID= %d \n", aID);
    sleep(5);
    printf("\nafter 5 second !! child#1 process EXIT(0) \n");
    exit(0);
  
  case -1:
    perror("aId=fork() error");
    exit(1); //process exit
    
  default: //parent process

        bID = fork();
          

    switch(bID){
      case 0: 
          printf("child#2 pid= %d \n", getpid());
          printf("child#2 ppid= %d \n", getppid());
          printf("child#2 bID= %d \n", bID);
          sleep(5);
          printf("\nafter 5 second !! child#2 process EXIT(0) \n");
          exit(0);

      case -1:
        perror("aId=fork() error");
        exit(1); //process exit
        
        default: //parent process
          cID = fork();
          switch(cID){
            case 0: 
              printf("child#3 pid= %d \n", getpid());
              printf("child#3 ppid= %d \n", getppid());
              printf("child#3 cID= %d \n", cID);
              sleep(10);
              printf("\nafter 10 second !! child#3 process EXIT(0) \n");
              exit(0);
              
            case -1:
              perror("aId=fork() error");
              exit(1); //process exit
              
            default: 
              printf("parent pid= %d \n", getpid());
              printf("parent ppid= %d \n", getppid());
              printf("parent child#1 aID= %d\n", aID);
              printf("parent child#2 bID= %d\n", bID);
              printf("parent child#3 cID= %d\n", cID);
              sleep(60);
              printf("\nafter 60 second !! parent process EXIT(0) \n");
              exit(0);              

            }
            
            }

            }
return 0;
}
