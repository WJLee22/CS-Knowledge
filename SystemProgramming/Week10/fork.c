#include<stdio.h>
#include<unistd.h>
#include <stdlib.h>

int main(int argc, char * argv[]){
	
	pid_t aId, bId, fId;
	
	//aId=getpid();
	//bId=getppid();
	//printf("getpid: aId= %d \n",aId);
	//printf("getppid: bId= %d \n",bId);
	
	fId=fork(); //create process
	if(fId==0){ // child process
	
	  printf("Child Process: getpid = %d\n", getpid());
	  printf("Child Process: getppid = %d\n", getppid());
 	  printf("Child Process fId = %d\n", fId);  
 	  exit(0); //process exit.
 	  
	}else if(fId >0){ //parent process
	  
	  printf("Parent Process: getpid = %d\n", getpid());
	  printf("Parent Process: getppid = %d\n", getppid());
 	  printf("Parent Process fId = %d\n", fId);
 	  sleep(10); //parent exit after 10 second.
 	  exit(0); //process exit.
 	  
	}else{  // <0 : fork error
	 
	 perror("fork error");
	  exit(1); //un_normal exit.
	  
	}
	
	
	//printf("fork: fId = %d\n", fId);
        //printf("Hansung ~~~~\n"); //
      
	return 0;
}
