package application.arquivos;

import Entities.Product;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Arquivos {

    public static void main(String[] args) {
        
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        List<Product> list = new ArrayList<>();
        
        //System.out.println("Digite o caminho do arquivo: ");
        String sourceFileStr = "C:\\Users\\COPEL\\Dropbox\\ESTUDOS\\JAVA\\Arquivos\\input.csv.txt";
        
        File sourceFile = new File(sourceFileStr);
        String sourceFolderStr = sourceFile.getParent();
        
        boolean success = new File(sourceFolderStr + "//out").mkdir();
        String targetFileStr = sourceFolderStr + "//out//summary.csv";
        
        try (BufferedReader br = new BufferedReader(new FileReader(sourceFileStr))){
            String itemCsv = br.readLine();
            while(itemCsv != null){
               String[] fields = itemCsv.split(",");
               String name = fields[0];
               double price = Double.parseDouble(fields[1]);
               int quantity = Integer.parseInt(fields[2]);
               list.add(new Product(name, price, quantity));
               itemCsv = br.readLine();
            }
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))){
                for (Product item:list){
                    bw.write(item.getName()+","+String.format("%.2f",item.Total()));
                    bw.newLine();
                }
                
            }
            catch(IOException e){
            System.out.println("Erro ao ler arquivo: "+e.getMessage());
        }
        }
        catch (IOException e){
            System.out.println("Erro ao ler arquivo: "+e.getMessage());
        }
        
        
        
        sc.close();
        
        
    }
}
