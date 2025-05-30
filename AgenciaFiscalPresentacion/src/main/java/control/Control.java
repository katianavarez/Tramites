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
import idaos.ILicenciaDAO;
import idaos.IPersonaDAO;
import interfaces.IAutomovilBO;
import interfaces.ILicenciaBO;
import interfaces.IPersonaBO;
import interfaces.IPlacaBO;
import interfaces.IVehiculoBO;
import java.util.List;
import mappers.LicenciaMapper;
import mappers.PersonaMapper;
import pantallas.PantallaConsultarHistorialPlacas;
import pantallas.PantallaInicio;
import pantallas.PantallaMenuPlacas;
import pantallas.PantallaPlacasAutoNuevo;
import pantallas.PantallaPlacasAutoUsado;
import pantallas.PantallaRegistroPersonaYLicencia;

/**
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
    
    public static Control getInstancia() {
        if (instancia == null) {
            instancia = new Control();
        }
        return instancia;
    }
    
    private void inicializarBOs() {
        this.licenciaBO = new LicenciaBO(new LicenciaDAO());
        this.personaBO = new PersonaBO(new PersonaDAO(), new LicenciaDAO(), this.licenciaBO);
        this.placaBO = new PlacaBO(new PlacaDAO(), new VehiculoDAO(), new LicenciaDAO(), new PersonaDAO());
        this.vehiculoBO = new VehiculoBO(new VehiculoDAO());
        this.automovilBO = new AutomovilBO(new AutomovilDAO());
    }
    
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
    
    private void ocultarTodas() {
        pantallaInicio.setVisible(false);
        pantallaRegistro.setVisible(false);
        pantallaMenuPlacas.setVisible(false);
        pantallaPlacasAutoNuevo.setVisible(false);
        pantallaPlacasAutoUsado.setVisible(false);
        pantallaConsultarHistorialPlacas.setVisible(false);
    }
    
    public void iniciar() {
        mostrarPantallaInicio();
    }
    
    public void registrarPersonaYLicencia(PersonaDTO personaDTO, LicenciaDTO licenciaDTO) throws NegocioException {
        personaBO.registrarPersonaConLicencia(personaDTO, licenciaDTO);
    }
    
    public PlacaDTO generarDatosPlacaAutoNuevo() {
        return placaBO.generarPlacaAutoNuevo();
    }
    
    public PlacaDTO registrarPlacaAutoNuevo(PlacaDTO dto, String rfc) throws NegocioException {
        return placaBO.registrarPlacaAutoNuevo(dto, rfc);
    }
    
    public PlacaDTO registrarPlacaAutoUsado(PlacaDTO dto, String rfcPersona) throws NegocioException {
        return placaBO.registrarPlacaAutoUsado(dto, rfcPersona);
    }
    
    public List<PlacaDTO> obtenerHistorialPlacas(String numeroSerie) throws NegocioException {
        return placaBO.obtenerHistorialPorNumSerie(numeroSerie);
    }

}
