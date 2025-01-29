package org.iesvdm.controlador;


import org.iesvdm.dto.ClienteDTO;
import org.iesvdm.dto.ComercialDTO;
import org.iesvdm.dto.PedidoDTO;
import org.iesvdm.modelo.Comercial;
import org.iesvdm.service.ClienteService;
import org.iesvdm.service.ComercialService;
import org.iesvdm.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
public class ComercialController {

    @Autowired
    ComercialService comercialService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    PedidoService pedidoService;

    @GetMapping("/comercial")
    public String listar(Model model) {

        List<Comercial> listaComercial = comercialService.listAll();
        model.addAttribute("listaComercial", listaComercial);

        return "/comercial/comerciales";
    }

    @GetMapping("/comercial/crear")
    public String crear(Model model) {

        Comercial comercial = new Comercial();

        model.addAttribute("comercial", comercial);

        return "/comercial/crear-comercial";
    }


    @PostMapping("/comercial/crear")
    public RedirectView submitCrear(@ModelAttribute("comercial") Comercial comercial) {

        comercialService.newComercial(comercial);

        return new RedirectView("/comercial");

    }


    @GetMapping("/comercial/editar/{id}")
    public String editar(Model model, @PathVariable Integer id) {

        Comercial comercial = comercialService.findById(id);

        model.addAttribute("comercial", comercial);

        return "/comercial/editar-comercial";
    }

    @PostMapping("/comercial/editar/{id}")
    public RedirectView editarSubmit(@ModelAttribute("comercial") Comercial comercial) {

        comercialService.replaceComercial(comercial);

        return new RedirectView("/comercial");

    }


    @PostMapping("/comercial/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id) {

        comercialService.deleteComercial(id);

        return new RedirectView("/comercial");
    }

    @GetMapping("/comercial/detalles/{id}")
    public String infoComercial(Model model, @PathVariable int id) {

        Comercial comercial = comercialService.findById(id);
        model.addAttribute("comercial", comercial);

        List<PedidoDTO> pedidoDTOList = pedidoService.commercialDetail(id);
        model.addAttribute("pedidoDTOList", pedidoDTOList);


        ComercialDTO comercialDTO = comercialService.totalAndMediaPedidos(id);
        model.addAttribute("comercialDTO", comercialDTO);



        PedidoDTO maxPedido = pedidoDTOList.stream().max(Comparator.comparingDouble(PedidoDTO::getTotal)).orElse(null);
        PedidoDTO minPedido = pedidoDTOList.stream().min(Comparator.comparingDouble(PedidoDTO::getTotal)).orElse(null);

        model.addAttribute("maxPedido", maxPedido);
        model.addAttribute("minPedido", minPedido);

        List<ClienteDTO> listaClienteDto = comercialService.listaPorCuantia(id);
        model.addAttribute("listaClienteDto",listaClienteDto);

        return "/comercial/detalle-comercial2";
    }

}
