#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
	system ("chcp 1253");
	
	int a,sum = 0,sum3 = 0,negative = 1; // �������, ������ �������, ������ ������������ ��� 3 ��� ���������
	double polla3 = 1.0,polla4 = 1.0; // ����������� ��� 3 ��� 4
	char choice;
	
	do{
		printf("���� ��� ���� ����� �������: ");
		scanf("%d",&a);
		sum++;
		if(a % 3 == 0){
			polla3 += a;
			sum3++;
		}
		if(a % 4 == 0) polla4 *= a;
		if(a < 0) negative *= a;
		
		printf("��� �� ������ ����� ������;('N' � '�')  ");
		fflush(stdin); // ��� �� keyboard buffer
		choice = getchar();
		
	}while(choice != 'O' && choice != 'o' && choice != '�' && choice != '�');
	// ��� ����� �� ������ �� ���� ����� ��� ������� ��������� ���� ��� � � �...
	// �� 2� ��� � ��� � ����� ��� ��������� ��������� ����������
		
	printf("������ %d ������/���\n",sum);
	if(polla3 != 1) {
		polla3 /= sum3;
		printf("� ����� ���� ��� ������������ ��� 3 ����� %.2lf\n",polla3);
    }else printf("��� ������ ����������� ��� 3\n");
	if(polla4 != 1) {
		printf("�� �������� ������������ ��� 4 ����� %.2lf\n",polla4);
	}else printf("��� ������ ����������� ��� 4\n");
	if(negative != 1){
		printf("�� �������� ��� ��������� ������� ����� %d\n",negative);
	}else printf("��� ������ ��������\n");
	return 0;
}
