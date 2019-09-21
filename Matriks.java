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
		int m,n;
		//ALGORITMA
		m=in.nextInt();
		n=in.nextInt();
		this.NB=m;
		this.NK=n;
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
		double tmp;
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
		double tmp;
		//ALGORITMA
		for(i=1;i<=this.NB;j++){
			tmp=this.el[i][s];
			this.el[i][s]=this.el[i][d];
			this.el[i][d]=tmp;
		}
	}
	
	int SearchBaris(Matriks M, int i, int X){
		//Mencari X di baris ke-i pada matriks M
		//return indeks, jika tidak ada akan mengembalikan nilai 0
		
		//KAMUS LOKAL
		int j,indeks;
		boolean found;
		//ALGORITMA
		indeks=0;
		found=false;
		for(j=GetFirstIdxKol(M);j<=GetLastIdxKol(M);j++){
			if(M.el[i][j]==X && !found){
				indeks=j;
				found=true;
			}
		}
		return indeks;
	}
	
	int SearchKolom(Matriks M, int j, int X){
		//Mencari X di kolom ke-j pada matriks M
		//return indeks, jika tidak ada akan mengembalikan nilai 0
		
		//KAMUS LOKAL
		int i,indeks;
		boolean found;
		//ALGORITMA
		indeks=0;
		found=false;
		for(i=GetFirstIdxBrs(M);i<=GetLastIdxBrs(M);i++){
			if(M.el[i][j]==X && !found){
				indeks=i;
				found=true;
			}
		}
		return indeks;
	}
	
	
	void GabungMatriks(Matriks M1, Matriks M2){
		//Prekondisi : Baris Matriks harus sama
		//I.S : M1, M2 terdefinisi
		//F.S : Membuat matriks augmented
		
		//KAMUS LOKAL
		int i,j;
		//ALGORITMA
		this.NK=M1.NK+M2.NK;
		for(i=GetFirstIdxBrs(M1);i<=GetLastIdxBrs(M1);i++){
			for(j=GetFirstIdxKol(M2);j<=GetLastIdxKol(M2);j++){
				this.el[i][j+GetLastIdxKol(M1)]=M2.el[i][j];
			}
		}
	}
	
	void CopyMATRIKS (Matriks MIn)
	// Melakukan assignment MHsl  MIn 
	{	// KAMUS LOKAL
		indeks i,j;
		// ALGORITMA
		
		for(i=GetFirstIdxBrs(MIn);i<=GetLastIdxBrs(MIn);i++){
			for(j=GetFirstIdxKol(MIn);j<=GetLastIdxKol(MIn);j++){
				this.el[i][j]=MIn.el[i][j];
			}
		}
	}
	
	void TambahKBaris(int RAw, int RAkh, int K){
		//I.S : Matriks terdefinisi
		//F.S : Menambahkan RAw dengan K kali RAkh
		
		//KAMUS LOKAL
		int j;
		//ALGORITMA
		for(j=1;j<=this.NK;j++){
			this.el[RAw][j] += K*this.el[RAkh][j];
		}
	}
	
	boolean IsBujurSangkar(Matriks M){
		//ALGORITMA
		return (this.NB==this.NK);
	}
	void ShrinkRow(int r){
		int i,j;
		
		if(r!=this.NB){
			for(i=r;i<=this.NB;i++){
				for(j=1;j<=this.NK;j++){
					this.el[i-1][j]=this.el[i][j];
				}
			}
		}
		this.NB-=1;
	}
	
	void ShrinkCol(int c){
		int i,j;
		
		if(c!=this.NK){
			for(j=c;j<=this.NK;j++){
				for(i=1;i<=this.NB;i++){
					this.el[i][j-1]=this.el[i][j];
				}
			}
		}
		this.NK-=1;
	}
	float Determinan(Matriks M){
		//KAMUS LOKAL
		int i,j;
		float res;
		//ALGORITMA
		if(IsBujurSangkar(M)){
			if(M.NB==1 && M.NK==1){
				return M.el[1][1];
			}else if(M.NB==2 && M.NK==2){
				return M.el[1][1]*M.el[2][2]-M.el[1][2]*M.el[2][1];
			}else{
				res=0;
				for(i=GetFirstIdxKol(M);i<=GetLastIdxKol(M);i++){
					if(i%2==1){
						res+=M.el[1][i]*Minor(M,1,i);
					}else{
						res-=M.el[1][i]*Minor(M,1,i);
					}
				}
				return res;
			}
		}
	}
	
	float Minor(Matriks M, int r, int c){
		//KAMUS LOKAL
		float res;
		int i,j;
		Matriks tmp;
		//ALGORITMA
		tmp=new Matriks();
		tmp=CopyMATRIKS(M);
		tmp.ShrinkCol(c);
		tmp.ShrinkRow(r);
		res=Determinan(tmp);
		return res;
	}
	
	Matriks Kofaktor(Matriks M){
		//KAMUS LOKAL
		int i,j;
		Matriks tmp;
		//ALGORITMA
		tmp=new Matriks();
		tmp=CopyMATRIKS(M);
		for(i=1;i<=M.NB;i++){
			for(j=1;j<=M.NK;j++){
				if((i+j)%2==0){
					tmp.el[i][j]=M.el[i][j]*Minor(M,i,j);
				}else{
					tmp.el[i][j]=-1*M.el[i][j]*Minor(M,i,j);
				}
			}
		}
		tmp.det=Determinan(tmp);
		return tmp;
	}
	Matriks Adjoin(Matriks M){
		//KAMUS LOKAL
		Matriks tmp=new Matriks();
		//ALGORITMA
		tmp=Kofaktor(M);
		tmp.Transpose();
		return tmp;
	}
}
