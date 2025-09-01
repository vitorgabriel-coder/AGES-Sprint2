package Sprint_2.Ages.Service;

import Sprint_2.Ages.Repository.FilmeRepository;
import Sprint_2.Ages.Model.Filme;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import Sprint_2.Ages.Model.Review;
import java.util.concurrent.atomic.AtomicLong;

import java.util.List;
import java.util.Objects;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;
    private final AtomicLong reviewIdGenerator = new AtomicLong(1); // Gerador simples de IDs para reviews

    @Autowired
    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    // Criar filme
    public Filme criarFilme(Filme filme) {
        filmeRepository.salvar(filme);
        return filme;
    }

    // Listar todos os filmes
    public List<Filme> listarTodos() {
        return filmeRepository.getAllFilmes();
    }

    // Buscar por ID
    public Filme getById(Long id) {
        return filmeRepository.getById(id);
    }

    // Atualizar
    public Filme atualizarFilme(Long id, Filme filmeAtualizado) {
        Filme filmeExistente = filmeRepository.getById(id);
        if (filmeExistente != null) {
            filmeExistente.setTitulo(filmeAtualizado.getTitulo());
            filmeExistente.setDiretor(filmeAtualizado.getDiretor());
            filmeExistente.setAnoLancamento(filmeAtualizado.getAnoLancamento());
            filmeExistente.setGenero(filmeAtualizado.getGenero());
            filmeExistente.setDuracao(filmeAtualizado.getDuracao());
            filmeExistente.setReviews(filmeAtualizado.getReviews());
            filmeRepository.salvar(filmeExistente);
            return filmeExistente;
        }
        return null;
    }

    // Deletar
    public void deletarFilme(Long id) {
        filmeRepository.deletarPorId(id);
    }

     public Filme adicionarReview(Long filmeId, Review review) {
        Filme filme = getById(filmeId);
        if (filme != null) {
            // --- LÓGICA DE GERAR ID PARA A REVIEW ---
            // 1. Gera um novo ID único para a review.
            Long novoReviewId = reviewIdGenerator.incrementAndGet();
            // 2. Atribui o novo ID à review que recebemos.
            review.setId(novoReviewId);

            // 3. Adiciona a review (agora com ID) à lista do filme.
            filme.getReviews().add(review);
            
            // Salva o filme para persistir a mudança (boa prática).
            return filmeRepository.salvar(filme);
        }
        return null;
    }

    public void atualizarReview(Long filmeID, Long reviewID, Review reviewAtualizada){
        Filme filme = filmeRepository.getById(filmeID);
        if (filme != null) {
            for (Review review : filme.getReviews()) {
                if (review.getId() == reviewID) {
                    review.setComentario(reviewAtualizada.getComentario());
                    review.setAvaliacao(reviewAtualizada.getAvaliacao());
                    return;
                }
            }
        }
    }
    public boolean deletarReview(Long filmeId, Long reviewId) {
        Filme filme = getById(filmeId);
        if (filme != null) {
            // Usa o 'removeIf' para remover a review de forma segura.
            boolean removido = filme.getReviews().removeIf(review -> Objects.equals(review.getId(), reviewId));
            if (removido) {
                filmeRepository.salvar(filme); // Salva o estado do filme sem a review.
            }
            return removido;
        }
        return false;
    }
}
