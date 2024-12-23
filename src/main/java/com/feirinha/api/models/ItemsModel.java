package com.feirinha.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                
@NoArgsConstructor
@AllArgsConstructor
@Entity  // Indica ao banco que isso é uma entidade a ser mapeada
@Table(name = "tb_items")  // Permite escolher o nome da tabela
public class ItemsModel {

  @Id // Identifica que é o id, a chave primária da tabela
  @GeneratedValue(strategy = GenerationType.AUTO) // Estratégia gerar IDs
  private Long id;

  @Column(length = 150, nullable = false) // Coluna da tabela + constraints
  private String name;

	@Column(nullable = false)
  private Double value;
}