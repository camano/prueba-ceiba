package com.ceiba.prueba.infrastructure.adapter.postgres.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fund",schema = "btg")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FundEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private FundCategoryEntity category;

    @Column(name = "min_amount_cop", nullable = false)
    private java.math.BigDecimal minAmountCop;
}
