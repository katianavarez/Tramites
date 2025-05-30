/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import dtos.LicenciaDTO;
import dtos.PersonaDTO;
import encriptador.Encriptador;
import entidades.Licencia;
import entidades.Persona;
import enums.Duracion;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import idaos.ILicenciaDAO;
import idaos.IPersonaDAO;
import interfaces.ILicenciaBO;
import interfaces.IPersonaBO;
import java.time.LocalDate;
import java.time.Month;
import mappers.LicenciaMapper;
import mappers.PersonaMapper;

/**
 *
 * @author katia
 */
public class PersonaBO implements IPersonaBO{
    private final IPersonaDAO personaDAO;
    private final PersonaMapper mapper;
    private final ILicenciaDAO licenciaDAO;
    private final LicenciaMapper licenciaMapper;
    private final ILicenciaBO licenciaBO;


    public PersonaBO(IPersonaDAO personaDAO, ILicenciaDAO licenciaDAO, ILicenciaBO licenciaBO) {
        this.personaDAO = personaDAO;
        this.mapper = new PersonaMapper();
        this.licenciaDAO = licenciaDAO;
        this.licenciaMapper = new LicenciaMapper();
        this.licenciaBO = licenciaBO;
    }

    @Override
    public PersonaDTO registrarPersona(PersonaDTO personaDTO) throws NegocioException {
        try {
            validarPersona(personaDTO);
            personaDTO.setTelefono(Encriptador.encriptar(personaDTO.getTelefono().trim()));
            Persona persona = mapper.toEntity(personaDTO);
            Persona registrada = personaDAO.registrarPersona(persona);
            return mapper.toDTO(registrada);

        } catch (PersistenciaException e) {
            throw new NegocioException("No se ha podido registrar a la persona.", e);
        }
    }

    @Override
    public PersonaDTO registrarPersonaConLicencia(PersonaDTO personaDTO, LicenciaDTO licenciaDTO) throws NegocioException {
        try {
            validarPersona(personaDTO);

            if (licenciaDTO.getDuracion() == null) {
                throw new NegocioException("La duración de la licencia es obligatoria.");
            }
            
            personaDTO.setTelefono(Encriptador.encriptar(personaDTO.getTelefono().trim()));
            Persona persona = mapper.toEntity(personaDTO);
            Persona personaRegistrada = personaDAO.registrarPersona(persona);

            licenciaDTO.setFechaExpedicion(LocalDate.now());
            licenciaDTO.setIdPersona(personaRegistrada.getId());
            
            double costo = 0;
            switch (licenciaDTO.getDuracion()) {
                case UNO -> costo = 500;
                case DOS -> costo = 900;
                case TRES -> costo = 1200;
            }
            
            licenciaDTO.setCosto(costo);

            Licencia licencia = licenciaMapper.toEntity(licenciaDTO);
            licenciaDAO.registrarLicencia(licencia);

            return mapper.toDTO(personaRegistrada);
        } catch (PersistenciaException e) {
            throw new NegocioException("No se ha podido registrar persona con licencia.", e);
        }
    }

    @Override
    public PersonaDTO buscarPersonaPorRFC(String rfc) throws NegocioException {
        if (rfc == null || rfc.length() != 13) {
            throw new NegocioException("El RFC debe tener 13 caracteres.");
        }
        try {
            Persona persona = personaDAO.buscarPersonaPorRFC(rfc);
            return mapper.toDTO(persona);
        } catch (PersistenciaException e) {
            throw new NegocioException("No se ha podido buscar a la persona por RFC.", e);
        }
    }

    @Override
    public PersonaDTO buscarPersonaPorId(Long id) throws NegocioException {
        try {
            Persona persona = personaDAO.buscarPersonaPorId(id);
            return mapper.toDTO(persona);
        } catch (PersistenciaException e) {
            throw new NegocioException("No se ha podido buscar a la persona por ID.", e);
        }
    }

    private void validarPersona(PersonaDTO personaDTO) throws NegocioException {
        if (personaDTO.getRfc() == null || personaDTO.getRfc().isBlank()) {
            throw new NegocioException("El RFC es obligatorio.");
        }
        if (personaDTO.getRfc().length() != 13) {
            throw new NegocioException("El RFC debe contener 13 caracteres.");
        }
        if (personaDTO.getNombre() == null || personaDTO.getNombre().isBlank()) {
            throw new NegocioException("El nombre es obligatorio.");
        }
        if (personaDTO.getApellidoPaterno() == null || personaDTO.getApellidoPaterno().isBlank()) {
            throw new NegocioException("El apellido paterno es obligatorio.");
        }
        if (personaDTO.getTelefono() == null || personaDTO.getTelefono().isBlank()) {
            throw new NegocioException("El teléfono es obligatorio.");
        }
        if (!personaDTO.getTelefono().matches("\\d{10}")) {
            throw new NegocioException("El teléfono debe contener 10 dígitos.");
        }
        if (personaDTO.getFechaNacimiento() == null) {
            throw new NegocioException("La fecha de nacimiento es obligatoria.");
        }
        if (personaDTO.getFechaNacimiento().isAfter(LocalDate.now())) {
            throw new NegocioException("Fecha de nacimiento inválida.");
        }
    }
    
    @Override
    public void insertarMasivamentePersonasConLicencia(int cantidad) throws NegocioException {
        if (cantidad <= 0) {
            throw new NegocioException("La cantidad debe ser un número positivo.");
        }

        for (int i = 1; i <= cantidad; i++) {
            try {
                PersonaDTO persona = new PersonaDTO();
                persona.setNombre("Jose" + i);
                persona.setApellidoPaterno("Lopez" + i);
                persona.setApellidoMaterno("Soto" + i);
                
                String rfcBase = "RFC" + String.format("%010d", i); // RFC0000000001
                String rfc = rfcBase.substring(0, 13);
                persona.setRfc(rfc);
                
                persona.setTelefono(String.format("66200000%02d", i));
                persona.setFechaNacimiento(LocalDate.of(1990 + (i % 10), Month.JANUARY, (i % 28) + 1));

                PersonaDTO personaRegistrada = registrarPersona(persona);

                LicenciaDTO licencia = new LicenciaDTO();
                licencia.setIdPersona(personaRegistrada.getId());
                licencia.setCosto(1200.0);
                licencia.setFechaExpedicion(LocalDate.now());
                licencia.setDuracion(Duracion.TRES);

                licenciaBO.registrarLicencia(licencia);

            } catch (NegocioException e) {
                System.err.println("Error al insertar personas" + e.getMessage());
            }
        }
    }
}
