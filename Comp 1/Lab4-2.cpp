//Tyrique Baker
//2-22-22, On a Tuesday
//this program demonstrates the use of various operators

#include <iostream>
#include <string>
using namespace std;

int main ()
{
    int testscore;

    cout << "Please enter test score." << endl;
    cin >> testscore;

    if ((testscore > 100) || (testscore < 0))
    {
        cout << "Invalid input." << endl;
    }
    else if (testscore >= 90)
    {
        cout << "Your grade is A." << endl;
    }
    else if (testscore >= 80)
    {
        cout << "Your grade is B." << endl;
    }
    else if (testscore >= 70)
    {
        cout << "Your grade is C." << endl;
    }
    else if (testscore >= 60)
    {
        cout << "Your grade is D." << endl;
    }
    else 
    {
        cout << "Your grade is F." << endl;
    }

    return 0;
}