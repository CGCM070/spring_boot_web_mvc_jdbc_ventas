package org.iesvdm.controlador;

import jakarta.validation.Valid;
import org.iesvdm.dto.ClienteDTO;
import org.iesvdm.dto.ComercialDTO;
import org.iesvdm.dto.PedidoDTO;
import org.iesvdm.modelo.Cliente;
import org.iesvdm.service.ClienteService;
import org.iesvdm.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @Autowired
    private PedidoService pedidoService;


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
    public String submitCrear(@Valid  @ModelAttribute("cliente") Cliente cliente , BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            return "clientes/crear-clientes";
        }

        clienteService.newCliente(cliente);

        return "redirect:/clientes";

    }

    @GetMapping("/clientes/detalles/{id}")
    public String infoCliente(Model model, @PathVariable int id) {

        Optional<ClienteDTO> clienteDTO = clienteService.obtenerDatosCliente(id);
        
        if (clienteDTO.isPresent()){
            model.addAttribute("clienteDetalle" , clienteDTO.get());
        }else {
            model.addAttribute("error", "Cliente no econtrado");
        }

        int cantidadPedido = clienteService.cantidadPedidos(id);
        model.addAttribute("cantidadPedido", cantidadPedido);

        List<ComercialDTO> listaConteoComercial = pedidoService.getComercialesYConteoDePedidos(id);
        model.addAttribute("listaConteoComercial", listaConteoComercial);

        List<PedidoDTO> listaPedidoPorFechas = pedidoService.resumenComercialPedidoFechas(id);
        model.addAttribute("listaPedidoPorFechas", listaPedidoPorFechas);

        return "/clientes/detalle-clientes";
    }


    @GetMapping("/clientes/editar/{id}")
    public String editar(   Model model, @PathVariable Integer id) {

        Cliente cliente = clienteService.findById(id);

        model.addAttribute("cliente", cliente);

        return "/clientes/editar-clientes";
    }

    @PostMapping("/clientes/editar/{id}")
    public String editarSubmit( @Valid   @ModelAttribute("cliente") Cliente cliente , BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            return "clientes/editar-clientes";
        }

        clienteService.replaceCliente(cliente);
        return "redirect:/clientes";
    }


    @PostMapping("/clientes/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id) {

        clienteService.deleteCliente(id);

        return new RedirectView("/clientes");
    }


}
