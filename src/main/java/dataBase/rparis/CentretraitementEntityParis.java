package dataBase.rparis;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CENTRETRAITEMENT", schema = "RPARIS")
public class CentretraitementEntityParis {
    private Integer nocentre;
    private String nomcentre;
    private Integer noruecentre;
    private String ruecentre;
    private Integer cpostalcentre;
    private String villecentre;

    @Id
    @Column(name = "NOCENTRE", nullable = false, precision = 0)
    public Integer getNocentre() {
        return nocentre;
    }

    public void setNocentre(Integer nocentre) {
        this.nocentre = nocentre;
    }

    @Basic
    @Column(name = "NOMCENTRE", nullable = true, length = 100)
    public String getNomcentre() {
        return nomcentre;
    }

    public void setNomcentre(String nomcentre) {
        this.nomcentre = nomcentre;
    }

    @Basic
    @Column(name = "NORUECENTRE", nullable = true, precision = 0)
    public Integer getNoruecentre() {
        return noruecentre;
    }

    public void setNoruecentre(Integer noruecentre) {
        this.noruecentre = noruecentre;
    }

    @Basic
    @Column(name = "RUECENTRE", nullable = true, length = 200)
    public String getRuecentre() {
        return ruecentre;
    }

    public void setRuecentre(String ruecentre) {
        this.ruecentre = ruecentre;
    }

    @Basic
    @Column(name = "CPOSTALCENTRE", nullable = true, precision = 0)
    public Integer getCpostalcentre() {
        return cpostalcentre;
    }

    public void setCpostalcentre(Integer cpostalcentre) {
        this.cpostalcentre = cpostalcentre;
    }

    @Basic
    @Column(name = "VILLECENTRE", nullable = true, length = 50)
    public String getVillecentre() {
        return villecentre;
    }

    public void setVillecentre(String villecentre) {
        this.villecentre = villecentre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CentretraitementEntityParis that = (CentretraitementEntityParis) o;
        return Objects.equals(nocentre, that.nocentre) &&
                Objects.equals(nomcentre, that.nomcentre) &&
                Objects.equals(noruecentre, that.noruecentre) &&
                Objects.equals(ruecentre, that.ruecentre) &&
                Objects.equals(cpostalcentre, that.cpostalcentre) &&
                Objects.equals(villecentre, that.villecentre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nocentre, nomcentre, noruecentre, ruecentre, cpostalcentre, villecentre);
    }
}
