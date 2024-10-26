#include <iostream.h>

using namespace std;


class Vehicle{
	private:
		char* make;
		char* model;
		int year;
		int weight;
		bool needsMaintenance = false;
		int tripsSinceMaintenance = 0;
	public:
		Vehicle(char* make, char* model, int year, int weight);
		
		void setMake(char* make){
			this->make = make;
		}
		void setModel(char* model){
			this->model = model;
		}
		void setYear(int year){
			this->year = year;
		}
		void setWeight(int weight){
			this->weight = weight;
		}
		void setTrips(int tripsSinceMaintenance){
			this->tripsSinceMaintenance = tripsSinceMaintenance;
		}
		void setMaintenance(bool needsMaintenance){
			this->needsMaintenance = needsMaintenance;
		}
		
		char* getMake(){
			return this->make;
		}
		char* getModel(){
			return this->model;
		}
		char* getYear(){
			return this->year;
		}
		char* getWeight(){
			return this->weight;
		}
		bool getMaintenance(){
			return this->needsMaintenance;
		}
		int getTrips(){
			return this->tripsSinceMaintenance;
		}
};

class Car:public Vehicle{
	private:
		bool isDriving;
		int HP;
	public:
		Car(int HP):Vehicle(char* make, char* model, int year, int weight);
		void Drive();
		void Stop();
};

Car::Car(int HP){
	
}

void Car::Drive){
	cout<<"Youre driving ur car\n";
	isDriving = true;
}
void Car::Stop(){
	cout<<"You stopped driving\n";
	isDriving = false;
	int trips = getTrips();
	setTrips(trips++);
	if(trips > 100) setMaintenance(true);
	
}

int main(int argc, char** argv){
	
	return 0;
}
