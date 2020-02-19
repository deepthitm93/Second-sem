#include <sys/time.h>
#include <bits/stdc++.h> 
#include <cstdio>
using namespace std;

#define max 100

int checkdegree(int[max][max], int*, int[max]);
int checkdegree(int A[max][max], int *vertex, int degA[max])
{
	int i, j, degreeA, k=0;	
	for(i=0;i<(*vertex);i++, k++)
	{
		 degreeA=0;
		for(j=0;j<(*vertex);j++)	
		{
			if(A[i][j] == 1)	
				degreeA++;
		}
		degA[k]= degreeA;
	}
	degreeA=0;
	for(k=0;k<(*vertex);k++)
	{
		degreeA= (degreeA + degA[k]); 
	}
	return(degreeA);
}

int main(int argc, char * argv[])
{
	int M1[max][max], M2[max][max], flag=0, f=-1;
  	int i, j, sum=0;
	int N;
	int A[max];
	int degA[max], degB[max], degreeA=0, degreeB=0;

	timeval timIn, timOut;
	cout<<"no of vertex:";
	cin>>N;
	cout<<"Enter the adjacency matrix of graph g1\n";
	// Matrix 1 
	for (i = 0; i < N; i++)
	{
		for (j = 0; j < N; j++)
		{
			cin>>M1[i][j];
		}
	}
	cout<<"enter adj of graph g2:\n";
	// Matrix 2
	for (i = 0; i < N; i++)
	{
		for (j = 0; j < N; j++)
		{
			cin>>M2[i][j];
		}
	}
	int idx[N];
  	for(i=0; i < N; i++)
    		idx[i] = 0;

	// Store the index for the permutation
	for(i=0;i<N;i++)
	{
		A[i]=i; 
	}
	// Calculate the start time
	gettimeofday(&timIn,NULL);
	printf("Start time: %ld.%ld\n", timIn.tv_sec, timIn.tv_usec);
	
	// Check the degree of matrix 1
	degreeA= checkdegree(M1, &N, degA);
	// Check the degree of matrix 2
	degreeB= checkdegree(M2, &N, degB);
	flag=0;
	if(degreeA == degreeB)
	{
		for(i=0;i<N;i++)
			for(j=0;j<N;j++)
			{
				if(degA[i] == degB[j])
				{
					f=0;
				}
				else
				{
					f=1;
					break;
				}
			}			
			
		if(f == 1)
			printf("\n\t Degrees are not equal so it is not isomorphic\n");
		else if(f == 0)	
			printf("\n\t Degrees are equal verify for Isomorphic\n");
	}
	else
		printf("\n\tNot Isomorphic\n");

	// Permute each index instance of Matrix M2 and compare with Matrix 1
	for (i=1; i < N;) {
		if (idx[i] < i) {
			int swap = i % 2 * idx[i];
			int tmp = A[swap];
			A[swap] = A[i];
			A[i] = tmp;
			
			for(int k = 0; k < N; k++)
			{
				for(int l = k+1; l < N;l++)
				{		
					sum+=abs(M1[k][l]-M2[A[k]][A[l]]);
				}
				// Dont compare the entire matrix.
				if(sum>0)
				{
					break;
				}
			}
			if(sum==0)
			{
				cout<<"graphs are isomorphic\n";
				for( int index = 0; index < N; index++)
				{
					cout<<"\t"<<index<<"vertex of graph g1 maps to "<< A[index] <<"vertex of map g2\n";
				}
				gettimeofday(&timOut,NULL);
				printf("End time: %ld.%ld\n", timOut.tv_sec, timOut.tv_usec);
				printf("Total time: %ld.%ld\n", (timOut.tv_sec - timIn.tv_sec), (timOut.tv_usec - timIn.tv_usec));
				exit(0);
			}
#endif
			idx[i]++;
			i = 1;
			sum=0;
		} 
		else 
		{
			idx[i++] = 0;
		}
	}
	cout<<"graphs are not isomorphic\n";
	gettimeofday(&timOut,NULL);
	printf("End time: %ld.%ld\n", timOut.tv_sec, timOut.tv_usec);
	printf("Total time: %ld.%ld\n", (timOut.tv_sec - timIn.tv_sec), (timOut.tv_usec - timIn.tv_usec));
  return 0;
}

