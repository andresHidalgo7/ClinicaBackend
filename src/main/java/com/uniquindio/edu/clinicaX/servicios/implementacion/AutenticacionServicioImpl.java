package com.uniquindio.edu.clinicaX.servicios.implementacion;

import com.uniquindio.edu.clinicaX.dto.LoginDTO;
import com.uniquindio.edu.clinicaX.dto.TokenDTO;
import com.uniquindio.edu.clinicaX.model.Cuenta;
import com.uniquindio.edu.clinicaX.model.Medico;
import com.uniquindio.edu.clinicaX.model.Paciente;
import com.uniquindio.edu.clinicaX.repositorios.CuentaRepo;
import com.uniquindio.edu.clinicaX.servicios.interfaces.AutenticacionServicio;
import com.uniquindio.edu.clinicaX.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutenticacionServicioImpl implements AutenticacionServicio {

    private final CuentaRepo cuentaRepo;
    private final JWTUtils jwtUtils;

    @Override
    public TokenDTO login(LoginDTO loginDTO) throws Exception {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Optional <Cuenta> cuentaOptional = cuentaRepo.findByCorreo(loginDTO.email());

        if(cuentaOptional.isEmpty()){
            throw new Exception("No existe el correo ingresado");
        }
        Cuenta cuenta = cuentaOptional.get();
        if( !passwordEncoder.matches(loginDTO.conrasenia(), cuenta.getPasswd()) ) {
            throw new Exception("La contraseña ingresada es incorrecta");
        }
        return new TokenDTO( crearToken(cuenta) );
    }

    private String crearToken(Cuenta cuenta) {

        String rol;
        String nombre;
        int codicoCuenta = cuenta.getCodigo();

        if( cuenta instanceof Paciente){
            rol = "paciente";
            nombre = ((Paciente) cuenta).getNombre();
        }else if( cuenta instanceof Medico){
            rol = "medico";
            nombre = ((Medico) cuenta).getNombre();
        } else{
            rol = "admin";
            nombre = "Administrador";
        }

        Map<String, Object> map = new HashMap<>();
        map.put("rol", rol);
        map.put("nombre", nombre);
        map.put("id", cuenta.getCodigo());

        return jwtUtils.generarToken(cuenta.getCorreo(), map);
    }



    @Override
    public void recuperarContrasenia(LoginDTO loginDTO) throws Exception {

    }
}
