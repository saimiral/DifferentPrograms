#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main(int argc, char *argv[]) {
	system ("chcp 1253");
	double a,b,c,d,r1,r2;
	printf("����� ��� ���� ����������� ���� �������������� ��������\n");
	printf("a = ");
	scanf("%lf",&a);
	printf("b = ");
	scanf("%lf",&b);
	printf("c = ");
	scanf("%lf",&c);
	d = (b*b) - (4*a*c);
	printf("����������� = %.2lf\n",d);
	if(d>0){
		r1 = (-b + sqrt(d))/(2*a);
		r2 = (-b - sqrt(d))/(2*a);
		printf("���� 2 �����!\n");
		printf("�� ����� ����� %.2f ��� %.2f\n",r1,r2);	
	}
	if(d == 0){
		r1 = (-b/2*a);
		printf("���� ��� ����!\n");
		printf("� ���� ����� %.2f\n",r1);
	}
	if(d<0)printf("����� ����!\n");
	return 0;
}





