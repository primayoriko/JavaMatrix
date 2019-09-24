import java.util.Scanner;
import java.lang.Math;

public class MatriksInverse extends Matriks{
    MatriksInverse(int NB, int NK){
        super(NB,NK);
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
        System.out.println("Solusi :");
        int i;
        for(i=this.GetFirstIdxBrs(); i<=this.GetLastIdxBrs(); i++){
            System.out.printf("X%d = %.3f%n", this.GetLastIdxBrs()-i+1, this.el[i][this.GetFirstIdxKol()]);
        }
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
}
