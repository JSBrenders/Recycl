package dataBase.rparis;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "TYPEDECHET", schema = "RPARIS")
public class TypedechetEntityParis {
    private Integer notypedechet;
    private String nomtypedechet;
    private Integer nivDanger;

    @Id
    @Column(name = "NOTYPEDECHET", nullable = false, precision = 0)
    public Integer getNotypedechet() {
        return notypedechet;
    }

    public void setNotypedechet(Integer notypedechet) {
        this.notypedechet = notypedechet;
    }

    @Basic
    @Column(name = "NOMTYPEDECHET", nullable = true, length = 50)
    public String getNomtypedechet() {
        return nomtypedechet;
    }

    public void setNomtypedechet(String nomtypedechet) {
        this.nomtypedechet = nomtypedechet;
    }

    @Basic
    @Column(name = "NIV_DANGER", nullable = true, precision = 0)
    public Integer getNivDanger() {
        return nivDanger;
    }

    public void setNivDanger(Integer nivDanger) {
        this.nivDanger = nivDanger;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypedechetEntityParis that = (TypedechetEntityParis) o;
        return Objects.equals(notypedechet, that.notypedechet) &&
                Objects.equals(nomtypedechet, that.nomtypedechet) &&
                Objects.equals(nivDanger, that.nivDanger);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notypedechet, nomtypedechet, nivDanger);
    }
}
