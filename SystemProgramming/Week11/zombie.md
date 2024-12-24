```c
#include<stdio.h>
#include<stdlib.h>
#include<sys/types.h>
#include<unistd.h>

int main(int argc, char* argv[]){

	pid_t aID;
	
	aID=fork();
	
	switch(aID){
	
	case 0: 
	  printf("child pid=%d \n", getpid()); 
	  printf("child ppid=%d \n", getppid());
	  printf("child aID=%d \n", aID);
	  printf("child process EXIT(0)\n");  
	  exit(0); // child ended before parent ended;
	  //break;
	
	case -1: 
	  perror("fork error");
	  exit(0); 
	
	default: 
	  printf("parent pid=%d \n", getpid());
	  printf("parent ppid=%d \n", getppid());
	  printf("parent aID=%d \n", aID);
	  sleep(30);
	  printf("parent process EXIT(0)\n"); 
	  exit(0); 
	}
	return 0;
}
```

한 터미널에서 ./zombie.out 으로 zombie 프로그램 실행.    
> 이 프로세스는, 부모-자식이 각각 pid ppid를 출력하는데,  
> 출력 이후, 자식은 곧바로 exit() 하여 종료되고   
> 부모는 30초 sleep뒤에 exit() 하여 자식 -> 부모순으로 정상적인 프로세스 종료순서이다.  
> 하지만 여기서, 부모는 sleep()으로 잠들어있는동안 자식이 종료되었다는 신호를 받지못하게된다.  
> 그래서 부모의 프로세스 테이블에서 그 자식프로세스가 사라지지않게되어 자식은 좀비프로세스로 메모리에 존재하게된다.  
> 다른 터미털 창에서 ps -ef | grep 자식pid  명령어를 입력하면 [zombie.out] <defunct> 와 같이 뜨는걸 확인가능하다.  


```
좀비 프로세스는 자식 프로세스가 실행 종료되었지만,
부모 프로세스가 wait() 시스템 호출을 통해 자식 프로세스의 종료 상태를 확인하지 않아서
메모리에 남아있는 프로세스를 말한다.
```
#### 부모 프로세스가 종료될때, 자신의 테이블에 기록된 이 좀비 프로세스를 정상종료 시킴으로써 좀비 프로세스가 종료되어진다.  

```
웹서버 같은 서버프로그램은 서비스를 제공하기위해 종료되지않고 계속 실행된다.  
즉, 서버 프로세스 - parent 프로세스는 종료되지않는다.
그렇기때문에, 서버 프로세스 실행중에 발생한 좀비 프로세스는 정상종료되어지지않고  
계속 프로세스로 남아있는 문제 발생.
좀비 프로세스의 종료는 부모의 종료시점에 부모가 종료해줘야하니깐. 근데 서버는 종료가 안됨.
```
