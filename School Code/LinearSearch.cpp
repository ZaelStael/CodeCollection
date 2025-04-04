#include <iostream>
using namespace std;

int LinearSearch(int array[], int size, int key); //prototype

int main()
{
    int index;
    int size;
    int key;

    size = 10;

    // declare 1-D Array
    int testScores[10];

    // input all values
    for (int k = 0; k < 10; k++) 
    {
        cout << "Enter an integer for Test Scores" << endl;
        cin >> testScores[k];
    }

    // print out 1-D Array
    for (int i = 0; i < 10; i++)
        cout << testScores[i] << endl;
    key = 60; // search key

    index = LinearSearch(testScores, size, key); // call function

    if (index == -1)
        cout << "Search key " << key << " not found" <<  endl;
    else
        cout << "Search key " << key << " found in index " << index << endl;

    return 0;
} // end of main

//**************************
int LinearSearch(int array[], int size, int key)
{
    bool found;
    int ix;
    found = false;
    ix = 0;
    while (ix < size && found == false)
    {
        if (array[ix] == key)
            found = true;
        else
            ix = ix + 1;
    }
    if (found == true)
        return ix;
    else
        return -1;
}