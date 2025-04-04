// Name: Tyrique Baker
// Date: 2/1-30
// Description: Say hello to the world user...

#include <iostream>
#include <string>
using namespace std;

int main()
{
    // declare variables
    string name;
    string title;

    // Prompt (or ask) user for input
    cout << "Enter your First Name" << endl;

    // Recieve input from user
    cin >> name;
    
    //Prompt user to give prefix
    cout << "Enter a suitable prefix (i.e. Sir, ma'am, etc.)" << endl;
    
    //Recieve title from user
    cin >> title;

    // Display output
    cout << "Hello World! My Name is" << " " << title << " " << name << endl;


    return 0;
} //Function End