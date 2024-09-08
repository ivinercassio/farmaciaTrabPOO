public class Gerente extends Funcionario {

    public static Gerente getInstance(String nome, float salario, String senha){
        if (nome.length()>2 && senha != null && senha != "") 
            return new Gerente(nome, salario, senha);
        return null;
    }

    private Gerente(String nome, float salario, String senha) {
        super(nome, salario, senha);
    }

}
