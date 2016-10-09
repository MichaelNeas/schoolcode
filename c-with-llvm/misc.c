//Michael Neas, CSE4100, Manipulate a simple data type represented by a C structure with two integer arrays of the same size.
#include <stdlib.h>
#include <stdio.h>

//DataType to manipulate
// a and b are pointers to arrays allocated on the heap via calls to malloc, this is why the heap is used
//<_a,_b>
struct AIP_tag {
   int* _a;
   int* _b;
};

typedef struct AIP_tag AIPair;

//fills the two arrays of a structure whose address you receive with integers
//the array a is filled with random values whereas b is filled with n consecutive integers in 0..n-1
//<2321,0>,<23,1>........<542,n-1>
void initPair(AIPair* p,int sz)
{
   int x;
   for(int i=0;i<sz;i++) {
      p->_a[i] = (int)(random() % 101);
      p->_b[i] = i;
   }
}

//routine prints the content of the two arrays (as a sequence of pairs), Nice this prints the above created pairs
void printPair(AIPair* p,int sz)
{
   for(int i=0;i<sz;i++) {
      printf("<%d,%d>",p->_a[i],p->_b[i]);
      if (i < sz-1)
         printf(",");
   }
   printf("\n");
}
