package Sprint_2.Ages.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Objects;
import org.springframework.stereotype.Repository;

import Sprint_2.Ages.Model.Filme;

@Repository
public class FilmeRepository {
    private List<Filme> filmes = new ArrayList<>();

    private AtomicLong idCounter = new AtomicLong();

    public List<Filme> getAllFilmes() {
        return filmes;
    }
    public Filme getById(long id) {
         for (Filme filme : this.filmes) {
            if (Objects.equals(filme.getId(), id)) {
                return filme;
            }
        }
        System.out.println("Filme não encontrado");
        return null;
    }

    public Filme salvar(Filme filme) {
        // --- LÓGICA DE CRIAÇÃO (se o ID for nulo ou zero) ---
        if (filme.getId() == null || filme.getId() == 0L) {
            // 1. Gera um ID novo e único.
            Long novoId = idCounter.incrementAndGet();
            // 2. Atribui o novo ID ao filme.
            filme.setId(novoId);
            // 3. Adiciona o filme à lista.
            this.filmes.add(filme);
            return filme;
        }
        // --- LÓGICA DE ATUALIZAÇÃO (se o filme já tem um ID) ---
        else {
            // Procura o filme existente na lista pelo ID.
            for (int i = 0; i < this.filmes.size(); i++) {
                if (Objects.equals(this.filmes.get(i).getId(), filme.getId())) {
                    // Encontrou! Substitui o filme antigo pelo novo na mesma posição.
                    this.filmes.set(i, filme);
                    return filme;
                }
            }
            // Se o loop terminou e não achou um filme com aquele ID para atualizar,
            // não fazemos nada e retornamos null para indicar que a atualização falhou.
            return null;
        }
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
    }


     public boolean deletarPorId(Long id) {
        // 'removeIf' é a forma SEGURA, MODERNA e EFICIENTE de remover de uma lista.
        // Ele percorre a lista internamente e remove todos os elementos que satisfazem a condição.
        // Retorna true se pelo menos um elemento foi removido.
        return this.filmes.removeIf(filme -> Objects.equals(filme.getId(), id));
    }
}

