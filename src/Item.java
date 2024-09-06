public class Item {
    private String medicamentoNom;
    private float valorPago;
    private int quantidade;

    public static Item getInstance(String medicamentoNom, float valorPago, int quantidade){
        if (medicamentoNom != null && valorPago > 0 && quantidade > 0)
            return new Item(medicamentoNom, valorPago, quantidade);
        return null;
    }

    public Item(String medicamentoNom, float valorPago, int quantidade){
        this.medicamentoNom = medicamentoNom;
        this.valorPago = valorPago;
        this.quantidade = quantidade;
    }

    public String getMedicamentoNom(){
        return medicamentoNom;
    }

    public float getValorPago(){
        return valorPago;
    }

    public int getQuantidade(){
        return quantidade;
    }
}
