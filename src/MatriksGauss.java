import java.util.Scanner;
import java.lang.Math;
import java.util.*;
import java.io.*;

public class MatriksGauss extends Matriks{
    MatriksGauss(int NB, int NK){
        super(NB,NK);
    }

    public Matriks GaussToMatriks(){
        int i,j;
		Matriks Mtemp = new Matriks(this.NB, this.NK);
		for(i=this.GetFirstIdxBrs();i<=this.GetLastIdxBrs();i++){
			for(j=this.GetFirstIdxKol();j<=this.GetLastIdxKol();j++){
				Mtemp.el[i][j]=this.el[i][j];
			}
		}
		return Mtemp;
	}

    public MatriksInterpolasi GaussToInterpolasi(){
        int i,j;
		MatriksInterpolasi Mtemp = new MatriksInterpolasi(this.NB, this.NK);
		for(i=this.GetFirstIdxBrs();i<=this.GetLastIdxBrs();i++){
			for(j=this.GetFirstIdxKol();j<=this.GetLastIdxKol();j++){
				Mtemp.el[i][j]=this.el[i][j];
			}
		}
		return Mtemp;
    } 

    public MatriksGauss Echelon(boolean augmented){
        float temp, mult;
        int i,j,k, augLim, p,q;
        boolean Fnd;
        MatriksGauss Mtemp = new MatriksGauss(0,0);
        Mtemp = this.CopyMATRIKS().MatriksToGauss();
        augLim = (augmented)? Mtemp.GetLastIdxKol() - 1 : Mtemp.GetLastIdxKol();
        j= Mtemp.GetFirstIdxBrs();
        for(i=Mtemp.GetFirstIdxKol(); i<=augLim && j<= Mtemp.GetLastIdxBrs(); i++){
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


    public MatriksGauss EchelonReduc(boolean augmented){
        float temp, mult;
        int i,j,k, augLim, c,r;
        boolean Fnd;
        MatriksGauss Mtemp = new MatriksGauss(0,0);
        Mtemp = this.CopyMATRIKS().MatriksToGauss();
        augLim = (augmented)? Mtemp.GetLastIdxKol() - 1 : Mtemp.GetLastIdxKol();
        for(i= augLim;i>=Mtemp.GetFirstIdxKol();i--){
            Fnd = false; r=-1;
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

    public void Solver(){
        int i,j, k,cnt;
        int[] nNull = new int[this.GetLastIdxBrs()+1]; 
        int[] fIdx = new int[this.GetLastIdxBrs()+1]; 
        int[] lIdx = new int[this.GetLastIdxBrs()+1]; 
        boolean loop = true;
        for(i=this.GetLastIdxBrs(); i>=this.GetFirstIdxBrs() && loop; i--){
            cnt=0;
            fIdx[i]=-1;
            for(j=this.GetLastIdxKol() - 1; j>=this.GetFirstIdxKol(); j--){
                if(this.el[i][j]!=0){
                    if(cnt==0)fIdx[i] = j;
                    lIdx[i]= j;
                    cnt++;                
                }
            }
            nNull[i]=cnt;
            if(cnt==0 && this.el[i][this.GetLastIdxKol()]!=0){
                System.out.printf("Tidak Ada Solusi Valid%n");
                loop = false;
            }
            else if(cnt==1){
                for(j=i-1; j>=this.GetFirstIdxBrs(); j--){
                    this.el[j][this.GetLastIdxKol()]-=this.el[j][fIdx[i]] * this.el[i][this.GetLastIdxKol()];
                    this.el[j][fIdx[i]] = 0;
                }
            }
        }
        this.writeGauss(nNull,fIdx,lIdx,loop);
        if (loop){
            System.out.println("Solusi dari SPL tersebut adalah");
            for(i=this.GetFirstIdxBrs(); i<=this.GetLastIdxBrs(); i++){
                if(nNull[i]==1){
                    if (this.el[i][fIdx[i]]!=1) System.out.printf("%.2f", this.el[i][fIdx[i]]); 
                    System.out.printf("X%d ", fIdx[i]);
                    System.out.printf("= %.3f", this.el[i][this.GetLastIdxKol()]);
                    System.out.printf("%n");
                }
                else if(nNull[i]>1){
                    cnt = 0;
                    for(j=lIdx[i]; cnt < nNull[i]-1; j++){
                        if(this.el[i][j]!=0){
                            System.out.printf("X%d ", j);
                            System.out.printf("= t%d", j);
                            System.out.printf("%n");
                            cnt++;
                        }
                    }

                    System.out.printf("X%d =", fIdx[i]);
                    if (this.el[i][this.GetLastIdxKol()]!=0) System.out.printf("%.3f", this.el[i][this.GetLastIdxKol()]);
                    cnt = 0;
                    for(j=lIdx[i]; cnt < nNull[i]-1; j++){
                        if(this.el[i][j]!=0){
                            System.out.printf(" - ");
                            if (this.el[i][j]!=1) System.out.printf("%.2f", this.el[i][j]); 
                            System.out.printf("t%d", j);
                            cnt++;
                        }
                    }
                    System.out.printf("%n");
                }
            }
        }
    }

   public void writeGauss(int[] nNull, int[] fIdx, int[] lIdx, boolean loop){
        //KAMUS LOKAL
        int i,j, k, cnt;
        StringBuffer strBuff;
        String s;
        //ALGORITMA
        strBuff=new StringBuffer();

        try (FileWriter writer = new FileWriter("outputGauss.txt");
            BufferedWriter bw = new BufferedWriter(writer)) {
        if(loop){
            strBuff.append("Solusi dari SPL tersebut adalah");
            s=strBuff.toString();
            bw.write(s);
            bw.write(System.lineSeparator());
            strBuff.delete(0,strBuff.length());
            for(i=this.GetFirstIdxBrs(); i<=this.GetLastIdxBrs(); i++){
                if(nNull[i]==1){
                    if (this.el[i][fIdx[i]]!=1){
                        strBuff.append(this.el[i][fIdx[i]]);
                        System.out.printf("%.2f", this.el[i][fIdx[i]]); 
                    }
                    strBuff.append("X");
                    strBuff.append(fIdx[i]);
                    System.out.printf("X%d ", fIdx[i]);
                    strBuff.append(" = ");
                    strBuff.append(this.el[i][this.GetLastIdxKol()]);
                    System.out.printf("= %.3f", this.el[i][this.GetLastIdxKol()]);
                    s=strBuff.toString();
                    bw.write(s);
                    bw.write(System.lineSeparator());
                    System.out.printf("%n");
                    strBuff.delete(0,strBuff.length());
                }
                else if(nNull[i]>1){
                    cnt = 0;
                    for(j=lIdx[i]; cnt < nNull[i]-1; j++){
                        if(this.el[i][j]!=0){
                            strBuff.append("X");
                            strBuff.append(j);
                            System.out.printf("X%d ", j);
                            strBuff.append(" = ");
                            strBuff.append(j);
                            System.out.printf("= t%d", j);
                            System.out.printf("%n");
                            s=strBuff.toString();
                            bw.write(s);
                            bw.write(System.lineSeparator());
                            strBuff.delete(0,strBuff.length());
                            cnt++;
                        }
                    }
                    strBuff.append("X");
                    strBuff.append(fIdx[i]);
                    strBuff.append(" =");
                    System.out.printf("X%d =", fIdx[i]);
                    if (this.el[i][this.GetLastIdxKol()]!=0){
                        strBuff.append(this.el[i][this.GetLastIdxKol()]);
                        System.out.printf("%.3f", this.el[i][this.GetLastIdxKol()]);
                    }
                    cnt = 0;
                    for(j=lIdx[i]; cnt < nNull[i]-1; j++){
                        if(this.el[i][j]!=0){
                            strBuff.append(" - ");
                            System.out.printf(" - ");
                            if (this.el[i][j]!=1) {
                                strBuff.append(this.el[i][j]);
                                System.out.printf("%.2f", this.el[i][j]); 
                            }
                                strBuff.append("t");
                                strBuff.append(j);
                                System.out.printf("t%d", j);
                                cnt++;
                        }
                    }
                    s=strBuff.toString();
                    bw.write(s);
                    bw.write(System.lineSeparator());
                    System.out.printf("%n");
                }
            }
        }
        else{
            strBuff.append("Tidak ada solusi valid");
            s=strBuff.toString();
            bw.write(s);
        }
        } 
        catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    } 
}

    

