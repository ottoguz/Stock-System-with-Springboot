package br.com.stockcontrol.model;

import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//CONTINUE AT 14:00 AULA PRÁTICA 1
@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Inform trading name!")
    @Size(min = 3, max = 50)
    private String tradingName; //Nome fantasia

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Inform company name!")
    @Size(min = 3, max = 50)
    private String companyName; //razão social

    @Column(length = 18)
    @CNPJ(message = "Invalid CNPJ!")
    private String cnpj;

    @Column(length = 14)
    private String phone;


    @Column(length = 15)
    private String cell;

    @Column(length = 20)
    private String email;
    private Boolean active;

    public Supplier() {
        this.active = true;
    } //constructor to start supplier as active

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
