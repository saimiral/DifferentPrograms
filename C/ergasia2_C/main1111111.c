#include <stdio.h>
#include <stdlib.h>
#include <math.h>


int main(int argc, char **argv){
	
	system("chcp 1253");
		
	int a,b;
	printf("1)  ����� 2 ��������� ��������\n");
	scanf("%d  ",&a);
	scanf("%d",&b);
	double c = (double)a / b;
	printf("a + b = %d\n\n",a+b);
	printf("a - b = %d\n\n",a-b);
	printf("a * b = %d\n\n",a*b);
	printf("a div b = %d\n\n",a/b);
	printf("a mod b = %d\n\n",a%b);
	printf("a / b = %.2lf\n\n",c); 
	printf("�� ��������� ��� ������ ������� ����� %.2lf\n\n",pow(a,2));
	printf("� ����������� ���� ��� �������� ������� ����� %.2lf\n\n",sqrt(b));


	double edge,area,volume,radius,area2,volume2;
	printf("2)  ����� ��� ���� ��� ����� �� �����\n");
	scanf("%lf",&edge);
	area = 4 * pow(edge,2); 
	printf("�� ������� ��� ����� ����� %.2lf m^2\n",area);
	volume = pow(edge,3);
	printf("� ����� ��� ����� ����� %.2lf m^3\n",volume);
	printf("���� ��� ���� ��� ���� �������...\n");
	printf("� ������ ��� ������� ����� ��� �� ��� ���� ��� �����\n");
	radius = edge;
	area2 = 4 * 3.14 * pow(radius,2);
	volume2 = (4 * 3.14 * pow(radius,3)) / 3;
	printf("�� ������� ��� ������� ����� %.2lf m^2\n",area2);
	printf("� ����� ��� ������� ����� %.2lf m^3\n\n",volume2);
	
	double side1,side2,side3,s,area1;
	printf("3) ����� �� ���� ������� ��� �������� �� ��.\n");
	scanf("%lf  ",&side1);
	scanf("%lf  ",&side2);
	scanf("%lf",&side3);
	printf("��� �� ������ �� ������� ��� �������� ������� ���� �� ���� ������� ��� �� ����������� ��� ���� ��� �����\n");
	printf("� ����� �����:A = sqrt(s(s-a)(s-b)(s-c)), ���� 's' � ������������� ��� ������� ��� ��� ���� s = (a + b + c)/2\n");
	s = (side1 + side2 + side3)/2.0;
	area1 = sqrt(s*(s-side1)*(s-side2)*(s-side3));
	printf("�� ������� ��� �������� ����� %.2lf m^2'",area1);
	return 0;
}
