package com.trabalho.autostore.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="status")
@NamedQuery(name="Status.findAll", query="SELECT s FROM Status s")
public class Status implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name="sts_code")
    private int stsCode;

    @Column(name="sts_name")
    private String stsName;

    @OneToMany
    @JoinTable(name="vehicle_status",
            joinColumns={@JoinColumn(name="sts_code")},
            inverseJoinColumns={@JoinColumn(name="vcl_code")})
    private List<Vehicle> vehicles;

    public Status() {}

    public int getStsCode() {
        return stsCode;
    }

    public void setStsCode(int stsCode) {
        this.stsCode = stsCode;
    }

    public String getStsName() {
        return stsName;
    }

    public void setStsName(String stsName) {
        this.stsName = stsName;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
