package br.com.stockcontrol.model;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity //create entity
@Table(name="customers") //create table
public class Customer {
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //defines a strategy to create a PK
    private Long id;

    @Column(nullable = false, length = 50) //not null
    @NotBlank(message = "Inform Your name!")
    @Size(min = 3, max = 50)
    private String name;

    @Column(length = 11) //11 character
    @CPF(message = "Invalid CPF!")
    private String cpf;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(nullable = false, name = "birth_date", columnDefinition = "DATE")
    @NotNull(message = "Inform your date of birth!")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Inform your sex!")
    private Sex sex;

    @Column(length = 10)
    private String phone;

    @Column(length = 11)
    private String cell;

    @Column(length = 50)
    @Email
    private String email;

    private Boolean active;

    //Added constructor to star application marked as ACTIVE from startup
    public Customer() {
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
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
