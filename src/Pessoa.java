import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Pessoa {
    protected static int geraId = 0;
    protected int idPessoa;
    protected String nome;
    protected String cpf;
    protected String cidade;
    protected Date dtNasc;

    protected Pessoa(String nome){
        if (nome.length() > 2) {
            geraId++;
            this.idPessoa = geraId;
            this.nome = nome;
        }
    }

    protected void setCidade(String cidade) {
        if (cidade.length() > 2) 
            this.cidade = cidade;
    }

    protected void setCpf(String cpf) {
        if (cidade.length() > 2) 
            this.cpf = cpf;
    }

    protected void setDtNasc(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dtNasc = dateFormat.parse(date);
            this.dtNasc = dtNasc;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setNome(String nome) {
        if (nome != null && nome != "")
            this.nome = nome;
    }
}
