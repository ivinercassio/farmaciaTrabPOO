public class Vendedor extends Funcionario{

    public static Vendedor getInstance(String nome, float salario, String dtAdmissao, String senha){
        if (nome.length()>2 && dtAdmissao != null && senha != null && senha != "") 
            return new Vendedor(nome, salario, dtAdmissao, senha);
        return null;
    }

    private Vendedor(String nome, float salario, String dtAdmissao, String senha){
        super(nome, salario, dtAdmissao, senha);
    }
}
