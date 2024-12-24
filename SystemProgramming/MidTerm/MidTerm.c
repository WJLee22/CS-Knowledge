#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <string.h>
#include <strings.h>

int main(int argc, char* argv[]){

	if(argc < 3) {
		perror("agrc < 3 error ");
		exit(1);
	}			
	
	char buf[80], pathName[80];
		
	//printf("==========================================\n");
	//printf(" Hansung University, %s, %s \n", argv[1], argv[2]);
	//printf("==========================================\n");

	bzero(buf, sizeof(buf));  //clear	
	strcat(buf, "==========================================\n");
	write(1, buf, strlen(buf));
	
	bzero(buf, sizeof(buf));	
	strcat(buf, " Hansung University, ");
	strcat(buf, argv[1]);
	strcat(buf, ", ");
	strcat(buf, argv[2]);
	strcat(buf, " \n");
	write(1, buf, strlen(buf));

	bzero(buf, sizeof(buf));	
	strcat(buf, "==========================================\n");
	write(1, buf, strlen(buf));
	
	


	bzero(buf, sizeof(buf));	
	strcat(buf, "Source PathName : ");
	write(1, buf, strlen(buf));
	
	
	
	//keyboard input
	bzero(pathName, sizeof(pathName));
	read(0, pathName, sizeof(pathName));  //keyboard input
	pathName[strlen(pathName)-1 ] ='\0';  //enter key delete  '\n' --> '\0'
	
	
	// file open, test.txt, O_RDONLY
	int  fdRead, readNum, fdWrite, nw;
	
	fdWrite=open(pathName, O_CREAT | O_WRONLY| O_EXCL, 0644); // -rw-r--r--	
	if(fdWrite == -1){
		perror("open error");
		exit(1);
	}
	
	// file contents key input
	while(1){
		bzero(buf, sizeof(buf));
		readNum=read(0, buf, sizeof(buf));  //keyboard input
		if(strcmp(buf, "quit\n") ==0){
	 		break;
		}
		
		write(fdWrite, buf, readNum);  //memory write, HDD save  NO
	}
	close(fdWrite);  //File save
	
	bzero(buf, sizeof(buf));	
	strcat(buf, pathName);
	strcat(buf, "  Created !! Thanks ~~ \n");
	write(1, buf, strlen(buf));
	
	bzero(buf, sizeof(buf));	
	strcat(buf, argv[1]);
	strcat(buf, "    ");
	strcat(buf, argv[2]);
	strcat(buf, "\n");
	write(1, buf, strlen(buf));

	return 0;
}
