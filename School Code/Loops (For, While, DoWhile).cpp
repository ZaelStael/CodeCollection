// Name: Tyrique Baker
// Date: 2/28-22
// Description: Loop de loops

#include <iostream>
#include <string>
using namespace std;

int main ()
{
    int i = 0;
    
    while (i < 5)    
    {
        cout << "Hello World!" << endl;
        i++;
    }

    cout << " ===And=== " << endl;

    for (int i = 0; i < 5; i++)
{
    cout << "Goodbye World!" << endl;
}
 cout << " =====And=====" << endl;

    i = 0;
    
    do
    {
        cout << "Cool World..." << endl;
        cout << "Cruel World..." << endl;
        i++;
    } while (i < 5);
    
    return 0;