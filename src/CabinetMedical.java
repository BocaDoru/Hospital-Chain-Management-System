public class CabinetMedical{
    private int idCabinetMedical;
    private String denumire;
    private int idUnitateMedicala;

    public CabinetMedical(int idCabinetMedical, String denumire, int idUnitateMedicala) {
        this.idCabinetMedical = idCabinetMedical;
        this.denumire = denumire;
        this.idUnitateMedicala = idUnitateMedicala;
    }

    public CabinetMedical() {
    }

    public int getIdCabinetMedical() {
        return idCabinetMedical;
    }

    public void setIdCabinetMedical(int idCabinetMedical) {
        this.idCabinetMedical = idCabinetMedical;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getIdUnitateMedicala() {
        return idUnitateMedicala;
    }

    public void setIdUnitateMedicala(int idUnitateMedicala) {
        this.idUnitateMedicala = idUnitateMedicala;
    }

    @Override
    public String toString() {
        return "('" + idCabinetMedical +
                "','" + denumire +
                "','" + idUnitateMedicala +
                "')";
    }
}
