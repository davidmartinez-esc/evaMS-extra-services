package evaMS.rdringresoservice.services;


import evaMS.rdringresoservice.entities.DescuentoAplicadoEntity;
import evaMS.rdringresoservice.repositories.DescuentoAplicadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DescuentoAplicadoService {
    @Autowired
    DescuentoAplicadoRepository descuentoAplicadoRepository;

    public ArrayList<DescuentoAplicadoEntity> getDescuentosAplicados(){
        return (ArrayList<DescuentoAplicadoEntity>) descuentoAplicadoRepository.findAll();
    }

    public DescuentoAplicadoEntity saveDescuentoAplicado(DescuentoAplicadoEntity descuentoAplicado){
        return descuentoAplicadoRepository.save(descuentoAplicado);
    }


    public DescuentoAplicadoEntity updateDescuentoAplicado(DescuentoAplicadoEntity descuentoAplicado) {
        return descuentoAplicadoRepository.save(descuentoAplicado);
    }

    public boolean deleteDescuentoAplicado(Long id) throws Exception {
        try{
            descuentoAplicadoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

}
