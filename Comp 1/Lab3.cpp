//Tyrique Baker
//2-22-22, On a Tuesday
//this program demonstrates the use of various operators

#include <iostream>
#include <string>
using namespace std;

int main ()
{
    int num1;
    int num2;
    int sum;
    int diff;
    int prod;
    float quot;
    float mod;

    cout << "Enter two integer values." << endl;
    cin >> num1 >> num2;

    sum = num1 + num2;
    cout << num1 << " + " << num2 << " is " << sum << endl;

    diff = num1 - num2;
    cout << num1 << " - " << num2 << " is " << diff << endl;

    prod = num1 * num2;
    cout << num1 << " * " << num2 << " is " << prod << endl;


    if (num2 == 0)
    {
        cout << "Can't divide by zero mate." << endl;
    }
    else
    {
    quot = (float) num1 / num2;
    cout << num1 << " / " << num2 << " is " << quot << endl;

    mod = num1 % num2;
    cout << num1 << " % " << num2 << " is " << mod << endl;
    }

    return 0;
}