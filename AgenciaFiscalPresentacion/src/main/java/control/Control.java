/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import bos.AutomovilBO;
import bos.LicenciaBO;
import bos.PersonaBO;
import bos.PlacaBO;
import bos.VehiculoBO;
import daos.AutomovilDAO;
import daos.LicenciaDAO;
import daos.PersonaDAO;
import daos.PlacaDAO;
import daos.VehiculoDAO;
import dtos.LicenciaDTO;
import dtos.PersonaDTO;
import dtos.PlacaDTO;
import excepciones.NegocioException;
import interfaces.IAutomovilBO;
import interfaces.ILicenciaBO;
import interfaces.IPersonaBO;
import interfaces.IPlacaBO;
import interfaces.IVehiculoBO;
import java.util.List;
import pantallas.PantallaConsultarHistorialPlacas;
import pantallas.PantallaInicio;
import pantallas.PantallaMenuPlacas;
import pantallas.PantallaPlacasAutoNuevo;
import pantallas.PantallaPlacasAutoUsado;
import pantallas.PantallaRegistroPersonaYLicencia;

/**
 * Clase que controla el flujo de navegación entre pantallas y 
 * delega la lógica a la capa de negocio.
 * Implementa singleton.
 * 
 * @author katia
 */
public class Control {
    private static Control instancia;

    private PantallaInicio pantallaInicio;
    private PantallaRegistroPersonaYLicencia pantallaRegistro;
    private PantallaMenuPlacas pantallaMenuPlacas;
    private PantallaPlacasAutoNuevo pantallaPlacasAutoNuevo;
    private PantallaPlacasAutoUsado pantallaPlacasAutoUsado;
    private PantallaConsultarHistorialPlacas pantallaConsultarHistorialPlacas;

    private IPersonaBO personaBO;
    private ILicenciaBO licenciaBO;
    private IPlacaBO placaBO;
    private IVehiculoBO vehiculoBO;
    private IAutomovilBO automovilBO;

    private Control() {
        inicializarBOs();
        inicializarPantallas();
    }
    
    /**
     * Obtiene la única instancia del controlador.
     * 
     * @return instancia única del controlador.
     */
    public static Control getInstancia() {
        if (instancia == null) {
            instancia = new Control();
        }
        return instancia;
    }
    
    /**
     * Inicializa todas las instancias de los objetos de negocio.
     */
    private void inicializarBOs() {
        this.licenciaBO = new LicenciaBO(new LicenciaDAO());
        this.personaBO = new PersonaBO(new PersonaDAO(), new LicenciaDAO(), this.licenciaBO);
        this.placaBO = new PlacaBO(new PlacaDAO(), new VehiculoDAO(), new LicenciaDAO(), new PersonaDAO());
        this.vehiculoBO = new VehiculoBO(new VehiculoDAO());
        this.automovilBO = new AutomovilBO(new AutomovilDAO());
    }
    
    /**
     * Inicializa todas las pantallas de la aplicación.
     */
    private void inicializarPantallas() {
        this.pantallaInicio = new PantallaInicio();
        this.pantallaRegistro = new PantallaRegistroPersonaYLicencia();
        this.pantallaMenuPlacas = new PantallaMenuPlacas();
        this.pantallaPlacasAutoNuevo = new PantallaPlacasAutoNuevo();
        this.pantallaPlacasAutoUsado = new PantallaPlacasAutoUsado();
        this.pantallaConsultarHistorialPlacas = new PantallaConsultarHistorialPlacas();
    }
    
    public IPersonaBO getPersonaBO() {
        return personaBO;
    }

    public ILicenciaBO getLicenciaBO() {
        return licenciaBO;
    }

    public IPlacaBO getPlacaBO() {
        return placaBO;
    }

    public IVehiculoBO getVehiculoBO() {
        return vehiculoBO;
    }

    public IAutomovilBO getAutomovilBO() {
        return automovilBO;
    }
    
    public void mostrarPantallaInicio() {
        ocultarTodas();
        pantallaInicio.setVisible(true);
    }

    public void mostrarPantallaRegistroPersonaYLicencia() {
        ocultarTodas();
        pantallaRegistro.setVisible(true);
    }

    public void mostrarPantallaMenuPlacas() {
        ocultarTodas();
        pantallaMenuPlacas.setVisible(true);
    }

    public void mostrarPantallaPlacasAutoNuevo() {
        ocultarTodas();
        pantallaPlacasAutoNuevo.setVisible(true);
    }

    public void mostrarPantallaPlacasAutoUsado() {
        ocultarTodas();
        pantallaPlacasAutoUsado.setVisible(true);
    }

    public void mostrarPantallaConsultarHistorialPlacas() {
        ocultarTodas();
        pantallaConsultarHistorialPlacas.setVisible(true);
    }
    
    /**
     * Oculta todas las pantallas de la aplicación.
     */
    private void ocultarTodas() {
        pantallaInicio.setVisible(false);
        pantallaRegistro.setVisible(false);
        pantallaMenuPlacas.setVisible(false);
        pantallaPlacasAutoNuevo.setVisible(false);
        pantallaPlacasAutoUsado.setVisible(false);
        pantallaConsultarHistorialPlacas.setVisible(false);
    }
    
    /**
     * Inicia la aplicación mostrando la pantalla de inicio.
     */
    public void iniciar() {
        mostrarPantallaInicio();
    }
    
    /**
     * Registra una persona junto con su licencia.
     * 
     * @param personaDTO DTO con los datos de la persona.
     * @param licenciaDTO DTO con los datos de la licencia.
     * @throws NegocioException si ocurre un error en la lógica de negocio.
     */
    public void registrarPersonaYLicencia(PersonaDTO personaDTO, LicenciaDTO licenciaDTO) throws NegocioException {
        personaBO.registrarPersonaConLicencia(personaDTO, licenciaDTO);
    }
    
    /**
     * Genera los datos para una placa de auto nuevo.
     * 
     * @return DTO con los datos generados.
     */
    public PlacaDTO generarDatosPlacaAutoNuevo() {
        return placaBO.generarPlacaAutoNuevo();
    }
    
    /**
     * Registra una placa para un auto nuevo.
     * 
     * @param dto DTO con los datos de la placa.
     * @param rfc RFC de la persona.
     * @return DTO de la placa registrada.
     * @throws NegocioException si ocurre un error en la lógica de negocio.
     */
    public PlacaDTO registrarPlacaAutoNuevo(PlacaDTO dto, String rfc) throws NegocioException {
        return placaBO.registrarPlacaAutoNuevo(dto, rfc);
    }
    
    /**
     * Registra una placa para un auto usado.
     * 
     * @param dto DTO con los datos de la placa.
     * @param rfcPersona RFC de la persona.
     * @return DTO de la placa registrada.
     * @throws NegocioException si ocurre un error en la lógica de negocio.
     */
    public PlacaDTO registrarPlacaAutoUsado(PlacaDTO dto, String rfcPersona) throws NegocioException {
        return placaBO.registrarPlacaAutoUsado(dto, rfcPersona);
    }
    
    /**
     * Obtiene el historial de placas asociadas a un número de serie de vehículo.
     * 
     * @param numeroSerie Número de serie del vehículo.
     * @return Lista de DTOs con el historial de placas.
     * @throws NegocioException si ocurre un error en la lógica de negocio.
     */
    public List<PlacaDTO> obtenerHistorialPlacas(String numeroSerie) throws NegocioException {
        return placaBO.obtenerHistorialPorNumSerie(numeroSerie);
    }

}
