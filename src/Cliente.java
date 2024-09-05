public class Cliente extends Pessoa{

    public static Cliente getInstance(String nome){
        if (nome.length()>2) 
            return new Cliente(nome);
        return null;
    }

    private Cliente(String nome) {
        super(nome);
    }
    
    public String getNome(){
        return nome;
    }
}
