package dataBase.rlille;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "EMPLOYE", schema = "RLILLE")
public class EmployeEntityLille {
    private Integer noemploye;
    private String nom;
    private String prenom;
    private Date datenaiss;
    private Date dateembauche;
    private Long salaire;

    @Id
    @Column(name = "NOEMPLOYE", nullable = false, precision = 0)
    public Integer getNoemploye() {
        return noemploye;
    }

    public void setNoemploye(Integer noemploye) {
        this.noemploye = noemploye;
    }

    @Basic
    @Column(name = "NOM", nullable = true, length = 50)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "PRENOM", nullable = true, length = 50)
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Basic
    @Column(name = "DATENAISS", nullable = true)
    public Date getDatenaiss() {
        return datenaiss;
    }

    public void setDatenaiss(Date datenaiss) {
        this.datenaiss = datenaiss;
    }

    @Basic
    @Column(name = "DATEEMBAUCHE", nullable = true)
    public Date getDateembauche() {
        return dateembauche;
    }

    public void setDateembauche(Date dateembauche) {
        this.dateembauche = dateembauche;
    }

    @Basic
    @Column(name = "SALAIRE", nullable = true, precision = 2)
    public Long getSalaire() {
        return salaire;
    }

    public void setSalaire(Long salaire) {
        this.salaire = salaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeEntityLille that = (EmployeEntityLille) o;
        return Objects.equals(noemploye, that.noemploye) &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(prenom, that.prenom) &&
                Objects.equals(datenaiss, that.datenaiss) &&
                Objects.equals(dateembauche, that.dateembauche) &&
                Objects.equals(salaire, that.salaire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noemploye, nom, prenom, datenaiss, dateembauche, salaire);
    }
}
