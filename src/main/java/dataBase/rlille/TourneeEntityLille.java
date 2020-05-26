package dataBase.rlille;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "TOURNEE", schema = "RLILLE")
public class TourneeEntityLille {
    private Integer notournee;
    private Date datetournee;
    private String noimmatric;
    private Integer noemploye;

    @Id
    @Column(name = "NOTOURNEE", nullable = false, precision = 0)
    public Integer getNotournee() {
        return notournee;
    }

    public void setNotournee(Integer notournee) {
        this.notournee = notournee;
    }

    @Basic
    @Column(name = "DATETOURNEE", nullable = true)
    public Date getDatetournee() {
        return datetournee;
    }

    public void setDatetournee(Date datetournee) {
        this.datetournee = datetournee;
    }

    @Basic
    @Column(name = "NOIMMATRIC", nullable = false, length = 10)
    public String getNoimmatric() {
        return noimmatric;
    }

    public void setNoimmatric(String noimmatric) {
        this.noimmatric = noimmatric;
    }

    @Basic
    @Column(name = "NOEMPLOYE", nullable = false, precision = 0)
    public Integer getNoemploye() {
        return noemploye;
    }

    public void setNoemploye(Integer noemploye) {
        this.noemploye = noemploye;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourneeEntityLille that = (TourneeEntityLille) o;
        return Objects.equals(notournee, that.notournee) &&
                Objects.equals(datetournee, that.datetournee) &&
                Objects.equals(noimmatric, that.noimmatric) &&
                Objects.equals(noemploye, that.noemploye);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notournee, datetournee, noimmatric, noemploye);
    }
}
