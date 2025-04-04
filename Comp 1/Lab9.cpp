 //*********************************************
//New Welcome Home Program
//This program prints a "Welcome Home" message
//*********************************************
#include <iostream>
using namespace std;
void PrintLines(int numLines); //prototype

int main()
{
	PrintLines(2); //Actual Parameters- 2
	cout << "Welcome Home!" << endl;
    PrintLines(4); //Actual Parameters- 4
    
	return 0;

} // end of main method

//*********************************************
//PrintLines prints lines of astericks, where
//numlines specifiles how many lines to print
//*********************************************
void PrintLines(int numLines) //Formal Parmeters- numLines
{
	int line;

	for (line = 0; line < numLines; line++)
	{
		cout << "******************************" << endl;
	}

}
