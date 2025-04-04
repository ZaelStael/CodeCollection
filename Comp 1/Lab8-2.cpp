// Tyrique Baker 4/5-22
#include <iostream>
#include <fstream> // includes ifstream and ofstream
using namespace std;

int main()
{

	// declare a handle 
	ofstream outFile; // open only (writing)

	//Open the file
	outFile.open("c:\\SchoolNShiz\\Coding\\Projects\\stuff.txt");
	//outFile is associated with stuff

	//Write to file
	outFile << "Hello" << endl;

	return 0;
}