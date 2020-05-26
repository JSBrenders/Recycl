package dataBase.rlille;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class DetaildepotEntityLillePK implements Serializable {
    private Integer notournee;
    private Integer notypedechet;
    private Integer nocentre;

    @Column(name = "NOTOURNEE", nullable = false, precision = 0)
    @Id
    public Integer getNotournee() {
        return notournee;
    }

    public void setNotournee(Integer notournee) {
        this.notournee = notournee;
    }

    @Column(name = "NOTYPEDECHET", nullable = false, precision = 0)
    @Id
    public Integer getNotypedechet() {
        return notypedechet;
    }

    public void setNotypedechet(Integer notypedechet) {
        this.notypedechet = notypedechet;
    }

    @Column(name = "NOCENTRE", nullable = false, precision = 0)
    @Id
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
        DetaildepotEntityLillePK that = (DetaildepotEntityLillePK) o;
        return Objects.equals(notournee, that.notournee) &&
                Objects.equals(notypedechet, that.notypedechet) &&
                Objects.equals(nocentre, that.nocentre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notournee, notypedechet, nocentre);
    }
}
