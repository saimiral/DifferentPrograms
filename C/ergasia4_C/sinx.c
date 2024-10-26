#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define PI 3.141592

int main(int argc, char *argv[]) {
	system ("chcp 1253");
	
	int i,prefix=1; // ������� ����
	double sinx,prev,next,x; // ���������� ��� ������, � ������������/�������� ���� ��� ����� ��� � ����� x 
	
	do{
		printf("���� ��� ��� ���� ��� ���������� x �� ������: ");
		scanf("%lf",&x);
	}while(x < 1 || x > 360);
	
	x = x * PI / 180.0;   // ��� Deg �� Rad
	prev = x;  // ������ ���� ����� �� x^1 / 1! = x  
	sinx = x;  // �� ���� 
	
	for(i=3; ; i+=2){
		next = prev * ((x*x) / (i*(i-1)));
		
		if(prefix == 1) sinx -= next;            // ������� ���� + 
		if(prefix == -1) sinx += next;           // ������� ���� -
		prefix *= -1; // ������ ��� �������� �� ���� ���
		
		if(prev - next < 0.000001 && next - prev < 0.000001) break;
		
		prev = next;
	}
	
	printf("�� ������� ��� x ����� %lf\n",sinx);
	printf("� ��������� ��� math.h �����: %lf\n",sin(x));
	return 0;
}
