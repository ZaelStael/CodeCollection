//Tyrique Baker
//2-22-22, On a Tuesday
//this program demonstrates the use of various operators

#include <iostream>
#include <string>
using namespace std;

int main()
{
    //local variable declaration
    char grade;

    cout << "What is your grade in ____?" << endl;
    cin >> grade;

    switch(grade)
    {
        case 'A':
        cout << "Excellent!" << endl;
        break;

        case 'B':
        cout << "Well done." << endl;
        break;

        case 'C':
        cout << "Meh." << endl;
        break;

        case 'D':
        cout << "Did you pass?." << endl;
        break;

        case 'F':
        cout << "You failed Bitch Boi." << endl;
        break;
    }
    

    cout << "Your grade is " << grade << "." << endl;

    return 0;
}


