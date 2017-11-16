package trabalho01_joseronaldosilveira;


public class MoviPedidos {
    private String numPedido;
    private Produtos codProduto;
    private int quatidade;
    private float vlrUnitario;
    private float vlrTotal;

    public MoviPedidos() {
    }

    public MoviPedidos(Produtos codProduto, int quatidade, float vlrUnitario, float vlrTotal) {
        this.codProduto = codProduto;
        this.quatidade = quatidade;
        this.vlrUnitario = vlrUnitario;
        this.vlrTotal = vlrTotal;
    }

    public Produtos getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(Produtos codProduto) {
        this.codProduto = codProduto;
    }

    public int getQuatidade() {
        return quatidade;
    }

    public void setQuatidade(int quatidade) {
        this.quatidade = quatidade;
    }

    public float getVlrUnitario() {
        return vlrUnitario;
    }

    public void setVlrUnitario(float vlrUnitario) {
        this.vlrUnitario = vlrUnitario;
    }

    public float getVlrTotal() {
        return vlrTotal;
    }

    public void setVlrTotal(float vlrTotal) {
        this.vlrTotal = vlrTotal;
    }    

    public String getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(String numPedido) {
        this.numPedido = numPedido;
    }
    
    @Override
    public String toString() {
        return  codProduto.getDescricao() + " | " + quatidade + " * " + vlrUnitario + " = " + vlrTotal;
    }
    
}
