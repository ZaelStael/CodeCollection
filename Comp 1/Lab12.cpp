// Tyrique Baker, 5/3-22
#include <iostream>
using namespace std;

int main()
{

	char TicTacToe[3][3];

	int  row;
	int  col;
	for (row = 0; row < 3; row++)
	{
		for (col = 0; col < 3; col++)
			TicTacToe[row][col] = '?';
	}

	//2nd: Assign X to the Frist row and column
    TicTacToe [0][0] = 'X';
	

	//3rd: Assign O to the Last row and column
    
    TicTacToe [2][2] = 'O';



	//1st: Print the array in table form

	int r;
	int c;

	for (r = 0; r < 3; r++)
	{
		for (c = 0; c < 3; c++)
			cout << TicTacToe[r][c] << " ";
		cout << endl;
	}



	return 0;
}
