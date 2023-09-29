package br.com.renato.taskflow.controller.dto.serializer;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.com.renato.taskflow.controller.dto.lista.ReadListaDTO;

public class ReadListaDTOSerializer extends JsonSerializer<ReadListaDTO> {

    @Override
    public void serialize(ReadListaDTO readListaDTO, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", readListaDTO.id());
        jsonGenerator.writeStringField("titulo", readListaDTO.titulo());
        jsonGenerator.writeNumberField("quadroId", readListaDTO.quadroId());
        jsonGenerator.writeStringField("dataCriacao", readListaDTO.dataCriacao().toString());

        if (readListaDTO.tarefas().isPresent() && !readListaDTO.tarefas().get().isEmpty()) {
            jsonGenerator.writeObjectField("tarefas", readListaDTO.tarefas().get());
        }

        jsonGenerator.writeEndObject();
    }

}
