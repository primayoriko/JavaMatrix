# JavaMatrix

## Description
------

Program untuk menjalankan berbagai fungsi di Matriks (Determinan, Kofaktor, Adjoint, Invers, serta melakukan berbagai Operasi Baris Elementer) dan implementasinya, seperti Menyelesaikan SPL dan juga Interpolasi.


## Prerequisite
-----------

For this project you need:
1. [JDK](https://www.oracle.com/java/technologies/javase-downloads.html), I recommend using version later than 11

## How to Run 
-------
### By java binary (.class)
1. Go to directory

		bin/
	
	then execute in terminal
	
		java DriverMatriks.class

### By java source code (.java) -> the advantages you can edit it as you wish
1. Go to directory

		src/
	
	then execute in terminal
	
		javac DriverMatriks.java
		java DriverMatriks.class
	

## How to Use
-------

1. Ikuti instruksi untuk diarahkan menuju fungsi yang diinginkan

2. Saat menginput matriks lewat konsol formatnya :

    (Jumlah Baris) (Jumlah Kolom)
    Baris dua dan setelahnya adalah Matriksnya

3. Input matriks dari file dengan formatnya langsung matriksnya saja.

4. Untuk input SPL, masukkan dalam bentuk matriks augmented.

5. Untuk input interpolasi dalam bentuk matriks, dimana jumlah persamaan sebagai baris, 
    dan perbaris adalah pair (x, y) sehingga jumlah kolomnya yaitu dua.
	
## Issues
-----------

1. Masih ada sedikit masalah pada pencarian determinan (kompleksitas masih tinggi), lalu float menyebabkan akurasi pada desimal rendah