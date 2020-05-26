package dataBase.rparis;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "DETAILDEPOT", schema = "RPARIS")
@IdClass(DetaildepotEntityParisPK.class)
public class DetaildepotEntityParis {
    private Integer quantitedeposee;
    private Integer notournee;
    private Integer notypedechet;
    private Integer nocentre;

    @Basic
    @Column(name = "QUANTITEDEPOSEE", nullable = false, precision = 0)
    public Integer getQuantitedeposee() {
        return quantitedeposee;
    }

    public void setQuantitedeposee(Integer quantitedeposee) {
        this.quantitedeposee = quantitedeposee;
    }

    @Id
    @Column(name = "NOTOURNEE", nullable = false, precision = 0)
    public Integer getNotournee() {
        return notournee;
    }

    public void setNotournee(Integer notournee) {
        this.notournee = notournee;
    }

    @Id
    @Column(name = "NOTYPEDECHET", nullable = false, precision = 0)
    public Integer getNotypedechet() {
        return notypedechet;
    }

    public void setNotypedechet(Integer notypedechet) {
        this.notypedechet = notypedechet;
    }

    @Id
    @Column(name = "NOCENTRE", nullable = false, precision = 0)
    public Integer getNocentre() {
        return nocentre;
    }

    public void setNocentre(Integer nocentre) {
        this.nocentre = nocentre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetaildepotEntityParis that = (DetaildepotEntityParis) o;
        return Objects.equals(quantitedeposee, that.quantitedeposee) &&
                Objects.equals(notournee, that.notournee) &&
                Objects.equals(notypedechet, that.notypedechet) &&
                Objects.equals(nocentre, that.nocentre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantitedeposee, notournee, notypedechet, nocentre);
    }
}
