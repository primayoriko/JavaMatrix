import java.util.Scanner;
import java.lang.Math;
import java.math.*;

public class MatriksInterpolasi extends Matriks{
    MatriksInterpolasi(int NB, int NK){
        super(NB, NK);
    }

    public MatriksGauss InterpolasiToGauss(){
        int i,j;
		MatriksGauss Mtemp = new MatriksGauss(this.NB, this.NK);
		for(i=this.GetFirstIdxBrs();i<=this.GetLastIdxBrs();i++){
			for(j=this.GetFirstIdxKol();j<=this.GetLastIdxKol();j++){
				Mtemp.el[i][j]=this.el[i][j];
			}
		}
		return Mtemp;
    } 

    public MatriksInterpolasi InterpretasiData(){
        int i,j;
        MatriksInterpolasi MI = new MatriksInterpolasi(this.GetLastIdxBrs(), this.GetLastIdxBrs()+1);
        for(i=MI.GetFirstIdxBrs(); i<=MI.GetLastIdxBrs(); i++){
            for(j=MI.GetFirstIdxKol(); j<=MI.GetLastIdxKol() - 1; j++){
                MI.el[i][j] = this.el[i][1].pow(MI.GetLastIdxKol()-j-1);
            }
            MI.el[i][MI.GetLastIdxKol()]=this.el[i][2];
        }
        return MI;
    }

    public void Solver(){
        int i,j, cnt,fIdx;
        boolean loop = true;
        for(i=this.GetLastIdxBrs(); i>=this.GetFirstIdxBrs() && loop; i--){
            cnt=0;
            fIdx=-1;
            for(j=this.GetLastIdxKol() - 1; j>=this.GetFirstIdxKol(); j--){
                if(this.el[i][j].compareTo(BigDecimal.ZERO)!=0){
                    if(cnt==0)fIdx = j;
                    cnt++;                
                }
            }
            if(cnt==0 && this.el[i][this.GetLastIdxKol()].compareTo(BigDecimal.ZERO)!=0){
                System.out.println("Tidak Ada Solusi Valid");
                loop = false;
            }
            else if (cnt > 1){
                System.out.println("Koefisien X tidak unik");
                loop = false;
            }
            else if(cnt==1){
                for(j=i-1; j>=this.GetFirstIdxBrs(); j--){
                    this.el[j][this.GetLastIdxKol()]=this.el[j][this.GetLastIdxKol()].subtract(this.el[j][fIdx].multiply(this.el[i][this.GetLastIdxKol()]));
                    this.el[j][fIdx] = BigDecimal.ZERO;
                }
            }
        }
        if (loop){
            System.out.println("Solusi dari Interpolasi titik-titik tersebut adalah");
            System.out.print("f(X) = ");
            cnt = 0;
            for(i=this.GetFirstIdxBrs();i<=this.GetLastIdxBrs();i++){
                if(this.el[i][this.GetLastIdxKol()].compareTo(BigDecimal.ZERO)!= 0){
                    loop = true;
                    for(j=this.GetFirstIdxKol();j<=this.GetLastIdxKol()-1 && loop;j++){
                        if(this.el[i][j].compareTo(BigDecimal.ZERO)!=0){
                            if(cnt!=0 && this.el[i][this.GetLastIdxKol()].compareTo(BigDecimal.ZERO)>0) System.out.printf(" + ");
                            if(this.el[i][this.GetLastIdxKol()].compareTo(BigDecimal.ZERO)<0) System.out.printf(" - ");
                            System.out.printf("%.2f", this.el[i][this.GetLastIdxKol()].abs());
                            if(this.GetLastIdxKol()-j-1 != 0) System.out.printf("X^%d", this.GetLastIdxKol()-j-1);
                            loop = false;
                            cnt++;
                        }
                    }
                }
            }
            System.out.printf("%n");
        }
    }
}
