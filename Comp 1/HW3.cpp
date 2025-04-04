#include <iostream>
using namespace std;

int main ()
{
    int num1;
    int sqr;
    int cub;
    
    cout << "Enter a number to start, Enter 999 when you wish to stop." << endl;
    cin >> num1;
    
    while (num1 != 999)
    {

    
        sqr = num1 * num1;
        cub = num1 * num1 * num1;
        
        cout << "Your number is " << num1 << endl;
        cout << "Its square is " << sqr << endl;
        cout << "Its cube is " << cub << endl;

        cout << "Okay, now enter a new number." << endl;
        num1 = 0;
        cin >> num1;
    } 
    
    cout << "It was fun while it lasted..." << endl;

    return 0;
}