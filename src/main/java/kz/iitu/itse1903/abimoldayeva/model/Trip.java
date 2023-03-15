package kz.iitu.itse1903.abimoldayeva.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jdk.jshell.Snippet;
import kz.iitu.itse1903.abimoldayeva.model.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Trip.findByPrice",
        query = "select c from Trip c where c.price = ?1")
public class Trip extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_id")
    private Long id;

    private String destination;
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", nullable = false)
    @JsonBackReference
    private BusRoute busRoute;

    private LocalDate arrivalDate;
    private LocalDate departureDate;

    @Enumerated(EnumType.STRING)
    private State state;

    public Trip(String destination, double price, BusRoute busRoute, LocalDate arraivalDate, LocalDate departureDate) {
        this.destination = destination;
        this.price = price;
        this.busRoute = busRoute;
        this.arrivalDate = arraivalDate;
        this.departureDate = departureDate;
    }

    //buyticket end -> create ticket bd -> approve end (message) *approveDTO create ()sms_message ticket_id state* ->
    //if success kafka(approvedto) prod sent to cons and cons set status Success

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY, mappedBy = "trip")
    @JsonManagedReference(value = "trip-ticket")
    private Set<Ticket> tickets = new HashSet<>();

    @PostConstruct
    public void doInit(){
        System.out.println("----------Trip bean init-------------");
    }

    @PreDestroy
    public void doDestroy(){
        System.out.println("----------Trip bean destroy-------------");
    }
}
