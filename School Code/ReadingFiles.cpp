#include <iostream>
#include <fstream> //includes ifstream and ofstream
#include <string>
using namespace std;

int main()
{
    fstream myFile;
    myFile.open("DummyFile.txt", ios::in); //read
    if (myFile.is_open())
    {
        string line;
        while (getline(myFile, line))
        {
            cout << line << endl;
        }
        myFile.close();

    }

    system("pause>0");
}
