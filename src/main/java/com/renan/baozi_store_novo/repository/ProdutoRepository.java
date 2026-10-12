package com.renan.baozi_store_novo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.renan.baozi_store_novo.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}