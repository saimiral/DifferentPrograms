#include <stdio.h>
#include <stdlib.h>
#define PI 3.141592

void menu();
void Syn1();
void Syn2();
void Syn3();
void Syn4();
void Syn5();
double sin_x(double);
double cos_x(double);
int myPow(int,int);
int combinations(int, int);
int fact(int);
double mesos_oros(int);

int main(int argc, char *argv[]) {
	system ("chcp 1253");
	
	menu();
	return 0;
}

void menu(){
	int c;

	do{
		system ("cls");
		printf("==== Επιλογές ====\n");
		printf("[1]   Υπολογισμός ημιτόνου\n");
		printf("[2]   Υπολογισμός συνημιτόνου\n");
		printf("[3]   Υπολογισμός δύναμης\n");
		printf("[4]   Υπολογισμός πλήθους συνδυασμών\n");
		printf("[5]   Υπολογισμός μέσου όρου\n");
		printf("[6]   Έξοδος\n");
		printf("=================\n\n");
		printf("Τι θα θέλατε να κάνω;\n");
		scanf("%d",&c);
		getchar();
		switch(c){
			case 1: Syn1(); break;
			case 2: Syn2(); break;
			case 3: Syn3(); break;
			case 4: Syn4(); break;
			case 5: Syn5(); break;
			case 6:	printf("Έξοδος\n");	break;
		}
	}while(c != 6);
}

void Syn1(){
	double sinx,x; // αποτέλεσμα της σειράς και η γωνία x 
	
	do{
		printf("Δώσε μου την τιμή της μεταβλητής x σε μοίρες: ");
		scanf("%lf",&x);
	}while(x < 1 || x > 360);
	
	sinx = sin_x(x);
	printf("Το ημίτονο του x είναι %lf\n",sinx);
	system ("pause");	
}
void Syn2(){
	double cosx,x; // αποτέλεσμα της σειράς και η γωνία x 
	
	do{
		printf("Δώσε μου την τιμή της μεταβλητής x σε μοίρες: ");
		scanf("%lf",&x);
	}while(x < 1 || x > 360);
	
	cosx = cos_x(x);
	
	printf("Το συνημίτονο του x είναι %lf\n",cosx);
	system ("pause");
}
void Syn3(){
	int b,e,res;
	printf("Δώστε μου την βάση\n");
	scanf("%d",&b);
	printf("Δώστε μου τον εκθέτη\n");
	scanf("%d",&e);
	res = myPow(b,e);
	printf("%d στην %dη είναι %d\n",b,e,res);
	system ("pause");
}
void Syn4(){
	int n,k,C;
	printf("Δώστε μου το Ν\n");
	scanf("%d",&n);
	printf("Δώστε μου το Κ\n");
	scanf("%d",&k);
	C = combinations(n, k); 
	printf("Υπάρχουν %d συνδυασμοί %d ανά %d\n",C,n,k);
	system ("pause");
}

void Syn5(){
	int N,avg;
		
	printf("Πόσους αριθμούς δώσατε;\n");
	scanf("%d",&N);
	avg = mesos_oros(N);
	
	printf("Ο μέσος όρος των αριθμών που δώσατε είναι: %.2lf\n",avg);
	system ("pause");
}

double sin_x(double x){
	int i,prefix=1; // πρόσημο όρου
	double sinx,prev,next; //αποτέλεσμα, προηγούμενος και επόμενος όρος
	
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
	return sinx;
}

double cos_x(double x){
	int i,prefix=1; //πρόσημο όρου
	double cosx,prev,next; //αποτέλεσμα, προηγούμενος και επόμενος όρος
	
	x = x * PI / 180.0;   // από Deg σε Rad
	prev = 1;  // πρώτος όρος είναι το x^0 / 0! = 1  
	cosx = 1;  // το ίδιο 
	
	for(i=2; ; i+=2){
		next = prev * ((x*x) / (i*(i-1)));
		
		if(prefix == 1) cosx -= next;            // πρόσημο όρου + 
		if(prefix == -1) cosx += next;           // πρόσημο όρου -
		prefix *= -1; // αλλαγή του προσήμου σε κάθε όρο
		
		if(prev - next < 0.000001 && next - prev < 0.000001) break;
		
		prev = next;
	}
	return cosx;
}

int myPow(b,e){
	int res=1,i;
	
	for(i=0; i<e; i++) res *= b;
	return res;
}

int combinations(n, k){
	int c = fact(n)/(fact(k) * fact(n - k));
	
	return c;
}

int fact(N){
	if(N == 0 || N == 1) return 1;
	else{
		N *= fact(N-1);
		return N;
	}
}

double mesos_oros(N){
	int i,prod=1;
	double avg;
	
	for(i=0; i<=N; i++){
		prod *= *argv[i];
	}
	avg = (double )prod / N; // Το sum δεν γίνεται να είναι 0 από περιορισμό
	return avg;
}
