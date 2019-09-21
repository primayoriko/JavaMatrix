#include "algomatrix.h"
#include <math.h>
#include <stdlib.h>
#include <stdio.h>

void MakeMATRIKS (int NB, int NK, MATRIKS * M){
    NBrsEff(*M) = NB;
    NKolEff(*M) = NK;
}

boolean IsIdxValid (int i, int j){
    return (i>=BrsMin && i<=  BrsMax   && j>=KolMin && j<= KolMax );
}

/* *** Selektor: Untuk sebuah matriks M yang terdefinisi: *** */
indeks GetFirstIdxBrs (MATRIKS M){
    return BrsMin;
}

indeks GetFirstIdxKol (MATRIKS M){
    return KolMin;
}

indeks GetLastIdxBrs (MATRIKS M){
    return NBrsEff(M);
}

indeks GetLastIdxKol (MATRIKS M){
    return NKolEff(M);
}

boolean IsIdxEff (MATRIKS M, indeks i, indeks j){
    return (i>=BrsMin && i<=  GetLastIdxBrs(M)   && j>=KolMin && j<= GetLastIdxKol(M) );
}

ElType GetElmtDiagonal (MATRIKS M, indeks i){
    return Elmt(M,i,i);
}


/* ********** Assignment  MATRIKS ********** */
void CopyMATRIKS (MATRIKS MIn, MATRIKS * MHsl){
    for(int i=GetFirstIdxBrs(MIn);i<=GetLastIdxBrs(MIn);i++){
        for(int j=GetFirstIdxKol(MIn);j<=GetLastIdxKol(MIn);j++){
            Elmt(*MHsl,i,j)=Elmt(MIn,i,j);
        }
    }
    NBrsEff(*MHsl)=NBrsEff(MIn);
    NKolEff(*MHsl)=NKolEff(MIn);
}
/* Melakukan assignment MHsl  MIn */

/* ********** KELOMPOK BACA/TULIS ********** */
void BacaMATRIKS (MATRIKS * M, int NB, int NK){
    MakeMATRIKS(NB, NK, M);
    for(int i=GetFirstIdxBrs(*M);i<=GetLastIdxBrs(*M);i++){
        for(int j=GetFirstIdxKol(*M);j<=GetLastIdxKol(*M);j++){
            scanf("%f", &Elmt(*M,i,j));
        }
    }
}

void TulisMATRIKS (MATRIKS M){
    for(int i=GetFirstIdxBrs(M);i<=GetLastIdxBrs(M);i++){
        for(int j=GetFirstIdxKol(M);j<=GetLastIdxKol(M);j++){
            printf("%.3f", Elmt(M,i,j));
            if(j<GetLastIdxKol(M)) printf(" ");
            else if (i<GetLastIdxBrs(M)) printf("\n");
        }
    }
}

/* ********** KELOMPOK OPERASI ARITMATIKA TERHADAP TYPE ********** */
MATRIKS TambahMATRIKS (MATRIKS M1, MATRIKS M2){
    for(int i=GetFirstIdxBrs(M1);i<=GetLastIdxBrs(M1);i++){
        for(int j=GetFirstIdxKol(M2);j<=GetLastIdxKol(M2);j++){
            Elmt(M1,i,j) = Elmt(M1,i,j) + Elmt(M2,i,j);
        }
    }
    return M1;
}

MATRIKS KurangMATRIKS (MATRIKS M1, MATRIKS M2){
    for(int i=GetFirstIdxBrs(M1);i<=GetLastIdxBrs(M1);i++){
        for(int j=GetFirstIdxKol(M2);j<=GetLastIdxKol(M2);j++){
            Elmt(M1,i,j) = Elmt(M1,i,j) - Elmt(M2,i,j);
        }
    }
    return M1;
}

MATRIKS KaliMATRIKS (MATRIKS M1, MATRIKS M2){
    MATRIKS M3;
    MakeMATRIKS(NBrsEff(M1), NKolEff(M2), &M3);
    for(int i=GetFirstIdxBrs(M1);i<=GetLastIdxBrs(M1);i++){
        for(int j=GetFirstIdxKol(M2);j<=GetLastIdxKol(M2);j++){
            Elmt(M3,i,j) = 0;
            for(int k=GetFirstIdxKol(M1);k<=GetLastIdxKol(M1);k++){
                Elmt(M3,i,j) += Elmt(M1,i,k) * Elmt(M2,k,j);
            }
        }
    }
    return M3;
}

MATRIKS KaliKons (MATRIKS M, ElType X){
    for(int i=GetFirstIdxBrs(M);i<=GetLastIdxBrs(M);i++){
        for(int j=GetFirstIdxKol(M);j<=GetLastIdxKol(M);j++){
            Elmt(M,i,j) *= X;
        }
    }
    return M;
}

void PKaliKons (MATRIKS * M, ElType K){
    for(int i=GetFirstIdxBrs(*M);i<=GetLastIdxBrs(*M);i++){
        for(int j=GetFirstIdxKol(*M);j<=GetLastIdxKol(*M);j++){
            Elmt(*M,i,j) *= K;
        }
    }
}


/* ********** KELOMPOK OPERASI RELASIONAL TERHADAP MATRIKS ********** */
boolean EQ (MATRIKS M1, MATRIKS M2){
    boolean res;
    int i,j;
    if (!EQSize(M1,M2)){
        res = false;
    }
    else{
        res = true;
        i=GetFirstIdxBrs(M1)-1;   
        do{
            i++;
            j=GetFirstIdxKol(M1)-1;
            do{
                j++;
                if(Elmt(M1,i,j) != Elmt(M2,i,j)){
                    res = false;
                }
            } while (res && j<GetLastIdxKol(M1));
        } while (res && i<GetLastIdxBrs(M1));
    }
    return res;
}

boolean NEQ (MATRIKS M1, MATRIKS M2){
    return !EQ(M1,M2);
}
/* Mengirimkan true jika M1 tidak sama dengan M2 */
boolean EQSize (MATRIKS M1, MATRIKS M2){
    return NBrsEff(M1) == NBrsEff(M2) && NKolEff(M1) == NKolEff(M2);
}

/* ********** Operasi lain ********** */
int NBElmt (MATRIKS M){
    return NBrsEff(M)*NKolEff(M);
}
/* Mengirimkan banyaknya elemen M */

/* ********** KELOMPOK TEST TERHADAP MATRIKS ********** */
boolean IsBujurSangkar (MATRIKS M){
    return NBrsEff(M)==NKolEff(M);
}

boolean IsSimetri (MATRIKS M){
    boolean res;
    int i,j;
    if(!IsBujurSangkar(M)){
        res = false;
    }
    else{
        res = true;
        i=GetFirstIdxBrs(M)-1;       
        do{
            i++;
            j=GetFirstIdxKol(M)-1;
            do{
                j++;
                if(Elmt(M,i,j) != Elmt(M,j,i)){
                    res = false;
                }    
            } while (res && j<GetLastIdxKol(M));
        } while (res && i<GetLastIdxBrs(M));   
    }
    return res;
}

boolean IsSatuan (MATRIKS M){
    int i,j;
    boolean res;
    if(!IsBujurSangkar(M)){
        res = false;
    }
    else{
        res = true;
        i=GetFirstIdxBrs(M)-1;
        do{
            i++;
            j=GetFirstIdxKol(M)-1;
            do{
                j++;
                if(i==j){
                    if(Elmt(M,i,j)!=1) res = false;
                }
                else{
                    if(Elmt(M,i,j)!=0) res = false;
                }    
            } while (res && j<GetLastIdxKol(M));
        } while (res && i<GetLastIdxBrs(M));   
    }
    return res;
}

boolean IsSparse (MATRIKS M){
    boolean res;
    int num,N,i,j;
    num=0;
    //float spars;
    N=NBElmt(M);
    res = true;
    i=GetFirstIdxBrs(M)-1;
    do{
        i++;
        j=GetFirstIdxKol(M)-1;
        do{
            j++;
            if(Elmt(M,i,j) != 0){
                num++;
                if(num*20>N) res = false;
            }    
        } while (res && j<GetLastIdxKol(M));
    } while (res && i<GetLastIdxBrs(M));  
    return res; 
}

MATRIKS Inverse1 (MATRIKS M){
    return KaliKons(M, -1);
}

float Determinan (MATRIKS M){
    float res;
    int i,j,k,cnt;
    if (NKolEff(M)==1){
        res = Elmt(M,GetFirstIdxBrs(M),GetFirstIdxBrs(M));
    }
    else{
        res = 0;
        MATRIKS Ma;
        MakeMATRIKS(NBrsEff(M)-1,NKolEff(M)-1,&Ma);
        for(i=GetFirstIdxBrs(M);i<=GetLastIdxBrs(M);i++){
            for(j=GetFirstIdxBrs(M)+1;j<=GetLastIdxBrs(M);j++){
                cnt=0;
                for(k=GetFirstIdxBrs(M);k<=GetLastIdxBrs(M);k++){
                    if(k==i) cnt=-1;
                    else Elmt(Ma,j-1,k+cnt) = Elmt(M,j,k);
                }
            }
            if (Elmt(M,GetFirstIdxBrs(M),i)!=0) res += pow(-1,i+1) * Elmt(M,GetFirstIdxBrs(M),i) * Determinan(Ma);
        }
    }
    return res;
}

void PInverse1 (MATRIKS * M){
    return PKaliKons(M, -1);
}

void Transpose (MATRIKS * M){
    MATRIKS M2;
    CopyMATRIKS(*M, &M2);
    for(int i=GetFirstIdxBrs(*M);i<=GetLastIdxBrs(*M);i++){
        for(int j=GetFirstIdxKol(*M);j<=GetLastIdxKol(*M);j++){
            Elmt(*M,i,j)= Elmt(M2,j,i);
        }
    }
}


MATRIKS Echelon(MATRIKS Me, boolean aug){
    ElType temp, mult; 
    int q,i,j,k,p, augLim;
    boolean Fnd;
    augLim = (aug)?GetLastIdxKol(Me) - 1 : GetLastIdxKol(Me);
    j=GetFirstIdxBrs(Me);
    for(i=GetFirstIdxKol(Me);i<=augLim && j<= GetLastIdxBrs(Me);i++){
        q = j-1; Fnd = false;
        do {
            q++;
            //printf("hell %d %d\n", q, j);
            if (Elmt(Me,q,i)!=0){
                Fnd = true;
            }
        } while (!Fnd && q<GetLastIdxBrs(Me));

        //printf("-----\n");
        if(Fnd){
            if (q!=j){
                for(k = i; k<=GetLastIdxKol(Me); k++){
                    temp = Elmt(Me,q,k);
                    Elmt(Me,q,k) = Elmt(Me,j,k);
                    Elmt(Me,j,k) = temp;
                }
            }
            temp = Elmt(Me,j,i);
            //printf(" %d %d %f\n",i,j, temp);
            for(k=i;k<=GetLastIdxKol(Me);k++){
                //printf("%d %d %f %f\n", j, k, Elmt(Me,j,k), temp);
                Elmt(Me,j,k) /= temp;
            }

            for(k = j+1; k<= GetLastIdxBrs(Me); k++){
                if (Elmt(Me, k, i)!=0){
                    mult = Elmt(Me, k,i)/Elmt(Me, j,i); 
                    for (p=i; p<=GetLastIdxKol(Me); p++){
                        Elmt(Me,k,p) -= mult * Elmt(Me,j,p);
                    }
                }
            } 
            //TulisMATRIKS(Me);
            //printf("\n");
            j++;
        }    
    }
    return Me;
}


MATRIKS EchelonReduc(MATRIKS Me, boolean aug){
    // I.S must be an echelon matrix first
    ElType temp, mult; 
    int c,r,i,j,k,augLim;
    boolean Fnd;
    augLim = (aug)?GetLastIdxKol(Me) - 1 : GetLastIdxKol(Me);

    for(i= augLim;i>=GetFirstIdxKol(Me);i--){
        Fnd = false;
        for(j= GetLastIdxBrs(Me); j>= GetFirstIdxBrs(Me); j--){
            if(Elmt(Me,j,i)!=0){
                if (!Fnd){
                    Fnd = true;
                    r= j;
                }
                else{
                    mult = Elmt(Me,j,i) / Elmt(Me, r, i);
                    for(c=GetLastIdxKol(Me); c>= GetFirstIdxKol(Me); c--){
                        Elmt(Me, j, c) -= mult * Elmt(Me, r, c);
                    }
                }
            }
        }
    }   
    return Me;
}

void SPLSolver(MATRIKS M){
    ElType kons, Sol[GetLastIdxKol(M)];
    const float ValUndef = -9999997;
    int i, j, k, cnt, ndef, nSol[GetLastIdxKol(M)];;
    boolean loop;

    for(i= GetFirstIdxKol(M);i<GetLastIdxKol(M);i++){
        Sol[i]= ValUndef;
        nSol[i]= (int)ValUndef;
    } 

    loop = true; cnt = 0; 
    for(i= GetLastIdxBrs(M); i>=GetFirstIdxBrs(M) && loop; i--){
        ndef = 0;
        kons = Elmt(M, i, GetLastIdxKol(M));
        for(j= GetLastIdxKol(M)-1; j>=GetFirstIdxKol(M); j--){
            if(Elmt(M,i,j) !=0){
                if(Sol[j]!=ValUndef){
                    kons -= Sol[j]*Elmt(M,i,j);
                }
                else { 
                    if (ndef == 0) k = j;
                    nSol[j] = i;
                    ndef++;
                }
            }  
        }
        if(ndef==1){
            Sol[k] = kons / Elmt(M, i, k);
            cnt++;
        }
        else if(kons!=0 && ndef==0){
            printf("Tidak Ada Solusi Valid\n");
            loop=false;
        }
        Elmt(M, i, GetLastIdxKol(M)) = kons;
    }
    if (loop){
        if(cnt==GetLastIdxKol(M)-1){
            printf("Terdapat solusi unik\n");
        }
        else{
            printf("Solusi ada Banyak\n");
        }
        for(j = GetFirstIdxKol(M); j<=GetLastIdxKol(M)-1; j++){
            if(Sol[GetLastIdxKol(M)-j]!=ValUndef){
                nSol[GetLastIdxKol(M)-j]= ValUndef;
                printf("x%d = %.3f\n", GetLastIdxKol(M)-j, Sol[GetLastIdxKol(M)-j]);
            }
        }
        for(j = GetFirstIdxKol(M); j<=GetLastIdxKol(M)-1; j++){
            if(nSol[GetLastIdxKol(M)-j]!=ValUndef){
                k = nSol[GetLastIdxKol(M)-j];
                printf("%.3fx%d ", Elmt(M,k,j), GetLastIdxKol(M)-j);
                nSol[GetLastIdxKol(M)-j]= ValUndef;
                for(i = j; i<=GetLastIdxKol(M)-1; i++){
                    if(nSol[GetLastIdxKol(M)-i] == k){
                        printf("+ %.3fx%d ", Elmt(M,k,i), GetLastIdxKol(M)-i);
                        nSol[GetLastIdxKol(M)-i]= ValUndef;
                    }
                }
                printf(" = %.3f\n", Elmt(M, k, GetLastIdxKol(M)));
            }
        }
    }
} 

void Interpolation(int Npers, int deg){
    MATRIKS M;
    MakeMATRIKS(Npers, deg+1, &M);
    int i,j,k;
    ElType a, b;
    for(j=GetFirstIdxBrs(M);j<=GetLastIdxBrs(M);j++){
        scanf("%f %f", &a, &b);
        Elmt(M, j, GetLastIdxKol(M)) = b;
        for(i=GetFirstIdxKol(M);i<GetLastIdxKol(M);i++){
            Elmt(M, j, i) = powf(a, GetLastIdxKol(M)-i);
        }
    }
    Echelon(M, true);
    EchelonReduc(M, true);
    SPLSolver(M);
}