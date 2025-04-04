 // Tyrique Baker, 4/19-2022
 // Value-returning example
#include <iostream>
using namespace std;

float Double(float number);

int main()
{
	char  ans;
	float number;
	float area;
	float finalResult;

	cout << " do you want to play today (y/n)" << endl;
	cin >> ans;

	while ((ans == 'Y' || ans == 'y'))
	{
		cout << "Enter a number" << endl;
		cin >> number;

		finalResult = Double(number);
		cout << number << " Doubled is " << finalResult << endl;

		cout << " do you want to play today (y/n)" << endl;
		cin >> ans;
	}

	return 0;
} // end of main function


//****function Double*****

float Double(float number) 
{
	float results;

	results = number * 2;

	return results;
}