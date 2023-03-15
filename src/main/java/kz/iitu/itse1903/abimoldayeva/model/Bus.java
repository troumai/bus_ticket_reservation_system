package kz.iitu.itse1903.abimoldayeva.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.iitu.itse1903.abimoldayeva.model.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Bus extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_id")
    private Long id;

    @NotNull
    private String registerNumber;

    @NotNull
    private int seatsNumber;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "bus")
    @JsonIgnore
    private List<BusRoute> busRouteList = new ArrayList<>();

    public Bus(String registerNumber, int seatsNumber) {
        this.registerNumber = registerNumber;
        this.seatsNumber = seatsNumber;
    }

    @PostConstruct
    public void doInit(){
        System.out.println("----------Bus bean init-------------");
    }

    @PreDestroy
    public void doDestroy(){
        System.out.println("----------Bus bean destroy-------------");
    }

}
