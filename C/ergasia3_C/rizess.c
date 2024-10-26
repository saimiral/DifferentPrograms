#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main(int argc, char *argv[]) {
	system ("chcp 1253");
	double a,b,c,d,r1,r2;
	printf("Δώστε μου τους συντελεστές μίας δευτεροβάθμιας εξίσωσης\n");
	printf("a = ");
	scanf("%lf",&a);
	printf("b = ");
	scanf("%lf",&b);
	printf("c = ");
	scanf("%lf",&c);
	d = (b*b) - (4*a*c);
	printf("Διακρίνουσα = %.2lf\n",d);
	if(d>0){
		r1 = (-b + sqrt(d))/(2*a);
		r2 = (-b - sqrt(d))/(2*a);
		printf("Έχει 2 ρίζες!\n");
		printf("Οι ρίζες είναι %.2f και %.2f\n",r1,r2);	
	}
	if(d == 0){
		r1 = (-b/2*a);
		printf("Έχει μία ρίζα!\n");
		printf("Η ρίζα είναι %.2f\n",r1);
	}
	if(d<0)printf("Καμία ρίζα!\n");
	return 0;
}





