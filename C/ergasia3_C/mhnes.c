#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
	system ("chcp 1253");
	
	int mhnas,mxEtos; //����� ��� �.�. ����
	
	do{
		printf("����� ��� ��� ������ ��� ����\n");
		scanf("%d",&mhnas);
	}while(mhnas < 1 && mhnas > 12);
	printf("����� ��� ��� ���� �.�.\n");
	scanf("%d",&mxEtos);
	switch(mhnas){
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12: printf("� ����� ���� 31 ������!\n"); break;
		case 4:
		case 6:
		case 9:
		case 11: printf("� ����� ���� 30 ������!\n"); break;
		case 2:	mxEtos % 4 == 0 ? printf("�i����� ����!!! � ����� ���� 29 ������!\n") :
		 		printf("� ����� ���� 28 ������!\n");
				break;
	}
	return 0;
}
