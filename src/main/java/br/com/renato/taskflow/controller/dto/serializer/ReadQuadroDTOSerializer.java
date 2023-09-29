package br.com.renato.taskflow.controller.dto.serializer;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.com.renato.taskflow.controller.dto.quadro.ReadQuadroDTO;

public class ReadQuadroDTOSerializer extends JsonSerializer<ReadQuadroDTO> {

    @Override
    public void serialize(ReadQuadroDTO readQuadroDTO, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", readQuadroDTO.id());
        jsonGenerator.writeStringField("titulo", readQuadroDTO.titulo());
        jsonGenerator.writeStringField("descricao", readQuadroDTO.descricao());
        jsonGenerator.writeStringField("dataCriacao", readQuadroDTO.dataCriacao().toString());

        if (readQuadroDTO.listas().isPresent() && !readQuadroDTO.listas().get().isEmpty()) {
            jsonGenerator.writeObjectField("listas", readQuadroDTO.listas().get());
        }

        jsonGenerator.writeEndObject();
    }

}
