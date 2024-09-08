public class Vendedor extends Funcionario{

    public static Vendedor getInstance(String nome, float salario, String senha){
        if (nome.length()>2 && senha != null && senha != "") 
            return new Vendedor(nome, salario, senha);
        return null;
    }

    private Vendedor(String nome, float salario, String senha){
        super(nome, salario, senha);
    }
}
