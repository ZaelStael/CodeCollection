#include <iostream>
using namespace std;

int main()
{
    int exarray[5];

    for (int e = 0; e < 5; e++) //e = exarray
    {   cout << e;
        cout << endl;
    }

    return 0;
}
// *******************************\\
// *******************************\\
// *******************************\\

#include <iostream>
using namespace std;

void ArrayShiz(int exarray);

int main()
{
    int exarray[5][5];
    int row, col;

    for (row = 0; row < 5; row++)
    {
        for (col = 0; col < 5; col++)
            exarray[row][col] = 0;
    }
    
    for (row = 0; row < 5; row++)
    {
        for (col = 0; col < 5; col++)
        cout << exarray[row][col] << " ";
        cout << endl;
    }

    return 0;
}