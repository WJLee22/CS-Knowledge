#include<unistd.h>
#include<stdio.h>
#include<stdlib.h>

int main(int argc, char * argv[]){
	
	pid_t pid, ppid;
	
	pid=getpid();//process id. if <0 -> error.
	if(pid<0){
		perror("getpid() error");
		exit(1);
	}
	
	ppid=getppid();
		if(ppid<0){
		perror("getppid() error");
		exit(1);
	}
	
	printf("pid = %d \n", pid);
	printf("ppid = %d \n", ppid);

	return 0;

}
