import java.math.*; 
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
public class Matriks{
    //Atribut
	int NB;
	int NK;
	BigDecimal[][] el = new BigDecimal[1001][1001];
	
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
				this.el[i][j]=BigDecimal.ZERO;
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
				this.el[i][j]=in.nextBigDecimal();
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
					System.out.println(this.el[i][j].setScale(4, RoundingMode.HALF_UP));
				}
			}
		}
	}
	
	public Matriks Transpose(){
		//KAMUS LOKAL
		int i,j,b,k;
		BigDecimal tmp;
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
	
	public Matriks KaliKons(BigDecimal K){
		//KAMUS LOKAL
		int i,j;
		Matriks tmp;
		//ALGORITMA
		tmp=new Matriks(this.NB,this.NK);
		for(i=1;i<=this.NB;i++){
			for(j=1;j<=this.NK;j++){
				tmp.el[i][j]=this.el[i][j].multiply(K);
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
				tmp.el[i][j]=this.el[i][j].add(M2.el[i][j]);
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
				tmp.el[i][j]=this.el[i][j].subtract(M2.el[i][j]);
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
				tmp.el[i][j]=BigDecimal.ZERO;
				for(k=1;k<=this.NK;k++){
					tmp.el[i][j]=tmp.el[i][j].add(this.el[i][k].multiply(M2.el[k][j]));
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
		BigDecimal tmp;
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
		BigDecimal tmp;
		//ALGORITMA
		for(i=1;i<=this.NB;i++){
			tmp=this.el[i][s];
			this.el[i][s]=this.el[i][d];
			this.el[i][d]=tmp;
		}
	}
	
	public int SearchBaris(int i, BigDecimal X){
		//Mencari X di baris ke-i pada matriks M
		//return indeks, jika tidak ada akan mengembalikan nilai 0
		
		//KAMUS LOKAL
		int j,indeks;
		boolean found;
		//ALGORITMA
		indeks=0;
		found=false;
		for(j=this.GetFirstIdxKol();j<=this.GetLastIdxKol();j++){
			if(this.el[i][j].compareTo(X)==0 && !found){
				indeks=j;
				found=true;
			}
		}
		return indeks;
	}
	
	public int SearchKolom(int j, BigDecimal X){
		//Mencari X di kolom ke-j pada matriks M
		//return indeks, jika tidak ada akan mengembalikan nilai 0
		
		//KAMUS LOKAL
		int i,indeks;
		boolean found;
		//ALGORITMA
		indeks=0;
		found=false;
		for(i=this.GetFirstIdxBrs();i<=this.GetLastIdxBrs();i++){
			if(this.el[i][j].compareTo(X)==0 && !found){
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
	
	public void TambahKBaris(int RAw, int RAkh, BigDecimal K){
		//I.S : Matriks terdefinisi
		//F.S : Menambahkan RAw dengan K kali RAkh
		
		//KAMUS LOKAL
		int j;
		//ALGORITMA
		for(j=1;j<=this.NK;j++){
			this.el[RAw][j] = this.el[RAw][j].add(this.el[RAkh][j].multiply(K));
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

	public BigDecimal Determinan(){
		BigDecimal res;
	    int i,j,k,cnt;
	    if (this.NK==1){
	        res = this.el[this.GetFirstIdxBrs()][this.GetFirstIdxBrs()];
	    }
	    else{
	        res = BigDecimal.ZERO;
	        Matriks Ma;
	        Ma=new Matriks(this.NB-1,this.NK-1);
	        for(i=this.GetFirstIdxBrs();i<=this.GetLastIdxBrs();i++){
	            for(j=this.GetFirstIdxBrs()+1;j<=this.GetLastIdxBrs();j++){
	                cnt=0;
	                for(k=this.GetFirstIdxBrs();k<=this.GetLastIdxBrs();k++){
	                    if(k==i) cnt=-1;
	                    else Ma.el[j-1][k+cnt] = this.el[j][k];
	                }
	            }
	            if (this.el[this.GetFirstIdxBrs()][i].compareTo(BigDecimal.ZERO)!=0){
	            	if(i%2==1)	res = res.add(this.el[this.GetFirstIdxBrs()][i]).multiply( Ma.Determinan());
	            		else res = res.subtract(this.el[this.GetFirstIdxBrs()][i]).multiply(Ma.Determinan());
	            }
	        }
	    }
	    return res;
	}
	
	public BigDecimal Minor(int r, int c){
		//KAMUS LOKAL
		BigDecimal res;
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
	public BigDecimal Cramer(int c){
		//KAMUS LOKAL
		BigDecimal det;
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

	public MatriksInterpolasi MatriksToInterpolasi(){
		//KAMUS LOKAL
		int i,j;
		//ALGORITMA
		MatriksInterpolasi Mtemp = new MatriksInterpolasi(this.NB, this.NK);
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
				this.el[r][c]=scanner.nextBigDecimal();
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

	public BigDecimal KaliDiagonalUtama(){
		BigDecimal Res = new BigDecimal(1);
		for(int i=this.GetFirstIdxBrs(); i<=this.GetLastIdxBrs();i++) Res = Res.multiply(this.el[i][i]);
		return Res;
	}

	public void writeFile(){
	//KAMUS LOKAL
		int i,j;
		StringBuffer strBuff;
		String s;
	//ALGORITMA
		strBuff=new StringBuffer();

		try (FileWriter writer = new FileWriter("output.txt");
		     BufferedWriter bw = new BufferedWriter(writer)) {
			for(i=this.GetFirstIdxBrs();i<=this.GetLastIdxBrs();i++){
				for(j=this.GetFirstIdxKol();j<=this.GetLastIdxKol();j++){
					strBuff.append(this.el[i][j]);
					if(j!=this.GetLastIdxKol()){
						strBuff.append(" ");
					}
				}
				s=strBuff.toString();
				bw.write(s);
				if(i!=this.GetLastIdxBrs()){
					bw.write(System.lineSeparator());
				}
			}

		} catch (IOException e) {
		    System.err.format("IOException: %s%n", e);
		}
	}
}
