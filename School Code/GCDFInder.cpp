//void function example
#include <iostream>
using namespace std;

int n1;
int n2;

int gcd (int n1, int n2)
{
    int gcd = 1; // Intial gcd is 1
    int k = 2;   // Possible gcd
    
    while (k <= n1 && k <= n2)
    {
        if (n1 % k == 0 && n2 % k == 0)
        gcd = k; // Update gcd
        k++;
    }
    return gcd; // Return gcd
}
int main()
{
    cout << "Enter your numbers" << endl;
    cin >> n1 >> n2;
    cout << "The greatest common divisor for " << n1 << " and " << n2 << " is " << gcd(n1, n2) << endl;
    
    return 0;
}