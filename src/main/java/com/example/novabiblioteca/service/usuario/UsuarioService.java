package com.example.novabiblioteca.service.usuario;

import com.example.novabiblioteca.model.usuario.DadosUsuario;
import com.example.novabiblioteca.model.usuario.Usuario;
import com.example.novabiblioteca.model.usuario.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {
    private UsuarioRepository repository;
    public UsuarioService(UsuarioRepository repository){this.repository = repository; }
    public void salvar(Usuario usuario) { this.repository.save(usuario);}
    public List<Usuario> listar(){ return this.repository.findAll();}
    public Usuario findByUUID(String uuid){
        UUID uuidformatado = UUID.fromString(uuid);
        return this.repository.findUsuarioByUuid(uuidformatado);}
    public void atualizarUUID(Usuario usuario){
        Usuario u = this.repository.findUsuarioByUuid(usuario.getUuid());
        u.setNomeUser(usuario.getNomeUser());
        u.setEmail(usuario.getEmail());
        u.setSenha(usuario.getSenha());
        u.setCpf(usuario.getCpf());
        u.setTelefone(usuario.getTelefone());
        u.setPermissao(usuario.getPermissao());
        this.repository.save(u);
    }
    public void excluirUUID(String uuid){ this.repository.deleteUsuarioByUuid(UUID.fromString(uuid));}

    public void cadastrar(Usuario usuario){
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        this.repository.save(usuario);
    }

    public DadosUsuario findUsuario(Long id){
        Usuario usuario = this.repository.getReferenceById(id);
        return new DadosUsuario(usuario);
    }

    public List<DadosUsuario> findAllUsuarios(){
        return this.repository.findAll().stream().map(DadosUsuario::new).toList();
    }
}
