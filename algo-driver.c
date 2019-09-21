#include "algomatrix.h"
#include <math.h>
#include <stdlib.h>
#include <stdio.h>

int main(){
    int m,n;
    scanf("%d %d",&m,&n);
    MATRIKS M1;
    BacaMATRIKS(&M1, m,n);
    //TulisMATRIKS(M1);
    M1 = Echelon(M1, true);
    M1 = EchelonReduc(M1, true);
    printf("\nsuccess\n");
    TulisMATRIKS(M1); 
    printf("\n");
    SPLSolver(M1);
    /*char r= (IsSparse(M1))? 't' : 'f';
    printf("%c\n", r); */
    BacaMATRIKS(&M1,3,3);

    return 0;
}