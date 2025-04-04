#include <iostream>
using namespace std;

void PrintArr(int table);

int main()
{
    int table[6][6];
    int row,col;
    
    for (row = 0; row < 6; row++)
    {
        for (col = 0; col < 6; col++)
            table[row][col]=0;
    }

    //Print the above matrix in table form
    for (row = 0; row < 6; row++)
    {
        for (col = 0; col < 6; col++)
            cout << table[row][col] << " ";
            cout << endl;
    }
   
   return 0;
}

