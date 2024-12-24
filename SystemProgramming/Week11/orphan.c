#include<stdio.h>
#include<stdlib.h>
#include<sys/types.h>
#include<unistd.h>

int main(int argc, char* argv[]){

	//current process: parent process
	//single process -> parent process. 

	pid_t aID;
	
	aID=fork();
	
	switch(aID){
	
	case 0:  //child process.
	  printf("child pid=%d \n", getpid()); //child's pid
	  printf("child ppid=%d \n", getppid());//child's ppid=parent's id.
	  printf("child aID=%d \n", aID);
	  sleep(15); //orphan process. 5second before this moment, parent is ended.
	  printf("child pid=%d \n", getpid()); //child's pid
	  printf("child ppid=%d \n", getppid());//child's ppid is modified, parent process id -> init process id. because, parent process is ended before child ended.
	  printf("child aID=%d \n", aID);
	  
	  exit(0);
	  //break;
	
	case -1: 
	  perror("fork error");
	  exit(0); 
	
	default: //parent process
	  printf("parent pid=%d \n", getpid()); //parent's pid
	  printf("parent ppid=%d \n", getppid());//parent's ppid=grandparent's id.
	  printf("parent aID=%d \n", aID);
	  sleep(10);
	  exit(0); //parent process ended. life end.die.
	}
	return 0;
}
