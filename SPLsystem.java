import System.util.Scanner;
import java.lang.Math;

public class SPLSystem extends Matriks{
    SPLSystem(Matriks M){
        this.CopyMatriks(M);
    }

    public void SolveX(){
        int i,j, cnt,fIdx;
        int[] nNull = new int[this.GetLastIdxBrs()]; 
        boolean loop = true;
        for(i=this.GetLastIdxBrs(); i>=this.GetFirstIdxBrs() && loop; i--){
            cnt=0;
            fIdx=-1;
            for(j=this.GetLastIdxKol() - 1; j>=this.GetFirstIdxKol(); j--){
                if(this.el[i][j]!=0){
                    if(cnt==0)fIdx = j;
                    cnt++;                
                }
            }
            nNull[i]=cnt;
            if(cnt==0 && this.el[i][this.GetLastIdxKol()]!=0){
                System.out.printf("Tidak Ada Solusi Valid%n");
                loop = false;
            }
            else if(cnt==1){
                for(j=i-1; j>=this.GetFirstIdxBrs(); j--){
                    this.el[j][This.GetLastIdxKol()]-=this.el[j][fIdx] * this.el[i][this.GetLastIdxKol()];
                    this.el[j][fIdx] = 0;
                }
            }
        }
        if (loop){
            for(i=This.GetFirstIdxBrs(); i<=This.GetLastIdxBrs(); i++){
                cnt = 0;
                for(j=This.GetFirstIdxKol(); j<=This.GetLastIdxKol()-1; j++){
                    if(This.el[i][j]!=0){
                        if(cnt!=0) printf("+ ");
                        if (This.el[i][j]!=1) printf("%.2f", This.el[i][j]); 
                        System.out.printf("x%d ", This.GetLastIdxKol()-j);
                        cnt++;
                    } 
                    
                }
                if (nNull[i]>0) printf("= %.3f", This.el[i,This.GetLastIdxKol()));
                System.out.printf("\n");
            }
        }
    }

    public void Interpolation(){
        int i,j, cnt,fIdx;
        int[] nNull = new int[this.GetLastIdxBrs()]; 
        boolean loop = true;
        for(i=this.GetLastIdxBrs(); i>=this.GetFirstIdxBrs() && loop; i--){
            cnt=0;
            fIdx=-1;
            for(j=this.GetLastIdxKol() - 1; j>=this.GetFirstIdxKol(); j--){
                if(this.el[i][j]!=0){
                    if(cnt==0)fIdx = j;
                    cnt++;                
                }
            }
            nNull[i]=cnt;
            if(cnt==0 && this.el[i][this.GetLastIdxKol()]!=0){
                printf("Tidak Ada Solusi Valid\n");
                loop = false;
            }
            else if (cnt > 1){
                printf("koefisien x tidak unik\n");
                loop = false;
            }
            else if(cnt==1){
                for(j=i-1; j>=this.GetFirstIdxBrs(); j--){
                    this.el[j][This.GetLastIdxKol()]-=this.el[j][fIdx] * this.el[i][this.GetLastIdxKol()];
                    this.el[j][fIdx] = 0;
                }
            }
        }
        if (loop){
            for(i=This.GetFirstIdxBrs(); i<=This.GetLastIdxBrs(); i++){
                cnt = 0;
                for(j=This.GetFirstIdxKol(); j<=This.GetLastIdxKol()-1; j++){
                    if(This.el[i][j]!=0){
                        if(cnt!=0) printf("+ ");
                        if (This.el[i][j]!=1) printf("%.2f", This.el[i][j]); 
                        printf("x%d ", This.GetLastIdxKol()-j);
                        cnt++;
                    } 
                    
                }
                if (nNull[i]>0) printf("= %.3f", This.el[i,This.GetLastIdxKol()));
                printf("\n");
            }
        }
    }
}