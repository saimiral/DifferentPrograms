#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define PI 3.141592

int main(int argc, char *argv[]) {
	system ("chcp 1253");
	
	int i,prefix=1; // πρόσημο όρου
	double sinx,prev,next,x; // αποτέλεσμα της σειράς, ο προηγούμενος/επόμενος όρος στη σειρά και η γωνία x 
	
	do{
		printf("Δώσε μου την τιμή της μεταβλητής x σε μοίρες: ");
		scanf("%lf",&x);
	}while(x < 1 || x > 360);
	
	x = x * PI / 180.0;   // από Deg σε Rad
	prev = x;  // πρώτος όρος είναι το x^1 / 1! = x  
	sinx = x;  // το ίδιο 
	
	for(i=3; ; i+=2){
		next = prev * ((x*x) / (i*(i-1)));
		
		if(prefix == 1) sinx -= next;            // πρόσημο όρου + 
		if(prefix == -1) sinx += next;           // πρόσημο όρου -
		prefix *= -1; // αλλαγή του προσήμου σε κάθε όρο
		
		if(prev - next < 0.000001 && next - prev < 0.000001) break;
		
		prev = next;
	}
	
	printf("Το ημίτονο του x είναι %lf\n",sinx);
	printf("Η συνάρτηση της math.h δίνει: %lf\n",sin(x));
	return 0;
}
