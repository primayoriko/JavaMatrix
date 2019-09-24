import System.util.Scanner;
import java.lang.Math;

public class MatriksInterpolasi extends Matriks{
    MatriksInterpolasi(Matriks M){
        this.CopyMatriks(M);
    }

    public void BacaInterpolasi(){
        int i,j,deg;
        deg = input.nextInt();

        Matriks dat = new Matriks(deg, deg+1);
        float a,b;
        for(i=dat.GetFirstIdxBrs(); i<=dat.GetLastIdxBrs(); i++){
            a = input.nextFloat();
            b = input.nextFloat();
            for(j=dat.GetFirstIdxKol(); j<=dat.GetLastIdxKol() - 1; j++){
                dat.el[i][j] = ((float)pow(a, GetLastIdxKol()-j));
            }
            dat.el[i][dat.GetLastIdxKol()]=b;
        }
        
    }

    public void InterpolationSolver(){
        int i,j, cnt,fIdx;
        boolean loop = true;
        for(i=this.GetLastIdxBrs(); i>=this.GetFirstIdxBrs() && loop; i--){
            cnt=0;
            fIdx=-1;
            for(j=this.GetLastIdxKol() - 1; j>=this.GetFirstIdxKol(); j--){
                if(this.el[i][j]!=0){
                    if(cnt==0)fIdx = j;
                    cnt++;                
                }
            }
            if(cnt==0 && this.el[i][this.GetLastIdxKol()]!=0){
                System.out.printf("Tidak Ada Solusi Valid%n");
                loop = false;
            }
            else if (cnt > 1){
                System.out.printf("koefisien x tidak unik%n");
                loop = false;
            }
            else if(cnt==1){
                for(j=i-1; j>=this.GetFirstIdxBrs(); j--){
                    this.el[j][This.GetLastIdxKol()]-=this.el[j][fIdx] * this.el[i][this.GetLastIdxKol()];
                    this.el[j][fIdx] = 0;
                }
            }
        }
        if (loop){
            System.out.printf("f(X) = ");
            cnt = 0;
            for(i=GetFirstIdxBrs(M);i<=GetLastIdxBrs(M);i++){
                if(Elmt(M,i,GetLastIdxKol(M))!= 0){
                    loop = true;
                    for(j=GetFirstIdxKol(M);j<=GetLastIdxKol(M)-1 && loop;j++){
                        if(this.el[i][j]!=0){
                            if(cnt!=0) printf(" + ");
                            printf("%.2fX^%d", this.el[i][this.GetLastIdxKol()], this.GetLastIdxKol()-j);
                            loop = false;
                            cnt++;
                        }
                    }
                }
            }
            printf("%n");
        }
    }
}