
package com.renan.baozi_store_novo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renan.baozi_store_novo.model.Produto;
import com.renan.baozi_store_novo.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody Produto produto) {
        Produto produtoSalvo = produtoRepository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }

    @GetMapping
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);

        return produto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(
            @PathVariable Long id,
            @RequestBody Produto dadosAtualizados) {

        Optional<Produto> produtoExistente =
                produtoRepository.findById(id);

        if (produtoExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Produto produto = produtoExistente.get();

        produto.setNome(dadosAtualizados.getNome());
        produto.setDescricao(dadosAtualizados.getDescricao());
        produto.setPreco(dadosAtualizados.getPreco());
        produto.setQuantidadeEstoque(
                dadosAtualizados.getQuantidadeEstoque());

        Produto produtoSalvo = produtoRepository.save(produto);

        return ResponseEntity.ok(produtoSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!produtoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        produtoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
