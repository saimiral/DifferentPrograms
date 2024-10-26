#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
	system ("chcp 1253");
	
	int mhnas,mxEtos; //μήνας και μ.Χ. έτος
	
	do{
		printf("Δώστε μου τον αριθμό του μήνα\n");
		scanf("%d",&mhnas);
	}while(mhnas < 1 && mhnas > 12);
	printf("Δώστε μου ένα έτος μ.Χ.\n");
	scanf("%d",&mxEtos);
	switch(mhnas){
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12: printf("Ο μήνας έχει 31 ημέρες!\n"); break;
		case 4:
		case 6:
		case 9:
		case 11: printf("Ο μήνας έχει 30 ημέρες!\n"); break;
		case 2:	mxEtos % 4 == 0 ? printf("Δiσεκτο έτος!!! ο μήνας έχει 29 ημέρες!\n") :
		 		printf("Ο μήνας έχει 28 ημέρες!\n");
				break;
	}
	return 0;
}
