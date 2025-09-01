package Sprint_2.Ages.Model;
import java.util.*;
public class Filme {
    private Long id;
    private String titulo;
    private String diretor;
    private int anoLancamento;
    private String genero;
    private int duracao; // duração em minutos
    private List<Review> reviews = new ArrayList<>();

    public Filme() {
    }

    public Filme(Long id, String titulo, String diretor, int anoLancamento, String genero, int duracao, List<Review> reviews) {
        this.id = id;
        this.titulo = titulo;
        this.diretor = diretor;
        this.anoLancamento = anoLancamento;
        this.genero = genero;
        this.duracao = duracao;
        this.reviews = reviews;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDiretor() {
        return diretor;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public String getGenero() {
        return genero;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }
    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}


