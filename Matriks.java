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
	
	public Matriks Transpose(){
		//KAMUS LOKAL
		int i,j,b,k;
		float tmp;
		Matriks Mtmp;
		//ALGORITMA
		Mtmp=new Matriks(this.NB,this.NK);
		Mtmp=this.CopyMATRIKS();
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
	public boolean IsEmpty(){
		//KAMUS LOKAL

		//ALGORITMA
		return (this.NB==0 && this.NK==0);
	}
	
	public void ResizeMATRIKS(int NB,int NK){
		this.NK=NK;
		this.NB=NB;
	}
	public Matriks JumlahMATRIKS(Matriks M2){
		//KAMUS LOKAL
		int i,j;
		Matriks tmp;
		//ALGORITMA
		tmp=new Matriks(this.NB,this.NK);
		for(i=1;i<=this.NB;i++){
			for(j=1;j<=this.NK;j++){
				tmp.el[i][j]=this.el[i][j]+M2.el[i][j];
			}
		}
		return tmp;
	}
	
	public Matriks KurangMATRIKS(Matriks M2){
		//KAMUS LOKAL
		int i,j;
		Matriks tmp;
		//ALGORITMA
		tmp=new Matriks(this.NB,this.NK);
		for(i=1;i<=this.NB;i++){
			for(j=1;j<=this.NK;j++){
				tmp.el[i][j]=this.el[i][j]-M2.el[i][j];
			}
		}
		return tmp;
	}
	
	public Matriks KaliMATRIKS(Matriks M2){
		//KAMUS LOKAL
		int i,j,k;
		Matriks tmp;
		//ALGORITMA
		tmp=new Matriks(this.NB,M2.NK);
		for(i=1;i<=this.NB;i++){
			for(j=1;j<=M2.NK;j++){
				tmp.el[i][j]=0;
				for(k=1;k<=this.NK;k++){
					tmp.el[i][j]+=this.el[i][k]*M2.el[k][j];
				}
			}
		}
		return tmp;
	}
	
	public boolean IsEQ(Matriks M2){
		//KAMUS LOKAL
		int i,j;
		boolean eq;
		//ALGORITMA
		if(this.NB==M2.NB && this.NK==M2.NK){
			eq=true;
			i=1;
			while(i<=this.NB && eq){
				j=1;
				while(j<=this.NK && eq){
					if(this.el[i][j]!=M2.el[i][j]){
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
	
	public int SearchBaris(int i, int X){
		//Mencari X di baris ke-i pada matriks M
		//return indeks, jika tidak ada akan mengembalikan nilai 0
		
		//KAMUS LOKAL
		int j,indeks;
		boolean found;
		//ALGORITMA
		indeks=0;
		found=false;
		for(j=this.GetFirstIdxKol();j<=this.GetLastIdxKol();j++){
			if(this.el[i][j]==X && !found){
				indeks=j;
				found=true;
			}
		}
		return indeks;
	}
	
	public int SearchKolom(int j, int X){
		//Mencari X di kolom ke-j pada matriks M
		//return indeks, jika tidak ada akan mengembalikan nilai 0
		
		//KAMUS LOKAL
		int i,indeks;
		boolean found;
		//ALGORITMA
		indeks=0;
		found=false;
		for(i=this.GetFirstIdxBrs();i<=this.GetLastIdxBrs();i++){
			if(this.el[i][j]==X && !found){
				indeks=i;
				found=true;
			}
		}
		return indeks;
	}
	
	
	public void GabungMatriks(Matriks M2){
		//Prekondisi : Baris Matriks harus sama
		//I.S : M1, M2 terdefinisi
		//F.S : Membuat matriks augmented
		
		//KAMUS LOKAL
		int i,j;
		//ALGORITMA
		this.NK=this.NK+M2.NK;
		for(i=this.GetFirstIdxBrs();i<=this.GetLastIdxBrs();i++){
			for(j=M2.GetFirstIdxKol();j<=M2.GetLastIdxKol();j++){
				this.el[i][j+this.GetLastIdxKol()]=M2.el[i][j];
			}
		}
	}
	
	public Matriks CopyMATRIKS ()
	// Melakukan assignment MHsl  MIn 
	{	// KAMUS LOKAL
		int i,j;
		Matriks tmp;
		// ALGORITMA
		tmp=new Matriks(this.NB,this.NK);
		for(i=this.GetFirstIdxBrs();i<=this.GetLastIdxBrs();i++){
			for(j=this.GetFirstIdxKol();j<=this.GetLastIdxKol();j++){
				tmp.el[i][j]=this.el[i][j];
			}
		}
		return tmp;
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
	
	public boolean IsBujurSangkar(){
		//ALGORITMA
		return (this.NB==this.NK);
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
	public float Determinan(){
		//KAMUS LOKAL
		int i,j;
		float res;
		//ALGORITMA
		if(this.IsBujurSangkar()){
			if(this.NB==1 && this.NK==1){
				return this.el[1][1];
			}else if(this.NB==2 && this.NK==2){
				return this.el[1][1]*this.el[2][2]-this.el[1][2]*this.el[2][1];
			}else{
				res=0;
				for(i=this.GetFirstIdxKol();i<=this.GetLastIdxKol();i++){
					if(i%2==1){
						res+=this.el[1][i]*this.Minor(1,i);
					}else{
						res-=this.el[1][i]*this.Minor(1,i);
					}
				}
				return res;
			}
        }else{
        	return -99999;
        }
	}
	
	public float Minor(int r, int c){
		//KAMUS LOKAL
		float res;
		int i,j;
		Matriks tmp;
		//ALGORITMA
		tmp=new Matriks(this.NB,this.NK);
		tmp=this.CopyMATRIKS();
		tmp.ShrinkCol(c);
		tmp.ShrinkRow(r);
		res=tmp.Determinan();
		return res;
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
	public boolean IsEQSize(Matriks M){
		//KAMUS LOKAL

		//ALGORITMA
		return (this.NB==M.NB && this.NK==M.NK);
	}
	public float Cramer(int c){
		//KAMUS LOKAL
		float det;
		Matriks tmp;
		//ALGORITMA
		tmp=new Matriks(this.NB,this.NK);
		tmp=this.CopyMATRIKS();
		tmp.SwapKolom(c,this.GetLastIdxKol());
		tmp.ShrinkCol(this.GetLastIdxKol());
		det=tmp.Determinan();
		return det;
	}
	
	public MatriksInverse MatriksToInverse(){
		//KAMUS LOKAL
		int i,j;
		//ALGORITMA
		MatriksInverse Mtemp = new MatriksInverse(this.NB, this.NK);
		for(i=this.GetFirstIdxBrs();i<=this.GetLastIdxBrs();i++){
			for(j=this.GetFirstIdxKol();j<=this.GetLastIdxKol();j++){
				Mtemp.el[i][j]=this.el[i][j];
			}
		}
		return Mtemp;
	}

	public MatriksGauss MatriksToGauss(){
		//KAMUS LOKAL
		int i,j;
		//ALGORITMA
		MatriksGauss Mtemp = new MatriksGauss(this.NB, this.NK);
		for(i=this.GetFirstIdxBrs();i<=this.GetLastIdxBrs();i++){
			for(j=this.GetFirstIdxKol();j<=this.GetLastIdxKol();j++){
				Mtemp.el[i][j]=this.el[i][j];
			}
		}
		return Mtemp;
	}
	public void inputFile(String s){
		//KAMUS LOKAL
		int i,j,r,c,col;
		String line;
		//ALGORITMA
		try (FileReader reader = new FileReader(s);
		     BufferedReader br = new BufferedReader(reader)) {

		    r=1;
		    col=0;
		    while ((line = br.readLine()) != null) {
			Scanner scanner = new Scanner(line);
			c=1;
			while(scanner.hasNext()){
				this.[r][c]=scanner.nextFloat();
				c++;
			}
			col=c-1;
			r++;
		    }
		    r--;
		    this.NB=r;
		    this.NK=col;
		} catch (IOException e) {
		    System.err.format("IOException: %s%n", e);
		}
	}
}
