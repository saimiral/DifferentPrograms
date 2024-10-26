#include <iostream>

using namespace std;
const double PI = 3.14159;

class Circle{
    private:
        double radius;
        double area;
        double circumference;
    public:
        Circle(double rad);
        //~Circle();
        double Area();
        double Circumference();
        void printData();
};

Circle::Circle(double rad){
    this->radius = rad;
}

double Circle::Area(){
    area = PI * radius * radius;
    return area;
}

double Circle::Circumference(){
    circumference = 2 * PI * radius;
    return circumference;
}

void Circle::printData(){
    cout<<"Printing data...\n\n";
    cout<<"Radius: " << radius << "\nArea: " << area << "\nCircumference: " << circumference;
}

/*Circle::~Circle(){
    delete radius;
    delete area;
    delete circumference;
}*/

int main(int agrc, char** argv){
    double rad;
    cout<<"Dwse to radius bruther\n";
    cin>>rad;
    Circle a(rad);
    a.Area();
    cout<< "Area calculated successfully!\n";

    a.Circumference();
    cout<< "Circumference calculated successfully!\n";

    a.printData();

    //a.~Circle();
    return 0;
}
