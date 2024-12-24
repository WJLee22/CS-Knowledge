 #include<fcntl.h>
 #include<unistd.h>
 #include<stdlib.h>
 #include<stdio.h>
 
 int main(){
 	int fd,n;
 	char buf[3];
 	
 	fd=open("./HansungUniversity.txt",O_RDONLY);
 	
 	if(fd==-1){
 		perror("Open error");
 		exit(1);
 	}
 	
 	printf("fdRead=%d\n",fd);
 	
 	while(1){
 	  n=read(fd,buf,3); 
 	        if(n==-1){
 	         perror("Read error");
 	         exit(1);
 	         }
 	         else if(n==0) 
 	            break;
 	            
		 printf("%s ",buf);
 	}
 	close(fd);
 	return 0;
 }
