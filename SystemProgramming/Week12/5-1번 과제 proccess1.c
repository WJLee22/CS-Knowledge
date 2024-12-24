#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>

int global = 66; //global variable


int main(int argc, char * argv[]){



	pid_t aID;

	aID=fork();

	switch(aID){
		case 0: //child process.
			printf("child process ID: %d\n", getpid());
			printf("child PPID: %d\n", getppid());
			global = global + 45;
			printf("child global: %d\n", global);
			printf("child exit(0)\n");
			exit(0);
			break;
		case -1: //fork error
			printf("fork error");
			exit(1);
			break;
		default: //parent
			printf("parent process ID: %d\n", getpid());
			printf("parent child ID: %d\n", aID);
			global = global * 2;
			printf("parent global: %d\n", global);
			sleep(10);
			printf("after 10 sec. parent exit(0)\n");
			exit(0);
			break;
			}
		

return 0;
}
