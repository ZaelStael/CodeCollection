#include <iostream>
using namespace std;

void PrintArr(int Bucky[]);

int main()
{
    int x;
    int Bucky[6] = {66, 75, 2, 43, 99, 23};

    cout << "Enter a number from 0-5." << endl;
    cin >> x;
    
    PrintArr(Bucky);
    
    cout << "Your value is " << Bucky[x] << endl;
    
    return 0;
}

//******************************
void PrintArr(int Bucky[])
{
    for (int i = 0; i < 6; i++)
    cout <<i << "******************" << Bucky[i] << endl;
}
