import java.util.Scanner;
import java.lang.Math;

public class MatriksInverse extends Matriks{
    MatriksInverse(int NB, int NK){
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

    public double KofaktorVal(int i, int j){
        return Math.pow(-1, i+j) * this.el[i][j] * this.Minor(i,j); 
    }

    public MatriksInverse KofaktorMatriks(){
        MatriksInverse kM = new MatriksInverse(this.NB, this.NK);
        for(int i= this.GetFirstIdxBrs() ; i<= this.GetLastIdxBrs() ; i++){
            for(int j= this.GetFirstIdxKol();j<= this.GetLastIdxKol(); j++){
                kM.el[i][j] = KofaktorVal(i, j);
            }
        } 
        return kM;
    }

    public MatriksInverse AdjointMatriks(){
        float temp;
        MatriksInverse adjM = this.KofaktorMatriks();
        for(int i= this.GetFirstIdxBrs() ; i<= this.GetLastIdxBrs() ; i++){
            for(int j= this.GetFirstIdxKol();j<= this.GetLastIdxKol(); j++){
                temp = adjM.el[i][j];
                adjM.el[i][j] = adjM.el[j][i];
                adjM.el[j][i] = temp;
            }
        } 
        return adjM;
    }

    public MatriksInverse Inverse(){
        if(this.Determinan() == 0){
            System.out.println("Error, determinan tidak boleh nol");
            return this;
        }
        else{
            return this.AdjointMatriks().KaliKons(1/this.Determinan());
        }
    }

    public Matriks InverseToMatriks(){
		Matriks Mtemp = new Matriks(this.NB, this.NK);
		for(i=this.GetFirstIdxBrs();i<=this.GetLastIdxBrs();i++){
			for(j=this.GetFirstIdxKol();j<=this.GetLastIdxKol();j++){
				Mtemp.el[i][j]=this.el[i][j];
			}
		}
		return Mtemp;
	}
}
