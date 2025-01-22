package org.iesvdm.controlador;

import org.iesvdm.dto.ClienteDTO;
import org.iesvdm.modelo.Cliente;
import org.iesvdm.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @GetMapping("/clientes")
    public String listar(Model model) {

        List<Cliente> listaClientes = clienteService.listAll();
        model.addAttribute("listaClientes", listaClientes);

        return "/clientes/clientes";

    }

    @GetMapping("/clientes/crear")
    public String crear(Model model) {

        Cliente cliente = new Cliente();

        model.addAttribute("cliente", cliente);

        return "/clientes/crear-clientes";
    }


    @PostMapping("/clientes/crear")
    public RedirectView submitCrear(@ModelAttribute("cliente") Cliente cliente) {

        clienteService.newCliente(cliente);

        return new RedirectView("/clientes");

    }

    @GetMapping("/clientes/detalles/{id}")
    public String infoCliente(Model model, @PathVariable int id) {

        Optional<ClienteDTO>clienteDTO = clienteService.obtenerDatosCliente( id);
        if (clienteDTO.isPresent()){
            model.addAttribute("clienteDTO" , clienteDTO.get());
        }else {
            model.addAttribute("error", "Cliente no econtrado");
        }
        return "/clientes/detalle-clientes";
    }


    @GetMapping("/clientes/editar/{id}")
    public String editar(Model model, @PathVariable Integer id) {

        Cliente cliente = clienteService.findById(id);

        model.addAttribute("cliente", cliente);

        return "/clientes/editar-clientes";
    }

    @PostMapping("/clientes/editar/{id}")
    public RedirectView editarSubmit(@ModelAttribute("cliente") Cliente cliente) {
        clienteService.replaceCliente(cliente);

        return new RedirectView("/clientes");

    }


    @PostMapping("/clientes/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id) {

        clienteService.deleteCliente(id);

        return new RedirectView("/clientes");
    }


}
