import java.util.Scanner;
import java.lang.Math;
import java.io.*;

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
                MI.el[i][j] = ((float)Math.pow(this.el[i][1], MI.GetLastIdxKol()-j-1));
            }
            MI.el[i][MI.GetLastIdxKol()]=this.el[i][2];
        }
        return MI;
    }

    public void writeInterpolasi(){
        //KAMUS LOKAL
        boolean loop = true;
        int i,j,cnt,fIdx;
        StringBuffer strBuff;
        String s;
        //ALGORITMA
        strBuff=new StringBuffer();

        try (FileWriter writer = new FileWriter("outputInterpolasi.txt");
            BufferedWriter bw = new BufferedWriter(writer)) {
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
                    strBuff.append("Tidak Ada Solusi Valid");
                    s=strBuff.toString();
                    bw.write(s);
                    bw.write(System.lineSeparator());
                    strBuff.delete(0,strBuff.length());
                        System.out.println("Tidak Ada Solusi Valid");
                        loop = false;
                    }
                    else if (cnt > 1){
                    strBuff.append("Koefisien X tidak unik");
                    s=strBuff.toString();
                    bw.write(s);
                    bw.write(System.lineSeparator());
                    strBuff.delete(0,strBuff.length());
                        System.out.println("Koefisien X tidak unik");
                        loop = false;
                    }
                    else if(cnt==1){
                        for(j=i-1; j>=this.GetFirstIdxBrs(); j--){
                            this.el[j][this.GetLastIdxKol()]-=this.el[j][fIdx] * this.el[i][this.GetLastIdxKol()];
                            this.el[j][fIdx] = 0;
                        }
                    }
                }
                if (loop){
                strBuff.append("Tidak Ada Solusi Valid");
                    s=strBuff.toString();
                    bw.write(s);
                    bw.write(System.lineSeparator());
                    strBuff.delete(0,strBuff.length());
                    System.out.println("Solusi dari Interpolasi titik-titik tersebut adalah");
                    strBuff.append("f(X) = ");
        
                    System.out.print("f(X) = ");
                    cnt = 0;
                    for(i=this.GetFirstIdxBrs();i<=this.GetLastIdxBrs();i++){
                        if(this.el[i][this.GetLastIdxKol()]!= 0){
                            loop = true;
                            for(j=this.GetFirstIdxKol();j<=this.GetLastIdxKol()-1 && loop;j++){
                                if(this.el[i][j]!=0){
                                    if(cnt!=0 && this.el[i][this.GetLastIdxKol()]>0) {
                                    strBuff.append(" + ");
                                    System.out.printf(" + ");
                                    }
                                    if(this.el[i][this.GetLastIdxKol()]<0){
                                    strBuff.append(" - ");
                                    System.out.printf(" - ");
                                    }
                                    strBuff.append(Math.abs(this.el[i][this.GetLastIdxKol()]));
                                    System.out.printf("%.2f", Math.abs(this.el[i][this.GetLastIdxKol()]));
                                    if(this.GetLastIdxKol()-j-1 != 0) {
                                    strBuff.append("X^");
                                    strBuff.append(this.GetLastIdxKol()-j-1);
                                    System.out.printf("X^%d", this.GetLastIdxKol()-j-1);
                                    }
                                    loop = false;
                                    cnt++;
                                }
                            }
                        }
                    }
                    s=strBuff.toString();
                    bw.write(s);
                    bw.write(System.lineSeparator());
                    System.out.printf("%n");
                }
                
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }    
    }
}

