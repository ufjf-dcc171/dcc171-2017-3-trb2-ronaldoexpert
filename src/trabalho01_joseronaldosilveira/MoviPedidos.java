package trabalho01_joseronaldosilveira;


public class MoviPedidos {
    private Pedido numPedido;
    private Produtos codProduto;
    private int quatidade;
    private float vlrUnitario;
    private float vlrTotal;

    public MoviPedidos() {
    }

    public MoviPedidos(Pedido numPedido, Produtos codProduto, int quatidade, float vlrUnitario, float vlrTotal) {
        this.numPedido = numPedido;
        this.codProduto = codProduto;
        this.quatidade = quatidade;
        this.vlrUnitario = vlrUnitario;
        this.vlrTotal = vlrTotal;
    }

    public Pedido getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(Pedido numPedido) {
        this.numPedido = numPedido;
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
    
    @Override
    public String toString() {
        return  codProduto + " - " + quatidade + " - " + vlrUnitario + " - " + vlrTotal;
    }
    
}
