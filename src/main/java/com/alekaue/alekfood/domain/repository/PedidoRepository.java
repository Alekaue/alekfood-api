package com.alekaue.alekfood.domain.repository;

import org.springframework.stereotype.Repository;

import com.alekaue.alekfood.domain.model.Pedido;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long>{

}
