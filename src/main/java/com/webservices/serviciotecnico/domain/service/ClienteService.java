package com.webservices.serviciotecnico.domain.service;

import com.webservices.serviciotecnico.domain.repository.ClienteRepository;
import com.webservices.serviciotecnico.domain.repository.RolRepository;
import com.webservices.serviciotecnico.domain.repository.UsuarioRepository;
import com.webservices.serviciotecnico.persistence.model.Persona;
import com.webservices.serviciotecnico.persistence.model.Rol;
import com.webservices.serviciotecnico.persistence.model.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final UsuarioService usuarioService;
    private final RolRepository rolRepository;

    // Hemos eliminado el UsuarioRolRepository ya que JPA manejará la tabla intermedia
    public ClienteService(ClienteRepository clienteRepository, UsuarioService usuarioService, RolRepository rolRepository){
        this.clienteRepository = clienteRepository;
        this.usuarioService = usuarioService;

        this.rolRepository = rolRepository;
    }

    public Optional<List<Persona>> getClients(){
        return clienteRepository.getClients();
    }

    @Transactional
    public Persona save(Persona persona) throws Exception {
        try {
            // 1. Guardar la persona para generar su ID
            Persona cliente = clienteRepository.save(persona);

            // 2. Generar el Username (Primer nombre + Primer apellido unidos)
            // Usamos [0] para tomar solo la primera palabra en caso de nombres compuestos
            String primerNombre = persona.getNombres().split(" ")[0];
            String primerApellido = persona.getApellidos().split(" ")[0];
            String userName = primerNombre + primerApellido;

            // 3. Generar la Contraseña de forma segura
            String cod1 = extraerSubcadena(persona.getNombres(), 0, 3);
            String cod2 = extraerSubcadenaFinal(persona.getApellidos(), 3);
            String cod3 = extraerSubcadenaFinal(persona.getCedula(), 3);
            String codigo = cod1 + cod2 + cod3 + "!*";

            // 4. Configurar y guardar el Usuario
            Usuario usuario = new Usuario();
            usuario.setUsuario(userName);
            usuario.setContrasenia(codigo);
            usuario.setIdPersona(cliente.getIdPersona()); // Corregido: () añadidos

            // 5. Asignar el Rol aprovechando @ManyToMany
            Integer idRol = rolRepository.SearchRoleId();
            Rol rolCliente = rolRepository.findById(idRol)
                    .orElseThrow(() -> new Exception("El rol especificado no existe en la base de datos"));

            // Pasamos el rol en una lista. JPA hará el INSERT en 'usuarios_roles' automáticamente.
            usuario.setRoles(List.of(rolCliente));

            usuarioService.save(usuario);

            return cliente;

        } catch (Exception e){
            throw new Exception("Ocurrió un error al registrar la transacción: " + e.getMessage(), e);
        }
    }

    /**
     * Métodos auxiliares para extraer subcadenas evitando errores si la longitud es menor a la esperada
     */
    private String extraerSubcadena(String texto, int inicio, int longitud) {
        if (texto == null || texto.isEmpty()) return "";
        return texto.substring(inicio, Math.min(texto.length(), longitud));
    }

    private String extraerSubcadenaFinal(String texto, int caracteresAlFinal) {
        if (texto == null || texto.isEmpty()) return "";
        int inicio = Math.max(0, texto.length() - caracteresAlFinal);
        return texto.substring(inicio);
    }

    public Optional<Persona> getClientById(Integer clientId){
        return clienteRepository.getClienteById(clientId);
    }

    public Persona update(Persona persona){
        return clienteRepository.save(persona);
    }

    public boolean delete(Integer clientId){
        Optional<Persona> client = clienteRepository.getClienteById(clientId);
        if (client.isPresent()){
            Persona clientDelete = client.get();
            clientDelete.setEstado("I");
            clienteRepository.save(clientDelete);
            return true;
        }else {
            return false;
        }
    }
}