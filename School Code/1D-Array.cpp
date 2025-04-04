#include <iostream>
using namespace std;

void PrintArr(int testScores[]);

int main()
{
    int testScores[10];

    for (int x = 0; x < 10; x++)
    {
        testScores[x] = 0;

        cout << "Enter a score." << endl;
        cin >> testScores[x];
    }

    PrintArr(testScores);

        return 0;
}

//************************************
void PrintArr(int testScores[])
{
    for (int i = 0; i < 10; i++)
    cout << testScores[i] << endl;
}