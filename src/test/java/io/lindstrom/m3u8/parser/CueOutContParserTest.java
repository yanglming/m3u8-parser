package io.lindstrom.m3u8.parser;

import io.lindstrom.m3u8.model.CueOutCont;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CueOutContParserTest {
    private CueOutCont cueOutCont = CueOutCont.builder()
            .elapsedTime(40)
            .duration(50)
            .scte35("/DAlAAAAAAAAA")
            .build();

    private String attributes = "ElapsedTime=40.0," +
            "Duration=50.0," +
            "IV=0xc055ee9f6c1eb7aa50bfab02b0814972," +
            "SCTE35=/DAlAAAAAAAAA";

    @Test
    public void parseAttributes() throws Exception {
        assertEquals(cueOutCont, CueOutContAttribute.parse(attributes, ParsingMode.STRICT));
    }

    @Test
    public void writeAttributes() {
        assertEquals(attributes, writeAttributes(cueOutCont));
    }

    private String writeAttributes(CueOutCont cueOutCont) {
        return new TextBuilder()
                .addTag("EXT-X-CUE-OUT-CONT", cueOutCont, CueOutContAttribute.attributeMap)
                .toString()
                .substring(20)
                .trim();
    }
}
