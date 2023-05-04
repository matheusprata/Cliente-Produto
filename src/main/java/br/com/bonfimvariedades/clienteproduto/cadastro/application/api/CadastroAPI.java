package br.com.bonfimvariedades.clienteproduto.cadastro.application.api;

import br.com.bonfimvariedades.clienteproduto.cadastro.application.api.request.CadastroAlteracaoRequest;
import br.com.bonfimvariedades.clienteproduto.cadastro.application.api.request.CadastroRequest;
import br.com.bonfimvariedades.clienteproduto.cadastro.application.api.response.CadastroDetalhadoResponse;
import br.com.bonfimvariedades.clienteproduto.cadastro.application.api.response.CadastroIdResponse;
import br.com.bonfimvariedades.clienteproduto.cadastro.application.api.response.CadastroListResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/v1/cadastro")
public interface CadastroAPI {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    CadastroIdResponse saveCadastro(@Valid @RequestBody CadastroRequest cadastroRequest);

    @PostMapping(value = "/orcamento")
    @ResponseStatus(code = HttpStatus.CREATED)
    CadastroIdResponse saveCadastroByOrcamento(@RequestParam String cpf);

    @GetMapping("/all")
    @ResponseStatus(code = HttpStatus.OK)
    List<CadastroListResponse> getAllCadastros();

    @GetMapping(value = "/{idCadastro}")
    @ResponseStatus(code = HttpStatus.OK)
    CadastroDetalhadoResponse getOneCadastro(@PathVariable UUID idCadastro);

    @DeleteMapping(value = "/{idCadastro}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void deleteCadastro(@PathVariable UUID idCadastro);

    @PatchMapping(value = "/{idCadastro}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void updateCadastro(@PathVariable UUID idCadastro,
                         @Valid @RequestBody CadastroAlteracaoRequest cadastroAlteracaoRequest);

    @PatchMapping("/finaliza-cadastro/{idCadastro}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void finalizaCadastro(@PathVariable UUID idCadastro);

    @PatchMapping("/ativa-cadastro/{idCadastro}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void ativaCadastro(@PathVariable UUID idCadastro);

    @PatchMapping("/cancela-cadastro/{idCadastro}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void cancelaCadastro(@PathVariable UUID idCadastro);
}
