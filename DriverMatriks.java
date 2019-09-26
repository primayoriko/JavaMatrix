import java.util.Scanner;

public class DriverMatriks{
    public static Scanner input = new Scanner(System.in);
    public static boolean loop;
    public static int[] inputvar = new int[20];

    public static void mainMenu(){
        padding();
        System.out.println("                                      PROGRAM MATRIKS");
        padding();
        System.out.println("   MENU");
        System.out.println();
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Matriks Kofaktor");
        System.out.println("5. Adjoin");
        System.out.println("6. Interpolasi Polinom");
        System.out.println("7. Keluar");
        System.out.println();
        inputMainMenu();
    }
    
    public static void menu1SPL(){
        padding();
        System.out.println("   SUBMENU 1");
        System.out.println();
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Cramer");
        System.out.println();
        inputMenu1();
    }

    public static Matriks menuInputMatriks(){
        padding();
        System.out.println("   Pilih metode Input:");
        System.out.println();   
        System.out.println("1. Input Langsung Console");
        System.out.println("2. Input dari File");
        System.out.println();
        return inputMatriks();
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
                break;
        }
        return M;
    }

    public static void inputMainMenu(){
        inputting(1, "pilihan menu");
        switch (inputvar[1]){
            case 1 :
                menu1SPL();
                break;
            case 2:
                menu2Determinan();
                break;
            case 3:
                menu3Invers();
                break;
            case 4:
                menu4Kofaktor();
                break;
            case 5:
                menu5Adjoin();
                break;
            case 6:
                menu6Interpolasi();
                break;
            case 7:
                System.out.printf("Terima kasih telah memakai aplikasi.%nSelamat Tinggal.%n");
                loop = false;
                break;
            default:
                System.out.println("Input salah, coba divalidasi kembali.");
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

    public static void menu2Determinan(){
        Matriks M = menuInputMatriks();
        if(!M.IsEmpty()){
            System.out.printf("Jadi, determinan Matriks ini adalah %.4f%n", M.Determinan());
        }
    }

    public static void menu3Invers(){
        Matriks M = menuInputMatriks();
        if(!M.IsEmpty()){

        }
    }

    public static void menu4Kofaktor(){
        Matriks M = menuInputMatriks();
        if(!M.IsEmpty()){
            System.out.println("Kofaktor dari Matriks ini adalah");
            System.out.println();
            M.MatriksToInverse().Kofaktor().TulisMATRIKS();
        }
    }

    public static void menu5Adjoin(){
        Matriks M = menuInputMatriks();
        if(!M.IsEmpty()){
            System.out.println("Adjoin dari Matriks ini adalah");
            System.out.println();
            M.MatriksToInverse().Adjoint().TulisMATRIKS();
        }
    }

    public static void menu6Interpolasi(){
        System.out.println();
        System.out.println("Note : Untuk input data interpolasi, banyak baris adalah jumlah titik data yang dimiliki dan banyak");
        System.out.println("       kolom adalah 2, lalu masukkan data tiap point dalam bentuk pair x dan dilanjutkan y");
        Matriks M = menuInputMatriks();
        if(!M.IsEmpty()){
            if(M.GetLastIdxKol()!=2){
                System.out.println("Error, masukkan dalam bentuk pair x dan dilanjutkan y (NK = 2)");
            }
            else{
                M.MatriksToInterpolasi().InterpretasiData().InterpolasiToGauss().Echelon(true).EchelonReduc(true).GaussToInterpolasi().Solver();
            }
        }
    }

    public static void Gauss(){
        Matriks M = menuInputMatriks();
        if(!M.IsEmpty()){
            System.out.println("Dengan metode eliminasi Gauss diperoleh Matriks");
            System.out.println();
            M.MatriksToGauss().Echelon(true).TulisMATRIKS();
            M.MatriksToGauss().Echelon(true).Solver();
        }
    }

    public static void GaussJordan(){
        Matriks M = menuInputMatriks();
        if(!M.IsEmpty()){
            System.out.println("Dengan metode eliminasi Gauss-Jordan diperoleh Matriks");
            System.out.println();
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
                    System.out.println("Diperoleh Matriks Inverse berikut");
                    M.MatriksToInverse().Inverse().TulisMATRIKS();
                    System.out.println();
                    System.out.println("Diperoleh Matriks Koefisien berikut");
                    B.TulisMATRIKS();
                    System.out.println();
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

    public static void inputting(int i, String A){
        String s = "Masukan input untuk " + A + " : ";
        System.out.print(s);
        inputvar[i] = input.nextInt();
    }

    public static void padding(){
        int i;
        String S = "";
        for (i=0;i<100;i++) S+="-";
        System.out.printf("%n%s%n%s%n%n", S, S);
    }

    public static void main(String[] args){
        loop = true;
        while(loop){
            mainMenu();
        }
    }

}
