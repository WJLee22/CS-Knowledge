#include<stdio.h>
#include<unistd.h>
#include<string.h>
#include<strings.h>

int main(int argc, char * argv[]){

  //int fd=open(argv[1], O_WRITE | O_EXCL | O_CREAT, 0644);
  
  int fd;
  
  ////file descripter 0,1,2 open
  char buf[80];
  strcpy(buf, "input data: ");
  //1 descripter = keyboard write-> screen output
    write(1, buf, strlen(buf)); //output= "input data: " 
  
  bzero(buf, sizeof(buf)); //clear buffer.
  
  //0 descripter = keyboard input
  int n=read(0, buf, sizeof(buf));
  
write(1, buf, strlen(buf));

  //printf("buf = %s\n", buf);


return 0;
}
