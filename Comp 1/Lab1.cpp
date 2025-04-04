//Tyrique Baker
//February 2, 2022

#include <iostream>
using namespace std;

int main()
{
    float num1;
    float num2;
    float avg;

    cout << "Please enter two numbers." << endl;
    cin >> num1;
    cin >> num2;

    avg = (num1 + num2) / 2;

    cout << "The average of these numbers is" << " " << avg << endl;

    return 0;
}