
public class Produto {
    int idProduto;
    String nomeProduto;
    int qntProduto;

    public Produto(int idProduto, String nomeProduto, int qntProduto) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.qntProduto = qntProduto;
    }

    public Produto(String nomeProduto, int qntProduto) {
        this.nomeProduto = nomeProduto;
        this.qntProduto = qntProduto;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQntProduto() {
        return qntProduto;
    }

    public void setQntProduto(int qntProduto) {
        this.qntProduto = qntProduto;
    }

    @Override
    public String toString() {
        return idProduto + "!" + nomeProduto + "!" + qntProduto;
    }  
}
