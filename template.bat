@echo off
cd src

set x=day%1%
mkdir %x%
cd %x%

call >input.txt
call > test.txt

echo package %x%; > Main.java
echo: >> Main.java
echo import java.io.File; >> Main.java
echo import java.io.FileNotFoundException; >> Main.java
echo import java.util.*; >> Main.java
echo: >> Main.java
echo public class Main { >> Main.java
echo     public static void main(String[] args) throws FileNotFoundException { >> Main.java
echo         File input = new File("src/%x%/test.txt"); >> Main.java
echo         Scanner s = new Scanner(input); >> Main.java
echo: >> Main.java
echo     } >> Main.java
echo } >> Main.java

cd ../..