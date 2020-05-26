package dataBase.rlille;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class DetaildemandeEntityLillePK implements Serializable {
    private Integer nodemande;
    private Integer notypedechet;

    @Column(name = "NODEMANDE", nullable = false, precision = 0)
    @Id
    public Integer getNodemande() {
        return nodemande;
    }

    public void setNodemande(Integer nodemande) {
        this.nodemande = nodemande;
    }

    @Column(name = "NOTYPEDECHET", nullable = false, precision = 0)
    @Id
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
        DetaildemandeEntityLillePK that = (DetaildemandeEntityLillePK) o;
        return Objects.equals(nodemande, that.nodemande) &&
                Objects.equals(notypedechet, that.notypedechet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodemande, notypedechet);
    }
}
