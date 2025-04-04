// Name: Joe Schmoe
// Date Created?: 1/28/2020
// Description: This program calculates the value of two integers and displays the total (Type Casting)

#include <iostream>
using namespace std;

int main()
{
    // Declare and Initialize variables
    int num1;
    int num2;
    int totalAmount;

    totalAmount = 0;

    // get input from user and calculate total
    cout << "Enter 2 numbers" << endl;
    cin >> num1 >> num2;
    totalAmount = num1 / num2;
    (float) num1 / num2 => float result;
    cout << "The Dividend is" <<totalAmount << endl;

    return 0
;} //end of main function