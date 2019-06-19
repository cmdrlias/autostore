package com.trabalho.autostore.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="group_t")
@NamedQuery(name="Group.findAll", query="SELECT g FROM Group g")
public class Group implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name="grp_code")
    private int grpCode;

    @Column(name="grp_name")
    private char grpName;

    @Column(name="grp_desc")
    private String grpDesc;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name="vehicle_group",
            joinColumns={@JoinColumn(name="grp_code")},
            inverseJoinColumns={@JoinColumn(name="vcl_code")})
    private List<Vehicle> vehicles;

    public Group() {}

    public int getGrpCode() {
        return grpCode;
    }

    public void setGrpCode(int vclCode) {
        this.grpCode = vclCode;
    }

    public char getGrpName() {
        return grpName;
    }

    public void setGrpName(char grpName) {
        this.grpName = grpName;
    }

    public String getGrpDesc() {
        return grpDesc;
    }

    public void setGrpDesc(String grpDesc) {
        this.grpDesc = grpDesc;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
