#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<sys/mman.h>
#include<sys/types.h>


int global = 66; //global variable


int main(int argc, char * argv[]){
	pid_t aID;
	int fd;  //18page code.
	caddr_t addr;
	struct stat statbuf;

        if(argc < 2){
          perror("int argc <2 error");
          exit(1);
          }
          
        if(stat(argv[1], &statbuf) == -1){
            perror("stat");
            exit(1);
          }
          
        if(fd = open(argv[1], O_RDWR)== -1){
            perror("open");
            exit(1); 
          }
          
          addr = mmap(NULL, statbuf.st_size, PROT_READ|PROT_WRITE, 
          MAP_SHARED, fd, (off_t)0);
        
        if(addr == MAP_FAILED){
          perror("mmap");
          exit(1);
        }
        
        close(fd); //file close.

	aID=fork();

	switch(aID){
		case 0: //child process.
			printf("child process ID: %d\n", getpid());
			printf("child PPID: %d\n", getppid());
			global = global + 45;
			printf("child global: %d\n", global);
			
			addr[0] = 'x';
			printf("child addr = %s\n", addr);	
			
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
			
			printf("child addr = %s\n", addr);
			
			sleep(10);
			printf("after 10 sec. parent exit(0)\n");
			exit(0);
			break;
			}
		

return 0;
}
