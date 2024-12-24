#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <string.h>
#include <strings.h>

int main(int argc, char * argv[]){

char buf[200], pathName[100];

if(argc < 3){
perror("argc < 3 error : ");
exit(1);
}

bzero(buf, sizeof(buf));
for(int i=0; i<42; i++){
  strcat(buf,"=");
}
strcat(buf, "\n ");
strcat(buf, "Hansung University, ");
strcat(buf, argv[1]);
strcat(buf, ", ");
strcat(buf, argv[2]);
strcat(buf, "\n");
for(int i=0; i<42; i++){
  strcat(buf,"=");
}

write(1,buf,strlen(buf));

bzero(buf, sizeof(buf));
strcpy(buf,"\nSource PathName : ");
write(1,buf,sizeof(buf));

bzero(pathName, sizeof(pathName));
read(0,pathName,sizeof(pathName));
pathName[strlen(pathName)-1]='\0';
int fdWrite;

fdWrite=open(pathName, O_WRONLY | O_APPEND);
	if(fdWrite == -1){
		perror("open error ");
		exit(1);
	}

while(1){
bzero(buf,sizeof(buf));
read(0,buf,sizeof(buf));

if(strcmp(buf,"quit\n")==0)
  break;

write(fdWrite,buf,strlen(buf));
}
bzero(buf,sizeof(buf));
strcat(buf,pathName);
strcat(buf," Appended !! Thanks ~~\n");
write(1,buf,sizeof(buf));
write(1,argv[1],strlen(argv[1]));
write(1,"    ",5);
write(1,argv[2],strlen(argv[2]));
write(1,"\n",strlen(argv[2]));

close(fdWrite);

return 0;
}
