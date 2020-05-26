package dataBase.rparis;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "CAMION", schema = "RPARIS")
public class CamionEntityParis {
    private String noimmatric;
    private Date dateachat;
    private String modele;
    private String marque;
    private int nbenlevementmax;

    @Id
    @Column(name = "NOIMMATRIC", nullable = false, length = 10)
    public String getNoimmatric() {
        return noimmatric;
    }

    public void setNoimmatric(String noimmatric) {
        this.noimmatric = noimmatric;
    }

    @Basic
    @Column(name = "DATEACHAT", nullable = true)
    public Date getDateachat() {
        return dateachat;
    }

    public void setDateachat(Date dateachat) {
        this.dateachat = dateachat;
    }

    @Basic
    @Column(name = "MODELE", nullable = false, length = 50)
    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    @Basic
    @Column(name = "MARQUE", nullable = false, length = 50)
    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    @Basic
    @Column(name = "NBENLEVEMENTMAX", nullable = false)
    public int getNbenlevementmax() {
        return nbenlevementmax;
    }

    public void setNbenlevementmax(int nbenlevementmax) {
        this.nbenlevementmax = nbenlevementmax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CamionEntityParis that = (CamionEntityParis) o;
        return Objects.equals(noimmatric, that.noimmatric) &&
                Objects.equals(dateachat, that.dateachat) &&
                Objects.equals(modele, that.modele) &&
                Objects.equals(marque, that.marque);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noimmatric, dateachat, modele, marque);
    }
}
