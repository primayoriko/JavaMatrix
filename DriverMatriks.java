import java.util.Scanner;

public class DriverMatriks{
    public static Scanner input = new Scanner(System.in);
    public static int inputnum;
    public static boolean loop;
    public static int[] inputvar = new int[20];

    public static void mainMenu(){
        System.out.println("MENU");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Matriks Kofaktor");
        System.out.println("5. Adjoin");
        System.out.println("6. Interpolasi Polinom");
        System.out.println("7. Keluar");
        inputMainMenu();
    }

    public static Matriks menuInputMatriks(){
        System.out.println("Pilih metode Input:");
        System.out.println("1. Input Langsung Console");
        System.out.println("2. Input dari File");
        return inputMatriks();
    }

    public static void menu1(){
        System.out.println("SUBMENU 1");
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Cramer");
        inputMenu1();
    }

    public static Matriks inputMatriks(){
        Matriks M = new Matriks(0,0);
        inputting(1, "pilihan input Matriks");
        switch (inputvar[1]){
            case 1 :
                M.BacaMATRIKS();
                break;
            case 2:


                break;
            default:
                System.out.println("Input salah, coba divalidasi kembali.");
                //mainMenu();
                break;
        }
        return M;
    }

    public static void inputMainMenu(){
        inputting(1, "pilihan menu");
        switch (inputvar[1]){
            case 1 :
                menu1();
                break;
            case 2:

                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                menuInterpolasi();
                break;
            case 7:
                loop = false;
                break;
            default:
                System.out.println("Input salah, coba divalidasi kembali.");
                //mainMenu();
                break;
        }
    }

    public static void inputMenu1(){
        inputting(1, "pilihan menu");
        switch (inputvar[1]){
            case 1:
                Gauss();
                break;
            case 2:
                GaussJordan();
                break;
            case 3:
                MBalikan();
                break;
            case 4:
                break;
            default:
                System.out.println("Input salah, coba divalidasi kembali.");
                break;
        }
    }

    public static void Gauss(){
        Matriks M = menuInputMatriks();
        if(!M.IsEmpty()){
            M.MatriksToGauss().Echelon(true).TulisMATRIKS();
            M.MatriksToGauss().Echelon(true).Solver();
        }
    }

    public static void GaussJordan(){
        Matriks M = menuInputMatriks();
        if(!M.IsEmpty()){
            M.MatriksToGauss().Echelon(true).EchelonReduc(true).TulisMATRIKS();
            M.MatriksToGauss().Echelon(true).EchelonReduc(true).Solver();
        }
    }

    public static void MBalikan(){
        Matriks M = menuInputMatriks();
        if(!M.IsEmpty()){
            Matriks B = new Matriks(0,0);
            B = M.CopyMATRIKS();
            B.SwapKolom(B.GetFirstIdxKol(), B.GetLastIdxKol());
            B.ResizeMATRIKS(B.GetLastIdxBrs(), 1);
            M.ResizeMATRIKS(M.GetLastIdxBrs(), M.GetLastIdxKol()-1);
            if(M.IsBujurSangkar()){
                if(M.Determinan()!=0){
                    M.MatriksToInverse().Inverse().TulisMATRIKS();
                    B.TulisMATRIKS();
                    M.MatriksToInverse().Inverse().KaliMATRIKS(B).MatriksToInverse().Solver();
                }
                else{
                    System.out.println("Error, determinan tidak boleh nol");
                }
            }
            else{
                System.out.println("Error, Matriks harus persegi");
            } 
        }
    }

    public static void menuInterpolasi(){
        
    }

    public static void inputting(int i, String A){
        String s = "Masukan input untuk " + A + " : ";
        System.out.print(s);
        inputvar[i] = input.nextInt();
    }

    public static void main(String[] args){
        loop = true;
        while(loop){
            mainMenu();
        }
    }

}
