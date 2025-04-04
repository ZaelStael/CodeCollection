//void function example
#include <iostream>
using namespace std;

void DisplayArea (float length, float width); //Prototype

int main ()
{
    char ans;
    float length;
    float width;
    float area;

    cout << " do you want to play today (y/n)" << endl;
    cin >> ans;

    while ((ans == 'Y' || ans == 'y'))
    {
        cout << "Enter length and width" << endl;
        cin >> length >> width;

        DisplayArea(length, width); //length and width are Actual Parameters

        cout << " do you want to play today (y/n)" << endl;
        cin << ans;
    }

    return 0;
} // end of main method

//*********************
void DisplayArea(float length, float width)//length and width are formal Parameters
{
    float area; //local variable

    area = length * width;
    cout << "Area is " << area << endl;
}