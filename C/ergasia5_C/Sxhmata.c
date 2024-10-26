#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
	int i,grammes,j,k;
	printf("Give me the number of lines:  ");
	scanf("%d",&grammes);
	printf("\n\n");
	for(i=0;i<grammes;i++){
		for(j=0;j<=i;j++){
			printf("*");
		}
		printf("\n");
	}
	printf("\n\n");
	for(i=1;i<=grammes;i++){
		for(j=grammes;j>=i;j--){
			printf("*");
		}
		printf("\n");
	}
	printf("\n\n");
	for(i=0;i<grammes;i++){
		for(k=grammes;k>i;k--) printf(" ");
		for(j=1;j<=i;j++) printf("*");
		printf(".");
		for(j=1;j<=i;j++) printf("*");
		printf("\n");
	}
	printf("\n\n");
	for(i=0;i<grammes;i++){
		for(j=0;j<grammes;j++){
			if(i == 0 || j == 0 || i == grammes - 1 || j == grammes - 1) printf("*");
			else if(i > 0  && i < grammes - 1 && i == j ) printf(".");  // main diagonal
			else if(i > 0  && i < grammes - 1 && i + j == grammes - 1 ) printf(".");  // secondary diagonal
			else printf(" ");
		}
		printf("\n");
	}
	return 0;
}
