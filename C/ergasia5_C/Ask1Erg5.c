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
		printf("==== �������� ====\n");
		printf("[1]   ����������� ��������\n");
		printf("[2]   ����������� �����������\n");
		printf("[3]   ����������� �������\n");
		printf("[4]   ����������� ������� ����������\n");
		printf("[5]   ����������� ����� ����\n");
		printf("[6]   ������\n");
		printf("=================\n\n");
		printf("�� �� ������ �� ����;\n");
		scanf("%d",&c);
		getchar();
		switch(c){
			case 1: Syn1(); break;
			case 2: Syn2(); break;
			case 3: Syn3(); break;
			case 4: Syn4(); break;
			case 5: Syn5(); break;
			case 6:	printf("������\n");	break;
		}
	}while(c != 6);
}

void Syn1(){
	double sinx,x; // ���������� ��� ������ ��� � ����� x 
	
	do{
		printf("���� ��� ��� ���� ��� ���������� x �� ������: ");
		scanf("%lf",&x);
	}while(x < 1 || x > 360);
	
	sinx = sin_x(x);
	printf("�� ������� ��� x ����� %lf\n",sinx);
	system ("pause");	
}
void Syn2(){
	double cosx,x; // ���������� ��� ������ ��� � ����� x 
	
	do{
		printf("���� ��� ��� ���� ��� ���������� x �� ������: ");
		scanf("%lf",&x);
	}while(x < 1 || x > 360);
	
	cosx = cos_x(x);
	
	printf("�� ���������� ��� x ����� %lf\n",cosx);
	system ("pause");
}
void Syn3(){
	int b,e,res;
	printf("����� ��� ��� ����\n");
	scanf("%d",&b);
	printf("����� ��� ��� ������\n");
	scanf("%d",&e);
	res = myPow(b,e);
	printf("%d ���� %d� ����� %d\n",b,e,res);
	system ("pause");
}
void Syn4(){
	int n,k,C;
	printf("����� ��� �� �\n");
	scanf("%d",&n);
	printf("����� ��� �� �\n");
	scanf("%d",&k);
	C = combinations(n, k); 
	printf("�������� %d ���������� %d ��� %d\n",C,n,k);
	system ("pause");
}

void Syn5(){
	int N,avg;
		
	printf("������ �������� ������;\n");
	scanf("%d",&N);
	avg = mesos_oros(N);
	
	printf("� ����� ���� ��� ������� ��� ������ �����: %.2lf\n",avg);
	system ("pause");
}

double sin_x(double x){
	int i,prefix=1; // ������� ����
	double sinx,prev,next; //����������, ������������ ��� �������� ����
	
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
	return sinx;
}

double cos_x(double x){
	int i,prefix=1; //������� ����
	double cosx,prev,next; //����������, ������������ ��� �������� ����
	
	x = x * PI / 180.0;   // ��� Deg �� Rad
	prev = 1;  // ������ ���� ����� �� x^0 / 0! = 1  
	cosx = 1;  // �� ���� 
	
	for(i=2; ; i+=2){
		next = prev * ((x*x) / (i*(i-1)));
		
		if(prefix == 1) cosx -= next;            // ������� ���� + 
		if(prefix == -1) cosx += next;           // ������� ���� -
		prefix *= -1; // ������ ��� �������� �� ���� ���
		
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
	avg = (double )prod / N; // �� sum ��� ������� �� ����� 0 ��� ����������
	return avg;
}
