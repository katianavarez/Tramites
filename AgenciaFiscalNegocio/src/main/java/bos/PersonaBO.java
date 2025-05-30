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
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import idaos.ILicenciaDAO;
import idaos.IPersonaDAO;
import interfaces.IPersonaBO;
import java.time.LocalDate;
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

    public PersonaBO(IPersonaDAO personaDAO, ILicenciaDAO licenciaDAO) {
        this.personaDAO = personaDAO;
        this.mapper = new PersonaMapper();
        this.licenciaDAO = licenciaDAO;
        this.licenciaMapper = new LicenciaMapper();
    }

    @Override
    public PersonaDTO registrarPersona(PersonaDTO personaDTO) throws NegocioException {
        try {
            validarPersona(personaDTO);
            Persona persona = mapper.toEntity(personaDTO);
            persona.setTelefono(Encriptador.encriptar(personaDTO.getTelefono().trim()));

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

            Persona persona = new Persona();
            persona.setRfc(personaDTO.getRfc().trim());
            persona.setNombre(personaDTO.getNombre().trim());
            persona.setApellidoPaterno(personaDTO.getApellidoPaterno().trim());
            persona.setApellidoMaterno(personaDTO.getApellidoMaterno() != null ? personaDTO.getApellidoMaterno().trim() : null);
            persona.setTelefono(Encriptador.encriptar(personaDTO.getTelefono().trim()));
            persona.setFechaNacimiento(personaDTO.getFechaNacimiento());

            Persona personaRegistrada = personaDAO.registrarPersona(persona);

            LicenciaDTO nuevaLicDTO = new LicenciaDTO();
            nuevaLicDTO.setDuracion(licenciaDTO.getDuracion());
            nuevaLicDTO.setFechaExpedicion(LocalDate.now());
            nuevaLicDTO.setIdPersona(personaRegistrada.getId());

            double costo = 0;
            switch (licenciaDTO.getDuracion()) {
                case UNO -> costo = 500;
                case DOS -> costo = 900;
                case TRES -> costo = 1200;
            }
            
            nuevaLicDTO.setCosto(costo);

            Licencia licencia = licenciaMapper.toEntity(nuevaLicDTO);
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
        if (!personaDTO.getNombre().matches("[a-zA-ZÁÉÍÓÚáéíóúñÑ ]+")) {
            throw new NegocioException("El nombre solo puede tener letras.");
        }
        if (personaDTO.getApellidoPaterno() == null || personaDTO.getApellidoPaterno().isBlank()) {
            throw new NegocioException("El apellido paterno es obligatorio.");
        }
        if (!personaDTO.getApellidoPaterno().matches("[a-zA-ZÁÉÍÓÚáéíóúñÑ ]+")) {
            throw new NegocioException("El apellido paterno solo puede tener letras.");
        }
        if (personaDTO.getApellidoMaterno() != null && !personaDTO.getApellidoMaterno().trim().isEmpty()) {
            if (!personaDTO.getApellidoMaterno().matches("[a-zA-ZÁÉÍÓÚáéíóúñÑ ]+")) {
                throw new NegocioException("El apellido materno solo puede tener letras.");
            }
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
}
