import java.util.ArrayList;

public class Deposito {
    private ArrayList<Paquete> paquetes;
    private boolean conRefrigeracion;
    private double capacidadMaxima;

    public Deposito(boolean conRefrigeracion, int capacidadMaxima) {
        this.paquetes = new ArrayList<Paquete>();
        this.conRefrigeracion = conRefrigeracion;
        this.capacidadMaxima = capacidadMaxima;
    }

    public void agregarPaquete(Paquete paquete) throws Exception {
        if (paquete.getVolumen() >= this.calcularCapacidadLibre()) {
            throw new RuntimeException("El deposito esta lleno");
        }
        if (this.esRefrigerado() != paquete.necesitaRefrigeracion()) {
            throw new RuntimeException("Este deposito no es el correcto");
        }
        this.paquetes.add(paquete);
    }

    public Paquete retirarPaquete(String destino) {
        for (int idx = 0; idx < this.paquetes.size(); idx++) {
            Paquete paquete = this.paquetes.get(idx);
            if (paquete.getDestino().equals(destino)) {
                return this.paquetes.remove(idx);
            }
        }
        return null;
    }

    public boolean esRefrigerado() {
        return this.conRefrigeracion;
    }
    
    private double calcularCapacidadLibre() {
    	double capacidad = this.capacidadMaxima;
    	for (Paquete paquete: this.paquetes) {
    		capacidad -= paquete.getVolumen();
    	}
    	return capacidad;
    }
}

// capacidadMaxima > 0 && paquetes != null
