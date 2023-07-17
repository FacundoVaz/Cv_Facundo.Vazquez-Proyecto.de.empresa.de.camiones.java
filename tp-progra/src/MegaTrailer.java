public class MegaTrailer extends Transporte {
    private double costoFijo;
    private double costoComida;


    public MegaTrailer(String matricula, double cargaMax, double capacidad, boolean tieneRefrigeracion,
                       double costoKm, double segCarga,  double costoFijo, double costoComida) {
        super(matricula, cargaMax, capacidad, tieneRefrigeracion, costoKm, segCarga);
        this.costoFijo = costoFijo;
        this.costoComida = costoComida;
    }

    @Override
    public double calcularCostoDelViaje() {
        return (this.costoKm * this.getDestino().getKms()) + costoFijo + costoComida + segCarga;
    }
}

// costoFijo >= 0 && costoComidaConductor >= 0
