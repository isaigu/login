package modelo;

public class Alumno
{
    private int NL;
    private String Nombre;
    private String Paterno;
    private String Materno;
    private double DDI;
    private double DWI;
    private double ECBD;

    public int getNL() {
        return NL;
    }

    public void setNL(int NL) {
        this.NL = NL;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getPaterno() {
        return Paterno;
    }

    public void setPaterno(String Paterno) {
        this.Paterno = Paterno;
    }

    public String getMaterno() {
        return Materno;
    }

    public void setMaterno(String Materno) {
        this.Materno = Materno;
    }

    public double getDDI() {
        return DDI;
    }

    public void setDDI(double DDI) {
        this.DDI = DDI;
    }

    public double getDWI() {
        return DWI;
    }

    public void setDWI(double DWI) {
        this.DWI = DWI;
    }

    public double getECBD() {
        return ECBD;
    }

    public void setECBD(double ECBD) {
        this.ECBD = ECBD;
    }

    public double getProm() {
        return (DDI + DWI + ECBD) / 3;
    }
}
