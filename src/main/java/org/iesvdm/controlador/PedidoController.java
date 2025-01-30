package org.iesvdm.controlador;

import jakarta.validation.Valid;
import org.iesvdm.modelo.Cliente;
import org.iesvdm.modelo.Comercial;
import org.iesvdm.modelo.Pedido;
import org.iesvdm.service.ClienteService;
import org.iesvdm.service.ComercialService;
import org.iesvdm.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    ComercialService comercialService;

    @Autowired
    ClienteService clienteService;

    @GetMapping("/pedidos")
    public String listar (Model model){

        List<Pedido> pedidoList = pedidoService.listAll();
        model.addAttribute("pedidoList",pedidoList);

        return "/pedidos/lista-pedidos";
    }



    @GetMapping("/pedidos/crear")
    public String crear(Model model) {

        Pedido pedido = new Pedido();
        List<Comercial> comercialList = comercialService.listAll();
        List<Cliente> clienteList = clienteService.listAll();

        model.addAttribute("comercialList", comercialList);
        model.addAttribute("clienteList", clienteList);
        model.addAttribute("pedido", pedido);

        return "pedidos/crear-pedido";
    }




    @PostMapping("/pedidos/crear")
    public String submitCrear( @ModelAttribute("pedido") Pedido pedido) {

        pedidoService.newPedido(pedido);

        return "redirect:/pedidos";

    }

}
