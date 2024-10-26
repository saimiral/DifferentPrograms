//Windows

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void readNumbers(double [], int, double, double);
void makeDouble(double [], int);
void printNumbers(double [], int);
void findMaxMin(double [], int, double, double);
void printMaxMin(double, double);

int main(int argc, char *argv[]) {
	system("chcp 1253");
	int N, i;
	double *pin,A,B;
	
	printf("����� �� ������ ��� ���������\n");
	do{
		scanf("%d",&N);
	}while(N < 10);
	
	pin = (double*)malloc(N * sizeof(double));
	if(pin == NULL) printf("������ ������\n");
	
	printf("����� �� ���� ��� ��� ����\n");
	do{
		scanf("%lf",&A);
		scanf("%lf",&B);
	}while(B < A);
	
	printf("����� ���� %d ��������\n",N);
	readNumbers(pin, N, A, B);
	
	printf("���� �� ������������� %d ������� ������ ��� ������\n", N/2);
	makeDouble(pin, N);
	
	printf("�����, �� ��������� �� �������� ��� ������, �� ������� ��� �������� ��������\n");
	printNumbers(pin, N);
	findMaxMin(pin, N, A, B);
	
	return 0;
}

void readNumbers(double pin[], int size, double A, double B){
	int i;
	
	for(i=0; i<size; i++){
		do{
			scanf("%lf",&pin[i]);
			getchar();
		}while(pin[i] < A || pin[i] > B);
	}
}

void makeDouble(double pin[], int size){
	srand(time(NULL));
	int i;
	for(i=1; i<= size/2; i++){
		pin[rand() % (size + 1)] *= 2;
	}
}

void printNumbers(double pin[], int size){
	int i;
	for(i=0; i<size; i++) printf("%.2lf\n", pin[i]);
}

void findMaxMin(double pin[], int size, double A, double B){
	double max = A-1 ,min = B+1;
	int i;
	for(i=0; i<size; i++){
		if(max < pin[i]) max = pin[i];
		if(min > pin[i]) min = pin[i];
	}
	printMaxMin(max, min);
}

void printMaxMin(double max, double min){
	printf("� ������� ���� ����� %lf ��� � �������� ���� ����� %lf\n",max, min);
}


/*�� ������ ��������� �� ����� ���� ���������� �����������:
1) �� �������� � ������������ �������� ��� �������� [� � �] ��� ���
standard ������ ��� �� ���� ��������� �� ������ �������� �. �� ������� � , �
��� � �����������, ������, ��� ��� standard ������.
2) �� �������� �/2 ������� ������������ ������ ��� ������ ��� �� ����������� ���
����� ��� ������ �����. ���� �������� �� ����������� ���� ��� ������ ��� ������.
3) �� ������� ����������� �� �������� ��� ������ (���� ��� �����������) ��� ���
�������� ��� ��� ������� ���� ��� ��������� �� �����.
���� ��� ��� ��� 3 ������������ �������� �� ���������������� ��������. ��
������ �� ����� ��������� ������� ���� ����� ������ ��� ������������.*/
