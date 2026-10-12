package com.renan.baozi_store_novo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renan.baozi_store_novo.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
