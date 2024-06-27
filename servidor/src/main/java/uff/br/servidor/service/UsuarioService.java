package uff.br.servidor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uff.br.servidor.model.Usuario;
import uff.br.servidor.model.UsuarioStatus;
import uff.br.servidor.repository.UsuarioRepository;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();

    }

    public Usuario buscarUsuarioPorId(UUID id) {
        return usuarioRepository.findById(id).orElse(null);

    }

    public Usuario salvarUsuario(Usuario usuario) {
        usuario.setStatus(UsuarioStatus.ATIVO);
        return usuarioRepository.save(usuario);

    }

    public Usuario atualizarUsuario(UUID id, Usuario usuarioAtualizado) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setStatus(usuarioAtualizado.getStatus());
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setCpf(usuarioAtualizado.getCpf());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setSenha_hash(usuarioAtualizado.getSenha_hash());
            usuario.setRole(usuarioAtualizado.getRole());
        }
        return null;

    }

    public void deletarUsuario(UUID id) {
        usuarioRepository.deleteById(id);
    }

}
