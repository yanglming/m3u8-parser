package io.lindstrom.m3u8.model;

import org.immutables.value.Value;

import java.util.Optional;

/**
 *  Cue Out Cont (EXT-X-CUE-OUT-CONT)
 */
@Value.Immutable
public interface CueOutCont {
    Optional<Double> elapsedTime();

    Optional<Double> duration();

    Optional<String> scte35();

    static Builder builder() {
        return new Builder();
    }

    class Builder extends CueOutContBuilder {}
}
