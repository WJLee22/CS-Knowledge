#include <sys/types.h> 
#include <sys/stat.h> 
#include <fcntl.h> 
#include <unistd.h> 
#include <stdlib.h> 
#include <stdio.h> 
 
int main() { 
    int fd1, fd2; 
    int n; 
    char buf[80]; 
 
    fd1 = open("./HansungUniversity.txt", O_RDONLY); 
    if (fd1 == -1) { 
        perror("Error"); 
        exit(1); 
    } 
 
    fd2 = open("./NewHansung.txt", O_WRONLY | O_CREAT | O_TRUNC, 0644); 
    if (fd2 == -1) { 
        perror("Error"); 
        close(fd1); 
        exit(1); 
    } 
 
    printf("fdRead = %d\nfdWrite = %d\n\n", fd1, fd2); 
 
 
    n = read(fd1, buf, 5); 
    write(fd2, buf, n); 
    while (n > 0) { 
        n = read(fd1, buf, 5); 
        write(fd2, buf, n); 
    } 
 
    close(fd1); 
    close(fd2); 
     
    printf(" ./NewHansung.txt created!!\n"); 
    return 0; 
} 
