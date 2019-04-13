package br.com.caelum.ingresso.controller;

import br.com.caelum.ingresso.dao.CompraDao;
import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Carrinho;
import br.com.caelum.ingresso.model.Cartao;
import br.com.caelum.ingresso.model.form.CarrinhoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CompraController {

    @Autowired
    private SessaoDao sessaoDao;

    @Autowired
    private LugarDao lugarDao;

    @Autowired
    private Carrinho carrinho;

    @Autowired
    private CompraDao compraDao;

    @PostMapping("/compra/ingressos")
    public ModelAndView enviarParaPagamento(CarrinhoForm carrinhoForm) {


        carrinhoForm.toIngressos(sessaoDao, lugarDao)
                .forEach(carrinho::add);

        ModelAndView modelAndView = new ModelAndView("redirect:/compra");

        return modelAndView;
    }

    @GetMapping("/compra")
    public ModelAndView checkout(Cartao cartao) {
        ModelAndView modelAndView = new ModelAndView("compra/pagamento");

        modelAndView.addObject("carrinho", carrinho);

        return modelAndView;
    }

    @Transactional
    @PostMapping("/compra/comprar")
    public ModelAndView comprar(Cartao cartao, BindingResult result) {

        ModelAndView modelAndView = new ModelAndView("redirect:/");

        if (!cartao.isValido()) {
            result.rejectValue("vencimento", "Vencimento inválido");
            return checkout(cartao);
        }

        compraDao.save(carrinho.toCompra());

        carrinho.limpa();

        return modelAndView;
    }
}
