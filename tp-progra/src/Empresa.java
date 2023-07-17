import java.util.ArrayList;
import java.util.HashMap;

public class Empresa {
    private String nombre;
    private String cuit;
    private ArrayList<Deposito> depositos;
    private HashMap<String, Transporte> transportes;
    private HashMap<String, Destino> destinos;

    public Empresa(String cuit, String nombre, int capacidadDepositos) {
        this.nombre = nombre;
        this.cuit = cuit;

        this.depositos = new ArrayList<Deposito>();
        this.depositos.add(new Deposito(true, capacidadDepositos));
        this.depositos.add(new Deposito(false, capacidadDepositos));

        this.destinos = new HashMap<String, Destino>();
        this.transportes = new HashMap<String, Transporte>();
    }

    @Override
    public String toString() {
        return this.cuit;
    }

    public void agregarDestino(String destino, int km) throws RuntimeException {
    	if (this.destinos.get(destino) != null) {
    		throw new RuntimeException("El destino ya existe");
    	}
        this.destinos.put(destino, new Destino(destino, km));
    }

    public void agregarTrailer(String matricula, double cargaMax, double capacidad, boolean tieneRefrigeracion,
                               double costoKm, double segCarga) {
        this.transportes.put(
                matricula,
                new Trailer(matricula, cargaMax, capacidad, tieneRefrigeracion, costoKm, segCarga)
        );
    }

    public void agregarMegaTrailer(String matricula, double cargaMax, double capacidad, boolean tieneRefrigeracion,
                               double costoKm, double segCarga,  double costoFijo, double costoComida) {
        this.transportes.put(
                matricula,
                new MegaTrailer(matricula, cargaMax, capacidad, tieneRefrigeracion, costoKm, segCarga, costoFijo, costoComida)
        );
    }

    public void agregarFlete(String matricula, double cargaMax, double capacidad, double costoKm,
                             int cantAcompaniantes, double costoPorAcompaniante) {
        this.transportes.put(
                matricula,
                new Flete(matricula, cargaMax, capacidad, costoKm, cantAcompaniantes, costoPorAcompaniante)
        );
    }

    public void asignarDestino(String matricula, String destino) throws Exception {
        Transporte transporte = this.getTransporte(matricula);
        Destino dest = this.destinos.get(destino);

        if (dest == null) {
            throw new Exception("El destino no existe");
        }

        if (transporte.getDestino() == null && !transporte.estaEnViaje()) {
            transporte.asignarDestino(dest);
        }
    }

    public boolean incorporarPaquete(String destino, double peso, double volumen, boolean necesitaRefrigeracion) {
        Paquete paquete = new Paquete(peso, volumen, destino, necesitaRefrigeracion);
        Deposito deposito = this.getDeposito(paquete.necesitaRefrigeracion());

        try {
            deposito.agregarPaquete(paquete);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private Deposito getDeposito(boolean refrigerado) {
        for (Deposito deposito : this.depositos) {
            if (deposito.esRefrigerado() == refrigerado) {
                return deposito;
            }
        }
        return null;
    }

    public double cargarTransporte(String matricula) throws Exception {
        Transporte transporte = this.getTransporte(matricula);
        if (transporte.estaEnViaje()) {
            throw new Exception("El transporte esta en viaje");
        }
        if (transporte.getDestino() == null) {
            throw new Exception("El transporte no tiene asignado un destino");
        }

        double volumenInicial = transporte.getVolumenTotal();
        Deposito deposito = getDeposito(transporte.esRefrigerado());
        while (true) {
            Paquete paquete = deposito.retirarPaquete(transporte.getDestino().getDestino());
            if (paquete == null) {
                break;
            }

            try {
                transporte.cargarPaquete(paquete);
                System.out.println("Paquete cargado correctamente" + " - peso: " + paquete.getPeso() + " - volumen: " + paquete.getVolumen());
            } catch (Exception e) {
                System.out.println("El paquete no entra");
                deposito.agregarPaquete(paquete);
                break;
            }
        }

        return transporte.getVolumenTotal() - volumenInicial;
    }

    public void iniciarViaje(String matricula) throws Exception {
        Transporte transporte = this.getTransporte(matricula);
        transporte.iniciarViaje();
    }

    public void finalizarViaje(String matricula) throws Exception {
        Transporte transporte = this.getTransporte(matricula);
        transporte.finalizarViaje();
    }

    public void printData() {
        System.out.println(this.destinos);
        System.out.println(this.transportes);
    }

    private Transporte getTransporte(String matricula) throws Exception {
        Transporte transporte = this.transportes.get(matricula);
        if (transporte == null) {
            throw new RuntimeException("El transporte no existe");
        }
        return transporte;
    }

    // Obtiene el costo de viaje del transporte identificado por la
    // matrícula pasada por parámetro.
    // Genera Excepción si el transporte no está en viaje.
    double obtenerCostoViaje(String matricula) throws Exception {
        Transporte transporte = this.getTransporte(matricula);
        if (!transporte.estaEnViaje()) {
            throw new Exception("El transporte no esta en viaje");
        }
        return transporte.calcularCostoDelViaje();
    }

    //  destino y carga.
    // En caso de que no se encuentre ninguno, se debe devolver null.
    String obtenerTransporteIgual(String matricula) throws Exception {
        Transporte transporte = this.getTransporte(matricula);

        for (Transporte t : this.transportes.values()) {
            if(t.getMatricula().equals(transporte.getMatricula())) {
                continue;
            }
            if (transporte.equals(t)) {
                return t.getMatricula();
            }
        }

        return null;
    }

}
