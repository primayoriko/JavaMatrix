import java.util.Scanner;

public class Matriks{
    //Atribut
	int NB;
	int NK;
	float[][] el = new float[1001][1001];
	
	//METHOD:
	//Konstruktor
	Matriks(int nb, int nk){
	//KAMUS LOKAL
		int i,j;
	//ALGORITMA
		this.NB=nb;
		this.NK=nk;
		for(i=1;i<=1000;i++){
			for(j=1;j<=1000;j++){
				this.el[i][j]=0;
			}
		}
	}
	//Input
	public void BacaMATRIKS(){
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
				this.el[i][j]=in.nextFloat();
			}
		}
	}
	//Output
	public void TulisMATRIKS(){
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
	
	public int GetFirstIdxKol(){
		//ALGORITMA
		return 1;
	}
	  
	public int GetFirstIdxBrs(){
		//ALGORITMA
		return 1;
	}
	  
	public int GetLastIdxKol(){
		//ALGORITMA
		return this.NK;
	}
	  
	public int GetLastIdxBrs(){
		//ALGORITMA
		return this.NB;
	}
	
	public Matriks Transpose(){
		//KAMUS LOKAL
		int i,j,b,k;
		float tmp;
		Matriks Mtmp;
		//ALGORITMA
		Mtmp=new Matriks(this.NB,this.NK);
		for(i=1;i<=this.NB;i++){
			for(j=1;j<=this.NK;j++){
				tmp=Mtmp.el[i][j];
				Mtmp.el[i][j]=Mtmp.el[j][i];
				Mtmp.el[j][i]=tmp;
			}
		}
		return Mtmp;
	}
	
	public Matriks KaliKons(int K){
		//KAMUS LOKAL
		int i,j;
		Matriks tmp;
		//ALGORITMA
		tmp=new Matriks(this.NB,this.NK);
		for(i=1;i<=this.NB;i++){
			for(j=1;j<=this.NK;j++){
				tmp.el[i][j]=K*this.el[i][j];
			}
		}
		return tmp;
	}
	
	public Matriks JumlahMATRIKS(Matriks M1, Matriks M2){
		//KAMUS LOKAL
		int i,j;
		Matriks tmp;
		//ALGORITMA
		tmp=new Matriks(this.NB,this.NK);
		for(i=1;i<=this.NB;i++){
			for(j=1;j<=this.NK;j++){
				tmp.el[i][j]=M1.el[i][j]+M2.el[i][j];
			}
		}
		return tmp;
	}
	
	public Matriks KurangMATRIKS(Matriks M1, Matriks M2){
		//KAMUS LOKAL
		int i,j;
		Matriks tmp;
		//ALGORITMA
		tmp=new Matriks(this.NB,this.NK);
		for(i=1;i<=this.NB;i++){
			for(j=1;j<=this.NK;j++){
				tmp.el[i][j]=M1.el[i][j]-M2.el[i][j];
			}
		}
		return tmp;
	}
	
	public Matriks KaliMATRIKS(Matriks M1, Matriks M2){
		//KAMUS LOKAL
		int i,j,k;
		Matriks tmp;
		//ALGORITMA
		tmp=new Matriks(M1.NB,M2.NK);
		for(i=1;i<=M1.NB;i++){
			for(j=1;j<=M2.NK;j++){
				tmp.el[i][j]=0;
				for(k=1;k<=M1.NK;k++){
					tmp.el[i][j]+=M1.el[i][k]*M2.el[k][j];
				}
			}
		}
		return tmp;
	}
	
	public boolean IsBujurSangkar(Matriks M){
		//ALGORITMA
		return (this.NB==this.NK);
	}
	
	public boolean IsEQ(Matriks M1, Matriks M2){
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
	
	public void SwapBaris(int s, int d){
		//KAMUS LOKAL
		int j;
		float tmp;
		//ALGORITMA
		for(j=1;j<=this.NK;j++){
			tmp=this.el[s][j];
			this.el[s][j]=this.el[d][j];
			this.el[d][j]=tmp;
		}
	}
	public void SwapKolom(int s, int d){
		//KAMUS LOKAL
		int i;
		float tmp;
		//ALGORITMA
		for(i=1;i<=this.NB;i++){
			tmp=this.el[i][s];
			this.el[i][s]=this.el[i][d];
			this.el[i][d]=tmp;
		}
	}
	
	public int SearchBaris(Matriks M, int i, int X){
		//Mencari X di baris ke-i pada matriks M
		//return indeks, jika tidak ada akan mengembalikan nilai 0
		
		//KAMUS LOKAL
		int j,indeks;
		boolean found;
		//ALGORITMA
		indeks=0;
		found=false;
		for(j=M.GetFirstIdxKol();j<=M.GetLastIdxKol();j++){
			if(M.el[i][j]==X && !found){
				indeks=j;
				found=true;
			}
		}
		return indeks;
	}
	
	public int SearchKolom(Matriks M, int j, int X){
		//Mencari X di kolom ke-j pada matriks M
		//return indeks, jika tidak ada akan mengembalikan nilai 0
		
		//KAMUS LOKAL
		int i,indeks;
		boolean found;
		//ALGORITMA
		indeks=0;
		found=false;
		for(i=M.GetFirstIdxBrs();i<=M.GetLastIdxBrs();i++){
			if(M.el[i][j]==X && !found){
				indeks=i;
				found=true;
			}
		}
		return indeks;
	}
	
	
	public void GabungMatriks(Matriks M1, Matriks M2){
		//Prekondisi : Baris Matriks harus sama
		//I.S : M1, M2 terdefinisi
		//F.S : Membuat matriks augmented
		
		//KAMUS LOKAL
		int i,j;
		//ALGORITMA
		this.NK=M1.NK+M2.NK;
		for(i=M1.GetFirstIdxBrs();i<=M1.GetLastIdxBrs();i++){
			for(j=M2.GetFirstIdxKol();j<=M2.GetLastIdxKol();j++){
				this.el[i][j+M1.GetLastIdxKol()]=M2.el[i][j];
			}
		}
	}
	
	public void CopyMATRIKS (Matriks MIn)
	// Melakukan assignment Matriks ini dengan MIn 
	{	// KAMUS LOKAL
		int i,j;
		// ALGORITMA
		tmp=new Matriks();
		for(i=GetFirstIdxBrs(MIn);i<=GetLastIdxBrs(MIn);i++){
			for(j=GetFirstIdxKol(MIn);j<=GetLastIdxKol(MIn);j++){
				this.el[i][j]=MIn.el[i][j];
			}
		}
		this.NB=MIn.NB;
		this.NK=MIn.NK;
	}
	
	public void TambahKBaris(int RAw, int RAkh, int K){
		//I.S : Matriks terdefinisi
		//F.S : Menambahkan RAw dengan K kali RAkh
		
		//KAMUS LOKAL
		int j;
		//ALGORITMA
		for(j=1;j<=this.NK;j++){
			this.el[RAw][j] += K*this.el[RAkh][j];
		}
	}
	
	public void ShrinkRow(int r){
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
	
	public void ShrinkCol(int c){
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
	
	public float Minor(Matriks M, int r, int c){
		//KAMUS LOKAL
		float res;
		int i,j;
		Matriks tmp;
		//ALGORITMA
		tmp=new Matriks(M.NB,M.NK);
		tmp=CopyMATRIKS(M);
		tmp.ShrinkCol(c);
		tmp.ShrinkRow(r);
		res=Determinan(tmp);
		return res;
	}
	
	public float Determinan(Matriks M){
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
				for(i=M.GetFirstIdxKol();i<=M.GetLastIdxKol();i++){
					if(i%2==1){
						res+=M.el[1][i]*Minor(M,1,i);
					}else{
						res-=M.el[1][i]*Minor(M,1,i);
					}
				}
				return res;
			}
        }else{
        	return -99999;
        }
	}
	
}
