package com.trabalho.autostore.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name="usr_code")
    private int usrCode;

    @Column(name="usr_name")
    private String usrName;

    @Column(name="usr_alias")
    private String usrAlias;

    @Column(name="usr_password")
    private String usrPassword;

    public User() { }

    public int getUsrCode() {
        return usrCode;
    }

    public void setUsrCode(int usrCode) {
        this.usrCode = usrCode;
    }

    public String getUsrPassword() {
        return usrPassword;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getUsrAlias() {
        return usrAlias;
    }

    public void setUsrAlias(String usrAlias) {
        this.usrAlias = usrAlias;
    }
}
