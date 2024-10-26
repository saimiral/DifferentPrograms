#include <stdio.h>
#include <stdlib.h>
#include <math.h>


int main(int argc, char **argv){
	
	system("chcp 1253");
		
	int a,b;
	printf("1)  Δώστε 2 ακέραιους αριθμούς\n");
	scanf("%d  ",&a);
	scanf("%d",&b);
	double c = (double)a / b;
	printf("a + b = %d\n\n",a+b);
	printf("a - b = %d\n\n",a-b);
	printf("a * b = %d\n\n",a*b);
	printf("a div b = %d\n\n",a/b);
	printf("a mod b = %d\n\n",a%b);
	printf("a / b = %.2lf\n\n",c); 
	printf("Το τετράγωνο του πρώτου αριθμού είναι %.2lf\n\n",pow(a,2));
	printf("Η τετραγωνική ρίζα του δεύτερου αριθμού είναι %.2lf\n\n",sqrt(b));


	double edge,area,volume,radius,area2,volume2;
	printf("2)  Δώστε την ακμή του κύβου σε μέτρα\n");
	scanf("%lf",&edge);
	area = 4 * pow(edge,2); 
	printf("Το εμβαδόν του κύβου είναι %.2lf m^2\n",area);
	volume = pow(edge,3);
	printf("Ο όγκος του κύβου είναι %.2lf m^3\n",volume);
	printf("Τώρα για ακμή και όγκο σφαίρας...\n");
	printf("Η ακτίνα της σφαίρας είναι ίση με την ακμή του κύβου\n");
	radius = edge;
	area2 = 4 * 3.14 * pow(radius,2);
	volume2 = (4 * 3.14 * pow(radius,3)) / 3;
	printf("Το εμβαδόν της σφαίρας είναι %.2lf m^2\n",area2);
	printf("Ο όγκος της σφαίρας είναι %.2lf m^3\n\n",volume2);
	
	double side1,side2,side3,s,area1;
	printf("3) Δώστε τα μήκη πλευρών του τριγώνου σε εκ.\n");
	scanf("%lf  ",&side1);
	scanf("%lf  ",&side2);
	scanf("%lf",&side3);
	printf("Για να βρούμε το εμβαδόν του τριγώνου έχοντας μόνο τα μήκη πλευρών του θα χρειαστούμε τον τύπο του Ήρωνα\n");
	printf("Ο τύπος είναι:A = sqrt(s(s-a)(s-b)(s-c)), όπου 's' η ημιπερίμετρος που δίνεται από τον τύπο s = (a + b + c)/2\n");
	s = (side1 + side2 + side3)/2.0;
	area1 = sqrt(s*(s-side1)*(s-side2)*(s-side3));
	printf("Το εμβαδόν του τριγώνου είναι %.2lf m^2'",area1);
	return 0;
}
