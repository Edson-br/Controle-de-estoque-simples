import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException; 
import java.io.FileNotFoundException;


public class TesteEstoque {

    static Scanner entrada = new Scanner(System.in);
    static String tipoCliente;
	
    static int menuPrincipal(){ //Funcao utilidada para gerar o menu principal
        int ent;
	System.out.println("Cadastro de Produto");
	System.out.println("1 - Cadastro de novos produtos");
	System.out.println("2 – Excluir produtos");
	System.out.println("3 - Editar produtos(mudar a quantidade)");
        System.out.println("4 - Sair");
	ent = entrada.nextInt();
	return ent;
    }
    
    static int criarTxt(){  //Funcao utilidada para gerar o arquivo estoque.txt
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
    
    static int lerTxt(List<Produto> produtos){  //Funcao utilidada para ler o aquivo e transformar seu conteudo num List<Produto>
      try {
        int i=0;
        produtos.clear();
        File myObj = new File("estoque.txt");
        
        Scanner myReader = new Scanner(myObj);
        
        System.out.println("Codigo   Nome                     Quantidade");
        while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
            if(!data.equalsIgnoreCase("")){
                String dataArray[]= new String[3];
                dataArray = data.split("!");
                Produto x = new Produto(i,dataArray[1],Integer.parseInt(dataArray[2]));
                i+=1;
                produtos.add(produtos.size(),x);
            
                printTabela(x);
            } 
        }
        atualizarTxt(produtos);
        myReader.close();
         
      } catch (FileNotFoundException e) {
        System.out.println("Ocorreu um erro.\n");
        e.printStackTrace();
        }  
       return 0;
    }
    
    static int printTabela(Produto x){  ////Funcao utilidada para printar um objeto Produto nos padroes da tabela
            int tamanhoProduto=0;
            String s=String.valueOf(x.getIdProduto());
            
            System.out.print(x.getIdProduto());
            for(int j=0;s.length()>j;j++){
                tamanhoProduto+=1;}
            for(int j=0;9-tamanhoProduto>j;j++){
                System.out.print(" ");
            }
            
            System.out.print(x.getNomeProduto());
            for(int j=0;x.getNomeProduto().length()>j;j++){
                tamanhoProduto+=1;}
            for(int j=0;26-tamanhoProduto>j;j++){
                System.out.print(" ");
            }
            
            System.out.print(x.getQntProduto()+"\n");  
            
    return 0;
    }
    
    static int atualizarTxt(List<Produto> produtos){ //Funcao utilidada para rescrever o aquivo baseando no conteudo do um List<Produto>
        try {
            String data = "";
            File inputFile = new File("estoque.txt");
            
            for (int i=0;produtos.size()>i;i++){
                data+=  "\n" + produtos.get(i).toString();
            }
            FileWriter fileWriter = new FileWriter("estoque.txt");
	    BufferedWriter writer = new BufferedWriter(fileWriter);
	    writer.append(data);
            writer.close();
        } catch (IOException | java.util.NoSuchElementException e) {
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
        
        System.out.println("--------------------//--------------------");
        
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
    
    static String adicionar(List<Produto> produtos){  //Funcao utilidada para receber as informacoes do novo produto, adiciona-los a lista produtos e chamar as funcoes atualizarTxt() e lerTxt()
        String nome;
	int quantidade;
        System.out.println("Informe o nome do produto");
	nome = entrada.next();
	System.out.println("Informe a quantidade do produto");
	quantidade = entrada.nextInt();
        Produto x = new Produto(produtos.size(),nome,quantidade);
        produtos.add(produtos.size(),x);
        System.out.println("--------------------//--------------------");
        atualizarTxt(produtos);
        lerTxt(produtos);
        return "";
    }
    
    static String remover(List<Produto> produtos){ //Funcao utilidada para receber as informacoes do produto a ser excluido, remove-lo da lista produtos e chamar as funcoes atualizarTxt() e lerTxt()
	int id;
        int ent;
        
        System.out.println("Voce vai inserir o");
        System.out.println("1 - Numero do produto");
        System.out.println("2 - Nome do produto");
	ent = entrada.nextInt();
	
	 switch (ent) {
		
                case 1:  //O usuario selecionou inserir o numero do produto a ser excluido
                    try{
                        System.out.println("Qual o codigo do produto a ser excluido?");
                        int j = entrada.nextInt();
                        produtos.remove(j);
                        atualizarTxt(produtos);
                        System.out.println("--------------------//--------------------");
                        lerTxt(produtos);
                        System.out.println("Excluido com sucesso!!!");
                    
                        break;
                    }catch(IndexOutOfBoundsException e){
                    System.out.println("Codigo do pruduto nao encontrado!!!");
                    }
		case 2:  //O usuario selecionou inserir o nome do produto a ser excluido
                    int i,w=0;
                    System.out.println("Qual o nome do produto a ser excluido?");
                    String k = entrada.next();
                    for(i=0;i<produtos.size();i++){
                        if(k.equalsIgnoreCase(produtos.get(i).getNomeProduto())){  //Percorre a lista produtos em busca do nome digitado
                            produtos.remove(i);
                            atualizarTxt(produtos);
                            System.out.println("--------------------//--------------------");
                            System.out.println("Excluido com sucesso!!!");
                            w=1;
                        }
                    }
                    if(w ==0){
                            System.out.println("Produto nao encontrado!!!");
                    }
                    lerTxt(produtos);
                    
                    break;
                default:
                    break;}
        
        return "";
    }
    
    static String editar(List<Produto> produtos){ //Funcao utilidada para receber as informacoes do produto a ser editado, atualiza-lo na lista produtos e chamar as funcoes atualizarTxt() e lerTxt()
        int id;
        int ent;
        int quant;
        
        System.out.println("Voce vai inserir o");
        System.out.println("1 - Numero do produto");
        System.out.println("2 - Nome do produto");
	ent = entrada.nextInt();
	
	 switch (ent) {
		case 1: //O usuario selecionou inserir o numero do produto a ser editado
                    try{
                        System.out.println("Qual o codigo do produto a ser Editado?");
                        int j = entrada.nextInt();
                   
                        System.out.println("Qual a nova quantidade do produto?");
                        quant = entrada.nextInt();
                        produtos.get(j).setQntProduto(quant);
                        System.out.println("--------------------//--------------------");
                        atualizarTxt(produtos);
                        lerTxt(produtos);
                        System.out.println("Editado com sucesso!!!");
                    
                        break;
                    }catch(IndexOutOfBoundsException e){
                        System.out.println("Codigo do pruduto nao encontrado!!!");
                    }
		case 2:  //O usuario selecionou inserir o nome do produto a ser editado
                    int i,w=0;
                    System.out.println("Qual o nome do produto a ser excluido?");
                    String k = entrada.next();
                    for(i=0;i<produtos.size();i++){  
                        if(k.equalsIgnoreCase(produtos.get(i).getNomeProduto())){  //Percorre a lista produtos em busca do nome digitado
                            System.out.println("Qual a nova quantidade do produto");
                            quant = entrada.nextInt();
                            produtos.get(i).setQntProduto(quant);
                            System.out.println("--------------------//--------------------");
                            atualizarTxt(produtos);
                            lerTxt(produtos);
                            System.out.println("Editado com sucesso!!!");
                            w+=1;
                        }
                    }
                    if(w ==0){
                            System.out.println("Produto nao encontrado!!!");
                    }
                    break;
                default:
                    break;}
        
        return "";
    }
}
