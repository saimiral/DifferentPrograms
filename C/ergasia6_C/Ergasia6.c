#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_WORD_LENGTH 20

void parameter_error();
void file_error();
void memory_error();
void line_error(int);
void print_frequency(int[]);
void print_combinations(char[], int);
void sort(int[], char[], int);

int main(int argc, char *argv[]) {
	
	int i,k,*freq,length;
	char *pin,*letters;
	FILE *fp;
	
	// αρχικοποίηση πινάκων και έλεγχος δέσμευσης μνήμης
	pin = (char *)malloc(MAX_WORD_LENGTH * sizeof(char));
	freq = (int *)malloc(26 * sizeof(int));
	letters = (char*)malloc(26 * sizeof(char));
	if(pin == NULL || freq == NULL || letters == NULL) memory_error();
	
	// έλεγχος πλήθους παραμέτρων
	if(argc != 2) parameter_error();
	
	// έλεγχος διαβάσματος αρχείου εισόδου
	if((fp = fopen(argv[1],"r")) == NULL) file_error();
	
	for(i=0; i<26; i++) freq[i] = 0; // αρχικοποίηση πίνακα συχνοτήτων
	
	// αποθήκευση λέξεων στον πίνακα, έλεγχος γραμμής και συχνότητες γραμμάτων
	int line=1;
	while(fgets(pin, MAX_WORD_LENGTH, fp) != NULL){
		for(i = 0; i < strlen(pin); i++) {
    		if(islower(pin[i]) || pin[strlen(pin)-1] != '\n') line_error(line); // εδώ εξετάζουμε αν υπάρχει κάποιο πεζό γράμμα ή αν η λέξη δεν τελειώνει με enter
    		for(k=65; k<=90; k++){ // A-Z σε ASCII
				if(pin[i] == k) freq[k - 65]++; // με την αφαίρεση του 65 από το κ προκύπτουν οι τιμές 0-25 για τα κελιά του πίνακα freq
			}
		}
		for(i = 0; i < strlen(pin)-2; i++) if(pin[i] == '\n') line_error(line);  // εδώ εξετάζουμε αν υπάρχει κάποιο άκυρο enter ανάμεσα στις λέξεις
		line++;
	}
	print_frequency(freq);
	
	for(i=0; i<26; i++) letters[i] = i + 65;
	sort(freq, letters, 26); // ταξινόμηση πίνακα συχνοτήτων
	
	//συνδυασμοί των 5 πρώτων γραμμάτων ανά 2
 	print_combinations(letters, 5);
 	
	fclose(fp);
	
	// αποδεσμεύσεις πινάκων
	free(pin);
	free(freq);
	free(letters);
	return 0;
}

void parameter_error(){
	printf("Invalid number of parameters"); 
	exit(1);
}

void file_error(){
	printf("Cannot read input file");
	exit(2);
}

void memory_error(){
	printf("Memory Problem");
	exit(3);
}

void line_error(int i){
	printf("Invalid Data in Line %07d",i);
	exit(4);
}

void print_frequency(int freq[]){
	int k;
	for(k=65; k<=90; k++) printf("%c %07d\n",k, freq[k-65]);
	printf("\n\n");
}

void print_combinations(char letters[], int length){
	int i,j;
	for(i=0; i<length-1; i++){
		for(j=i+1; j<length; j++) printf("%c%c\n",letters[i],letters[j]);
	}
}

void sort(int freq[], char letters[], int size){
	int i,j,temp,temp2;
	for(i=0; i<size-1; i++){
		for(j=0;j<size-1-i; j++){
			if(freq[j] < freq[j+1]){
				temp = freq[j];
				freq[j] = freq[j+1];
				freq[j+1] = temp;
				
				temp2 = letters[j];
				letters[j] = letters[j+1];
				letters[j+1] = temp2;
			}
		}
	}
}
