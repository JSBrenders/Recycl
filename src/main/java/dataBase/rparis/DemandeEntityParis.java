package dataBase.rparis;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "DEMANDE", schema = "RPARIS")
public class DemandeEntityParis {
    private Integer nodemande;
    private Date datedemande;
    private Date dateenlevement;
    private char[] webON;
    private Long siret;
    private Integer notournee;

    @Id
    @Column(name = "NODEMANDE", nullable = false, precision = 0)
    public Integer getNodemande() {
        return nodemande;
    }

    public void setNodemande(Integer nodemande) {
        this.nodemande = nodemande;
    }

    @Basic
    @Column(name = "DATEDEMANDE", nullable = true)
    public Date getDatedemande() {
        return datedemande;
    }

    public void setDatedemande(Date datedemande) {
        this.datedemande = datedemande;
    }

    @Basic
    @Column(name = "DATEENLEVEMENT", nullable = true)
    public Date getDateenlevement() {
        return dateenlevement;
    }

    public void setDateenlevement(Date dateenlevement) {
        this.dateenlevement = dateenlevement;
    }

    @Basic
    @Column(name = "WEB_O_N", nullable = true, length = 1)
    public char[] getWebON() {
        return webON;
    }

    public void setWebON(char[] webON) {
        this.webON = webON;
    }

    @Basic
    @Column(name = "SIRET", nullable = false, precision = 0)
    public Long getSiret() {
        return siret;
    }

    public void setSiret(Long siret) {
        this.siret = siret;
    }

    @Basic
    @Column(name = "NOTOURNEE", nullable = true, precision = 0)
    public Integer getNotournee() {
        return notournee;
    }

    public void setNotournee(Integer notournee) {
        this.notournee = notournee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DemandeEntityParis that = (DemandeEntityParis) o;
        return Objects.equals(nodemande, that.nodemande) &&
                Objects.equals(datedemande, that.datedemande) &&
                Objects.equals(dateenlevement, that.dateenlevement) &&
                Arrays.equals(webON, that.webON) &&
                Objects.equals(siret, that.siret) &&
                Objects.equals(notournee, that.notournee);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(nodemande, datedemande, dateenlevement, siret, notournee);
        result = 31 * result + Arrays.hashCode(webON);
        return result;
    }
}
