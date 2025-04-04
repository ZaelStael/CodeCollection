//void function example
#include <iostream>
#include <iomanip>
using namespace std;

// Check whether number is prime
bool isPrime(int number)
{
    for (int divisor = 2; divisor <= number / 2; divisor++)
    {
        if (number % divisor == 0)
        {
            //If true, number is not prime
            return false; //number is not prime
        }
    }
    return true; // number is prim0e
}
//************************
void printPrimeNumbers(int numberofPrimes)
{
    int count = 0; // Count the number of prime numbers
    int number = 2; // A number to be tested for primeness
    
    // Repeatedly find prime numbers
    while (count < numberofPrimes)
    {
        // Print the prime number and increase the count
        if (isPrime(number))
        {
            count++; // Increase the count
            if (count % 10 == 0) //10 numbers per line
            {
                //Print the number and advance to the new line
                cout << setw(4) << number << endl;
            }
            else
                cout << setw(4) << number;
        }
        number++; // Check if the next number is prime
    }
}
//***************************
int main()
{
    cout << "The first 50 prime numbers are \n";
    printPrimeNumbers(50);
    
    return 0;
}