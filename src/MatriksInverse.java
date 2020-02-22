import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.lang.Math;

public class MatriksInverse extends Matriks{
    MatriksInverse(int NB, int NK){
        super(NB,NK);
    }

    public Matriks InverseToMatriks(){
        int i,j;
		Matriks Mtemp = new Matriks(this.NB, this.NK);
		for(i=this.GetFirstIdxBrs();i<=this.GetLastIdxBrs();i++){
			for(j=this.GetFirstIdxKol();j<=this.GetLastIdxKol();j++){
				Mtemp.el[i][j]=this.el[i][j];
			}
		}
		return Mtemp;
	}

    public float KofaktorVal(int i, int j){
        return ((float)Math.pow(-1, i+j)) * this.el[i][j] * this.Minor(i,j); 
    }

    public MatriksInverse Kofaktor(){
        MatriksInverse kM = new MatriksInverse(this.NB, this.NK);
        for(int i= this.GetFirstIdxBrs() ; i<= this.GetLastIdxBrs() ; i++){
            for(int j= this.GetFirstIdxKol();j<= this.GetLastIdxKol(); j++){
                kM.el[i][j] = KofaktorVal(i, j);
            }
        } 
        return kM;
    }

    public MatriksInverse Adjoint(){
        return this.Kofaktor().Transpose().MatriksToInverse();
    }

    public MatriksInverse Inverse(){
        return this.Adjoint().KaliKons(1/this.Determinan()).MatriksToInverse();
    }

    public void Solver(){
        System.out.println("Solusi dari SPL tersebut adalah");
        int i;
        for(i=this.GetFirstIdxBrs(); i<=this.GetLastIdxBrs(); i++){
            System.out.printf("X%d = %.3f%n", i, this.el[i][this.GetFirstIdxKol()]);
        }
    }

    public void writeInverse(){
        //KAMUS LOKAL
        int i;
        StringBuffer strBuff;
        String s;
        //ALGORITMA
        strBuff=new StringBuffer();

        try (FileWriter writer = new FileWriter("outputInverse.txt");
            BufferedWriter bw = new BufferedWriter(writer)) {
            strBuff.append("Solusi dari SPL tersebut adalah ");
            for(i=this.GetFirstIdxBrs(); i<=this.GetLastIdxBrs(); i++){
                strBuff.append("X");
                strBuff.append(i);
                strBuff.append(" = ");
                strBuff.append(this.el[i][this.GetFirstIdxKol()]);
                s=strBuff.toString();
                bw.write(s);
                bw.write(System.lineSeparator());       
            }
        } 
        catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
                
    }
}