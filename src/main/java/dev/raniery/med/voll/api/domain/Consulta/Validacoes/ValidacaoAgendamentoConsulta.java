package dev.raniery.med.voll.api.domain.Consulta.Validacoes;

import dev.raniery.med.voll.api.domain.Consulta.DadosAgendamentoConsulta;

public interface ValidacaoAgendamentoConsulta {

    void validar(DadosAgendamentoConsulta dados);
}
