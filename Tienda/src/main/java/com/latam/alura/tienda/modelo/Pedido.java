package com.latam.alura.tienda.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha=LocalDate.now();
    private BigDecimal valorTotal=new BigDecimal(0);

    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade=CascadeType.ALL)
    private List<ItemsPedido> items=new ArrayList<>();

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pedido() {

    }

    public void agregarItems(ItemsPedido item){
        item.setPedido(this);
        this.items.add(item);
        this.valorTotal = this.valorTotal.add(item.getValor());
    }

    public Long getId() {
        return id;
    }
}
