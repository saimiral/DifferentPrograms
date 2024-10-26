#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
	system ("chcp 1253");
	
	int step = 0;
	float x;
	for(x= 0.0; x<10.0; x+=0.1){
		printf("Ο αριθμός βήματος είναι %d \t",step);
		step++;
		
		printf("Η τιμή της μεταβλητής είναι %f \n",x);
	}
	return 0;
}
