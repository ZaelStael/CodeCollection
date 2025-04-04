#include <iostream>
using namespace std;

int main()
{
    int num;
    int TotofNum;
    int TotofIn;

    cout << endl << "Enter numbers, 999 to quit"<<endl; //1.Start
    cin >> num; //1.Start

    TotofNum = 0;
    TotofIn = 0;

    while (num != 999) //2.Test
    {
        TotofIn = TotofIn + 1;
        TotofNum = TotofNum + num;

        cout << "Number entered is: " << num << endl; //3.Action
        cout << "Enter numbers, 999 to quit" << endl; //4.Restart
        cout << "Number of Inputs: " << TotofIn << endl;
        cout << "Total of all numbers: " << TotofNum << endl;
        cin >> num; //4.Restart
    }

    return 0;
}