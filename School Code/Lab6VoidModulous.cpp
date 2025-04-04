#include <iostream>
using namespace std;

int main()
{
    int num;
    int TotofNum;
    int TotofIn;
    char ans;
    
    void Calculations (int num, int TotofNum, int TotofIn);
    
    cout << "Would you like to Begin? (Y/N)" << endl;
    cin >> ans;

    while ((ans == 'Y' || ans == 'y'))
    {
    cout << endl << "Enter numbers, 999 to quit"<<endl; //1.Start
    cin >> num; //1.Start

    TotofNum = 0;
    TotofIn = 0;

    while (num != 999) //2.Test
    {
        TotofIn = TotofIn + 1;
        TotofNum = TotofNum + num;

        Calculations(num, TotofNum, TotofIn);
        
        cin >> num; //4.Restart
    } 
    cout << "Thanks for playing." << endl;

    }
    return 0;
} 

//******************
void Calculations(int num, int TotofNum, int TotofIn)
{
    

    cout << "Number entered is: " << num << endl; //3.Action
    cout << "Enter numbers, 999 to quit" << endl; //4.Restart
    cout << "Number of Inputs: " << TotofIn << endl;
    cout << "Total of all numbers: " << TotofNum << endl;
}

//Adding an & sign to the formal parameters (in both instances) will allow it to update values stored in memory, i.e. ... (WIP)
void Calculations(int& num, int& TotofNum, int& TotofIn)
{
    TotofIn = TotofIn + 1;
    TotofNum = TotofNum + num;

    cout << "Number entered is: " << num << endl; //3.Action
    cout << "Enter numbers, 999 to quit" << endl; //4.Restart
    cout << "Number of Inputs: " << TotofIn << endl;
    cout << "Total of all numbers: " << TotofNum << endl;
}