package io.lindstrom.m3u8.parser;

import io.lindstrom.m3u8.model.CueOutCont;

import java.util.Map;

/*
 * #EXT-X-CUE-OUT-CONT:<attribute-list>
 */
public enum CueOutContAttribute implements Attribute<CueOutCont, CueOutCont.Builder> {
    ELAPSED_TIME {
        @Override
        public void read(CueOutCont.Builder builder, String value) {
            builder.elapsedTime(Double.parseDouble(value));
        }

        @Override
        public void write(CueOutCont cueOutCont, TextBuilder textBuilder) {
            cueOutCont.elapsedTime().ifPresent(elapsedTime -> textBuilder.add(camelCaseKey(), elapsedTime));
        }
    },

    DURATION {
        @Override
        public void read(CueOutCont.Builder builder, String value) {
            builder.duration(Double.parseDouble(value));
        }

        @Override
        public void write(CueOutCont cueOutCont, TextBuilder textBuilder) {
            cueOutCont.duration().ifPresent(duration -> textBuilder.add(camelCaseKey(), duration));
        }
    },

    SCTE35 {
        @Override
        public void read(CueOutCont.Builder builder, String value) {
            builder.scte35(value);
        }

        @Override
        public void write(CueOutCont cueOutCont, TextBuilder textBuilder) {
            cueOutCont.scte35().ifPresent(scte35 -> textBuilder.add(name(), scte35));
        }
    };

    final static Map<String, CueOutContAttribute> attributeMap = ParserUtils.toMap(values(), value -> {
        if (SCTE35 == value) {
            return value.name();
        } else {
            return value.camelCaseKey();
        }
    });

    static CueOutCont parse(String attributes, ParsingMode parsingMode) throws PlaylistParserException {
        CueOutCont.Builder builder = CueOutCont.builder();
        ParserUtils.readAttributes(attributeMap, attributes, builder, parsingMode);
        return builder.build();
    }
}
