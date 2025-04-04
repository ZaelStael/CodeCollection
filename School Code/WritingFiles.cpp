#include <iostream>
#include <fstream> //includes ifstream and ofstream
using namespace std;

int main()
{
    fstream myFile;
    int num1;

    cout << "Enter a number to place into the Dummy file." << endl;
    cin >> num1;

    myFile.open("DummyFile.txt",ios::out); //write

    if (myFile.is_open())
    {
        myFile << "Hello.\n";
        myFile << "How are you today?\n";
        myFile << num1;
        myFile.close();
    }
    myFile.open("DummyFile.txt",ios::app); //append

    if (myFile.is_open())
    {
        myFile << " Thanks for playing\n";
        myFile.close();
    }

   system("pause>0");
}
