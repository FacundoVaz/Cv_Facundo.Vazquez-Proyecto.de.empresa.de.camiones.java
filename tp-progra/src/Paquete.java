public class Paquete {
    private double peso;
    private double volumen;
    private String destino;
    private boolean necesitaRefrigeracion;

    public Paquete(double peso, double volumen, String destino, boolean necesitaRefrigeracion) {
        this.peso = peso;
        this.volumen = volumen;
        this.destino = destino;
        this.necesitaRefrigeracion = necesitaRefrigeracion;
    }

    public double getPeso() {
        return this.peso;
    }

    public double getVolumen() {
        return this.volumen;
    }

    public String getDestino() {
        return this.destino;
    }

    public boolean necesitaRefrigeracion() {
        return this.necesitaRefrigeracion;
    }

    @Override
    public boolean equals(Object p) {
        if (getClass() != p.getClass()) {
            return false;
        }
        Paquete paquete = (Paquete) p;

        if (this.peso != paquete.getPeso()) {
            System.out.println(this.peso + " es dif " + paquete.getPeso());
            return false;
        }

        if (this.volumen != paquete.getVolumen()) {
            System.out.println(this.volumen + " es dif " + paquete.getVolumen());
            return false;
        }

        if (!this.destino.equals(paquete.getDestino())) {
            System.out.println(this.destino + " es dif " + paquete.getDestino());
            return false;
        }

        if (this.necesitaRefrigeracion != paquete.necesitaRefrigeracion()) {
            System.out.println(this.necesitaRefrigeracion + " es dif " + paquete.necesitaRefrigeracion());
            return false;
        }

        return true;
    }

}

// peso > 0 && volumen > 0 && destino != null && destino.length > 0
