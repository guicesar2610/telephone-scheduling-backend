package com.noxtec.telephone_scheduling.entities;

import com.noxtec.telephone_scheduling.repositories.SchedulingRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity(name = "schedulings")
@Table(name = "desafio")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Scheduling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String cellphone;
    private String telephone;
    private Boolean favorite;
    private Boolean active;

    public Scheduling(SchedulingRequest schedulingRequest) {
        this.id = schedulingRequest.id();
        this.name = schedulingRequest.name();
        this.email = schedulingRequest.email();
        this.cellphone = schedulingRequest.cellphone();
        this.telephone = schedulingRequest.telephone();
        this.favorite = schedulingRequest.favorite();
        this.active = schedulingRequest.active();
    }
}
