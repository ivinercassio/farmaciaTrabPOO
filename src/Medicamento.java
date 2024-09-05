public class Medicamento {
    private static int geraId = 0;
    private int idMedicamento;
    private String nome;
    private float valorAtual;
    private String restricao;
    private String efeitosColaterias;

    public static Medicamento getInstance(String nome, float valorAtual){
        if (nome != null && nome != "" && valorAtual > 0) 
            return new Medicamento(nome, valorAtual);
        return null;
    }

    private Medicamento(String nome, float valorAtual){
        geraId++;
        this.idMedicamento = geraId;
        this.nome = nome;
        this.valorAtual = valorAtual;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public String getNome() {
        return nome;
    }

    public Float getValorAtual() {
        return valorAtual;
    }

    public void setNome(String nome) {
        if (nome != null && nome != "")
            this.nome = nome;
    }

    public void setValorAtual(float valorAtual) {
        if (valorAtual > 0)
            this.valorAtual = valorAtual;
    }

    public void setRestricao(String restricao) {
        if (restricao != null && restricao.length() > 3)
            this.restricao = restricao;
    }

    public void setEfeitosColaterais(String efeitos) {
        if (efeitos != null && efeitos.length() > 3)
            this.efeitosColaterias = efeitos;
    }
}
