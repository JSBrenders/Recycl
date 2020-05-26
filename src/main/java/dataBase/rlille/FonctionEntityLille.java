package dataBase.rlille;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "FONCTION", schema = "RLILLE")
public class FonctionEntityLille {
    private Integer nofonction;
    private String nomfonction;

    @Id
    @Column(name = "NOFONCTION", nullable = false, precision = 0)
    public Integer getNofonction() {
        return nofonction;
    }

    public void setNofonction(Integer nofonction) {
        this.nofonction = nofonction;
    }

    @Basic
    @Column(name = "NOMFONCTION", nullable = false, length = 50)
    public String getNomfonction() {
        return nomfonction;
    }

    public void setNomfonction(String nomfonction) {
        this.nomfonction = nomfonction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FonctionEntityLille that = (FonctionEntityLille) o;
        return Objects.equals(nofonction, that.nofonction) &&
                Objects.equals(nomfonction, that.nomfonction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nofonction, nomfonction);
    }
}
