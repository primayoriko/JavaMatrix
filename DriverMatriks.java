import java.util.Scanner;

public class DriverMatriks{
    public static int inputnum;
    public static boolean loop;

    public static void mainMenu(){
        System.out.println("MENU");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Matriks Kofaktor");
        System.out.println("5. Adjoin");
        System.out.println("6. Interpolasi Polinom");
        System.out.println("7. Keluar");
    }

    public static void menu1(){
        System.out.println("SUBMENU 1");
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Cramer");
    }

    public static void inputting(){
        Scanner input = new Scanner(System.in);
        System.out.print("Masukan input : ");
        inputnum = input.nextInt();
    }

    public static void inputMenu1(){
        inputting();
        switch (inputnum){
            case 1 :

                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                System.out.println("Input salah, coba divalidasi kembali.");
                inputMainMenu();
                break;
        }
    }

    public static void inputMainMenu(){
        inputting();
        switch (inputnum){
            case 1 :
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
                break;
            case 7:
                loop = false;
                break;
            default:
                System.out.println("Input salah, coba divalidasi kembali.");
                inputMainMenu();
                break;
        }
    }

    public static void main(String[] args){
        //Driver myDriver = new Driver();
        loop = true;
        while(loop){
            mainMenu();
            inputMainMenu();
        }

    }

}