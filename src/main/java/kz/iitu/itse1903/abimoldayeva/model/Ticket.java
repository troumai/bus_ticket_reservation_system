package kz.iitu.itse1903.abimoldayeva.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import kz.iitu.itse1903.abimoldayeva.model.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trip_id", nullable = false)
    @JsonBackReference(value = "trip-ticket")
    private Trip trip;

    private int seat;

    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    public Ticket(Trip trip, int seat, State state, User user) {
        this.trip = trip;
        this.seat = seat;
        this.state = state;
        this.user = user;
    }

    @PostConstruct
    public void doInit(){
        System.out.println("----------Ticket bean init-------------");
    }

    @PreDestroy
    public void doDestroy(){
        System.out.println("----------Ticket bean destroy-------------");
    }
}
