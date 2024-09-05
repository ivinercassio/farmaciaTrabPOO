public class Gerente extends Funcionario {

    public static Gerente getInstance(String nome, float salario, String dtAdmissao, String senha){
        if (nome.length()>2 && dtAdmissao != null && senha != null && senha != "") 
            return new Gerente(nome, salario, dtAdmissao, senha);
        return null;
    }

    private Gerente(String nome, float salario, String dtAdmissao, String senha) {
        super(nome, salario, dtAdmissao, senha);
    }

}
