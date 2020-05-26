package dataBase.rlille;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ENTREPRISE", schema = "RLILLE")
public class EntrepriseEntityLille {
    private Long siret;
    private String raisonsociale;
    private Integer norueentr;
    private String rueentr;
    private Integer cpostalentr;
    private String villeentr;
    private String notel;
    private String contact;

    @Id
    @Column(name = "SIRET", nullable = false, precision = 0)
    public Long getSiret() {
        return siret;
    }

    public void setSiret(Long siret) {
        this.siret = siret;
    }

    @Basic
    @Column(name = "RAISONSOCIALE", nullable = false)
    public String getRaisonsociale() {
        return raisonsociale;
    }

    public void setRaisonsociale(String raisonsociale) {
        this.raisonsociale = raisonsociale;
    }

    @Basic
    @Column(name = "NORUEENTR", nullable = true, precision = 0)
    public Integer getNorueentr() {
        return norueentr;
    }

    public void setNorueentr(Integer norueentr) {
        this.norueentr = norueentr;
    }

    @Basic
    @Column(name = "RUEENTR", nullable = true, length = 200)
    public String getRueentr() {
        return rueentr;
    }

    public void setRueentr(String rueentr) {
        this.rueentr = rueentr;
    }

    @Basic
    @Column(name = "CPOSTALENTR", nullable = true, precision = 0)
    public Integer getCpostalentr() {
        return cpostalentr;
    }

    public void setCpostalentr(Integer cpostalentr) {
        this.cpostalentr = cpostalentr;
    }

    @Basic
    @Column(name = "VILLEENTR", nullable = true, length = 50)
    public String getVilleentr() {
        return villeentr;
    }

    public void setVilleentr(String villeentr) {
        this.villeentr = villeentr;
    }

    @Basic
    @Column(name = "NOTEL", nullable = true, length = 10)
    public String getNotel() {
        return notel;
    }

    public void setNotel(String notel) {
        this.notel = notel;
    }

    @Basic
    @Column(name = "CONTACT", nullable = true, length = 50)
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntrepriseEntityLille that = (EntrepriseEntityLille) o;
        return Objects.equals(siret, that.siret) &&
                Objects.equals(raisonsociale, that.raisonsociale) &&
                Objects.equals(norueentr, that.norueentr) &&
                Objects.equals(rueentr, that.rueentr) &&
                Objects.equals(cpostalentr, that.cpostalentr) &&
                Objects.equals(villeentr, that.villeentr) &&
                Objects.equals(notel, that.notel) &&
                Objects.equals(contact, that.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(siret, raisonsociale, norueentr, rueentr, cpostalentr, villeentr, notel, contact);
    }
}
