import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Funcionario extends Pessoa {
    protected float salario;
    protected Date dtAdmissao;
    protected Date dtDemissao;
    protected String senha;

    protected Funcionario(String nome, float salario, String dtAdmissao, String senha) {
        super(nome);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.salario = salario;
        try {
            this.dtAdmissao = dateFormat.parse(dtAdmissao);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.senha = senha;
    }

    public void setDtDemissao(String dtDemissao) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = dateFormat.parse(dtDemissao);
            if (dtDemissao != null && date.after(dtAdmissao))       
                this.dtDemissao = date;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getSenha() {
        return senha;
    }    

    public void setSalario(float salario) {
        if (salario > 0)
            this.salario = salario;
    }
}
