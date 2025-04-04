// Tyrique Baker
// February 15, 2022

#include <iostream>
#include <string>
using namespace std;

int main ()
{
    //declarations
    string firstname;
    string lastname;
    string name;
    int age;
    float bodytemp;
    int heightft;
    int heightin;
    string height;
    char sex;
    float weight;

    //input
    cout << "What's yo name." << endl;
    cin >> firstname;

    cout << "Mmmhmm, and yo last?"<< endl;
    cin >> lastname;

    cout << "Coo. Coo. What's yo age baby?" << endl;
    cin >> age;

    cout << "Just how hot are you?" << endl;
    cin >> bodytemp;

    cout << "How high do those legs go? Tell me the first digit." << endl;
    cin >> heightft;
    cout << "And the last." << endl;
    cin >> heightin;

    cout << "What 'equipment' am I working with down there" << endl;
    cin >> sex;

    cout << "How much is there to love?" << endl;
    cin >> weight; 

    name = firstname + " " + lastname;

    //output
     cout << "How you doin' " << name << "!" << endl;
     cout << "Mmmmm, " << age << " how delectable." << endl;
     cout << bodytemp << "? You're almost too hot to handle." << endl;
     cout << sex << " huh. I can work with that." << endl;
     cout << "Sweet, " << heightft << " ft and " << heightin << " inches?" << " That's Perfect." << endl;
     cout << weight << "? Wonderful..." << endl;
     
     return 0;
}