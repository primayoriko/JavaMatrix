import java.util.Scanner;
import java.lang.*;

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

    public BigDecimal KofaktorVal(int i, int j){
        return (BigDecimal(-1).pow(i+j).multiply(this.el[i][j]).multiply(this.Minor(i,j))); 
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
        return this.Adjoint().KaliKons(BigDecimal.ONE.divide(this.Determinan(),10, RoundingMode.HALF_UP)).MatriksToInverse();
    }

    public void Solver(){
        System.out.println("Solusi dari SPL adalah");
        int i;
        for(i=this.GetFirstIdxBrs(); i<=this.GetLastIdxBrs(); i++){
            System.out.printf("X%d = %.3f%n", i, this.el[i][this.GetFirstIdxKol()]);
        }
    }
}
