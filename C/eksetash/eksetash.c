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
	
	printf("Δώστε το πλήθος των στοιχείων\n");
	do{
		scanf("%d",&N);
	}while(N < 10);
	
	pin = (double*)malloc(N * sizeof(double));
	if(pin == NULL) printf("Σφάλμα μνήμης\n");
	
	printf("Δώστε το κάτω και άνω όριο\n");
	do{
		scanf("%lf",&A);
		scanf("%lf",&B);
	}while(B < A);
	
	printf("Δώστε τους %d αριθμούς\n",N);
	readNumbers(pin, N, A, B);
	
	printf("Τώρα θα διπλασιάσουμε %d τυχαίες θέσεις του πίνακα\n", N/2);
	makeDouble(pin, N);
	
	printf("Τέλος, θα τυπώσουμε τα στοιχεία του πίνακα, το μέγιστο και ελάχιστο στοιχείο\n");
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
	printf("Η μέγιστη τιμή είναι %lf και η ελάχιστη τιμή είναι %lf\n",max, min);
}


/*Να γραφεί πρόγραμμα το οποίο μέσω κατάλληλων συναρτήσεων:
1) Θα διαβάζει Ν πραγματικούς αριθμούς στο διάστημα [Α … Β] από την
standard είσοδο και θα τους τοποθετεί σε πίνακα μεγέθους Ν. Οι αριθμοί Ν , Α
και Β διαβάζονται, επίσης, από την standard είσοδο.
2) Θα επιλέγει Ν/2 τυχαίες διαφορετικές θέσεις του πίνακα και θα διπλασιάζει τις
τιμές των θέσεων αυτών. Στην συνέχεια θα διπλασιάζει όλες τις θέσεις του πίνακα.
3) Θα τυπώνει στοιχισμένα τα στοιχεία του πίνακα (μετά τον διπλασιασμό) και την
ελάχιστη και την μέγιστη τιμή που βρίσκεται σε αυτόν.
Κάθε μία από τις 3 προηγούμενες εργασίες θα πραγματοποιείται αυτόνομα. Θα
πρέπει να δοθεί ιδιαίτερη προσοχή στην σωστή δόμηση του προγράμματος.*/
