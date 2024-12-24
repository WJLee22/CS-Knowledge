#include<stdio.h>
#include<signal.h>

int main(int argc, char * argv[]){
	
	int pid; //pid_t pid;
	
	printf("process ID: ");
	scanf("%d", &pid); //keyboard input & save in variable pid. 
	
	kill(pid,SIGINT); //kill:  signal sending systemCall.
	
	printf("SIGINT signal process send to %d \n", pid);
	return 0;
	}
