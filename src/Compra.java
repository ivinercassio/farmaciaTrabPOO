public class Compra {
    private static int geraId = 0;
    private int idCompra;
    private Cliente cliente;
    private String funcionarioNom;
    private Item[] itens;

    public static Compra getInstance(Cliente cliente, String funcionarioNom, Item[] itens){
        if (cliente != null && funcionarioNom != null && itens != null)
            return new Compra(cliente, funcionarioNom, itens);
        return null;
    }

    public Compra(Cliente cliente, String funcionarioNom, Item[] itens){
        geraId++;
        this.idCompra = geraId;
        this.cliente = cliente;
        this.funcionarioNom = funcionarioNom;
        this.itens = itens;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public String getFuncionarioNom(){
        return funcionarioNom;
    }

    public Cliente getCliente(){
        return cliente;
    }
}
