//Tyrique Baker
//2-22-22, On a Tuesday
//this program demonstrates the use of various operators

#include <iostream>
#include <string>
using namespace std;

int main ()
{
    float applePrice;
    float orangePrice;

    cout << "Please enter apple price." << endl;
    cin >> applePrice;
    cout << "Please enter orange price." << endl;
    cin >> orangePrice;

    if (applePrice > orangePrice)
    {
        cout << "Apples cost more." << endl;
    }
    else if (orangePrice > applePrice)
    {
        cout << "Oranges cost more." << endl;
    }
    else if ((orangePrice <= 0) || (applePrice <= 0))
    {
        cout << "So you're getting free fruit then?" << endl;
    }
    
    else 
    {
        cout << "Apples and oranges cost the same." << endl;
    }
    return 0;
}