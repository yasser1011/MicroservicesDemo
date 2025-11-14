package com.yasser.ecommerce.order;

import com.yasser.ecommerce.orderline.OrderLine;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@Entity
@EntityListeners(AuditingEntityListener.class) // for the created at, etc events
@NoArgsConstructor
@Table(name = "customer_order")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true,  nullable = false)
    private String reference;

    private Integer totalAmount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private Long customerId;

    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;}
