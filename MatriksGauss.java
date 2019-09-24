import java.util.Scanner;
import java.lang.Math;

public class MatriksGauss extends Matriks{
    MatriksGauss(int NB, int NK){
        //KAMUS LOKAL
		int i,j;
	    //ALGORITMA
		this.NB=NB;
		this.NK=NK;
		for(i=1;i<=1000;i++){
			for(j=1;j<=1000;j++){
				this.el[i][j]=0;
			}
		}
    }

    public Matriks GaussToMatriks(){
		Matriks Mtemp = new Matriks(this.NB, this.NK);
		for(i=this.GetFirstIdxBrs();i<=this.GetLastIdxBrs();i++){
			for(j=this.GetFirstIdxKol();j<=this.GetLastIdxKol();j++){
				Mtemp.el[i][j]=this.el[i][j];
			}
		}
		return Mtemp;
	}

    Public MatriksGauss Echelon(boolean augmented){
        float temp, mult;
        int i,j,k, augLim, p,q;
        boolean Fnd;
        MatriksGauss Mtemp = new MatriksGauss(0,0);
        Mtemp.CopyMatriks(this);
        augLim = (augmented)? Mtemp.GetLastIdxKol() - 1 : Mtemp.GetLastIdxKol();
        j=Mtemp.GetFirstIdxBrs();
        for(i=Mtemp.GetFirstIdxKol();i<=augLim && j<= Mtemp.GetLastIdxBrs();i++){
            q = j-1; Fnd = false;
            do {
                q++;
                if (Mtemp.el[q][i]!=0){
                    Fnd = true;
                }
            } while (!Fnd && q<Mtemp.GetLastIdxBrs());

            if(Fnd){
                if (q!=j){
                    for(k = i; k<=Mtemp.GetLastIdxKol(); k++){
                        temp = Mtemp.el[q][k];
                        Mtemp.el[q][k] = Mtemp.el[j][k];
                        Mtemp.el[j][k] = temp;
                    }
                }
                temp = Mtemp.el[j][i];
                for(k=i;k<=Mtemp.GetLastIdxKol();k++){
                    Mtemp.el[j][k] /= temp;
                }

                for(k = j+1; k<= Mtemp.GetLastIdxBrs(); k++){
                    if (Mtemp.el[k][i]!=0){
                        mult = Mtemp.el[k][i]/Mtemp.el[j][i]; 
                        for (p=i; p<=Mtemp.GetLastIdxKol(); p++){
                            Mtemp.el[k][p] -= mult * Mtemp.el[j][p];
                        }
                    }
                } 
                j++;
            }    
        }
        return Mtemp;
    }


    Public MatriksGauss EchelonReduc(boolean augmented){
        float temp, mult;
        int i,j,k, augLim, c,r;
        boolean Fnd;
        MatriksGauss Mtemp = new MatriksGauss(0,0);
        Mtemp.CopyMatriks(this);
        augLim = (augmented)? GetLastIdxKol(Me) - 1 : GetLastIdxKol(Me);
        for(i= augLim;i>=Mtemp.GetFirstIdxKol();i--){
            Fnd = false;
            for(j= Mtemp.GetLastIdxBrs(); j>= Mtemp.GetFirstIdxBrs(); j--){
                if(Mtemp.el[j][i]!=0){
                    if (!Fnd){
                        Fnd = true;
                        r= j;
                    }
                    else{
                        mult = Mtemp.el[j][i] / Mtemp.el[r][i];
                        for(c=GetLastIdxKol(); c>= GetFirstIdxKol(); c--){
                            Mtemp.el[j][c] -= mult * Mtemp.el[r][c];
                        }
                    }
                }
            }
        }   
        return Mtemp;
    }

    

