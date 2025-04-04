#include <iostream>
#include <string>
using namespace std;

int main()
{
    int rmlength;
    int rmwidth;
    int rmArea;
    int rmPerimeter;

    cout << "Enter the length and width of the room." << endl;
    cin >> rmlength >> rmwidth;

    rmArea = rmlength * rmwidth;
    rmPerimeter = 2 * (rmlength + rmwidth);

    cout << "Area of a " << rmlength << " by " << rmwidth << " room is " << rmArea << endl;
    cout << "Perimeter of a " << rmlength << " by " << rmwidth << " room is " << rmPerimeter << endl;

    return 0;
}