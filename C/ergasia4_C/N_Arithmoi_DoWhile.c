#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
	system ("chcp 1253");
	
	int a,sum = 0,sum3 = 0,negative = 1; // αριθμός, πλήθος αριθμών, πλήθος πολλαπλασίων του 3 και αρνητικοί
	double polla3 = 1.0,polla4 = 1.0; // πολλαπλάσια του 3 και 4
	char choice;
	
	do{
		printf("Δώσε μου έναν άρτιο ακέραιο: ");
		scanf("%d",&a);
		sum++;
		if(a % 3 == 0){
			polla3 += a;
			sum3++;
		}
		if(a % 4 == 0) polla4 *= a;
		if(a < 0) negative *= a;
		
		printf("Θες να δώσεις άλλον αριθμό;('N' ή 'Ο')  ");
		fflush(stdin); // για το keyboard buffer
		choice = getchar();
		
	}while(choice != 'O' && choice != 'o' && choice != 'Ο' && choice != 'ο');
	// δεν ήξερα αν έπρεπε να λάβω υπόψη την επιλογή χαρακτήρα πέρα από Ν ή Ο...
	// το 2ο σετ Ο και ο είναι για περίπτωση Ελληνικών χαρακτήρων
		
	printf("Έδωσες %d αριθμό/ούς\n",sum);
	if(polla3 != 1) {
		polla3 /= sum3;
		printf("Ο μέσος όρος των πολλαπλασίων του 3 είναι %.2lf\n",polla3);
    }else printf("Δεν έδωσες πολλαπλάσιο του 3\n");
	if(polla4 != 1) {
		printf("Το γινόμενο πολλαπλασίων του 4 είναι %.2lf\n",polla4);
	}else printf("Δεν έδωσες πολλαπλάσιο του 4\n");
	if(negative != 1){
		printf("Το γινόμενο των αρνητικών αριθμών είναι %d\n",negative);
	}else printf("Δεν έδωσες αρνητικό\n");
	return 0;
}
