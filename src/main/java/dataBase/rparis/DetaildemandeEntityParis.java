package dataBase.rparis;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "DETAILDEMANDE", schema = "RPARIS")
@IdClass(DetaildemandeEntityParisPK.class)
public class DetaildemandeEntityParis {
    private Integer quantiteenlevee;
    private Integer nodemande;
    private Integer notypedechet;

    @Basic
    @Column(name = "QUANTITEENLEVEE", nullable = false, precision = 0)
    public Integer getQuantiteenlevee() {
        return quantiteenlevee;
    }

    public void setQuantiteenlevee(Integer quantiteenlevee) {
        this.quantiteenlevee = quantiteenlevee;
    }

    @Id
    @Column(name = "NODEMANDE", nullable = false, precision = 0)
    public Integer getNodemande() {
        return nodemande;
    }

    public void setNodemande(Integer nodemande) {
        this.nodemande = nodemande;
    }

    @Id
    @Column(name = "NOTYPEDECHET", nullable = false, precision = 0)
    public Integer getNotypedechet() {
        return notypedechet;
    }

    public void setNotypedechet(Integer notypedechet) {
        this.notypedechet = notypedechet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetaildemandeEntityParis that = (DetaildemandeEntityParis) o;
        return Objects.equals(quantiteenlevee, that.quantiteenlevee) &&
                Objects.equals(nodemande, that.nodemande) &&
                Objects.equals(notypedechet, that.notypedechet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantiteenlevee, nodemande, notypedechet);
    }
}
