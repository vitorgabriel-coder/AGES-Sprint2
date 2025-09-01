package Sprint_2.Ages.Controller;

import Sprint_2.Ages.Model.Filme;
import Sprint_2.Ages.Service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Sprint_2.Ages.Model.Review;  

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    // A conexão com o Service continua igual.
    @Autowired
    private FilmeService filmeService;

    /**
     * Endpoint para LER (buscar) todos os filmes.
     * Mapeia: GET /filmes
     * Retorna a lista de filmes diretamente. O Spring converte para JSON.
     */
    @GetMapping
    public List<Filme> listarTodosFilmes() {
        return filmeService.listarTodos();
    }

    /**
     * Endpoint para LER (buscar) um único filme pelo seu ID.
     * Mapeia: GET /filmes/{id}
     * Retorna o objeto Filme diretamente.
     */
    @GetMapping("/{id}")
    public Filme buscarFilmePorId(@PathVariable Long id) {
        // Se o service não encontrar, ele retornará null.
        // O Spring, por padrão, retornará um corpo vazio com status 200 OK,
        // o que não é ideal, mas é simples.
        return filmeService.getById(id);
    }

    /**
     * Endpoint para CRIAR um novo filme.
     * Mapeia: POST /filmes
     * Recebe um Filme em JSON e o retorna após salvar (agora com ID).
     */
    @PostMapping
    public Filme criarFilme(@RequestBody Filme filme) {
        return filmeService.criarFilme(filme);
    }

     @PostMapping("/{filmeID}/reviews")
    public void adicionarReview(@PathVariable Long filmeID, @RequestBody Review review) {
        filmeService.adicionarReview(filmeID, review);
    }

    /**
     * Endpoint para ATUALIZAR um filme existente.
     * Mapeia: PUT /filmes/{id}
     */
    @PutMapping("/{id}")
    public Filme atualizarFilme(@PathVariable Long id, @RequestBody Filme filmeParaAtualizar) {
        return filmeService.atualizarFilme(id, filmeParaAtualizar);
    }

    /**
     * Endpoint para DELETAR um filme.
     * Mapeia: DELETE /filmes/{id}
     * O método não retorna nada (void), então o Spring retornará uma
     * resposta com corpo vazio e status 200 OK por padrão.
     */
    @DeleteMapping("/{id}")
    public void deletarFilme(@PathVariable Long id) {
        filmeService.deletarFilme(id);
    }

    @DeleteMapping("/{filmeId}/reviews/{reviewId}")
    public void deletarReview(@PathVariable Long filmeId, @PathVariable Long reviewId) {
        filmeService.deletarReview(filmeId, reviewId);
    }
}
