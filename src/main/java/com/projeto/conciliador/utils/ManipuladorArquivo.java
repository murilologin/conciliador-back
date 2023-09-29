package com.projeto.conciliador.utils;

import java.io.*;
import java.util.Scanner;

public class ManipuladorArquivo {

    public static void leitor(String path) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha = "";
        while (true) {
            if (linha != null) {
                System.out.println(linha);
            } else
                break;
            linha = buffRead.readLine();
        }
        buffRead.close();
    }

    public static void escritor(String path, String content) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
        Scanner in = new Scanner(System.in);
        buffWrite.append(content + "\n");
        buffWrite.close();
    }
}
