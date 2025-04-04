// Name: Tyrique Baker
// Date: 2/1-30
// Description: Food Prices

#include <iostream>
#include <string>
using namespace std;

int main()
{
    float APrice;
    float OPrice;

    cout << "First enter the price of apples, then enter the price of oranges." << endl;
    cin >> APrice >> OPrice;

    if (APrice > OPrice)
    cout << "The apples cost more than the oranges." << endl;

    if (OPrice > APrice)
    cout << "The oranges cost more than the apples." << endl;




return 0
}