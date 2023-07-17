public class Flete extends Transporte {
    private int acompanantes;
    private double costoPorAcompaniante;


    public Flete(String matricula, double cargaMax, double capacidad, double costoKm, int cantAcompaniantes,
                 double costoPorAcompaniante) {
        super(matricula, cargaMax, capacidad, costoKm);
        this.acompanantes = cantAcompaniantes;
        this.costoPorAcompaniante = costoPorAcompaniante;
    }

    @Override
    public double calcularCostoDelViaje() {
        return (this.costoKm * this.getDestino().getKms()) + (this.acompanantes * this.costoPorAcompaniante);
    }
}

// acompanantes >= 0 && costoPorAcompañante >= 0
