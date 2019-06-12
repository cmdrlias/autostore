package com.trabalho.autostore.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;

@Entity
@Table(name="vehicle")
@NamedQuery(name="Vehicle.findAll", query="SELECT v FROM Vehicle v")
public class Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name="vcl_code")
    private int vclCode;

    @Column(name="vlc_name")
    private String vclName;

    @Column(name="vcl_plaque")
    private String vclPlaque;

    @Column(name="vcl_photo")
    private Blob vclPhoto;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name="vehicle_group",
            joinColumns={@JoinColumn(name="vcl_code")},
            inverseJoinColumns={@JoinColumn(name="grp_code")})
    private Group group;

    public Vehicle() {}

    public int getVclCode() {
        return vclCode;
    }

    public void setVclCode(int vclCode) {
        this.vclCode = vclCode;
    }

    public String getVclName() {
        return vclName;
    }

    public void setVclName(String vclName) {
        this.vclName = vclName;
    }

    public String getVclPlaque() {
        return vclPlaque;
    }

    public void setVclPlaque(String vclPlaque) {
        this.vclPlaque = vclPlaque;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Blob getVclPhoto() {
        return vclPhoto;
    }

    public void setVclPhoto(Blob vclPhoto) {
        this.vclPhoto = vclPhoto;
    }
}
