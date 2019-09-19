import java.util.Scanner;

public class Matriks{
    //Atribut
	int NB;
	int NK;
	double[][] el = new int[1000][1000];
	double det;
	
	//METHOD:
	//Konstruktor
	Matriks(){
	//KAMUS LOKAL
		int i,j;
	//ALGORITMA
		this.NB=0;
		this.NK=0;
		for(i=1;i<=1000;i++){
			for(j=1;j<=1000;j++){
				this.el[i][j]=0;
			}
		}
		this.det=0;
	}
	//Input
	void BacaMATRIKS(){
		//KAMUS LOKAL
		Scanner in = new Scanner(System.in);
		int i,j;
		//ALGORITMA
		for(i=1;i<=this.NB;i++){
			for(j=1;j<=this.NK;j++){
				this.el[i][j]=in.nextDouble();
			}
		}
	}
	//Output
	void TulisMATRIKS(){
		//KAMUS LOKAL
		int i,j;
		//ALGORITMA
		for(i=1;i<=this.NB;i++){
			for(j=1;j<=this.NK;j++){
				if(j!=this.NK){
					System.out.print(this.el[i][j]+" ");
				}else{
					System.out.println(this.el[i][j]);
				}
			}
		}
	}
	
	void Transpose(){
		//KAMUS LOKAL
		int i,j,b,k;
		double tmp;
		//ALGORITMA
		for(i=1;i<=this.NB;i++){
			for(j=1;j<=this.NK;j++){
				tmp=this.el[i][j];
				this.el[i][j]=this.el[j][i];
				this.el[j][i]=tmp;
			}
		}
		b=this.NB;
		k=this.NK;
		this.NB=k;
		this.NK=b;
	}
	
	void KaliKons(int K){
		//KAMUS LOKAL
		int i,j;
		//ALGORITMA
		for(i=1;i<=this.NB;i++){
			for(j=1;j<=this.NK;j++){
				this.el[i][j]=K*this.el[i][j];
			}
		}
		this.NB=M1.NB;
		this.NK=M1.NK;
	}
	
	void JumlahMATRIKS(Matriks M1, Matriks M2){
		//KAMUS LOKAL
		int i,j;
		//ALGORITMA
		for(i=1;i<=this.NB;i++){
			for(j=1;j<=this.NK;j++){
				this.el[i][j]=M1.el[i][j]+M2.el[i][j];
			}
		}
		this.NB=M1.NB;
		this.NK=M1.NK;
	}
	
	void KurangMATRIKS(Matriks M1, Matriks M2){
		//KAMUS LOKAL
		int i,j;
		//ALGORITMA
		for(i=1;i<=this.NB;i++){
			for(j=1;j<=this.NK;j++){
				this.el[i][j]=M1.el[i][j]-M2.el[i][j];
			}
		}
		this.NB=M1.NB;
		this.NK=M1.NK;
	}
	
	void KaliMATRIKS(Matriks M1, Matriks M2){
		//KAMUS LOKAL
		int i,j,k;
		//ALGORITMA
		for(i=1;i<=M1.NB;i++){
			for(j=1;j<=M2.NK);j++){
				this.el[i][j]=0;
				for(k=1;k<=M1.NK;k++){
					this.el[i][j]+=M1.el[i][k]*M2.el[k][j];
				}
			}
		}
		this.NB=M1.NB;
		this.NK=M2.NK;
	}
	
	boolean IsEQ(Matriks M1, Matriks M2){
		//KAMUS LOKAL
		int i,j;
		boolean eq;
		//ALGORITMA
		if(M1.NB==M2.NB && M1.NK==M2.NK){
			eq=true;
			i=1;
			while(i<=M1.NB && eq){
				j=1;
				while(j<=M1.NK && eq){
					if(M1.el[i][j]!=M2.el[i][j]){
						eq=false;
					}
					j++;
				}
				i++;
			}
		}else{
			eq=false;
		}
		return eq;
	}
	void SwapBaris(int S, int D){
		//KAMUS LOKAL
		int j;
		int tmp;
		//ALGORITMA
		for(j=1;j<=this.NK;j++){
			tmp=this.el[s][j];
			this.el[s][j]=this.el[d][j];
			this.el[d][j]=tmp;
		}
	}
	void SwapKolom(int S, int D){
		//KAMUS LOKAL
		int i;
		int tmp;
		//ALGORITMA
		for(i=1;i<=this.NB;j++){
			tmp=this.el[i][s];
			this.el[i][s]=this.el[i][d];
			this.el[i][d]=tmp;
		}
	}
}
