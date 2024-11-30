package dev.raniery.med.voll.api.Medico;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;

@JsonDeserialize(using = EspecialidadeDeserializer.class)
public enum Especialidade {
    ORTOPEDIA,
    CARDIOLOGIA,
    GINECOLOGIA,
    PEDIATRIA,
    DERMATOLOGIA,
    CANCEROLOGIA,
    NEUROLOGIA,
    PSICOLOGIA,
    PSIQUIATRIA,
    OFTALMOLOGIA,
    UROLOGIA,
    ENDOCRINOLOGIA
}

class EspecialidadeDeserializer extends JsonDeserializer<Especialidade> {
    @Override
    public Especialidade deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText().toUpperCase();
        return Especialidade.valueOf(value);
    }
}