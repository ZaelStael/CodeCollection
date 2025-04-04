#include <iostream>
#include <fstream> //includes ifstream and ofstream
#include <string>
using namespace std;

int main()
{
    fstream myFile;
    
    string story; //Taking the input string from the user
    cout << "Enter your tale...\n";
    getline(cin, story);

    char story_char[story.length()]; //Converting the string to character array
    for (int i = 0; i < story.length();i++)
    {
        story_char[i]=story[i];
    }
    
    myFile.open("Tale.txt", ios::out); //Ciphering the tale and writing it to the file...
    if (myFile.is_open())
    {
        for (int i = 0; i< story.length(); i++)
        {
        myFile<<int(story_char[i]);
        }
    }
    myFile.close();

    cout << "The deciphered numbers form the tale...\n"; //De-ciphering the numbers into a ledgible tale...

    myFile.open("story.txt", ios::in);
    if (myFile.is_open())
    {
        string line;
        while (getline(myFile,line))
        {int num = 0;
            for (int i = 0; i<line.length();i++)
            {
                num = num*10 + (line[i]-'0');
                if(num >=32 && num <=122)
                {  char ch = (char)num;
                    cout << ch;
                    num = 0;
                 }
            }
        }

    }
    myFile.close();
return 0;
}