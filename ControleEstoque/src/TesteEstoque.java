import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException; 
import java.io.FileNotFoundException;

public class TesteEstoque {

    static Scanner entrada = new Scanner(System.in);
    static String tipoCliente;
	
    static int menuPrincipal(){
        int ent;
	System.out.println("Cadastro de Produto");
	System.out.println("1 - Cadastro de novos produtos");
	System.out.println("2 – Excluir produtos");
	System.out.println("3 - Editar produtos(mudar a quantidade)");
        System.out.println("4 - Sair");
	ent = entrada.nextInt();
	return ent;
    }
    
    static int criarTxt(){
        try {
            File myObj = new File("estoque.txt");
            if (myObj.createNewFile()) {
                System.out.println("Arquivo criado: " + myObj.getName() + "\n");
            } else {
                System.out.println("O arquivo de estoque ja existe.\n");
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro.\n");
        }   
      return 0;  
    }
    
    static int lerTxt(List<Produto> produtos){
      try {
        File myObj = new File("estoque.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        
        if(!data.equalsIgnoreCase("")){
            System.out.println(data);
            String dataArray[]= new String[3];
            dataArray = data.split("!");
            Produto x = new Produto(Integer.parseInt(dataArray[0]),dataArray[1],Integer.parseInt(dataArray[2]));
            produtos.add(produtos.size(),x);
        }
      }
        myReader.close();
      } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }  
       return 0;
    }
    
    static int escreverTxt(String a){
        try {
            String data = "";
            File myObj = new File("estoque.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
            data = data+ "\n" + myReader.nextLine();
            }
            FileWriter fileWriter = new FileWriter("estoque.txt");
	    BufferedWriter writer = new BufferedWriter(fileWriter);
	    writer.append(data+"\n");
	    writer.append(a);
            writer.close();
            System.out.println("Adicionado com sucesso.");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro.\n");
        }
    
        return 0;
    }
	 
    
    public static void main(String[] args) {
        List<Produto> produtos;
        int idProduto;
        String nomeProduto;
        int qntProduto;
        int ent;
        
        criarTxt();
        
        produtos = new ArrayList<>();
        
        lerTxt(produtos);
        System.out.println("----------//----------");
        
        do {
            ent = menuPrincipal();
		 switch (ent) {
			case 1:
                            adicionar(produtos);
                            
                            break;
			case 2:
                            remover(produtos);
                            
                            break;
                        case 3:
                            editar(produtos);
                        
                        default:
				break;}
	}while (ent==4);
    }
    
    static String adicionar(List<Produto> produtos){
        String nome;
	int quantidade;
        System.out.println("Informe o nome do produto");
	nome = entrada.next();
	System.out.println("Informe a quantidade do produto");
	quantidade = entrada.nextInt();
        Produto x = new Produto(produtos.size(),nome,quantidade);
        produtos.add(produtos.size(),x);
        escreverTxt(x.toString());
        return "";
    }
    
    static String remover(List<Produto> produtos){
	int id;
        int ent;
        
        System.out.println("Voce vai inserir o");
        System.out.println("1 - Numero do produto");
        System.out.println("2 - Nome do produto");
	ent = entrada.nextInt();
	
	 switch (ent) {
		case 1:
                    System.out.println("Qual o codigo do produto a ser excluido?");
                    int j = entrada.nextInt();
                    produtos.remove(j);
                    
                    break;
		case 2:
                    int i;
                    System.out.println("Qual o nome do produto a ser excluido?");
                    String k = entrada.next();
                    for(i=0;i<=produtos.size();i++){
                        if(k.equalsIgnoreCase(produtos.get(i).getNomeProduto())){
                            produtos.remove(i);
                            System.out.println("Excluido com sucesso!!!");
                        }else{
                            System.out.println("produto nao encontrado!!!");
                        };
                    }
                    
                    break;
                default:
                    break;}
        return "";
    }
    
    static String editar(List<Produto> produtos){
        int id;
        int ent;
        int quant;
        
        System.out.println("Voce vai inserir o");
        System.out.println("1 - Numero do produto");
        System.out.println("2 - Nome do produto");
	ent = entrada.nextInt();
	
	 switch (ent) {
		case 1:
                    System.out.println("Qual o codigo do produto a ser excluido?");
                    int j = entrada.nextInt();
                   
                    System.out.println("Qual a nova quantidade do produto");
                    quant = entrada.nextInt();
                    produtos.get(j).setQntProduto(quant);
		case 2:
                    int i;
                    System.out.println("Qual o nome do produto a ser excluido?");
                    String k = entrada.next();
                    for(i=0;i<=produtos.size();i++){
                        if(k.equalsIgnoreCase(produtos.get(i).getNomeProduto())){
                            System.out.println("Qual a nova quantidade do produto");
                            quant = entrada.nextInt();
                            produtos.get(i).setQntProduto(quant);
                            System.out.println("Editado com sucesso!!!");
                        }else{
                            System.out.println("produto nao encontrado!!!");
                        };
                    }
                default:
                    break;}
        return "";
    }
}
