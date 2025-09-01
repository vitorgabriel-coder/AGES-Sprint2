package Sprint_2.Ages.Model;

public class Review {
    private Long id;
    private String comentario;
    private int avaliacao; // Avaliação de 1 a 5

    public Review() {
    }

    public Review(Long id, String comentario, int avaliacao) {
        this.id = id;
        this.comentario = comentario;
        this.avaliacao = avaliacao;
    }

    public long getId() {
        return id;
    }

    public String getComentario() {
        return comentario;
    }

    public int getAvaliacao() {
        return avaliacao;
    }
    public void setComentario(String comentario){
        this.comentario=comentario;
    }
    public void setAvaliacao(int avaliacao){
      if (avaliacao < 1 || avaliacao > 5) {
            throw new IllegalArgumentException("A avaliação deve ser entre 1 e 5.");
        }
        this.avaliacao = avaliacao;
    }
    public void setId(Long id) {
        this.id = id;
    }
}